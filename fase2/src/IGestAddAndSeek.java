import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IGestAddAndSeek {
    boolean login(String name,String password) throws IOException;
    boolean register(String id, String password, String name, String email);
    void logout();
}
