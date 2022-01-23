package GuiaTuristico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class GuiaTuristicoApplication {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SpringApplication.run(GuiaTuristicoApplication.class, args);
	}
}
