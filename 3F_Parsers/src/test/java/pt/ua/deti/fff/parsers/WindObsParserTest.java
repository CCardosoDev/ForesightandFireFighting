/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ua.deti.fff.parsers;

import java.util.Date;
import junit.framework.TestCase;
import pt.ua.deti.fff.parsers.WindObsParser.Coordinates;
import pt.ua.deti.fff.parsers.WindObsParser.NewDate;
import pt.ua.deti.fff.parsers.WindObsParser.WindInfo;

/**
 *
 * @author Renato Rodrigues <renato.pinho@ua.pt>
 */
public class WindObsParserTest extends TestCase {
    WindObsParser parser;
    
    public WindObsParserTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        parser = new WindObsParser("https://dl.dropboxusercontent.com/u/5952458/wind.obs");
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getStationNumber method, of class WindObsParser.
     */
    public void testGetStationNumber() {
        System.out.println("getStationNumber");
        WindObsParser instance = parser;
        int expResult = 1;
        int result = instance.getStationNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDate method, of class WindObsParser.
     */
    public void testGetDate() {
        System.out.println("getDate");
        WindObsParser instance = parser;
        NewDate result = instance.getDate();
        assertEquals(1630, result.hourAndMinutes);
        assertEquals(30, result.day);
        assertEquals(05, result.month);
        assertEquals(2002, result.year);
    }

    /**
     * Test of getStationCoordinates method, of class WindObsParser.
     */
    public void testGetStationCoordinates() {
        System.out.println("getStationCoordinates");
        WindObsParser instance = parser;
        Coordinates result = instance.getStationCoordinates();
        assertEquals(1.3816, result.x);
        assertEquals(0.6156, result.y);
    }

    /**
     * Test of getElevation method, of class WindObsParser.
     */
    public void testGetElevation() {
        System.out.println("getElevation");
        WindObsParser instance = parser;
        double expResult = 783.0;
        double result = instance.getElevation();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getNumObservations method, of class WindObsParser.
     */
    public void testGetNumObservations() {
        System.out.println("getNumObservations");
        WindObsParser instance = parser;
        int expResult = 1;
        int result = instance.getNumObservations();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInfo method, of class WindObsParser.
     */
    public void testGetInfo() {
        System.out.println("getInfo");
        WindObsParser instance = parser;
        WindInfo result = instance.getInfo();
        assertEquals(0.59, result.u);
        assertEquals(0.54, result.v);
        assertEquals(0.0, result.x);
        assertEquals(0.0, result.y);
        assertEquals(6.0, result.z);
    }

    /**
     * Test of getEndOfFile method, of class WindObsParser.
     */
    public void testGetEndOfFile() {
        System.out.println("getEndOfFile");
        WindObsParser instance = parser;
        String expResult = "-1 -1 -1 -1 -1 -1 -1 -1 -1";
        String result = instance.getEndOfFile();
        assertEquals(expResult, result);
    }

}
