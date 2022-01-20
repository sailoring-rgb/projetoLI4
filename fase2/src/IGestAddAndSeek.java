import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IGestAddAndSeek {
    boolean login(String name,String password);
    boolean register(String name,String password,String email);
    void logout();
}
