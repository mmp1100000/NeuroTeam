package es.uma.is;

public class AccessionNumber {
    private final String accessionNumber;
    private final String type;

    public AccessionNumber(String accessionNumber, String type){
        this.accessionNumber = accessionNumber;
        this.type = type;
    }

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public String getType() {
        return type;
    }
}
