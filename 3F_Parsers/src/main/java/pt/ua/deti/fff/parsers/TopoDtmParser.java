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
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.pkcs.ParsingException;

/**
 *
 * @author tiagosousa - 50170
 */
public class TopoDtmParser 
{
    private String title;                           // Titulo
    private Cells numCells;                         // Numero de celulas em x, y e z
    private Resolution resolution;                  // Resolucao em x e y
    private double maxHeight;                       // Altitude maxima
    private MilitarCoordinates originCoordinates;   // Coordenadas na origem
    private final List<Double> sigma;               // Níveis Sigma (fixos)
    private List<List<Double>> topoValues;          // Topografia em matriz
    
    private Date criationTime;                      // Data e Hora de geracao do ficheiro
    
    public TopoDtmParser(String filePath) throws MalformedURLException, IOException, ParseException
    {
        URL path = new URL(filePath);
        criationTime = new Date();
        
        InputStream input = path.openStream();
        InputStreamReader reader = new InputStreamReader(input);
        BufferedReader in = new BufferedReader(reader);
        
        String inputLine;
        
        // Primeira Linha - Titulo
        title = in.readLine();
        
        // Segunda Linha - Numero de celulas em x, y e z
        inputLine = in.readLine();
        String [] tmp = inputLine.split(" ");
        ArrayList<String> spacesDeleted = deleteSpaces(tmp);
        numCells = new Cells();
        try
        {
            numCells.x = Integer.parseInt(spacesDeleted.get(0));
            numCells.y = Integer.parseInt(spacesDeleted.get(1));
            numCells.z = Integer.parseInt(spacesDeleted.get(2));
        }
        catch(Exception e)
        {
            throw new ParseException("Invalid number of cells !", 1);
        }
        
        // Terceira Linha - Resolucao em X e Y
        inputLine = in.readLine();
        tmp = inputLine.split(" ");
        spacesDeleted = deleteSpaces(tmp);
        resolution = new Resolution();
        try
        {
            resolution.width = Double.parseDouble(spacesDeleted.get(0));
            resolution.height = Double.parseDouble(spacesDeleted.get(1));
        }
        catch(Exception e)
        {
            throw new ParseException("Invalid resolution !", 2);
        }
        
        // Quarta Linha - Altitude maxima
        try
        {
            maxHeight = Double.parseDouble(in.readLine());
        }
        catch(Exception e)
        {
            throw new ParseException("Invalid max height value", 3);
        }
        
        // Quinta Linha - Coordenadas na Origem
        inputLine = in.readLine();
        tmp = inputLine.split(" ");
        spacesDeleted = deleteSpaces(tmp);
        originCoordinates = new MilitarCoordinates();
        try
        {
            originCoordinates.x = Double.parseDouble(spacesDeleted.get(0));
            originCoordinates.y = Double.parseDouble(spacesDeleted.get(1));
        }
        catch(Exception e)
        {
            throw new ParseException("Invalid origin coordinates !", 4);
        }
        
        // Sigma
        sigma = new ArrayList<Double>();
        int lineNumber = 5;
        while(!(inputLine = in.readLine()).equals("VALUES FOLLOW"))
        {
            try
            {
                sigma.add(Double.parseDouble(inputLine));
            }
            catch(Exception e)
            {
                throw new ParseException("Invalid sigma values", lineNumber);
            }
            finally
            {
                lineNumber++;
            }
        }
        
        // Valores da matriz
        topoValues = new ArrayList<>();
        while((inputLine = in.readLine()) != null)
        {
            String [] line = inputLine.split(" ");
            ArrayList<Double> tmpList = new ArrayList<Double>();
            for(int i = 0; i < line.length; i++)
                tmpList.add(Double.parseDouble(line[i]));
            
            topoValues.add(tmpList);
        }
        
        in.close();
        
        int colsNumb = -1;
        for(int i = 0; i < topoValues.size();i++){
            
            if(colsNumb == -1) // first time cycle run
                colsNumb = topoValues.get(i).size();
            else{
                if(colsNumb != topoValues.get(i).size())
                    throw new ParseException("Number of columns not equal for every lines. Location: row:"+i+".",i);
            }
                for(int j = 0; j < topoValues.get(i).size();j++){
                    if(topoValues.get(i).get(j) < 0 && topoValues.get(i).get(j) < -1)
                        throw new ParseException("Cell number ii less the '0' and diferent of '-1'. Location: row:"+i+" column:"+j+".",j);
            }
        }
    }

    
    /* Methods Get */
    public String getTitle() {
        return title;
    }

    public Cells getNumCells() {
        return numCells;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public double getMaxHeight() {
        return maxHeight;
    }

    public MilitarCoordinates getOriginCoordinates() {
        return originCoordinates;
    }

    public List<Double> getSigma() {
        return sigma;
    }

    public List<List<Double>> getTopoValues() {
        return topoValues;
    }

    public Date getCriationTime() {
        return criationTime;
    }
    
    @Override
    public String toString()
    {
        String res = "File parsed at " + criationTime.toString() + "\n";
        res += "Title: " + title + "\n";
        res += "Number of cells: x = " + numCells.x + ", y = " + numCells.y + ", z = " + numCells.z + "\n";
        res += "Resolution: width = " + resolution.width + ", height = " + resolution.height + "\n";
        res += "Origin Coordinates: x = " + originCoordinates.x + ", y = " + originCoordinates.y + "\n";
        res += "Sigma values:\n";
        
        Iterator<Double> it = sigma.iterator();
        while(it.hasNext())
            res += it.next() + "; ";
        
        return res;
    }
            
    /* Classes Auxiliares */
    class Cells
    {
        int x, y, z;
    }
    
    class Resolution
    {
        double width, height;
    }
    
    class MilitarCoordinates
    {
        double x, y;
    }
    
    /* Métodos Auxiliares */
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
//        TopoDtmParser tp;
//        try {
//            System.out.println("Testing class: TopoDtmParser");
//
//            tp = new TopoDtmParser("https://dl.dropboxusercontent.com/u/5952458/topo.dtm");
//            System.out.println(tp);
//
//            System.out.println("Sucessed test on class: TopoDtmParser");
//
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(ParsingTesteSergioMartins.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("Failed test on class: TopoDtmParser");
//        } catch (IOException ex) {
//            Logger.getLogger(ParsingTesteSergioMartins.class.getName()).log(Level.SEVERE, null, ex);
//        System.out.println("Failed test on class: TopoDtmParser");
//        } catch (ParseException ex) {
//            Logger.getLogger(ParsingTesteSergioMartins.class.getName()).log(Level.SEVERE, null, ex);
//        System.out.println("Failed test on class: TopoDtmParser");
//        }
//    }
}
