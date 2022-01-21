import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Plan {
    private String name;
    private LocalTime start_time;
    private LocalTime finish_time;
    private String day;
    private String city;
    private List<PlacePlanneable> places;

    public Plan(String name,LocalTime start_time,LocalTime finish_time,String day,String city,List<PlacePlanneable> places){
        this.name = name;
        this.start_time = start_time;
        this.finish_time = finish_time;
        this.day = day;
        this.city = city;
        this.places = new ArrayList<>(places);
    }

    public Plan(Plan plan){
        this.name = plan.getName();
        this.start_time = plan.getStartTime();
        this.finish_time = plan.getFinishTime();
        this.day = plan.getDay();
        this.city = plan.getCity();
        this.places = plan.getPlaces();
    }

    public String getName(){
        return this.name;
    }

    public LocalTime getStartTime(){
        return this.start_time;
    }

    public LocalTime getFinishTime(){
        return this.finish_time;
    }

    public String getDay(){
        return this.day;
    }

    public String getCity(){
        return this.city;
    }

    public List<PlacePlanneable> getPlaces(){
        return this.places;
    }

    public Plan clone(){ return new Plan(this);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return Objects.equals(name, plan.name) && Objects.equals(start_time, plan.start_time)
                && Objects.equals(finish_time, plan.finish_time) && Objects.equals(day, plan.day)
                && Objects.equals(city, plan.city) && Objects.equals(places, plan.places);
    }

    public void addPlaceToPlan(PlacePlanneable p){
        if (this.places == null) this.places = new ArrayList<>();
        this.places.add(p);
    }

    public void removePlaceFromPlan(PlacePlanneable p){
        this.places.remove(p);
    }

    public void editaPlaceFromPlano(String placeName, LocalTime inicio, LocalTime fim){
        for(PlacePlanneable p : this.places){
            if (p.getName().equals(placeName)){
                p.atualizaPlano(inicio,fim);
                break;
            }
        }
    }
}


