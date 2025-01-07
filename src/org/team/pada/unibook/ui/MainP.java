/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.team.pada.unibook.ui;

import java.util.Scanner;
import org.team.pada.unibook.service.Services;

/**
 * Unibook app.
 * 
 * @version 1.0
 * @author Apostolos Alexiadis, Maria Toutoudaki, Moschanthi Theodorou
 */
public class MainP {

    /**
     * Main method for the UniBook application.
     * Creates a Scanner object for user input and a Services object to handle the application logic.
     * Loads the main menu for user interaction.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in); // Δημιουργία αντικειμένου Scanner για είσοδο χρήστη
        Services service = new Services(); // Δημιουργία αντικειμένου Services για την λογική της εφαρμογής
        service.loadMenu(scanner); // Κλήση της μεθόδου loadMenu για να φορτώσει το κύριο μενού της εφαρμογής
        
    }

    
     

            

    
    
    
    
}
                

