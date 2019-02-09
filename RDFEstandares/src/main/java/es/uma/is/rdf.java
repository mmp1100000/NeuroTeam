package es.uma.is;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class rdf{

    public static void main (String args[]) {
        String path = "/Volumes/1TB HDD/Google Drive (UMA)/Ingeniería de la Salud/Asignaturas/EDAID/chebi/";
        ArrayList<Compound> compoundsData = new LoadData(path).readAllData();
        String URI = "http://edaid.mmp.compounds/";
        // create an empty Model
        OntModel model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );

        addCompounds(model, URI,compoundsData);
        // list the statements in the Model
        modelToFile(model, path+"compounds.rdf");

        //model.write(System.out);

    }

    private static void addCompounds(OntModel model, String URI, ArrayList<Compound> compoundsData){
        String definition = "hasDefinition";
        //OntClass compClass = model.createClass(URI + "compound");
        DatatypeProperty propertyDefinition = model.createDatatypeProperty( URI + definition );
        for (Compound comp : compoundsData) {
            String compoundURI = URI + comp.getId();
            if(!comp.getDefinition().equals("")){
                model.createIndividual(compoundURI,model.createResource(compoundURI)).addProperty(propertyDefinition,comp.getDefinition());
            }else{
                model.createIndividual(compoundURI,model.createResource(compoundURI));
            }
            addChemicalNames(model,URI,compoundURI, comp);
            addChemicalData(model,URI,compoundURI, comp);
            addAccessionNumber(model,URI,compoundURI, comp);
        }
    }

    private static void addChemicalNames(OntModel model, String URI, String compoundURI, Compound comp) {
        String names = "hasNames";
        String name = "hasName";
        String nameType = "hasNameType";
        String nameSource = "hasNameSource";
        String nameAdapted = "isNameAdapted";
        String nameLanguage = "hasNameLanguage";

        Property propertyNames = model.createProperty( URI + names );
        DatatypeProperty propertyName = model.createDatatypeProperty( URI + name );
        DatatypeProperty propertyNameType = model.createDatatypeProperty( URI + nameType);
        DatatypeProperty propertyNameSource = model.createDatatypeProperty( URI + nameSource );
        DatatypeProperty propertyNameAdapted = model.createDatatypeProperty( URI + nameAdapted );
        DatatypeProperty propertyLanguage = model.createDatatypeProperty( URI + nameLanguage );


        for (ChemicalName chemNames : comp.getChemName()) {
            model.getIndividual(compoundURI)
                    .addProperty(propertyNames,
                            model.createResource(URI+chemNames.getName())
                                    .addProperty(propertyName,chemNames.getName())
                                    .addProperty(propertyNameType,chemNames.getType())
                                    .addProperty(propertyNameSource,chemNames.getSource())
                                    .addProperty(propertyNameAdapted, chemNames.getAdapted())
                                    .addProperty(propertyLanguage,chemNames.getLanguage())
                    );
        }
    }

    private static void addChemicalData(OntModel model, String URI, String compoundURI, Compound comp){
        String chemicalData = "hasChemicalData";
        String chemicalDataValue = "hasChemicalDataValue";
        String chemicalDataSource = "hasChemicalDataSource";
        String chemicalDataType = "isChemicalDataType";

        Property propertyChemicalData = model.createProperty( URI + chemicalData );
        DatatypeProperty propertyChemicalDataValue = model.createDatatypeProperty( URI + chemicalDataValue );
        DatatypeProperty propertyChemicalDataSource = model.createDatatypeProperty( URI + chemicalDataSource );
        DatatypeProperty propertyChemicalDataType = model.createDatatypeProperty( URI + chemicalDataType );

        for (ChemicalData chemData : comp.getChemData()) {
            model.getIndividual(compoundURI)
                    .addProperty(propertyChemicalData,
                            model.createResource(compoundURI+chemData.getFormula())
                                    .addProperty(propertyChemicalDataValue,chemData.getFormula())
                                    .addProperty(propertyChemicalDataSource,chemData.getSource())
                                    .addProperty(propertyChemicalDataType,chemData.getType())
                    );
        }
    }

    private static void addAccessionNumber(OntModel model, String URI, String compoundURI, Compound comp){
        String accessionData = "hasAccessionData";
        String accessionDataNumber = "hasAccessionDataNumber";
        String accessionDataType = "hasAccessionDataType";

        DatatypeProperty propertyAccessionData = model.createDatatypeProperty( URI + accessionData );
        DatatypeProperty propertyAccessionDataNumber = model.createDatatypeProperty( URI + accessionDataNumber );
        DatatypeProperty propertyAccessionDataType = model.createDatatypeProperty( URI + accessionDataType );


        for (AccessionNumber accData : comp.getAccesNum()) {
            model.getIndividual(compoundURI)
                    .addProperty(propertyAccessionData,
                            model.createResource(compoundURI+accData.getAccessionNumber())
                                    .addProperty(propertyAccessionDataNumber,accData.getAccessionNumber())
                                    .addProperty(propertyAccessionDataType,accData.getType())
                    );
        }
    }

    private static void modelToFile(Model model, String filePath){
        FileWriter out = null;
        try {
            out = new FileWriter( filePath );
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            model.write( out, "RDF/XML-ABBREV" );
        }
        finally {
            try {
                out.close();
            }
            catch (IOException closeException) {
                // ignore
            }
        }
    }
}


