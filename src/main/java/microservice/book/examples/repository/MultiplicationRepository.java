package microservice.book.examples.repository;

import org.springframework.data.repository.CrudRepository;

import microservice.book.examples.domain.Multiplication;

public interface MultiplicationRepository extends CrudRepository<Multiplication, Long> {
	
}