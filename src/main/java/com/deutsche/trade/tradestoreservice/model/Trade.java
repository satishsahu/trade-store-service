package com.deutsche.trade.tradestoreservice.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Trade implements Serializable {

	private static final long serialVersionUID = -1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private int version;
	@NotNull
	private String counterPartyId;
	@NotNull
	private String bookId;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date maturityDate;
	private Date createdDate;
	private char expired;

	public Trade() {
	}

	public Trade(long id, int version, String counterPartyId, String bookId, Date maturityDate, Date createdDate,
			char expired) {
		super();
		this.id = id;
		this.version = version;
		this.counterPartyId = counterPartyId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
		this.createdDate = createdDate;
		this.expired = expired;
	}

	@Override
	public String toString() {
		return "Trade [id=" + id + ", version=" + version + ", counterPartyId=" + counterPartyId + ", bookId=" + bookId
				+ ", maturityDate=" + maturityDate + ", createdDate=" + createdDate + ", expired=" + expired + "]";
	}
	
}
