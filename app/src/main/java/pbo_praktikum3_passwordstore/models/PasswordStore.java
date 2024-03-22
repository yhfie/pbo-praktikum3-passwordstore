/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo_praktikum3_passwordstore.models;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class PasswordStore {
    public String name, username;
    private String password, hashkey;
    private double score;
    private int category;
            
    public static final int UNCATEGORIZED = 0;
    public static final int CAT_WEBAPP = 1;
    public static final int CAT_MOBILEAPP = 2;
    public static final int CAT_OTHER = 3;
    
    
    public PasswordStore(String name, String username, String plainPass)
    {
        this.name = name;
        this.username = username;
        try {
            this.hashkey = Encryptor.generateKey();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PasswordStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        setPassword(plainPass);
        setCategory(UNCATEGORIZED);
    }
        
    public PasswordStore(String name, String username, String plainPass, int category)
    {
        this.name = name;
        this.username = username;
        try {
            this.hashkey = Encryptor.generateKey();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PasswordStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        setPassword(plainPass);
        setCategory(category);
    }
    
    public void setPassword(String plainPass)
    {
        try {
            this.password = Encryptor.encrypt(plainPass, this.hashkey);
        } catch (Exception ex) {
            Logger.getLogger(PasswordStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        calculateScore(plainPass);
    }
    
    public String getPassword()
    {
        String decrypted = null;
        try {
            decrypted = Encryptor.decrypt(password, hashkey);
        } catch (Exception ex) {
            Logger.getLogger(PasswordStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return decrypted;
    }
    
    public void setCategory(int category)
    {
        if( (category == 1) || (category == 2) || (category == 3) )
        {
            this.category = category;
        }
        else
        {
            this.category = UNCATEGORIZED;
        }
    }
    
    public String getCategory()
    {
        switch(category){
            default -> {
                return "Belum terkategori";
            }
            case 1 -> {
                return "Aplikasi web";
            }
            case 2 -> {
                return "Aplikasi mobile";
            }
            case 3 -> {
                return"Akun lainnya";
            }
        }
    }
    
    private void calculateScore(String plainPass)
    {
        double len = plainPass.length();
        if( len > 15 ){
            this.score = 10;
        }
        else{
            this.score = (len/15) * 10;
        }
    }
    
    @Override
    public String toString()
    {
        return "Username: " + username + "\nPassword: " + password + "\nHashkey: " + hashkey + "\nKategori: " + getCategory() + "\nScore: " + Math.round(score);
    }
}
