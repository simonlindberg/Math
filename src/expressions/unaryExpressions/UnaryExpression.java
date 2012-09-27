package expressions.unaryExpressions;

import expressions.Expression;

public abstract class UnaryExpression implements Expression {
	protected Expression expr;

	public UnaryExpression(Expression expr) {
		this.expr = expr;
	}
}
