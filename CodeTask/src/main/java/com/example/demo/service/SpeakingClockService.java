/**
 * 
 */
package com.example.demo.service;

import java.util.Map;

/**
 * @author dhruvin.patel
 *
 */

public interface SpeakingClockService {

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> convertCurrentTimeIntoWords() throws Exception;
	
}
