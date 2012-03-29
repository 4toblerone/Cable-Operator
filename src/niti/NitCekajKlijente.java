/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author Sale
 */
public class NitCekajKlijente extends Thread {
    
    Socket soket;

    @Override
    public void run() {
        try{
        ServerSocket ss = new ServerSocket(9321);
        
        while(true)
        {
            soket = ss.accept();
            //procitaj sifru i ime i vidi da li se poklapaju...
           // System.out.println(" radi 2");
            CitajKlijenta ck = new CitajKlijenta(soket);
            ck.start();
           /* ObjectInputStream inSoket = new ObjectInputStream(soket.getInputStream());
            Object obj = inSoket.readObject();
            TransferObjekat to = (TransferObjekat) obj;*/
            
            
          /*  List<String> listaPodataka = (List<String>) to.getKlijentObjekat();
            if(ispravnostSifre(listaPodataka) == true)
            {
             CitajKlijenta ck = new CitajKlijenta(soket);   
             ck.start();
                System.out.println("uspesno su se povezali");
                //vrati klijentu poruku o tome...
                //otvori novu nit za njega
            
            }
            else{
            
           //posalji poruku o neispravnosti klijentu i nastavi da vrtis petlju
           //i zatvori soket?
                
            }
            */
           
        }
        
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private boolean ispravnostSifre(List<String> listaPodataka) {
     
        if("proba".equals(listaPodataka.get(0))){
            
        
        return true;
        }
        else return false;
    }
    
    
    
}
