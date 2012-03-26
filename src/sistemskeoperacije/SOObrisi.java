/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemskeoperacije;

import dbb.KomunikacijaSaBazom;
import kab.op.domen.DomenskiObjekat;

/**
 *
 * @author Sale
 */
public class SOObrisi extends SOOpstaSO {


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
     signal = KomunikacijaSaBazom.vratiInstancuKomunikacijeSaBazom().obrisi(obj);
     return signal;
    }

    @Override
    boolean stanjeOperacije(int signal) {
        switch(signal){
            case 4: return true;//preduslov je zadovoljen
            case 5: return false;//preduslov nije zadovoljen
            case 8: return true;//uspesno obrisan
            case 9 : return false;//neuspesno obrisan 
        }
        
        return false;
    }
    
   private boolean daLiPostoji(DomenskiObjekat obj){
   
   return KomunikacijaSaBazom.vratiInstancuKomunikacijeSaBazom().daLiPostoji(obj);
   }
    
    
}
