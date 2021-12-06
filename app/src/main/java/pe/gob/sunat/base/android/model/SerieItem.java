package pe.gob.sunat.base.android.model;

import java.util.ArrayList;
import java.util.List;

public class SerieItem {
    private int numero;
    private String descripcion;

    public SerieItem(int numero, String descripcion) {
        this.numero = numero;
        this.descripcion = descripcion;
    }

    public SerieItem() {
    }

    public List<SerieItem> getSerieItemList() {
        return serieItemList;
    }

    public void setSerieItemList(List<SerieItem> serieItemList) {
        this.serieItemList = serieItemList;
    }

    private List<SerieItem> serieItemList = new ArrayList<>();

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
