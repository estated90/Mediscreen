import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private snackBar: MatSnackBar) { }

  openSnackBar(message: string, action: string, style: string) {
    this.snackBar.open(message, action, {
    duration: 3000,
    panelClass: [style],
    verticalPosition: 'top', // Allowed values are  'top' | 'bottom'
    horizontalPosition: 'end', // Allowed values are 'start' | 'center' | 'end' | 'left' | 'right'
  });
}

}
