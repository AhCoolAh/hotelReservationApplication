package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.*;

public class ReservationService {

    Set<IRoom> setOfRooms = new HashSet<IRoom>();
    Set<Reservation> setOfReservations = new HashSet<Reservation>();
    private static ReservationService instance;

    private ReservationService() {}

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }
        return instance;
    }

    public void addRoom(IRoom room) {
        setOfRooms.add(room);
    }

    public IRoom getARoom(String roomId) {
        for (IRoom room : setOfRooms) {
            if (room.getRoomNumber().equals(roomId)) {
                 return room;
            }
        }
        return null;
    }

    public Reservation reserveARoom (Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        setOfReservations.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> foundRooms = new ArrayList<IRoom>();
        for (IRoom room : setOfRooms) {
            if (!setOfReservations.contains(room)) {
                foundRooms.add(room);
            } else {
                boolean isBookable = true;
                for (Reservation reservation : setOfReservations) {
                    if (checkInDate.before(reservation.getCheckOutDate()) || checkOutDate.after(reservation.getCheckInDate())) {
                        isBookable = false;
                    }
                }
                if (isBookable) {
                    foundRooms.add(room);
                }
            }
        }
        return foundRooms;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        List<Reservation> customersReservations = new ArrayList<Reservation>();
        for (Reservation reservation : setOfReservations) {
            if (reservation.getCustomer() == customer) {
                customersReservations.add(reservation);
            }
        }
        return customersReservations;
    }

    public void printAllReservation() {
        /*for (Reservation reservation : setOfReservations) {
            System.out.println(reservation);
        }*/
        System.out.println(setOfReservations);
    }

    public Set<IRoom> getSetOfRooms() {
        return setOfRooms;
    }

}
