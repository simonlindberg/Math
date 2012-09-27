package expressions.unaryExpressions.trigonometrics;

import expressions.Expression;

import expressions.Number;
import expressions.unaryExpressions.UnaryExpression;

public abstract class TrigonometricFunction extends UnaryExpression {
	private String name;

	public TrigonometricFunction(Expression expr, String name) {
		super(expr);
		this.name = name;
	}

	public String toString() {
		if (expr instanceof Number) {
			return name + "(" + expr + ")";
		}
		return name + expr;
	}
}
