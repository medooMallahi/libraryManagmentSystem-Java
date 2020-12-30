package librarySystem;

public class Request {
   Book book;
   User user;
   
   public Request(User user, Book book ) {
	   this.book  = book;
	   this.user = user;
   }// end of Request constructor
   
}// end of Request
