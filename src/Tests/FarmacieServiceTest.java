package Tests;

import Repository.FarmacieRepository;
import Service.ShoppingCart;
import Service.FarmacieService;
import Validator.MedValidator;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Mihnea on 31.10.2017.
 */
class FarmacieServiceTest {

    FarmacieRepository repo = new FarmacieRepository();
    MedValidator validator = new MedValidator();
    ShoppingCart cart = new ShoppingCart();
    FarmacieService service = new FarmacieService(repo, validator);

    @org.junit.jupiter.api.Test
    void addMed() {
        int beforeLenght = repo.getAll().size();
        service.addMed("xyz", 30);
        int afterLength = repo.getAll().size();
        assertNotEquals(beforeLenght,afterLength);
    }

    @org.junit.jupiter.api.Test
    void deleteMed() {
        service.addMed("xyz", 30);
        service.deleteMed("xyz");
        int afterLength = repo.getAll().size();
        assertEquals(afterLength,0);
    }

    @org.junit.jupiter.api.Test
    void deleteAllMeds(){
        service.addMed("xyz",40);
        service.addMed("xyz",40);
//        service.deleteAllMeds("xyz");
        int afterLength = repo.getAll().size();
//        assertEquals(afterLength,0);
        assertTrue(service.deleteAllMeds("xyz"));
    }

    @org.junit.jupiter.api.Test
    void search() {
        service.addMed("xyz1",30);
        service.addMed("xyz2",40);
        service.addMed("xyx",50);
        List<String> searchedNames;
        searchedNames = service.search("xyz");
        assertEquals(searchedNames.size(), 2);
    }

    @org.junit.jupiter.api.Test
    void bubbleSort() {
        service.addMed("Morphine", 65);
        service.addMed("Epinefrina",41);
        service.addMed("Xanax",40);
        boolean b = false;
        String x = service.bubbleSort();
        if((x.indexOf("Xanax") < x.indexOf("Morphine")) && (x.indexOf("Epinefrina") > x.indexOf("Xanax"))){
            b = true;
        }
//        System.out.println(x);
        assertTrue(b);
    }

    @org.junit.jupiter.api.Test
    void innerAddToCart() {
        int beforePrice = cart.getTotalPrice();
//        System.err.println(beforePrice);
//        service.addMed("Xanax", 10);
        repo.medsDB();
//        System.out.println(repo.getAll());
        service.innerAddToCart(repo, service, "Viagra", 2);

//        service.getCart().showCart();
        int afterPrice = service.getCart().getTotalPrice();
//        System.err.println(afterPrice);
        assertNotEquals(beforePrice,afterPrice);
    }

    @org.junit.jupiter.api.Test
    void innerDeleteFromCart() {
        int beforePrice = cart.getTotalPrice();
        repo.medsDB();
        service.innerAddToCart(repo, service, "Viagra", 2);
        int inBetweenPrice = service.getCart().getTotalPrice();
//        System.err.println(inBetweenPrice);
//        service.getCart().showCart();
         service.innerDeleteFromCart(repo, service, "Viagra");
        service.innerDeleteFromCart(repo, service, "Viagra");

//        service.innerDeleteFromCart(repo, service, "Viagra");

        int afterPrice = service.getCart().getTotalPrice();
//        System.err.println(afterPrice);
        assertEquals(beforePrice,afterPrice);
    }

}