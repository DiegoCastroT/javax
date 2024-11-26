package insitutoDB;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties props = new Properties();
        MysqlDataSource ds = null;
        FileInputStream fis = null;

        try{
            fis = new FileInputStream("src/main/java/db.propierties");
            props.load(fis);

            ds = new MysqlDataSource();

            ds.setURL(props.getProperty("URL"));
            ds.setUser(props.getProperty("USUARIO"));
            ds.setPassword(props.getProperty("PASSWORD"));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
