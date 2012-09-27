package expressions.binaryExpressions;

import expressions.Expression;
import expressions.ExpressionException;

public class Division extends BinaryExpression {

	public Division(Expression a, Expression b) {
		super(a, b, "/");
	}

	@Override
	public double calculate() throws ExpressionException {
		double temp1 = a.calculate();
		double temp2 = b.calculate();
		return temp1 / temp2;
	}

}
