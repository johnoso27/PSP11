/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos.psp11.modelo;

import edu.uniandes.ecos.psp11.controlador.ControladorTamanoRelativo;
import edu.uniandes.ecos.psp11.vista.VistaConsola;
import java.util.LinkedList;
import junit.framework.TestCase;

/**
 *
 * @author JohnDany
 */
public class CalculadorTamanoRelativoTest extends TestCase {

    public CalculadorTamanoRelativoTest(String testName) {
        super(testName);
    }

    /**
     * Test of calcularTamanoRelativo method, of class CalculadorTamanoRelativo.
     * @throws Exception presentada
     */
    public void testCalcularTamanoRelativo() throws Exception {
        Fuente fuente = new Fuente();
        LinkedList<PuntoDosDimensiones> puntos = null;
        CalculadorTamanoRelativo calculador = new CalculadorTamanoRelativo();

        String rutaArvhivo = ControladorTamanoRelativo.class.getClassLoader().getResource("Test1.txt").getFile();
        puntos = fuente.obtenerPuntosDosDimensionesDeArchivo(rutaArvhivo);

        VistaConsola.mostrarResultado("Puntos de Test 1: " + puntos);
        TamanoRelativo respuesta = calculador.calcularTamanoRelativo(puntos);

        assertNotNull(null, respuesta);
        assertEquals(4.3953, respuesta.getMuyPequeno(), 0.5);
        assertEquals(8.5081, respuesta.getPequeno(), 0.5);
        assertEquals(16.4696, respuesta.getMediano(), 0.5);
        assertEquals(31.8811, respuesta.getGrande(), 0.5);
        assertEquals(61.7137, respuesta.getMuyGrande(), 0.5);
        
        VistaConsola.mostrarResultado("Pruebas unitarias pasadas, los datos calculados de Test 1:\n" + respuesta);
    }

}
