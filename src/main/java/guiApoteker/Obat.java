/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiApoteker;

import static cerdik.desktop.Account.createID;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gabri
 */
public class Obat {
    private   int Stok;
    private  String Nama, Jenis, saranPenyajian;

    
    public Obat(String nama, String jenis, int stok ){
        setNama(nama);
        setJenis(jenis);
        setStok(stok);
    }

    public  String getSaranPenyajianObat() {
        return saranPenyajian;
    }

    public void setSaranPenyajian(String saranPenyajian) {
        this.saranPenyajian = saranPenyajian;
    }
    
    public  int getStokObat() {
        return Stok;
    }

    public void setStok(int Stok) {
        this.Stok = Stok;
    }

    public String getNamaObat() {
        return Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public String getJenisObat() {
        return Jenis;
    }

    public void setJenis(String Jenis) {
        this.Jenis = Jenis;
    }
    
     public void addObat(DefaultTableModel tableModel) {
        Object[] row = {getNamaObat(), getJenisObat(), getStokObat()};
        tableModel.addRow(row);
    }
     
      public static int createID(){
        int min = 1000, max = 9999;
        return (min + (int)(Math.random() * ((max-min)+1)));
    }
    
      
   

    
}
