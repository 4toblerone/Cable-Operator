/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolerPL;

import java.util.List;
import kab.op.domen.DomenskiObjekat;
import sistemskeoperacije.*;

/**
 *
 * @author Sale
 */
public class KontrolerPL {

    public static int dodajDomenskiObjekat(DomenskiObjekat domenskiObjekat) {
        SOSacuvaj soSacuvaj = new SOSacuvaj();
        return soSacuvaj.izvrsiSO(domenskiObjekat);

    }

    public static int izmeniDomenskiObjekat(DomenskiObjekat domenskiObjekat) {
        SOAzuriraj so = new SOAzuriraj();
        return so.izvrsiSO(domenskiObjekat);

    }

    public static int obrisiDomenskiObjekat(DomenskiObjekat domenskiObjekat) {
        SOObrisi soObrisi = new SOObrisi();
        return soObrisi.izvrsiSO(domenskiObjekat);
    }

    public static DomenskiObjekat vratiDomenskiObjekat(DomenskiObjekat domenskiObjekat) throws Exception {
        SOVratiDomenskiObjekat soVratiDomenskiObjekat = new SOVratiDomenskiObjekat();
        return soVratiDomenskiObjekat.izvrsiSO(domenskiObjekat);

    }

    public static List<DomenskiObjekat> vratiSveDomenskeObjekte(DomenskiObjekat domenskiObjekat) throws Exception {
        SOVratiSveDomenskeObjekte soVratiSveDomenskeObjekte = new SOVratiSveDomenskeObjekte();
        return soVratiSveDomenskeObjekte.izvrsiSO(domenskiObjekat);
    }
}

