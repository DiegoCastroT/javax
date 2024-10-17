package persona;

import liberia.Libreria;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;


public class ReadXML {

    public static void readXML(DocumentBuilder documentBuilder) {
        try {
            Document document = documentBuilder.parse(new File("personas.xml")); // Da un document

            System.out.println("Elemento raíz: " + document.getDocumentElement().getNodeName());
            NodeList nodeList = document.getElementsByTagName("persona"); // Da una lista de nodos

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i); // Da un nodo

                // Mirar de qué tipo es el nodo
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Nombre: " + element.getElementsByTagName("nombre").item(0).getTextContent());
                    System.out.println("DNI: " + element.getElementsByTagName("dni").item(0).getTextContent());
                    System.out.println("Teléfono: " + element.getElementsByTagName("telefono").item(0).getTextContent());
                    System.out.println("Edad: " + element.getElementsByTagName("edad").item(0).getTextContent());
                    System.out.println("-------------------");
                }
            }
            System.out.println("Personas leidas desde DOM");


        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void SAXread() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();

        SAXHandler saxHandler = new SAXHandler();
        xmlReader.setContentHandler(saxHandler);

        InputSource inputSource = new InputSource("personas.xml");
        xmlReader.parse(inputSource);
        System.out.println("Personas leidas desde el SAX");
    }

    public static void JAXBReader() throws IOException, ClassNotFoundException, JAXBException {

    }


}


