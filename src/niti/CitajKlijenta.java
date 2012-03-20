/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import dbb.KomunikacijaSaBazom;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import kab.op.domen.DomenskiObjekat;
import kab.op.domen.Korisnik;
import kab.op.domen.MesecnoDugovanje;
import kab.op.domen.Osoba;
import kab.op.domen.Paket;
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


        if (operacija == util.Util.DODAJ_DOMENSKI_OBJEKAT) {

            KontrolerPL.dodajDomenskiObjekat(domenskiObjekat);
        }
        if (operacija == util.Util.IZMENI_DOMENSKI_OBJEKAT) {

            KontrolerPL.izmeniDomenskiObjekat(domenskiObjekat);

            if (operacija == util.Util.OBRISI_DOMENSKI_OBJEKAT) {
                KontrolerPL.obrisiDomenskiObjekat(domenskiObjekat);

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

        /*  int operacija = to.getOperacija();
        if(operacija ==util.Util.DODAJ_KORISNIKA)
        {
        Osoba osoba = (Osoba) to.getKlijentObjekat();   
        KontrolerPL.dodajDomenskiObjekat(osoba);
        }
        if(operacija == util.Util.IZMENI_KORISNIKA)
        {
        Osoba osoba = (Osoba) to.getKlijentObjekat();   
        KontrolerPL.izmeniDomenskiObjekat(osoba);
        
        }
        if(operacija == util.Util.OBRISI_KORISNIKA)
        {
        Osoba osoba =  (Osoba) to.getKlijentObjekat(); 
        KontrolerPL.obrisiDomenskiObjekat(osoba);
        
        }
        if(operacija == util.Util.SACUVAJ_PAKET)
        {
        Paket paket = (Paket) to.getKlijentObjekat();
        KontrolerPL.dodajKorisnickiPaket(paket);
        
        }
        if(operacija == util.Util.IZMENI_PAKET)
        {
        Paket paket = (Paket) to.getKlijentObjekat();
        KontrolerPL.promeniKorisnickiPaket(paket);
        
        }
        if(operacija == util.Util.VRATI_LISTU_DUGOVANJA)
        {
        Korisnik korisnik = (Korisnik) to.getKlijentObjekat();
        List<MesecnoDugovanje> listaDugovanja = KontrolerPL.vratiMesecnaDugovanjaKorisnika(korisnik);
        //sta dalje ja radim sa tom listom
        ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
        to.setServerObjekat(listaDugovanja);
        out.writeObject(to);
        
        }
        if(operacija == util.Util.VRATI_KORISNIKE)
        {
        List<Korisnik> listaKorisnika =KontrolerPL.vratiListuKorisnika();
        ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
        to.setServerObjekat(listaKorisnika);
        out.writeObject(to);
        
        
        }
        if(operacija == util.Util.VRATI_LISTU_PAKETA)
        {
        
        List<Paket> listaPaketa = KontrolerPL.vratiListuPaketa();
        ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
        to.setServerObjekat(listaPaketa);
        out.writeObject(to);
        
        }
        
        
        if(operacija == util.Util.IZMIRI_DUGOVANJE)
        {
        MesecnoDugovanje dugovanje = (MesecnoDugovanje) to.getKlijentObjekat();
        KontrolerPL.izmiriDugovanje(dugovanje);
        }
        
         */

    }
}
