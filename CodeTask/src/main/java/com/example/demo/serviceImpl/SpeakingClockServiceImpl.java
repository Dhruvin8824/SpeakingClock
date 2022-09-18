/**
 * 
 */
package com.example.demo.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.service.SpeakingClockService;

/**
 * @author dhruvin.patel
 *
 */
@Service
public class SpeakingClockServiceImpl implements SpeakingClockService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private static final String MID_DAY = "12:00";
	private static final String MID_NIGHT = "00:00";

	@Override
	public Map<String, Object> convertCurrentTimeIntoWords() throws Exception {

		logger.info("In convertCurrentTimeIntoWords method call");
		Map<String, Object> output = new HashMap<String, Object>();

		// Map for hour i.e eight, nine, etc.
		Map<Integer, String> clockConstHourMap = new HashMap<Integer, String>();
		clockConstHourMap.put(1, "one");
		clockConstHourMap.put(2, "two");
		clockConstHourMap.put(3, "three");
		clockConstHourMap.put(4, "four");
		clockConstHourMap.put(5, "five");
		clockConstHourMap.put(6, "six");
		clockConstHourMap.put(7, "seven");
		clockConstHourMap.put(8, "eight");
		clockConstHourMap.put(9, "nine");
		clockConstHourMap.put(10, "ten");
		clockConstHourMap.put(11, "eleven");
		clockConstHourMap.put(12, "twelve");

		// Map for minutes i.e twenty, thirty, etc.
		Map<Integer, String> minMap = new HashMap<Integer, String>();
		minMap.put(1, "ten");
		minMap.put(2, "twenty");
		minMap.put(3, "thirty");
		minMap.put(4, "fourty");
		minMap.put(5, "fifty");

		// Map to convert 24 hr format to 12 hr format for hour.
		Map<Integer, Integer> map24HrTo12HrMap = new HashMap<Integer, Integer>();
		map24HrTo12HrMap.put(13, 1);
		map24HrTo12HrMap.put(14, 2);
		map24HrTo12HrMap.put(15, 3);
		map24HrTo12HrMap.put(16, 4);
		map24HrTo12HrMap.put(17, 5);
		map24HrTo12HrMap.put(18, 6);
		map24HrTo12HrMap.put(19, 7);
		map24HrTo12HrMap.put(20, 8);
		map24HrTo12HrMap.put(21, 9);
		map24HrTo12HrMap.put(22, 10);
		map24HrTo12HrMap.put(23, 11);
		map24HrTo12HrMap.put(00, 12);

		// Snippet to get 24 hour current time.
		Date currentDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("kk:mm");
		String clockTime = simpleDateFormat.format(currentDate);

		if (clockTime.equals(MID_DAY)) {
			output.put("currentTimeInWords", "It's Midday");
		} else if (clockTime.equals(MID_NIGHT)) {
			output.put("currentTimeInWords", "It's Midnight");
		} else {

			try {
				String[] clockTimeArray = clockTime.split(":");

				int hour = Integer.parseInt(clockTimeArray[0]);
				if ((hour > 12 && hour < 24) || hour == 0) {
					hour = map24HrTo12HrMap.get(hour);
				}
				String hourInWords = clockConstHourMap.get(hour);

				String minuteInWordsWithZero = "";
				String minuteInWords = "";
				if (!clockTimeArray[1].equals("00")) {
					int minute = Integer.parseInt(clockTimeArray[1]);
					if (clockTimeArray[1].startsWith("0")) {
						minuteInWordsWithZero = clockConstHourMap.get(minute);
					} else {
						int lastDigit = minute % 10;
						minuteInWords = minMap.get(minute / 10);
						if (lastDigit != 0) {
							minuteInWords = minuteInWords.concat(" " + clockConstHourMap.get(lastDigit));
						}
					}
				}
				output.put("currentTimeInWords", "It's " + hourInWords + " " + minuteInWordsWithZero + minuteInWords);
				output.put("success", "true");
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
						"Error converting current time into words!!");
			}

		}
		logger.info("convertCurrentTimeIntoWords method call ended");
		return output;
	}

}
