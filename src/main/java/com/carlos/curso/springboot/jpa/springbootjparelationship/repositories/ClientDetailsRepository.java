package com.carlos.curso.springboot.jpa.springbootjparelationship.repositories;

import com.carlos.curso.springboot.jpa.springbootjparelationship.entities.ClientDetails;
import org.springframework.data.repository.CrudRepository;

public interface ClientDetailsRepository extends CrudRepository<ClientDetails, Long> {
}
