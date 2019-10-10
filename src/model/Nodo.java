
package model;

import java.util.ArrayList;

public class Nodo {
    
    private Sensor sensor;
    private ArrayList<Nodo> listaHijos;
    private Nodo padre;

    public Nodo(Sensor pSensor, Nodo pPadre) {
        this.sensor = pSensor;
        this.listaHijos = new ArrayList<>();
        this.padre = pPadre;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo pPadre) {
        this.padre = pPadre;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor pSensor) {
        this.sensor = pSensor;
    }

    public ArrayList<Nodo> getListaHijos() {
        return listaHijos;
    }

    public void setListaHijos(ArrayList<Nodo> pListaHijos) {
        this.listaHijos = pListaHijos;
    }
}
