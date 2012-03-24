/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemskeoperacije;

import dbb.KomunikacijaSaBazom;
import kab.op.domen.DomenskiObjekat;

/**
 *
 * @author sale
 */
public class SOSacuvaj extends SOOpstaSO {

    @Override
    protected boolean izvrsiPreduslov(DomenskiObjekat obj) {
        //preduslov je da ne postoji takav domenski objekat vec...
    if(daLiPostoji(obj)){
        signal = 5;
        return false;
    }
    else {
        signal = 4;
    return  true;
    }
    }

    @Override
    protected int izvrsiOperaciju(DomenskiObjekat obj) {
      signal = KomunikacijaSaBazom.vratiInstancuKomunikacijeSaBazom().sacuvaj(obj);
     return signal;
    }

    @Override
    boolean stanjeOperacije(int signal) {
       switch(signal){
           case 4 : return true; //preduslov je zadovoljen
           case 5: return false;//preduslov nije zadovoljen
           case 6 : return true; //uspesno zapamceno
           case 7 : return false;//neuspesno zapamceno
               
       }
       return false;
    }

    private boolean daLiPostoji(DomenskiObjekat obj){
        //return false;
        return KomunikacijaSaBazom.vratiInstancuKomunikacijeSaBazom().daLiPostoji(obj);
    }
    
}
