package org.sienkiewicz.quote;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table
@Entity
public class Quote {

	@Id
	@Column(name = "QUOTE_ID")
	@GeneratedValue (strategy= GenerationType.SEQUENCE, generator="quote_id_seq")
	@SequenceGenerator(name = "quote_id_seq", sequenceName = "quote_id_seq", initialValue=1, allocationSize = 1)
	private Integer id;
	
	@Column(name = "CATEGORY")
	private QuoteCategory category;
	
	@Column(name = "ROOT")
	private String root;
	
	@Column(name = "CONTENT")
	private String quoteContent;
	
	@Column(name = "RATE")
	private Double rate;
	
	@Column(name = "ADDITION_DATE")
	private Date additionDate;

	Quote() {
	};

	Quote(Integer id, QuoteCategory category, String root, String quoteContent, Double rate, Date additionDate) {
		super();
		this.id = id;
		this.category = category;
		this.root = root;
		this.quoteContent = quoteContent;
		this.rate = rate;
		this.additionDate = additionDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public QuoteCategory getCategory() {
		return category;
	}

	public void setCategory(QuoteCategory category) {
		this.category = category;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getQuoteContent() {
		return quoteContent;
	}

	public void setQuoteContent(String quoteContent) {
		this.quoteContent = quoteContent;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Date getAdditionDate() {
		return additionDate;
	}

	public void setAdditionDate(Date additionDate) {
		this.additionDate = additionDate;
	}

	

}
