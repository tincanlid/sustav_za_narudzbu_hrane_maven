package entities;


/**
 * Predstavlja item u sustavu.
 * Koristi Builder dizajn obrazac za izgradnju objekata itema.
 */
public final class Item {
    private final String naziv;
    private final double cijena;

    private Item(Builder builder){
        if (builder.cijena < 0)
            throw new NegativePriceException("Cijena ne moze biti negativna!");
        this.naziv = builder.ime;
        this.cijena = builder.cijena;
    }

    /**
     * Vraca ime itema.
     *
     * @return Ime itema
     */
    public String getNaziv(){
        return naziv;
    }

    /**
     * Vraca cijenu itema.
     *
     * @return Cijena itema
     */
    public double getCijena(){
        return cijena;
    }

    /**
     * Vraca string reprezentaciju itema.
     *
     * @return String s imenom i cijenom itema.
     */
    @Override
    public String toString(){
        return naziv + " - " + cijena + " eura";
    }

    /**
     * Stvaranje objekata itema pomocu buildera.
     */
    public static class Builder {
        private String ime;
        private double cijena;

        /**
         * Postavlja ime itema.
         *
         * @param ime Ime itema
         * @return Sam builder za lancano pozivanje metoda
         */
        public Builder setIme(String ime){
            this.ime = ime;
            return this;
        }

        /**
         * Postavlja cijenu itema.
         *
         * @param cijena Cijena itema
         * @return Sam builder za lancano pozivanje metoda
         */
        public Builder setCijena(double cijena){
            this.cijena = cijena;
            return this;
        }

        /**
         * Gradi objekat itema.
         *
         * @return Izgradeni objekt itema
         */
        public Item build(){
            return new Item(this);
        }
    }
}
