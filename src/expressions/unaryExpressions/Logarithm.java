package expressions.unaryExpressions;

import expressions.Expression;
import expressions.Number;

public abstract class Logarithm extends UnaryExpression {
	private String name;

	public Logarithm(Expression expr, String name) {
		super(expr);
		this.name = name;
	}

	public String toString() {
		String temp = "(" + name;
		temp += expr instanceof Number ? " " : "";
		temp += expr.toString() + ")";
		return temp;
	}

}
