package model;

public class PlayerModel {
    private final int player_id;
    private final String pname;
    private final String email;
    private final String city;

    public PlayerModel(int player_id, String pname, String email, String city) {
        this.player_id = player_id;
        this.pname = pname;
        this.email = email;
        this.city = city;
    }

    public int getPlayer_id(){return player_id;}
    public String getPname(){return pname;}
    public String getEmail(){return email;}
    public String getCity(){return city;}
}
