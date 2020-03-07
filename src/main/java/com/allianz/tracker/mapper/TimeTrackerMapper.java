package com.allianz.tracker.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.allianz.tracker.dto.TimeTrackerDto;
import com.allianz.tracker.dto.TimeTrackerLegacyDto;
import com.allianz.tracker.model.TimeTrackerModel;
import com.allianz.tracker.util.CommonBeansUtil;
import com.allianz.tracker.util.DateUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TimeTrackerMapper {

	@Autowired
	private CommonBeansUtil beansUtil;

	@Autowired
	private DateUtils dateUtils;

	/**
	 * @param timeTrackerModel
	 * @return
	 */
	public TimeTrackerDto toTimeTrackerDto(TimeTrackerModel timeTrackerModel) {
		TimeTrackerDto timeTrackerDto = new TimeTrackerDto();
		beansUtil.copy(timeTrackerModel, timeTrackerDto);
		return timeTrackerDto;
	}

	/**
	 * @param timeTrackerModel
	 * @return
	 */
	public TimeTrackerDto toTimeTrackerDto(TimeTrackerLegacyDto timeTrackerLegacyDto) {
		TimeTrackerDto timeTrackerDto = new TimeTrackerDto();
		timeTrackerDto.setStartDate(dateUtils.parseToUIDateFormat(timeTrackerLegacyDto.getStart(), "start"));
		timeTrackerDto.setEndDate(dateUtils.parseToUIDateFormat(timeTrackerLegacyDto.getEnd(), "end"));
		timeTrackerDto.setEmailAddress(timeTrackerLegacyDto.getEmail());
		return timeTrackerDto;
	}

}
