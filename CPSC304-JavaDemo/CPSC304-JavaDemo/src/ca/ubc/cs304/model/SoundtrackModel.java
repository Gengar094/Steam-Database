package ca.ubc.cs304.model;

public class SoundtrackModel {
    private final String app_id;
    private final int total_length;

    public SoundtrackModel(String app_id, int total_length){
        this.app_id = app_id;
        this.total_length = total_length;
    }

    public String getApp_id(){return app_id;}
    public int getTotal_length(){return total_length;}
}
