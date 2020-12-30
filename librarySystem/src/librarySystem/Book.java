package librarySystem;
import java.util.Stack;

public class Book {
	String name;
	int ISBN;
	boolean isAvailable;
	Stack<User> borrowingHistory;
	
	
	Book(int ISBN, String name,boolean isAvailable,Stack<User> borrowingHistory){
		this.ISBN = ISBN;
		this.name = name;
		this.isAvailable = isAvailable;
		this.borrowingHistory = borrowingHistory;
	}
	

}
