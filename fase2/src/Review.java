public class Review {

    private String userId;
    private String placeId;
    private float classification;
    private String comment;

    public Review(){
        this.userId = "";
        this.placeId = "";
        this.classification = 0;
        this.comment = "";
    }

    public Review(String userId, String placeId, float classification, String comment){
        this.userId = userId;
        this.placeId = placeId;
        this.classification = classification;
        this.comment = comment;
    }

    public Review(Review rev){
        this.userId = rev.getUserId();
        this.placeId = rev.getPlaceId();
        this.classification = rev.getClassification();
        this.comment = rev.getComment();
    }

    public String getUserId(){ return this.userId; }

    public String getPlaceId(){ return this.placeId; }

    public float getClassification(){ return this.classification; }

    public String getComment(){ return this.comment; }

    public Review clone(){ return new Review(this);}
}
