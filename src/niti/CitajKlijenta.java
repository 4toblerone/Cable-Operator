/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import kab.op.domen.DomenskiObjekat;
import kontrolerPL.KontrolerPL;
import transfer.TransferObjekat;

/**
 *
 * @author Sale
 */
public class CitajKlijenta extends Thread {

    Socket soket;

    public CitajKlijenta(Socket soket) {
        this.soket = soket;
    }

    @Override
    public void run() {
        System.out.println("uspostavila se veza");
        while (!interrupted()) {
            try {
                ObjectInputStream inSoket = new ObjectInputStream(soket.getInputStream());
                Object obj = inSoket.readObject();
                TransferObjekat to = (TransferObjekat) obj;
                obradiZahtev(to);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void obradiZahtev(TransferObjekat to) throws Exception, IOException {
        //ovde saljem nazad klijentu ono sto je trazio
        //do kontrolera, on so operaciju ona do brokera
        ObjectOutputStream oos = new ObjectOutputStream(soket.getOutputStream());
        DomenskiObjekat domenskiObjekat = (DomenskiObjekat) to.getKlijentObjekat();
        int operacija = to.getOperacija();
        int povratniSignal = 0;

        if (operacija == util.Util.DODAJ_DOMENSKI_OBJEKAT) {

            povratniSignal = KontrolerPL.dodajDomenskiObjekat(domenskiObjekat);
            System.out.println(povratniSignal);
            to.setUspesnostOperacije(povratniSignal);
            oos.writeObject(to);
            oos.flush();
        }
        if (operacija == util.Util.IZMENI_DOMENSKI_OBJEKAT) {
            System.out.println("izmeni domenski objekat");
            povratniSignal = KontrolerPL.izmeniDomenskiObjekat(domenskiObjekat);
            to.setUspesnostOperacije(povratniSignal);
            oos.writeObject(to);
            oos.flush();

        }
        if (operacija == util.Util.OBRISI_DOMENSKI_OBJEKAT) {
            povratniSignal = KontrolerPL.obrisiDomenskiObjekat(domenskiObjekat);
            to.setUspesnostOperacije(povratniSignal);
            oos.writeObject(to);
            oos.flush();

        }


        if (operacija == util.Util.VRATI_SVE_DOMENSKE_OBJEKTE) {

            List<DomenskiObjekat> listaDomenskihObjekata = KontrolerPL.vratiSveDomenskeObjekte(domenskiObjekat);

            to.setServerObjekat(listaDomenskihObjekata);
            if (listaDomenskihObjekata != null) {
                povratniSignal = 12;//uspesno izvrseno vracanje liste, da li ovo treba ovde da se setuje ili negde drugde...
            } else {
                povratniSignal = 13;
            }
            to.setUspesnostOperacije(povratniSignal);
            oos.writeObject(to);
            oos.flush();
        }

        if (operacija == util.Util.VRATI_DOMENSKI_OBJEKAT) {

            DomenskiObjekat domObj = KontrolerPL.vratiDomenskiObjekat(domenskiObjekat);
            to.setServerObjekat(domObj);
            oos.writeObject(to);
            oos.flush();

        }
    }
}
