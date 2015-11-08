/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.ua.deti.fff.parsers.utils;


import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Renato Rodrigues <renato.pinho@ua.pt>
 */
public class ReadXML {
    ArrayList<ArrayList<Float>> matriz = new ArrayList<>();
    
    public ReadXML(String path) {
        try {
            
            ArrayList<Float> linhaArray = null;
            File fXmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            
            doc.getDocumentElement().normalize();
            
            NodeList linhaList = doc.getElementsByTagName("linha");
            
            for (int temp = 0; temp < linhaList.getLength(); temp++) {
                
                Node linhaNode = linhaList.item(temp);
                
                if (linhaNode.getNodeType() == Node.ELEMENT_NODE) {
                    
                    Element linhaElement = (Element) linhaNode;
                    
                    NodeList colunaList = linhaElement.getElementsByTagName("coluna");
                    
                    linhaArray = new ArrayList<>();
                    for (int i = 0; i < colunaList.getLength(); i++) {
                        if (colunaList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                            Element colunaElement = (Element) colunaList.item(i);
                            linhaArray.add(Float.parseFloat(colunaElement.getTextContent()));                                                        
                        }                        
                    }
                    
                    matriz.add(linhaArray);
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public float[][] testToArray(){
        if(matriz.isEmpty())
            return new float [0][0];
        if(matriz.get(0).isEmpty())
            return new float[0][matriz.size()];
        
        float [][] ret =  new float[matriz.get(0).size()][matriz.size()];
        
        for(int x = 0; x < matriz.get(0).size() ;x++){
            for(int y = 0; y < matriz.size() ;y++){
                ret[x][y] = matriz.get(y).get(x);
            }
        }
        
        return ret;
    }
    
    public ArrayList<ArrayList<Float>> getMatriz() {
        return matriz;
    }
}
