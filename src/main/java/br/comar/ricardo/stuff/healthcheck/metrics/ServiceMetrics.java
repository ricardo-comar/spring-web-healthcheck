package br.comar.ricardo.stuff.healthcheck.metrics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import lombok.Data;

import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;

@Data
public abstract class ServiceMetrics implements PublicMetrics, MetricsService {

	public static final Integer INTERNAL_ERROR_CODE = 500;
	public static final Integer ERROR_CODE = 400;
	public static final Integer SUCCESS_CODE = 200;
	
	@Override
	public Collection<Metric<?>> metrics() {

		Collection<Metric<?>> metrics = new ArrayList<Metric<?>>();

		Date end = null, start = new Date();

		metrics.add(getResponseMetrics());
		end = new Date();

		metrics.add(new Metric<Number>(getResponseTimeKey(),
				(end.getTime() - start.getTime()), start));

		return metrics;
	}

	abstract MetricsResponse getMetricsResponse();

	abstract String getResponseTimeKey();

	abstract String getResponseKey();
	
	@Override
	public synchronized Metric<? extends Number> getResponseMetrics() {
	
		try {
		
			System.err.println(this.getClass().getSimpleName() + " - getResponseMetrics()");
			
			MetricsResponse response = getMetricsResponse();
			return new Metric<Number>(getResponseKey(), ((response
					.isSuccess()) ? SUCCESS_CODE : ERROR_CODE));
		
		} catch (Exception e) {
			return new Metric<Number>(getResponseKey(), INTERNAL_ERROR_CODE);
		}

	}

}
