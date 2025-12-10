package org.hotelman.service;
import org.hotelman.model.Room;
import org.hotelman.repository.RoomRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RoomService {
    private final RoomRepository repo;
    public RoomService(RoomRepository repo){this.repo = repo;}


    public Room create(Room room){ return repo.save(room); }

    public Optional<Room> getByNumber(String number){ return repo.findById(number); }

    public List<Room> listAll(){ return repo.findAll(); }

    public List<Room> filterAvailable(boolean available){ return repo.findByAvailable(available); }

    public Room update(String number, Room updated){
        updated.setRoomNumber(number);
        return repo.save(updated);
    }

    public List<Room> getAvailableRooms() {
        return repo.findAll()
                .stream()
                .filter(room -> room.isAvailable())
                .collect(Collectors.toList());
    }
}
