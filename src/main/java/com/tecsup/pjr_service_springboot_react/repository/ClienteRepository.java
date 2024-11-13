package com.tecsup.pjr_service_springboot_react.repository;

import com.tecsup.pjr_service_springboot_react.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
