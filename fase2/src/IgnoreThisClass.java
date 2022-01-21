import java.util.Map;

public class IgnoreThisClass {
    public static void main(String[] args) {
        try{
            ConnectionDB c = new ConnectionDB();
            Map<String, Review> us = c.loadReviews();
            for(Review u : us.values()) {
                System.out.println(u.getUserId());
                System.out.println(u.getPlaceId());
                System.out.println(u.getClassification());
                System.out.println(u.getComment());
            }
        }  catch (Exception e){
            System.out.println(e);
        }
    }
}

