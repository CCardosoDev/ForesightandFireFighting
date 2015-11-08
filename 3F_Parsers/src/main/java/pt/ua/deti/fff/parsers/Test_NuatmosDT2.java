package pt.ua.deti.fff.parsers;

/**
 *
 * @author Francisco Vaz, nº 26572
 */
public class Test_NuatmosDT2 {

    public static void main(String[] args) throws Exception
    {
        long startTime;
        long elapsedTime;
        
        startTime = System.nanoTime();        
        NuatmosDt2Parser testNuatmos = new NuatmosDt2Parser("D:\\ES-Testes\\nuatmos.dt2");        
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("\n\n\nNuatmos.dt2 has " + testNuatmos.getnLines() + " lines and " + testNuatmos.getnLists()+ " lists");
        System.out.println("Time to parse Nuatmos.dt2: " + (double)elapsedTime / 1000000000.0 + " seconds");
        
        
        startTime = System.nanoTime();  
        testNuatmos.createJSONFile("D:\\ES-Testes\\nuatmosDT2");
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("Time to create the json file: " + (double)elapsedTime / 1000000000.0 + " seconds");
        
        //testNuatmos.printLists();  //here be dragons!!
        
        System.out.println("done!\n\n\n");
    }
}
