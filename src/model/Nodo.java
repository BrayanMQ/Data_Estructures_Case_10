
package model;

import java.util.ArrayList;

public class Nodo {
    
    private Sensor sensor;
    private ArrayList<Nodo> listaHijos;

    public Nodo(Sensor sensor) {
        this.sensor = sensor;
        this.listaHijos = new ArrayList<>();
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public ArrayList<Nodo> getListaHijos() {
        return listaHijos;
    }

    public void setListaHijos(ArrayList<Nodo> listaHijos) {
        this.listaHijos = listaHijos;
    }
    
    
    
    
    
}
