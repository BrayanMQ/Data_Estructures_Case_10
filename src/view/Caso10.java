
package view;

import model.Arbol;
import model.IConstants;
import model.Nodo;
import model.Sensor;
import model.TTipo;

public class Caso10 {

    public static void main(String[] args) {
        Sensor planta = new Sensor(IConstants.ID_PLANTA, IConstants.NOMBRE_PLANTA, 
                                    IConstants.TIPO_PLANTA, IConstants.CONSUMO_PLANTA);
        Nodo nodoPlanta = new Nodo(planta, IConstants.PADRE_PLANTA);
        Arbol arbol = new Arbol(nodoPlanta);
        
        Sensor sensorCanton = new Sensor(1, "Cartago", TTipo.Canton, 150.34);
        
        Sensor sensorDistrito = new Sensor(2, "Paraíso", TTipo.Distrito, 12.23);
        
        Sensor sensorBarrio = new Sensor(3, "Llanos", TTipo.Barrio, 123.123);
        
        //Inserta el cantón
        arbol.insertarNodo(sensorCanton, 0);
        //Inserta el distrito
        arbol.insertarNodo(sensorDistrito, 1);
        //Inserta el barrio
        arbol.insertarNodo(sensorBarrio, 2);
        
        System.out.println("Tree size is: " + arbol.getSize(arbol.getRaiz(), arbol));
        arbol.setTamañoArbol();
        arbol.graphArbol(arbol);  
        
        
        arbol.removerSensor(1);
        
        System.out.println("Tree size is: " + arbol.getSize(arbol.getRaiz(), arbol));
        arbol.setTamañoArbol();
    }
}
