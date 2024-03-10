package main.validator.util;

import java.util.List;

public class ValidatorResult {

    private boolean isSuccess;
    private List<String> errorMessages;

    public ValidatorResult(boolean isSuccess, List<String> errorMessages) {
        this.isSuccess = isSuccess;
        this.errorMessages = errorMessages;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public List<String> getMessages() {
        return errorMessages;
    }

    public void addMessage(String message) {
        errorMessages.add(message);
    }
}
