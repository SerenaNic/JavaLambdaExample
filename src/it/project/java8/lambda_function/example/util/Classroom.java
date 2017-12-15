package it.project.java8.lambda_function.example.util;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Classroom {

	private List<Student> listStudents;

	public Classroom(List<Student> listStudents) {
		super();
		this.listStudents = listStudents;
	}

	public Classroom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Student> getListStudents() {
		return listStudents;
	}

	public void setListStudents(List<Student> listStudents) {
		this.listStudents = listStudents;
	}

	//method 1
	public void printStudentOlderThan(int age) {
		for(Student s: listStudents) {
			if(s.getAge()>=age)
				s.printStudent();
		}
	}

	//method 2
	public void printStudentInRange(int low, int high) {
		for(Student s: listStudents) {
			if(s.getAge()>=low&&s.getAge()<high)
				s.printStudent();
		}
	}

	//method 3 - I do not change anymore this class and this method
	public void printStudentGeneral(CheckStudent checkStudent) {
		for(Student s: listStudents) {
			if(checkStudent.test(s))
				s.printStudent();
		}
	}

	//method 5 - I do not change anymore this class and this method
	//and there is no need to define an interface
	public void printStudentGeneralPredicate(Predicate<Student> checkStudent) {
		for(Student s: listStudents) {
			if(checkStudent.test(s))
				s.printStudent();
		}
	}

	//method 6 - use more lambda expression 
	public void printStudentGeneralConsumer(Predicate<Student> checkStudent, Consumer<Student> block) {
		for(Student s: listStudents) {
			if(checkStudent.test(s))
				block.accept(s);//s.printStudent();
		}
	}

	public void printStudentGeneralFunction(Predicate<Student> checkStudent, Function<Student,String> mapper, Consumer<String> block) {
		for(Student s: listStudents) {
			if(checkStudent.test(s)) {
				String data = mapper.apply(s);
				block.accept(data);//s.printStudent();
			}
		}
	}
	
	//method 7 - use generics and iterable interface
	public <X,Y> void  printStudentGeneralFunctionGenerics(Iterable<X> list, Predicate<X> checkStudent, Function<X,Y> mapper, Consumer<Y> block) {
		for(X s: list) {
			if(checkStudent.test(s)) {
				Y data = mapper.apply(s);
				block.accept(data);//s.printStudent();
			}
		}
	}

}
