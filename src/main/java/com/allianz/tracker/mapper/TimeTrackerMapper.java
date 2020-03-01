package com.allianz.tracker.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.allianz.tracker.dto.TimeTrackerDto;
import com.allianz.tracker.model.TimeTrackerModel;
import com.allianz.tracker.util.CommonBeansUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TimeTrackerMapper {
	
	@Autowired
	private CommonBeansUtil beansUtil;
	
	/**
	 * Populates CatalogDto using Catalog entity. Used in Get All Catalogs api
	 * 
	 * @param catalog
	 * @return
	 */
	public TimeTrackerDto toTimeTrackerDto(TimeTrackerModel timeTrackerModel) {
		TimeTrackerDto timeTrackerDto = new TimeTrackerDto();
		beansUtil.copy(timeTrackerModel, timeTrackerDto);
		return timeTrackerDto;
	}

}
