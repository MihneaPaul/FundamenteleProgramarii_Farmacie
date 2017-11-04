package Service;

import Domain.Medicament;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Created by Mihnea on 29.10.2017.
 */
public class ShoppingCart {
    private ArrayList<Medicament> cartList;
    private int medCount;
    private int totalPrice;

    /**
     * Constructor with no parameters
     */
    public ShoppingCart() {
        this.cartList = new ArrayList<>();
        this.medCount = 0;
        this.totalPrice = 0;
    }

    /**
     * Adds the Medicament object passed from the addToCart method in the CartInnerClass from the FarmacieService class
     *
     * @param object   - a Medicament object
     * @param quantity - number of Medicament objects to add to the cartList ArrayList
     */
    public void mediatorAddToCart(Medicament object, int quantity) {
        for (int i = 1; i <= quantity; i++) {
            cartList.add(object);
            totalPrice += object.getPrice();
            medCount++;
//            System.out.println(cartList);
        }
    }

    /**
     * Deletes the Medicament object passed from the deleteFromCart method in the CartInnerClass from the FarmacieService class
     *
     * @param object - a Medicament object
     */
    public void mediatorDeleteFromCart(Medicament object) {
//        for (Medicament x : cartList) {
//            if (x.equals(object)) {
//                cartList.remove(x);
//                totalPrice -= object.getPrice();
//            }
//        }
        for (int i = 0; i < cartList.size(); i++) {
            int size = cartList.size();
            if (size == 0) {
                break;
            }
            while (cartList.get(i).getName().equals(object.getName())) {
                boolean inBounds = (i >= 0) && (i < cartList.size());
                cartList.remove(i);
                totalPrice -= object.getPrice();
                size = cartList.size();
                if (size == 0) {
                    break;
                }
            }
        }
    }

    /**
     * Accessor for the field 'totalPrice' of this class
     *
     * @return an integer containing the added prices from all the cartList's objects
     */
    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     * Removes all the objects from the cartList ArrayList
     *
     * @throws IllegalStateException if the cartList is empty
     */
    public void emptyCart() {
        if (!(cartList.isEmpty())) {
            cartList.removeAll(cartList);
            medCount = 0;
            totalPrice = 0;
        } else {
            throw new IllegalStateException("The Cart is already empty!");
        }
    }

    /**
     * Displays the totalPrice field and then calls the empyCart() method
     *
     * @return a confirmation message if the emptyCart() method was successful or an empty String if not
     */
    public String checkout() {
        try {
            emptyCart();
            return "Checkout complete.";
        } catch (IllegalStateException e) {
            System.err.println(e.getMessage());
            System.err.println("Add something to the cart.");
        } finally {
            System.out.println("Final price is: " + getTotalPrice());

        }
        return "";
    }

    /**
     * Find the Medicament object in the cartList with the highest price
     *
     * @return a String containing the Medicament object's name and price
     */
    public String max() {
        int max = Integer.MIN_VALUE;
        String maxMed = "";
        for (Medicament bean : cartList) {
            if (bean.getPrice() > max) {
                maxMed = bean.getName();
                max = bean.getPrice();
            }
        }
        return maxMed + ", price is " + max + " lei.";
    }

    public void showCart() {
        System.out.println(cartList.toString());
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartList=" + cartList +
                '}';
    }
}
