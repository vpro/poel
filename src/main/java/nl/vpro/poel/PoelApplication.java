package nl.vpro.poel;

import nl.vpro.poel.security.CasSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = VelocityAutoConfiguration.class) // Spring Security CAS brings in Velocity, but we want to use Freemarker as our template engine
@Import(CasSecurityConfiguration.class)
public class PoelApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoelApplication.class, args);
	}
}
