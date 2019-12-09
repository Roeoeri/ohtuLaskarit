/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import statistics.Player;

/**
 *
 * @author htomi
 */
public class Not implements Matcher{
    
    private  Matcher matcher;
    
    public Not(Matcher m){
        this.matcher = m;
    }
    
    
    @Override
    public boolean matches(Player p){
        if(this.matcher.matches(p)){
            return false;
        }
        return true;
    }
    
}
