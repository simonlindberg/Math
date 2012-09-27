package expressions;

public class Number implements Expression {
	private double number;

	public Number(double number) {
		this.number = number;
	}

	public String toString() {
		return number + "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(number);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Number other = (Number) obj;
		if (Double.doubleToLongBits(number) != Double
				.doubleToLongBits(other.number))
			return false;
		return true;
	}

	public double calculate() {
		return number;
	}
}