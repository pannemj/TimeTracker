package com.allianz.tracker.service;

import java.util.List;

import com.allianz.tracker.dto.TimeTrackerDto;
import com.allianz.tracker.model.TimeTrackerModel;

public interface TimeTrackerService {

	/**
	 * @param timeTrackerModel
	 */
	public void recordTimeTrack(TimeTrackerModel timeTrackerModel);

	/**
	 * @param emailId
	 * @param offset
	 * @param length
	 * @return
	 */
	public List<TimeTrackerDto> getEmployeeTimeTrackDetails(String emailId, int offset, int length);

}
