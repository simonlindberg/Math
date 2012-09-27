package expressions.unaryExpressions.trigonometrics;

import expressions.Expression;
import expressions.ExpressionException;

public class Cosine extends TrigonometricFunction {

	public Cosine(Expression expr) {
		super(expr, "cos");
	}

	@Override
	public double calculate() throws ExpressionException {
		double temp = expr.calculate() % (2 * Math.PI);
		if (temp == Math.PI) {
			return -1;
		}
		if (temp == Math.PI / 2) {
			return 1;
		}
		if (temp == Math.PI / 3) {
			return 0.5;
		}
		if (temp == Math.PI / 4) {
			return Math.sqrt(2) / 2;
		}
		if (temp == (Math.PI / 6)) {
			return Math.sqrt(3) / 2;
		}
		if (temp == (Math.PI * 3 / 2)) {
			return 0;
		}
		return Math.cos(temp);
	}
}
