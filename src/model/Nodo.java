
package model;

import java.util.ArrayList;

public class Nodo {
    
    private Sensor sensor;
    private ArrayList<Nodo> listaHijos;

    public Nodo(Sensor pSensor) {
        this.sensor = pSensor;
        this.listaHijos = new ArrayList<>();
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
