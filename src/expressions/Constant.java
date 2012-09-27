package expressions;

public class Constant implements Expression {
	public static final Number e = new Number(Math.E);
	public static final Number PI = new Number(Math.PI);
	public static final Number PHI = new Number((Math.sqrt(5) + 1) / 2);
	private String constant;
	private Expression expr;

	public Constant(String name, Expression expr) {
		this.constant = name;
		this.expr = expr;
	}

	public String toString() {
		try {
			return constant + " = " + expr.calculate();
		} catch (ExpressionException e) {
			return constant + " = " + expr.toString();
		}
	}

	public String getName() {
		return constant;
	}

	public void setExpression(Expression expr) {
		this.expr = expr;
	}

	public double calculate() throws ExpressionException {
		return expr.calculate();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((constant == null) ? 0 : constant.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Constant other = (Constant) obj;
		if (constant == null) {
			if (other.constant != null)
				return false;
		} else if (!constant.equals(other.constant))
			return false;
		return true;
	}
}
