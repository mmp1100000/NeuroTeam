package es.uma.is;

public class ChemicalData {
    private String formula;
    private String source;
    private String type;

    public ChemicalData(String formula, String source, String type){
        this.formula = formula;
        this.source = source;
        this.type = type;
    }

    public String getFormula() {
        return formula;
    }

    public String getSource() {
        return source;
    }

    public String getType() {
        return type;
    }
}
