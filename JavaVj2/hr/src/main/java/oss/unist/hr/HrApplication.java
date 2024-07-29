package oss.unist.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//set JAVA_HOME=C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2023.2.5\jbr ili C:\Users\Josip\AppData\Local\Programs\IntelliJ IDEA Community Edition\jbr
//cd /d C:\JavaTestAplikacjie\hr ili cd /d I:\JavaProjekti\JavaTestAplikacjieV2/hr
//mvnw spring-boot:run
//POST http://localhost:8080/api/devices/1/readings
//GET http://localhost:8080/api/devices/1/readings
//POST http://localhost:8080/api/devices
/*{
		"name": "John Doe",
		"address": {
		"street": "123 Main St",
		"city": "YourCity"
		}
}*/

@SpringBootApplication
@EntityScan(basePackages = "oss.unist.hr.model")
public class HrApplication {
	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}
}
