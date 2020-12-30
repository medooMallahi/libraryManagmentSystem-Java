package librarySystem;
import java.util.Stack;

public class User {
	int ID;
	String name;
	String Uertype;
	Stack<Book> borrowingHistory;
    int grade;
    int password;
    boolean isActive;
 
    
    public User(int ID,String name, String Uertype,Stack<Book> borrowingHistory, int grade, int password, boolean isActive) {
    	this.ID = ID;
    	this.name = name;
    	this.Uertype = Uertype;
    	this.borrowingHistory = borrowingHistory;
    	this.grade = grade;
    	this.password = password;
    	this.isActive = isActive;
    }// end of the Constructor

}// end of the Class