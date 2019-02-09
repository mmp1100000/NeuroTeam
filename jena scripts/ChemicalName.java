package es.uma.is;

public class ChemicalName {
    private String name;
    private String type;
    private String source;
    private String adapted;
    private String language;
    public ChemicalName(String name,String type,String source,String adapted,String language){
        this.name = name;
        this.type = type;
        this.source = source;
        this.adapted = adapted;
        this.language = language;
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    public String getAdapted() {
        return adapted;
    }

    public String getLanguage() {
        return language;
    }
}
