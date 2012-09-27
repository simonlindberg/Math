package parse;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;

import expressions.Function;

public class FunctionBuilder extends StreamTokenizer {
    private int token;

    public FunctionBuilder(String parseString) throws IOException {
	super(new StringReader(parseString));
	ordinaryChar('/');
	ordinaryChar('-');
	token = nextToken();
    }

    public Function build() throws IOException {
	String name = name();
	ArrayList<String> params = parameters();
	String func = function();

	return new Function(name, params, func);
    }

    private String function() throws IOException {
	token = nextToken();
	String temp = "";
	while (token != TT_EOF) {
	    if (token == TT_WORD) {
		temp += sval;
	    } else if (token == TT_NUMBER) {
		temp += nval;
	    } else {
		temp += String.valueOf((char) token);
	    }
	    token = nextToken();
	}
	return temp;
    }

    private ArrayList<String> parameters() throws IOException {
	ArrayList<String> temp = new ArrayList<String>();
	token = nextToken();
	// if (token != '(') {
	// throw new IOException();
	// }
	token = nextToken();
	while (token != ')') {
	    if (token == TT_WORD) {
		temp.add(sval);
	    }
	    token = nextToken();
	}
	token = nextToken();
	return temp;
    }

    private String name() throws IOException {
	// if (token == TT_WORD) {
	return sval;
	// } else {
	// throw new IOException();
	// }
    }

}
