package streams.domain;

public class Employee {

	private String firstName;
	private String lastName;
	private int id;
	private int age;
	private Position Position;

	public Employee(String firstName, String lastName, int id, int age, streams.domain.Position position) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.age = age;
		Position = position;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Position getPosition() {
		return Position;
	}

	public void setPosition(Position position) {
		Position = position;
	}

}
