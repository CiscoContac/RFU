package pe.gob.sunat.base.android.model;

import java.io.Serializable;
import java.util.List;

public class Contenedor implements Serializable {

    private static final long serialVersionUID = 5177628228643312263L;

    private String numContenedor;

    private String escaner;

    private List<Precinto> precintos;

    private InformeSini informeSini;

    private int cantidadBultosRM;

    private int cantidadBultosRCE;

    private boolean visible;

    public Contenedor(String numContenedor, String escaner, List<Precinto> precintos) {
        this.numContenedor = numContenedor;
        this.escaner = escaner;
        this.precintos = precintos;
        this.visible = false;
    }

    public Contenedor(String numContenedor, InformeSini informeSini) {
        this.numContenedor = numContenedor;
        this.informeSini = informeSini;
        this.visible = false;
    }

    public Contenedor(String numContenedor, int cantidadBultosRM, int cantidadBultosRCE) {
        this.numContenedor = numContenedor;
        this.cantidadBultosRM = cantidadBultosRM;
        this.cantidadBultosRCE = cantidadBultosRCE;
        this.visible = false;
    }



    public String getNumContenedor() {
        return numContenedor;
    }

    public void setNumContenedor(String numContenedor) {
        this.numContenedor = numContenedor;
    }

    public String getEscaner() {
        return escaner;
    }

    public void setEscaner(String escaner) {
        this.escaner = escaner;
    }

    public List<Precinto> getPrecintos() {
        return precintos;
    }

    public void setPrecintos(List<Precinto> precintos) {
        this.precintos = precintos;
    }

    public InformeSini getInformeSini() {
        return informeSini;
    }

    public void setInformeSini(InformeSini informeSini) {
        this.informeSini = informeSini;
    }

    public int getCantidadBultosRM() {
        return cantidadBultosRM;
    }

    public void setCantidadBultosRM(int cantidadBultosRM) {
        this.cantidadBultosRM = cantidadBultosRM;
    }

    public int getCantidadBultosRCE() {
        return cantidadBultosRCE;
    }

    public void setCantidadBultosRCE(int cantidadBultosRCE) {
        this.cantidadBultosRCE = cantidadBultosRCE;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
