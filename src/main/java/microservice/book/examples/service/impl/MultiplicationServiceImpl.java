package microservice.book.examples.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import microservice.book.examples.domain.Multiplication;
import microservice.book.examples.domain.MultiplicationResultAttempt;
import microservice.book.examples.domain.User;
import microservice.book.examples.event.EventDispatcher;
import microservice.book.examples.event.MultiplicationSolvedEvent;
import microservice.book.examples.repository.MultiplicationResultAttemptRepository;
import microservice.book.examples.repository.UserRepository;
import microservice.book.examples.service.MultiplicationService;
import microservice.book.examples.service.RandomGeneratorService;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {
	
	private RandomGeneratorService randomService;	
	private MultiplicationResultAttemptRepository attemptRepository;
	private UserRepository userRepository;
	private EventDispatcher eventDispatcher;

	@Autowired
	public MultiplicationServiceImpl(RandomGeneratorService randomService, MultiplicationResultAttemptRepository attemptRepository,
			UserRepository userRepository,final EventDispatcher eventDispatcher){
		this.randomService=randomService;
		this.attemptRepository = attemptRepository;
		this.userRepository = userRepository;
		this.eventDispatcher = eventDispatcher;
	}
	
	@Override
	public Multiplication createRandomMultiplication() {
		
		int factorA= randomService.generateRandomFactor();
		int factorB= randomService.generateRandomFactor();
		
		return new Multiplication(factorA, factorB);
	}
	
	@Transactional
	@Override
	public boolean checkAttempt(final MultiplicationResultAttempt resultAttempt) {
		
		
		Optional<User> user = userRepository.findByAlias(resultAttempt.getUser().getAlias());
		
		boolean correct= (resultAttempt.getResultAttempt() == 
				(resultAttempt.getMultiplication().getFactorA()*resultAttempt.getMultiplication().getFactorB()));
		
		Assert.isTrue(!resultAttempt.isCorrect(),"You can't send an attempt marked as correct!!");
		
		MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(user.orElse(resultAttempt.getUser()),resultAttempt.getMultiplication(),resultAttempt.getResultAttempt(),correct);
		
		attemptRepository.save(checkedAttempt);
		
		eventDispatcher.send(new MultiplicationSolvedEvent(checkedAttempt.getId(),checkedAttempt.getUser().getId(),checkedAttempt.isCorrect()));
		return correct;
	}
	
	@Override
	public List<MultiplicationResultAttempt> getStatsForUser(String userAlias) {
		return attemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
	 }
	
	@Override
	public Optional<MultiplicationResultAttempt> getResultById(Long id){ 
		return attemptRepository.findById(id);
	}

	
}
