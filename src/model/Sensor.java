package model;

public class Sensor {
    private int id;
    private String nombre;
    private TTipo tipo;
    private double consumo;
    private double consumoActual;

    public Sensor(int pId, String pNombre, TTipo pTipo, double pConsumo) {
        this.id = pId;
        this.nombre = pNombre;
        this.tipo = pTipo;
        this.consumo = pConsumo;
        this.consumoActual = 0;
    }
    

    public Sensor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int pId) {
        this.id = pId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }

    public TTipo getTipo() {
        return tipo;
    }

    public void setTipo(TTipo pTipo) {
        this.tipo = pTipo;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double pConsumo) {
        this.consumo = pConsumo;
    }

    public double getConsumoActual() {
        return consumoActual;
    }

    public void setConsumoActual(double pConsumoActual) {
        this.consumoActual = pConsumoActual;
    }
}
