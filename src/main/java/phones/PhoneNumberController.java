package phones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PhoneNumberController {

    @Autowired
    private PhoneNumberRepository repository;

    @RequestMapping("/phones")
    @ResponseBody
    public List<PhoneNumber> getAll() {
        return repository.findAll();
    }

    @RequestMapping("/phones/{id}")
    @ResponseBody
    public PhoneNumber getOne(@PathVariable("id") Integer id) {
        //metoda findById zwraca obiekt Optional<PhoneNumber> czyli  ze jesli jest
        //tam obiekt PhoneNumber to go zwróci a jeśli nie to zwróci NULL-a
        return repository.findById(id).orElse(null);
    }

    @RequestMapping("/phones/number/{number}")
    @ResponseBody
    public List<PhoneNumber> getByNumber(@PathVariable("number") String number) {
        return repository.findByNumber(number);
    }

    @RequestMapping("/phones/lastName/{name}")
    @ResponseBody
    public List<PhoneNumber> getByLastName(@PathVariable("name") String lastName) {
        return repository.findByLastName(lastName);
    }

    @RequestMapping("/phones/byLastName")
    @ResponseBody
    public List<PhoneNumber> getByOrderByLastName() {
        return repository.findByOrderByLastName();
    }

    @RequestMapping("/phones/byFirstNameDesc")
    @ResponseBody
    public List<PhoneNumber> getByOrderByFirstNameDesc() {
        return repository.findByOrderByFirstNameDesc();
    }

    @RequestMapping("/phones/size")
    @ResponseBody
    public long size() {
        return repository.count();
    }

    @RequestMapping(value = "/phones", method = RequestMethod.POST)
    @ResponseBody
    public PhoneNumber create(@RequestBody PhoneNumber phoneNumber) {
        phoneNumber.setId(null);
        return repository.save(phoneNumber);
    }

//    //PUT tak naprawde działa jak PATCH czyli czesciowe modyfikacje
//    @RequestMapping(value = "/phones/{id}", method = RequestMethod.PUT)
//    @ResponseBody
//    public PhoneNumber updateOrCreate(@PathVariable("id") Integer id, @RequestBody PhoneNumber phoneNumber) {
//        phoneNumber.setId(id);
//        return repository.save(phoneNumber);
//        //podmienia caly obiekt, powienien tylko nanosic zmiany na obiekt
//    }

    @RequestMapping(value = "/phones/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public PhoneNumber updateOrCreate(@PathVariable("id") Integer id, @RequestBody PhoneNumber phoneNumber) {
        PhoneNumber old = repository.getOne(id);
        if (old == null) {
            return repository.save(phoneNumber);
        }
        //taki obiekt o podanym id jest w repozytorium, wiec modyfikuje częściowo
        if (phoneNumber.getNumber() != null) old.setNumber(phoneNumber.getNumber());
        if (phoneNumber.getLastName() != null) old.setLastName(phoneNumber.getLastName());
        if (phoneNumber.getFirstName() != null) old.setFirstName((phoneNumber.getFirstName()));
        return repository.save(old);
    }

    @RequestMapping(value = "/phones/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer id) {
        repository.deleteById(id);
    }

    @RequestMapping("/phones/name/{lastName}/sort")
    @ResponseBody
    public List<PhoneNumber> getByLastNameOrderByFirstName(@PathVariable("lastName") String lastName) {
        return repository.findAllByLastNameOrderByFirstName(lastName);
    }


}
