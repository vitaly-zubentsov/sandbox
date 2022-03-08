package serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class DataObject extends NonSerializable implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int i = 5;
	private String s = "aaa";
	private String[] def = new String[]{"1","2","3"};
	
	private transient CustomObject customObject = new CustomObject();
	
	//порядок вызова данного метода при сериализации:
	//1 - сохраняются метаданные сериализуемого объекта
	//2 - сохраняются меданные родителей
	//3 - сохраняются данные родителей
	//4 - сохраняются данные объекта
	//5 - вызывается данный метод у объекта
	private void writeObject(ObjectOutputStream out) throws IOException {
		// стандарная реализация сериализации
		// если не вызвать стандартную сериализацию, то объект не сериализуется
		// (сериализуются только родители, если они реализуют Serializable)
		out.defaultWriteObject();
		out.writeObject(getMyData());
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		// стандартная реализация десериализации
		// если не вызвать, то десериализации не будет
		in.defaultReadObject(); 
		// Как определяется порядок установки параметров при десериализации?
		// он повторяет порядок сериализации в writeObject
		// out.writeObject(Object1); 
		// out.writeObject(Object2); 
		// in.readObject(Object1); 
		// in.readObject(Object2); 
		super.setMyData((String) in.readObject()); 
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String[] getDef() {
		return def;
	}

	public void setDef(String[] def) {
		this.def = def;
	}

	public CustomObject getCustomObject() {
		return customObject;
	}

	public void setCustomObject(CustomObject customObject) {
		this.customObject = customObject;
	}

	@Override
	public String toString() {
		return "DataObject [i=" + i + ", s=" + s + ", def=" + Arrays.toString(def) + ", customObject=" + customObject
				 + ", MyData" + super.getMyData() + "]";
	}
	
	
	
}
