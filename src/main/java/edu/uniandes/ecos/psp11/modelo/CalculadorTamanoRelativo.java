package edu.uniandes.ecos.psp11.modelo;

import java.util.LinkedList;

/**
 * Clase que ejecuta los cálculos de tamaños relativos de programas.
 *
 * @author JohnDany
 */
public class CalculadorTamanoRelativo {

    /**
     * Lista de líneas de código por método
     */
    private LinkedList<Double> locXMetodo;

    /**
     * Sumatoriia de las líneas de código por método.
     */
    private Double sumatoriaLnLocXMetodo;

    /**
     * Lista de logaritmos naturales de los items de locXMetodo al cuadrado
     */
    private LinkedList<Double> lnsXiLocXMetodo;

    /**
     * Sumatoria de los itemas de lnsXiLocXMetodo
     */
    private Double sumatoriaLnsXiLocXMetodo;

    /**
     * primedio de los itemas de lnsXiLocXMetodo
     */
    private Double promedioLnXiLocXMetodo;

    /**
     * varianza
     */
    private Double varianzaLnXiLocXMetodo;

    /**
     * sumatoria de los items de lnsXiLocXMetodo memos el promedio, al cuadrado.
     */
    private Double sumatoriaLnLocXMmenosPromedioLnXiLocXMetodo;

    /**
     * Derivacién estandar
     */
    private Double derivacionStandar;

    /**
     * Método que calcula el tamanó relativo de un conjunto de datos de dos
     * tipos: Cantidad de lineas de código verson la cantidad de métodos.
     *
     * @param datos
     * @return
     * @throws Exception
     */
    public TamanoRelativo calcularTamanoRelativo(LinkedList<PuntoDosDimensiones> datos) throws Exception {
        TamanoRelativo tamanoRelativo = null;
        if (datos == null || datos.size() <= 0) {
            throw new Exception("No se puede calcular la regreción y correlación porque la lista de datos no tiene datos.");
        }

        this.locXMetodo = new LinkedList<Double>();
        this.lnsXiLocXMetodo = new LinkedList<Double>();
        this.sumatoriaLnLocXMetodo = 0.0;
        this.sumatoriaLnsXiLocXMetodo = 0.0;
        this.varianzaLnXiLocXMetodo = 0.0;
        this.derivacionStandar = 0.0;
        this.sumatoriaLnLocXMmenosPromedioLnXiLocXMetodo = 0.0;

        for (PuntoDosDimensiones dato : datos) {
            double x = dato.x / dato.y;
            this.locXMetodo.add(x);
            this.sumatoriaLnLocXMetodo = this.sumatoriaLnLocXMetodo + x;
            double ln = Math.log(x);
            this.lnsXiLocXMetodo.add(ln);
            this.sumatoriaLnsXiLocXMetodo = this.sumatoriaLnsXiLocXMetodo + ln;
        }

        this.promedioLnXiLocXMetodo = this.sumatoriaLnsXiLocXMetodo / datos.size();

        for (Double lnx : lnsXiLocXMetodo) {
            this.sumatoriaLnLocXMmenosPromedioLnXiLocXMetodo = this.sumatoriaLnLocXMmenosPromedioLnXiLocXMetodo + Math.pow((lnx - this.promedioLnXiLocXMetodo), 2);
        }

        this.varianzaLnXiLocXMetodo = (this.sumatoriaLnLocXMmenosPromedioLnXiLocXMetodo) / (datos.size() - 1);
        this.derivacionStandar = Math.sqrt(this.varianzaLnXiLocXMetodo);

        tamanoRelativo = new TamanoRelativo();
        tamanoRelativo.setMuyPequeno(Math.pow(Math.E, (this.promedioLnXiLocXMetodo - (2 * this.derivacionStandar))));
        tamanoRelativo.setPequeno(Math.pow(Math.E, (this.promedioLnXiLocXMetodo - this.derivacionStandar)));
        tamanoRelativo.setMediano(Math.pow(Math.E, (this.promedioLnXiLocXMetodo)));
        tamanoRelativo.setGrande(Math.pow(Math.E, (this.promedioLnXiLocXMetodo + this.derivacionStandar)));
        tamanoRelativo.setMuyGrande(Math.pow(Math.E, (this.promedioLnXiLocXMetodo + (2 * this.derivacionStandar))));

        return tamanoRelativo;
    }
}
