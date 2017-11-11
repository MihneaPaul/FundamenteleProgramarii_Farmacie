package Tests;

import Domain.Medicament;
import Repository.FarmacieRepository;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Mihnea on 02.11.2017.
 */
class FarmacieRepositoryTest extends TestCase {

    FarmacieRepository repo = new FarmacieRepository();
    @Test
    void add() {
        int beforeLenght = repo.getAll().size();
        repo.add(new Medicament("xyz",14));
        int afterLenght = repo.getAll().size();
        assertNotEquals(beforeLenght,afterLenght);
    }

    @Test
    void removeFromList() {
        int beforeLenght = repo.getAll().size();
        Medicament med1 = new Medicament("xyz",25);
        repo.add(med1);
        repo.removeFromList(med1);
        int afterLenght = repo.getAll().size();
        assertEquals(beforeLenght,afterLenght);
    }

}