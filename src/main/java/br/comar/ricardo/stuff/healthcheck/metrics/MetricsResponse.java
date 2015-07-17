package br.comar.ricardo.stuff.healthcheck.metrics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MetricsResponse {
	
	private boolean success;
	
	private String message;
	
}
