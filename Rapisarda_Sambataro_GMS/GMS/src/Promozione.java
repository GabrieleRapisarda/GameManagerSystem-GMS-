public class Promozione {

    private int idPromozione;
    private String nomePromozione;
    private int percentualePromozione;

    public Promozione(int id, String nome, int perc){
        idPromozione=id;
        nomePromozione=nome;
        percentualePromozione=perc;
    }

    public Promozione() {
    }

    public int getIdPromozione() {
        return idPromozione;
    }

    public void setPercentualePromozione(int percentualePromozione) {
        this.percentualePromozione = percentualePromozione;
    }

    public String toString() {
        String result;

        result = ("idPromozione:" + idPromozione + "\n"
                + "nome promozione:" + nomePromozione + "\n"
                + "sconto:" + percentualePromozione +"%"+ "\n");
        return result;
    }
    public String toString2() {
        String result;

        result = (idPromozione + "\n"
                +  nomePromozione + "\n"
                +percentualePromozione +"\n");
        return result;
    }

    public int getPercentualePromozione() {
        return percentualePromozione;
    }
}
