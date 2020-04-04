package ca.ubc.cs304.model;

public class TradeModel {
    private final String giver_id;
    private final String receiver_id;
    private final String item_id;
    private final String tdate;

    public TradeModel(String giver_id, String receiver_id, String item_id, String tdate){
        this.giver_id = giver_id;
        this.receiver_id = receiver_id;
        this.item_id = item_id;
        this.tdate = tdate;
    }

    public String getGiver_id(){return giver_id;}
    public String getReceiver_id(){return receiver_id;}
    public String getItem_id(){return item_id;}
    public String getTdate(){return tdate;}
}
