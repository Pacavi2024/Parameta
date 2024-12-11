package com.parameta.empleadoservice.soap;

import com.parameta.empleadoservice.model.Empleado;
import com.parameta.empleadoservice.service.EmpleadoService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@WebService
public class EmpleadoSoapEndpoint {
    private static final String NAMESPACE_URI = "http://parameta.com/empleados";

    @Autowired
    private EmpleadoService empleadoService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "guardarEmpleadoRequest")
    @ResponsePayload
    @WebMethod
    public GuardarEmpleadoResponse guardarEmpleado(@RequestPayload GuardarEmpleadoRequest request) {
        // Convertir request SOAP a entidad
        Empleado empleado = convertToModelEmpleado(request.getEmpleado());
        //System.out.println("Aqui lleguecito");
        // Guardar empleado
        Empleado empleadoGuardado = empleadoService.guardarEmpleado(empleado);
        //System.out.println("Aqui lleguéeee");
        // Preparar respuesta SOAP
        GuardarEmpleadoResponse response = new GuardarEmpleadoResponse();
        response.setEmpleado(convertToSoapEmpleado(empleadoGuardado));

        return response;
    }

    // Método de conversión de SoapEmpleado a Empleado
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

    // Método de conversión de Empleado a SoapEmpleado
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
}