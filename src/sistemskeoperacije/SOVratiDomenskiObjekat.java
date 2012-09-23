/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemskeoperacije;

import dbb.KomunikacijaSaBazom;
import java.sql.SQLException;
import kab.op.domen.DomenskiObjekat;

/**
 *
 * @author Sale
 */
public class SOVratiDomenskiObjekat {
    
    
    public DomenskiObjekat izvrsiSO(DomenskiObjekat domenskiObjekat) throws SQLException, Exception{
    
    DomenskiObjekat domObj =    KomunikacijaSaBazom.vratiInstancuKomunikacijeSaBazom().vratiDomenskiObjekat(domenskiObjekat);
    return domObj;
    }
    
}
