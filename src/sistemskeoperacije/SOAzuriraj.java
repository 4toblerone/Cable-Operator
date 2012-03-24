/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemskeoperacije;

import dbb.KomunikacijaSaBazom;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kab.op.domen.DomenskiObjekat;

/**
 *
 * @author Sale
 */
public class SOAzuriraj extends SOOpstaSO {

    @Override
    protected boolean izvrsiPreduslov(DomenskiObjekat obj) {
        if (daLiPostoji(obj)){
              signal = 4;
              return true;
          }
          else {
              signal= 5;
              return false;
          }
    }

    @Override
    protected int izvrsiOperaciju(DomenskiObjekat obj) {
        signal = KomunikacijaSaBazom.vratiInstancuKomunikacijeSaBazom().azuriraj(obj);
        return signal;
    }

    @Override
    boolean stanjeOperacije(int signal) {
        switch (signal) {
            case 4:
                return true; //preduslov je zadovoljen
            case 5:
                return false;//preduslov nije zadovoljen
            case 10:
                return true; //uspesno azurirano
            case 11:
                return false;//neuspesno azurirano

        }
        return false;
    }
    
     private boolean daLiPostoji(DomenskiObjekat obj){
   
   return KomunikacijaSaBazom.vratiInstancuKomunikacijeSaBazom().daLiPostoji(obj);
   }
}
