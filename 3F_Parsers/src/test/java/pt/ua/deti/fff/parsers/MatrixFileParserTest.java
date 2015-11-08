/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ua.deti.fff.parsers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import junit.framework.TestCase;
import pt.ua.deti.fff.parsers.utils.ReadXML;

/**
 *
 * @author Anton
 */
public class MatrixFileParserTest extends TestCase {
    private MatrixFileParser parser;
    public MatrixFileParserTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        parser = new MatrixFileParser("https://dl.dropboxusercontent.com/u/7103082/Lixo/carga.asc");
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of print method, of class MatrixFileParser.
     */
    public void testParser() {
        System.out.println("parser");
        MatrixFileParser instance = parser;
        
        ReadXML reader = new ReadXML("C:\\Users\\Anton\\Dropbox\\Partilha\\Cadeiras\\4Ano\\ES\\Ficheiros\\disperfire\\matrizes\\carga.xml");
        
        List<List<Float>> original = parser.getValues();
        ArrayList<ArrayList<Float>> xpto = reader.getMatriz();
        assertEquals(original.size(), xpto.size());
        for (int i = 0; i < xpto.size(); i++) {
            ArrayList<Float> linhaXML = xpto.get(i);
            List<Float> linhaOri = original.get(i);
            assertEquals(original.size(), xpto.size());
            
            for (int j = 0; j < linhaXML.size(); j++) {
                assertEquals(linhaXML.get(j), linhaOri.get(j));
            }
            
        }
    }

    /**
     * Test of toArray method, of class MatrixFileParser.
     */
    public void testToArray() {
        System.out.println("toArray");
        MatrixFileParser instance = parser;
        
        
        ReadXML reader = new ReadXML("C:\\Users\\Anton\\Dropbox\\Partilha\\Cadeiras\\4Ano\\ES\\Ficheiros\\disperfire\\matrizes\\carga.xml");
        
        float[][] expResult = reader.testToArray();
        float[][] result = instance.toArray();
        assertEquals(expResult.length, result.length);
        for (int i = 0; i < parser.nCols(); i++) {
            for (int j = 0; j < parser.nRows(); j++) {
                assertEquals(expResult[i][j], result[i][j]);   
            }
        }
    }

    
    /**
     * Test of nCols method, of class MatrixFileParser.
     */
    public void testNCols() {
        System.out.println("nCols");
        MatrixFileParser instance = parser;
        int expResult = 41;
        int result = instance.nCols();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of nRows method, of class MatrixFileParser.
     */
    public void testNRows() {
        System.out.println("nRows");
        MatrixFileParser instance = parser;
        int expResult = 34;
        int result = instance.nRows();
        assertEquals(expResult, result);
    }
}
