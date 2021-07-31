package userInterface;

import api.AdminResource;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    Scanner scanner = new Scanner(System.in);

    private static AdminMenu instance;
    public AdminMenu() {}

    public static AdminMenu getInstance() {
        if (instance == null) {
            instance = new AdminMenu();
        }
        return instance;
    }



    public void adminMenuUi() {
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println("Admin menu (select a number)" + "\n" +
                    "1. See all customers" + "\n" +
                    "2. See all rooms" + "\n" +
                    "3. See all reservations" + "\n" +
                    "4. Add a room" + "\n" +
                    "5. Back to main menu\n");

            try {
                String selection = scanner.nextLine();
                switch (selection) {
                    case "1": System.out.println(AdminResource.getInstance().getAllCustomers());
                        break;
                    case "2": System.out.println(AdminResource.getInstance().getAllRooms());
                        break;
                    case "3": AdminResource.getInstance().displayAllReservations();
                        break;
                    case "4": instance.addARoom();
                        break;
                    case "5":
                        continueLoop = false;
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

    public void addARoom() {
        boolean continueLoop = true;
        boolean isFree;
        String selection;
        String roomNumberInput;
        String roomTypeSelection;
        Double priceInput;
        RoomType roomType;
        List<IRoom> rooms = new ArrayList<IRoom>();
        Scanner scanner = new Scanner(System.in);

        while(continueLoop) {
            System.out.println("Would you like to add another room? (Y/N?)");
            selection = scanner.nextLine();
            try {
                switch (selection) {
                    case "y": case "Y":
                        System.out.println("Enter room number:");
                        roomNumberInput = scanner.nextLine();
                        System.out.println("Enter price per night:");
                        priceInput = scanner.nextDouble();
                        if (priceInput == 0) {
                            isFree = true;
                        } else {
                            isFree = false;
                        }

                        System.out.println("Enter room type - 1 for single, 2 for double:");
                        scanner.nextLine();
                        roomTypeSelection = scanner.nextLine();


                        switch (roomTypeSelection) {
                            case "1":
                                roomType = RoomType.SINGLE;
                                break;
                            case "2":
                                roomType = RoomType.DOUBLE;
                                break;
                            default: throw new IllegalArgumentException("--------------------------------\n" +
                                    "There are only 2 types of a room\n" +
                                    "--------------------------------");

                        }
                        Room room = new Room(roomNumberInput, priceInput, roomType, isFree);
                        rooms.add(room);
                        break;

                    case "n": case "N":
                        continueLoop = false;
                        break;
                    default: throw new IllegalArgumentException("------------------------------------\n" +
                            "Please enter y(yes) or n(no).\n" +
                            "------------------------------------");
                }
            }
            catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        }
        AdminResource.getInstance().addRoom(rooms);
    }


}
