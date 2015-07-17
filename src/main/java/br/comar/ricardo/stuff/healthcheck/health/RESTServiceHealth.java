package br.comar.ricardo.stuff.healthcheck.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.stereotype.Component;

import br.comar.ricardo.stuff.healthcheck.metrics.RESTServiceMetrics;
import br.comar.ricardo.stuff.healthcheck.metrics.ServiceMetrics;

@Component
public class RESTServiceHealth implements HealthIndicator {

	@Autowired
	private RESTServiceMetrics serverMetrics;

	@Override
	public Health health() {

		Metric<? extends Number> lastMetric = serverMetrics.getResponseMetrics();
		if (!ServiceMetrics.SUCCESS_CODE.equals(lastMetric.getValue())) {
			return Health.outOfService()
					.withDetail("service.return", lastMetric.getValue())
					.build();
		}

		return Health.up().build();
	}

}
