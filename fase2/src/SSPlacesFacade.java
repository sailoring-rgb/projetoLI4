import java.util.HashMap;
import java.util.Map;

public class SSPlacesFacade implements IGestPlace{
    private Map<String,Place> places;  // chave: placeId, objeto: Place

    public SSPlacesFacade(Map<String,User> users){
        this.places = new HashMap<>(places);
    }

    public SSPlacesFacade(SSPlacesFacade facade){
        this.places = facade.getPlaces();
    }

    public Map<String,Place> getPlaces() {
        return this.places;
    }
}
