package com.allianz.tracker.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class TimeTrackerDto
 *
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@ToString
public class TimeTrackerModel {

	/**
	 * The Start Date
	 */
	@NotNull(message = "startDate is Required")
	private String startDate;

	@JsonIgnore
	private Date start;

	@JsonIgnore
	private Date end;

	/**
	 * The End Date.
	 */
	@NotNull(message = "endtDate is Required")
	private String endDate;

	/**
	 * The Email Address.
	 */
	@NotNull(message = "Email Adress is Required, Please enter in e.g. XXXXX@XXX.XXX format")
	@Email(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Please enter Email Adress in e.g. XXXXX@XXX.XXX format")
	private String emailAddress;

	/**
	 * @param startDate
	 * @param endDate
	 * @param emailAddress
	 */
	public TimeTrackerModel(String startDate, String endDate, String emailAddress) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.emailAddress = emailAddress;
	}

	public TimeTrackerModel(Date start, Date end, String emailAddress) {
		super();
		this.start = start;
		this.end = end;
		this.emailAddress = emailAddress;
	}

}
