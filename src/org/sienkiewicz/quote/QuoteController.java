package org.sienkiewicz.quote;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/quote")
class QuoteController {
	
	private final QuoteService quoteService;
	
	@Autowired
	private QuoteController(QuoteService quoteService) {
		super();
		this.quoteService = quoteService;
	}
	
	@RequestMapping(value = "/random", method = RequestMethod.GET)
	private ResponseEntity<Quote> getRandomQuote() {
		
		Optional<Quote> randomQuote = quoteService.getRandomQuote();
		ResponseEntity<Quote> response = getHttpResponseWithGivenQuote(randomQuote);
		
		return response;

	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	private ResponseEntity<Quote> getQuote(@PathVariable("id") Integer id ) {

		Optional<Quote> quote = quoteService.getQuoteById(id);
		ResponseEntity<Quote> response = getHttpResponseWithGivenQuote(quote);
		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	private ResponseEntity<Quote> addQuote(@RequestBody QuoteModel quoteModel) {
		ResponseEntity<Quote> response = (quoteService.addQuote(quoteModel)) ? 
				new ResponseEntity<Quote>(HttpStatus.CREATED) : 
				new ResponseEntity<Quote>(HttpStatus.BAD_GATEWAY);
		
		return response;
	}
	
	
	public HttpRequest deleteQuote() {
		return null;
	}
	
	private ResponseEntity<Quote> getHttpResponseWithGivenQuote(Optional<Quote> quote) {
		ResponseEntity<Quote> response = quote.isPresent() ? 
				new ResponseEntity<Quote>(quote.get(), HttpStatus.OK) : 
				new ResponseEntity<Quote>(HttpStatus.NOT_FOUND);
		
		return response;
	}
}


