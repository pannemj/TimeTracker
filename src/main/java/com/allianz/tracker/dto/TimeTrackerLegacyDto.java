package com.allianz.tracker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@ToString	
public class TimeTrackerLegacyDto {
	
	
	/**
	 * The Start Date
	 */
	private String start;

	/**
	 * The End Date.
	 */
	private String end;

	/**
	 * The Email Address.
	 */
	private String email;

}
