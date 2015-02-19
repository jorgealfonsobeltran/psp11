package edu.uniandes.ecos.psp11;

import java.util.LinkedList;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;

/**
 * 
 * @author JorgeAlfonso Beltrán Sandoval
 * Clase Controlador que llama a los modelos 
 * y realiza la impresion de los datos en las vistas
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	
    	FileToList archivoLectura=new FileToList();
    	LinkedList<Double> listaNumeros=archivoLectura.archivoAListaSencilla();
    	LinkedList<Double> listaLNNumeros=new LinkedList<Double>();
    	LinkedList<Double> listaRangos=new LinkedList<Double>();
    	LinkedList<String> listaTitulos=new LinkedList<String>();
		for(double valor : listaNumeros){
			listaLNNumeros.add(Math.log(valor));
		}
    	Operations calculos=new Operations();
    	double promedio=calculos.promedio(listaLNNumeros);
    	double desviacion=calculos.desviacion(listaLNNumeros);
    	double r=desviacion,veryShortRange=calculos.rangeVeryShort(r, promedio),shortRange=calculos.rangeShort(r, promedio),mediumRange=calculos.rangeMedium(r, promedio),largeRange=calculos.rangeLarge(r, promedio),veryLargeRange=calculos.rangeVeryLarge(r, promedio);
    	
    	PrintConsole impresoraConsola=new PrintConsole();
    	System.out.println("Tabla de valores, obtenida del archivo arreglo.txt en la base del archivo: ");
    	System.out.println("X");
    	impresoraConsola.printListArrayDoubleSensilla(listaNumeros);
    	impresoraConsola.printListArrayDoubleSensilla(listaLNNumeros);
    	System.out.println("Promedio "+promedio);
    	System.out.println("Desviacion "+desviacion);
    	System.out.println("VS: "+veryShortRange);
    	System.out.println("S: "+shortRange);
    	System.out.println("M: "+mediumRange);
    	System.out.println("L: "+largeRange);
    	System.out.println("VL: "+veryLargeRange);

    	listaRangos.add(veryShortRange);
    	listaRangos.add(shortRange);
    	listaRangos.add(mediumRange);
    	listaRangos.add(largeRange);
    	listaRangos.add(veryLargeRange);
    	PrintWeb impresoraWeb=new PrintWeb();
	    impresoraWeb.listaNumerosSensilla=listaRangos;
	    listaTitulos.add("Very Short");
	    listaTitulos.add("Short");
	    listaTitulos.add("Medium");
	    listaTitulos.add("Large");
	    listaTitulos.add("Very Large");
	    impresoraWeb.listaTitulos=listaTitulos;
	    Server server = new Server(5000);
	    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
	    context.setContextPath("/");
	    server.setHandler(context);
	    context.addServlet(new ServletHolder(impresoraWeb), "/*");
		server.start();
	    server.join();
    }
}
