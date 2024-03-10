package main.validator;

import dto.CreateCompetitionDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import main.validator.util.ValidatorResult;

public class CreateCompetitionValidator implements IValidator<CreateCompetitionDTO> {

    private static CreateCompetitionValidator instance;

    private CreateCompetitionValidator() {
    }

    public static CreateCompetitionValidator getInstance() {
        if (instance == null) {
            instance = new CreateCompetitionValidator();
        }
        return instance;
    }

    @Override
    public ValidatorResult validate(CreateCompetitionDTO input) throws IllegalArgumentException{
        ValidatorResult result = new ValidatorResult(true, new ArrayList<>());
        if (input == null) {
            throw new IllegalArgumentException("Takmičenje je null!");
        }

        /* VALIDACIJA NAZIVA */
        String name = input.getName();
        if (name.isEmpty()) {
            result.setIsSuccess(false);
            result.addMessage("Morate proslediti vrednost za naziv takmičenja.");
        }
        if (input.getName().length() < 2) {
            result.setIsSuccess(false);
            result.addMessage("Naziv takmičenja mora imati bar dva karaktera");
        }

        /* VALIDACIJA DATUMA */
        LocalDate startDate = input.getStartDate();
        LocalDate endDate = input.getEndDate();
        if (LocalDate.now().isAfter(endDate) || LocalDate.now().isAfter(startDate)) {
            result.setIsSuccess(false);
            result.addMessage("Datum završetka takmičenja i datum početka ne mogu da budu pre datuma koji je danas.");
        }
        if (endDate.isBefore(startDate)) {
            result.setIsSuccess(false);
            result.addMessage("Datum završetka takmičenja ne sme da bude pre datuma početka.");
        }
        return result;
    }
}
