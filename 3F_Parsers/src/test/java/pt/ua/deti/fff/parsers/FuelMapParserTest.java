/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ua.deti.fff.parsers;

import java.util.ArrayList;
import junit.framework.TestCase;
import pt.ua.deti.fff.parsers.FuelMapParser.MilitarCoordinates;
import pt.ua.deti.fff.parsers.utils.ReadXML;

/**
 *
 * @author Renato Rodrigues <renato.pinho@ua.pt>
 */
public class FuelMapParserTest extends TestCase {
    
    public FuelMapParserTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of FuelMapParser method, of class FuelMapParser.
     */
    public void testFuelMapParser() throws Exception {
        System.out.println("FuelMapParser");
        String path = "https://dl.dropboxusercontent.com/u/5952458/fuelmap.asc";
        FuelMapParser instance = new FuelMapParser();
        instance.FuelMapParser(path);
    }

    /**
     * Test of getIndexLastLine method, of class FuelMapParser.
     */
    public void testGetIndexLastLine() throws Exception {
        System.out.println("getIndexLastLine");
        String path = "https://dl.dropboxusercontent.com/u/5952458/fuelmap.asc";
        FuelMapParser instance = new FuelMapParser();
        instance.FuelMapParser(path);
        int expResult = 40;
        int result = instance.getIndexLastLine();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIndexLastColumn method, of class FuelMapParser.
     */
    public void testGetIndexLastColumn() throws Exception {
        System.out.println("getIndexLastColumn");
        String path = "https://dl.dropboxusercontent.com/u/5952458/fuelmap.asc";
        FuelMapParser instance = new FuelMapParser();
        instance.FuelMapParser(path);
        int expResult = 33;
        int result = instance.getIndexLastColumn();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCoord method, of class FuelMapParser.
     */
    public void testGetCoord() throws Exception{
        System.out.println("getCoord");
        String path = "https://dl.dropboxusercontent.com/u/5952458/fuelmap.asc";
        FuelMapParser instance = new FuelMapParser();
        instance.FuelMapParser(path);
        MilitarCoordinates result = instance.getCoord();
        
        assertEquals(571000., result.x);
        assertEquals(4432925., result.y);
        
    }

    /**
     * Test of getResolution method, of class FuelMapParser.
     */
    public void testGetResolution() throws Exception {
        System.out.println("getResolution");
        String path = "https://dl.dropboxusercontent.com/u/5952458/fuelmap.asc";
        FuelMapParser instance = new FuelMapParser();
        instance.FuelMapParser(path);
        float expResult = 10.0F;
        float result = instance.getResolution();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getValues method, of class FuelMapParser.
     */
    public void testGetValues() throws Exception {
        System.out.println("getValues");
        String path = "https://dl.dropboxusercontent.com/u/5952458/fuelmap.asc";
        FuelMapParser instance = new FuelMapParser();
        instance.FuelMapParser(path);
        
        ArrayList<ArrayList<Integer>> result = instance.getValues();
        ArrayList<ArrayList<Float>> expResult = new ReadXML("C:\\Users\\Renato\\Dropbox\\My Shared Folders\\A - Partilha\\Cadeiras\\4Ano\\ES\\Ficheiros\\disperfire\\matrizes\\fuelmap.xml").getMatriz();
        
        System.out.println("expResult: " + expResult.size());
        System.out.println("result: " + result.size());
        assertEquals(expResult.size(), result.size());
        
        for (int i = 0; i < result.size(); i++) {
            System.out.println("Linha " + (i+1));
            ArrayList<Integer> ar = result.get(i);
            ArrayList<Float> expAr = expResult.get(i);
            for (int j = 0; j < ar.size(); j++) {
                System.out.println("Coluna " + (j+1));
                assertEquals(expAr.get(j), (float) ar.get(j));
            }
            
        }
        
    }
}
