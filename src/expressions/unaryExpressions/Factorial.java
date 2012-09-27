package expressions.unaryExpressions;

import expressions.Expression;
import expressions.ExpressionException;

public class Factorial extends UnaryExpression {

	public Factorial(Expression expr) {
		super(expr);
	}

	public String toString() {
		return expr.toString() + "!";
	}

	public double calculate() throws ExpressionException {
		long temp = (long) expr.calculate();
		for (long i = temp - 1; i > 1; i--) {
			temp *= i;
		}
		return temp;
	}

}
