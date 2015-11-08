/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ua.deti.fff.parsers;

import java.util.ArrayList;
import junit.framework.TestCase;
import pt.ua.deti.fff.parsers.utils.ReadXML;

/**
 *
 * @author Renato Rodrigues <renato.pinho@ua.pt>
 */
public class ProgreminParserTest extends TestCase {
    ProgreminParser parser;
    
    public ProgreminParserTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        parser = new ProgreminParser("https://dl.dropboxusercontent.com/u/5952458/progremin.asc");
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getMaxTime method, of class ProgreminParser.
     */
    public void testGetMaxTime() {
        System.out.println("getMaxTime");
        ProgreminParser instance = parser;
        int expResult = 14668;
        int result = instance.getMaxTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDelta method, of class ProgreminParser.
     */
    public void testGetDelta() {
        System.out.println("getDelta");
        ProgreminParser instance = parser;
        int expResult = 60;
        int result = instance.getDelta();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValues method, of class ProgreminParser.
     */
    public void testGetValues() {
        System.out.println("getValues");
        ProgreminParser instance = parser;
        ArrayList<ArrayList<Integer>> result = instance.getValues();
        ArrayList<ArrayList<Float>> expResult = new ReadXML("C:\\Users\\Renato\\Dropbox\\My Shared Folders\\A - Partilha\\Cadeiras\\4Ano\\ES\\Ficheiros\\disperfire\\matrizes\\progremin.xml").getMatriz();
        
        assertEquals(result.size(), expResult.size());
        
        for (int i = 0; i < result.size(); i++) {
            ArrayList<Integer> ar = result.get(i);
            ArrayList<Float> expAr = expResult.get(i);
            for (int j = 0; j < ar.size(); j++) {
                assertEquals(expAr.get(j), (float) ar.get(j));
            }
            
        }
    }
}
