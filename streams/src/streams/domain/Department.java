package streams.domain;

import java.util.HashSet;
import java.util.Set;

public class Department {
	private final int id;
	private final int parent;
	
	private final String name;
	
	private Set<Department> child = new HashSet<>();

	public Department(int id, int parent, String name) {
		this.id = id;
		this.parent = parent;
		this.name = name;
	}

	public Set<Department> getChild() {
		return child;
	}

	public void setChild(Set<Department> child) {
		this.child = child;
	}

	public int getId() {
		return id;
	}

	public int getParent() {
		return parent;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", child=" + child + "]";
	}
	
	
	

}
