package es.uma.is;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LoadData {
    private String filePath;

    public LoadData(String filePath){

        this.filePath = filePath;
    }

    public ArrayList<Compound> readAllData()
    {
        ArrayList<Compound> compounds = new ArrayList<>();
        List<String[]> compounds_csv = readCSV(filePath + "compounds.csv");
        assert compounds_csv != null;
        for (String[] row : compounds_csv) {
            if (row.length==3) {
                compounds.add(new Compound(new Integer(row[0]),row[2],filePath));
            }else{
                compounds.add(new Compound(new Integer(row[0]),filePath));
            }
        }
        return compounds;
    }

    public static List<String[]> readCSV(String file){
        try {
            // Create an object of file reader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);

            // create csvReader object and skip first Line
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();
            return allData;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
