package Repository;

import Domain.Medicament;

import java.util.ArrayList;

/**
 * Created by Mihnea on 29.10.2017.
 */
public class ShoppingCart {
    private ArrayList<Medicament> cartList;
    private int medCount;
    private int totalPrice;

    public ShoppingCart() {
        this.cartList = new ArrayList<>();
        this.medCount = 0;
        this.totalPrice = 0;
    }

    public void mediatorAddToCart(Medicament object, int quantity) {
        for (int i = 1; i <= quantity;i++) {
            cartList.add(object);
            totalPrice += object.getPrice();
            medCount++;
        }
    }

    public void mediatorDeleteFromCart(Medicament object){
        for(Medicament x:cartList){
            if(x.equals(object)){
                cartList.remove(x);
                totalPrice -= object.getPrice();
            }
        }
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void emptyCart(){
        if(!(cartList.isEmpty())){
            cartList.removeAll(cartList);
            medCount = 0;
            totalPrice = 0;
        }
    }

    public String checkout(){
        System.out.println("Final price is: " + getTotalPrice());
        emptyCart();
        return "Checkout complete.";
    }

    public String max() {
        int max = Integer.MIN_VALUE;
        String maxMed = "";
        int price = 0;
        for (Medicament bean : cartList) {
            if (bean.getPrice() > max) {
                maxMed = bean.getName();
                price = bean.getPrice();
            }
        }
        return maxMed + ", price is " + price + " lei.";
    }
}
