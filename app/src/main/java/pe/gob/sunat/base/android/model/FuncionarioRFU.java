package pe.gob.sunat.base.android.model;

import java.io.Serializable;
import java.util.List;

public class FuncionarioRFU implements Serializable {

    private static final long serialVersionUID = 3622962742354963143L;

    private String numeroRegistro;
    private String apellidos;
    private String nombres;
    private String email;
    private String tipoFuncionario;
    private List<String> telefonos;
    private String recursoFoto;

    public FuncionarioRFU(String numeroRegistro, String apellidos, String nombres, String email,
                          String tipoFuncionario, List<String> telefonos, String recursoFoto) {
        this.numeroRegistro = numeroRegistro;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.email = email;
        this.tipoFuncionario = tipoFuncionario;
        this.telefonos = telefonos;
        this.recursoFoto = recursoFoto;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoFuncionario() {
        return tipoFuncionario;
    }

    public void setTipoFuncionario(String tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }

    public List<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<String> telefonos) {
        this.telefonos = telefonos;
    }

    public String getRecursoFoto() {
        return recursoFoto;
    }

    public void setRecursoFoto(String recursoFoto) {
        this.recursoFoto = recursoFoto;
    }
}
