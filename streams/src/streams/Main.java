package streams;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import streams.domain.Department;
import streams.domain.Employee;
import streams.domain.Position;

public class Main {

	private List<Employee> emps = List.of(new Employee("Michael", "Smith", 243, 43, Position.CHIEF),
			new Employee("Jane", "Smith", 523, 40, Position.MANAGER),
			new Employee("Jury", "Gagarin", 6423, 26, Position.MANAGER),
			new Employee("Jack", "London", 5543, 53, Position.WORKER),
			new Employee("Eric", "Jackson", 2534, 22, Position.WORKER),
			new Employee("Andrew", "Bosh", 3456, 44, Position.WORKER),
			new Employee("Joe", "Smith", 723, 30, Position.MANAGER),
			new Employee("Jack", "Gagarin", 7423, 35, Position.MANAGER),
			new Employee("Jane", "London", 7543, 42, Position.WORKER),
			new Employee("Mike", "Jackson", 7534, 31, Position.WORKER),
			new Employee("Jack", "Bosh", 7456, 54, Position.WORKER),
			new Employee("Mark", "Smith", 123, 41, Position.MANAGER),
			new Employee("Jane", "Gagarin", 1423, 28, Position.MANAGER),
			new Employee("Sam", "London", 1543, 52, Position.WORKER),
			new Employee("Jack", "Jackson", 1534, 27, Position.WORKER),
			new Employee("Eric", "Bosh", 1456, 32, Position.WORKER));

	private List<Department> deps = List.of(new Department(1, 0, "Head"),
			new Department(2, 1, "West"),
			new Department(3, 1, "East"), 
			new Department(4, 2, "Germany"), 
			new Department(5, 2, "France"),
			new Department(6, 3, "China"), new Department(7, 3, "Japan"));

	public static void main(String[] args) {

		Main main = new Main();

		// создание мапы из листа
		Map<Integer, String> empsMap = main.emps.stream().collect(Collectors.toMap(Employee::getId,
				emp -> String.format("%s %s", emp.getFirstName(), emp.getLastName())));

		// объединение стримов с объектами с помощью стороннего класа использующего
		// рекурсию
		Department chiefDep = main.deps.stream().reduce(Main::reducer).orElse(null);
		System.out.println(chiefDep);

		main.emps.stream().max((o1, o2) -> o1.getAge() - o2.getAge());

		// стрим не может быть использован повторно
		Stream<Employee> stream = main.emps.stream();
		stream.forEach(System.out::println);
		// stream.filter(o -> o.getAge()<10);
		// stream.forEach(System.out::println);

		// ofNullable возвращает пустой, список если объект = null
		String str = Math.random() > 0.9 ? "I'm feeling lucky" : null;
		Stream.ofNullable(str).forEach(System.out::println);

		// сортировка с промежуточной операцие peek
		Stream.of(120, 410, 85, 32, 314, 12).sorted().peek(x -> System.out.printf("%s, ", x))
				.sorted(Comparator.reverseOrder()).forEach(System.out::println);

		String[] arguments = { "-i", "in.txt", "--limit", "40", "-d", "1", "-o", "out.txt" };
		// перевод массива строк в мапу, стримом переводить не целесообразно
		Map<String, String> argsMap = new LinkedHashMap<>(arguments.length / 2);
		for (int i = 0; i < arguments.length; i += 2) {
			argsMap.put(arguments[i], arguments[i + 1]);
		}

		// перевод из мапы в массив строк
		String[] stringsFromStream = argsMap.entrySet().stream().flatMap(x -> Stream.of(x.getKey(), x.getValue()))
				.toArray(String[]::new);

		// получение отсортированного списка должностей
		main.emps.stream().map(Employee::getPosition).distinct().sorted(Comparator.comparing(Enum::name))
				.forEach(System.out::println);

		// количество работников на каждой позиции
		main.emps.stream().collect(Collectors.groupingBy(Employee::getPosition)).entrySet().stream()
				.collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue().size())).entrySet().stream()
				.forEach(System.out::println);

		// то жесамое но лушче
		main.emps.stream().collect(Collectors.groupingBy(Employee::getPosition, Collectors.counting()))
				.forEach((position, count) -> System.out.printf("%s : %s \n", position, count));

		// паралельный стрим не должен зависеть от внешних переменных
		// final List<Integer> ints = new ArrayList<>();
		// IntStream.range(0, 1000000)
		// .parallel()
		// .forEach(i -> ints.add(i));

		// в результате можем получить правильное значение, неправильное или исключение
		// System.out.println(ints.size());

		IntStream.range(-5, 0).flatMap(i -> IntStream.of(i, -i)).boxed().sorted(Comparator.comparing(Math::abs))
				.forEach(System.out::println);
	}

	public static Department reducer(Department parentDep, Department childDep) {
		if (childDep.getParent() == parentDep.getId()) {
			parentDep.getChild().add(childDep);
		} else {
			parentDep.getChild().forEach(subParent -> reducer(subParent, childDep));
		}

		return parentDep;
	}

}
