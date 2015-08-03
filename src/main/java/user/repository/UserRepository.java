package user.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import user.model.User;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {
	User findByUserId(@Param("userId") Long userId);

	List<User> findByUsername(@Param("username") String username);
}