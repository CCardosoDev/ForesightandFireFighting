package pt.ua.deti.fff.parsers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

/**
 * @version 0.1
 * @author Sergio Martins, sfsm@ua.pt
 * 
 * 3F
 * Parser specialized class for files of type "carga.asc"
 */
public class CargaParser extends MatrixFileParser{
    public CargaParser(String path) throws MalformedURLException, IOException, ParseException{
        super(path);
    }
}
