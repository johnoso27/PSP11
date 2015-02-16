package edu.uniandes.ecos.psp11.modelo;

/**
 * Clase que representa a un objeto Tama�o relativo.
 *
 * @author JohnDany
 */
public class TamanoRelativo {

    /**
     * Rango para el tama�o muy peque�o
     */
    private double muyPequeno;

    /**
     * Rango para el tama�o peque�o.
     */
    private double pequeno;

    /**
     * Rango para el tama�o mediano.
     */
    private double mediano;

    /**
     * Rango para el tama�o grande.
     */
    private double grande;

    /**
     * Rango para el tama�o muy grande.
     */
    private double muyGrande;

    /**
     * @return the muyPequeno
     */
    public double getMuyPequeno() {
        return muyPequeno;
    }

    /**
     * @param muyPequeno the muyPequeno to set
     */
    public void setMuyPequeno(double muyPequeno) {
        this.muyPequeno = muyPequeno;
    }

    /**
     * @return the pequeno
     */
    public double getPequeno() {
        return pequeno;
    }

    /**
     * @param pequeno the pequeno to set
     */
    public void setPequeno(double pequeno) {
        this.pequeno = pequeno;
    }

    /**
     * @return the mediano
     */
    public double getMediano() {
        return mediano;
    }

    /**
     * @param mediano the mediano to set
     */
    public void setMediano(double mediano) {
        this.mediano = mediano;
    }

    /**
     * @return the grande
     */
    public double getGrande() {
        return grande;
    }

    /**
     * @param grande the grande to set
     */
    public void setGrande(double grande) {
        this.grande = grande;
    }

    /**
     * @return the muyGrande
     */
    public double getMuyGrande() {
        return muyGrande;
    }

    /**
     * @param muyGrande the muyGrande to set
     */
    public void setMuyGrande(double muyGrande) {
        this.muyGrande = muyGrande;
    }

    /**
     * M�todo que sobrescribe el m�todo toString
     * @return el valor del objeto en una cadena.
     */
    @Override
    public String toString() {
        return "Tama�o Relativo: \n" + 
                "Muy Pequeno (VS)=" + muyPequeno + 
                "\nPequeno (S)=" + pequeno + 
                "\nMediano(M)=" + mediano + 
                "\nGrande (L)=" + grande + 
                "\nMuy Grande(VL)=" + muyGrande;
    }

}
