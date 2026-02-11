package com.libreriauniversal;

public enum ErrorCatalog {
    // Errores genéricos
    GENERIC_ERROR("ERR-000", "Error genérico"),

    // Errores de Cliente (CL = dominio de cliente)
    CLIENTE_NOT_FOUND("ERR-CL-001", "Cliente no encontrado"),
    CLIENTE_SAVE_ERROR("ERR-CL-002", "Error al guardar cliente"),
    CLIENTE_UPDATE_ERROR("ERR-CL-003", "Error al actualizar cliente"),
    CLIENTE_DELETE_ERROR("ERR-CL-004", "Error al eliminar cliente"),
    CLIENTE_LIST_ERROR("ERR-CL-005", "Error al listar clientes"),
    CLIENTE_ID_MISMATCH("ERR-CL-006", "El ID del cliente no coincide con el registro existente"),
    CLIENTE_INVALID_DATA("ERR-CL-007", "Datos del cliente inválidos");

    // Mas errores para los diferentes dociminios:

    private final String errorCode;
    private final String errorMessage;

    ErrorCatalog(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
