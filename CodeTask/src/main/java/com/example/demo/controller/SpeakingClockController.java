package com.example.demo.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.SpeakingClockService;

/**
 * @author dhruvin.patel
 * @description Controller for Speaking clock code task
 *
 */
@RestController
@RequestMapping(value = "/speaking-clock")
public class SpeakingClockController {

	public final Logger LOGGER = LogManager.getLogger(getClass());

	@Autowired
	private SpeakingClockService speakingClockService;

	@GetMapping(value = "/convert/current-time", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> convertCurrentTime() throws Exception {
		return speakingClockService.convertCurrentTimeIntoWords();
	}
}
