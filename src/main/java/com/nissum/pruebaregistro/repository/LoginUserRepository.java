package com.nissum.pruebaregistro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nissum.pruebaregistro.modelo.LoginUser;

@Repository
public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {

}
