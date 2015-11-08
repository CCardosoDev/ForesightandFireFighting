/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ua.deti.fff.parsers;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author Renato Rodrigues <renato.pinho@ua.pt>
 */
public class NuatmosInpParserTest extends TestCase {
    private NuatmosInpParser parser;
    public NuatmosInpParserTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        parser = new NuatmosInpParser("https://dl.dropboxusercontent.com/u/5952458/nuatmos.inp");
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getWindData method, of class NuatmosInpParser.
     */
    public void testGetWindData() {
        System.out.print("getWindData... ");
        NuatmosInpParser instance = parser;
        String expResult = "wind.obs";
        String result = instance.getWindData();
        assertEquals(expResult, result);
        System.out.println("Success!");
    }

    /**
     * Test of getTopoData method, of class NuatmosInpParser.
     */
    public void testGetTopoData() {
        System.out.print("getTopoData... ");
        NuatmosInpParser instance = parser;
        String expResult = "topo.dtm";
        String result = instance.getTopoData();
        assertEquals(expResult, result);
        System.out.println("Success!");
    }

    /**
     * Test of getOutputFileName method, of class NuatmosInpParser.
     */
    public void testGetOutputFileName() {
        System.out.println("getOutputFileName");
        NuatmosInpParser instance = parser;
        String expResult = "nuatmos.dt2";
        String result = instance.getOutputFileName();
        assertEquals(expResult, result);
        System.out.println("Success!");
    }

    /**
     * Test of getFixedParameters method, of class NuatmosInpParser.
     */
    public void testGetFixedParameters() {
        System.out.print("getFixedParameters... ");
        NuatmosInpParser instance = parser;
        List expResult = new LinkedList<Double>();
        expResult.add((double) 0.5); expResult.add((double) 0.8); expResult.add((double) 200);
        expResult.add((double) 1); expResult.add((double) 1.35); expResult.add((double) 1.7);
        expResult.add((double) 0.001); expResult.add((double) 2); expResult.add((double) 1);
        expResult.add((double) 5000); expResult.add((double) 1); expResult.add((double) 0);
        List result = instance.getFixedParameters();
        assertEquals(expResult, result);
        System.out.println("Success!");
    }
    
}
