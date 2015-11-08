package pt.ua.deti.fff.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Francisco Vaz, nº 26572
 */
public class NuatmosDt2Parser {
    private List<List<Double>> u, v, w;
    private int nLines, nLists; 
    
    public NuatmosDt2Parser(String path) throws Exception
    {
        File fin;   //input file
        StringBuilder aux_path; //processed path
        String line;    //line read from the file
        String string_values[]; //line values as strings
        double double_values[]; //line values as doubles
        int listsIndex;

        u = new LinkedList<>();
        v = new LinkedList<>();
        w = new LinkedList<>();
        
        //checks if argument is null
        if (path == null)
            throw new IllegalArgumentException();
        
        //processes the path
        aux_path = new StringBuilder();        
        for (int i = 0; i < path.length(); i++)
        {
            if (path.charAt(i) == '\\')
            {
                aux_path.append("\\\\");
            }
            else
            {
                aux_path.append(path.charAt(i));
            }
        }
        
        fin = new File(aux_path.toString());
        //checks for permission to read file
        if (!fin.canRead())
            throw new SecurityException();
        
        //creates a buffered reader to the file
        BufferedReader br = new BufferedReader(new FileReader(fin));
        
        //initiates number of lists per list
        listsIndex = 0;     
        
        u.add(new LinkedList<Double>());
        v.add(new LinkedList<Double>());
        w.add(new LinkedList<Double>());        
        
        //reads the file line by line
        while ((line = br.readLine()) != null)
        {        
            //trims the edges
            line = line.trim();
            
            //splits line in multiple strings
            string_values = line.split("[ ]+");
            
            //gets double values from the strings
            double_values = stringToDouble(string_values);  
            
            if (double_values[0] != 0.0 || double_values[1] != 0.0 || double_values[2] != 0.0)
            {
                u.get(listsIndex).add(double_values[0]);
                v.get(listsIndex).add(double_values[1]);
                w.get(listsIndex).add(double_values[2]);
            }
            else
            {
                u.add(new LinkedList<Double>());
                v.add(new LinkedList<Double>());
                w.add(new LinkedList<Double>());
                listsIndex++;                
            }
        }
        
        nLines = u.get(0).size();
        nLists = u.size();
        
        //closes reader
        br.close();
    }
    
    private double[] stringToDouble(String string_values[]) throws ParseException
    {
        double double_values[] = new double[string_values.length];  //line values as doubles
        double coefficient, exponent;  //to calculate values in scientific notation;

        for (int i = 0; i < string_values.length; i++)
        {
            /* ex: -0.5900000 || 0.5900000 */
            if (string_values[i].matches("[-]?[0-9]+.[0-9]+"))
            {
                double_values[i] = Double.parseDouble(string_values[i]);
            }
            /* ex: -0.5900000E-04 || 0.5900000E+32 */
            else if (string_values[i].matches("[-]?[0-9]+.[0-9]+E[-+]?[0-9]+"))
            {
                //splits the string in coefficient and exponent
                String aux[] = string_values[i].split("E");
                coefficient = Double.parseDouble(aux[0]);
                exponent = Double.parseDouble(aux[1]);
                
                //calculates value
                double_values[i] = coefficient * Math.pow(10, exponent);
            }
            else
            {
                throw new ParseException("Cannot process: <" + string_values[i] + ">", 0);
            }
        }
        return double_values;
    }

    public void createJSONFile(String path) throws FileNotFoundException
    {
        JSONBuilder jbuilder = new JSONBuilder(path);
        
        jbuilder.put("numLinhas", nLines);
        jbuilder.put("u", u);
        jbuilder.put("v", v);
        jbuilder.put("w", w);
        jbuilder.put("numPontos", nLists);
        jbuilder.saveAndClose();

    }
    public int getnLines()
    {
        return nLines;
    }
    
    public int getnLists()
    {
        return nLists;
    }
    
    
    public void printLists()
    {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < nLists; i++)
        {
            for (int j = 0; j < u.get(i).size(); j++)
            {
                System.out.println(u.get(i).get(j) + "; " + v.get(i).get(j) + "; " + w.get(i).get(j));
            }
        }
    }
}
