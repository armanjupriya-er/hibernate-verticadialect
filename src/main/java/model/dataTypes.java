package model;

import java.awt.Point;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.util.UUID;

public class dataTypes {
	private long id;
	private byte[] binaryData;
	private byte[] varBinaryData;
	private byte[] longVarBinaryData;
	private Boolean booleanDatatype;
	private char chardatatype;
	private String varcharDatatype;
	private String longVarCharDataType;
	private Date dateDataType;
	private Time timeDataType;
//	 private OffsetTime timeWithTimezone;
	 private Timestamp timeStamp;
//	 private OffsetTime timeStampWithTimeZone;
	 private OffsetTime timeWithTimeZone;
	 private OffsetDateTime timestampWithTimezone;
//	 private Duration interval;
	 private Duration intervalDaytosec;
//	 private Period intervalYearToMonth;
	 private Double doublePrecisionField;
	 private float floatValue;
//	 private float floatn;
//	 private double float8field;
//	 private float realField;
	 private int integerField;
//	 private int intValue;
	 private long bingIntValue;
//	 private long int8Value;
	 private short smallIntValue;
//	 private byte tinyintValue;
	 private BigDecimal decimalValue;

//	 private BigDecimal numericValue;
//	 private BigInteger numberValue;
//	 private BigDecimal moneyValue;
//	 private Point geographicValue;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public byte[] getBinaryData() {
		return binaryData;
	}
	public void setBinaryData(byte[] binaryData) {
		this.binaryData = binaryData;
	}
	public byte[] getVarBinaryData() {
		return varBinaryData;
	}
	public void setVarBinaryData(byte[] varBinaryData) {
		this.varBinaryData = varBinaryData;
	}
	public byte[] getLongVarBinaryData() {
		return longVarBinaryData;
	}
	public void setLongVarBinaryData(byte[] longVarBinaryData) {
		this.longVarBinaryData = longVarBinaryData;
	}
	public Boolean getBooleanDatatype() {
		return booleanDatatype;
	}
	public void setBooleanDatatype(Boolean booleanDatatype) {
		this.booleanDatatype = booleanDatatype;
	}
	public char getChardatatype() {
		return chardatatype;
	}
	public void setChardatatype(char chardatatype) {
		this.chardatatype = chardatatype;
	}
	public String getVarcharDatatype() {
		return varcharDatatype;
	}
	public void setVarcharDatatype(String varcharDatatype) {
		this.varcharDatatype = varcharDatatype;
	}
	public String getLongVarCharDataType() {
		return longVarCharDataType;
	}
	public void setLongVarCharDataType(String longVarCharDataType) {
		this.longVarCharDataType = longVarCharDataType;
	}
	public Date getDateDataType() {
		return dateDataType;
	}
	public void setDateDataType(Date dateDataType) {
		this.dateDataType = dateDataType;
	}
	public Time getTimeDataType() {
		return timeDataType;
	}
	public void setTimeDataType(Time timeDataType) {
		this.timeDataType = timeDataType;
	}
//	public OffsetTime getTimeWithTimezone() {
//		return timeWithTimezone;
//	}
//	public void setTimeWithTimezone(OffsetTime timeWithTimezone) {
//		this.timeWithTimezone = timeWithTimezone;
//	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
//	public OffsetTime getTimeStampWithTimeZone() {
//		return timeStampWithTimeZone;
//	}
//	public void setTimeStampWithTimeZone(OffsetTime timeStampWithTimeZone) {
//		this.timeStampWithTimeZone = timeStampWithTimeZone;
//	}
//	public Duration getInterval() {
//		return interval;
//	}
//	public void setInterval(Duration interval) {
//		this.interval = interval;
//	}
	public Duration getIntervalDaytosec() {
		return intervalDaytosec;
	}
	public void setIntervalDaytosec(Duration intervalDaytosec) {
		this.intervalDaytosec = intervalDaytosec;
	}
//	public Period getIntervalYearToMonth() {
//		return intervalYearToMonth;
//	}
//	public void setIntervalYearToMonth(Period intervalYearToMonth) {
//		this.intervalYearToMonth = intervalYearToMonth;
//	}
	public Double getDoublePrecisionField() {
		return doublePrecisionField;
	}
	public void setDoublePrecisionField(Double doublePrecisionField) {
		this.doublePrecisionField = doublePrecisionField;
	}
	public float getFloatValue() {
		return floatValue;
	}
	public void setFloatValue(float floatValue) {
		this.floatValue = floatValue;
	}
	

	public OffsetTime getTimeWithTimeZone() {
		return timeWithTimeZone;
	}
	public void setTimeWithTimeZone(OffsetTime timeWithTimeZone) {
		this.timeWithTimeZone = timeWithTimeZone;
	}
	//	public float getFloatn() {
//		return floatn;
//	}
//	public void setFloatn(float floatn) {
//		this.floatn = floatn;
//	}
//	public double getFloat8field() {
//		return float8field;
//	}
//	public void setFloat8field(double float8field) {
//		this.float8field = float8field;
//	}
//	public float getRealField() {
//		return realField;
//	}
//	public void setRealField(float realField) {
//		this.realField = realField;
//	}
	public int getIntegerField() {
		return integerField;
	}
	public void setIntegerField(int integerField) {
		this.integerField = integerField;
	}
//	public int getIntValue() {
//		return intValue;
//	}
//	public void setIntValue(int intValue) {
//		this.intValue = intValue;
//	}
	public long getBingIntValue() {
		return bingIntValue;
	}
	public void setBingIntValue(long bingIntValue) {
		this.bingIntValue = bingIntValue;
	}
//	public long getInt8Value() {
//		return int8Value;
//	}
//	public void setInt8Value(long int8Value) {
//		this.int8Value = int8Value;
//	}
	public short getSmallIntValue() {
		return smallIntValue;
	}
	public void setSmallIntValue(short smallIntValue) {
		this.smallIntValue = smallIntValue;
	}
//	public byte getTinyintValue() {
//		return tinyintValue;
//	}
//	public void setTinyintValue(byte tinyintValue) {
//		this.tinyintValue = tinyintValue;
//	}
	public BigDecimal getDecimalValue() {
		return decimalValue;
	}
	public void setDecimalValue(BigDecimal decimalValue) {
		this.decimalValue = decimalValue;
	}
//	public BigDecimal getNumericValue() {
//		return numericValue;
//	}
//	public void setNumericValue(BigDecimal numericValue) {
//		this.numericValue = numericValue;
//	}
//	public BigInteger getNumberValue() {
//		return numberValue;
//	}
//	public void setNumberValue(BigInteger numberValue) {
//		this.numberValue = numberValue;
//	}
//	public BigDecimal getMoneyValue() {
//		return moneyValue;
//	}
//	public void setMoneyValue(BigDecimal moneyValue) {
//		this.moneyValue = moneyValue;
//	}
//	public Point getGeographicValue() {
//		return geographicValue;
//	}
//	public void setGeographicValue(Point geographicValue) {
//		this.geographicValue = geographicValue;
//	}
	public OffsetDateTime getTimestampWithTimezone() {
		return timestampWithTimezone;
	}
	public void setTimestampWithTimezone(OffsetDateTime timestampWithTimezone) {
		this.timestampWithTimezone = timestampWithTimezone;
	}

	

	
	
	
	
	
	
	

}
