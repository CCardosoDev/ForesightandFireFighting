package pt.ua.deti.fff.parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @version 0.1
 * @author Sergio Martins, sfsm@ua.pt
 * 
 * 3F
 * Parsing class for files of type matrix
 */
public class MatrixFileParser {
    private String fileName;
    private Date criationTime;
    private List<List<Float>> values;
    
    /**
     *
     * @param path URI to file to be parsed
     */
    public MatrixFileParser(String path) throws MalformedURLException, IOException, ParseException{
        fileName = path.toString();
        criationTime =  new Date();
        URL p = new URL(path);
        InputStream input;
        input = p.openStream();
        InputStreamReader ISR;
        ISR = new InputStreamReader(input);
        
        BufferedReader in;
        in = new BufferedReader(ISR);
        
        values = new ArrayList<>();
        
        String inputLine;
        while ((inputLine = in.readLine()) != null){
            
            String [] aux = inputLine.split(" ");
            
            ArrayList<Float> tempList;
            tempList = new ArrayList<>();
            
            for(int i = 0; i < aux.length; i++){
                tempList.add(Float.parseFloat(aux[i]));
            }
            values.add(tempList);
            
        }
        in.close();
       
        int colsNumb = -1;
        for(int i = 0; i < values.size();i++){
            
            if(colsNumb == -1) // first time cycle run
                colsNumb = values.get(i).size();
            else{
                if(colsNumb != values.get(i).size())
                    throw new ParseException("Number of columns not equal for every lines. Location: row:"+i+".",i);
            }
                for(int j = 0; j < values.get(i).size();j++){
                    if(values.get(i).get(j) < 0 && values.get(i).get(j) < -1)
                        throw new ParseException("Cell number ii less the '0' and diferent of '-1'. Location: row:"+i+" column:"+j+".",j);
            }
        }
    }
    
    /**
     * Print of parsed content. Just for debug and tests.
     */
    public void print(){
        
        System.out.println("Class type:    "+this.getClass().toString());
        System.out.println("Creation time: "+criationTime.toString());
        System.out.println("Origin File:   "+fileName);
        
        
        float [][] aux = this.toArray();
        
        for (float[] arr : aux) {
                System.out.println(Arrays.toString(arr));
            }
    }
    
    
    /**
     * @return Content in primitive types.
     */
    public float[][] toArray(){
        if(values.isEmpty())
            return new float [0][0];
        if(values.get(0).isEmpty())
            return new float[0][values.size()];
        
        float [][] ret =  new float[values.get(0).size()][values.size()];
        
        for(int x = 0; x < values.get(0).size() ;x++){
            for(int y = 0; y < values.size() ;y++){
                ret[x][y] = values.get(y).get(x);
            }
        }
        
        return ret;
    }

    /**
     * @return the name of the source file
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @return the time when data was parsed
     */
    public Date getCriationTime() {
        return criationTime;
    }
    
    /**
     * @return number of columns of data. Column number is like 'x' axis coordinate;
     */
    public int nCols(){
        if(values.isEmpty())
            return 0;
        if(values.get(0).isEmpty())
            return 0;
        return values.get(0).size();
    }
    
    /**
     * @return number of rows of data. Row number is like 'y' axis coordinate;
     */
    public int nRows(){
        if(values.isEmpty())
            return 0;
        return values.size();
    }

    public List<List<Float>> getValues() {
        return values;
    }
    
}
