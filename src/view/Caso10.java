
package view;

import model.Arbol;
import model.IConstants;
import model.Nodo;
import model.Sensor;
import model.SplayTree;
import model.TTipo;

public class Caso10 {

    public static void main(String[] args) {
        //Genera el arbol con la planta por defecto
        Sensor planta = new Sensor(IConstants.ID_PLANTA, IConstants.NOMBRE_PLANTA, 
                                    IConstants.TIPO_PLANTA, IConstants.CONSUMO_PLANTA);
        Nodo nodoPlanta = new Nodo(planta, IConstants.PADRE_PLANTA);
        Arbol arbol = new Arbol(nodoPlanta);
        
        //Splay Tree
        SplayTree splayTree = new SplayTree();
        splayTree.insert(nodoPlanta);
        
        Sensor sensorCanton = new Sensor(1, "Cartago", TTipo.Canton, 123.12);
        
        Sensor sensorDistrito = new Sensor(2, "Paraíso", TTipo.Distrito, 12.23);
        
        Sensor sensorBarrio = new Sensor(3, "Llanos", TTipo.Barrio, 123.123);
        
        
        //Inserta el cantón
        arbol.insertarNodo(sensorCanton, 0);
        //Inserta el nodo al splayTree
        splayTree.insert(arbol.buscarNodo(1));
        //Inserta el distrito
        arbol.insertarNodo(sensorDistrito, 1);
        //Inserta el nodo al splayTree
        splayTree.insert(arbol.buscarNodo(2));
        //Inserta el barrio
        arbol.insertarNodo(sensorBarrio, 2);
        //Inserta el nodo al splayTree
        splayTree.insert(arbol.buscarNodo(3));
        
        
        System.out.println("Tree size is: " + arbol.getSize(arbol.getRaiz(), arbol));
        arbol.setTamañoArbol();
        //arbol.graphArbol(arbol);  
        
        
        arbol.removerSensor(1);
        
        System.out.println("Tree size is: " + arbol.getSize(arbol.getRaiz(), arbol));
        arbol.setTamañoArbol();
        
        splayTree.inorder();
        
        
        //Ventana principal
        //PantallaPrincipal pantallaPrincipal = new PantallaPrincipal(arbol, splayTree);
        //pantallaPrincipal.setVisible(true);
    }
}
