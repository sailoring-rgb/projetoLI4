import java.io.IOException;

public class SAddAndSeekFacade implements IGestAddAndSeek{
    private String currentUser;
    private boolean functional;
    private IGestUser ssUsers;
    private IGestPlace ssPlaces;

    public SAddAndSeekFacade(){
        this.functional = true;
        /**
         * PARSER !!!!
        this.ssUsers = Parser.parseUsers();
        this.ssPlaces = Parser.parsePlaces();
         */
    }

    @Override
    public boolean add_favourite(String userId, String placeId) {
        if(this.ssUsers.getUsers().containsKey(userId)){
            Place place = this.ssPlaces.getPlaces().get(placeId);
            this.ssUsers.getUsers().get(userId).getFavourites().add(place.clone());
            return true;
        }
        return false;
    }

    @Override
    public boolean remove_favourite(String userId, String placeId) {
        if(this.ssUsers.getUsers().containsKey(userId)){
            Place place = this.ssPlaces.getPlaces().get(placeId);
            this.ssUsers.getUsers().get(userId).getFavourites().remove(place);
            return true;
        }
        return false;
    }

    public boolean login(String id,String password) throws IOException {
        boolean res = false;
        if (this.ssUsers.getUsers().containsKey(id)) {
            this.currentUser = id;
            User user = this.ssUsers.getUsers().get(id);
            if (password.equals(user.getPassword())) {
                res = true;
            }
        }
        return res;
    }

    public boolean register(String id, String password, String name, String email){
        if (!this.ssUsers.getUsers().containsKey(id)){
            User user = new User(id,password,name,email);
            this.currentUser = id;
            this.ssUsers.getUsers().put(this.currentUser,user.clone());
            return true;
        }
        return false;
    }

    public void logout() {
        this.functional = false;
    }
}
