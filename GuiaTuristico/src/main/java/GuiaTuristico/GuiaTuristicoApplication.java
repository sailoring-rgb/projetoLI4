package GuiaTuristico;
import GuiaTuristicoLN.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
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
	public IGestPlace sSPlaceFacadeConfiguration() throws SQLException, ClassNotFoundException {
		return new SSPlacesFacade();
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}



	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(GuiaTuristicoApplication.class);
	}

	//@EnableAutoConfiguration(exclude={ErrorMvcAutoConfiguration.class})
	public static void main(String[] args) {
		SpringApplication.run(GuiaTuristicoApplication.class, args);
	}
}
