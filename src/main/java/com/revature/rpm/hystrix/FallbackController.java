package com.revature.rpm.hystrix;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.rpm.dtos.ErrorResponse;

import reactor.core.publisher.Mono;

/**
 * A fallback controller which is invoked by the circuit breaker in order to
 * provide an alternative message to failed calls. Currently only exposes an
 * endpoint which
 *
 */
@RestController
public class FallbackController {

	/**
	 * Serves as a generic fallback method which indicates that the service which
	 * was needed to complete their request was unavailable.
	 * 
	 * @return an ErrorResponse object which conveys pertinent error information
	 */
	@RequestMapping("/fallback")
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public Mono<ErrorResponse> fallback() {
		ErrorResponse err = new ErrorResponse(503, LocalDate.now());
		err.setMessage("Unable to contact service, please try again later.");
		return Mono.just(err);
	}

}
