import { Component } from '@angular/core';
import { Student} from 'src/app/models/student.model';
import { StudentService } from 'src/app/services/student.service';

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.css']
})
export class AddStudentComponent {

  student: Student = {
    name: '',
  
  };
  submitted = false;

  constructor(private studentService: StudentService) { }

  saveStudent(): void {
    const data = {
      name: this.student.name,
    };

    this.studentService.create(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }

  newStudent(): void {
    this.submitted = false;
    this.student = {
      name: '',
    };
  }

}