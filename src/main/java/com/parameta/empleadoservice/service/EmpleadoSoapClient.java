package com.parameta.empleadoservice.service;

import com.parameta.empleadoservice.model.Empleado;
import com.parameta.empleadoservice.soap.GuardarEmpleadoRequest;
import com.parameta.empleadoservice.soap.GuardarEmpleadoResponse;
import com.parameta.empleadoservice.soap.SoapEmpleado;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service
public class EmpleadoSoapClient {
    private final WebServiceTemplate webServiceTemplate;
    private static final String NAMESPACE_URI = "http://parameta.com/empleados";

    @Autowired
    public EmpleadoSoapClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public Empleado guardarEmpleadoViaSoap(Empleado empleado) {
        // Crear request SOAP
        GuardarEmpleadoRequest request = new GuardarEmpleadoRequest();
        SoapEmpleado soapEmpleado = convertToSoapEmpleado(empleado);
        request.setEmpleado(soapEmpleado);

        GuardarEmpleadoResponse response = (GuardarEmpleadoResponse)
                webServiceTemplate.marshalSendAndReceive(NAMESPACE_URI + "/guardarEmpleadoRequest", request);

        return convertToModelEmpleado(response.getEmpleado());
    }

    private SoapEmpleado convertToSoapEmpleado(Empleado empleado) {
        if (empleado == null) {
            return null;
        }

        SoapEmpleado soapEmpleado = new SoapEmpleado();
        soapEmpleado.setNombres(empleado.getNombres());
        soapEmpleado.setApellidos(empleado.getApellidos());
        soapEmpleado.setTipoDocumento(empleado.getTipoDocumento());
        soapEmpleado.setNumeroDocumento(empleado.getNumeroDocumento());
        soapEmpleado.setFechaNacimiento(empleado.getFechaNacimiento());
        soapEmpleado.setFechaVinculacion(empleado.getFechaVinculacion());
        soapEmpleado.setCargo(empleado.getCargo());
        soapEmpleado.setSalario(empleado.getSalario());

        return soapEmpleado;
    }

    private Empleado convertToModelEmpleado(SoapEmpleado soapEmpleado) {
        if (soapEmpleado == null) {
            return null;
        }

        Empleado empleado = new Empleado();
        empleado.setNombres(soapEmpleado.getNombres());
        empleado.setApellidos(soapEmpleado.getApellidos());
        empleado.setTipoDocumento(soapEmpleado.getTipoDocumento());
        empleado.setNumeroDocumento(soapEmpleado.getNumeroDocumento());
        empleado.setFechaNacimiento(soapEmpleado.getFechaNacimiento());
        empleado.setFechaVinculacion(soapEmpleado.getFechaVinculacion());
        empleado.setCargo(soapEmpleado.getCargo());
        empleado.setSalario(soapEmpleado.getSalario());

        return empleado;
    }

}