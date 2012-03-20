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
public class SOObrisi extends SOOpstaSO {


    @Override
    protected boolean izvrsiPreduslov(DomenskiObjekat obj) {
            //domenski objekat postoji...
       throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected int izvrsiOperaciju(DomenskiObjekat obj) {
        //obrisi iz baze
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    boolean stanjeOperacije(int signal) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
   
    
    
}
