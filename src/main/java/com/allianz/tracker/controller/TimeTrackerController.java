package com.allianz.tracker.controller;

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
import com.allianz.tracker.model.TimeTrackerModel;
import com.allianz.tracker.service.TimeTrackerService;
import com.allianz.tracker.util.DateUtils;

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

	@Autowired
	private DateUtils dateUtils;

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
		List<TimeTrackerDto> result = timeTrackerService.getEmployeeTimeTrackDetails(emailId, offset, length);
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
		timeTrackerModel.setStartDate(dateUtils.parseToBackendDateFormat(timeTrackerModel.getStartDate(), "startDate"));
		timeTrackerModel.setEndDate(dateUtils.parseToBackendDateFormat(timeTrackerModel.getEndDate(), "endDate"));
		timeTrackerModel.setStart(dateUtils.parseToBackendDate(timeTrackerModel.getStartDate(), "startDate"));
		timeTrackerModel.setEnd(dateUtils.parseToBackendDate(timeTrackerModel.getEndDate(), "endDate"));
	}

}
