/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ua.deti.fff.parsers;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import pt.ua.deti.fff.parsers.TopoDtmParser.Cells;
import pt.ua.deti.fff.parsers.TopoDtmParser.MilitarCoordinates;
import pt.ua.deti.fff.parsers.TopoDtmParser.Resolution;
import pt.ua.deti.fff.parsers.utils.ReadXML;

/**
 *
 * @author Renato Rodrigues <renato.pinho@ua.pt>
 */
public class TopoDtmParserTest extends TestCase {
    TopoDtmParser parser;
    
    public TopoDtmParserTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        parser = new TopoDtmParser("https://dl.dropboxusercontent.com/u/5952458/topo.dtm");
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getTitle method, of class TopoDtmParser.
     */
    public void testGetTitle() {
        System.out.println("getTitle");
        TopoDtmParser instance = parser;
        String expResult = "Gestosa 165x138, resolucao de 10 m. 30/05/2002";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumCells method, of class TopoDtmParser.
     */
    public void testGetNumCells() {
        System.out.println("getNumCells");
        TopoDtmParser instance = parser;
        Cells result = instance.getNumCells();
        assertEquals(165, result.x);
        assertEquals(137, result.y);
        assertEquals(20, result.z);
    }

    /**
     * Test of getResolution method, of class TopoDtmParser.
     */
    public void testGetResolution() {
        System.out.println("getResolution");
        TopoDtmParser instance = parser;
        Resolution result = instance.getResolution();
        assertEquals(10.0, result.height);
        assertEquals(10.0, result.width);
    }

    /**
     * Test of getMaxHeight method, of class TopoDtmParser.
     */
    public void testGetMaxHeight() {
        System.out.println("getMaxHeight");
        TopoDtmParser instance = parser;
        double expResult = 3000.0;
        double result = instance.getMaxHeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getOriginCoordinates method, of class TopoDtmParser.
     */
    public void testGetOriginCoordinates() {
        System.out.println("getOriginCoordinates");
        TopoDtmParser instance = parser;
        MilitarCoordinates result = instance.getOriginCoordinates();
        assertEquals(570111.1, result.x);
        assertEquals(4432624.4, result.y);
    }

    /**
     * Test of getSigma method, of class TopoDtmParser.
     */
    public void testGetSigma() {
        System.out.println("getSigma");
        TopoDtmParser instance = parser;
        List<Double> expResult = new ArrayList<>();
        List<Double> result = instance.getSigma();
        expResult.add(0.0);expResult.add(0.159529);expResult.add(0.294516);expResult.add(0.408728);
        expResult.add(0.505364);expResult.add(0.587128);expResult.add(0.656308);expResult.add(0.714842);
        expResult.add(0.764368);expResult.add(0.806272);expResult.add(0.841726);expResult.add(0.871725);
        expResult.add(0.897107);expResult.add(0.918582);expResult.add(0.936753);expResult.add(0.952127);
        expResult.add(0.965135);expResult.add(0.976142);expResult.add(0.985454);expResult.add(0.993333);
        expResult.add(1.0);
        
        assertEquals(expResult.size(), result.size());
        for (int i = 0; i < expResult.size(); i++) {
            assertEquals(expResult.get(i), result.get(i));
        }
    }

    /**
     * Test of getTopoValues method, of class TopoDtmParser.
     */
    public void testGetTopoValues() {
        System.out.println("getTopoValues");
        TopoDtmParser instance = parser;
        ArrayList<ArrayList<Float>> expResult = new ReadXML("C:\\Users\\Renato\\Dropbox\\My Shared Folders\\A - Partilha\\Cadeiras\\4Ano\\ES\\Ficheiros\\disperfire\\matrizes\\topo.xml").getMatriz();
        List<List<Double>> result = instance.getTopoValues();
        
        assertEquals(expResult.size(), result.size());
        
        for (int i = 0; i < result.size(); i++) {
            ArrayList<Float> expAr = expResult.get(i);
            List<Double> ar = result.get(i);
            for (int j = 0; j < ar.size(); j++) {
                assertEquals(expAr.get(j), Float.parseFloat(ar.get(j).toString()));
            }
        }
        
    }
}
