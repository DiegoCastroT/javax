package persona;

import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;


public class Main {
    static ArrayList<Persona> personas = new ArrayList<>();
    static ArrayList<Persona> personasLeidas = new ArrayList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException, ParserConfigurationException, SAXException, JAXBException {
        Persona persona1 = new Persona("Pepe", "12345678", 19, "71986574Y");
        Persona persona2 = new Persona("Pepe2", "22345678", 20, "71986574Y");
        Persona persona3 = new Persona("Pepe3", "32345678", 21, "71986574Y");
        Persona persona4 = new Persona("Pepe4", "42345678", 22, "71986574Y");

        // Agregar personas a la lista
        personas.add(persona1);
        personas.add(persona2);
        personas.add(persona3);
        personas.add(persona4);

        // Escribir la lista completa de personas en el archivo
        writePersonas(personas);
        leerPersonas();

        //Crear XML
        XMLGenerator.generateXML(personasLeidas);
        ReadXML.JAXBReader();

    }

    private static void writePersonas(ArrayList<Persona> personas) throws IOException {
        File file = new File("FichPersona.dat");

        // Usar FileOutputStream sin append
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        // Escribir la lista de personas
        objectOutputStream.writeObject(personas);

        objectOutputStream.close();
        fileOutputStream.close();

        System.out.println("Personas guardadas en FichPersona.dat con éxito.");
    }

    private static void leerPersonas() throws IOException, ClassNotFoundException {

        File file = new File("FichPersona.dat");
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        // Leer la lista completa de personas desde el archivo
        personasLeidas = (ArrayList<Persona>) objectInputStream.readObject();

        objectInputStream.close();
        fileInputStream.close();
    }

    private static void readXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            ReadXML.readXML(documentBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
