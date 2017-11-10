package Repository;

import Domain.Medicament;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mihnea on 29.10.2017.
 */
public class FarmacieRepository {
    private List<Medicament> medsList = new ArrayList<>();

    /**
     * Adds a Medicament object to the arrayList
     *
     * @param med
     * @return FarmacieRepository class
     */
    public FarmacieRepository add(Medicament med) {
        try {
            this.medsList.add(new Medicament(med.getName(), med.getPrice()));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        return this;
    }

    /**
     * Removes a Medicament object from the arrayList
     *
     * @param med
     * @return a boolean that shows if the remove was successful
     */
    public boolean removeFromList(Medicament med) {
        Object[] obj = medsList.toArray();
        boolean b = false;
        for (int i = 0; i < medsList.size(); i++) {
            if (medsList.get(i).getName().equalsIgnoreCase(med.getName())) {
                medsList.remove(i);
                b = true;
            }
        }
        return b;
    }

    /**
     * Accessor for the arrayList with Medicament objects
     *
     * @return
     */
    public List<Medicament> getAll() {
        return this.medsList;
    }

    /**
     * Adds some predefined Medicament objects to the arrayList when the program starts
     */
    public void medsDB() {
        Medicament med1 = new Medicament("Nurofen", 10);
        Medicament med2 = new Medicament("Algocalmin", 15);
        Medicament med3 = new Medicament("Xanax", 5);
        Medicament med4 = new Medicament("Zyloft", 17);
        Medicament med5 = new Medicament("Codeine", 23);
        Medicament med6 = new Medicament("Lorazepam", 2);
        Medicament med7 = new Medicament("Viagra", 69);
        Medicament med8 = new Medicament("Xanax", 99);
        add(med1);
        add(med2);
        add(med3);
        add(med4);
        add(med5);
        add(med6);
        add(med7);
        add(med8);

    }

    /**
     * Prints the arrayList with the Medicament objects
     */
    public void printAll() {
        for (Medicament med : medsList) {
            System.out.println("[ " + med.getName() + " ]" +
                    "[ " + med.getPrice() + " ]");
        }
    }


}
