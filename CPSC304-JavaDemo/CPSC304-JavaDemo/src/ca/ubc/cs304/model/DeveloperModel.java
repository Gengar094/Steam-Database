package ca.ubc.cs304.model;

public class DeveloperModel {
    private final String dv_name;

    public DeveloperModel(String dv_name){
        this.dv_name = dv_name;
    }

    public String getDv_name(){ return dv_name; }
}
