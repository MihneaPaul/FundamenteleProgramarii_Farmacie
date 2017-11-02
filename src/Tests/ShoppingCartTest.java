package Tests;

import Domain.Medicament;
import Service.ShoppingCart;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Mihnea on 02.11.2017.
 */
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
        String returnString = cart.checkout();
        assertEquals("Checkout complete.",returnString);
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