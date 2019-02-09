package es.uma.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Compound {
    private int id;
    private String definition;
    private String filepath;
    private ArrayList<ChemicalData> chemData = new ArrayList<>();
    private ArrayList<ChemicalName> chemName = new ArrayList<>();
    private ArrayList<AccessionNumber> accesNum = new ArrayList<>();

    public Compound(int id,String definition, String filepath){
        this.id = id;
        this.definition = definition;
        this.filepath = filepath;
        loadRelations();
    }
    public Compound(int id,String filepath){
        this.id = id;
        this.filepath = filepath;
        loadRelations();
    }

    private void loadRelations(){
        addAccessionNumber();
        addChemicalData();
        addChemicalName();
    }

    private void addChemicalData(){
        List<String[]> chemDataCVS = LoadData.readCSV(filepath + "chemical_data.csv");
        for (String[] row : chemDataCVS) {
            if(new Integer(row[1]).equals(id)){
                chemData.add(new ChemicalData(row[2],row[3],row[4]));
            }
        }
    }

    private void addChemicalName(){
        List<String[]> chemNameCVS = LoadData.readCSV(filepath + "names.csv");
        for (String[] row : chemNameCVS) {
            if(new Integer(row[1]).equals(id)){
                chemName.add(new ChemicalName(row[2],row[3],row[4],row[5],row[6]));
            }
        }
    }

    private void addAccessionNumber(){
        List<String[]> accessNumCVS = LoadData.readCSV(filepath + "database_accession.csv");
        for (String[] row : accessNumCVS) {
            if(new Integer(row[1]).equals(id)){
                accesNum.add(new AccessionNumber(row[2],row[3]));
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getDefinition() {
        return definition;
    }

    public ArrayList<ChemicalData> getChemData() {
        return chemData;
    }

    public ArrayList<ChemicalName> getChemName() {
        return chemName;
    }

    public ArrayList<AccessionNumber> getAccesNum() {
        return accesNum;
    }
}
