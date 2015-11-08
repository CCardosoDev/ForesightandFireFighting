/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiagosousa - 50170
 */
public class WindObsParser 
{
    private int stationNumber;                  // Numero da estacao
    private NewDate date;                       // Hora dia mês ano
    private Coordinates stationCoordinates;     // Coordenadas da estacao
    private double elevation;                   // Altitude
    private int numObservations;                // Numero de observacoes em altura
    private WindInfo info;                      
    private String endOfFile;                   // Indicador de fim de ficheiro
    
    private Date criationTime;          // Data e Hora de geracao do ficheiro
    
    /* Construtor */
    /**
     *
     * @param filePath URI to file to be parsed
     */
    public WindObsParser(String filePath) throws MalformedURLException, IOException, ParseException
    {
        URL path = new URL(filePath);
        criationTime = new Date();
        
        InputStream input = path.openStream();
        InputStreamReader reader = new InputStreamReader(input);
        BufferedReader in = new BufferedReader(reader);
        
        String inputLine;
        
        // Primeira Linha - Numero da estacao
        inputLine = in.readLine();
        try
        {
            stationNumber = Integer.parseInt(inputLine);
        }
        catch(Exception e)
        {
            throw new ParseException("Invalid station number !", 0);
        }
        
        // Segunda Linha - Hora e Data
        inputLine = in.readLine();
        String [] tmp = inputLine.split(" ");
        ArrayList<String>spacesDeleted = deleteSpaces(tmp);
        date = new NewDate();
        try
        {
            date.hourAndMinutes = Integer.parseInt(spacesDeleted.get(0));
            date.day = Integer.parseInt(spacesDeleted.get(1));
            date.month = Integer.parseInt(spacesDeleted.get(2));
            date.year = Integer.parseInt(spacesDeleted.get(3));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new ParseException("Invalid date !", 1);
        }
        
        // Terceira Linha - Coordenadas da Estacao
        inputLine = in.readLine();
        tmp = inputLine.split(" ");
        spacesDeleted = deleteSpaces(tmp);
        stationCoordinates = new Coordinates();
        try
        {
            stationCoordinates.x = Double.parseDouble(spacesDeleted.get(0));
            stationCoordinates.y = Double.parseDouble(spacesDeleted.get(1));
        }
        catch(Exception e)
        {
            throw new ParseException("Invalid station coordinates !", 2);
        }
        
        // Quarta Linha - Altirude
        inputLine = in.readLine();
        try
        {
            elevation = Double.parseDouble(inputLine);
        }
        catch(Exception e)
        {
            throw new ParseException("Invalid elevation value !", 3);
        }
        
        // Quinta Linha - Numero de observacoes em altura
        inputLine = in.readLine();
        try
        {
            numObservations = Integer.parseInt(inputLine);
        }
        catch(Exception e)
        {
            throw new ParseException("Invalid number of observations !", 4);
        }
        
        // Sexta Linha - Informacao do Vento
        inputLine = in.readLine();
        tmp = inputLine.split(" ");
        spacesDeleted = deleteSpaces(tmp);
        info = new WindInfo();
        try
        {
            info.u = Double.parseDouble(spacesDeleted.get(0));
            info.v = Double.parseDouble(spacesDeleted.get(1));
            info.x = Double.parseDouble(spacesDeleted.get(2));
            info.y = Double.parseDouble(spacesDeleted.get(3));
            info.z = Double.parseDouble(spacesDeleted.get(4));
        }
        catch(Exception e)
        {
            throw new ParseException("Invalid wind information !", 5);
        }
        
        
        // Setima Linha - Indicador de fim de ficheiro
        endOfFile = in.readLine();
        
        
        in.close();
    }
 
    /* Get Methods */
    /**
     * @return the station number
     */
    public int getStationNumber() {
        return stationNumber;
    }

    /**
     * @return the hour and date of the measurement
     */
    public NewDate getDate() {
        return date;
    }

    /**
     * @return the station coordinates
     */
    public Coordinates getStationCoordinates() {
        return stationCoordinates;
    }

    /**
     * @return the elevation value
     */
    public double getElevation() {
        return elevation;
    }
    
    
    /**
     * @return the number of observations
     */
    public int getNumObservations() {
        return numObservations;
    }

    /**
     * @return the information about the wind (u, v, x, y, z)
     */
    public WindInfo getInfo() {
        return info;
    }

    /**
     * @return the end of file line terminator
     */
    public String getEndOfFile() {
        return endOfFile;
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
        String res = "File parsed at " + criationTime.toString() + "\n";
        res += "Station number: " + stationNumber + "\n";
        res += "Date: " + date.hourAndMinutes + ", " + date.day + "/" + date.month + "/" + date.year + "\n";
        res += "Station Coordinates: " + stationCoordinates.x + " ; " + stationCoordinates.y + "\n";
        res += "Elevation: " + elevation + "\n";
        res += "Number of observations: " + numObservations + "\n";
        res += "Wind info:\nu = " + info.u + "\nv = " + info.v + "\nx = " + info.x + "\ny = " + info.y + "\nz = " + info.z + "\n";
        res += "End Of File: " + endOfFile;
        
        return res;
    }
    
    /* --- Classes Auxiliares --- */
    class NewDate
    {
        int hourAndMinutes;
        int day, month, year;
    }
    
    class Coordinates
    {
        double x, y;
    }
    
    class WindInfo
    {
        double u, v, x, y, z;
    }
    
    /* Metodos auxiliares */
    private ArrayList<String> deleteSpaces(String [] array)
    {
        ArrayList<String> res = new ArrayList<>();
        for(int i = 0; i < array.length; i++)
            if(!array[i].equals(""))
                res.add(array[i]);
        return res;
    }
    
    /* --- TEST --- */
//    public static void main(String[]args)
//    {
//        WindObsParser wnd;
//        try {
//            System.out.println("Testing class: WindObsParser");
//
//            wnd = new WindObsParser("https://dl.dropboxusercontent.com/u/5952458/wind.obs");
//            System.out.println(wnd);
//
//            System.out.println("Sucessed test on class: WindObsParser");
//
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(ParsingTesteSergioMartins.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("Failed test on class: WindObsParser");
//        } catch (IOException ex) {
//            Logger.getLogger(ParsingTesteSergioMartins.class.getName()).log(Level.SEVERE, null, ex);
//        System.out.println("Failed test on class: WindObsParser");
//        } catch (ParseException ex) {
//            Logger.getLogger(ParsingTesteSergioMartins.class.getName()).log(Level.SEVERE, null, ex);
//        System.out.println("Failed test on class: WindObsParser");
//        }
//    }
}
