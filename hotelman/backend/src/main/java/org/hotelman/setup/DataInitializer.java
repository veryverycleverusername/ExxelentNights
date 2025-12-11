package org.hotelman.setup;

import org.hotelman.model.Room;
import org.hotelman.repository.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//AF 2 Beim Hochfahren der Anwendung werden drei Hotelzimmer mit beliebiger
//Zimmernummer angelegt
@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner init(RoomRepository repo){
        return args -> {
            repo.deleteAll();

            //Eines besitzt eine Minibar und ist ein Doppelzimmer
            Room r1 = new Room();
            r1.setRoomNumber("200");
            r1.setSize(Room.Size.DOUBLE);
            r1.setHasMinibar(true);
            r1.setAvailable(true);

            //Eines besitzt eine Minibar und ist ein Einzelzimmer
            Room r2 = new Room();
            r2.setRoomNumber("100");
            r2.setSize(Room.Size.SINGLE);
            r2.setHasMinibar(true);
            r2.setAvailable(true);

            //Eines besitzt keine Minibar und ist eine Suite
            Room r3 = new Room();
            r3.setRoomNumber("300");
            r3.setSize(Room.Size.SUITE);
            r3.setHasMinibar(false);
            r3.setAvailable(false);


            repo.save(r1);
            repo.save(r2);
            repo.save(r3);
        };
    }
}