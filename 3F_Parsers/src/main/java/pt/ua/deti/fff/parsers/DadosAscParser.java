package pt.ua.deti.fff.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import pt.ua.deti.fff.parsers.dataTypes.FuelType;
import pt.ua.deti.fff.parsers.dataTypes.IDT;
import pt.ua.deti.fff.parsers.dataTypes.NTime;
import pt.ua.deti.fff.parsers.dataTypes.Stab;

/**
 *
 * @author Francisco Vaz, nº 26572
 */
public class DadosAscParser {
    private String title;
    private NTime time;
    private IDT idt;
    private int nFuelTypes;
    private int density;
    private FuelType[] fuelTypes;
    private int absort;
    private int reflex;
    private Stab stab;


    public DadosAscParser(String path) throws Exception
    {
        File fin; //input file
        StringBuilder aux_path; //processed path
        String line;    //line read from the file
        String aux[];   //auxiliary string
        int hour, minute, second;   //time values
        int idt_input, idt_output;  //itd values
        int fuel_value; //fuel value
        StringBuilder fuel_type;   //fuel type
        
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
        
        
        /*
         * TITLE line
         */
        if ((line = br.readLine()) == null)
            throw new ParseException("Incomplete file", 0);
        
        //trims the edges and replaces tabs by single spaces
        title = line.trim().replace("\t", " ");
        
        
        /*
         * TIME line
         */
        if ((line = br.readLine()) == null)
            throw new ParseException("Incomplete file", 0);
        
        //trims the edges and splits by tabs
        aux = line.trim().split("\t");
        
        //get time values
        try
        {
            hour = Integer.parseInt(aux[0]);
            minute = Integer.parseInt(aux[1]);
            second = Integer.parseInt(aux[2]);
        }
        catch (NumberFormatException e)
        {
            throw new ParseException("Cannot process: <" + line + ">", 0);
        }
        
        //set time values
        try
        {
            time = new NTime(hour, minute, second);
        }
        catch (IllegalArgumentException e)
        {
            throw new ParseException("Bad time values: <" + line + "> ", 0);
        }        
        
        
        /*
         * //IDT input output line
         */        
        if ((line = br.readLine()) == null)
            throw new ParseException("Incomplete file", 0);
        
        //trims the edges and splits by tabs
        aux = line.trim().split("\t");
        
        //get idt values
        try
        {
            idt_input = Integer.parseInt(aux[0]);
            idt_output = Integer.parseInt(aux[1]);
        }
        catch (NumberFormatException e)
        {
            throw new ParseException("Cannot process: <" + line + ">", 0);
        }
        
        //set idt values
        idt = new IDT(idt_input, idt_output);
        

        /*
         * Number of fuel types line
         */
        if ((line = br.readLine()) == null)
            throw new ParseException("Incomplete file", 0);
        
        //trims the edges and splits by tabs
        aux = line.trim().split("\t");
        
        //set number of fuel types
        try
        {
            nFuelTypes = Integer.parseInt(aux[0]);
        }
        catch (NumberFormatException e)
        {
            throw new ParseException("Cannot process: <" + line + ">", 0);
        }
        
        /*
         * DENSITY line
         */
        if ((line = br.readLine()) == null)
            throw new ParseException("Incomplete file", 0);
        
        //trims the edges and splits by tabs
        aux = line.trim().split("\t");
        
        //sets density value
        try
        {
            density = Integer.parseInt(aux[0]);
        }
        catch (NumberFormatException e)
        {
            throw new ParseException("Cannot process: <" + line + ">", 0);
        }
        
        /*
         * FUEL TYPE VALUES lines
         */
        fuelTypes = new FuelType[nFuelTypes];
        for (int i = 0; i < nFuelTypes; i++)
        {
            if ((line = br.readLine()) == null)
                throw new ParseException("Incomplete file", 0);
            
            //trims the edges and splits by tabs
            aux = line.trim().split("\t");

            //gets fuel value
            try
            {
                fuel_value = Integer.parseInt(aux[0]);
            }
            catch (NumberFormatException e)
            {
                throw new ParseException("Cannot process: <" + line + ">", 0);
            }
            
            //gets fuel type
            fuel_type = new StringBuilder();
            for (int j = 1; j < aux.length; j++)
            {
                fuel_type.append(aux[j]);
                if (!(j == aux.length - 1))
                {
                    fuel_type.append(" ");
                }
            }
            
            //sets fuel type and value
            fuelTypes[i] = new FuelType(fuel_value, fuel_type.toString());
        }
        
        /*
         * ABSORTION and REFLECTION line
         */
        if ((line = br.readLine()) == null)
            throw new ParseException("Incomplete file", 0);
        
        //trims the edges and splits by tabs
        aux = line.trim().split("\t");
        
        //set absortion and reflection values
        try
        {
            absort = Integer.parseInt(aux[0]);
            reflex = Integer.parseInt(aux[1]);            
        }
        catch (NumberFormatException e)
        {
            throw new ParseException("Cannot process: <" + line + ">", 0);
        }
        
        /*
         * ABSORTION and REFLECTION line
         */
        if ((line = br.readLine()) == null)
            throw new ParseException("Incomplete file", 0);
        
        //trims the edges and splits by tabs
        aux = line.trim().split("\t");
        
        //set stability value
        switch(Integer.parseInt(aux[0]))
        {
            case (1) :  stab = Stab.ONE;
                        break;
            case (2) :  stab = Stab.TWO;
                        break;
            case (3) :  stab = Stab.THREE;
                        break;
            default  :  throw new ParseException("Cannot process: <" + line + ">", 0);
        }       
    }
    
    //
    public void printValues()
    {
        System.out.println("DADOS.ASC");
        System.out.println("TITULO: " + title);
        System.out.println("TEMPO: " + time.getHour() + "h " + time.getMinute() + "m " + time.getSecond() + "s");
        System.out.println("IDT: " + idt.getInput()+ " input;  " + idt.getOutput()+ " output ");
        System.out.println("Nº COMBUSTIVEIS: " + nFuelTypes);
        System.out.println("DENSIDADE: " + density);
        System.out.println("TIPOS DE COMBUSTIVEL E VALORES");
        for (int x = 0; x < fuelTypes.length; x++)
        {
            System.out.println("\t" + fuelTypes[x].getValue() + "\t" + fuelTypes[x].getType());
        }
        System.out.println("ABSORCAO: " + absort);
        System.out.println("REFLEXAO: " + reflex);
        System.out.println("ESTABILIDADE: " + stab.getValue());
    }

    public String getTitle()
    {
        return title;
    }

    public NTime getTime()
    {
        return time;
    }

    public IDT getIdt()
    {
        return idt;
    }

    public int getnFuelTypes()
    {
        return nFuelTypes;
    }

    public int getDensity()
    {
        return density;
    }

    public FuelType[] getFuelTypes()
    {
        return fuelTypes;
    }

    public int getAbsort()
    {
        return absort;
    }

    public int getReflex()
    {
        return reflex;
    }

    public Stab getStab()
    {
        return stab;
    }   
}