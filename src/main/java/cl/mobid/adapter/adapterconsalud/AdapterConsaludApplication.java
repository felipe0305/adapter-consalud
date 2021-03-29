package cl.mobid.adapter.adapterconsalud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdapterConsaludApplication {
	
	@Bean
	InitBean initBean() {
        return new InitBean();
    }


	public static void main(String[] args) {
		SpringApplication.run(AdapterConsaludApplication.class, args);
	}

}
