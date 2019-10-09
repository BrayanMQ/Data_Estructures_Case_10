package model;

public class Sensor {
    private int id;
    private String nombre;
    private TLocalidad tipo;
    private double consumo;
    private double consumoActual;

    public Sensor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TLocalidad getTipo() {
        return tipo;
    }

    public void setTipo(TLocalidad tipo) {
        this.tipo = tipo;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public double getConsumoActual() {
        return consumoActual;
    }

    public void setConsumoActual(double consumoActual) {
        this.consumoActual = consumoActual;
    }
    
    
    
}
