package GuiaTuristico;
import GuiaTuristicoLN.SSUserFacade;
import GuiaTuristicoLN.IGestUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;

@SpringBootApplication
public class GuiaTuristicoApplication extends SpringBootServletInitializer {

	@Bean
	public IGestUser sSUserFacadeConfiguration() throws SQLException, ClassNotFoundException {
		return new SSUserFacade();
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(GuiaTuristicoApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(GuiaTuristicoApplication.class, args);
	}
}
