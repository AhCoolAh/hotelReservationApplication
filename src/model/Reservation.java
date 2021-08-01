package model;

import java.util.Date;
import java.util.Objects;

import model.IRoom;

public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public IRoom getRoom() {return room;}

    // Idea for overriding: https://mkyong.com/java/java-how-to-overrides-equals-and-hashcode/
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Reservation)) {
            return false;
        }
        Reservation reservation = (Reservation) o;
        return customer.equals(reservation.customer) &&
                room.equals(reservation.room) &&
                Objects.equals(checkInDate, reservation.checkInDate) &&
                Objects.equals(checkOutDate, reservation.checkOutDate);

    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, room, checkInDate, checkOutDate);
    }


    @Override
    public String toString() {
        return customer.toString() + room.toString() + " Check-In Date:" + checkInDate + " Check-Out Date:" + checkOutDate;
    }
}
