import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Room } from '../models/room.model';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private apiUrl = 'http://localhost:8080/api/rooms';

  constructor(private http: HttpClient) { }

  getAll(): Observable<Room[]> {
    return this.http.get<Room[]>(this.apiUrl);
  }

  getByRoomNumber(roomNumber: string): Observable<Room> {
    return this.http.get<Room>(`${this.apiUrl}/${roomNumber}`);
  }

  create(room: Room): Observable<Room> {
    return this.http.post<Room>(this.apiUrl, room);
  }

  update(roomNumber: string, room: Room): Observable<Room> {
    return this.http.put<Room>(`${this.apiUrl}/${roomNumber}`, room);
  }

  getAvailable(): Observable<Room[]> {
    return this.http.get<Room[]>(`${this.apiUrl}/available`);
  }
}