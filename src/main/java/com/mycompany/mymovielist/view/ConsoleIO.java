/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.view;
import java.util.Scanner;
import java.io.Console;
import javax.swing.*;




/**
 *
 * @author kiran
 */
public class ConsoleIO {
    
    private final Scanner scanner;
    
    public ConsoleIO(){
        this.scanner = new Scanner(System.in);
    }
    
    public void displayMessage(String message){
        System.out.println(message); 
    }
    
    public int readInt(String prompt){
        displayMessage(prompt);
        int num = scanner.nextInt();
        scanner.nextLine(); 
        return num;
    }
    
    public String readString(String prompt) {
        displayMessage(prompt);
        return scanner.nextLine();
    }
    
    public long readLong(String prompt) {
        displayMessage(prompt);
        long num = scanner.nextLong();
        scanner.nextLine();
        return num;
    }
    
    public String readPassword(String prompt){
        JPasswordField passwordField = new JPasswordField();
        int option = JOptionPane.showConfirmDialog(null, passwordField, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String password = new String(passwordField.getPassword());
            System.out.println("Password Entered: " + "*".repeat(password.length())); // Masked output
            return password;
        } else {
            System.out.println("Login Canceled");
            return null;
        }
    }
    
}
