package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        lukujono = new int[KAPASITEETTI];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        lukujono = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti ei voi olla negatiivinen");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Joukun kasvatuskoko ei voi olla negatiivinen");
        }
        lukujono = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }

    public boolean lisaa(int luku) {

        if (!kuuluu(luku)) {
            lukujono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm == lukujono.length) {
                kasvataTaulukkoa();
            }
            return true;
        }
        return false;
    }

    public void kasvataTaulukkoa() {
        int[] kasvatettuTaulukko = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(lukujono, kasvatettuTaulukko);
        lukujono = kasvatettuTaulukko;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        if (!kuuluu(luku)) {
            return false;
        }

        for (int j = luvunIndeksi(luku); j < alkioidenLkm - 1; j++) {

            lukujono[j] = lukujono[j + 1];

        }
        lukujono[alkioidenLkm] = 0;
        alkioidenLkm--;
        return true;

    }

    public int luvunIndeksi(int luku) {
        int indeksi = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return i;
            }
        }
        return indeksi;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {

        String tekstimuoto = "";
        for (int i = 0; i < alkioidenLkm; i++) {
            tekstimuoto += lukujono[i];
            if (i != alkioidenLkm - 1) {
                tekstimuoto += ", ";
            }
        }

        return "{" + tekstimuoto + "}";

    }

    public int[] toIntArray() {
        int[] taulukko = new int[alkioidenLkm];
        for (int i = 0; i < taulukko.length; i++) {
            taulukko[i] = lukujono[i];
        }
        return taulukko;
    }
    
    public static void lisaaKokoJoukko(IntJoukko x, IntJoukko b){
        int[] asArray = b.toIntArray();
        for(int i = 0; i<asArray.length; i++){
            x.lisaa(asArray[i]);
        }
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
    
        lisaaKokoJoukko(x,a);
        lisaaKokoJoukko(x,b);
      
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko tulos = new IntJoukko();
        int[] aTaulukko = a.toIntArray();
        int[] bTaulukko = b.toIntArray();
        for (int i = 0; i < aTaulukko.length; i++) {
            for (int j = 0; j < bTaulukko.length; j++) {
                if (aTaulukko[i] == bTaulukko[j]) {
                    tulos.lisaa(bTaulukko[j]);
                }
            }
        }
        return tulos;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko tulos = new IntJoukko();
        int[] bTaulukko = b.toIntArray();
        
        lisaaKokoJoukko(tulos,a);
        
        for (int i = 0; i < bTaulukko.length; i++) {
            tulos.poista(bTaulukko[i]);
        }

        return tulos;
    }

}
