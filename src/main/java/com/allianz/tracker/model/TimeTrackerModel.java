package com.allianz.tracker.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class TimeTrackerDto
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class TimeTrackerModel {

	/**
	 * The Start Date
	 */
	@NotNull(message = "startDate is Required")
	private String startDate;

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

}
