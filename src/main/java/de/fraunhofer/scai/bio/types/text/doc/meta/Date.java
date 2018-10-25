/*
 * Copyright 2018 Fraunhofer Institute SCAI, St. Augustin, Germany
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.fraunhofer.scai.bio.types.text.doc.meta;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author klein
 *
 */
public class Date implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8973046289828947316L;
    private int day;
    private int month;
    private int year;
    
    /**
     * Default constructor.
     *
     */
    public Date() {
    }

    /**
     * 
     * @return the {@link Calendar} object of the {@link Date}.
     */
    public Calendar getDate() {
	Calendar calendar = Calendar.getInstance();
	calendar.set(getYear(), getMonth(), getDay());
	return calendar;
    }

    /**
     * getter for day - gets Restricted: 1-31
     * 
     * @return day of the {@link Date}
     */
    public int getDay() {
	return day;
    }

    /**
     * getter for month - gets Restriction: 1-12
     * 
     * @return month of the {@link Date}
     */
    public int getMonth() {
	return month;
    }

    /**
     * getter for year - gets Gregorian Calendar: Four digits. i.e. 2012
     * 
     * @return year of the {@link Date}
     */
    public int getYear() {
	return year;
    }

    /**
     * setter for day - sets Restricted: 1-31
     * 
     * @param day value to set to {@link Date}
     */
    public void setDay(int day) {
	if (day < 1 || day > 31)
	    throw new IllegalArgumentException("Argument 'day' must be between '1' and '31' but was " + day + "!");
	this.day = day;
    }

    /**
     * setter for month - sets Restriction: 1-12
     * 
     * @param month value to set to {@link Date}
     */
    public void setMonth(int month) {
	if (month < 1 || month > 12)
	    throw new IllegalArgumentException("Argument 'month' must be between '1' and '12' but was " + month + "!");
	this.month = month;
    }

    /**
     * setter for year - sets Gregorian Calendar: Four digits. i.e. 2012
     * 
     * @param year to set to {@link Date}
     */
    public void setYear(int year) {
	if (year < 1 || year > 2050)
	    throw new IllegalArgumentException("Argument 'year' must be between '0' and '" + Calendar.getInstance().get(Calendar.YEAR) + "' but was " + year + "!");
	this.year = year;
    }
}
