package com.allianz.tracker.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allianz.tracker.dto.TimeTrackerDto;
import com.allianz.tracker.mapper.TimeTrackerMapper;
import com.allianz.tracker.model.TimeTrackerModel;
import com.allianz.tracker.service.TimeTrackerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TimeTrackerServiceImpl implements TimeTrackerService {

	@Autowired
	private TimeTrackerMapper timeTrackerMapper;

	private final Map<String, List<TimeTrackerDto>> records = new HashMap<>();

	/**
	 * @param timeTrackerDto
	 * @return
	 */
	@Override
	public void recordTimeTrack(TimeTrackerModel timeTrackerModel) {
		TimeTrackerDto timeTrackerDto = timeTrackerMapper.toTimeTrackerDto(timeTrackerModel);
		List<TimeTrackerDto> empTimeRecords = records.computeIfAbsent(timeTrackerDto.getEmailAddress(),
				timeTrackerRecord -> new ArrayList<>());
		empTimeRecords.add(timeTrackerDto);
		records.put(timeTrackerDto.getEmailAddress(), empTimeRecords);
	}

	@Override
	public List<TimeTrackerDto> getEmployeeTimeTrackDetails(String emailId, int offset, int length) {
		List<TimeTrackerDto> timetrackRecords = (emailId == null) ? findAll() : findByEmailAddress(emailId);
		return filterRecords(timetrackRecords, offset, length);
	}

	/**
	 * @param email
	 * @return
	 */
	private List<TimeTrackerDto> findByEmailAddress(String email) {
		List<TimeTrackerDto> allEmpTimeTrackerRecords = null;

		if (records.containsKey(email)) {
			allEmpTimeTrackerRecords = records.get(email);
		} else {
			allEmpTimeTrackerRecords = records.getOrDefault(email, new ArrayList<TimeTrackerDto>());
		}

		return allEmpTimeTrackerRecords;
	}

	/**
	 * @return
	 */
	private List<TimeTrackerDto> findAll() {
		List<TimeTrackerDto> allEmpTimeTrackerRecords = new ArrayList<>();

		records.forEach((k, v) -> {
			allEmpTimeTrackerRecords.addAll(v);
		});

		return allEmpTimeTrackerRecords;
	}

	/**
	 * @param timetrackRecords
	 * @param offset
	 * @param length
	 * @return
	 */
	private List<TimeTrackerDto> filterRecords(List<TimeTrackerDto> timetrackRecords, int offset, int length) {
		TimeTrackerDto[] timeTrackerDtoRecords = timetrackRecords.toArray(new TimeTrackerDto[1]);
		return Arrays.asList(Arrays.copyOfRange(timeTrackerDtoRecords, offset,
				(length == -1) ? timeTrackerDtoRecords.length : (offset + length)));
	}
}
