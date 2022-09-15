import java.io.*;

public class Main {

    //private GameManagerStore gms;



    public static int menu(BufferedReader tastiera){
        try{
            System.out.println("***MENU***\n"                +
                    "1)crea una nuova vendita\n"             +
                    "2)aggiungi nuova copia al Magazzino\n"  +
                    "3)Modifica Copia\n"                     +
                    "4)elimina copia\n"                      +
                    "5)visualizza Magazzino\n"               +
                    "6)visualizza Catalogo\n"                +
                    "7)inserisci promozione\n"               +
                    "8)modifica promozione\n"                +
                    "9)elimina promozione\n"                 +
                    "10)visualizza promozioni\n"             +
                    "11)esci\n"                              +
                    "scegli---->:"                         );

            return (Integer.parseInt(tastiera.readLine()));
        }catch(IOException e){
            System.out.println("errore nella funzione menu");
            System.exit(-5);
        }
        return-1;
    }


    public static void main(String[] args) {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        GMS gms = GMS.getInstance();
        gms.start();
        Carrello c= new Carrello();
        while(true)
            switch(menu(bf)){
                case 1:
                    gms.creaVendita();
                    break;
                case 2:
                    gms.addNewCopia();
                    break;
                case 3:
                    gms.modificaCopia();
                case 4:
                    gms.eliminaCopia();
                case 5:
                    gms.printMagazzino();
                    break;
                case 6:
                    gms.printCatalogo();
                    break;
                case 7:
                    gms.inserisciPromozione();
                    break;
                case 8:
                    gms.modificaPromozione();
                    break;
                case 9:
                    gms.eliminaPromozione();
                    break;
                case 10:
                    gms.printPromozioni();
                    break;
                case 11:
                    gms.terminate();
                    System.exit(-2);
                default:
                    System.out.println("inserisci un numero valido\n");
            }
    }




}