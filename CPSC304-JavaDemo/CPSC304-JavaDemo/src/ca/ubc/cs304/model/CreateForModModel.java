package ca.ubc.cs304.model;

public class CreateForModModel {
    private final String app_id;
    private final String mod_id;
    private final String player_id;

    public CreateForModModel(String app_id, String mod_id, String player_id){
        this.app_id = app_id;
        this.mod_id = mod_id;
        this.player_id = player_id;
    }
}
