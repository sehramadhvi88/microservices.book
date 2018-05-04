package microservice.book.examples.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import microservice.book.examples.domain.User;

public interface UserRepository extends CrudRepository<User,Long> {
 
	Optional<User> findByAlias(final String alias);
}