import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"phones"}) // znajdzie kontrolery
@EntityScan(basePackages = {"phones"}) // znajdzie mapowanie klasy na tabele
@EnableJpaRepositories(basePackages = {"phones"})
public class Program {
    public static void main(String[] args) {
        SpringApplication.run(Program.class, args);
    }
}
