package pt.ua.deti.fff.parsers;

import junit.framework.TestCase;
import pt.ua.deti.fff.parsers.dataTypes.FuelType;
import pt.ua.deti.fff.parsers.dataTypes.IDT;
import pt.ua.deti.fff.parsers.dataTypes.NTime;
import pt.ua.deti.fff.parsers.dataTypes.Stab;

/**
 *
 * @author Renato Rodrigues <renato.pinho@ua.pt>
 */
public class DadosAscParserTest extends TestCase {
    private DadosAscParser parser;
    
    public DadosAscParserTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        parser = new DadosAscParser("D:\\ES-Testes\\dados.asc");
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getTitle method, of class DadosAscParser.
     */
    public void testGetTitle() {
        System.out.print("getTitle...");
        DadosAscParser instance = parser;
        String expResult = "Marvão 12.5 x 11 km resolucao de 30 m. Fogo Marvão Agosto 2000 imaginário";
        String result = instance.getTitle();
        assertEquals(expResult, result);
        
        System.out.println("Success!");
    }

    /**
     * Test of getTime method, of class DadosAscParser.
     */
    public void testGetTime() {
        System.out.print("getTime...");
        DadosAscParser instance = parser;
        NTime expResult = new NTime(0, 0, 0);
        NTime result = instance.getTime();
        assertEquals(expResult.getHour(), result.getHour());
        assertEquals(expResult.getMinute(), result.getMinute());
        assertEquals(expResult.getSecond(), result.getSecond());
        
        System.out.println("Success!");
    }

    /**
     * Test of getIdt method, of class DadosAscParser.
     */
    public void testGetIdt() {
        System.out.print("getIdt... ");
        DadosAscParser instance = parser;
        IDT expResult = new IDT(7200, 9660);
        IDT result = instance.getIdt();
        assertEquals(expResult.getInput(), result.getInput());
        assertEquals(expResult.getOutput(), result.getOutput());
        
        System.out.println("Success!");
    }

    /**
     * Test of getnFuelTypes method, of class DadosAscParser.
     */
    public void testGetnFuelTypes() {
        System.out.print("getnFuelTypes... ");
        DadosAscParser instance = parser;
        int expResult = 6;
        int result = instance.getnFuelTypes();
        assertEquals(expResult, result);
        
        System.out.println("Success!");
    }

    /**
     * Test of getDensity method, of class DadosAscParser.
     */
    public void testGetDensity() {
        System.out.print("getDensity... ");
        DadosAscParser instance = parser;
        int expResult = 1;
        int result = instance.getDensity();
        assertEquals(expResult, result);
        
        System.out.println("Success!");
    }

    /**
     * Test of getFuelTypes method, of class DadosAscParser.
     */
    public void testGetFuelTypes() {
        System.out.print("getFuelTypes... ");
        DadosAscParser instance = parser;
        FuelType[] expResult = { new FuelType(1183, "PINHEIRO"), 
            new FuelType(1183, "EUCALIPTO"), new FuelType(183, "AGRICOLA"),
            new FuelType(1813, "FOLHOSAS"), new FuelType(1813, "MATO"),
            new FuelType(0, "ZONAS URBANAS") };
        FuelType[] result = instance.getFuelTypes();
        
        assertEquals(expResult.length, result.length);
        for(int i = 0; i < result.length; i++) {
            assertEquals(expResult[i].getType(), result[i].getType());
            assertEquals(expResult[i].getValue(), result[i].getValue());
        }
        
        System.out.println("Success!");
    }

    /**
     * Test of getAbsort method, of class DadosAscParser.
     */
    public void testGetAbsort() {
        System.out.print("getAbsort... ");
        DadosAscParser instance = parser;
        int expResult = 0;
        int result = instance.getAbsort();
        assertEquals(expResult, result);
        
        System.out.println("Success!");
    }

    /**
     * Test of getReflex method, of class DadosAscParser.
     */
    public void testGetReflex() {
        System.out.print("getReflex... ");
        DadosAscParser instance = parser;
        int expResult = 1;
        int result = instance.getReflex();
        assertEquals(expResult, result);
        
        System.out.println("Success!");
    }

    /**
     * Test of getStab method, of class DadosAscParser.
     */
    public void testGetStab() {
        System.out.print("getStab... ");
        DadosAscParser instance = parser;
        Stab expResult = Stab.ONE;
        Stab result = instance.getStab();
        assertEquals(expResult, result);
        
        System.out.println("Success!");
    }
}
