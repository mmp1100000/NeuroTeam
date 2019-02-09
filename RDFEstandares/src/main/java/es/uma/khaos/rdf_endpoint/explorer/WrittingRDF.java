package es.uma.khaos.rdf_endpoint.explorer;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;

import java.io.FileWriter;
import java.io.IOException;

public class WrittingRDF {

	// some definitions
	static String URI = "http://standares/clasesIngenieria/";
	static String pathway = "pathway";
	static String pathwayURI = URI + pathway;
	static String name = "Glycolysis";
	static String reaction = "hasReaction";
	static String reactionURI = URI + reaction;
	static String reactionName = URI + name;
	static String location = "hasCellularLocation";
	static String locationURI = URI + location;
	static String cellularLocation = URI + "Mitochondria";


	public static void main(String[] args) {

		// create an empty Model
		Model model = ModelFactory.createDefaultModel();
		Property propertyReaction = model.createProperty( URI + reaction );
		Property propertyLocation = model.createProperty( URI + location);

		// create the resource
		Resource pathway = model.createResource(pathwayURI)
				.addProperty(propertyReaction, reactionName)
				.addProperty(propertyLocation, cellularLocation);

		modelToFile(model,"/Volumes/1TB HDD/Google Drive (UMA)/Ingeniería de la Salud/Asignaturas/EDAID/chebi/comp2.rdf");

		StmtIterator iter = model.listStatements();
		
		while (iter.hasNext()) {
		    
			Statement stmt      = iter.nextStatement();  // get next statement
			Resource subject = stmt.getSubject(); // get the subject
			Property  predicate = stmt.getPredicate();   // get the predicate
		    RDFNode   object    = stmt.getObject();      // get the object
		    System.out.print(subject.toString() + " " );
		    System.out.print(predicate.toString() + " " );
		    System.out.print(object.toString() + " " + "\n");


		}

		// list the statements in the Model
		/*StmtIterator iter = model.listStatements();
		while (iter.hasNext()) {

			Statement stmt = iter.nextStatement(); // get next statement
			Resource subject = stmt.getSubject(); // get the subject
			Property predicate = stmt.getPredicate(); // get the predicate
			RDFNode object = stmt.getObject(); // get the object

			System.out.print(subject.toString());
			System.out.print(" " + predicate.toString() + " ");

			if (object instanceof Resource) {
				System.out.print(object.toString());
			} else {
				// object is a literal
				System.out.print(" \"" + object.toString() + "\"");
			}

			System.out.println(" .");
		}
		
		model.write(System.out, "RDF/XML-ABBREV");
*/
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
