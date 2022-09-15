import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistroClientiTest {

    @Test
    void caricaClienti() {
        RegistroClienti r= new RegistroClienti();
        r.caricaClienti();
        assertNotNull(r.elencoClienti.size());
    }

    @Test
    void addNewCliente() {
        RegistroClienti r= new RegistroClienti();
        r.addNewCliente(new Cliente("123","abc","abc","abc","123","abc"));
        assertNotNull(r.elencoClienti.size());
    }
}