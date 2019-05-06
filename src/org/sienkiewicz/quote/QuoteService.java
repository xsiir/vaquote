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
	

	/**
	 *
	 * @return losowy cytat z bazy danych
	 */
	Optional<Quote> getRandomQuote() {
		Optional<Quote> randomQuote = Optional.ofNullable(null);
		randomQuote = quoteRepository.getRandom();
		
		return randomQuote;
	}


	/**
	 * 
	 * @param id - ID obiektu który zostanie wyciagniêty z bazy danych
	 * @return zwraca obiekt cytatu o podanym w parametrze @id, opakowanego w Optionala.
	 */
	Optional<Quote> getQuoteById(Integer id) {
		return quoteRepository.get(Quote.class, id);
	}
	
	
	/**
	 * 
	 * @param quoteModel
	 * @return
	 */
	boolean addQuote(QuoteModel quoteModel) {
		int id = quoteRepository.save(
				QuoteConverter.convertModelToDTO(quoteModel));
		
		return isOperationSuccesful(id);
	}


	private boolean isOperationSuccesful(int id) {
		return (id==0) ? false : true;
	}

}
