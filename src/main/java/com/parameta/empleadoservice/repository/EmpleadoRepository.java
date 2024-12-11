package com.parameta.empleadoservice.repository;

import com.parameta.empleadoservice.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    boolean existsByNumeroDocumento(String numeroDocumento);
}