package com.parameta.empleadoservice.controller;

import com.parameta.empleadoservice.model.Empleado;
import com.parameta.empleadoservice.service.EmpleadoService;
import com.parameta.empleadoservice.service.EmpleadoSoapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private EmpleadoSoapClient soapClient;

    @GetMapping("/registro")
    public ResponseEntity<?> registrarEmpleado(
            @RequestParam String nombres,
            @RequestParam String apellidos,
            @RequestParam String tipoDocumento,
            @RequestParam String numeroDocumento,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaNacimiento,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaVinculacion,
            @RequestParam String cargo,
            @RequestParam Double salario
    ) {
        // Validaciones
        validarCamposNoVacios(nombres, apellidos, tipoDocumento,
                numeroDocumento, cargo, salario);

        validarEdad(fechaNacimiento);

        // Crear empleado
        Empleado empleado = new Empleado(
                nombres, apellidos, tipoDocumento, numeroDocumento,
                fechaNacimiento, fechaVinculacion, cargo, salario
        );

        try {

            Empleado empleadoGuardado = soapClient.guardarEmpleadoViaSoap(empleado);

            // Preparar respuesta
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("empleado", empleadoGuardado);

            // Calcular tiempo de vinculación
            Period tiempoVinculacion = Period.between(
                    fechaVinculacion, LocalDate.now()
            );
            respuesta.put("tiempoVinculacion",
                    formatearPeriodo(tiempoVinculacion));

            // Calcular edad
            Period edadActual = Period.between(
                    fechaNacimiento, LocalDate.now()
            );
            respuesta.put("edadActual",
                    formatearPeriodo(edadActual));

            return ResponseEntity.ok(respuesta);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar: " + e.getMessage());
        }
    }

    // Métodos de validación
    private void validarCamposNoVacios(String nombres, String apellidos,
                                       String tipoDocumento, String numeroDocumento,
                                       String cargo, Double salario) {
        if (nombres == null || nombres.isEmpty() ||
                apellidos == null || apellidos.isEmpty() ||
                tipoDocumento == null || tipoDocumento.isEmpty() ||
                numeroDocumento == null || numeroDocumento.isEmpty() ||
                cargo == null || cargo.isEmpty() ||
                salario == null) {
            throw new IllegalArgumentException("Todos los campos son obligatorios");
        }
    }

    private void validarEdad(LocalDate fechaNacimiento) {
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        if (edad < 18) {
            throw new IllegalArgumentException("El empleado debe ser mayor de 18 años");
        }
    }

    private String formatearPeriodo(Period periodo) {
        return String.format("%d años, %d meses, %d días",
                periodo.getYears(), periodo.getMonths(), periodo.getDays());
    }
}