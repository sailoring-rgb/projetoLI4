package GuiaTuristicoLN;

import java.io.IOException;
import java.sql.SQLException;

public interface IGestAddAndSeek {

    boolean add_favourite(String userId, String placeId);

    boolean remove_favourite(String userId, String placeId);

    void logout() throws SQLException, ClassNotFoundException;
}
