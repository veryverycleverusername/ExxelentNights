import { Component, OnInit } from '@angular/core';
import { RoomService } from '../../services/room.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Room } from '../../models/room.model';

@Component({
  selector: 'app-room-form',
  templateUrl: './room-form.component.html'
})
export class RoomFormComponent implements OnInit {

  room: Room = {
    roomNumber: '',
    type: '',
    minibar: false,
    available: true
  };

  isEditing = false;

  constructor(
    private roomService: RoomService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const roomNumber = this.route.snapshot.paramMap.get('roomNumber');

    if (roomNumber) {
      this.isEditing = true;
      this.roomService.getByRoomNumber(roomNumber).subscribe(data => this.room = data);
    }
  }

  submit(): void {
    if (this.isEditing) {
      this.roomService.update(this.room.roomNumber, this.room).subscribe(() => {
        this.router.navigate(['/rooms']);
      });
    } else {
      this.roomService.create(this.room).subscribe(() => {
        this.router.navigate(['/rooms']);
      });
    }
  }
}