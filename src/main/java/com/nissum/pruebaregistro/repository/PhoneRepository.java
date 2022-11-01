package com.nissum.pruebaregistro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nissum.pruebaregistro.modelo.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
