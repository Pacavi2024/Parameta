//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.12.10 a las 04:29:00 PM COT 
//


package com.parameta.empleados;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.parameta.empleados package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.parameta.empleados
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GuardarEmpleadoRequest }
     * 
     */
    public GuardarEmpleadoRequest createGuardarEmpleadoRequest() {
        return new GuardarEmpleadoRequest();
    }

    /**
     * Create an instance of {@link Empleado }
     * 
     */
    public Empleado createEmpleado() {
        return new Empleado();
    }

    /**
     * Create an instance of {@link GuardarEmpleadoResponse }
     * 
     */
    public GuardarEmpleadoResponse createGuardarEmpleadoResponse() {
        return new GuardarEmpleadoResponse();
    }

}
