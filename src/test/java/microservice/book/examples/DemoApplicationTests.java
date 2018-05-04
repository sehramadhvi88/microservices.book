package microservice.book.examples;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import microservice.book.examples.domain.Multiplication;
import microservice.book.examples.service.MultiplicationService;
import microservice.book.examples.service.RandomGeneratorService;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoApplicationTests {

	@MockBean
	private RandomGeneratorService randomService;
	
	@Autowired
	private MultiplicationService multiplicationService;
	
	//@Test
	public void createRandomMultiplicationTest() {
		
		//given
		given(randomService.generateRandomFactor()).willReturn(50,30);
		
		//when
		Multiplication multiplication = multiplicationService.createRandomMultiplication();
		
		//then
		assertThat(multiplication.getFactorA()).isEqualTo(50);
		assertThat(multiplication.getFactorB()).isEqualTo(30);
		//assertThat(multiplication.getResult()).isEqualTo(1500);
	}

}
