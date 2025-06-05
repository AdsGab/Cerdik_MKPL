/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cerdik.desktop;



/**
 *
 * @author KnightlyTech
 */

public abstract class Account {
    private String Username;
    private String Password;
    private String Email;
    private String ID;
    private String Role;
    
    public Account(){
        
    }
    
    public Account(String user, String ID){
        this.Username = user;
        this.ID = ID;
    }
    
    public abstract String Login(String Username, String Password);
    //abstract method karena implementasi semua jenis role akan sama

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }
    
    //ID 5 Digit Random
    public static int createID(){
        int min = 10000, max = 99999;
        return (min + (int)(Math.random() * ((max-min)+1)));
    }
    
    
}
