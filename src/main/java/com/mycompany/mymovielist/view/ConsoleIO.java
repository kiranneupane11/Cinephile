/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mymovielist.view;
import java.util.Scanner;


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
    
}
