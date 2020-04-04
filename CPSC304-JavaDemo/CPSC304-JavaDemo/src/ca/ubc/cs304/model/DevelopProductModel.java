package ca.ubc.cs304.model;

import java.util.Date;

public class DevelopProductModel {
    private final String app_id;
    private final String dv_name;
    private final String ddate;
    private final String product_name;
    private final int price;
    private final String base_game_name;

    public DevelopProductModel(String app_id, String dv_name, String ddate,
                               String product_name, int price, String base_game_name){
        this.app_id = app_id;
        this.dv_name = dv_name;
        this.ddate = ddate;
        this.product_name = product_name;
        this.price = price;
        this.base_game_name = base_game_name;
    }

    public String getApp_id(){return app_id;}
    public String getDv_name(){return dv_name;}
    public String getDdate(){return ddate;}
    public String getProduct_name(){return product_name;}
    public int getPrice(){return price;}
    public String getBase_game_name(){return base_game_name;}
}
