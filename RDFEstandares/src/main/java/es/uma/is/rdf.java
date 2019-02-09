package es.uma.is;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.*;
import es.uma.khaos.rdf_endpoint.explorer.MySQLConnection;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class rdf{
    static final String URI = "http://edaid.neuroteam.ontology/";

    public static void main (String args[]) {
        String path = "/Volumes/1TB HDD/Google Drive (UMA)/Ingeniería de la Salud/Asignaturas/EDAID/chebi/";
        MySQLConnection conn = new MySQLConnection("localhost","3306","neuroteam","root","01aMarco");
        ArrayList<Compound> compoundsData = new LoadData(path).readAllData();

        // create an empty Model
        OntModel model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
        createRDFSchema(model);
        createPatientIndividuals(model,conn);
        createDoctorIndividuals(model,conn);
        createTreatmentIndividuals(model,conn);
        createMedicalFileIndividuals(model,conn);
        createReportIndividuals(model,conn);
        createMedicalTestIndividuals(model,conn);
        createPatientDoctorPredicates(model,conn);
        createReportPatDocPredicates(model,conn);
        createHasTreatmentPredicates(model,conn);
        createHasTestPredicates(model,conn);
        createHasFilePredicates(model,conn);
        createHasDiagnosisPredicates(model,conn);
        //addCompounds(model, URI,compoundsData);
        // list the statements in the Model
            modelToFile(model, path+"neuroteam.rdf");

        //model.write(System.out);

    }

    private static void createRDFSchema(OntModel model){
      // Crear clases
        //OntClass persona = model.createClass(URI + "persona");
        OntClass paciente = model.createClass(URI + "paciente");
        //persona.addSubClass(paciente);
        OntClass medico = model.createClass(URI + "medico");
        //persona.addSubClass(medico);
        paciente.addDisjointWith(medico);
        OntClass tratamiento = model.createClass(URI + "tratamiento");
        OntClass informe = model.createClass(URI + "informe");
        OntClass archivoMedico = model.createClass(URI + "archivoMedico");
        OntClass pruebaMedica = model.createClass(URI + "pruebaMedica");
        OntClass enfermedad = model.createClass(URI + "enfermedad");

      // Crear Data Properties
        // persona
        DatatypeProperty tieneNombrePaciente = model.createDatatypeProperty( URI + "tieneNombrePaciente" );
        tieneNombrePaciente.addDomain(paciente);
        DatatypeProperty tieneNombreMedico = model.createDatatypeProperty( URI + "tieneNombreMedico" );
        tieneNombreMedico.addDomain(medico);
        DatatypeProperty tieneApellidosPaciente = model.createDatatypeProperty( URI + "tieneApellidosPaciente" );
        tieneApellidosPaciente.addDomain(paciente);
        DatatypeProperty tieneApellidosMedico = model.createDatatypeProperty( URI + "tieneApellidosMedico" );
        tieneApellidosMedico.addDomain(medico);
        // paciente
        DatatypeProperty tieneNSS = model.createDatatypeProperty( URI + "tieneNSS" );
        tieneNSS.addDomain(paciente);
        DatatypeProperty tieneGenero = model.createDatatypeProperty( URI + "tieneGenero" );
        tieneGenero.addDomain(paciente);
        DatatypeProperty tieneAltura = model.createDatatypeProperty( URI + "tieneAltura" );
        tieneAltura.addDomain(paciente);
        DatatypeProperty tienePeso = model.createDatatypeProperty( URI + "tienePeso" );
        tienePeso.addDomain(paciente);
        DatatypeProperty tieneFechaNacimiento = model.createDatatypeProperty( URI + "tieneFechaNacimiento" );
        tieneFechaNacimiento.addDomain(paciente);
        // medico
        DatatypeProperty tieneLicencia = model.createDatatypeProperty( URI + "tieneLicencia" );
        tieneLicencia.addDomain(medico);
        // informe
        DatatypeProperty tieneSintomas = model.createDatatypeProperty( URI + "tieneSintomas" );
        tieneSintomas.addDomain(informe);
        DatatypeProperty tieneFechaInforme = model.createDatatypeProperty( URI + "tieneFechaInforme" );
        tieneFechaInforme.addDomain(informe);
        // tratamiento
        DatatypeProperty tieneFechaInicio = model.createDatatypeProperty( URI + "tieneFechaInicio" );
        tieneFechaInicio.addDomain(tratamiento);
        DatatypeProperty tieneFechaFin = model.createDatatypeProperty( URI + "tieneFechaFin" );
        tieneFechaFin.addDomain(tratamiento);
        DatatypeProperty tieneDescripcion = model.createDatatypeProperty( URI + "tieneDescripcion" );
        tieneDescripcion.addDomain(tratamiento);
        DatatypeProperty tienePosologia = model.createDatatypeProperty( URI + "tienePosologia" );
        tienePosologia.addDomain(tratamiento);
        // archivoMedico
        DatatypeProperty tieneDocumento = model.createDatatypeProperty( URI + "tieneDocumento" );
        tieneDocumento.addDomain(archivoMedico);
        //pruebaMedica
        DatatypeProperty tieneFechaPrueba = model.createDatatypeProperty( URI + "tieneFechaPrueba" );
        tieneFechaPrueba.addDomain(pruebaMedica);
        DatatypeProperty tieneConclusion = model.createDatatypeProperty( URI + "tieneConclusion" );
        tieneConclusion.addDomain(pruebaMedica);


      // Crear Object Properties
        ObjectProperty tieneMedico = model.createObjectProperty(URI+"tieneMedico");
        tieneMedico.addDomain(paciente);
        tieneMedico.addRange(medico);
        ObjectProperty tienePaciente = model.createObjectProperty(URI+"tienePaciente");
        tienePaciente.addDomain(medico);
        tienePaciente.addRange(paciente);
        tienePaciente.setInverseOf(tieneMedico);
        ObjectProperty perteneceAMedico = model.createObjectProperty(URI+"perteneceAMedico");
        perteneceAMedico.addDomain(informe);
        perteneceAMedico.addRange(medico);
        ObjectProperty perteneceAPaciente = model.createObjectProperty(URI+"perteneceAPaciente");
        perteneceAPaciente.addDomain(informe);
        perteneceAPaciente.addRange(paciente);
        ObjectProperty tieneTratamiento = model.createObjectProperty(URI+"tieneTratamiento");
        tieneTratamiento.addDomain(informe);
        tieneTratamiento.addRange(tratamiento);
        ObjectProperty tienePruebaMedica = model.createObjectProperty(URI + "tienePruebaMedica");
        tienePruebaMedica.addDomain(informe);
        tienePruebaMedica.addRange(pruebaMedica);
        ObjectProperty tieneArchivoMedico = model.createObjectProperty(URI + "tieneArchivoMedico");
        tieneArchivoMedico.addDomain(pruebaMedica);
        tieneArchivoMedico.addRange(archivoMedico);
        ObjectProperty tieneDiagnostico = model.createObjectProperty( URI + "tieneDiagnostico" );
        tieneDiagnostico.addDomain(informe);
        tieneDiagnostico.addRange(enfermedad);

        //String names = "hasNames";
        //DatatypeProperty propertyName = model.createDatatypeProperty( URI + names );
    }

    private static void createPatientIndividuals(OntModel model, MySQLConnection conn){
      ArrayList<String> pacientes = conn.selectAll("paciente");
      for(String paciente : pacientes){
        String[] attrs = paciente.split("\t");
        model.getOntClass(URI + "paciente").createIndividual(URI + "paciente"+attrs[0]).
        addLiteral(model.getDatatypeProperty(URI+"tieneNombrePaciente"),attrs[1]).
        addLiteral(model.getDatatypeProperty(URI+"tieneApellidosPaciente"),attrs[2]).
        addLiteral(model.getDatatypeProperty(URI+"tieneNSS"),attrs[3]).
        addLiteral(model.getDatatypeProperty(URI+"tieneGenero"),attrs[4]).
        addLiteral(model.getDatatypeProperty(URI+"tieneAltura"),attrs[5]).
        addLiteral(model.getDatatypeProperty(URI+"tienePeso"),attrs[6]).
        addLiteral(model.getDatatypeProperty(URI+"tieneFechaNacimiento"),attrs[7]);
      }
    }

    private static void createDoctorIndividuals(OntModel model, MySQLConnection conn){
      ArrayList<String> medicos = conn.selectAll("medico");
      for(String medico : medicos){
        String[] attrs = medico.split("\t");
        model.getOntClass(URI + "medico").createIndividual(URI + "medico"+attrs[0]).
        addLiteral(model.getDatatypeProperty(URI+"tieneNombreMedico"),attrs[1]).
        addLiteral(model.getDatatypeProperty(URI+"tieneApellidosMedico"),attrs[2]).
        addLiteral(model.getDatatypeProperty(URI+"tieneLicencia"),attrs[3]);
      }
    }

    private static void createTreatmentIndividuals(OntModel model, MySQLConnection conn){
      ArrayList<String> tratamientos = conn.selectAll("tratamiento");
      if(tratamientos.size()==0) return;
      for(String tratamiento : tratamientos){
        String[] attrs = tratamiento.split("\t");
        model.getOntClass(URI + "tratamiento").createIndividual(URI + "tratamiento"+attrs[0]).
        addLiteral(model.getDatatypeProperty(URI+"tieneFechaInicio"),attrs[1]).
        addLiteral(model.getDatatypeProperty(URI+"tieneFechaFin"),attrs[2]).
        addLiteral(model.getDatatypeProperty(URI+"tieneDescripcion"),attrs[3]).
        addLiteral(model.getDatatypeProperty(URI+"tienePosologia"),attrs[4]);
      }
    }

    private static void createMedicalFileIndividuals(OntModel model, MySQLConnection conn){
      ArrayList<String> archivos = conn.selectAll("archivo_medico");
      if(archivos.size()==0) return;
      for(String archivo : archivos){
        String[] attrs = archivo.split("\t");
        model.getOntClass(URI + "archivoMedico").createIndividual(URI + "archivoMedico"+attrs[0]).
        addLiteral(model.getDatatypeProperty(URI+"tieneDocumento"),attrs[1]);
      }
    }

    private static void createReportIndividuals(OntModel model, MySQLConnection conn){
      createDiseaseIndividuals(model, conn);
      ArrayList<String> informes = conn.selectAll("informe");
        if(informes.size()==0) return;
      for(String informe : informes){
        String[] attrs = informe.split("\t");
        model.getOntClass(URI + "informe").createIndividual(URI + "informe"+attrs[0]).
        addLiteral(model.getDatatypeProperty(URI+"tieneSintomas"),attrs[1]).
        addLiteral(model.getDatatypeProperty(URI+"tieneFechaInforme"),attrs[2]);
      }
    }

    private static void createDiseaseIndividuals(OntModel model, MySQLConnection conn){
      ArrayList<String> informes = conn.selectAll("informe");
      if(informes.size()==0) return;
      Set<String> set = new HashSet<String>();
      for(String informe : informes){
        String[] attrs = informe.split("\t");
        set.add(attrs[3]);
      }
      for(String enfermedad: set){
        model.getOntClass(URI + "enfermedad").createIndividual(URI + enfermedad);
      }
    }

    private static void createMedicalTestIndividuals(OntModel model, MySQLConnection conn){
      ArrayList<String> pruebas = conn.selectAll("prueba_media");
      if(pruebas.size()==0) return;
      for(String prueba : pruebas){
        String[] attrs = prueba.split("\t");
        model.getOntClass(URI + "pruebaMedica").createIndividual(URI + "pruebaMedica"+attrs[0]).
        addLiteral(model.getDatatypeProperty(URI+"tieneFechaPrueba"),attrs[1]).
        addLiteral(model.getDatatypeProperty(URI+"tieneConclusion"),attrs[2]);
      }
    }

    private static void createPatientDoctorPredicates(OntModel model, MySQLConnection conn){
      ArrayList<String> relations = conn.selectAll("medico_has_paciente");
      if(relations.size()==0) return;
      for(String relation : relations){
        String[] attrs = relation.split("\t");

        Individual paciente = model.getIndividual(URI + "paciente" + attrs[1]);
        Individual doctor = model.getIndividual(URI + "medico" + attrs[0]);
        ObjectProperty tieneMedico = model.getObjectProperty(URI + "tieneMedico");
        //ObjectProperty tienePaciente = model.getObjectProperty(URI + "tienePaciente");

        model.add(paciente,tieneMedico,doctor);
        //model.add(doctor,tienePaciente,paciente);

      }
    }

    private static void createReportPatDocPredicates(OntModel model, MySQLConnection conn){
      ArrayList<String> relations = conn.selectAll("informe");
      if(relations.size()==0) return;
      for(String relation : relations){
        String[] attrs = relation.split("\t");

        Individual informe = model.getIndividual(URI + "informe" + attrs[0]);
        Individual paciente = model.getIndividual(URI + "paciente" + attrs[5]);
        Individual doctor = model.getIndividual(URI + "medico" + attrs[4]);
        ObjectProperty perteneceAMedico = model.getObjectProperty(URI + "perteneceAMedico");
        ObjectProperty perteneceAPaciente = model.getObjectProperty(URI + "perteneceAPaciente");

        model.add(informe,perteneceAMedico,doctor);
        model.add(informe,perteneceAPaciente,paciente);

      }
    }

    private static void createHasTreatmentPredicates(OntModel model, MySQLConnection conn){
      ArrayList<String> relations = conn.selectAll("tratamiento");
      if(relations.size()==0) return;
      for(String relation : relations){
        String[] attrs = relation.split("\t");

        Individual tratamiento = model.getIndividual(URI + "tratamiento" + attrs[0]);
        Individual informe = model.getIndividual(URI + "informe" + attrs[5]);
        ObjectProperty tieneTratamiento = model.getObjectProperty(URI + "tieneTratamiento");

        model.add(informe,tieneTratamiento,tratamiento);
      }
    }

    private static void createHasTestPredicates(OntModel model, MySQLConnection conn){
      ArrayList<String> relations = conn.selectAll("prueba_medica");
      if(relations.size()==0) return;
      for(String relation : relations){
        String[] attrs = relation.split("\t");

        Individual pruebaMedica = model.getIndividual(URI + "pruebaMedica" + attrs[0]);
        Individual informe = model.getIndividual(URI + "informe" + attrs[3]);
        ObjectProperty tienePruebaMedica = model.getObjectProperty(URI + "tienePruebaMedica");

        model.add(informe,tienePruebaMedica,pruebaMedica);
      }
    }


    private static void createHasFilePredicates(OntModel model, MySQLConnection conn){
      ArrayList<String> relations = conn.selectAll("archivo_medico");
      if(relations.size()==0) return;
      for(String relation : relations){
        String[] attrs = relation.split("\t");

        Individual archivoMedico = model.getIndividual(URI + "archivoMedico" + attrs[0]);
        Individual pruebaMedica = model.getIndividual(URI + "pruebaMedica" + attrs[2]);
        ObjectProperty tieneArchivoMedico = model.getObjectProperty(URI + "tieneArchivoMedico");

        model.add(pruebaMedica,tieneArchivoMedico,archivoMedico);
      }
    }

    private static void createHasDiagnosisPredicates(OntModel model, MySQLConnection conn){
      ArrayList<String> relations = conn.selectAll("informe");
      if(relations.size()==0) return;
      for(String relation : relations){
        String[] attrs = relation.split("\t");

        Individual informe = model.getIndividual(URI + "informe" + attrs[0]);
        Individual diagnostico = model.getIndividual(URI + attrs[3]);
        ObjectProperty tieneDiagnostico = model.getObjectProperty(URI + "tieneDiagnostico");

        model.add(informe,tieneDiagnostico,diagnostico);
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
