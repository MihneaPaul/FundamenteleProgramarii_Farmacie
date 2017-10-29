package UI;

import Domain.Medicament;
import Repository.FarmacieRepository;
import Service.FarmacieService;

import java.util.Scanner;

/**
 * Created by Mihnea on 29.10.2017.
 */
public class Console {
    private Scanner scanner = new Scanner(System.in);
    private FarmacieService service;
    private FarmacieRepository repo;

    public Console(FarmacieService service, FarmacieRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    private void showAll() {
        for (Medicament m : this.service.getAll()) {
            System.out.println(m);
        }
    }

    private void addMed() {
        System.out.println("Nume:  ");
        String name = scanner.nextLine();
        name = scanner.nextLine();
        System.out.println();
//        scanner.nextLine();
        System.out.println("Pret:  ");
        int price = scanner.nextInt();
        System.out.println();

        try {
            Medicament m = new Medicament(name, price);
            service.addMed(m.getName(), m.getPrice());
            System.out.println("Medicament adaugat cu success");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteMed() {
        System.out.println("Nume:  ");
        String name = scanner.nextLine();
        name = scanner.nextLine();
        try {
            service.deleteMed(name);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void search() {
        System.out.println("Nume:  ");
        String name = scanner.next();
        System.out.println(service.search(name));
    }

    private void bubbleSort() {
        System.out.println(service.bubbleSort());
    }

    private void mainMenu() {
        System.out.println("1. Customer");
        System.out.println("2. Admin");
    }

    private void menuAdmin() {
        System.out.println("----- Farmacie -----");
        System.out.println();
        System.out.println("1. Vizualizati stocul");
        System.out.println("2. Adaugati un medicament");
        System.out.println("3. Cautati un medicament");
        System.out.println("4. Stergeti un medicament");
        System.out.println("5. Modificati un medicament");
        System.out.println("6. Sortati medicamentele in functie de pret");
        System.out.println("x. Exit");
        System.out.println();
        System.out.print("Selectia: ");
    }

    private void menuCustomer() {
        System.out.println("----- Farmacie -----");
        System.out.println();
        System.out.println("1. Vizualizati stocul");
        System.out.println("2. Cautati un medicament");
        System.out.println("3. Sortati medicamentele in functie de pret");
        System.out.println("4. Cumparati");
        System.out.println("x. Exit");
        System.out.println();
        System.out.print("Selectia: ");

    }

    private void menuShop() {
        System.out.println("----- Magazin -----");
        System.out.println();
        System.out.println("1. Adaugati un produs in cos");
        System.out.println("2. Stergeti un produs din cos");
        System.out.println("3. Cost total");
        System.out.println("4. Checkout");
    }

    public void start() {
        repo.medsDB();
        boolean canAccessAdmin = false;
        boolean canAccessCustomer = false;

        mainMenu();

        System.out.print("Choose:  ");
        int selectionMainMenu = scanner.nextInt();
        switch (selectionMainMenu) {
            case 1:
                canAccessCustomer = true;
                break;
            case 2:
                System.out.print("Account:  ");
                String account = scanner.next();
//                System.out.println("selections is now: " + account);
                if (account.equalsIgnoreCase("admin")) {
                    System.out.print("Password:  ");
                    String password = scanner.next();
                    if (password.equalsIgnoreCase("admin")) {
                        canAccessAdmin = true;
                    } else try {
                        throw new Exception("Incorrect password");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else try {
                    throw new Exception("Incorrect account");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println();
                break;
        }

        CAN_ACCESS_ADMIN:
        while (canAccessAdmin) {
            menuAdmin();
            String selectionAdmin = scanner.next();
            switch (selectionAdmin) {
                case "1":
                    showAll();
                    break;

                case "2":
                    addMed();
                    System.out.println();
                    break;

                case "3":
                    search();
                    System.out.println();
                    break;

                case "4":
                    deleteMed();
                    break;

                case "5": // TODO
                    break;

                case "6":
                    bubbleSort();
                    System.out.println();
                    break;

                case "x":
                    break CAN_ACCESS_ADMIN;
            }
        }

        boolean canAccessCart;

        CAN_ACCESS_CUSTOMER:
        while (canAccessCustomer) {
            menuCustomer();
            String selectionCustomer = scanner.next();
            switch (selectionCustomer) {
                case "1":
                    showAll();
                    System.out.println();
                    break;

                case "2":
                    search();
                    System.out.println();
                    break;

                case "3":
                    bubbleSort();
                    System.out.println();
                    break;

                case "4":
                    canAccessCart = true;
                    Scanner scanShop = new Scanner(System.in);
                    while (canAccessCart) {
                        menuShop();
                        System.out.print("Selectia:  ");
                        String selectionShop = scanShop.next();
                        FarmacieService.CartInnerClass inner = new FarmacieService.CartInnerClass(repo, service);

                        switch (selectionShop) {
                            case "1":
                                System.out.print("Selectati produsul:  ");
                                String selectedProduct = scanShop.next();
                                System.out.println();
                                scanShop.nextLine();
                                System.out.print("Introduceti cantitatea:  ");
                                int quantityOfProduct = scanShop.nextInt();
                                inner.addToCart(selectedProduct, quantityOfProduct);
                                break;

                            case "2":
                                System.out.print("Selectati produsul:  ");
                                String willBeDeletedProduct = scanShop.next();
                                inner.deleteFromCart(willBeDeletedProduct);
                                break;

                            case "3":
                                System.out.println(service.getCart().getTotalPrice());
                                break;

                            case "4":
                                System.out.println(service.getCart().checkout());
                                canAccessCart = false;
                                break;
                        }

                    }
                    break;

                case "x":
                    break CAN_ACCESS_CUSTOMER;
            }
        }
    }
}
