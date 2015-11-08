package pt.ua.deti.fff.parsers;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import net.sf.json.JSONObject;

/**
 *
 * @author Tiago Magalhaes <tiagomagalhaes@ua.pt>
 */
public class JSONBuilder {
    //atributos
    private PrintWriter out;
    private JSONObject json;

    /**
     * Cria um novo objeto JSONBuilder.
     *
     * @param name nome do ficheiro (SEM EXTENSÃO)
     */
    public JSONBuilder(String name) {
        try {
            out = new PrintWriter(name + ".json");
        } catch (FileNotFoundException ex) {
        }
        json = new JSONObject();
    }
    
    /**
     * Escreve o atributo "key" e o seu valor "value" no ficheiro.
     * @param key nome do atributo.
     * @param value valor do atributo.
     */
    public void put(String key, Object value){
        json.put(key, value);
    }

    /**
     * Grava o ficheiro.
     */
    public void saveAndClose() {
        out.print(json);
        out.flush();
        out.close();
    }
    
    /**
     * Devolve o conteúdo do objeto JSONBuilder numa string.
     * @return conteúdo do objeto JSONBuilder.
     */
    @Override
    public String toString(){
        return json.toString();
    }
}
