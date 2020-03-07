package com.allianz.tracker.service.impl;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.allianz.tracker.dto.TimeTrackerDto;
import com.allianz.tracker.dto.TimeTrackerLegacyDto;
import com.allianz.tracker.exception.TimeTrackerAppException;
import com.allianz.tracker.mapper.TimeTrackerMapper;
import com.allianz.tracker.model.TimeTrackerModel;
import com.allianz.tracker.service.TimeTrackerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TimeTrackerServiceImpl implements TimeTrackerService {

	@Autowired
	private TimeTrackerMapper timeTrackerMapper;

	@Autowired
	@Qualifier("appRestTemplate")
	private RestTemplate restTemplate;

	@Value("${allianz.add.time.tracker.service.url}")
	private String addTimeTrackerLegacyServiceApiUrl;

	@Value("${allianz.get.time.tracker.service.url}")
	private String getTimeTrackerLegacyServiceApiUrl;

	/**
	 * @return
	 */
	private HttpHeaders getRequiredHttpHeaders() {
		HttpHeaders requestheaders = new HttpHeaders();
		requestheaders.setContentType(MediaType.APPLICATION_JSON);
		requestheaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return requestheaders;
	}

	/**
	 * @param timeTrackerDto
	 * @return
	 */
	private String populateRequestURI(TimeTrackerModel timeTrackerModel) {
		StringBuilder builder = new StringBuilder(addTimeTrackerLegacyServiceApiUrl);
		builder.append("?start=").append(timeTrackerModel.getStart()).append("?end=").append(timeTrackerModel.getEnd())
				.append("?email=").append(timeTrackerModel.getEmailAddress());
		return builder.toString();
	}

	/**
	 * @param timeTrackerDto
	 * @return
	 */
	@Override
	public void recordTimeTrack(TimeTrackerModel timeTrackerModel) {
		try {
			HashMap<String, String> urlParams = new HashMap<>(3);
			urlParams.put("email", timeTrackerModel.getEmailAddress());
			urlParams.put("start", timeTrackerModel.getStartDate());
			urlParams.put("end", timeTrackerModel.getEndDate());

			JSONObject createTimeTrackWrapperRequest = new JSONObject();
			HttpHeaders httpHeaders = getRequiredHttpHeaders();
			HttpEntity<String> request = new HttpEntity<>(createTimeTrackWrapperRequest.toString(), httpHeaders);
			ResponseEntity<Object> addTimeTrackerresponse = null;
			String url = addTimeTrackerLegacyServiceApiUrl;
			String bsURL = UriComponentsBuilder.fromUriString(url).buildAndExpand(urlParams).toUri().toURL().toString();

			log.debug(
					"Inside TimeTrackerServiceImpl.recordTimeTrack() Request Details --> \n addTimeTrackerLegacyApiUrl={},\n requestHeaders={},\n request={}",
					bsURL, httpHeaders.toSingleValueMap(), request);

			addTimeTrackerresponse = restTemplate.exchange(url, HttpMethod.POST, request, Object.class, urlParams);

			if (HttpStatus.OK.equals(addTimeTrackerresponse.getStatusCode())) {
				log.debug("Inside TimeTrackerServiceImpl.recordTimeTrack() Response Received -->\n {}",
						addTimeTrackerresponse.getBody());
			} else {
				throw new TimeTrackerAppException(addTimeTrackerresponse.getBody().toString());
			}

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			log.error("HttpClientErrorException or HttpServerErrorException in calling Add Time Tracker Legacy Service",
					e.getMessage(), e);
			throw new TimeTrackerAppException(e.getResponseBodyAsString());
		} catch (RestClientException e) {
			log.error("RestClientException in calling Add Time Tracker Legacy Service ", e.getMessage(), e);
			throw new TimeTrackerAppException(e.getMessage());

		} catch (JSONException e) {
			log.error("JSONException in calling Add Time Tracker Legacy Service ", e.getMessage(), e);
			throw new TimeTrackerAppException(e.getMessage());
		} catch (MalformedURLException e) {
			log.error("MalformedURLException in calling Add Time Tracker Legacy Service ", e.getMessage(), e);
			throw new TimeTrackerAppException(e.getMessage());
		}

	}

	@Override
	public List<TimeTrackerDto> getEmployeeTimeTrackDetails(String emailId, int offset, int length) {
		List<TimeTrackerDto> timetrackRecords = new ArrayList<>();
		HttpEntity<String> request = null;
		ResponseEntity<List<TimeTrackerLegacyDto>> timeTrackerLegacyResponse = null;
		String bsURL = null;
		HashMap<String, String> urlParams = getUrlParamsMap(offset, length);
		HttpHeaders headers = getRequiredHttpHeaders();
		StringBuilder builder = new StringBuilder(getTimeTrackerLegacyServiceApiUrl);

		try {

			if (!StringUtils.isEmpty(emailId)) {
				builder.append("&email=").append(emailId);
			}

			bsURL = UriComponentsBuilder.fromUriString(builder.toString()).buildAndExpand(urlParams).toUri().toURL()
					.toString();
			request = new HttpEntity<>(headers);

			log.debug(
					"Inside TimeTrackerServiceImpl.getEmployeeTimeTrackDetails()()--> Request Details \n  "
							+ "emailId={},\n offset={},\n length={},\n bsURL={},\n request={} ",
					emailId, offset, length, bsURL, request);

			timeTrackerLegacyResponse = restTemplate.exchange(builder.toString(), HttpMethod.GET, request,
					new ParameterizedTypeReference<List<TimeTrackerLegacyDto>>() {
					}, urlParams);

			if (HttpStatus.OK.equals(timeTrackerLegacyResponse.getStatusCode())) {

				log.debug("Inside TimeTrackerServiceImpl.getEmployeeTimeTrackDetails() Response --> {}",
						timeTrackerLegacyResponse.getBody());

				List<TimeTrackerLegacyDto> timeTrackerLegacyDtoResponseList = timeTrackerLegacyResponse.getBody();

				timetrackRecords = populateTimetrackRecords(timeTrackerLegacyDtoResponseList);
			}

		} catch (HttpClientErrorException | HttpServerErrorException e) {
			log.error(
					"HttpClientErrorException or HttpServerErrorException in fetching Time Tracker details From Legacy Time Tracker Service",
					e.getMessage(), e);

			throw new TimeTrackerAppException((e.getResponseBodyAsString()));

		} catch (RestClientException e) {
			log.error("RestClientException in fetching Time Tracker details From Legacy Time Tracker Service",
					e.getMessage(), e);
			throw new TimeTrackerAppException(e.getMessage());
		} catch (JSONException e) {
			log.error("JSONException in fetching Time Tracker details From Legacy Time Tracker Service", e.getMessage(),
					e);
			throw new TimeTrackerAppException(e.getMessage());
		} catch (MalformedURLException e) {
			log.error("MalformedURLException in fetching Time Tracker details From Legacy Time Tracker Service",
					e.getMessage(), e);
			throw new TimeTrackerAppException(e.getMessage());
		}

		return timetrackRecords;
	}

	/**
	 * @param timeTrackerLegacyDtoResponseList
	 * @return
	 */
	private List<TimeTrackerDto> populateTimetrackRecords(List<TimeTrackerLegacyDto> timeTrackerLegacyDtoResponseList) {
		List<TimeTrackerDto> timeTrackerDtoList = new ArrayList<>();

		if (!CollectionUtils.isEmpty(timeTrackerLegacyDtoResponseList)) {
			timeTrackerLegacyDtoResponseList.forEach(timeTrackerLegacyDto -> {
				
				if(timeTrackerLegacyDto != null) {
					timeTrackerDtoList.add(timeTrackerMapper.toTimeTrackerDto(timeTrackerLegacyDto));
				}
				
			});
		}

		return timeTrackerDtoList;
	}

	/**
	 * @param emailId
	 * @param offset
	 * @param length
	 * @return
	 */
	private HashMap<String, String> getUrlParamsMap(int offset, int length) {
		HashMap<String, String> urlParams = new HashMap<>(2);
		urlParams.put("OffSet", Integer.toString(offset));
		urlParams.put("Length", Integer.toString(length));
		return urlParams;
	}

}
