import { Component } from '@angular/core';
import { Student } from 'src/app/models/student.model';
import { StudentService } from 'src/app/services/student.service';
import { Tutorial } from 'src/app/models/tutorial.model';
import { TutorialService } from 'src/app/services/tutorial.service';

@Component({
  selector: 'app-group-list',
  templateUrl: './group-list.component.html',
  styleUrls: ['./group-list.component.css']
})
export class GroupListComponent {

  tutorials?: Tutorial[];
  title = '';
  
  students?: Student[];
  name = '';
  tutorial = '';


  constructor(private tutorialService: TutorialService,private studentService: StudentService) { }

  ngOnInit() {
    this.getGroups();
  }

  getGroups() {
    this.studentService.getGroups().subscribe(data => {
      this.students = data;
    });
      this.tutorialService.getAll().subscribe(data => {
      this.tutorials = data;
    });
  }

}
