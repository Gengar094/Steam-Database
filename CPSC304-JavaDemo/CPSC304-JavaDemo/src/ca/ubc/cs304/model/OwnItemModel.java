package ca.ubc.cs304.model;

public class OwnItemModel {
    private final String item_id;
    private final String player_id;
    private final String item_type;

    public OwnItemModel(String item_id, String player_id, String item_type){
        this.item_id = item_id;
        this.player_id = player_id;
        this.item_type = item_type;
    }

    public String getItem_id(){return item_id;}
    public String getPlayer_id(){return player_id;}
    public String getItem_type(){return item_type;}
}
