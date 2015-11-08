package pt.ua.deti.fff.parsers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 0.1
 * @author Sergio Martins, sfsm@ua.pt
 */
public class ParsingTesteSergioMartins {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        CalorParser calor;
        try {
            System.out.println("Testing class: CalorParser");

            calor = new CalorParser("https://dl.dropbox.com/s/8duc2ulm577q1cv/calor.asc?token_hash=AAEeDXAkFseZFsF6g2EMAhupsm_JPebUgxuYdSCsXT1vIA&dl=1");
            calor.print();

            System.out.println("Sucessed test on class: CalorParser");

        } catch (MalformedURLException ex) {
            Logger.getLogger(ParsingTesteSergioMartins.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed test on class: CalorParser");
        } catch (IOException ex) {
            Logger.getLogger(ParsingTesteSergioMartins.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Failed test on class: CalorParser");
        } catch (ParseException ex) {
            Logger.getLogger(ParsingTesteSergioMartins.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Failed test on class: CalorParser");
        }

        CargaParser carga;
        try {
            System.out.println("Testing class: CargaParser");

            carga = new CargaParser("https://dl.dropbox.com/s/gh79xm5k6dh5b0m/carga.asc?token_hash=AAHayOKrJDnG0X8w_8CuMXnSMKQb_XWLRroz9VVdTtxzeg&dl=1");
            carga.print();
            
            System.out.println("Sucessed test on class: CargaParser");

        } catch (MalformedURLException ex) {
            Logger.getLogger(ParsingTesteSergioMartins.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Failed test on class: CargaParser");
        } catch (IOException ex) {
            Logger.getLogger(ParsingTesteSergioMartins.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Failed test on class: CargaParser");
        } catch (ParseException ex) {
            Logger.getLogger(ParsingTesteSergioMartins.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Failed test on class: CargaParser");
        }

        VelprogParser velprog;
        try {
            System.out.println("Testing class: VelprogParser");

            velprog = new VelprogParser("https://dl.dropbox.com/s/mpwleef8e2t85rq/velprog.asc?token_hash=AAE-Yj7lRDQRdIJ-NHsab7CZq3BwsPQdOdGcS7xfHdBhFA&dl=1");
            velprog.print();
            
            System.out.println("Sucessed test on class: VelprogParser");

        } catch (MalformedURLException ex) {
            Logger.getLogger(ParsingTesteSergioMartins.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Failed test on class: VelprogParser");
        } catch (IOException ex) {
            Logger.getLogger(ParsingTesteSergioMartins.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Failed test on class: VelprogParser");
        } catch (ParseException ex) {
            Logger.getLogger(ParsingTesteSergioMartins.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Failed test on class: VelprogParser");
        }
    }
}
