package br.comar.ricardo.stuff.healthcheck.metrics;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.comar.ricardo.stuff.healthcheck.pojo.Quote;
import br.comar.ricardo.stuff.healthcheck.service.RESTServices;

@Component
@Data
@EqualsAndHashCode(callSuper=false)
public class RESTServiceMetrics extends ExternalServiceMetrics {

	final String responseKey = "restservice.lastrequest.response";
	final String responseTimeKey = "restservice.lastrequest.responseTime";

	@Autowired
	private RESTServices service;

	@Override
	MetricsResponse getMetricsResponse() {
		Quote quote = service.getQuote();
		
		return "success".equals(quote.getType()) ? 
				new MetricsResponse(true, quote.getValue().getQuote()) : 
					new MetricsResponse(false, "");
	}

}
