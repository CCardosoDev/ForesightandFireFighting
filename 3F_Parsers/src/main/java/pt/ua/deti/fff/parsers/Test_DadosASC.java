package pt.ua.deti.fff.parsers;
/**
 *
 * @author Francisco Vaz, nº 26572
 */
public class Test_DadosASC {

    public static void main(String[] args) throws Exception
    {
        long startTime;
        long elapsedTime;

        startTime = System.nanoTime();
        DadosAscParser testDados = new DadosAscParser("D:\\ES-Testes\\dados.asc");
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("\n\n\nTime to parse Dados.asc: " + (double)elapsedTime / 1000000000.0 + " seconds");
        
        testDados.printValues();
        
        System.out.println("done!\n\n\n");
    }
}
