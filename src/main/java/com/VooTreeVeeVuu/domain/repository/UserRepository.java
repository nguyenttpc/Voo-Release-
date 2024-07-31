package com.VooTreeVeeVuu.domain.repository;

import com.VooTreeVeeVuu.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query ("SELECT o FROM User o WHERE o.firstName LIKE %:firstName% OR o.lastName LIKE %:lastName%")
	List<User> findByName (@Param ("firstName") String firstName, @Param ("lastName") String lastName);
}