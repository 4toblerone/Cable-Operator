/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemskeoperacije;

import dbb.KomunikacijaSaBazom;
import java.util.ArrayList;
import java.util.List;
import kab.op.domen.DomenskiObjekat;

/**
 *
 * @author sale
 */
public class SOVratiSveDomenskeObjekte {

    public List<DomenskiObjekat> izvrsiSO(DomenskiObjekat obj) throws Exception {
        List< DomenskiObjekat> listaDomenskiObjekata = new ArrayList<DomenskiObjekat>();
        listaDomenskiObjekata = KomunikacijaSaBazom.vratiInstancuKomunikacijeSaBazom().vratiListuDomenskihObjekata(obj);
        return listaDomenskiObjekata;
    }
}
