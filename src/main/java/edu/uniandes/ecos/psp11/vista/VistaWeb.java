package edu.uniandes.ecos.psp11.vista;

import edu.uniandes.ecos.psp11.modelo.PuntoDosDimensiones;
import edu.uniandes.ecos.psp11.modelo.TamanoRelativo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JohnDany
 */
public class VistaWeb {

    /**
     * Métod que muestra la página de inicio de la aplicación.
     *
     * @param req llamado de la página
     * @param resp respuesta de la página
     * @throws ServletException
     * @throws IOException
     */
    public static void mostrarFormunlarioInicial(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter pw = resp.getWriter();
        pw.write("<html>");
        pw.write("<head><title>John Dany Osorio R</title></head>");
        pw.println("<h1>Programa PSP 1.1</h1>");
        pw.write("<form  method=\"post\">\n"
                + "<div><h2>Cálculo del tamaño relativo</h2></div> \n"
                + "<div><input style=\"width:200;\" id=\"btnTest\" name=\"btnTest\" type=\"submit\" value=\"Calcular el archivo de prueba\"></div>\n"
                + "<div><input style=\"width:200;\"  id=\"btnEntrada\" name=\"btnEntrada\" type=\"submit\" value=\"Calcular datos personalizados\"></div>\n"
                + "</form> ");
        pw.write("</html>");
    }

    /**
     * Método que muestra los resultados del conteo
     *
     * @param req llamado de la página
     * @param resp respuesta de la página
     * @param resultado del conteo de líneas de código fue
     * @param puntos
     * @param titulo
     * @throws ServletException
     * @throws IOException
     */
    public static void mostrarResultados(HttpServletRequest req, HttpServletResponse resp, TamanoRelativo resultado, LinkedList<PuntoDosDimensiones> puntos, String titulo)
            throws ServletException, IOException {

        String vs = String.format("%.4f", resultado.getMuyPequeno());
        String s = String.format("%.4f", resultado.getPequeno());
        String m = String.format("%.4f", resultado.getMediano());
        String l = String.format("%.4f", resultado.getGrande());
        String vl = String.format("%.4f", resultado.getMuyGrande());

        PrintWriter r = resp.getWriter();
        r.println("<br><h3>" + titulo + "</h3>");
        r.println("<html>");
        r.write("<head>");
        r.write("<title>John Dany Osorio R</title>");
        r.write("<meta http-equiv=\"Content-Language\" >");
        r.write("<meta http-equiv=\"Content-Type\" content=\"text/html;\">");
        r.println("<style>");
        r.println("table, th, td");
        r.print("{");
        r.println("border: 1px solid black;");
        r.println("border-collapse: collapse;");
        r.println("}");
        r.println("th, td");
        r.print("{");
        r.println("padding: 5px;");
        r.println("text-align: left;");
        r.print("}");
        r.println("</style>");
        r.write("</head>");
        r.write("<body>");
        r.println("<table>");
        r.println("<tr>");
        r.println("<th>VS</th>");
        r.println("<th>S</th>");
        r.println("<th>M</th>");
        r.println("<th>L</th>");
        r.println("<th>VL</th>");
        r.println("</tr>");
        r.println("<tr>");
        r.println("<td><label>" + vs + " </label>");
        r.println("</td>");
        r.println("<td><label>" + s + " </label>");
        r.println("</td>");
        r.println("<td><label>" + m + " </label>");
        r.println("</td>");
        r.println("<td><label>" + l + "</label>");
        r.println("</td>");
        r.println("<td><label>" + vl + "</label>");
        r.println("</tr>");
        r.println("<tr>");
        r.println("</tr>");
        r.println("<td colspan=\"6\">");
        r.println("<h4>Datos (X=Items, Y=Divisiones): </h4><label>" + puntos + "</label>");
        r.println("</td>");
        r.println("</table>");
        r.println("</body>");
        r.println("</html>");
        r.flush();
    }

    public static void mostrarVentanaEntrada(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter pw = resp.getWriter();
        pw.write("<html>");
        pw.println("<head><title>John Dany Osorio R</title></head>");
        pw.println("<h1>Captura de datos</h1>");
        pw.write("<form  method=\"post\">\n"
                + "<div><h2>Cálculo del tamaño relativo</h2></div> \n"
                + "<div><label style=\"width:1500; display: inline-block;\" >Items:</label><input  style=\"width:300;\" title=\"Separe cada valor por coma\" type=\"text\" name=\"datosx\" id=\"datosx\"></div> \n"
                + "<div><label style=\"width:1500; display: inline-block;\" >Divisiones:</label><input  style=\"width:300;\" title=\"Separe cada valor por coma\" type=\"text\" name=\"datosy\" id=\"datosy\"</div> \n"
                + "<div><input id=\"btnCalcularEntrada\" name=\"btnCalcularEntrada\" type=\"submit\" value=\"Calcular\" text=\"La cantidad de valores debe coincidir en ambas cajas\"></div>\n"
                + "</form> ");
        pw.write("</html>");
    }

    /**
     * Método que controla la presentación de errores.
     *
     * @param req llamado de la página
     * @param resp respuesta de la página
     * @param ex excepción capturada
     * @throws ServletException
     * @throws IOException
     */
    public static void error(HttpServletRequest req, HttpServletResponse resp, Exception ex)
            throws ServletException, IOException {
        resp.getWriter().println("Vaya se ha presentado un error!" + ex.getMessage());
    }
}
