import Repository.FarmacieRepository;
import Service.FarmacieService;
import UI.Console;
import Validator.MedValidator;

/**
 * Created by Mihnea on 29.10.2017.
 */
public class Main {

    public static void main(String[] args) {
        FarmacieRepository repo = new FarmacieRepository();
        MedValidator validator = new MedValidator();
        FarmacieService service = new FarmacieService(repo,validator);
        Console console = new Console(service, repo);

        console.start();
    }
}
