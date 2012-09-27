package session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import parse.ExprParser;
import expressions.Constant;
import expressions.Expression;
import expressions.ExpressionException;
import expressions.Function;

public class FunctionCalculater {
    private ExprParser ep;

    private String replaceConstants(String func,
	    Map<String , Constant> constants) throws ExpressionException {
	String calcValue = func;
	for (Constant c : constants.values()) {
	    calcValue = calcValue.replaceAll("\\b" + c.getName() + "\\b", ""
		    + c.calculate());
	}
	return calcValue;
    }

    private String replaceParameters(String function, String entry,
	    ArrayList<String> parameters) throws ExpressionException {
	ArrayList<Expression> exprs = getInputParameters(entry);
	if (exprs.size() != parameters.size()) {
	    throw new ExpressionException("");
	}
	for (int i = 0; i < parameters.size(); i++) {
	    try {
		function = function.replaceAll("\\b" + parameters.get(i)
			+ "\\b", "" + exprs.get(i).calculate());
	    } catch (ExpressionException e) {
	    }
	}
	return function;
    }

    private ArrayList<Expression> getInputParameters(String string) {
	ArrayList<Expression> temp = new ArrayList<Expression>();
	String[] t = string.split(",");
	if (t.length > 1) {
	    for (String par : t) {
		temp.add(findExpr(par));
	    }
	} else {
	    temp.add(findExpr(string));
	}
	return temp;
    }

    private Expression findExpr(String string) {
	Expression expr = Expression.NULL;
	try {
	    ep = new ExprParser(string);
	    expr = ep.build();
	} catch (IOException e) {
	}
	return expr;
    }

    public String calculate(Map<String , Function> functions,
	    Map<String , Constant> constants, String input) {
	// Validate name
	String[] temp = input.split("[(]");
	Function f = functions.get(temp[0]);
	String func = f.getFunction();
	// Replace parameters
	String entry = temp[1];
	entry = constantsInEntry(entry, constants);
	try {
	    func = replaceParameters(func, entry, f.getParameters());
	} catch (ExpressionException e2) {
	    return "Error in function parameters";
	}
	// Replace constants
	try {
	    func = replaceConstants(func, constants);
	} catch (ExpressionException e) {
	    return "Wrong use of constant";
	}
	// Calculate
	try {
	    ep = new ExprParser(func);
	    return input + " = " + ep.build().calculate();
	} catch (Exception e) {
	    return "Error in expression";
	}
    }

    private String constantsInEntry(String entry,
	    Map<String , Constant> constants) {
	for (String cons : constants.keySet()) {
	    if (entry.matches(".*" + cons + ".*")) {
		try {
		    entry = entry.replaceAll(cons, constants.get(cons)
			    .calculate() + "");
		} catch (ExpressionException e) {
		}
	    }
	}
	return entry;
    }
}
