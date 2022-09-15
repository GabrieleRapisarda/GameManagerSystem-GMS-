public class Cliente {

    private String codiceFiscale;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String telefono;
    private String mail;

    public Cliente(String cf, String nom ,String cogn,String ind, String tel, String mail){
        this.codiceFiscale=cf;
        nome=nom;
        cognome=cogn;
        indirizzo=ind;
        telefono=tel;
        this.mail=mail;
    }

    public String toString() {
        String result;

        result = ("cf:" + codiceFiscale + "\n"
                + "nome:" + nome + "\n"
                + "cognome:" + cognome + "\n"
                + "indirizzo:" + indirizzo  + "\n"
                + "mail:" + mail  + "\n"
                + "telefono:" + telefono  + "\n");
        return result;
    }
    public String toString2() {
        String result;

        result = (codiceFiscale + "\n"
                  + nome + "\n"
                  + cognome + "\n"
                  + indirizzo  + "\n"
                  + mail  + "\n"
                  + telefono  + "\n");
        return result;
    }

    public String getNome(){
        return nome;
    }

    public String getCognome(){
        return cognome;
    }
    public String getIndirizzo(){
        return indirizzo;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }
}
