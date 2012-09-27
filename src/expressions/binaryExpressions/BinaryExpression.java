package expressions.binaryExpressions;

import expressions.Expression;

public abstract class BinaryExpression implements Expression {
	protected Expression a, b;
	protected String sign;

	public BinaryExpression(Expression a, Expression b, String sign) {
		this.a = a;
		this.b = b;
		this.sign = sign;
	}

	public String toString() {
		if (b.equals(Expression.NULL)) {
			return a.toString();
		}
		return "(" + a + sign + b + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		result = prime * result + sign.hashCode();
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
		BinaryExpression other = (BinaryExpression) obj;
		if (a == null) {
			if (other.a != null) {
				return false;
			}
		} else if (!a.equals(other.a))
			return false;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		return true;
	}
}
