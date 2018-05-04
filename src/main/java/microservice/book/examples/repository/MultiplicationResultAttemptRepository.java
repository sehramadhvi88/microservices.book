package microservice.book.examples.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import microservice.book.examples.domain.MultiplicationResultAttempt;

public interface MultiplicationResultAttemptRepository extends CrudRepository<MultiplicationResultAttempt,Long> {

	List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);
}
