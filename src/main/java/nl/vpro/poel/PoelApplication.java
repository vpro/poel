package nl.vpro.poel;

import nl.vpro.poel.security.CasAuthentication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CasAuthentication.class)
public class PoelApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoelApplication.class, args);
	}
}
