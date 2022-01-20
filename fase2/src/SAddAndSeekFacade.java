public class SAddAndSeekFacade implements IGestAddAndSeek{
    private IGestUser ssUsers;
    private IGestPlace ssPlaces;

    public boolean create_review(String userId, String placeName, float classification, String comment) {
        return false;
    }

    @Override
    public boolean login(String name, String password) {
        return false;
    }

    @Override
    public boolean register(String name, String password, String email) {
        return false;
    }

    @Override
    public void logout() {

    }
}
