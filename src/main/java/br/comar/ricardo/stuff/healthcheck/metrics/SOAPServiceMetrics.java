package br.comar.ricardo.stuff.healthcheck.metrics;

import lombok.Data;
import lombok.EqualsAndHashCode;
import metrics.wsdl.ForecastReturn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.comar.ricardo.stuff.healthcheck.service.SOAPServices;

@Component
@Data
@EqualsAndHashCode(callSuper=false)
public class SOAPServiceMetrics extends ExternalServiceMetrics {

	final String responseKey = "soapservice.lastrequest.response";
	final String responseTimeKey = "soapservice.lastrequest.responseTime";
	
	@Autowired
	private SOAPServices service;
	
	@Override
	MetricsResponse getMetricsResponse() {
		ForecastReturn forecast = service.getForecast("94304");
		return new MetricsResponse(forecast.isSuccess(), forecast.getResponseText());
	}

}
