package pe.gob.sunat.base.android.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Declaracion {

    @JsonProperty("idDam")
    private String idDam;

    public String getIdDam() {
        return idDam;
    }

    public void setIdDam(String idDam) {
        this.idDam = idDam;
    }
}
