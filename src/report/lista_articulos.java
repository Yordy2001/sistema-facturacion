/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

/**
 *
 * @author Yordy
 */
public class lista_articulos {

    private String cantidad;
    private String descripcion;
    private String itbis;
    private String precio;
    private String importe;
    private String descuento;

    public lista_articulos(String descripcion, String cantidad, String precio, String itbis, String descuento, String importe) {
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.itbis = itbis;
        this.precio = precio;
        this.importe = importe;
        this.descuento = descuento;
    }

    public String getcantidad() {
        return cantidad;
    }

    public void setcantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getdescripcion() {
        return descripcion;
    }

    public void setdescription(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getitbis() {
        return itbis;
    }

    public void setitbis(String itbis) {
        this.itbis = itbis;
    }

    public String getdescuento() {
        return descuento;
    }

    public void setdescuento(String descuento) {
        this.descuento = descuento;
    }

    public void setprecio(String precio) {
        this.precio = precio;
    }

    public String getprecio() {
        return precio;
    }

    public String getimporte() {
        return importe;
    }

    public void setimporte(String importe) {
        this.importe = importe;
    }

}
