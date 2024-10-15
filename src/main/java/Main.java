import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws JAXBException {
        System.out.println("Hello World");
        JAXBContext context;

        ArrayList<Libro> libros = new ArrayList<>();

        Libreria libreria = new Libreria(libros, "Libreria ia", "ia");
        libreria.rellenarLibreria();

        context = JAXBContext.newInstance(Libreria.class);
        Marshaller marshaller = context.createMarshaller(); //XML transform
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(libreria, System.out);
        marshaller.marshal(libreria, new File("libreria.xml"));

    }
}
