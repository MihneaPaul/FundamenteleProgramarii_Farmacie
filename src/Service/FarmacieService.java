package Service;

import Domain.Medicament;
import Repository.FarmacieRepository;
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

    /**
     * Constructor
     *
     * @param farmacieRepository
     * @param medValidator
     */
    public FarmacieService(FarmacieRepository farmacieRepository, MedValidator medValidator) {
        super();
        this.farmacieRepository = farmacieRepository;
        this.medValidator = medValidator;
    }

    /**
     * Accessor for the ShoppingCart class, for use of the 'cartList' arrayList in the ShoppingCart class and it's methods
     *
     * @return the ShoppingCart class
     */
    public ShoppingCart getCart() {
        return cart;
    }

    /**
     * Calls the add method from the Repository class to add a new Medicament object with the desired name and price to the Medicament ArrayList, after validation
     *
     * @param name  of the Medicament
     * @param price of the Medicament
     */
    public String addMed(String name, int price) {
        Medicament med = new Medicament(name, price);
        try {
            this.medValidator.validate(med);
            FarmacieRepository added = this.farmacieRepository.add(med);
            return "Medicament adaugat cu success";
//            if (added == null) {
//                throw new IllegalArgumentException("Cannot add med with price " + price);
//            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    /**
     * Calls the delete method from the Repository class to delete the desired Medicament object (if found) from the Medicament ArrayList
     *
     * @param nameOfMed - field 'name' from the Medicament class
     */
    public boolean deleteMed(String nameOfMed) {
        List<String> searchedNames;
        searchedNames = search(nameOfMed);

        if (searchedNames.size() == 1) {
            String name = searchedNames.get(0);
            for (int i = 0; i < farmacieRepository.getAll().size(); i++) {
                if (name.equalsIgnoreCase(farmacieRepository.getAll().get(i).getName())) {
                    farmacieRepository.removeFromList(farmacieRepository.getAll().get(i));
//                    return "Medicamentul a fost sters cu success";
                    return true;
                }
            }
        } else throw new IllegalArgumentException("Found more than 1 med with this name: " + nameOfMed);
//        return "";
        return false;
    }

    public boolean deleteAllMeds(String nameOfMed) {
        List<String> searchedNames;
                searchedNames = search(nameOfMed);
                boolean b2 = false;
            int count = 0;
            int size = farmacieRepository.getAll().size();
            try {
                deleteMed(nameOfMed);
                b2 = true;
            } catch (IllegalArgumentException e) {
                String name = searchedNames.get(0);
                for (int i = 0; i < farmacieRepository.getAll().size(); i++) {
                    if (name.equalsIgnoreCase(farmacieRepository.getAll().get(i).getName())) {
                        farmacieRepository.removeFromList(farmacieRepository.getAll().get(i));
                        b2 = true;
                    } else {
                        count++;
                    }
                    if (count == size) {
                        b2 = false;
                    }

//                if (i > 0) {
//                    i--;
//                }
                }

            }
            return b2;
        }



    /**
     * Searches the ArrayList from the Repository class for a Medicament with the desired name
     *
     * @param partialName - name of the Medicament to search
     * @return a List containing the Medicament objects that contains the value of the parameter
     */
    public List<String> search(String partialName) {
        List<String> searchedNames = new ArrayList<>();
        for (Medicament med : farmacieRepository.getAll()) {
            if (med.getName().toLowerCase().contains(partialName.toLowerCase())) {
                searchedNames.add(med.getName());
            }
        }
        return searchedNames;
    }

    /**
     * Checks if Med object with a specified name exists in the Repository
     * @param name
     * @return
     */
    public boolean exists(String name){
        List<String> searchedNames = search(name);
        if(searchedNames.size() == 0) return false;
        return true;
    }

    /**
     * Sorts by price field the Medicament Objects in the ArrayList from the Repository
     *
     * @return a String containing all the Medicament objects, in ascending order of their price
     */
    public String bubbleSort() {
        List<Medicament> sortMedList = new ArrayList<>();

        sortMedList.addAll(farmacieRepository.getAll());
//        System.out.println(sortMedList);
        int temp;
        String temp2;
        Medicament temp3;
        for (int i = 0; i < sortMedList.size(); i++) {
            for (int j = 1; j < sortMedList.size(); j++) {
                if (sortMedList.get(j - 1).getPrice() > sortMedList.get(j).getPrice()) {

//                    temp = sortMedList.get(j-1).getPrice();
//                    sortMedList.get(j-1).setPrice(sortMedList.get(j).getPrice());
//                    sortMedList.get(j).setPrice(temp);

                    temp3 = sortMedList.get(j - 1);
                    sortMedList.set(j - 1, sortMedList.get(j));
                    sortMedList.set(j, temp3);

//                    temp2 = sortMedList.get(j-1).getName();
//                    sortMedList.get(j-1).setName(sortMedList.get(j).getName());
//                    sortMedList.get(j).setName(temp2);
//                    System.out.println(sortMedList);
                }
            }
        }


        return anotherToString(sortMedList);
    }

    /**
     * Accessor
     *
     * @return a List containing all the Medicament objects in the Repository class
     */
    public List<Medicament> getAll() {
        return this.farmacieRepository.getAll();
    }

    /**
     * Calls the inner class method addToCart
     *
     * @param repo     - an instance of the Repository class
     * @param service  - an instance of the Service class
     * @param medName  - name of the Medicament object
     * @param quantity - quantity of the Medicament object
     */

    public void innerAddToCart(FarmacieRepository repo, FarmacieService service, String medName, int quantity) {
        FarmacieService.CartInnerClass inner = new CartInnerClass(repo, service);
        inner.addToCart(medName, quantity);
    }

    /**
     * Calls the inner class method deleteFromCart
     *
     * @param repo    - an instance of the Repository class
     * @param service - an instance of the Service class
     * @param medName - name of the Medicament object
     */
    public void innerDeleteFromCart(FarmacieRepository repo, FarmacieService service, String medName) {
        FarmacieService.CartInnerClass inner = new CartInnerClass(repo, service);
        inner.deleteFromCart(medName);
    }

    /**
     * Displays the sorted Medicament List
     *
     * @param list
     * @return a String containing all the Medicament objects
     */
    public String anotherToString(List<Medicament> list) {
        StringBuilder result = new StringBuilder("+");
        for (int i = 0; i < list.size(); i++) {
            result.append(" ").append(list.get(i));
            result.append('\n');
        }
        return result.toString();
    }

    private class CartInnerClass {

        private FarmacieRepository repo;
        private FarmacieService service;

        /**
         * Constructor of the inner class
         *
         * @param repo    - an instance of the Repository class
         * @param service - an instance of the Service class
         */
        private CartInnerClass(FarmacieRepository repo, FarmacieService service) {
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
            for (int i = 0; i < repo.getAll().size(); i++) {
                if (repo.getAll().get(i).getName().equalsIgnoreCase(medName)) {
                    service.getCart().mediatorDeleteFromCart(repo.getAll().get(i));
                }
            }
        }
    }


}
