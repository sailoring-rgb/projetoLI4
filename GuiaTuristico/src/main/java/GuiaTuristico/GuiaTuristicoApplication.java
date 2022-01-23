package GuiaTuristico;

import GuiaTuristicoLN.IGestPlace;
import GuiaTuristicoLN.IGestUser;
import GuiaTuristicoLN.SSPlacesFacade;
import GuiaTuristicoLN.SSUserFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class GuiaTuristicoApplication {

	private static IGestUser users;
	private static IGestPlace places;
	/**
	 * MAYBE THIS!!
	public GuiaTuristicoApplication() throws SQLException, ClassNotFoundException {
		this.users = new SSUserFacade();
		this.places = new SSPlacesFacade();
	}
	*/
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		 /** OR THIS!! */
		 users = new SSUserFacade();
		 places = new SSPlacesFacade();
		SpringApplication.run(GuiaTuristicoApplication.class, args);
	}
}
