package nl.vpro.poel;

import nl.vpro.poel.configuration.CasSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication(exclude = VelocityAutoConfiguration.class) // Spring Security CAS brings in Velocity, but we want to use Freemarker as our template engine
@EntityScan(basePackageClasses = { PoelApplication.class, Jsr310JpaConverters.class }) // Remove this annotation after upgrading to Hibernate 5.x, as that natively supports persisting java.time.* classes
@Import(CasSecurityConfiguration.class)
public class PoelApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoelApplication.class, args);
	}
}
