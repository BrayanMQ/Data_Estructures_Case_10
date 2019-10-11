
package controller;

import model.SplayTree;
import model.Arbol;
import model.IConstants;
import model.Nodo;
import model.Sensor;
import model.TTipo;

public class PantallaAgregarSensorController {

    public PantallaAgregarSensorController() {
        
    }
    
    public Nodo buscarPadreExistente(Arbol pArbol, String pCanton, String pDistrito, String pBarrio){
        Nodo cantonPadre = pArbol.buscarNodoPorNombre(pCanton);
        Nodo distritoPadre = pArbol.buscarNodoPorNombre(pCanton);
        Nodo nodoPadre = null;
        if (cantonPadre != null) {
            nodoPadre = cantonPadre;
        }else{
            if (distritoPadre != null) {
                nodoPadre = distritoPadre;
            }else{
                if (!"".equals(pBarrio)) {
                nodoPadre = distritoPadre;
                }
            }
        }
        return nodoPadre;
    }
    
    public TTipo asignarTipo(Enum pTipoPadre){
        if (pTipoPadre == TTipo.Canton) {
            return TTipo.Distrito;
        }
        return TTipo.Barrio;
    }
    
    public String validarCamposVacios(String pCanton, String pDistrito, String pBarrio){
        if ("".equals(pCanton)) {
            return IConstants.NOMBRE_PLANTA;
        }else{
            if ("".equals(pDistrito)) {
                return pCanton;
            }else{
                if ("".equals(pBarrio)) {
                    return pDistrito;
                }
            }
        }
        return null;
    }
    
    public boolean agregarSensor(Arbol pArbol, SplayTree splayTree, int pId, String pCanton, String pDistrito, String pBarrio, double pConsumo){
        String nombreSensor = validarCamposVacios(pCanton, pDistrito, pBarrio);
        if (nombreSensor != null) {
            if (nombreSensor == pCanton) {
                Sensor sensor = new Sensor(pId, pCanton, TTipo.Canton, pConsumo);
                pArbol.insertarNodo(sensor, IConstants.ID_PLANTA);
            }else{
                Nodo nodoPadre = buscarPadreExistente(pArbol, pCanton, pDistrito, pBarrio);
                if (nodoPadre != null) {
                    TTipo tipoSensorPadre = nodoPadre.getSensor().getTipo();
                    TTipo tipoSensor = asignarTipo(tipoSensorPadre);
                    Sensor sensor = new Sensor(pId, pCanton, tipoSensor, pConsumo);
                    pArbol.insertarNodo(sensor, nodoPadre.getSensor().getId());
                }
            }
            splayTree.insert(pArbol.buscarNodo(pId));
            return true;
        }
        
        return false;
    }
    
    
    
}
