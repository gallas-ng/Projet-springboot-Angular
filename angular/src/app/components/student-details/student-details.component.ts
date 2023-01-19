import { Component, Input, OnInit } from '@angular/core';
import { StudentService } from 'src/app/services/student.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Student } from 'src/app/models/student.model';
import { Tutorial } from 'src/app/models/tutorial.model';

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.css']
})
export class StudentDetailsComponent implements OnInit {

  @Input() viewMode = false;

  @Input() currentStudent: Student = {
    name: '',
    tutorial: new Tutorial
  };
  
  message = '';

  constructor(
    private studentService: StudentService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getStudent(this.route.snapshot.params["id"]);
    }
  }

  getStudent(id: string): void {
    this.studentService.get(id)
      .subscribe({
        next: (data) => {
          this.currentStudent = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  
  updateStudent(): void {
    this.message = '';

    this.studentService.update(this.currentStudent.id, this.currentStudent)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'This Student was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }

  deleteStudent(): void {
    this.studentService.delete(this.currentStudent.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/students']);
        },
        error: (e) => console.error(e)
      });
  }

}