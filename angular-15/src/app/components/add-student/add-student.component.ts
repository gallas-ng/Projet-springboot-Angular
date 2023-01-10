import { Component } from '@angular/core';
import { Student} from 'src/app/models/student.model';
import { Tutorial } from 'src/app/models/tutorial.model';
import { StudentService } from 'src/app/services/student.service';

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.css']
})
export class AddStudentComponent {

  student: Student = {
    name: '',
    tutorial: new Tutorial
  };
  submitted = false;

  constructor(private studentService: StudentService) { }

  saveStudent(): void {
    const data = {
      name: this.student.name,
      tutorial: new Tutorial
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
      tutorial: new Tutorial
    };
  }

}