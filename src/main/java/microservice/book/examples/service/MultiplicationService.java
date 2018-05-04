package microservice.book.examples.service;

import java.util.List;
import java.util.Optional;

import microservice.book.examples.domain.Multiplication;
import microservice.book.examples.domain.MultiplicationResultAttempt;

public interface MultiplicationService {

	Multiplication createRandomMultiplication();
	
	boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);
	
	 List<MultiplicationResultAttempt> getStatsForUser(String userAlias);
	
	 Optional<MultiplicationResultAttempt> getResultById(Long id);
}
