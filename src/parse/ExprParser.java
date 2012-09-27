package parse;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

import expressions.Expression;
import expressions.ExpressionException;
import expressions.Number;
import expressions.binaryExpressions.Addition;
import expressions.binaryExpressions.Division;
import expressions.binaryExpressions.Exponential;
import expressions.binaryExpressions.Multiplication;
import expressions.binaryExpressions.Subtraction;
import expressions.unaryExpressions.CubeRoot;
import expressions.unaryExpressions.Factorial;
import expressions.unaryExpressions.Ln;
import expressions.unaryExpressions.Log;
import expressions.unaryExpressions.Negation;
import expressions.unaryExpressions.Sqrt;
import expressions.unaryExpressions.trigonometrics.Cosecant;
import expressions.unaryExpressions.trigonometrics.Cosine;
import expressions.unaryExpressions.trigonometrics.Cotangent;
import expressions.unaryExpressions.trigonometrics.Secant;
import expressions.unaryExpressions.trigonometrics.Sine;
import expressions.unaryExpressions.trigonometrics.Tangent;

public class ExprParser extends StreamTokenizer implements InputConstants {

    /*
     * GRAMMAR, the parser follows the following rules.
     * 
     * expression ::= (unaryop)* expr
     * 
     * expr ::= term (addop term)*
     * 
     * term ::= factor (mulop factor)*
     * 
     * factor ::= exp (Ô^' exp)*
     * 
     * exp ::= number | '(' factorial ')'
     * 
     * factorial ::= expression(!)?
     * 
     * addop ::= '+' | '-' |
     * 
     * mulop ::= '*' | '/' |
     * 
     * unaryop ::= '-'|'ln'|'log'|'sqrt'|'cube'|'cos'|'tan'|
     * 'csc'|'cot'|'sec'|'sin'
     */

    private int token;

    public ExprParser(String input) throws IOException {
	super(new StringReader(input));
	ordinaryChar('/');
	ordinaryChar('-');
	token = nextToken();
    }

    public Expression build() throws IOException {
	return expression();
    }

    private Expression expression() throws IOException {
	Expression expr = expr();
	while (tokenIsValidUnaryOp()) {
	    int op = token;
	    String tempString = sval;
	    token = nextToken();
	    if (op == TT_WORD) {
		if (tempString.equals("ln")) {
		    expr = new Ln(expr());
		} else if (tempString.equals("log")) {
		    expr = new Log(expr());
		} else if (tempString.equals("sqrt")) {
		    expr = new Sqrt(expr());
		} else if (tempString.equals("cube")) {
		    expr = new CubeRoot(expr());
		} else if (tempString.equals("sin")) {
		    expr = new Sine(expr());
		} else if (tempString.equals("cos")) {
		    expr = new Cosine(expr());
		} else if (tempString.equals("tan")) {
		    expr = new Tangent(expr());
		} else if (tempString.equals("csc")) {
		    expr = new Cosecant(expr());
		} else if (tempString.equals("sec")) {
		    expr = new Secant(expr());
		} else if (tempString.equals("cot")) {
		    expr = new Cotangent(expr());
		} else {

		}
	    }
	}
	return expr;
    }

    private Expression expr() throws IOException {
	Expression term1, term2;
	term1 = term();
	while (token == '-' || token == '+') {
	    int op = token;
	    token = nextToken();
	    term2 = expression();
	    switch (op) {
	    case '-':
		term1 = new Subtraction(term1, term2);
		break;
	    case '+':
		term1 = new Addition(term1, term2);
		break;
	    }
	}
	return term1;
    }

    private Expression term() throws IOException {
	Expression result, factor;
	result = factor();
	while (token == '*' || token == '/') {
	    int op = token;
	    token = nextToken();
	    factor = expression();
	    switch (op) {
	    case '*':
		result = new Multiplication(result, factor);
		break;
	    case '/':
		result = new Division(result, factor);
		break;
	    }
	}
	return result;
    }

    private Expression factor() throws IOException {
	Expression base, expon;
	base = exp();
	while (token == '^') {
	    int op = token;
	    token = nextToken();
	    expon = expression();
	    switch (op) {
	    case '^':
		base = new Exponential(base, expon);
		break;
	    }
	}
	return base;
    }

    private Expression exp() throws IOException {
	Expression expr;
	switch (token) {
	case '(':
	    token = nextToken();
	    expr = factorial();
	    return expr;
	case TT_NUMBER:
	    double tempVal = 0;
	    try {
		tempVal = theNumber();
	    } catch (ExpressionException e) {
		return Expression.NULL;
	    }
	    expr = new Number(tempVal);
	    while (token == '!') {
		expr = new Factorial(expr);
		token = nextToken();
	    }
	    return expr;
	case '-':
	    token = nextToken();
	    return new Negation(exp());
	}
	return Expression.NULL;
    }

    private Expression factorial() throws IOException {
	Expression expr = expression();
	token = nextToken();
	if (token == '!') {
	    return new Factorial(expr);
	}
	return expr;
    }

    private double theNumber() throws IOException, ExpressionException {
	double tempVal = nval;
	token = nextToken();
	if (token == TT_WORD) {
	    if (sval.matches("E\\d+")) {
		sval = sval.replace("E", "");
		nval = Double.parseDouble(sval);
		tempVal = tempVal * Math.pow(10, nval);
	    } else {
		throw new ExpressionException("");
	    }
	}
	return tempVal;
    }

    private boolean tokenIsValidUnaryOp() {
	if (token == TT_WORD) {
	    for (String valid : validUnaryOp) {
		if (sval.equals(valid)) {
		    return true;
		}
	    }
	}
	return false;
    }

    public static String[] validUnaryInputs() {
	return validUnaryOp;
    }
}