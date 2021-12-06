package pe.gob.sunat.base.android.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HerramientaUrl {

    private String codParametro;
    private String codDataParametro;
    private String desParametro;
    private String desAbreviatura;
    private String desGlosa;

    public HerramientaUrl(String codParametro, String codDataParametro, String desParametro, String desAbreviatura, String desGlosa) {
        this.codParametro = codParametro;
        this.codDataParametro = codDataParametro;
        this.desParametro = desParametro;
        this.desAbreviatura = desAbreviatura;
        this.desGlosa = desGlosa;
    }

    public HerramientaUrl() {
    }

    public String getCodParametro() {
        return codParametro;
    }

    public void setCodParametro(String codParametro) {
        this.codParametro = codParametro;
    }

    public String getCodDataParametro() {
        return codDataParametro;
    }

    public void setCodDataParametro(String codDataParametro) {
        this.codDataParametro = codDataParametro;
    }

    public String getDesParametro() {
        return desParametro;
    }

    public void setDesParametro(String desParametro) {
        this.desParametro = desParametro;
    }

    public String getDesAbreviatura() {
        return desAbreviatura;
    }

    public void setDesAbreviatura(String desAbreviatura) {
        this.desAbreviatura = desAbreviatura;
    }

    public String getDesGlosa() {
        return desGlosa;
    }

    public void setDesGlosa(String desGlosa) {
        this.desGlosa = desGlosa;
    }

    public static class Builder{
        private HerramientaUrl herramientaUrl;
        List<HerramientaUrl> lstHerramientaUrl;

        public Builder(HerramientaUrl herramientaUrl) { this.herramientaUrl = herramientaUrl;}

        public Builder(List<HerramientaUrl> lstHerramientaUrl) {this.lstHerramientaUrl = lstHerramientaUrl;}

        public HerramientaUrl build(){ return herramientaUrl;}

        public List<HerramientaUrl> buildAll(){ return lstHerramientaUrl;}

        public static HerramientaUrl.Builder from (JSONObject json){
            return new Builder( new HerramientaUrl(
                    json.optString("codParametro").trim(),
                    json.optString("codDataParametro").trim(),
                    json.optString("desParametro").trim(),
                    json.optString("desAbreviatura").trim(),
                    json.optString("desGlosa").trim()
            ));
        }

        public static Builder from(JSONArray json){
            int length = json.length();

            List<HerramientaUrl> lstHerramientasURL = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                try {
                    lstHerramientasURL.add(Builder.from(json.getJSONObject(i)).build());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return new Builder(lstHerramientasURL);
        }

    }



}
