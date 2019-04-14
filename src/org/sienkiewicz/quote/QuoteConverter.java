package org.sienkiewicz.quote;

import java.util.Date;

public class QuoteConverter {

	public static Quote convertModelToDTO(QuoteModel quoteModel) {
		Quote quote = new Quote();
		quote.setCategory(quoteModel.getCategory());
		quote.setRoot(quoteModel.getRoot());
		quote.setQuoteContent(quoteModel.getQuoteContent());
		quote.setAdditionDate(new Date());
		quote.setRate(0.0);
		return quote;
	}

}
