package microservice.book.examples.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface KafkaOutputStream {

	public static final String MULTIPLY_OUTPUT = "multiplication_exchange";
	
	@Output(KafkaOutputStream.MULTIPLY_OUTPUT)
	MessageChannel sendMultiplication();
	
}
