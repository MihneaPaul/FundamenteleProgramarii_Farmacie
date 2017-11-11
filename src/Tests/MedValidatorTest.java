package Tests;

import Domain.Medicament;
import Validator.MedValidator;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Mihnea on 02.11.2017.
 */
class MedValidatorTest extends TestCase {
    @Test
    void validate() {
        Medicament med1 = new Medicament("abc", 3);
        Medicament med2 = new Medicament("bcd", 0);
        Medicament med3 = new Medicament("", 10);
        Medicament med4 = new Medicament("" ,0);

        MedValidator validator = new MedValidator();


        try {
            validator.validate(med1);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }

        try {
            validator.validate(med2);
            fail("Exceptie nelansata");
        } catch (IllegalArgumentException e){
            assertEquals("Pret incorect!\n", e.getMessage());
        }

        try {
            validator.validate(med3);
            fail("Exceptie nelansata");
        } catch (IllegalArgumentException e){
            assertEquals("Nume incorect!", e.getMessage());
        }

        try {
            validator.validate(med4);
            fail("Exceptie nelansata");
        } catch (IllegalArgumentException e){
            assertEquals("Pret incorect!\nNume incorect!", e.getMessage());
        }
    }

}