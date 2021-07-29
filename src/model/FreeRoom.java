package model;

public class FreeRoom extends Room{
    public FreeRoom (String roomNumber, Double price, RoomType roomType, boolean isFree) {
        super(roomNumber, price, roomType, isFree);
        this.price = 0.0;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
