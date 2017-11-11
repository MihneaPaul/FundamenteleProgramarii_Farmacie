package TestSuite;

import Domain.Medicament;
import Repository.FarmacieRepository;
import Service.FarmacieService;
import Service.ShoppingCart;
import Validator.MedValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Mihnea on 11.11.2017.
 */
@SuppressWarnings("Duplicates")
public class FarmacieTestSuite {
    private FarmacieService service;
    private FarmacieRepository repo;
    private MedValidator validator;
    private ShoppingCart cart;

    @BeforeEach
    void setup(){
        repo = new FarmacieRepository();
        validator = new MedValidator();
        cart = new ShoppingCart();
        service = new FarmacieService(repo, validator);
    }

    @Nested
    @DisplayName("Repository Test")
    class RepositoryTest {
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

    @Nested
    @DisplayName("Service Test")
    class ServiceTest {
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

    @Nested
    @DisplayName("Validator Test")
    class ValidatorTest {
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

    @Nested
    @DisplayName("Shopping Cart Test")
    class ShoppingCartTest {
        @Test
        void mediatorAddToCart() {
            ShoppingCart cart = new ShoppingCart();
            Medicament med = new Medicament("xyz", 14);
            int beforePrice = cart.getTotalPrice();
            cart.mediatorAddToCart(med,1);
            int afterPrice = cart.getTotalPrice();
            assertNotEquals(beforePrice,afterPrice);

        }

        @Test
        void mediatorDeleteFromCart() {
            ShoppingCart cart = new ShoppingCart();
            Medicament med = new Medicament("xyz", 14);
            int beforePrice = cart.getTotalPrice();
            cart.mediatorAddToCart(med,2);
            cart.mediatorDeleteFromCart(med);
            cart.mediatorDeleteFromCart(med);
            int afterPrice = cart.getTotalPrice();
            assertEquals(beforePrice,afterPrice);
        }

        @Test
        void emptyCart() {
            ShoppingCart cart = new ShoppingCart();
            Medicament med = new Medicament("xyz", 14);
//        cart.mediatorAddToCart(med,2);
            try {
                cart.emptyCart();
                fail("Exceptie nelansata");
            } catch (IllegalStateException e){
                assertEquals("The Cart is already empty!", e.getMessage());
            }
        }

        @Test
        void checkout() {
            ShoppingCart cart = new ShoppingCart();
            Medicament med = new Medicament("xyz", 14);
            cart.mediatorAddToCart(med,2);
            int finalPrice = cart.getTotalPrice();
            String returnString = "Final price is: " + finalPrice + ". Checkout complete.";
            assertEquals("Final price is: 28. Checkout complete.",returnString);
        }

        @Test
        void max() {
            ShoppingCart cart = new ShoppingCart();
            Medicament med2 = new Medicament("abc", 15);
            Medicament med3 = new Medicament("efg", 11);
            Medicament med = new Medicament("xyz", 14);

            cart.mediatorAddToCart(med,1);
            cart.mediatorAddToCart(med2,1);
            cart.mediatorAddToCart(med3,1);

            String returnString = cart.max();
            assertEquals((med2.getName() + ", price is " + med2.getPrice() + " lei."), returnString);

        }
    }
}
