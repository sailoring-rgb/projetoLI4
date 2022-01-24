package GuiaTuristicoLN;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class PlacePlanneable extends Place {
    private LocalDateTime start_time;
    private LocalDateTime finish_time;

    public PlacePlanneable(String id, String name, String category, String location, String city, Map<String, Review> reviews, LocalDateTime start_time, LocalDateTime finish_time) {
        super(id, name, category, location, city, reviews);
        this.start_time = start_time;
        this.finish_time = finish_time;
    }

    public PlacePlanneable(PlacePlanneable pp) {
        super(pp);
        this.start_time = pp.getStartTime();
        this.finish_time = pp.getFinishTime();
    }

    public LocalDateTime getStartTime() {
        return this.start_time;
    }

    public LocalDateTime getFinishTime() {
        return this.finish_time;
    }

    public void atualizaPlano(LocalDateTime inicio, LocalDateTime fim) {
        this.start_time = inicio;
        this.finish_time = fim;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlacePlanneable that = (PlacePlanneable) o;
        return Objects.equals(start_time, that.start_time) && Objects.equals(finish_time, that.finish_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start_time, finish_time);
    }
}
