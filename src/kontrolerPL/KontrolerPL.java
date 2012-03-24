/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolerPL;

import java.util.List;
import kab.op.domen.DomenskiObjekat;
import kab.op.domen.Korisnik;
import kab.op.domen.MesecnoDugovanje;
import kab.op.domen.Osoba;
import kab.op.domen.Paket;
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
/*


public static void izmiriDugovanje(MesecnoDugovanje dugovanje)
{
new SOIzmiriDugovanje().izmiriDugovanje(dugovanje);
}

public static void promeniKorisnickiPaket(Paket paket)
{
new SOPromeniKorisnickiPaket().promeniKorisnickiPaket(paket);
}

public static void dodajKorisnickiPaket(Paket paket)
{
new SODodajKorisnickiPaket().dodajKorisnickiPaket(paket);
}

public static Korisnik vratiKorisnika(String id)
{
return new SONadjiKorisnika().nadjiKorisnika(id);
}

public static List<Korisnik> vratiListuKorisnika()
{
return  new SOVratiSveKorisnike().vratiSveKorisnike();
}

public static List<MesecnoDugovanje> vratiMesecnaDugovanjaKorisnika(Korisnik korisnik)
{

return new SOVratiListuDugovanja().vratiListuDugovanja(korisnik);
}

public static List<Paket> vratiListuPaketa() {
throw new UnsupportedOperationException("Not yet implemented");
}
 */
