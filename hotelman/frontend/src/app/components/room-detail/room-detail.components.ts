import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RoomService } from '../../services/room.service';
import { Room } from '../../models/room.model';

@Component({
  selector: 'app-room-detail',
  templateUrl: './room-detail.component.html'
})
export class RoomDetailComponent implements OnInit {

  room?: Room;

  constructor(
    private route: ActivatedRoute,
    private roomService: RoomService
  ) {}

  ngOnInit(): void {
    const roomNumber = this.route.snapshot.paramMap.get('roomNumber')!;
    this.roomService.getByRoomNumber(roomNumber).subscribe(data => this.room = data);
  }
}