package session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parse.ExprParser;
import parse.FunctionBuilder;
import parse.InputConstants;
import expressions.Constant;
import expressions.Number;
import expressions.Expression;
import expressions.ExpressionException;
import expressions.Function;

public class CopyOfSession {
	private Map<String, Constant> constants;
	private Map<String, Function> functions;
	private FunctionBuilder fb;
	private FunctionCalculater fc;
	private ExprParser ep;

	private static final int parseFunction = 1;
	private static final int parseConstant = 2;
	private static final int functionInit = 4;
	private static final int constantInit = 3;
	private static final int parse = 0;

	public CopyOfSession() {
		try {
			ep = new ExprParser("");
		} catch (IOException e) {
		}
		fc = new FunctionCalculater();
		constants = initConstants();
		functions = new HashMap<String, Function>();
	}

	private Map<String, Constant> initConstants() {
		HashMap<String, Constant> cons = new HashMap<String, Constant>();
		cons.put("pi", new Constant("pi", new Number(Math.PI)));
		cons.put("PI", new Constant("PI", new Number(Math.PI)));
		cons.put("Pi", new Constant("Pi", new Number(Math.PI)));
		cons.put("e", new Constant("e", new Number(Math.E)));
		cons.put("E", new Constant("E", new Number(Math.E)));
		return cons;
	}

	public String input(String input) {
		input = input.replace(" ", "");
		switch (findCase(input)) {
		case functionInit:
			return createFunction(input);
		case constantInit:
			return createConstant(input);
		case parseFunction:
			return parseFunction(input);
		case parseConstant:
			return parseConstant(input);
		}
		return parseExpr(input);
	}

	private String parseConstant(String input) {
		return parseExprWithConstants(input, constantsInInput(input));
	}

	private String parseFunction(String input) {
		return fc.calculate(functions, constants, input);
	}

	private String createFunction(String input) {
		try {
			fb = new FunctionBuilder(input);
			Function f = fb.build();
			functions.put(f.getName(), f);
			return "Function " + f.toString() + " is defined";
		} catch (IOException e) {
			return "Error when defining function";
		}
	}

	private int findCase(String input) {
		if (constantInitiation(input))
			return constantInit;
		if (functionInitiation(input))
			return functionInit;
		if (functionParsing(input))
			return parseFunction;
		if (constantParsing(input))
			return parseConstant;
		return parse;
	}

	private boolean constantInitiation(String input) {
		return input.matches("[A-Za-z_]+=[A-Za-z0-9-!()*+^.,]+");
	}

	private boolean constantParsing(String input) {
		return !constantsInInput(input).isEmpty();
	}

	private boolean functionParsing(String input) {
		StringBuilder funcs = new StringBuilder();
		for (String s : functions.keySet()) {
			funcs.append(s + "|");
		}
		if (funcs.length() == 0) {
			return false;
		} else {
			String f = "[" + funcs.substring(funcs.length() - 2)
					+ "]+\\([A-Za-z0-9-!()*+^.,]+\\)";
			return input.matches(f);
		}
	}

	private boolean functionInitiation(String input) {
		return input
				.matches("[A-Za-z_]+\\([A-Za-z,]+\\)=[A-Za-z0-9-!()*+^.,]+");
	}

	// private void sysoCons() {
	// System.out.println("--------------------");
	// for (Function c : functions.values()) {
	// System.out.println(c.toString());
	// }
	// System.out.println("--------------------");
	// }

	private String parseExprWithConstants(String input, ArrayList<Constant> cons) {
		String calcValue = replaceConstant(input, cons);
		try {
			ep = new ExprParser(calcValue);
			Expression expr = ep.build();
			return input + " = " + expr.calculate();
		} catch (IOException e) {
			return "Error in input";
		} catch (ExpressionException e) {
			return "Wrong use of constant";
		}
	}

	public String replaceConstant(String input, ArrayList<Constant> cons) {
		String calcValue = input;
		for (Constant c : cons) {
			try {
				calcValue = calcValue.replaceAll("\\b" + c.getName() + "\\b",
						"" + c.calculate());
			} catch (ExpressionException e) {
				return "Wrong use of constant";
			}
		}
		return calcValue;
	}

	private ArrayList<Constant> constantsInInput(String input) {
		ArrayList<Constant> constantUsed = new ArrayList<Constant>();
		for (String c : constants.keySet()) {
			if (input.matches(".*" + c + ".*")) {
				constantUsed.add(constants.get(c));
			}
		}
		return constantUsed;
	}

	private String createConstant(String input) {
		String[] sections = input.split("=");
		if (isValidConsName(sections[0])) {
			try {
				ep = new ExprParser(sections[1]);
				Constant cons = new Constant(sections[0], ep.build());
				constants.remove(cons.getName());
				constants.put(cons.getName(), cons);
				return cons.toString();
			} catch (IOException e) {
				return "Error in expression";
			}
		} else {
			return sections[0] + " is an invalid constant name";
		}
	}

	private boolean isValidConsName(String name) {
		ep.getValidInputs();
		if (name.equals("e")) {
			return false;
		}
		for (String invalid : InputConstants.validUnaryOp) {
			if (name.equals(invalid)) {
				return false;
			}
		}
		return true;
	}

	private String parseExpr(String input) {
		try {
			ep = new ExprParser(input);
			Expression expr = ep.build();
			return input + " = " + expr.calculate();
		} catch (Exception e) {
			return "Error in input";
		}
	}
}