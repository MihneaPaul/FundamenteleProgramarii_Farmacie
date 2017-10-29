package Validator;

import Domain.Medicament;

/**
 * Created by Mihnea on 29.10.2017.
 */
public class MedValidator {

    public void validate(Medicament med){
        StringBuilder builder = new StringBuilder();

        if(med.getPrice() <= 0){
            builder.append("Pret incorect!");
        }
        if(builder.length() > 0){
            throw new IllegalArgumentException(builder.toString());
        }
    }
}
