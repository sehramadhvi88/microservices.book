/*package microservice.book.examples.configuration;

import org.springframework.cloud.stream.annotation.EnableBinding;

import microservice.book.examples.stream.KafkaOutputStream;

@EnableBinding(KafkaOutputStream.class)
public class StreamConfiguration {

	@Bean
	public TopicExchange multiplicationExchange(@Value("${multiplication.exchange}") final String exchangeName){
		return new TopicExchange(exchangeName);
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}
	
	@Bean
	 public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	 }
}
*/