package com.microservice.cliente.domain.cliente;
import com.libreriauniversal.BaseEntity;
import com.microservice.cliente.domain.common.TipoCliente;
import com.microservice.cliente.domain.common.TipoDocumento;

public class Cliente extends BaseEntity {
    private String keycloakId;
    private String apellidos;
    private String nombre;
    private String email;
    private TipoCliente tipoPersona;
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;
    private String rol;

    public Cliente() {
    }

    public String getKeycloakId() {
        return keycloakId;
    }

    public void setKeycloakId(String keycloakId) {
        this.keycloakId = keycloakId;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoCliente getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoCliente tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
}