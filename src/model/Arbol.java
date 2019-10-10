
package model;

import java.io.File;
import java.util.ArrayList;

public class Arbol {
    
    private Nodo raiz;
    private Nodo resultado;
    
    private int tamañoArbol;
    
    private static int contadorNodos = 0;
    private static int contadorNulo = 0;
    private ArrayList<Integer> nodosVisitados = new ArrayList<>();
    private GraphViz graphBuilder = new GraphViz(); 
    private static int nuevoContadorNulo = 0;

    public Arbol(Nodo raiz) {
        this.raiz = raiz;
        this.resultado = null;
        this.tamañoArbol = 0;
    }

    public void setTamañoArbol() {
        this.tamañoArbol = 0;
    }
    
    

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
    
    public int findTreeNode(Nodo nodo){
      return nodosVisitados.indexOf(4);
    }
    
    private Nodo buscarNodo(Sensor sensor){
        return (buscarNodo(raiz, sensor));
    }
    
    private Nodo buscarNodo(Nodo nodo, Sensor sensor){
        if (nodo == null) {
            resultado = null;
        }
        
        if (sensor == nodo.getSensor()) {
            resultado = nodo;
        }
        
        for (Nodo nodoActual : nodo.getListaHijos()) {
            buscarNodo(nodoActual, nodo.getSensor());
        }
        
        return resultado;
    }
    
    public void insertarNodo(Sensor sensor, Sensor padre){
        raiz = insertarNodo(raiz, sensor, padre);
    }
    
    private Nodo insertarNodo(Nodo nodo, Sensor sensor, Sensor padre){
        if (nodo == null) {
            nodo = new Nodo(sensor);
        }else{
            Nodo nodoAInsertar = buscarNodo(padre);
            if(padre != null)          
                nodoAInsertar.getListaHijos().add(new Nodo(sensor));
            else{
                nodo.getListaHijos().add(new Nodo(sensor));
            }
                
        }
        return nodo;
    }
    
    public int getSize(Nodo nodo, Arbol arbol){
        if(nodo != null){
            for(Nodo nodoActual: nodo.getListaHijos()){
                this.tamañoArbol++;
                System.out.println(nodoActual.getSensor().getId());
                System.out.println(nodoActual.getSensor().getNombre());
                getSize(nodoActual, arbol); 
            }                  
        }
        
        return tamañoArbol;
    }
    
    public Nodo recorrerHijos(Nodo nodo, int id){
        for (Nodo nodoActual: nodo.getListaHijos()){
            if (nodoActual.getSensor().getId() == id) {
                return nodoActual;
            }
        }
        return null;
    }
    
    public void agregarUnaListaNodosAPadre(ArrayList<Nodo> listaHijos, Nodo padre){
        for (Nodo nodoAgregar: listaHijos){
            padre.getListaHijos().add(nodoAgregar);
        }
    }
    
    public void eliminarNodo(Nodo padre, Nodo nodoEliminar){
        padre.getListaHijos().remove(nodoEliminar);
    }
    
    public boolean removerSensor(Nodo raiz, int id){
        for (Nodo nodoActual: raiz.getListaHijos()) {
            Nodo padre = nodoActual;
            Nodo nodoEliminar = recorrerHijos(nodoActual, id);
            if (nodoEliminar != null) {
                ArrayList<Nodo> listaHijos = nodoEliminar.getListaHijos();
                agregarUnaListaNodosAPadre(listaHijos, padre);
                eliminarNodo(padre, nodoEliminar);
                return true;
            }
        }
        return false;
    }
    
    //Lo que se necesita para graficar
    public void findTreeNodes(Nodo nodo, Arbol arbol){
        if (nodo != null) {
            String valorNulo = "null";
            graphBuilder.addln(String.format("%d [ label=<%d> ]", contadorNodos, nodo.getSensor().getId()));
            nodosVisitados.add(nodo.getSensor().getId());
            contadorNodos++;
            
            for(Nodo nodoActual: nodo.getListaHijos()){
                if (nodoActual == null) {
                    valorNulo = "null" + contadorNulo;
                    contadorNulo++;
                    graphBuilder.addln(String.format("%s[shape=point]", valorNulo));
                }
                findTreeNodes(nodoActual, arbol);
            }
        }
    } 
    
    public void asignFatherToSon(Nodo nodo, Arbol arbol){
        if (nodo != null) {
            for(Nodo nodoActual: nodo.getListaHijos()){
                if (nodoActual != null) {
                    graphBuilder.addln(String.format("%d -> %d", findTreeNode(nodo), findTreeNode(nodoActual)));
                }else{
                    graphBuilder.addln(String.format("%d -> %s", findTreeNode(nodo), "null" + nuevoContadorNulo++));
                }
                asignFatherToSon(nodoActual, arbol);
            }
        }
    }
    
    public void finalizarGraph(){
        System.out.println("\n");
        graphBuilder.addln(graphBuilder.end_graph());
        System.out.println(graphBuilder.getDotSource());
        String type = "gif";
        File out = new File("arbol." + type);
        graphBuilder.writeGraphToFile(graphBuilder.getGraph(graphBuilder.getDotSource(), type), out);
    }
    
    public void graphArbol(Arbol arbol){
        graphBuilder.addln("ordering = out");
        findTreeNodes(raiz, arbol);
        asignFatherToSon(raiz, arbol);
        finalizarGraph();
    }
}
