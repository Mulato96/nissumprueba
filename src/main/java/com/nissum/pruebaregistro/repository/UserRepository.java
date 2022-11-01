package com.nissum.pruebaregistro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nissum.pruebaregistro.modelo.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Metodo si existe un user
	 * 
	 * @param email
	 * @return boolean
	 */
	@Query("from User u where u.email =:email")
	User existUser(@Param("email") String email);

	/**
	 * Metodo login
	 * 
	 * @param email,password
	 * @return User
	 */
	@Query("from User u where u.email =:email and u.password =:password")
	User existUserPassword(@Param("email") String email, @Param("password") String password);
}
