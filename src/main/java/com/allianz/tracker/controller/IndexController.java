package com.allianz.tracker.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import com.allianz.tracker.model.TimeTrackerModel;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {
	
	@InitBinder
	public void initBinder(DataBinder dataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
		dateFormat.setLenient(false);
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * @param model
	 * @return
	 */
	@GetMapping(path = "/")
	public ModelAndView getIndex(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("empDetails", new TimeTrackerModel());
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	
	/**
	 * @param model
	 * @return
	 */
	@GetMapping(path = "/timetracker")
	public ModelAndView search(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("emailAddress", new String());
		modelAndView.setViewName("timetracker");
		return modelAndView;
	}

}
