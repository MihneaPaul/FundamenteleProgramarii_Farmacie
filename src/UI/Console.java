package UI;

import Domain.Medicament;
import Repository.FarmacieRepository;
import Service.FarmacieService;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Mihnea on 29.10.2017.
 */
public class Console {
    private Scanner scanner = new Scanner(System.in);
    private FarmacieService service;
    private FarmacieRepository repo;

    /**
     * Constructor
     *
     * @param service
     * @param repo
     */
    public Console(FarmacieService service, FarmacieRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    /**
     * Prints all the Medicament objects from the Repository's ArrayList of Medicament objects
     */
    private void showAll() {
        for (Medicament m : this.service.getAll()) {
            System.out.println(m);
        }
    }

    /**
     * Console method that calls the add() method from the Service class
     */
    private void addMed() {
        System.out.println("Nume:  ");
        String name = scanner.nextLine();
        name = scanner.nextLine();
        System.out.println();
//        scanner.nextLine();
        System.out.println("Pret:  ");
        int price = scanner.nextInt();
        System.out.println();


        Medicament m = new Medicament(name, price);
        service.addMed(m.getName(), m.getPrice());
        if ((int) price != price) {
            throw new InputMismatchException("Price must be a number!");
        } else {
            System.out.println("Medicament adaugat cu success");
        }
//        } catch (IllegalArgumentException e) {
//            System.err.println("Error: " + e.getMessage());
//        } catch (InputMismatchException e){
//            System.err.println("Wrong input");
//        }
    }

    /**
     * Console method that calls the delete() method from the Service class
     */
    private void deleteMed() {
        System.out.println("Nume:  ");
        String name = scanner.nextLine();
        name = scanner.nextLine();
        try {
            service.deleteMed(name);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Wrong input");
        }
    }

    /**
     * Console method that calls the search() method from the Service class
     */
    private void search() {
        System.out.println("Nume:  ");
        String name = scanner.next();
        System.out.println(service.search(name));
    }

    /**
     * Console method that calls the bubbleSort() method from the Service class
     */
    private void bubbleSort() {
        System.out.println(service.bubbleSort());
    }

    /**
     * Displays the first menu
     */
    private void mainMenu() {
        System.out.println("1. Customer");
        System.out.println("2. Admin");
    }

    /**
     * Displays the 'ADMIN' menu
     */
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

    /**
     * Displays the 'CUSTOMER' menu
     */
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

    /**
     * Displays the 'SHOP' menu
     */
    private void menuShop() {
        System.out.println("----- Magazin -----");
        System.out.println();
        System.out.println("0. Vizualiati cosul de cumparaturi");
        System.out.println("1. Adaugati un produs in cos");
        System.out.println("2. Stergeti un produs din cos");
        System.out.println("3. Produsul cu cost maxim din cos");
        System.out.println("4. Cost total");
        System.out.println("5. Checkout");
        System.out.println("X. Exit");
    }

    /**
     * Calls all the 'console methods' when the program starts
     */
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
                        System.err.println(e.getMessage());
                    }
                } else try {
                    throw new Exception("Incorrect account");
                } catch (Exception e) {
                    System.err.println(e.getMessage());
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
                    try {
                        addMed();
                    } catch (InputMismatchException e) {
                        System.err.println(e.getMessage());
                    }
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

//                        for (Constructor<?> constructor : FarmacieService.CartInnerClass.class.getDeclaredConstructors()) {
//                            System.out.println(constructor);
//                        }
//                        try {
//                            Constructor<CartInnerClass> ctor = FarmacieService.CartInnerClass.class.getDeclaredConstructor(FarmacieService.class);
//                            ctor.setAccessible(true);
//
//                            try {
//                                FarmacieService.CartInnerClass inner = ctor.newInstance(service);
//                            } catch (InstantiationException e) {
//                                e.printStackTrace();
//                            } catch (IllegalAccessException e) {
//                                e.printStackTrace();
//                            } catch (IllegalArgumentException e) {
//                                e.printStackTrace();
//                            } catch (InvocationTargetException e) {
//                                e.printStackTrace();
//                            }
//                        } catch (NoSuchMethodException e) {
//                            e.printStackTrace();
//                        } catch (SecurityException e) {
//                            e.printStackTrace();
//                        }

//                        FarmacieService.CartInnerClass inner = new FarmacieService.CartInnerClass(repo, service);

                        switch (selectionShop) {
                            case "0":
                                service.getCart().showCart();
                                break;

                            case "1":
                                System.out.print("Selectati produsul:  ");
                                String selectedProduct = scanShop.next();
                                System.out.println();
                                scanShop.nextLine();
                                System.out.print("Introduceti cantitatea:  ");
                                int quantityOfProduct = scanShop.nextInt();
//                                inner.addToCart(selectedProduct, quantityOfProduct);
                                service.innerAddToCart(repo, service, selectedProduct, quantityOfProduct);
                                break;

                            case "2":
                                System.out.print("Selectati produsul:  ");
                                String willBeDeletedProduct = scanShop.next();
//                                inner.deleteFromCart(willBeDeletedProduct);
                                service.innerDeleteFromCart(repo, service, willBeDeletedProduct);
                                break;

                            case "3":
                                System.out.println(service.getCart().max());
                                break;

                            case "4":
                                System.out.println(service.getCart().getTotalPrice());
                                break;

                            case "5":
                                System.out.println(service.getCart().checkout());
                                canAccessCart = false;
                                break;

                            case "x":
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
