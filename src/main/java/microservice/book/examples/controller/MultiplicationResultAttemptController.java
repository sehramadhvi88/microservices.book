package microservice.book.examples.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import microservice.book.examples.domain.MultiplicationResultAttempt;
import microservice.book.examples.service.MultiplicationService;

@RestController
@RequestMapping("/results")
public class MultiplicationResultAttemptController {

	private MultiplicationService multiplicationService;
	
	@Autowired
	MultiplicationResultAttemptController(MultiplicationService multiplicationService){
		this.multiplicationService=multiplicationService;
	}
	
	@PostMapping
	ResponseEntity<MultiplicationResultAttempt> postResult(@RequestBody MultiplicationResultAttempt resultAttempt){
		boolean isCorrect = multiplicationService.checkAttempt(resultAttempt);
		
		MultiplicationResultAttempt attemptCopy = new MultiplicationResultAttempt(resultAttempt.getUser(),resultAttempt.getMultiplication(),resultAttempt.getResultAttempt(),isCorrect);
		
		return ResponseEntity.ok(attemptCopy);
	}	
	
	@GetMapping
	ResponseEntity<List<MultiplicationResultAttempt>> getStatistics(@RequestParam("alias") String alias) {
		return ResponseEntity.ok(multiplicationService.getStatsForUser(alias));
	}
	
	@GetMapping("/{resultId}")
	ResponseEntity<MultiplicationResultAttempt> getResultById(@PathVariable("resultId") Long	resultId){
		Optional<MultiplicationResultAttempt> result = multiplicationService.getResultById(resultId);
		return ResponseEntity.ok(result.orElse(new MultiplicationResultAttempt()));		
	}
	
}
