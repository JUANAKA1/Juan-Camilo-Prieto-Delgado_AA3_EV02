package alpha.zapatos.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
//http://localhost:8081/auth/login
//http://localhost:8081/auth/register
/*    {
        "username": "anaLopez",
        "password": "anaLopez456",
        "firstname": "Ana",
        "lastname": "Lopez",
        "country": "España"
    }*/
  