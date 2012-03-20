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
 * @author sale
 */
public class SOAzuriraj extends SOOpstaSO{

    @Override
    protected boolean izvrsiPreduslov(DomenskiObjekat obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected int izvrsiOperaciju(DomenskiObjekat obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    boolean stanjeOperacije(int signal) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

}
