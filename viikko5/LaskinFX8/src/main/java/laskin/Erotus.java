/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author htomi
 */
public class Erotus extends Komento{
    
    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus){
        super(tuloskentta,syotekentta,nollaa,undo,sovellus);
    }
    
    @Override
    public void suorita(){
        lue();
        haeEdellinen();
        sovellus.miinus(arvo);
        naytaTulos(sovellus.tulos());
    }
    
}