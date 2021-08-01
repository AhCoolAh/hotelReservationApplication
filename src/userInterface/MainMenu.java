package userInterface;

import api.HotelResource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {

    Scanner scanner = new Scanner(System.in);

    private static MainMenu instance;
    public MainMenu() {}

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    public static void main(String[] args) {}

    public void mainMenuUi() {

        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println("Welcome to Hotel Reservation Application! What would you like to do? (select a number)" + "\n" +
                    "1. Find and reserve a room" + "\n" +
                    "2. See my reservations" + "\n" +
                    "3. Create an account" + "\n" +
                    "4. Admin" + "\n" +
                    "5. Exit\n");

            try {
                String selection = scanner.nextLine();
                switch (selection) {
                    case "1": instance.findAndReserveARoom();
                        break;
                    case "2": instance.seeMyReservations();
                        break;
                    case "3": instance.createAnAccount();
                        break;
                    case "4": AdminMenu.getInstance().adminMenuUi();
                        break;
                    case "5": continueLoop = false;
                    break;
                    default: throw new Exception("------------------------------------\n" +
                                                 "Please enter a value in range [1-5].\n" +
                                                 "------------------------------------");
                }
            }
            catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        }
    }

    public void findAndReserveARoom() {
        Date checkInDate;
        Date checkOutDate;
        String checkInInput;
        String checkOutInput;
        String emailInput;
        String roomNumber;
        String selection;
        boolean continueLoop = true;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        while(continueLoop) {
            try {
                /*System.out.println("Would you like to book a new room? (Y/N?)");
                selection = scanner.nextLine();*/
                System.out.println("Please, enter check-in date (dd/mm/yyyy):");
                checkInInput = scanner.nextLine();
                checkInDate = format.parse(checkInInput);
                System.out.println("Please, enter check-out date (dd/mm/yyyy):");
                checkOutInput = scanner.nextLine();
                checkOutDate = format.parse(checkOutInput);
                if (HotelResource.getInstance().findARoom(checkInDate, checkOutDate).isEmpty()) {
                    continueLoop = false;
                    throw new Exception("-------------------------------------------\n" +
                            "There are no rooms available for these dates.\n" +
                            "-------------------------------------------");
                }

                System.out.println("List of all available rooms:\n" + HotelResource.getInstance().findARoom(checkInDate, checkOutDate));
                System.out.println("Would you like to book one of these rooms? (Y/N?)");
                selection = scanner.nextLine();
                switch(selection) {
                    case "y": case "Y":
                        System.out.println("do you have an Account? (Y/N?)");
                        selection = scanner.nextLine();
                        switch (selection) {
                            case "n": case "N":
                                instance.createAnAccount();
                            case "y": case "Y":
                                System.out.println("Enter your email");
                                emailInput = scanner.nextLine();
                                System.out.println("What room would you like to reserve? (enter number)");
                                roomNumber = scanner.nextLine();
                                //System.out.println(HotelResource.getInstance().getRoom(roomNumber));
                                HotelResource.getInstance().bookARoom(emailInput, HotelResource.getInstance().getRoom(roomNumber), checkInDate, checkOutDate);
                                System.out.println("You`ve successfully booked a room!");
                                continueLoop = false;
                                break;
                            default: throw new Exception("-------------------------------\n" +
                                    "Please choose y(yes) or n(no).\n" +
                                    "-------------------------------");
                        }

                        break;
                    case "n": case "N":
                        continueLoop = false;
                        break;
                    default: throw new Exception("-------------------------------\n" +
                            "Please choose y(yes) or n(no).\n" +
                            "-------------------------------");
                }
            }
            catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        }
    }

    public void seeMyReservations() {
        String emailInput;
        System.out.println("Please, enter your email.");
        try {
            emailInput = scanner.nextLine();
            if (HotelResource.getInstance().getCustomersReservations(emailInput).isEmpty()) {
                throw new Exception("-------------------------------------------\n" +
                        "There are no reservations for your account.\n" +
                        "-------------------------------------------");
            } else {
                System.out.println(HotelResource.getInstance().getCustomersReservations(emailInput));
            }


        }
        catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void createAnAccount() {
        String emailInput;
        String firstNameInput;
        String lastNameInput;
        String selection;
        boolean continueLoop = true;
        while(continueLoop) {
            try {
                /*System.out.println("Would you like to create a new account? (Y/N?)");
                selection = scanner.nextLine();
                switch(selection) {
                    case "y": case "Y":
                        System.out.println("Please, enter your email (example@example.com):");
                        emailInput = scanner.nextLine();
                        System.out.println("Please, enter your First Name:");
                        firstNameInput = scanner.nextLine();
                        System.out.println("Please, enter your Last Name:");
                        lastNameInput = scanner.nextLine();
                        HotelResource.getInstance().createACustomer(emailInput, firstNameInput, lastNameInput);
                        System.out.println("You have successfully created your Account!");
                        break;
                    case "n": case "N":
                        continueLoop = false;
                        break;
                    default:  throw new Exception("-------------------------------\n" +
                            "Please choose y(yes) or n(no).\n" +
                            "-------------------------------");
                }*/
                System.out.println("Please, enter your email (example@example.com):");
                emailInput = scanner.nextLine();
                System.out.println("Please, enter your First Name:");
                firstNameInput = scanner.nextLine();
                System.out.println("Please, enter your Last Name:");
                lastNameInput = scanner.nextLine();
                HotelResource.getInstance().createACustomer(emailInput, firstNameInput, lastNameInput);
                System.out.println("You have successfully created your Account!");
                continueLoop = false;
            }
            catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        }


    }




}
