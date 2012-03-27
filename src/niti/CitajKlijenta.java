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
        DomenskiObjekat domenskiObjekat = (DomenskiObjekat) to.getKlijentObjekat();
        int operacija = to.getOperacija();
        int povratniSignal = 0;

        if (operacija == util.Util.DODAJ_DOMENSKI_OBJEKAT) {

          povratniSignal =  KontrolerPL.dodajDomenskiObjekat(domenskiObjekat);
            System.out.println(povratniSignal);
          to.setUspesnostOperacije(povratniSignal);
          ObjectOutputStream oos = new ObjectOutputStream(soket.getOutputStream());
          oos.writeObject(to);
          oos.flush();
        }
        if (operacija == util.Util.IZMENI_DOMENSKI_OBJEKAT) {

            povratniSignal =     KontrolerPL.izmeniDomenskiObjekat(domenskiObjekat);

            if (operacija == util.Util.OBRISI_DOMENSKI_OBJEKAT) {
               povratniSignal =  KontrolerPL.obrisiDomenskiObjekat(domenskiObjekat);

            }
        }

        if (operacija == util.Util.VRATI_SVE_DOMENSKE_OBJEKTE) {

            List<DomenskiObjekat> listaDomenskihObjekata = KontrolerPL.vratiSveDomenskeObjekte(domenskiObjekat);
            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            to.setServerObjekat(listaDomenskihObjekata);
            out.writeObject(to);
            
            /* try {

            List<DomenskiObjekat> listaOsoba = new ArrayList<DomenskiObjekat>();


            DomenskiObjekat osobaDomObjekat = new Osoba();
            System.out.println("ispisiSveOsobe1");
            listaOsoba = KomunikacijaSaBazom.vratiInstancuKomunikacijeSaBazom().vratiListuDomenskihObjekata(osobaDomObjekat);
            System.out.println("ispisiSveOsobe 2");
            for (DomenskiObjekat domenskiObjekat1 : listaOsoba) {
                Osoba o = (Osoba) domenskiObjekat1;
                System.out.println(o.getIme());


            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/

        }

        if (operacija == util.Util.VRATI_DOMENSKI_OBJEKAT) {

            DomenskiObjekat domObj = KontrolerPL.vratiDomenskiObjekat(domenskiObjekat);
            to.setServerObjekat(domObj);
            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(to);

            
            
        }
        if (operacija == util.Util.VRATI_LISTU_DUGOVANJA) {
        }

       

    }
}
