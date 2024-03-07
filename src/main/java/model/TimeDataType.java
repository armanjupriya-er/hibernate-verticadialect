package model;

import java.sql.Time;
import java.time.OffsetDateTime;
import java.time.OffsetTime;

public class TimeDataType {
	private long id;
	private OffsetTime timeWithTimeZone;
	 private OffsetDateTime timestampWithTimezone;
	 private Time timeDataType;
	 private String name;
	 
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public OffsetTime getTimeWithTimeZone() {
		return timeWithTimeZone;
	}
	public void setTimeWithTimeZone(OffsetTime timeWithTimeZone) {
		this.timeWithTimeZone = timeWithTimeZone;
	}
	public OffsetDateTime getTimestampWithTimezone() {
		return timestampWithTimezone;
	}
	public void setTimestampWithTimezone(OffsetDateTime timestampWithTimezone) {
		this.timestampWithTimezone = timestampWithTimezone;
	}
	public Time getTimeDataType() {
		return timeDataType;
	}
	public void setTimeDataType(Time timeDataType) {
		this.timeDataType = timeDataType;
	}
	 
	 

}
