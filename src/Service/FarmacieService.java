package Service;

import Domain.Medicament;
import Repository.FarmacieRepository;
import Repository.ShoppingCart;
import Validator.MedValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mihnea on 29.10.2017.
 */
public class FarmacieService {
    private FarmacieRepository farmacieRepository;
    private MedValidator medValidator;
    private ShoppingCart cart = new ShoppingCart();

    public FarmacieService(FarmacieRepository farmacieRepository, MedValidator medValidator) {
        this.farmacieRepository = farmacieRepository;
        this.medValidator = medValidator;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void addMed(String name, int price){
        Medicament med = new Medicament(name, price);
        this.medValidator.validate(med);

        FarmacieRepository added = this.farmacieRepository.add(med);
        if(added == null){
            throw new IllegalArgumentException("Cannot add med with price "+ price);
        }
    }

    public void deleteMed(String nameOfMed){
        List<String> searchedNames;
        searchedNames = search(nameOfMed);

        if(searchedNames.size() == 1){
            String name = searchedNames.get(0);
            for(int i=0;i<farmacieRepository.getAll().size();i++){
                if(name.equalsIgnoreCase(farmacieRepository.getAll().get(i).getName())){
                    farmacieRepository.removeFromList(farmacieRepository.getAll().get(i));
                }
            }
        } else throw new IllegalArgumentException("Found more than 1 med with this name: " + nameOfMed);

    }

    public List<String> search(String partialName){
        List<String> searchedNames = new ArrayList<>();
        for(Medicament med : farmacieRepository.getAll()){
            if(med.getName().toLowerCase().contains(partialName.toLowerCase())){
                searchedNames.add(med.getName());
            }
        }
        return searchedNames;
    }

    public String bubbleSort(){
        List<Medicament> sortMedList = new ArrayList<>();

        sortMedList.addAll(farmacieRepository.getAll());

        int temp;
        String temp2;
        for(int i=0;i<sortMedList.size();i++){
            for(int j=1;j<sortMedList.size()-1;j++){
                if(sortMedList.get(j-1).getPrice() > sortMedList.get(j).getPrice()){

                    temp = sortMedList.get(j-1).getPrice();
                    sortMedList.get(j-1).setPrice(sortMedList.get(j).getPrice());
                    sortMedList.get(j).setPrice(temp);

                    temp2 = sortMedList.get(j-1).getName();
                    sortMedList.get(j-1).setName(sortMedList.get(j).getName());
                    sortMedList.get(j).setName(temp2);
                }
            }
        }


        return anotherToString(sortMedList);
    }

    public List<Medicament> getAll(){
        return this.farmacieRepository.getAll();
    }



    public static class CartInnerClass {

        private FarmacieRepository repo;
        private FarmacieService service;

        public CartInnerClass(FarmacieRepository repo, FarmacieService service) {
            this.repo = repo;
            this.service = service;
        }

        /**
         * Adds a new Med to the private cartList so the user would not need to add the object directly, but the Med with it's name
         *
         * @param medName
         * @param quantity
         */
        public void addToCart(String medName, int quantity) {
            for (Medicament x : repo.getAll()) {
                if (x.getName().equalsIgnoreCase(medName)) {
                    service.getCart().mediatorAddToCart(x, quantity);
                }
            }
        }

        /**
         * Deletes a Med from the private cartList so that the user would not have to delete the object directly, but the Med with it's name
         *
         * @param medName
         */
        public void deleteFromCart(String medName) {
            for (Medicament x : repo.getAll()) {
                if (x.getName().equalsIgnoreCase(medName)) {
                    service.getCart().mediatorDeleteFromCart(x);
                }
            }
        }
    }

    public String anotherToString(List<Medicament> list) {
        StringBuilder result = new StringBuilder("+");
        for (int i = 0; i < list.size(); i++) {
            result.append(" ").append(list.get(i));
            result.append('\n');
        }
        return result.toString();
    }


}
