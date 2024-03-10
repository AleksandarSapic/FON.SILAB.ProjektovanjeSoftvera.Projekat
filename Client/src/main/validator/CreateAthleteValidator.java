package main.validator;

import dto.CreateAthleteDTO;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import main.validator.util.ValidatorResult;

public final class CreateAthleteValidator implements IValidator<CreateAthleteDTO> {

    private static CreateAthleteValidator instance;

    private CreateAthleteValidator() {
    }

    public static CreateAthleteValidator getInstance() {
        if (instance == null) {
            instance = new CreateAthleteValidator();
        }
        return instance;
    }

    @Override
    public ValidatorResult validate(CreateAthleteDTO input) throws IllegalArgumentException {
        ValidatorResult result = new ValidatorResult(true, new ArrayList<>());

        if (input == null) {
            throw new IllegalArgumentException("Atletičar je null!");
        }

        /* **IME** */
        if (input.getFirstname().isEmpty()) {
            result.setIsSuccess(false);
            result.addMessage("Morate proslediti vrednost za ime");
        }
        if (input.getFirstname().length() < 2) {
            result.setIsSuccess(false);
            result.addMessage("Ime mora imati bar dva karaktera");
        }
        String regex = "^[A-Š][a-š]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input.getFirstname());
        if (!matcher.matches()) {
            result.setIsSuccess(false);
            result.addMessage("Ime mora biti napisano velikim početnim slovom i da bude napisano srpskom latinicom");
        }

        /* **PREZIME** */
        if (input.getLastname().isEmpty()) {
            result.setIsSuccess(false);
            result.addMessage("Morate proslediti vrednost za prezime");
        }
        if (input.getLastname().length() < 2) {
            result.setIsSuccess(false);
            result.addMessage("Prezime mora imati bar dva karaktera");
        }
        regex = "^[A-Š][a-š]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input.getLastname());
        if (!matcher.matches()) {
            result.setIsSuccess(false);
            result.addMessage("Prezime mora biti napisano velikim početnim slovom i da bude napisano srpskom latinicom");
        }

        /* **DRZAVA** */
        if (input.getState().isEmpty()) {
            result.setIsSuccess(false);
            result.addMessage("Morate proslediti vrednost za državu");
        }
        if (input.getState().length() < 2) {
            result.setIsSuccess(false);
            result.addMessage("Država mora imati bar dva karaktera");
        }
        regex = "^[A-Š][a-š]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input.getState());
        if (!matcher.matches()) {
            result.setIsSuccess(false);
            result.addMessage("Država mora biti napisana velikim početnim slovom i da bude napisano srpskom latinicom");
        }
        return result;
    }
}
