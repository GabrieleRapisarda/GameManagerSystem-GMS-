import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PromozioniTest {

    @Test
    void addNewPromozione() {
        Promozioni p= new Promozioni();
        p.addNewPromozione(1,"abc",10);
        assertNotEquals(0,p.getListaPromozioni().size());
    }

    @Test
    void deletePromozione() {
        Promozioni p= new Promozioni();
        p.addNewPromozione(1,"abc",10);
        p.addNewPromozione(2,"abc",20);
        p.deletePromozione(2);
        assertEquals(1,p.getListaPromozioni().size());
    }

    @Test
    void updatePromozione() {
        Promozioni p= new Promozioni();
        p.addNewPromozione(1,"abc",10);
        p.updatePromozione(1,30);
        assertEquals(30,p.getPercentuale(1));
    }

    @Test
    void caricaPromozioni() {
        Promozioni p= new Promozioni();
        p.caricaPromozioni();
        assertNotEquals(0,p.getListaPromozioni().size());
    }
}