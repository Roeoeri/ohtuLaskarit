/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import static org.mockito.Mockito.*;

/**
 *
 * @author htomi
 */
public class KauppaTest {

    Pankki pankki;
    Kauppa k;
    Viitegeneraattori viite;
    Varasto varasto;
    final String kaupantili = "33333-44455";

    final int maidonHinta = 5;
    final int piimanHinta = 4;
    final int colanHinta = 3;

    public KauppaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);

        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(12);
        when(varasto.saldo(3)).thenReturn(0);

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", maidonHinta));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "piimä", piimanHinta));
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "cola", colanHinta));
        k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();

    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {

        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreillä() {

        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", kaupantili, maidonHinta);

    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilläKunKorissaKaksiTuotettaJoitaVarastossa() {

        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", kaupantili, maidonHinta + piimanHinta);

    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilläKunKorissaKaksiSamaaTuotettaJoitaVarastossa() {

        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", kaupantili, 2 * maidonHinta);

    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilläKunKorissaKaksiTuotettaJoistaToistaEiVarastossa() {

        k.lisaaKoriin(1);
        k.lisaaKoriin(3);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", kaupantili, maidonHinta);

    }

    @Test
    public void aloitaOstoksetNollaaEdellisenOstoksenTiedot() {
        k.lisaaKoriin(2);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", kaupantili, maidonHinta);

    }

    @Test
    public void uusiViitePyydetaanJokaiselleTapahtumalle() {
        for (int i = 1; i < 4; i++) {
            k.aloitaAsiointi();
            k.lisaaKoriin(1);
            k.tilimaksu("pekka", "12345");
            verify(viite, times(i)).uusi();

        }
    }
    
    @Test
    public void poistettuTuoteEiNäyKorissa(){
        
        k.lisaaKoriin(2);
        k.poistaKorista(2);
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", kaupantili, maidonHinta);
        
    }
}
