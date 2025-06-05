/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiApoteker;

/**
 *
 * @author gabri
 */
public class Jadwal {
    private int IDJadwal,IDPasien,IDObat;
    private String Gejala, Dosis, Start_Date, End_Date,namaPasien, namaObat;
    private boolean IsConfirmedNakes, IsConfirmedApoteker;

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    
    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }
    
    public Jadwal(int IDPasien,int IDObat, String Start_Date, String End_Date,int statusNakes, int statusApoteker ){
        setIDPasien(IDPasien);
        setIDObat(IDObat);
        setStart_Date(Start_Date);
        setEnd_Date(End_Date);
        if (statusNakes ==1){
                  this.setIsConfirmedNakes(true);
                }else{
                    this.setIsConfirmedNakes(false);
                }
                if (statusApoteker == 1){
                    this.setIsConfirmedApoteker(true);
                }else{
                    this.setIsConfirmedApoteker(false);
                }
    }
    
    public int getIDJadwal() {
        return IDJadwal;
    }

    public void setIDJadwal(int IDJadwal) {
        this.IDJadwal = IDJadwal;
    }

    public int getIDPasien() {
        return IDPasien;
    }

    public void setIDPasien(int IDPasien) {
        this.IDPasien = IDPasien;
    }

    public int getIDObat() {
        return IDObat;
    }

    public void setIDObat(int IDObat) {
        this.IDObat = IDObat;
    }

    public String getStart_Date() {
        return Start_Date;
    }

    public void setStart_Date(String Start_Date) {
        this.Start_Date = Start_Date;
    }

    public String getEnd_Date() {
        return End_Date;
    }

    public void setEnd_Date(String End_Date) {
        this.End_Date = End_Date;
    }

    public boolean getIsConfirmedNakes() {
        return IsConfirmedNakes;
    }

    public void setIsConfirmedNakes(boolean IsConfirmedNakes) {
        this.IsConfirmedNakes = IsConfirmedNakes;
    }

    public boolean getIsConfirmedApoteker() {
        return IsConfirmedApoteker;
    }

    public void setIsConfirmedApoteker(boolean IsConfirmedApoteker) {
        this.IsConfirmedApoteker = IsConfirmedApoteker;
    }

    public String getGejala() {
        return Gejala;
    }

    public void setGejala(String Gejala) {
        this.Gejala = Gejala;
    }

    public String getDosis() {
        return Dosis;
    }

    public void setDosis(String Dosis) {
        this.Dosis = Dosis;
    }
    
    
}
