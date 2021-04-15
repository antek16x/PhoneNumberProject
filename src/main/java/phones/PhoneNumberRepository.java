package phones;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {

    public List<PhoneNumber> findByNumber(String number);
    public List<PhoneNumber> findByLastName(String lastName);
    public List<PhoneNumber> findByOrderByLastName();
    public List<PhoneNumber> findByOrderByFirstNameDesc();

//    Zwykły SQL
//    @Query(value = "SELECT * FROM phone_number where last_name = ?1 order by first_name"
//            , nativeQuery = true
//    ?1 - pierwszy parametr z funkcji

//  jezyk JPQL
    @Query(value = "SELECT p FROM PhoneNumber p where p.lastName = ?1 order by p.firstName")
    List<PhoneNumber> findAllByLastNameOrderByFirstName(String lastName);
}
