package expressions.binaryExpressions;

import expressions.Expression;
import expressions.ExpressionException;

public class Addition extends BinaryExpression {

	public Addition(Expression term1, Expression term2) {
		super(term1, term2, "+");
	}

	public double calculate() throws ExpressionException {
		double temp1 = a.calculate();
		double temp2 = b.calculate();
		return temp1 + temp2;
	}
}
