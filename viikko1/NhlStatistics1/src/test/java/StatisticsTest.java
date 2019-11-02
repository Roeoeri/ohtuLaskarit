/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author htomi
 */
public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    public StatisticsTest() {
        
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void searchReturnsCorrectPlayer(){
        assertEquals("Gretzky", stats.search("Gretzky").getName());
    }
    
    @Test
    public void searchReturnsNullIfNoPlayerFound(){
        assertEquals(null,stats.search("Vatanen"));
    }
    
    @Test 
    public void returnsCorrectPlayersInTeam(){
        boolean playerIsInCorrectTeam = true;
        for(Player p : stats.team("EDM")){
            if(!p.getTeam().equals("EDM")){
                playerIsInCorrectTeam = false;
            }
        }
        
        assertTrue(playerIsInCorrectTeam);
    }
    
    @Test
    public void returnsCorrectTopScorers(){
        List<Player> top = stats.topScorers(1);
        assertEquals("Gretzky",top.get(0).getName());
    }

  
}
