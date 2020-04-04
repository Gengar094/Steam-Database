package ca.ubc.cs304.model;

public class TypeTradabilityModel {
    private final String item_type;
    private final boolean tradability;

    public TypeTradabilityModel(String item_type, boolean tradability){
        this.item_type = item_type;
        this.tradability = tradability;
    }
}
