package com.webs.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.webs.user.model.User;

public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {
	User findByUserId(@Param("userId") Long userId);

	User findByUsername(@Param("username") String username);
}