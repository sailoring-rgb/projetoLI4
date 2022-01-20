import java.util.HashMap;
import java.util.Map;

public class SSUserFacade implements IGestUser{
    private Map<String,User> users;    // chave: userId, objeto: user

    public SSUserFacade(Map<String,User> users){
        this.users = new HashMap<>(users);
    }

    public SSUserFacade(SSUserFacade facade){
        this.users = facade.getUsers();
    }

    public Map<String,User> getUsers() {
        return this.users;
    }
}
