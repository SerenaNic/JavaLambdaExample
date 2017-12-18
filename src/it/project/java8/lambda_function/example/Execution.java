package it.project.java8.lambda_function.example;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import it.project.java8.lambda_function.example.util.CheckStudent;
import it.project.java8.lambda_function.example.util.Classroom;
import it.project.java8.lambda_function.example.util.Student;
import it.project.java8.lambda_function.example.util.Student.Sex;

public class Execution {

	public static void main(String[] args) {

		Student s1 = new Student("Mario", "Rossi", LocalDate.of(1988, Month.JUNE, 20),Sex.MALE,"mariorossi@gmail.com",2);
		Student s2 = new Student("Irina", "Verdi", LocalDate.of(1989, Month.JANUARY, 2),Sex.FEMALE,"irinave@gmail.com",3);
		Student s3 = new Student("Stefania", "Bianchi", LocalDate.of(1988, Month.MAY, 12),Sex.FEMALE,"sbianchi@gmail.com",10);


		int lowAge = 18, highAge = 25;
		/**1) Create a method that search for students older than age
		 * this method is restrictive if you want to change conditions or data type?
		 */
		LinkedList<Student> slist = new LinkedList<>();
		slist.addAll(Arrays.asList(s1, s2,s3));
		Classroom c = new Classroom(slist);
		c.printStudentOlderThan(lowAge);


		/**2) Create a more general method that search for students older than age
		 * and younger than age2 but this method is still
		 * restrictive if you want to change conditions type or data type?
		 */
		c.printStudentInRange(lowAge,highAge);

		/**3) You can separate the code that specifies the searching criteria
		 * in a different class.
		 * You can use and interface and specify search criteria code in an Anonymous class,
		 * but syntax is bulky.
		 */
		c.printStudentGeneral(new CheckStudent() {
			@Override
			public boolean test(Student s) {
				return s.getGender()==Student.Sex.MALE
						&& s.getAge()>=lowAge
						&& s.getAge()<highAge;
			}	
		});

		/**4) Specify search criteria through lambda expression.
		 */
		c.printStudentGeneral((Student s) -> s.getGender()==Student.Sex.MALE
				&& s.getAge()>=lowAge
				&& s.getAge()<highAge);

		/**5) Since the interface used is very simple, we can use one of the standard functional 
		 * interface defined by the JDK. For instance Predicate<T> (generic interface) in place of CheckStudent
		 */
		c.printStudentGeneralPredicate((Student s) -> s.getGender()==Student.Sex.MALE
				&& s.getAge()>=lowAge
				&& s.getAge()<highAge);

		/**6) We can use one of the standard functional 
		 * interface defined by the JDK called Consumer<T> (generic interface) to print user
		 */
		c.printStudentGeneralConsumer((Student s) -> s.getGender()==Student.Sex.MALE
				&& s.getAge()>=lowAge && s.getAge()<highAge, 
				s->s.printStudent());

		/**3) We can use one of the standard functional 
		 * interface defined by the JDK called Function<T,R> (generic interface) to map user to their email
		 * and print email
		 */
		c.printStudentGeneralFunction((Student s) -> s.getGender()==Student.Sex.MALE
				&& s.getAge()>=lowAge && s.getAge()<highAge, 
				s->s.getEmail(), 
				email -> System.out.println(email));

		/**8) We can use one of the standard functional 
		 * interface defined by the JDK called Iterable<X> (generic interface) to iterate over a list and generics
		 */
		c.printStudentGeneralFunctionGenerics(
				c.getListStudents(),//iterable
				s -> s.getGender()==Student.Sex.MALE && s.getAge()>=lowAge && s.getAge()<highAge, //predicate
				s->s.getEmail(), //function
				email -> System.out.println(email));//consumer

		/**9)Aggregate operation
		 */
		c.getListStudents()
		.stream()
		.filter(s->s.getGender()==Student.Sex.MALE && s.getAge()>=lowAge && s.getAge()<35)
		.map(s->s.getEmail())
		.forEach(email->System.out.println(email));
		
		c.getListStudents()
		.stream()
		.filter(s-> s.getName().equals("Irina"))
		.map(s->s.getAge())
		.forEachOrdered(System.out::println);// or forEach(System.out::println);

		//bifunction that computes the sum of two integer
		BiFunction<Integer, Integer , Integer> f = (a,b) -> a+b;
		System.out.println(f.apply(2, 3));
		
			
		/**10)Reduction
		 */
		double average = c.getListStudents()
				.stream()
				.filter(s-> s.getGender().equals(Sex.FEMALE))
				.mapToInt(Student::getAge)
				.average()
				.getAsDouble();
		System.out.println("Avg of the age of female students: "+average);
		
		//sum total number of exams reduce 
		//<identity (initial value and default result), accumulator (partial result of the reduction)>
		Integer totNumExams = c.getListStudents()
				.stream()
				.mapToInt(s-> s.getNumberExams())
				.reduce(0, (a,b)->a + b);//or use .sum();
		System.out.println("Total number of exams done by all students: "+totNumExams);
		
		List<String> femaleStud = c.getListStudents()
				.stream()
				.filter(s-> s.getGender().equals(Sex.FEMALE))
				.map(s->s.getName())
				.collect(Collectors.toList());
		System.out.println("Name list of all female students: "+femaleStud.toString());

		Map<Sex,List<String>> byGender = c.getListStudents()
				.stream()
				.collect(Collectors.groupingBy(s->s.getGender(),Collectors.mapping(Student::getName,Collectors.toList())));
		System.out.println("Map of studend grouped by gender "+byGender.toString());
		
	}
}
