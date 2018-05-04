package microservice.book.examples.event;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import lombok.extern.slf4j.Slf4j;
import microservice.book.examples.stream.KafkaOutputStream;

@Service
@Slf4j
@EnableBinding(KafkaOutputStream.class)
public class EventDispatcher {

	private KafkaOutputStream kafkaOutput;
	
	public EventDispatcher(KafkaOutputStream kafkaOutput) {
		this.kafkaOutput = kafkaOutput;
	}
	
	public void send(final MultiplicationSolvedEvent multiplicationSolvedEvent) {
		log.info("Sending MultiplicationSolvedEvent {}", multiplicationSolvedEvent);
		
		MessageChannel messageChannel = kafkaOutput.sendMultiplication();
		messageChannel.send(MessageBuilder.withPayload(multiplicationSolvedEvent).
				setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).setHeader("key", "multiplication.solved").build()); 
		
	}
}
