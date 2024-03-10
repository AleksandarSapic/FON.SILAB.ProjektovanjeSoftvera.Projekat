package communication;

import java.io.Serializable;

public class Response  implements Serializable{

    private final Object result;
    private final Exception exception;

    public Response(Object result, Exception exception) {
        this.result = result;
        this.exception = exception;
    }

    public Object getResult() {
        return result;
    }

    public Exception getException() {
        return exception;
    }

}
