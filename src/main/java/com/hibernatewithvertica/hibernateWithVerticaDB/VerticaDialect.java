package com.hibernatewithvertica.hibernateWithVerticaDB;

import java.sql.Types;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.CommonFunctionFactory;
import org.hibernate.query.sqm.function.SqmFunctionRegistry;
import org.hibernate.type.descriptor.jdbc.JdbcType;
import org.hibernate.type.descriptor.jdbc.spi.JdbcTypeRegistry;
import org.hibernate.type.spi.TypeConfiguration;

public class VerticaDialect extends Dialect {

	private static final DatabaseVersion MINIMUM_VERSION = DatabaseVersion.make(24);
	private final SqmFunctionRegistry functionRegistry;

	public VerticaDialect() {
		super(MINIMUM_VERSION);
		this.functionRegistry = new SqmFunctionRegistry();

		registerNumericTypeMappings();
		registerDateTimeTypeMappings();
		registerLargeObjectTypeMappings();

		registerOtherTypeMappings();

//		registerReverseHibernateTypeMappings();

//		registerFunctions();

//		registerDefaultProperties();
	}

	@Override
	public JdbcType resolveSqlTypeDescriptor(String columnTypeName, int jdbcTypeCode, int precision, int scale,
			JdbcTypeRegistry jdbcTypeRegistry) {

//		switch (jdbcTypeCode) {
//		case Types.VARCHAR -> jdbcTypeCode = StandardBasicTypes.STRING.getSqlTypeCode();
//		// ETC
//		}

		return super.resolveSqlTypeDescriptor(columnTypeName, jdbcTypeCode, precision, scale, jdbcTypeRegistry);
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
			return "float4";
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
		case Types.OTHER:
			return "varchar($l)";
		case Types.BIT:
			return "bool";
		default:
			return super.columnType(sqlTypeCode);
		}
	}

	protected void registerNumericTypeMappings() {

	}

	protected void registerDateTimeTypeMappings() {

	}

	protected void registerLargeObjectTypeMappings() {

	}

	protected void registerOtherTypeMappings() {

	}

	@Override
	public void initializeFunctionRegistry(FunctionContributions functionContributions) {
		super.initializeFunctionRegistry(functionContributions);
		final TypeConfiguration typeConfiguration = functionContributions.getTypeConfiguration();

		CommonFunctionFactory functionFactory = new CommonFunctionFactory(functionContributions);
//		functionRegistry.namedDescriptorBuilder( "radians" )
//		.setExactArgumentCount( 1 )
//		.setParameterTypes(NUMERIC)
//		.setInvariantType(doubleType)
//		.register();
	}
//	public String getBasicSelectClauseNullString(int sqlType) {
//		return super.getSelectClauseNullString( sqlType );
//	}

	public String getSelectClauseNullString(int sqlType) {
		switch (sqlType) {
		case Types.VARCHAR:
		case Types.CHAR:
			return "to_char(null)";
		case Types.DATE:
		case Types.TIMESTAMP:
		case Types.TIME:
			return "to_date(null)";
		default:
			return "to_number(null)";
		}
	}
//    @Override
//    public boolean bindLimitParametersInReverseOrder()
//    {
//        return true;
//    }

	@Override
	public boolean dropConstraints() {
		return false;
	}

	@Override
	public String getAddColumnString() {
		return "add column";
	}

	@Override
	public String getCascadeConstraintsString() {
		return " cascade";
	}

//    @Override
//    public String getCreateSequenceString( String sequenceName )
//    {
//        return "create sequence " + sequenceName; // starts with 1, implicitly
//    }

	@Override
	public String getCurrentTimestampSelectString() {
		return "select now()";
	}

//    @Override
//    public String getDropSequenceString( String sequenceName )
//    {
//        return "drop sequence " + sequenceName;
//    }

	@Override
	public String getForUpdateString(String aliases) {
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

	@Override
	public String getNoColumnsInsertString() {
		return "default values";
	}

	@Override
	public String getQuerySequencesString() {
		return "select sequence_name from sequences";
	}

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
		return false;
	}

	@Override
	public boolean supportsCommentOn() {
		return true;
	}

	@Override
	public boolean supportsCurrentTimestampSelection() {
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
		return false;
	}

	@Override
	public boolean supportsUnboundedLobLocatorMaterialization() {
		return false;
	}

	@Override
	public boolean supportsUnionAll() {
		return true;
	}

	@Override
	public String toBooleanValueString(boolean bool) {
		return bool ? "true" : "false";
	}

	@Override
	public boolean useInputStreamToInsertBlob() {
		return false;
	}

}
