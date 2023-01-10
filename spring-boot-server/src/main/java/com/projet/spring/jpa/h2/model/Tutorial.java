package com.projet.spring.jpa.h2.model;

import javax.persistence.*;

@Entity
@Table(name = "tutorials")
public class Tutorial {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "title")
	private String title;

	//@OneToMany(mappedBy = "tutorial")
	//private List<Student> students;

	public Tutorial() {}


	public Tutorial(String title) {
		this.title = title;

	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getGroupSize() {return 2;}

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", title=" + title + "]";
	}

}
