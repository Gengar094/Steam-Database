package ca.ubc.cs304.model;

public class PurchaseModel {
    private final String player_id;
    private final String app_id;
    private final String pdate;

    public PurchaseModel(String player_id, String app_id, String pdate){
        this.player_id = player_id;
        this.app_id = app_id;
        this.pdate = pdate;
    }
}
