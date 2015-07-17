package br.comar.ricardo.stuff.healthcheck.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.stereotype.Component;

import br.comar.ricardo.stuff.healthcheck.metrics.SOAPServiceMetrics;
import br.comar.ricardo.stuff.healthcheck.metrics.ServiceMetrics;

@Component
public class SOAPServiceHealth implements HealthIndicator {
	
	@Autowired
	private SOAPServiceMetrics serverMetrics;

	@Override
	public Health health() {

		Metric<? extends Number> lastMetric = serverMetrics.getResponseMetrics();
		if (!ServiceMetrics.SUCCESS_CODE.equals(lastMetric.getValue())) {
			return Health.down()
					.withDetail("service.return", lastMetric.getValue())
					.build();
		}

		return Health.up().build();
	}

}
