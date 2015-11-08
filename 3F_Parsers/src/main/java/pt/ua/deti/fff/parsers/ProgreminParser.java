/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ua.deti.fff.parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author diogo.vieira@ua.pt - 35479
 */
public class ProgreminParser {

    private int maxTime;
    private int delta;
    // VALORES
    private ArrayList<ArrayList<Integer>> values;

    public int getMaxTime() {
        return this.maxTime;
    }

    public int getDelta() {
        return this.delta;
    }

    public ArrayList<ArrayList<Integer>> getValues() {
        return this.values;
    }

    public ProgreminParser(String path) throws ParseException, IOException {
        try {
            this.values = new ArrayList<>();

            URL p = new URL(path);
            if (p == null) {
                throw new ParseException("Failed opening URL.", 0);
            }

            InputStream input;
            InputStreamReader ISR;
            input = p.openStream();
            ISR = new InputStreamReader(input);
            if (ISR == null) {
                throw new ParseException("Failed creating InputStreamReader.", 0);
            }
            BufferedReader in;
            in = new BufferedReader(ISR);
            if (in == null) {
                throw new ParseException("Failed creating BufferedReader.", 0);
            }

            String inputLine;
            Integer linha = 0;
            while ((inputLine = in.readLine()) != null) {
                String[] aux = inputLine.split(" ");
                if (linha == 0) {
                    maxTime = Integer.parseInt(aux[0]);
                } else if (linha == 1) {
                    delta = Integer.parseInt(aux[0]);
                } else {
                    ArrayList<Integer> tmpList = new ArrayList<>();
                    for (int i = 0; i < aux.length; i++) {
                        tmpList.add(Integer.parseInt(aux[i]));
                    }
                    this.values.add(tmpList);
                }
                linha++;
            }
        } catch (ParseException | IOException e) {
            throw e;
        }
    }
}