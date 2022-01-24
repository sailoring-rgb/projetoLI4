package GuiaTuristicoLN;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Plan {
    private String userId;
    private String name;
    private LocalDateTime start_time;
    private LocalDateTime finish_time;
    private String day;
    private String city;
    private List<PlacePlanneable> places;
    private String startTime;
    private String finishTime;

    public Plan(String userId, String name, LocalDateTime start_time, LocalDateTime finish_time, String day, String city) {
        this.userId = userId;
        this.name = name;
        this.start_time = start_time;
        this.finish_time = finish_time;
        this.day = day;
        this.city = city;
        this.places = new ArrayList<>();
    }

    public Plan(String userId, String name, LocalDateTime start_time, LocalDateTime finish_time, String day, String city, List<PlacePlanneable> places) {
        this.userId = userId;
        this.name = name;
        this.start_time = start_time;
        this.finish_time = finish_time;
        this.day = day;
        this.city = city;
        this.places = new ArrayList<>(places);
    }

    public Plan(Plan plan) {
        this.userId = plan.getUserID();
        this.name = plan.getName();
        this.start_time = plan.getStartTime();
        this.finish_time = plan.getFinishTime();
        this.day = plan.getDay();
        this.city = plan.getCity();
        this.places = plan.getPlaces();
    }

    public Plan(String name, LocalDateTime start_time, LocalDateTime finish_time, String city, String userId) {
        this.userId = userId;
        this.name = name;
        this.start_time = start_time;
        this.finish_time = finish_time;
        this.city = city;
        this.day = start_time.getDayOfWeek().toString();
        this.places = new ArrayList<>();
    }

    public Plan() {

    }

    public String getUserID() {
        return this.userId;
    }

    public String getName() {
        return this.name;
    }

    public LocalDateTime getStartTime() {
        return this.start_time;
    }

    public LocalDateTime getFinishTime() {
        return this.finish_time;
    }

    public String getDay() {
        return this.day;
    }

    public String getCity() {
        return this.city;
    }

    public String getSTime(){ return this.startTime;}

    public String getFTime(){ return this.finishTime;}

    public void setSTime(String sTime){
        this.startTime = sTime;
    }

    public void setFTime(String fTime){
        this.finishTime = fTime;
    }

    public List<PlacePlanneable> getPlaces() {
        return this.places;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userid) {
        this.userId = userid;
    }

    public void setStartTime(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public void setFinishTime(LocalDateTime finish_time) {
        this.finish_time = finish_time;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPlaces(List<PlacePlanneable> places) {
        this.places = new ArrayList<>(places);
    }

    public Plan clone() {
        return new Plan(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return Objects.equals(userId, plan.userId) && Objects.equals(name, plan.name) && Objects.equals(start_time, plan.start_time) && Objects.equals(finish_time, plan.finish_time) && Objects.equals(day, plan.day) && Objects.equals(city, plan.city) && Objects.equals(places, plan.places);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, start_time, finish_time, day, city, places);
    }

    public void addPlaceToPlan(PlacePlanneable p) {
        if (this.places == null) this.places = new ArrayList<>();
        this.places.add(p);
    }

    public void removePlaceFromPlan(PlacePlanneable p) {
        this.places.remove(p);
    }

    public void editaPlaceFromPlano(String placeName, LocalDateTime inicio, LocalDateTime fim) {
        for (PlacePlanneable p : this.places) {
            if (p.getName().equals(placeName)) {
                p.atualizaPlano(inicio, fim);
                break;
            }
        }
    }
}


