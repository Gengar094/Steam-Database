package model;

public class HasPurchaseModel {
    private final String ach_id;
    private final String app_id;

    public HasPurchaseModel(String ach_id, String app_id){
        this.ach_id = ach_id;
        this.app_id = app_id;
    }

    public String getAch_id(){return ach_id;}
    public String getApp_id(){return app_id;}
}
