package expressions;

public interface Expression {
	public String toString();

	/**
	 * 
	 * Calulates the value of the given Expression. OBS. Cannot be used if there
	 * is a constant involved, this will cause an IllegalArgumentException to be
	 * thrown.
	 * 
	 * @return the value of the expression.
	 * @throws ExpressionException
	 */
	public double calculate() throws ExpressionException;

	public static final Expression NULL = new Expression() {
		public String toString() {
			return "NULL";
		}

		public double calculate() throws ExpressionException {
			throw new ExpressionException("Cannot calculate null values");
		}
	};
}
