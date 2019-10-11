
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
    
    public Nodo buscarNodo(int pId){
        nodoEncontrado = null;
        return (buscarNodo(raiz, pId));
    }
    
    private Nodo buscarNodo(Nodo pRaiz, int pId){
        
        if (pId == pRaiz.getSensor().getId()) {
            nodoEncontrado = pRaiz;
        }
        
        for (Nodo nodoActual : pRaiz.getListaHijos()) {
            buscarNodo(nodoActual, pId);
        }
        
        return nodoEncontrado;
    }

    public boolean insertarNodo(Sensor pSensor, int pId){
        
        //Parche si la planta no tiene hijos
        if (this.raiz.getListaHijos().isEmpty()) {
            this.raiz.getListaHijos().add(new Nodo(pSensor, raiz));
        }else{
            Nodo nodoAInsertar = buscarNodo(pId);
            if(nodoAInsertar != null)          
                nodoAInsertar.getListaHijos().add(new Nodo(pSensor, nodoAInsertar));
            else{
                return false;
            }
        }
        return true;
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
    
    public boolean removerSensor(int pId){
        Nodo nodoEliminar = buscarNodo(pId);
            
        if (nodoEliminar != null) {
            Nodo padre = nodoEliminar.getPadre();
             
            for (Nodo nodoHuerfano: nodoEliminar.getListaHijos()){
                nodoHuerfano.setPadre(padre);
            }
              
             padre.getListaHijos().remove(padre.getListaHijos()
                     .indexOf(nodoEliminar));
             padre.getListaHijos().addAll(nodoEliminar.getListaHijos());

        }
        return false;
    }
    
    public Nodo buscarNodoPorNombre(String pNombre){
        nodoEncontrado = null;
        return (buscarNodoPorNombre(raiz, pNombre));
    }
    
    private Nodo buscarNodoPorNombre(Nodo pRaiz, String pNombre){
        
        if (pNombre.equals(pRaiz.getSensor().getNombre())) {
            nodoEncontrado = pRaiz;
        }
        
        for (Nodo nodoActual : pRaiz.getListaHijos()) {
            buscarNodoPorNombre(nodoActual, pNombre);
        }
        
        return nodoEncontrado;
    }
    
    //Lo que se necesita para graficar
    public void findTreeNodes(Nodo pNodo, Arbol pArbol){
        if (pNodo != null) {
            String valorNulo = "null";
            graphBuilder.addln(String.format("%d [ label=<%d> ]", contadorNodos,
                    pNodo.getSensor().getId()));
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
