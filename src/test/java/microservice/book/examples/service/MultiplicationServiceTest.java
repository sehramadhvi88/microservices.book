package microservice.book.examples.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import microservice.book.examples.domain.Multiplication;
import microservice.book.examples.domain.MultiplicationResultAttempt;
import microservice.book.examples.domain.User;
import microservice.book.examples.event.EventDispatcher;
import microservice.book.examples.repository.MultiplicationResultAttemptRepository;
import microservice.book.examples.repository.UserRepository;
import microservice.book.examples.service.RandomGeneratorService;
import microservice.book.examples.service.impl.MultiplicationServiceImpl;

public class MultiplicationServiceTest {

	private MultiplicationServiceImpl multiplicationServiceImpl;
	MultiplicationResultAttemptRepository attemptRepository;
	UserRepository userRepository;
	private EventDispatcher eventDispatcher;
	@Mock
	RandomGeneratorService randomService;
	
	@Before
	public void setup(){
		
		MockitoAnnotations.initMocks(this);
		multiplicationServiceImpl = new MultiplicationServiceImpl(randomService,attemptRepository,userRepository,eventDispatcher);

	}
	
	//@Test
	public void checkCorrectAttemptTest(){
		
		// given
		Multiplication multiplication = new Multiplication(50, 60);
		User user = new User("john_doe");
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000,false);
		
		//when
		boolean attemptResult= multiplicationServiceImpl.checkAttempt(attempt);
		//assertThat(attemptResult).isTrue();		
	}
	
	//@Test
	public void checkWrongAttemptTest(){
		
		// given
		Multiplication multiplication = new Multiplication(50, 60);
		User user = new User("john_doe");
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3010,false);
		
		//when
		boolean attemptResult= multiplicationServiceImpl.checkAttempt(attempt);
		//assertThat(attemptResult).isFalse();		
	}
	
	/* @Test
	 public void createRandomMultiplicationTest() {
	 // given (our mocked Random Generator service will return first 50, then 30)
	 given(randomService.generateRandomFactor()).willReturn(50, 30);
	 // when
	 Multiplication multiplication = multiplicationServiceImpl.createRandomMultiplication();
	 // assert
	 assertThat(multiplication.getFactorA()).isEqualTo(50);
	 assertThat(multiplication.getFactorB()).isEqualTo(30);
	 assertThat(multiplication.getResult()).isEqualTo(1500);
	 }*/
	
}
