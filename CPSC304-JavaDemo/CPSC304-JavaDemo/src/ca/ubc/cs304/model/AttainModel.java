package ca.ubc.cs304.model;

public class AttainModel {
    private final String ach_id;
    private final String player_id;

    public AttainModel(String ach_id, String player_id){
        this.ach_id = ach_id;
        this.player_id = player_id;
    }

    public String getAch_id(){ return ach_id; }
    public String getPlayer_id(){ return player_id; }
}
