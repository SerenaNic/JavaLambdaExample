package it.project.java8.lambda_function.example.util;

import java.time.LocalDate;
import java.time.Period;

public class Student {
	
	public enum Sex{ MALE, FEMALE}

	private String name;
	private String surname;
	private LocalDate birthday;
	private Sex gender;
	private String email;
	private int age;
	private int numberExams;
	
	
	public Student() {
		
	}
	public Student(String name, String surname, LocalDate birthday, Sex gender, String email, int numberExams) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;
		Period p = Period.between(this.getBirthday(), LocalDate.now());
		this.age = p.getYears();
		this.numberExams = numberExams;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public Sex getGender() {
		return gender;
	}
	public void setGender(Sex gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}

	
	@Override
	public String toString() {
		return "Student [name=" + name + ", surname=" + surname + ", birthday=" + birthday + ", gender=" + gender
				+ ", email=" + email + ", age=" + age + ", numberExams=" + numberExams +"]";
	}
	public void printStudent() {
		System.out.println(this.toString());
	}
	public int getNumberExams() {
		return numberExams;
	}
	public void setNumberExams(int numberExams) {
		this.numberExams = numberExams;
	}
}
