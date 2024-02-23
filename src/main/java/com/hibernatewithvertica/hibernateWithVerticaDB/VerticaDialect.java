package com.hibernatewithvertica.hibernateWithVerticaDB;


import static org.hibernate.query.sqm.TemporalUnit.DAY;
import static org.hibernate.query.sqm.TemporalUnit.EPOCH;
import static org.hibernate.type.SqlTypes.ARRAY;
import static org.hibernate.type.SqlTypes.BINARY;
import static org.hibernate.type.SqlTypes.CHAR;
import static org.hibernate.type.SqlTypes.FLOAT;
import static org.hibernate.type.SqlTypes.GEOGRAPHY;
import static org.hibernate.type.SqlTypes.GEOMETRY;
import static org.hibernate.type.SqlTypes.INET;
import static org.hibernate.type.SqlTypes.JSON;
import static org.hibernate.type.SqlTypes.LONG32NVARCHAR;
import static org.hibernate.type.SqlTypes.LONG32VARBINARY;
import static org.hibernate.type.SqlTypes.LONG32VARCHAR;
import static org.hibernate.type.SqlTypes.NCHAR;
import static org.hibernate.type.SqlTypes.NVARCHAR;
import static org.hibernate.type.SqlTypes.OTHER;
import static org.hibernate.type.SqlTypes.SQLXML;
import static org.hibernate.type.SqlTypes.STRUCT;
import static org.hibernate.type.SqlTypes.TIME;
import static org.hibernate.type.SqlTypes.TIMESTAMP;
import static org.hibernate.type.SqlTypes.TIMESTAMP_UTC;
import static org.hibernate.type.SqlTypes.TIME_UTC;
import static org.hibernate.type.SqlTypes.UUID;
import static org.hibernate.type.SqlTypes.VARBINARY;
import static org.hibernate.type.SqlTypes.VARCHAR;
import static org.hibernate.type.descriptor.DateTimeUtils.appendAsDate;
import static org.hibernate.type.descriptor.DateTimeUtils.appendAsLocalTime;
import static org.hibernate.type.descriptor.DateTimeUtils.appendAsTime;
import static org.hibernate.type.descriptor.DateTimeUtils.appendAsTimestampWithMicros;
import static org.hibernate.type.descriptor.DateTimeUtils.appendAsTimestampWithMillis;

import java.sql.Types;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.boot.model.TypeContributions;
import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.OracleServerConfiguration;
import org.hibernate.dialect.PostgreSQLDriverKind;
import org.hibernate.dialect.function.CommonFunctionFactory;
import org.hibernate.dialect.function.ModeStatsModeEmulation;
import org.hibernate.dialect.function.OracleTruncFunction;
import org.hibernate.dialect.sequence.PostgreSQLSequenceSupport;
import org.hibernate.dialect.sequence.SequenceSupport;
import org.hibernate.engine.jdbc.dialect.internal.StandardDialectResolver;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolutionInfo;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolver;
import org.hibernate.query.SemanticException;
import org.hibernate.query.sqm.TemporalUnit;
import org.hibernate.query.sqm.function.SqmFunctionRegistry;
import org.hibernate.query.sqm.produce.function.FunctionParameterType;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.sql.ast.spi.SqlAppender;
import org.hibernate.tool.schema.extract.spi.ColumnTypeInformation;
import org.hibernate.type.BasicTypeRegistry;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.descriptor.jdbc.AggregateJdbcType;
import org.hibernate.type.descriptor.jdbc.JdbcType;
import org.hibernate.type.descriptor.jdbc.JdbcTypeConstructor;
import org.hibernate.type.descriptor.jdbc.spi.JdbcTypeRegistry;
import org.hibernate.type.descriptor.sql.internal.ArrayDdlTypeImpl;
import org.hibernate.type.descriptor.sql.internal.CapacityDependentDdlType;
import org.hibernate.type.descriptor.sql.internal.DdlTypeImpl;
import org.hibernate.type.descriptor.sql.internal.NamedNativeEnumDdlTypeImpl;
import org.hibernate.type.descriptor.sql.internal.Scale6IntervalSecondDdlType;
import org.hibernate.type.descriptor.sql.spi.DdlTypeRegistry;
import org.hibernate.type.spi.TypeConfiguration;

import jakarta.persistence.TemporalType;

public class VerticaDialect extends Dialect {

	private static final DatabaseVersion MINIMUM_VERSION = DatabaseVersion.make(24);
//	// Is it an Autonomous Database Cloud Service?
//	protected final boolean autonomous;
//
//	// Is MAX_STRING_SIZE set to EXTENDED?
//	protected final boolean extended;
	
	public VerticaDialect() {
		this( MINIMUM_VERSION );
	}
	
	public VerticaDialect(DatabaseVersion version) {
		super(version);

	}

	
	@Override
	protected DatabaseVersion getMinimumSupportedVersion() {
		return MINIMUM_VERSION;
	}

	@Override
	protected String columnType(int sqlTypeCode) {
		switch (sqlTypeCode) {
		case Types.CHAR:
			return "char(1)";
		case Types.VARCHAR:
			return "varchar2($l)";
		case Types.BIGINT:
		case Types.SMALLINT:
		case Types.TINYINT:
		case Types.INTEGER:
			return "int";
		case Types.FLOAT:
			return "float";
		case Types.DOUBLE:
			return "float8";
		case Types.NUMERIC:
			return "numeric($p,$s)";
		case Types.DECIMAL:
			return "numeric($p,$s)";
		case Types.DATE:
			return "date";
			
		case Types.TIME:
			return "time";
		case Types.TIMESTAMP:
			return "timestamp";
		case Types.BINARY:
			return "binary"; 
		case Types.LONGVARBINARY:
			return "LongVarBinary";
		case Types.LONGVARCHAR:
			return "LongVarchar";
		case Types.TIME_WITH_TIMEZONE:
			return "TimeTz";
		case Types.TIMESTAMP_WITH_TIMEZONE:
			return "TimestampTz";
		
		case Types.OTHER:
			return "varchar($l)";
		case Types.BIT:
			return "bool";
		default:
			return super.columnType(sqlTypeCode);
		}
	}

	@Override
	protected String castType(int sqlTypeCode) {
		switch ( sqlTypeCode ) {
			case CHAR:
			case NCHAR:
			case VARCHAR:
			case NVARCHAR:
			case LONG32VARCHAR:
			case LONG32NVARCHAR:
				return "text";
			case BINARY:
			case VARBINARY:
			case LONG32VARBINARY:
				return "bytea";
		}
		return super.castType( sqlTypeCode );
	}
	@Override
	protected Integer resolveSqlTypeCode(String columnTypeName, TypeConfiguration typeConfiguration) {
		switch ( columnTypeName ) {
			case "bool":
				return Types.BOOLEAN;
			case "float4":
				// Use REAL instead of FLOAT to get Float as recommended Java type
				return Types.REAL;
			case "float8":
				return Types.DOUBLE;
			case "int2":
				return Types.SMALLINT;
			case "int4":
				return Types.INTEGER;
			case "int8":
				return Types.BIGINT;
		}
		return super.resolveSqlTypeCode( columnTypeName, typeConfiguration );
	}
	@Override
	protected void registerColumnTypes(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
		super.registerColumnTypes( typeContributions, serviceRegistry );
		final DdlTypeRegistry ddlTypeRegistry = typeContributions.getTypeConfiguration().getDdlTypeRegistry();

		// We need to configure that the array type uses the raw element type for casts
		ddlTypeRegistry.addDescriptor( new ArrayDdlTypeImpl( this, true ) );

		// Register this type to be able to support Float[]
		// The issue is that the JDBC driver can't handle createArrayOf( "float(24)", ... )
		// It requires the use of "real" or "float4"
		// Alternatively we could introduce a new API in Dialect for creating such base names
		ddlTypeRegistry.addDescriptor(
				CapacityDependentDdlType.builder( FLOAT, columnType( FLOAT ), castType( FLOAT ), this )
						.withTypeCapacity( 24, "float4" )
						.build()
		);

		ddlTypeRegistry.addDescriptor( new DdlTypeImpl( SQLXML, "xml", this ) );
		ddlTypeRegistry.addDescriptor( new DdlTypeImpl( UUID, "uuid", this ) );
		ddlTypeRegistry.addDescriptor( new DdlTypeImpl( INET, "inet", this ) );
		ddlTypeRegistry.addDescriptor( new DdlTypeImpl( GEOMETRY, "geometry", this ) );
		ddlTypeRegistry.addDescriptor( new DdlTypeImpl( GEOGRAPHY, "geography", this ) );
		ddlTypeRegistry.addDescriptor( new Scale6IntervalSecondDdlType( this ) );

		// Prefer jsonb if possible
		ddlTypeRegistry.addDescriptor( new DdlTypeImpl( JSON, "jsonb", this ) );

		ddlTypeRegistry.addDescriptor( new NamedNativeEnumDdlTypeImpl( this ) );
	}

	@Override
	public String timestampdiffPattern(TemporalUnit unit, TemporalType fromTemporalType, TemporalType toTemporalType) {
		if ( unit == null ) {
			return "(?3-?2)";
		}
		if ( toTemporalType == TemporalType.DATE && fromTemporalType == TemporalType.DATE ) {
			// special case: subtraction of two dates
			// results in an integer number of days
			// instead of an INTERVAL
			switch ( unit ) {
				case YEAR:
				case MONTH:
				case QUARTER:
					return "extract(" + translateDurationField( unit ) + " from age(?3,?2))";
				default:
					return "(?3-?2)" + DAY.conversionFactor( unit, this );
			}
		}
		else {
			switch ( unit ) {
				case YEAR:
					return "extract(year from ?3-?2)";
				case QUARTER:
					return "(extract(year from ?3-?2)*4+extract(month from ?3-?2)/3)";
				case MONTH:
					return "(extract(year from ?3-?2)*12+extract(month from ?3-?2))";
				case WEEK: //week is not supported by extract() when the argument is a duration
					return "(extract(day from ?3-?2)/7)";
				case DAY:
					return "extract(day from ?3-?2)";
				//in order to avoid multiple calls to extract(),
				//we use extract(epoch from x - y) * factor for
				//all the following units:
				case HOUR:
				case MINUTE:
				case SECOND:
				case NANOSECOND:
				case NATIVE:
					return "extract(epoch from ?3-?2)" + EPOCH.conversionFactor( unit, this );
				default:
					throw new SemanticException( "Unrecognized field: " + unit );
			}
		}
	}
	@Override
	public void appendDateTimeLiteral(
			SqlAppender appender,
			TemporalAccessor temporalAccessor,
			TemporalType precision,
			TimeZone jdbcTimeZone) {
		switch ( precision ) {
			case DATE:
				appender.appendSql( "date '" );
				appendAsDate( appender, temporalAccessor );
				appender.appendSql( '\'' );
				break;
			case TIME:
				if ( supportsTemporalLiteralOffset() && temporalAccessor.isSupported( ChronoField.OFFSET_SECONDS ) ) {
					appender.appendSql( "time with time zone '" );
					appendAsTime( appender, temporalAccessor, true, jdbcTimeZone );
				}
				else {
					appender.appendSql( "time '" );
					appendAsLocalTime( appender, temporalAccessor );
				}
				appender.appendSql( '\'' );
				break;
			case TIMESTAMP:
				if ( supportsTemporalLiteralOffset() && temporalAccessor.isSupported( ChronoField.OFFSET_SECONDS ) ) {
					appender.appendSql( "timestamp with time zone '" );
					appendAsTimestampWithMicros( appender, temporalAccessor, true, jdbcTimeZone );
					appender.appendSql( '\'' );
				}
				else {
					appender.appendSql( "timestamp '" );
					appendAsTimestampWithMicros( appender, temporalAccessor, false, jdbcTimeZone );
					appender.appendSql( '\'' );
				}
				break;
			default:
				throw new IllegalArgumentException();
		}
	}

	@Override
	public void appendDateTimeLiteral(SqlAppender appender, Date date, TemporalType precision, TimeZone jdbcTimeZone) {
		switch ( precision ) {
			case DATE:
				appender.appendSql( "date '" );
				appendAsDate( appender, date );
				appender.appendSql( '\'' );
				break;
			case TIME:
				appender.appendSql( "time with time zone '" );
				appendAsTime( appender, date, jdbcTimeZone );
				appender.appendSql( '\'' );
				break;
			case TIMESTAMP:
				appender.appendSql( "timestamp with time zone '" );
				appendAsTimestampWithMicros( appender, date, jdbcTimeZone );
				appender.appendSql( '\'' );
				break;
			default:
				throw new IllegalArgumentException();
		}
	}

	@Override
	public void appendDateTimeLiteral(
			SqlAppender appender,
			Calendar calendar,
			TemporalType precision,
			TimeZone jdbcTimeZone) {
		switch ( precision ) {
			case DATE:
				appender.appendSql( "date '" );
				appendAsDate( appender, calendar );
				appender.appendSql( '\'' );
				break;
			case TIME:
				appender.appendSql( "time with time zone '" );
				appendAsTime( appender, calendar, jdbcTimeZone );
				appender.appendSql( '\'' );
				break;
			case TIMESTAMP:
				appender.appendSql( "timestamp with time zone '" );
				appendAsTimestampWithMillis( appender, calendar, jdbcTimeZone );
				appender.appendSql( '\'' );
				break;
			default:
				throw new IllegalArgumentException();
		}
	}

	@Override
	public void initializeFunctionRegistry(FunctionContributions functionContributions) {
		System.out.println("130");
		super.initializeFunctionRegistry(functionContributions);
		SqmFunctionRegistry functionRegistry = functionContributions.getFunctionRegistry();
		TypeConfiguration typeConfiguration = functionContributions.getTypeConfiguration();

		BasicTypeRegistry basicTypeRegistry = typeConfiguration.getBasicTypeRegistry();
		CommonFunctionFactory functionFactory = new CommonFunctionFactory(functionContributions);
		functionFactory.math();
		functionFactory.trigonometry();
		functionFactory.stddev();
		functionFactory.variance();
		functionFactory.round();
		functionFactory.initcap(); 
		functionFactory.ascii();
		functionFactory.toCharNumberDateTimestamp();
		functionFactory.nowCurdateCurtime();
		functionFactory.lastDay();
		functionFactory.sysdate();
		functionFactory.concat();
		functionFactory.instr();
		functionFactory.replace();
		functionFactory.pad();
		functionFactory.substring_substr();
		functionFactory.translate();
		functionFactory.bitLength();
		functionFactory.coalesce();
		functionFactory.log();
		functionFactory.addMonths();
		functionFactory.ceiling_ceil();
		functionFactory.monthsBetween();
		functionFactory.char_chr();
		functionFactory.initcap();
		functionFactory.lowerUpper();
		functionFactory.trim2();
		functionFactory.substr();

		functionContributions.getFunctionRegistry().registerBinaryTernaryPattern(
				"locate",
				typeConfiguration.getBasicTypeRegistry().resolve( StandardBasicTypes.INTEGER ),
				"instr(?2,?1)",
				"instr(?2,?1,?3)",
				FunctionParameterType.STRING, FunctionParameterType.STRING, FunctionParameterType.INTEGER,
				typeConfiguration
		).setArgumentListSignature("(pattern, string[, start])");
		// The within group clause became optional in 18
		functionFactory.listagg( null );
		functionFactory.windowFunctions();
		functionFactory.hypotheticalOrderedSetAggregates();
		functionFactory.inverseDistributionOrderedSetAggregates();
		// Oracle has a regular aggregate function named stats_mode
		functionContributions.getFunctionRegistry().register(
				"mode",
				new ModeStatsModeEmulation( typeConfiguration )
		);
		functionContributions.getFunctionRegistry().register(
				"trunc",
				new OracleTruncFunction( functionContributions.getTypeConfiguration() )
		);
		functionContributions.getFunctionRegistry().registerAlternateKey( "truncate", "trunc" );

		functionFactory.array_oracle();
		functionFactory.arrayAggregate_jsonArrayagg();
		functionFactory.arrayPosition_oracle();
		functionFactory.arrayPositions_oracle();
		functionFactory.arrayLength_oracle();
		functionFactory.arrayConcat_oracle();
		functionFactory.arrayPrepend_oracle();
		functionFactory.arrayAppend_oracle();
		functionFactory.arrayContains_oracle();
		functionFactory.arrayOverlaps_oracle();
		functionFactory.arrayGet_oracle();
		functionFactory.arraySet_oracle();
		functionFactory.arrayRemove_oracle();
		functionFactory.arrayRemoveIndex_oracle();
		functionFactory.arraySlice_oracle();
		functionFactory.arrayReplace_oracle();
		functionFactory.arrayTrim_oracle();
		functionFactory.arrayFill_oracle();
		functionFactory.arrayToString_oracle();
	}
	
	@Override
	public JdbcType resolveSqlTypeDescriptor(
			String columnTypeName,
			int jdbcTypeCode,
			int precision,
			int scale,
			JdbcTypeRegistry jdbcTypeRegistry) {
		switch ( jdbcTypeCode ) {
			case OTHER:
				switch ( columnTypeName ) {
					case "uuid":
						jdbcTypeCode = UUID;
						break;
					case "json":
					case "jsonb":
						jdbcTypeCode = JSON;
						break;
					case "xml":
						jdbcTypeCode = SQLXML;
						break;
					case "inet":
						jdbcTypeCode = INET;
						break;
					case "geometry":
						jdbcTypeCode = GEOMETRY;
						break;
					case "geography":
						jdbcTypeCode = GEOGRAPHY;
						break;
				}
				break;
			case TIME:
				// The PostgreSQL JDBC driver reports TIME for timetz, but we use it only for mapping OffsetTime to UTC
				if ( "timetz".equals( columnTypeName ) ) {
					jdbcTypeCode = TIME_UTC;
				}
				break;
			case TIMESTAMP:
				// The PostgreSQL JDBC driver reports TIMESTAMP for timestamptz, but we use it only for mapping Instant
				if ( "timestamptz".equals( columnTypeName ) ) {
					jdbcTypeCode = TIMESTAMP_UTC;
				}
				break;
			case ARRAY:
				final JdbcTypeConstructor jdbcTypeConstructor = jdbcTypeRegistry.getConstructor( jdbcTypeCode );
				// PostgreSQL names array types by prepending an underscore to the base name
				if ( jdbcTypeConstructor != null && columnTypeName.charAt( 0 ) == '_' ) {
					final String componentTypeName = columnTypeName.substring( 1 );
					final Integer sqlTypeCode = resolveSqlTypeCode( componentTypeName, jdbcTypeRegistry.getTypeConfiguration() );
					if ( sqlTypeCode != null ) {
						return jdbcTypeConstructor.resolveType(
								jdbcTypeRegistry.getTypeConfiguration(),
								this,
								jdbcTypeRegistry.getDescriptor( sqlTypeCode ),
								ColumnTypeInformation.EMPTY
						);
					}
				}
				break;
			case STRUCT:
				final AggregateJdbcType aggregateDescriptor = jdbcTypeRegistry.findAggregateDescriptor( columnTypeName );
				if ( aggregateDescriptor != null ) {
					return aggregateDescriptor;
				}
				break;
		}
		return jdbcTypeRegistry.getDescriptor( jdbcTypeCode );
	}
	@Override
	public boolean dropConstraints() {
		System.out.println("227");
		return false;
	}

	@Override
	public String getAddColumnString() {
		System.out.println("233");
		return "add column";
	}

	@Override
	public String getCascadeConstraintsString() {
		System.out.println("239");
		return " cascade";
	}

	@Override
	public String getCurrentTimestampSelectString() {
		System.out.println("251");
		return "select now()";
	}
	@Override
	public String getAlterColumnTypeString(String columnName, String columnType, String columnDefinition) {
		// would need multiple statements to 'set not null'/'drop not null', 'set default'/'drop default', 'set generated', etc
		return "alter column " + columnName + " set data type " + columnType;
	}
	@Override
	public String getSelectClauseNullString(int sqlType, TypeConfiguration typeConfiguration) {
		// TODO: adapt this to handle named enum types!
		// Workaround for postgres bug #1453
		return "null::" + typeConfiguration.getDdlTypeRegistry().getDescriptor( sqlType ).getRawTypeName();
	}
//    @Override
//    public String getDropSequenceString( String sequenceName )
//    {
//        return "drop sequence " + sequenceName;
//    }

	@Override
	public String getForUpdateString(String aliases) {
		System.out.println("263");
		return getForUpdateString() + " of " + aliases;
	}

//    @Override
//    public String getIdentityColumnString( int type )
//    {
//        return "not null auto_increment"; // starts with 1, implicitly
//    }

//    @Override
//    public String getIdentitySelectString( String table, String column, int type )
//    {
//        return "select last_insert_id()";
//    }

//    @Override
//    public Class<? extends IdentifierGenerator> getNativeIdentifierGeneratorClass()
//    {
//        return SequenceGenerator.class;
//    }
//	@Override
//	public SequenceSupport getSequenceSupport() {
//		System.out.println("Sequence");
//		return PostgreSQLSequenceSupport.INSTANCE;
//	}

	@Override
	public String getNativeIdentifierGeneratorStrategy() {
		return "sequence";
	}

	@Override
	public String getNoColumnsInsertString() {
		System.out.println("292");
		return "default values";
	}

//	@Override
//	public String getQuerySequencesString() {
//		System.out.println("299");
//		return "select sequence_name from sequences";
//	}

//    @Override
//    public String getSelectSequenceNextValString( String sequenceName )
//    {
//        return "nextval('" + sequenceName + "')";
//    }

//    @Override
//    public String getSequenceNextValString( String sequenceName )
//    {
//        return "select " + getSelectSequenceNextValString( sequenceName );
//    }

//    @Override
//    public boolean hasDataTypeInIdentityColumn()
//    {
//        return false;
//    }

	@Override
	public boolean isCurrentTimestampSelectStringCallable() {
		System.out.println("323");
		return false;
	}

	@Override
	public boolean supportsCommentOn() {
		System.out.println("329");
		return true;
	}

	@Override
	public boolean supportsCurrentTimestampSelection() {
		System.out.println("335");
		return true;
	}

//    @Override
//    public boolean supportsEmptyInList()
//    {
//        return false;
//    }
//
//    @Override
//    public boolean supportsIdentityColumns()
//    {
//        return true;
//    }
//
//    @Override
//    public boolean supportsLimit()
//    {
//        return true;
//    }
//
//    @Override
//    public boolean supportsLobValueChangePropogation()
//    {
//        return false;
//    }
//
//    @Override
//    public boolean supportsPooledSequences()
//    {
//        return true;
//    }

	// Overridden informational metadata ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//    @Override
////    public boolean supportsSequences()
//    {
//        return true;
//    }

	@Override
	public boolean supportsTupleDistinctCounts() {
		System.out.println("379");
		return false;
	}

	@Override
	public boolean supportsUnboundedLobLocatorMaterialization() {
		System.out.println("385");
		return false;
	}

	@Override
	public boolean supportsUnionAll() {
		System.out.println("390");
		return true;
	}

	@Override
	public String toBooleanValueString(boolean bool) {
		System.out.println("397");
		return bool ? "true" : "false";
	}

	@Override
	public boolean useInputStreamToInsertBlob() {
		System.out.println("403");
		return false;
	}
//	public class VerticaDialectSupport implements SequenceSupport {
//
//	    @Override
//	    public String getSelectSequenceNextValString(String sequenceName) {
//	        return "SELECT NEXTVAL('" + sequenceName + "')";
//	    }
//
//	    @Override
//	    public String getSelectSequencePreviousValString(String sequenceName) {
//	        return "SELECT PREVVAL('" + sequenceName + "')";
//	    }
//
//	    @Override
//	    public boolean sometimesNeedsStartingValue() {
//	        // Handle any cases where a starting value is required for sequence operations
//	        return true;
//	    }
//
//	    @Override
//	    public String getDropSequenceString(String sequenceName) {
//	        return "DROP SEQUENCE IF EXISTS " + sequenceName;
//	    }
//
//	    @Override
//	    public String getCreateSequenceString(String sequenceName) {
//	        return "CREATE SEQUENCE " + sequenceName;
//	    }
//	}

}


	
