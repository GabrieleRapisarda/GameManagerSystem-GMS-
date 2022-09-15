import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatalogoTest {

    @Test
    void caricaCatalogoTest() {
        Catalogo c=new Catalogo();
        c.caricaCatalogo();
        //ci aspettiamo che carichi i 4 prodotti presenti nel file catalogo.txt
        assertNotEquals(0,c.getListaCatalogo().size());
    }

    @Test
    void caricaDisp() {
        Catalogo c=new Catalogo();
        c.caricaCatalogo();
        Magazzino m= new Magazzino();
        m.caricaCopie();
        m.aggiornaHashmap(c.getListaCatalogo());
        c.caricaDisp(m.register());
        RigaCatalogo Fifa=c.findArticolo("FIFA23","PS5");
        assertEquals(true,Fifa.isDisponibilità());

    }

    @Test
    void update() {
        Catalogo c=new Catalogo();
        RigaCatalogo r= new RigaCatalogo(666,"NBA23","PS5",79.99);
        c.getListaCatalogo().add(r);
        //ho settato il numero di copie passate come parametro ad un numero positivo mi aspetto
        //di trovare disponobilità settata a true
        c.update(666,1);
        assertEquals(true,r.isDisponibilità());
        //ho settato il numero di copie passate come parametro a 0 mi aspetto
        //di trovare disponobilità settata a false
        c.update(666,0);
        assertEquals(false,r.isDisponibilità());

    }

    @Test
    void findArticolo() {
        Catalogo c=new Catalogo();
        RigaCatalogo r= new RigaCatalogo(666,"NBA23","PS5",79.99);
        c.getListaCatalogo().add(r);
        assertNotNull(c.findArticolo("NBA23","PS5"));
    }
}