/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbb;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import kab.op.domen.DomenskiObjekat;

/**
 *
 * @author Sale
 */
public class KomunikacijaSaBazom {

    private static KomunikacijaSaBazom instanca;
    Connection konekcija;
    static public final String driver = "com.mysql.jdbc.Driver";

    private KomunikacijaSaBazom() /*throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException*/ {

        try {
            String url = "jdbc:mysql://localhost:3306/KablovskiProvajder";
            String username = "root";
            String pass = "admin";


            DriverManager.registerDriver((Driver) Class.forName(driver).newInstance());

            konekcija = DriverManager.getConnection(url, username, pass); //ne zaboravi da se vratis na ovo i popunis kako treba

            konekcija.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static KomunikacijaSaBazom vratiInstancuKomunikacijeSaBazom() {
        try {
            if (instanca == null) {
                instanca = new KomunikacijaSaBazom();
            }

        } catch (Exception e) {
        }
        return instanca;
    }

    public int potvrdiTransakciju() {

        try {
            konekcija.commit();
        } catch (SQLException esql) {
            System.err.println("Nije uspesno odradjen commit: " + esql);
            return 52;//pozabavi se brojevima!!!
        }
        return 51;
    }

    public int ponistiTransakciju() {
        try {
            konekcija.rollback();
        } catch (SQLException esql) {
            System.err.println("Nije uspesno odradjen rollback: " + esql);
            return 54;
        }
        return 53;
    }

    public int sacuvaj(DomenskiObjekat obj) {
        try {

            String upit = "INSERT INTO " + obj.vratiNazivTabele() + " VALUES(" + obj.vratiVrednostiZaInsert() + ")";
            Statement dbIzraz = konekcija.createStatement();
            dbIzraz.executeUpdate(upit);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 7; 

        }
        return 6; 
    }

    public int azuriraj(DomenskiObjekat obj) {
        try {
            String upit = "UPDATE " + obj.vratiNazivTabele() + " SET " + obj.vratiUslovZaUpdate() + " WHERE " + obj.vratiUslovPK() + ";";
            Statement dbIzmena = konekcija.createStatement();
            dbIzmena.executeUpdate(upit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 11;
        }
        return 10;
    }

    public int obrisi(DomenskiObjekat obj) {
        try {
            String upit = "DELETE FROM " + obj.vratiNazivTabele() + " WHERE" + obj.vratiUslovPK()+";";
            Statement dbIzraz = konekcija.createStatement();
            dbIzraz.executeUpdate(upit);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 9;
        }

        return 8;
    }

    public boolean daLiPostoji(DomenskiObjekat obj) {
        try {
            String upit = "SELECT * FROM " + obj.vratiNazivTabele() + " WHERE " + obj.vratiUslovPK();
            Statement dbIzraz = konekcija.createStatement();
            ResultSet rs = dbIzraz.executeQuery(upit);

            return rs.next() ? true : false;
            //    return obj.vratiObjekatPK(rs)!=null ? true : false;
        } catch (Exception e) {
            System.err.println("Doslo je do greske u da li postoji : " + e.getMessage());

            return false;
        }


    }

    public DomenskiObjekat vratiDomenskiObjekat(DomenskiObjekat obj) throws Exception {
        try {
            String upit = "SELECT * FROM " + obj.vratiNazivTabele() + " WHERE " + obj.vratiUslovPK();

            Statement dbIzraz = konekcija.createStatement();
            ResultSet rs = dbIzraz.executeQuery(upit);
            return obj.vratiObjekatPK(rs);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public <T extends DomenskiObjekat> List<T> vratiListuDomenskihObjekata(T obj)  {
      
        try {
            System.out.println("Komunikacija sa bazom ....");
            String upit = "SELECT * FROM " + obj.vratiNazivTabele();
            Statement dbIzraz = konekcija.createStatement();
            ResultSet rs = dbIzraz.executeQuery(upit);
            return obj.vratiSveDomeneskeObjekte(rs);
        } catch (Exception e) {
            System.out.println("upao u catrch");
             return null;
        }
      
    }
}
