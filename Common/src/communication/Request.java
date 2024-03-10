package communication;

import java.io.Serializable;

public class Request implements Serializable{

    private final Operation operation;
    private final Object argument;

    public Request(Operation operation, Object argument) {
        this.operation = operation;
        this.argument = argument;
    }

    public Operation getOperation() {
        return operation;
    }

    public Object getArgument() {
        return argument;
    }

}
