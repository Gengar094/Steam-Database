package ca.ubc.cs304.model;

public class PlayerGroupModel {
    private final String gname;
    private final int num_mem;
    private final String tag;

    public PlayerGroupModel(String gname, int num_mem, String tag){
        this.gname = gname;
        this.num_mem = num_mem;
        this.tag = tag;
    }
    public String getGname(){return gname;}
    public int getNum_mem(){return num_mem;}
    public String getTag(){return tag;}
}
