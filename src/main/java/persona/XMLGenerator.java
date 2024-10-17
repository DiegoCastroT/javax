package persona;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.ArrayList;

public class XMLGenerator {

    public static void generateXML(ArrayList<Persona> personas) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            DOMImplementation domImpl = builder.getDOMImplementation();
            Document document = domImpl.createDocument(null, "Personas", null);
            document.setXmlVersion("1.0");

            for (Persona persona : personas) {
                Element raiz = document.createElement("persona");
                document.getDocumentElement().appendChild(raiz);

                createElement(raiz, document, "nombre", persona.getNombre());
                createElement(raiz, document, "dni", persona.getDni());
                createElement(raiz, document, "telefono", persona.getTelefono());
                createElement(raiz, document, "edad", String.valueOf(persona.getEdad()));
            }

            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("personas.xml"));
            try {
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.transform(source, result);
                System.out.println("Archivo XML generado con éxito");
            } catch (TransformerException e) {
                throw new RuntimeException(e);
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }



    private static void createElement(Element raiz, Document documento, String tagName, String data) {
        //Crear elemento
        Element elemento = documento.createElement(tagName);
        Text texto = documento.createTextNode(data);
        raiz.appendChild(elemento);
        elemento.appendChild(texto);


    }
}