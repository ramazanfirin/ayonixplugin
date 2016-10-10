package org.slevin.util;

import java.util.Calendar;
import java.util.Date;

public class SmartSchoolUtil {

	public static Date enrollDate(Date date,int field,int value){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(field, value);
		return c.getTime();
	}
}
