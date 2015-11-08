/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.ua.deti.fff.parsers.utils;

import java.util.ArrayList;

/**
 *
 * @author Renato Rodrigues <renato.pinho@ua.pt>
 */
public class testReader {

    
    public static void main(String[] args) {
        ReadXML reader = new ReadXML("C:\\Users\\Renato\\Dropbox\\My Shared Folders\\A - Partilha\\Cadeiras\\4Ano\\ES\\Ficheiros\\disperfire\\matrizes\\carga.xml");
        
        ArrayList<ArrayList<Float>> xpto = reader.getMatriz();
        
        for(ArrayList<Float> ar : xpto) {
            System.out.println("----------------");
            
            for(Float f : ar){
                System.out.println(f);
            }
        }
    }
}
