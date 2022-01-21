import javax.xml.stream.Location;
import java.time.LocalTime;
import java.util.Map;

public class PlacePlanneable extends Place {
    private LocalTime start_time;
    private LocalTime finish_time;

    public PlacePlanneable(String id, String name, String category, Location location, String city, Map<String, Review> reviews, LocalTime start_time, LocalTime finish_time){
        super(id,name,category,location,city,reviews);
        this.start_time = start_time;
        this.finish_time = finish_time;
    }

    public PlacePlanneable(PlacePlanneable pp){
        super(pp);
        this.start_time = pp.getStartTime();
        this.finish_time = pp.getFinishTime();
    }

    public LocalTime getStartTime(){
        return this.start_time;
    }

    public LocalTime getFinishTime(){
        return this.finish_time;
    }

    public void atualizaPlano(LocalTime inicio, LocalTime fim) {
        this.start_time = inicio;
        this.finish_time = fim;
    }
}
