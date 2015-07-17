package br.comar.ricardo.stuff.healthcheck.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.comar.ricardo.stuff.healthcheck.pojo.Quote;

@Component
public class RESTServices {

	public Quote getQuote() {
		RestTemplate restTemplate = new RestTemplate();
		Quote quote = restTemplate.getForObject(
				"http://gturnquist-quoters.cfapps.io/api/random", Quote.class);

		return quote;
	}

}
