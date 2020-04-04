package ca.ubc.cs304.model;

public class ReviewWritereviewModel {
    private final String review_id;
    private final String rdate;
    private final boolean recommendation;
    private final String player_id;
    private final String app_id;

    public ReviewWritereviewModel(String review_id, String rdate, boolean recommendation,
                                  String player_id, String app_id){
        this.review_id = review_id;
        this.rdate = rdate;
        this.recommendation = recommendation;
        this.player_id = player_id;
        this.app_id = app_id;
    }
}
