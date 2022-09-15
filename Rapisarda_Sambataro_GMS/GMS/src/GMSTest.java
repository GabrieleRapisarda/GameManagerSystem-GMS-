import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GMSTest {

    @Test
    void associaPromozione() {
        GMS gms= GMS.getInstance();
        Promozioni listap=gms.getPromozioni();
        listap.addNewPromozione(1,"abc",10);
        double totale=100.0;
        assertEquals(90.0,gms.associaPromozione(totale));
    }


}