package data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author emil
 */
public class Config implements JsonExport {
    
    private static Config instance;
    
    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    @Override
    public void writeTo(BufferedWriter w) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadInto(InputStream fis) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
