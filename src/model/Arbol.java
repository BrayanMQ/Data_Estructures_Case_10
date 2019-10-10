
package model;

import java.io.File;
import java.util.ArrayList;

public class Arbol {
    
    private Nodo raiz;
    private Nodo nodoEncontrado;
    
    private int tamañoArbol;
    
    private static int contadorNodos = 0;
    private static int contadorNulo = 0;
    private ArrayList<Integer> nodosVisitados = new ArrayList<>();
    private GraphViz graphBuilder = new GraphViz(); 
    private static int nuevoContadorNulo = 0;

    public Arbol(Nodo pRaiz) {
        this.raiz = pRaiz;
        this.nodoEncontrado = null;
        this.tamañoArbol = 0;
    }

    public void setTamañoArbol() {
        this.tamañoArbol = 0;
    }
    
    

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo pRaiz) {
        this.raiz = pRaiz;
    }
    
    public int findTreeNode(Nodo pNodo){
      return nodosVisitados.indexOf(pNodo);
    }
    
    private Nodo buscarNodo(Sensor pSensor){
        return (buscarNodo(raiz, pSensor));
    }
    
    private Nodo buscarNodo(Nodo pNodo, Sensor pSensor){
        if (pNodo == null) {
            nodoEncontrado = null;
        }
        
        if (pSensor == pNodo.getSensor()) {
            nodoEncontrado = pNodo;
        }
        
        for (Nodo nodoActual : pNodo.getListaHijos()) {
            buscarNodo(nodoActual, pNodo.getSensor());
        }
        
        return nodoEncontrado;
    }
    
    public void insertarNodo(Sensor pSensor, Sensor pPadre){
        raiz = insertarNodo(raiz, pSensor, pPadre);
    }
    
    private Nodo insertarNodo(Nodo pNodo, Sensor pSensor, Sensor pPadre){
        if (pNodo == null) {
            pNodo = new Nodo(pSensor);
        }else{
            Nodo nodoAInsertar = buscarNodo(pPadre);
            if(pPadre != null)          
                nodoAInsertar.getListaHijos().add(new Nodo(pSensor));
            else{
                pNodo.getListaHijos().add(new Nodo(pSensor));
            }
        }
        return pNodo;
    }
    
    public int getSize(Nodo pNodo, Arbol pArbol){
        if(pNodo != null){
            for(Nodo nodoActual: pNodo.getListaHijos()){
                this.tamañoArbol++;
                System.out.println(nodoActual.getSensor().getId());
                System.out.println(nodoActual.getSensor().getNombre());
                getSize(nodoActual, pArbol); 
            }                  
        }
        return tamañoArbol;
    }
    
    public Nodo recorrerHijos(Nodo pNodo, int pId){
        for (Nodo nodoActual: pNodo.getListaHijos()){
            if (nodoActual.getSensor().getId() == pId) {
                return nodoActual;
            }
        }
        return null;
    }
    
    public void agregarUnaListaNodosAPadre(ArrayList<Nodo> pListaHijos, Nodo pPadre){
        for (Nodo nodoAgregar: pListaHijos){
            pPadre.getListaHijos().add(nodoAgregar);
        }
    }
    
    public void eliminarNodo(Nodo pPadre, Nodo pNodoEliminar){
        pPadre.getListaHijos().remove(pNodoEliminar);
    }
    
    public boolean removerSensor(Nodo pRaiz, int pId){
        for (Nodo nodoActual: pRaiz.getListaHijos()) {
            Nodo padre = nodoActual;
            Nodo nodoEliminar = recorrerHijos(nodoActual, pId);
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
    public void findTreeNodes(Nodo pNodo, Arbol pArbol){
        if (pNodo != null) {
            String valorNulo = "null";
            graphBuilder.addln(String.format("%d [ label=<%d> ]", contadorNodos, pNodo.getSensor().getId()));
            nodosVisitados.add(pNodo.getSensor().getId());
            contadorNodos++;
            
            for(Nodo nodoActual: pNodo.getListaHijos()){
                if (nodoActual == null) {
                    valorNulo = "null" + contadorNulo;
                    contadorNulo++;
                    graphBuilder.addln(String.format("%s[shape=point]", valorNulo));
                }
                findTreeNodes(nodoActual, pArbol);
            }
        }
    } 
    
    public void asignFatherToSon(Nodo pNodo, Arbol pArbol){
        if (pNodo != null) {
            for(Nodo nodoActual: pNodo.getListaHijos()){
                if (nodoActual != null) {
                    graphBuilder.addln(String.format("%d -> %d", findTreeNode(pNodo), findTreeNode(nodoActual)));
                }else{
                    graphBuilder.addln(String.format("%d -> %s", findTreeNode(pNodo), "null" + nuevoContadorNulo++));
                }
                asignFatherToSon(nodoActual, pArbol);
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
    
    public void graphArbol(Arbol pArbol){
        graphBuilder.addln("ordering = out");
        findTreeNodes(raiz, pArbol);
        asignFatherToSon(raiz, pArbol);
        finalizarGraph();
    }
}
