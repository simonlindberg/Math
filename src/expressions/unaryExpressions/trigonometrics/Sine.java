package expressions.unaryExpressions.trigonometrics;

import expressions.Expression;
import expressions.ExpressionException;

public class Sine extends TrigonometricFunction {

	public Sine(Expression expr) {
		super(expr, "sin");
	}

	@Override
	public double calculate() throws ExpressionException {
		double temp = expr.calculate() % (2 * Math.PI);
		if (temp == Math.PI) {
			return 0;
		}
		if (temp == Math.PI / 2) {
			return 1;
		}
		if (temp == Math.PI / 3) {
			return Math.sqrt(3) / 2;
		}
		if (temp == Math.PI / 4) {
			return Math.sqrt(2) / 2;
		}
		if (temp == (Math.PI / 6)) {
			return 0.5;
		}
		if (temp == (3 * Math.PI / 2)) {
			return -1;
		}
		return Math.sin(temp);
	}
}