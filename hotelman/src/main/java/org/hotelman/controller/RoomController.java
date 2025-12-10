package org.hotelman.controller;

import org.hotelman.model.Room;
import org.hotelman.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService service;
    public RoomController(RoomService service){this.service = service;}

    //Hotelzimmer lassen sich in einer Liste anzeigen
    // (wenn para available angegeben dann wird direkt nach verfügbaren Zimmern gefiltert)
    @GetMapping
    public List<Room> list(@RequestParam(value = "available", required = false) Boolean available){
        if(available == null) return service.listAll();
        return service.filterAvailable(available);
    }

    //Hotelzimmer lassen sich via REST API mittels ihrer Zimmernummer abfragen, damit die
    //Rezeption auskunftsfähig gegenüber neuen Gästen ist
    @GetMapping("/{roomNumber}")
    public ResponseEntity<Room> get(@PathVariable String roomNumber){
        return service.getByNumber(roomNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Hotelzimmer lassen sich via REST API neu anlegen, da das Hotel stetig wächs
    @PostMapping
    public ResponseEntity<Room> create(@Valid @RequestBody Room room){
        Room created = service.create(room);
        return ResponseEntity.created(URI.create("/api/rooms/"+created.getRoomNumber())).body(created);
    }

    //Hotelzimmer lassen sich via REST API mittels ihrer Zimmernummer anpassen, z.B. ist
    //dies nötig, wenn nachträglich eine Minibar eingebaut wird
    @PutMapping("/{roomNumber}")
    public ResponseEntity<Room> update(@PathVariable String roomNumber, @Valid @RequestBody Room room){
        if(service.getByNumber(roomNumber).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Room updated = service.update(roomNumber, room);
        return ResponseEntity.ok(updated);
    }
    @GetMapping("/available")
    public List<Room> getAvailableRooms() {
        return service.getAvailableRooms();
    }
}
