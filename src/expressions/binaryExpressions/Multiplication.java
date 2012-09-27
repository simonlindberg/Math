package expressions.binaryExpressions;

import expressions.Expression;
import expressions.ExpressionException;

public class Multiplication extends BinaryExpression {

	public Multiplication(Expression a, Expression b) {
		super(a, b, "*");
	}

	@Override
	public double calculate() throws ExpressionException {
		double temp1 = a.calculate();
		double temp2 = b.calculate();
		return temp1 * temp2;
	}
}
