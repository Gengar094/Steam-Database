package model;

public class InGroupModel {
    private final String gname;
    private final String player_id;

    public InGroupModel(String gname, String player_id){
        this.gname = gname;
        this.player_id = player_id;
    }

    public String getGname(){return gname;}
    public String getPlayer_id(){return player_id;}
}
