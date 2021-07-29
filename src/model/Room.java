package model;

public class Room implements IRoom {
    protected String roomNumber;
    protected Double price;
    protected RoomType roomType;
    protected boolean isFree;

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
        return false;
    }


    @Override
    public String toString() {
        return "Room number:" + roomNumber + " Price:" + price + " Room type:" + roomType;
    }
}
