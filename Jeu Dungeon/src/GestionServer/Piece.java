/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionServer;

import GestionPersonnage.Personnage;
import java.util.ArrayList;

/**
 *
 * @author root
 */
public class Piece {
     private int numeroPiece;
     private ArrayList<String>nomPorte;
     private ArrayList<Personnage>personneDansLapiece;
     
    /*
    *nom de la porte qui peut être NORD ou SUD ou ESTou OUEST.
    */
    private String porte[]={"NORD","SUD","EST","OUEST"};
    
    public Piece(Integer numeroPiece) {
        
        this.numeroPiece = numeroPiece;
        this.nomPorte=new ArrayList<>();
        personneDansLapiece=new ArrayList<>();
        creerUnePiece(numeroPiece);
               
    }
    
    
       /**
    * 
    * @param numeroPiece 
    * On initialise les portes de chaque pièce en fonction du numero de pièce
    * le labyrinthe est composé de 9 pièces 3*3
    */
    public void creerUnePiece(Integer numeroPiece)
    {
        
        if(numeroPiece==1 || numeroPiece==2)
        {
          for(int i=1;i<porte.length;i++)
          {
              
              nomPorte.add(porte[i]);
          
          }
             
        }
        
        else if(numeroPiece==4 || numeroPiece==7)
        {
              for(int i=0;i<porte.length-1;i++)
              nomPorte.add(porte[i]);
            
        }
          else if(numeroPiece==5 || numeroPiece==8)
        {
            for(int i=0;i<porte.length;i++)
              nomPorte.add(porte[i]);
        }
        else 
        {
          
              
              nomPorte.add(porte[3]);
              nomPorte.add(porte[1]);
           
        }
    }
    
}
