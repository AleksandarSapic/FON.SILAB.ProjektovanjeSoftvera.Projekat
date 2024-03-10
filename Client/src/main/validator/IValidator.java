package main.validator;

import main.validator.util.ValidatorResult;

public interface IValidator<T> {

    ValidatorResult validate(T input) throws IllegalArgumentException;
}
