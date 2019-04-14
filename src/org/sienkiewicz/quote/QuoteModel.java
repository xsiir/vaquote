package org.sienkiewicz.quote;

public class QuoteModel {

	private QuoteCategory category;
	private String root;
	private String quoteContent;
	
	public QuoteModel() {
		// TODO Auto-generated constructor stub
	}

	QuoteModel(QuoteCategory category, String root, String quoteContent) {
		super();
		this.category = category;
		this.root = root;
		this.quoteContent = quoteContent;
	}

	QuoteCategory getCategory() {
		return category;
	}

	void setCategory(QuoteCategory category) {
		this.category = category;
	}

	String getRoot() {
		return root;
	}

	void setRoot(String root) {
		this.root = root;
	}

	String getQuoteContent() {
		return quoteContent;
	}

	void setQuoteContent(String quoteContent) {
		this.quoteContent = quoteContent;
	}

}
