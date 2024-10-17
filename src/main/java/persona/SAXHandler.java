package persona;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {

    private final StringBuilder currentValue = new StringBuilder(); // Para acumular el texto de los elementos
    private Persona currentPersona; // Objeto que representa a la persona actual

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        currentValue.setLength(0); // Limpia el StringBuilder para el nuevo elemento
        if (qName.equals("persona")) {
            currentPersona = new Persona("", "", 0, ""); // Inicializa un nuevo objeto Persona
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // Al final de un elemento, se asigna el valor correspondiente
        switch (qName) {
            case "nombre" -> currentPersona.setNombre(currentValue.toString());
            case "dni" -> currentPersona.setDni(currentValue.toString());
            case "telefono" -> currentPersona.setTelefono(currentValue.toString());
            case "edad" -> currentPersona.setEdad(Integer.parseInt(currentValue.toString()));
            case "persona" -> {
                // Imprime la información de la persona
                System.out.println("Nombre: " + currentPersona.getNombre());
                System.out.println("DNI: " + currentPersona.getDni());
                System.out.println("Teléfono: " + currentPersona.getTelefono());
                System.out.println("Edad: " + currentPersona.getEdad());
                System.out.println("-------------------");
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // Acumula los caracteres que forman el contenido de un elemento
        currentValue.append(ch, start, length);
    }
}
