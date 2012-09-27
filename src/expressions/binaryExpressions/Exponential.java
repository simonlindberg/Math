package expressions.binaryExpressions;

import expressions.Expression;
import expressions.ExpressionException;

public class Exponential extends BinaryExpression {
	public Exponential(Expression base, Expression exp) {
		super(base, exp, "^");
	}

	@Override
	public double calculate() throws ExpressionException {
		double temp1 = a.calculate();
		double temp2 = b.calculate();
		return Math.pow(temp1, temp2);
	}
}
