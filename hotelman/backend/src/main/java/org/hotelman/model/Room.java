package org.hotelman.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "rooms")
public class Room {
        @Id
        private String roomNumber; // z. B. "101"


        public enum Size { SINGLE, DOUBLE, SUITE }


        private Size size;
        private boolean hasMinibar;
        private boolean available;


    public void setSize(Size size) {
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean isHasMinibar() {
        return hasMinibar;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setHasMinibar(boolean hasMinibar) {
        this.hasMinibar = hasMinibar;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}
