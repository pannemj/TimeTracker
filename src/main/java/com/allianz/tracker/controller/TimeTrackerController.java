package com.allianz.tracker.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.allianz.tracker.dto.TimeTrackerDto;
import com.allianz.tracker.exception.TimeTrackerBadDataException;
import com.allianz.tracker.model.TimeTrackerModel;
import com.allianz.tracker.service.TimeTrackerService;

import lombok.extern.slf4j.Slf4j;

/**
 * The TimeTracker Controller
 *
 */
@RestController
@Slf4j
public class TimeTrackerController {

	@InitBinder
	public void activateDirectFieldAccess(DataBinder dataBinder) {
		dataBinder.initDirectFieldAccess();
	}

	/**
	 * 
	 */
	@Autowired
	private TimeTrackerService timeTrackerService;

	/**
	 * @param emailId
	 * @param offset
	 * @param length
	 * @return
	 */
	@GetMapping(path = "/records")
	public ResponseEntity<List<TimeTrackerDto>> get(@RequestParam(name = "emailId", required = false) String emailId,
			@RequestParam(name = "offset", defaultValue = "0", required = false) int offset,
			@RequestParam(name = "length", defaultValue = "-1", required = false) int length) {
		List<TimeTrackerDto> result =timeTrackerService.getEmployeeTimeTrackDetails(emailId, offset, length);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/**
	 * @param timeTrackerModel
	 * @return
	 */
	@PostMapping(path = "/record")
	public ResponseEntity<String> put(@RequestBody @Valid TimeTrackerModel timeTrackerModel) {
		validateModel(timeTrackerModel);
		timeTrackerService.recordTimeTrack(timeTrackerModel);
		return new ResponseEntity<>("Record Added Successfully", HttpStatus.OK);
	}

	/**
	 * @param timeTrackerModel
	 */
	private void validateModel(TimeTrackerModel timeTrackerModel) {
	        Date startDate = parseDateField(timeTrackerModel.getStartDate(),"startDate");
	        Date endDate = parseDateField(timeTrackerModel.getEndDate(),"endDate");
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
	        timeTrackerModel.setStartDate(simpleDateFormat.format(startDate));
	        timeTrackerModel.setEndDate(simpleDateFormat.format(endDate));
	}

	private Date parseDateField(String dateValue , String field) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
		Date date = null;
		try {
			date = formatter.parse(dateValue);
        } catch (ParseException e) {
            throw new TimeTrackerBadDataException("Please enter "+field+ " in valid date formate");
        }
		
		return date;
	}
	

}
