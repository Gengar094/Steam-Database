package model;

public class GameModel {
    private final String app_id;
    private final String genre;

    public GameModel(String app_id, String genre){
        this.app_id = app_id;
        this.genre = genre;
    }

    public String getApp_id(){return app_id;}
    public String getGenre(){return genre;}
}
