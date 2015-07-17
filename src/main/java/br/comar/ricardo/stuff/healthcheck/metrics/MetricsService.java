package br.comar.ricardo.stuff.healthcheck.metrics;

import org.springframework.boot.actuate.metrics.Metric;

public interface MetricsService {
	
	public Metric<? extends Number> getResponseMetrics();

}
