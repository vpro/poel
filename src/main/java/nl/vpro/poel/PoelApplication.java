package nl.vpro.poel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;

@SpringBootApplication(exclude = VelocityAutoConfiguration.class) // Spring Security CAS brings in Velocity, but we want to use Freemarker as our template engine
public class PoelApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoelApplication.class, args);
	}
}
