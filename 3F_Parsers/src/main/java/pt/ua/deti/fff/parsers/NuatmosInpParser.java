package pt.ua.deti.fff.parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiago sousa - 50170
 */
public class NuatmosInpParser 
{
    /* Variaveis */
    private String windData;            // Nome do ficheiro com dados de vento (wind.obs)
    private String topoData;            // Nome do ficheiro com a topografia (topo.dtm)
    private String outputFileName;      // Nome do ficheiro de saída (nuatmos.dt2)
    
    private final List<Double> fixedParameters;     // Parametros ficos
    
    private Date criationTime;          // Data e Hora de geracao do ficheiro
    
    
    /* Construtor */
    /**
     *
     * @param filePath URI to file to be parsed
     */
    public NuatmosInpParser(String filePath) throws MalformedURLException, IOException, ParseException
    {
        URL path = new URL(filePath);
        criationTime = new Date();
        
        InputStream input = path.openStream();
        InputStreamReader reader = new InputStreamReader(input);
        BufferedReader in = new BufferedReader(reader);
        
        String inputLine;
        
        // Primeira Linha - Nome do ficheiro com dados de vento
        inputLine = in.readLine();
        if((windData = inputLine.split(" ")[0]).equals(""))
            throw new ParseException("Wind data filename is not valid !", 0);
        
        // Segunda Linha - Nome do ficheiro com a topografia
        inputLine = in.readLine();
        if((topoData = inputLine.split(" ")[0]).equals(""))
            throw new ParseException("Topographic filename is not valid !", 1);
        
        // Terceira Linha - Nome do ficheiro de saida
        inputLine = in.readLine();
        if((outputFileName = inputLine.split(" ")[0]).equals(""))
            throw new ParseException("Output filename is not valid !", 2);
        
        // Parametros fixos
        int lineNumber = 3;
        fixedParameters = new ArrayList<>();
        while((inputLine = in.readLine()) != null)
        {
            String[] tmp = inputLine.split(" ");
            double value = 0;
            
            for(int i = 0; i < tmp.length; i++)
            {
                try
                {
                    value = Double.parseDouble(tmp[i]);
                }
                catch(Exception e)
                {
                    throw new ParseException("Wrong value at line: " + lineNumber, lineNumber);
                }
                finally
                {
                    fixedParameters.add(value);
                }
            }
                    
            lineNumber++;
        }
        
        in.close();
    }
    
    
    /* Get Methods */
    /**
     * @return the name of the wind data file
     */
    public String getWindData() {
        return windData;
    }

    /**
     * @return the name of the topographic file
     */
    public String getTopoData() {
        return topoData;
    }

    /**
     * @return the name of the output file
     */
    public String getOutputFileName() {
        return outputFileName;
    }

    /**
     * @return the List of fixed parameters
     */
    public List<Double> getFixedParameters() {
        return fixedParameters;
    }

    /**
     * @return the time when data was parsed
     */
    public Date getCriationTime() {
        return criationTime;
    }
    
    
    @Override
    public String toString()
    {
        String res = "File parsed at: " + criationTime.toString() + "\n";
        
        res += "Wind data filename: " + windData + "\n";
        res += "Topographic data filename: " + topoData + "\n";
        res += "Output filenam: " + outputFileName + "\n";
        
        res += "Fixed Parameters:\n";
        Iterator<Double> it = fixedParameters.iterator();
        while(it.hasNext())
            res += it.next() + "\n";
        
        return res;
    }
    
    
    /* --- Teste --- */
//    public static void main(String[]args)
//    {
//        NuatmosInpParser ntm;
//        try {
//            System.out.println("Testing class: NuatmosInpParser");
//
//            ntm = new NuatmosInpParser("https://gslb-dl.dropbox.com/s/5tvwsqlfj9o8mzs/nuatmos.inp?token_hash=AAFU-9_ElqP40Ql5YbscP-k8uhDCNoGVq3lYllePfLtZSQ&dl=1");
//            System.out.println(ntm);
//
//            System.out.println("Sucessed test on class: NuatmosInpParser");
//
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(ParsingTesteSergioMartins.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("Failed test on class: NuatmosInpParser");
//        } catch (IOException ex) {
//            Logger.getLogger(ParsingTesteSergioMartins.class.getName()).log(Level.SEVERE, null, ex);
//        System.out.println("Failed test on class: NuatmosInpParser");
//        } catch (ParseException ex) {
//            Logger.getLogger(ParsingTesteSergioMartins.class.getName()).log(Level.SEVERE, null, ex);
//        System.out.println("Failed test on class: NuatmosInpParser");
//        }
//    }
}
