package serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Operation {

	public static void main(String[] args)  {
	
		DataObject object = new DataObject();
		object.setMyData("bla-;lkadshf;khasd;");
		File file = new File("store1.bin");
		
		System.out.println("Old Object: " + object.toString());
		
		
		try (ObjectOutputStream so = new ObjectOutputStream(new FileOutputStream(file))) {
			
			so.writeObject(object);
			so.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		try (ObjectInputStream si = new ObjectInputStream(new FileInputStream(file))) {
			
			DataObject objectNew = (DataObject) si.readObject();
			System.out.println("New Object: " + objectNew.toString());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
