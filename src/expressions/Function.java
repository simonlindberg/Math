package expressions;

import java.util.ArrayList;

public class Function {
    private String name;
    private String function;
    private ArrayList<String> parameters;

    public Function(String name, ArrayList<String> parameters, String function) {
	this.name = name;
	this.function = function;
	this.parameters = parameters;
    }

    public String getFunction() {
	return function;
    }

    public String toString() {
	String temp = name + "(";
	for (int i = 0; i < parameters.size(); i++) {
	    if (i == parameters.size() - 1) {
		temp += parameters.get(i);
	    } else {
		temp += parameters.get(i) + ", ";
	    }
	}
	return temp + ")";
    }

    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
    }

    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Function other = (Function) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }

    public String getName() {
	return name;
    }

    public ArrayList<String> getParameters() {
	return parameters;
    }

}
