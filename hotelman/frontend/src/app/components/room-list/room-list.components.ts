import { Component, OnInit } from '@angular/core';
import { RoomService } from '../../services/room.service';
import { Room } from '../../models/room.model';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html'
})
export class RoomListComponent implements OnInit {

  rooms: Room[] = [];
  filterAvailable = false;

  constructor(private roomService: RoomService) {}

  ngOnInit(): void {
    this.loadRooms();
  }

  loadRooms(): void {
    if (this.filterAvailable) {
      this.roomService.getAvailable().subscribe(data => this.rooms = data);
    } else {
      this.roomService.getAll().subscribe(data => this.rooms = data);
    }
  }

  toggleFilter(): void {
    this.filterAvailable = !this.filterAvailable;
    this.loadRooms();
  }
}