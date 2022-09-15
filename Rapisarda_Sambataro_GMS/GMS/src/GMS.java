import java.io.*;
import java.util.LinkedList;

public class GMS {

    private static GMS gms;
    private Carrello currentCarrello;
    private Cliente currentCliente;
    private Copia  currentArticolo;
    private RegistroClienti regClienti;
    private Magazzino magazzino;
    private Catalogo catalogo;
    private Promozioni promozioni;

    BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
    private GMS(){
        this.magazzino = new Magazzino();
        this.regClienti = new RegistroClienti();
        this.catalogo = new Catalogo();
        this.promozioni=new Promozioni();
    }

    public static GMS getInstance(){
        if(gms == null)
            gms = new GMS();
        else
            System.out.println("Istanza già creata");

        return gms;
    }

    public void start(){
        magazzino.caricaCopie();
        regClienti.caricaClienti();
        catalogo.caricaCatalogo();
        magazzino.aggiornaHashmap(catalogo.getListaCatalogo());
        catalogo.caricaDisp(magazzino.register());
        promozioni.caricaPromozioni();
    }

    public void creaVendita(){

        String tit;
        String piattaforma;
        int flag=0;


        currentCarrello= new Carrello();
        try{
            currentCliente=visualizzaCliente();
            if(currentCliente.getNome().equals("missing")){
                System.out.println("vuoi registrare il Cliente(inserisci yes per proseguire): ");
                String risp = bf.readLine();
                if(risp.equals("yes")) {
                    currentCliente = registraCliente();
                }
            }
            currentCarrello.setCliente(currentCliente);

            do {
                System.out.println("inserisci il Titolo del prodotto: ");
                tit = bf.readLine();
                System.out.println("inserisci la Piattaforma del prodotto : ");
                piattaforma = bf.readLine();
                currentArticolo = visualizzaMagazzino(tit,piattaforma);
                if (currentArticolo==null) {
                    System.out.println("il cliente vuole prenotare questo articolo?: ");
                    String risposta = bf.readLine();
                    if(risposta.equals("yes")){
                        RigaCatalogo x=visualizzaCatalogo(tit,piattaforma);
                        if(x!=null)
                            System.out.println(prenotaArticolo(x,currentCliente));
                        else{
                            System.out.println("il prodotto non esiste");
                            return;
                        }
                    }
                    return;
                }
                aggiungiArticolo(currentArticolo);
                System.out.println("vuoi continuare?(inserisci 1 per finire): ");
                flag=Integer.parseInt(bf.readLine());
            }while(flag!=1);
            calcolaTotale();
            System.out.println(currentCarrello.getTotale());
            terminaAcquisto();
        }catch(IOException e){
            System.out.println("errore in lettura");
            return;      }

    }

    public Copia visualizzaMagazzino(String t, String p){
        //non usiamo il numero di copia perchè prende il primo disponibile
        return magazzino.getArticolo(t,p);
    }
    public void aggiungiArticolo(Copia c){
        currentCarrello.listaCarrello.add(c);
    }

    public Cliente visualizzaCliente(){
        int flag;
        String cf;
        try{
            System.out.println("il cliente è registrato?(1 si, 0 no): ");
            flag = Integer.parseInt(bf.readLine());
            if(flag!=0){
                System.out.println("inserisci il codice fiscale del del cliente: ");
                cf = bf.readLine();
                for(Cliente c :regClienti.elencoClienti){
                    if(c.getCodiceFiscale().equals(cf))
                        return c;
                }
            }
            return new Cliente("missing","missing","missing","missing","missing","missing");
        }catch (Exception e){
            System.out.println("errore in funzione visualizzaCliente");
            System.exit(-6);
        }
        return null;
    }

    public void calcolaTotale(){
        double count=0;
        for (Copia c : currentCarrello.listaCarrello)
            count+=c.getPrezzo();
        count=associaPromozione(count);
        currentCarrello.setTotale(count);
    }

    public double associaPromozione(double totale){
        int idProm;
        try {
            if(totale>=100){
                double percentuale= promozioni.getPercentuale(1);
                totale= totale -((percentuale * totale)/100);
                return totale;
            }else{
                System.out.println("vuoi associare a questa transazione una promozione?: ");
                String risp =bf.readLine();
                if(risp.equals("yes")) {
                    System.out.println("inserisci l'id della promozione?: ");
                    idProm=Integer.parseInt(bf.readLine());
                    double percentuale= promozioni.getPercentuale(idProm);
                    totale= totale -((percentuale * totale)/100);
                    return totale;
            }else
                return totale;
            }
        } catch (Exception e) {
            System.out.println("Errore nella funzione: associaPromozione");
        }
        return -1;
    }

    public void terminaAcquisto(){
        System.out.println(currentCarrello.stampaRicevuta());
        magazzino.aggiornaMagazzino(currentCarrello.listaCarrello);
    }

    public void notify(int id, int numerocopie){
        catalogo.update(id,numerocopie);
    }

    public void printCatalogo(){
        System.out.println(catalogo.toString());
    }

    public RigaCatalogo visualizzaCatalogo(String titolo, String piattaforma){
        RigaCatalogo res=catalogo.findArticolo(titolo, piattaforma);
        return res;
    }

    public String prenotaArticolo(RigaCatalogo r,Cliente c){
        Prenotazione prenotazione=new Prenotazione(r,c);
        return prenotazione.getRicevuta();
    }

    public Cliente registraCliente(){
        String codiceFiscale;
        String nome;
        String cognome;
        String indirizzo;
        String telefono;
        String mail;
        Cliente c;

        try{
            System.out.println("inserire il del codice fiscale cliente: ");
            codiceFiscale = bf.readLine();
            System.out.println("inserire il cognome del cliente: ");
            cognome = bf.readLine();
            System.out.println("inserire il nome del cliente: ");
            nome = bf.readLine();
            System.out.println("inserire l'indirizzo del cliente: ");
            indirizzo = bf.readLine();
            System.out.println("inserire il telefono del cliente: ");
            telefono = bf.readLine();
            System.out.println("inserire la mail del cliente: ");
            mail = bf.readLine();

            c= new Cliente(codiceFiscale,nome,cognome,indirizzo,telefono,mail);
            regClienti.addNewCliente(c);
            return c;
        }catch (Exception e){
            System.exit(-3);
        }
        return null;
    }

    public void addNewCopia(){

        String titolo;
        String piattaforma;
        int idCopia;

        try {
            System.out.println("inserire il titolo della nuova copia: ");
            titolo = bf.readLine();
            System.out.println("inserire la piattaforma della nuova copia: ");
            piattaforma = bf.readLine();
            System.out.println("inserire il numero della copia: ");
            idCopia = Integer.parseInt(bf.readLine());
            RigaCatalogo r=catalogo.findArticolo(titolo,piattaforma);
            if (r!=null){
                magazzino.addCopia(idCopia,r.getId(),titolo,piattaforma,r.getPrezzo());
            }
        }catch (Exception e){
            System.out.println("Errore nella funzione: addNewCopia");
        }
    }

    public void modificaCopia() {
        String titolo;
        String piattaforma;
        int idCopia;

        try {
            System.out.println("inserire il titolo della copia da modificare: ");
            titolo = bf.readLine();
            System.out.println("inserire la piattaforma della copia da modificare: ");
            piattaforma = bf.readLine();
            System.out.println("inserire il numero della copia da modificare: ");
            idCopia = Integer.parseInt(bf.readLine());
            magazzino.updateCopia(titolo, piattaforma, idCopia);
        } catch (Exception e) {
            System.out.println("Errore nella funzione: modificaCopia");
        }
    }

    public void eliminaCopia(){
        String titolo;
        String piattaforma;
        int idCopia;
        try {
            System.out.println("inserire il titolo della copia da modificare: ");
            titolo = bf.readLine();
            System.out.println("inserire la piattaforma della copia da modificare: ");
            piattaforma = bf.readLine();
            System.out.println("inserire il numero della copia da modificare: ");
            idCopia = Integer.parseInt(bf.readLine());
            magazzino.aggiornaMagazzino2(titolo, piattaforma, idCopia);
        } catch (Exception e) {
            System.out.println("Errore nella funzione: eliminaCopia");
        }
    }

    public void printMagazzino(){
        System.out.println(magazzino.toString());
    }


    public void terminate(){
        promozioni.terminate();
        magazzino.terminate();

    }



    public void inserisciPromozione(){

        int idProm;
        String nomeProm;
        int percentualprom;

        try {
            System.out.println("inserire l'id della nuova promozione: ");
            idProm = Integer.parseInt(bf.readLine());
            System.out.println("inserire il nome della nuova promozione: ");
            nomeProm = bf.readLine();
            System.out.println("inserire la percentuale di sconto della nuova promozione: ");
            percentualprom = Integer.parseInt(bf.readLine());
            promozioni.addNewPromozione(idProm,nomeProm,percentualprom);
        } catch (Exception e) {
            System.out.println("Errore nella funzione: inserisciPromozioni");
        }
    }


    public void modificaPromozione(){
        int idProm;
        int percentualprom;

        try {
            System.out.println("inserire l'id della promozione da modificare: ");
            idProm = Integer.parseInt(bf.readLine());
            System.out.println("inserire la percentuale di sconto della promozione da modificare: ");
            percentualprom = Integer.parseInt(bf.readLine());
            promozioni.updatePromozione(idProm,percentualprom);
        } catch (Exception e) {
            System.out.println("Errore nella funzione: modificaPromozione");
        }
    }

    public void eliminaPromozione(){
        int idProm;

        try {
            System.out.println("inserire l'id della promozione da modificare: ");
            idProm = Integer.parseInt(bf.readLine());
            promozioni.deletePromozione(idProm);
        } catch (Exception e) {
            System.out.println("Errore nella funzione: eliminaPromozione");
        }

    }

    public void printPromozioni(){
        System.out.println(promozioni.toString());
    }
    public Promozioni getPromozioni(){
        return promozioni;
    }

    public Carrello getCurrentCarrello() {
        return currentCarrello;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public Magazzino getMagazzino() {
        return magazzino;
    }
}
