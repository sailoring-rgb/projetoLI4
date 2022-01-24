package GuiaTuristicoLN;

public interface Reviewable {
    void add_review(Review r);

    void remove_review(String userId, String placeId);
}
