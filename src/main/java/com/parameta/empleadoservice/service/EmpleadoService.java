package com.parameta.empleadoservice.service;

import com.parameta.empleadoservice.model.Empleado;
import com.parameta.empleadoservice.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public Empleado guardarEmpleado(Empleado empleado) {

        if (empleadoRepository.existsByNumeroDocumento(empleado.getNumeroDocumento())) {
            throw new RuntimeException("Ya existe un empleado con este número de documento");
        }
        return empleadoRepository.save(empleado);
    }
}
