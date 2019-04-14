package org.sienkiewicz.quote;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class QuoteService {

	private final QuoteRepository quoteRepository;

	@Autowired
	public QuoteService(QuoteRepository quoteRepository) {
		super();
		this.quoteRepository = quoteRepository;
	}
	
	Optional<Quote> getRandomQuote() {
		Optional<Quote> randomQuote = Optional.ofNullable(null);
		Long rows = quoteRepository.rowCount();
		System.out.println("MAMY KOLUMNY! " + rows);

		
		if(rows!=0) {
			randomQuote = quoteRepository.getRandom(rows);
			System.out.println("TWOJ RANDOM TO : " + randomQuote.get());
		}
		
		return randomQuote;
	}

	Optional<Quote> getQuoteById(Integer id) {
		return quoteRepository.get(Quote.class, id);
	}

	boolean addQuote(QuoteModel quoteModel) {
		Quote quote = QuoteConverter.convertModelToDTO(quoteModel);
		int id = quoteRepository.save(quote);
		
		boolean isOperationSuccesful = (id==0) ? false : true;
		
		return isOperationSuccesful;
	}

}
