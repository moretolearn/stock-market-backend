package com.cts.fse.stockmarket.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.fse.stockmarket.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String roleName);

	Boolean existsByName(String roleName);
}
