package com.parameta.empleadoservice.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "guardarEmpleadoRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class GuardarEmpleadoRequest {
    @XmlElement(required = true)
    private SoapEmpleado empleado;

    // Constructor sin argumentos
    public GuardarEmpleadoRequest() {}

    // Constructor con empleado
    public GuardarEmpleadoRequest(SoapEmpleado empleado) {
        this.empleado = empleado;
    }

    // Getters y Setters
    public SoapEmpleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(SoapEmpleado empleado) {
        this.empleado = empleado;
    }
}