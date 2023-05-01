package atlier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}
	/*@Bean
	CommandLineRunner run(ServerRepo serverRepo){
		return args -> {
			serverRepo.save(new Server(null, "789.146.78.111", "Windows ", "24 GB","Manager pc","http://localhost:8081/server/image/serveur2.png"));
			serverRepo.save(new Server(null, "200.146.78.111", "Kali linux", "100 GB","pc","http://localhost:8081/server/image/serveur1.png"));
			serverRepo.save(new Server(null, "1.146.78.111", "Debian linux", "89 GB","laptop","http://localhost:8081/server/image/Serveur4.png"));
			serverRepo.save(new Server(null, "188.146.78.111", "sunn linux", "70 GB","Dell","http://localhost:8081/server/image/serveur1.png"));
			serverRepo.save(new Server(null, "1729.146.78.111", "solaire linux", "60 GB","HP","http://localhost:8081/server/image/Serveur3.png"));
		};
	}*/
}
