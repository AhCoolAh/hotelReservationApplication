package model;

import java.util.Objects;

public class Room implements IRoom {
    protected final String roomNumber;
    protected Double price;
    protected final RoomType roomType;
    protected final boolean isFree;

    public Room(String roomNumber, Double price, RoomType roomType, boolean isFree) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
        this.isFree = isFree;
    }

    @Override
    public String getRoomNumber(){
        return roomNumber;
    }

    @Override
    public Double getRoomPrice(){
        return price;
    }

    @Override
    public RoomType getRoomType(){
        return roomType;
    }

    @Override
    public boolean isFree(){
        return isFree;
    }

    // Idea for overriding: https://mkyong.com/java/java-how-to-overrides-equals-and-hashcode/
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Room)) {
            return false;
        }
        Room room = (Room) o;
        return Objects.equals(roomNumber, room.roomNumber) &&
                Objects.equals(price, room.price) &&
                Objects.equals(roomType, room.roomType) &&
                Objects.equals(isFree, room.isFree);

    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, price, roomType, isFree);
    }

    @Override
    public String toString() {
        return "Room number:" + roomNumber + " Price:" + price + " Room type:" + roomType;
    }
}
