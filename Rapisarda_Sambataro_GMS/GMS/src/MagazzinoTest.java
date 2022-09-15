import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MagazzinoTest {

    @Test
    void caricaCopie() {
        Magazzino m=new Magazzino();
        m.caricaCopie();
        //ci aspettiamo che carichi i 4 prodotti presenti nel file catalogo.txt
        assertNotEquals(0,m.listaCopie.size());
    }

    @Test
    void addCopia() {
        GMS gms=GMS.getInstance();
        gms.start();
        Magazzino m=gms.getMagazzino();
        int prec=m.listaCopie.size();
        //inserisco una copia il cui id e' presente nell'hashmap
        m.addCopia(222,777,"FIFA23","PS5",79.99);
        assertNotEquals(prec,m.listaCopie.size());
    }

    @Test
    void getArticolo() {
        GMS gms=GMS.getInstance();
        gms.start();
        Magazzino m=gms.getMagazzino();
        int prec=m.listaCopie.size();
        System.out.println(m);
        //inserisco una copia il cui id e' presente nell'hashmap
        m.addCopia(222,777,"FIFA23","PS5",79.99);
        assertNotNull(m.getArticolo("FIFA23","PS5"));
    }


    @Test
    void aggiornaHashmap() {
        GMS gms=GMS.getInstance();
        gms.start();
        Magazzino m= gms.getMagazzino();
        Catalogo c=gms.getCatalogo();
        m.aggiornaHashmap(c.getListaCatalogo());
        //FIFA23 e' presente in magazzino quindi la disponibilità è maggiore di zero
        assertNotEquals(0,m.listaDisp.get(333));
    }


}