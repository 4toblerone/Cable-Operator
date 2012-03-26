/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemskeoperacije;

import dbb.KomunikacijaSaBazom;
import kab.op.domen.DomenskiObjekat;

/**
 *
 * @author Sale
 */
public abstract class SOOpstaSO {
    
    static KomunikacijaSaBazom  KSB ;
    static boolean BazaOtvorena = false;
    static int signal;
    
    public int  izvrsiSO(DomenskiObjekat obj) {
        signal = 0;
        
       if( !izvrsiPreduslov(obj)){ 
           System.out.println(signal);
           return signal;}
        izvrsiOperaciju(obj);
        potvrdiIzvrsenjeSO();
        System.err.println(signal);
        return signal;
    }

   protected abstract boolean izvrsiPreduslov(DomenskiObjekat obj) ;

   //ovde ce se setovati signal koji ce kasnije proveravati
   //u potvrdi izvrsenje SO
    protected abstract int izvrsiOperaciju(DomenskiObjekat obj) ;

    protected boolean ponistiIzvrsenjeSO() {
       return true;
    }

    protected boolean potvrdiIzvrsenjeSO() {
        int signal1 ;
        if(stanjeOperacije(signal)){
        signal1 = KomunikacijaSaBazom.vratiInstancuKomunikacijeSaBazom().potvrdiTransakciju();
        
        if(!stanjeOperacijeOpstaSO(signal1)){
                signal = signal1;  
                return false;
            }
        }
        else{
            signal1= KomunikacijaSaBazom.vratiInstancuKomunikacijeSaBazom().ponistiTransakciju();
            
            if(!stanjeOperacijeOpstaSO(signal1)){
                signal = signal1;
                return false;
            }    
        }
        return true;
    }
    
    //ovim cu primorati da klase koje budu nasledjivale ovu
    //implementiraju ovu metodu...
   abstract boolean stanjeOperacije(int signal);
    
    boolean stanjeOperacijeOpstaSO(int signal){
        
        switch(signal){
            case 51 : return true;
            case 52 :  return false;
            case 53 : return true;
            case 54 : return false;
        }
        return false;
                
    }
    
     
    //implementacijom singlotona to je vec odradjeno
    private boolean otvoriBazu(){
     
        return true;
    }
    
    //da li je neophodno?
    private boolean zatvoriBazu(){
        return true;
    }
}
