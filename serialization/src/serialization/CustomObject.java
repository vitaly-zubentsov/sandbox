package serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class CustomObject implements Externalizable{
								
	private transient boolean b;

	public boolean isB() {
		return b;
	}

	public void setB(boolean b) {
		this.b = b;
	}

	@Override
	public String toString() {
		return "CustomObject [b=" + b + "]";
	}

	//В отличии от serializable метод запуститься сразу,
	//без сохранения по цепочке метаданных и данных
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeBoolean(b);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		in.readBoolean();
	}
	
	
}
