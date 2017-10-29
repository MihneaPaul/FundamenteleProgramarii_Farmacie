package Repository;

import Domain.Medicament;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mihnea on 29.10.2017.
 */
public class FarmacieRepository {
    private List<Medicament> medsList = new ArrayList<>();

    public FarmacieRepository add(Medicament med){

        this.medsList.add(new Medicament(med.getName(),med.getPrice()));
        return this;
    }

    public boolean removeFromList(Medicament med){
        Object[] obj = medsList.toArray();

        for(int i=0;i<medsList.size();i++){
            if(medsList.get(i).getName().equalsIgnoreCase(med.getName())){
                medsList.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Medicament> getAll(){
        return this.medsList;
    }

    public void medsDB(){
        Medicament med1 = new Medicament("Nurofen",10);
        Medicament med2 = new Medicament("Algocalmin",15);
        Medicament med3 = new Medicament("Xanax", 5);
        Medicament med4 = new Medicament("Zyloft", 17);
        Medicament med5 = new Medicament("Codeine", 23);
        Medicament med6 = new Medicament("Lorazepam", 2);
        Medicament med7 = new Medicament("Viagra", 69);

        add(med1);
        add(med2);
        add(med3);
        add(med4);
        add(med5);
        add(med6);
        add(med7);

    }

    public void printAll(){
        for(Medicament med: medsList){
            System.out.println("[ " + med.getName() + " ]" +
                               "[ " + med.getPrice() + " ]");
        }
    }


}
