package edu.uniandes.ecos.psp11.controlador;

import edu.uniandes.ecos.psp11.modelo.CalculadorTamanoRelativo;
import edu.uniandes.ecos.psp11.modelo.Fuente;
import edu.uniandes.ecos.psp11.modelo.PuntoDosDimensiones;
import edu.uniandes.ecos.psp11.modelo.TamanoRelativo;
import edu.uniandes.ecos.psp11.vista.VistaConsola;
import edu.uniandes.ecos.psp11.vista.VistaWeb;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Hello world!
 *
 */
public class ControladorTamanoRelativo extends HttpServlet {

    public static void main(String[] args) {
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        //Server server = new Server(5100);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new ControladorTamanoRelativo()), "/*");
        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(ControladorTamanoRelativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        VistaWeb.mostrarFormunlarioInicial(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("btnTest") != null) {
                calculoArchivosTest(req, resp);
            } else if (req.getParameter("btnEntrada") != null) {
                VistaWeb.mostrarVentanaEntrada(req, resp);
            } else if (req.getParameter("btnCalcularEntrada") != null) {
                calculoArchivoEntrada(req, resp);
            }
        } catch (Exception ex) {
            Logger.getLogger(ControladorTamanoRelativo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void calculoArchivosTest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Fuente fuente = new Fuente();
            LinkedList<PuntoDosDimensiones> puntos = null;
            CalculadorTamanoRelativo calculador = new CalculadorTamanoRelativo();

            String rutaArvhivo = ControladorTamanoRelativo.class.getClassLoader().getResource("Test1.txt").getFile();
            puntos = fuente.obtenerPuntosDosDimensionesDeArchivo(rutaArvhivo);

            VistaConsola.mostrarResultado("Puntos de Test1.txt: " + puntos);
            TamanoRelativo respuesta = calculador.calcularTamanoRelativo(puntos);
            VistaConsola.mostrarResultado("Datos calculados de Test 1:\n" + respuesta);
            VistaWeb.mostrarResultados(req, resp, respuesta, puntos, "Tamaño relativo de los datos de Test1.txt");

        } catch (Exception ex) {
            //VistaWeb.error(req, resp, ex);
        }
    }

    private static void calculoArchivoEntrada(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CalculadorTamanoRelativo calculador = new CalculadorTamanoRelativo();
            TamanoRelativo respuesta = null;
            LinkedList<PuntoDosDimensiones> puntos = new LinkedList<PuntoDosDimensiones>();

            String[] datosx = req.getParameter("datosx").split(",");
            String[] datosy = req.getParameter("datosy").split(",");

            if (datosx == null || datosx.length == 0) {
                throw new Exception("No se ingresaron datos para las X");
            }
            if (datosy == null || datosy.length == 0) {
                throw new Exception("No se ingresaron datos para las Y");
            }

            if (datosx.length != datosy.length) {
                throw new Exception("No se ingresaron la misma cantidad de datos en X y Y");
            }

            for (int i = 0; i < datosx.length; i++) {
                puntos.add(new PuntoDosDimensiones(Double.parseDouble(datosx[i]), Double.parseDouble(datosy[i])));
            }
            VistaConsola.mostrarResultado("Datos ingresados (X=LOC de clases y Y=Cantidad de métodos): " + puntos);
            respuesta = calculador.calcularTamanoRelativo(puntos);
            VistaConsola.mostrarResultado("Datos calculados de datos entrados:\n" + respuesta);
            VistaWeb.mostrarResultados(req, resp, respuesta, puntos, "Tamaño relativo de los datos entrados");

        } catch (Exception ex) {
            VistaWeb.error(req, resp, ex);
        }
    }
}
