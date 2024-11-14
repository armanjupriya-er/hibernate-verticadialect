package org.hibernate.dialect;

import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.query.sqm.FetchClauseType;
import org.hibernate.sql.ast.spi.StandardSqlAstTranslator;
import org.hibernate.sql.ast.tree.Statement;
import org.hibernate.sql.ast.tree.expression.Expression;
import org.hibernate.sql.exec.spi.JdbcOperation;

public class CustomVerticaSqlAstTranslator<T extends JdbcOperation>
        extends StandardSqlAstTranslator<T> {

    public CustomVerticaSqlAstTranslator(
            SessionFactoryImplementor sessionFactory, Statement statement) {
        super(sessionFactory, statement);
    }

    @Override
    protected void renderOffsetFetchClause(
            Expression offsetExpression,
            Expression fetchExpression,
            FetchClauseType fetchClauseType,
            boolean renderOffsetRowsKeyword) {
        if (fetchExpression != null) {
            renderFetch(fetchExpression, null, fetchClauseType);
        }

        if (offsetExpression != null) {
            renderOffset(offsetExpression, false);
        }
    }

    @Override
    protected void renderFetch(
            Expression fetchExpression,
            Expression offsetExpressionToAdd,
            FetchClauseType fetchClauseType) {
        appendSql(" LIMIT ");
        renderFetchExpression(fetchExpression);
    }
}