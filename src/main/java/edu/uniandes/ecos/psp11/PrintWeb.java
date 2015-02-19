package edu.uniandes.ecos.psp11;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * 
 * @author Jorge Alfonso Beltrán Sandoval
 * Clase realizada para imprimir datos por un servlet levantando un servivio en el puerto 5000
 *
 */

public class PrintWeb extends HttpServlet {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String mensaje;
	public LinkedList<LinkedList<Double>> listaNumerosDoble=new LinkedList<LinkedList<Double>>();
	public LinkedList<Double> listaNumerosSensilla=new LinkedList<Double>();
	public LinkedList<String> listaTitulos=new LinkedList<String>();
	public double correlacion=0;
	public double b0=0;
	public double b1=0;
	public int LOCcounter;
	
	@Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	      showHome(req,resp);
	  }

	  private void showHome(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException {
		    resp.setContentType("text/html");
		    resp.getWriter().print(printHeader());
		    resp.getWriter().print("<table'><tr><th>Valores Tabla</th></tr>");
		    resp.getWriter().print("<tr><td>");
		    resp.getWriter().print(printListArrayDoubleSimple(listaTitulos,listaNumerosSensilla));
		    resp.getWriter().print("</td></tr></table>");
		    resp.getWriter().print(printFooter());
	  }

		/**
		 * 
		 * @parameter listaDoble
		 *		Imprime todos los valores recibidos en la lista
		 *		la listaEnlazada debe tener dos dimensiones y
		 *		contener datos tipo Double
		 *
		 * */
		public String printListArrayDoubleDoble(LinkedList<LinkedList<Double>> listaDoble){
			String texto="<table><tr><td>X</td><td>Y</td></tr>";
			for(LinkedList<Double> lista: listaDoble){
				texto+="<tr>";
				for(double value: lista){
					texto+="<td>"+value+"</td>";
				}
				texto+="</tr>";
			}
			texto+="</table>";
			return texto;
		}

		/**
		 * 
		 * @parameter listaSimple
		 *		Imprime todos los valores recibidos en la lista
		 *		la listaEnlazada debe tener una dimensiones y
		 *		contener datos tipo Double
		 *
		 * */
		public String printListArrayDoubleSimple(LinkedList<String> listaTitulos,LinkedList<Double> listaSimple){
			String texto="<table><tr>";
			for(String value: listaTitulos){
				texto+="<th>"+value+"</th>";
			}
			texto+="</tr>";
			for(double value: listaSimple){
				texto+="<td>"+value+"</td>";
			}
			texto+="</tr>";
			texto+="</table>";
			return texto;
		}
		

		/**
		 * 
		 * @return texto
		 *		Imprime la cabecera del documento HTML
		 *
		 * */
		public String printHeader(){
			String texto="<html><head><style>table, th, td {border: solid black 1px;}</style></head><body>";
			return texto;
		}

		/**
		 * 
		 * @return texto
		 *		Imprime el pie de pagina del documento HTML
		 *
		 * */
		public String printFooter(){
			String texto="</body></html>";
			return texto;
		}
}


