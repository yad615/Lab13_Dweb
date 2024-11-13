package com.tecsup.pjr_service_springboot_react.repository;

import com.tecsup.pjr_service_springboot_react.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}