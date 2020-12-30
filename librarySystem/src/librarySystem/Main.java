package librarySystem;
import java.util.*;


public class Main {

	public static LinkedList<Book> books;
	public static ArrayList<User> users;
	public static Queue<Request> requests;

	static{

		// Users
		var user1 =  new User(1,"mohammed", "student", new Stack<Book>(), 70, 1111, true);
		var user2 =  new User(2,"hassan", "librarian", new Stack<Book>(), 80, 2222, true);
		var user3 =  new User(3,"tamer", "employee", new Stack<Book>(), 0, 3333, false );

		// Books
		var book1 = new Book(1,"data structure",true, new Stack<User>());
		var book2 = new Book(2,"calculus",false, new Stack<User>());
		var book3 = new Book(3,"biology",false, new Stack<User>());
		
		

		Collection<User> usersCollection = new ArrayList<User>(); 
		usersCollection.add(user1); 
		usersCollection.add(user2); 
		usersCollection.add(user3); 

		Collection<Book> booksCollection = new ArrayList<Book>(); 
		booksCollection.add(book1); 
		booksCollection.add(book2); 
		booksCollection.add(book3); 

		books = new LinkedList<Book>();
		users = new ArrayList <>();
		requests = new ArrayDeque<>();

		books.addAll(booksCollection);
		users.addAll(usersCollection);

	} // end of static Block

	public static void showBooks() {
		for(Book book : books) {
			System.out.print(book.ISBN+", "+book.name);
			if(book.isAvailable == false)
				System.out.println(", not available");
			else
				System.out.println(", available");

			if(book.borrowingHistory.isEmpty())
				System.out.println("No Borrowing History");
			else 
				for (User item: book.borrowingHistory) 
					System.out.println(item);
			
		}// end of for loop
	}// end of showBooks

	public static void borrowABook(User user) {
		var reader = new Scanner(System.in);
		
		System.out.println("Want to borrow a book? Enter its ISBN:");
		var bookISBN =  reader.nextInt();

		if(user.Uertype.equalsIgnoreCase("student")) {
			for(Book book : books) 
				if(book.ISBN == bookISBN ) {
					if(user.grade >= 85) {
						if(book.isAvailable) {
							user.borrowingHistory.add(book);
							book.isAvailable = false;
							break;
						}else {
							System.out.println("This Book is not available right now!");
							break;
						}

					}else {
						System.out.println("Your request has been submitted to librarian, check with him...");
						requests.add(new Request(user, book));
					}

				}
		}else 
			for(Book book : books) 
				if(book.ISBN == bookISBN ) {
					user.borrowingHistory.add(book);
					book.isAvailable = false;
					break;
				}
	}// end of borrowABook

	public static void seachByName() {
		System.out.println("Enter the name you want to look for...");

		var reader = new Scanner(System.in);
		var name = reader.nextLine();

		for(Book book : books) {
			if(book.name.equalsIgnoreCase(name)) {
				System.out.print(book.ISBN+", "+book.name);
				if(book.isAvailable == false)
					System.out.println(", not available");
				else
					System.out.println(", available");

				if(book.borrowingHistory.isEmpty())
					System.out.println("No Borrowing History");
				else {

					for (User item: book.borrowingHistory) 
						System.out.println(item);

				}
			}

		} // end of outer for loop

	}// end of seachByName
	
	public static void userMenu(User user) {

		var reader = new Scanner(System.in);
		char repeat;
		do {
			System.out.println("Welcome "+ user.name);
			System.out.println("1- Show available books");
			System.out.println("2- Borrow a book (by ISBN)");
			System.out.println("3- Search for books by name");

			var choice = reader.nextInt(); 
			switch (choice) {
			case 1 : 
				showBooks();
				break;
			case 2:
				borrowABook(user);
				break;
			case 3:
				seachByName();
				break;
			default :
				System.out.println(" no such as choice.. repeat!");
				break;
			}// end of switch

			System.out.println("\nare you willing to repeat?!(y or n) \n");
			repeat = reader.next().charAt(0);  

		}while(repeat == 'y');
	}// end of userMenu

	public static void checkBorrowingHistoryForABook() {

		showBooks();

		var reader = new Scanner(System.in);
		System.out.println("Enter book ISBN");
		var BookISBN = reader.nextInt();

		for(Book book : books) {
			if(book.ISBN == BookISBN) {
				System.out.print(book.ISBN+", "+book.name);
				if(book.isAvailable == false)
					System.out.println(", not available");
				else
					System.out.println(", available");

				if(book.borrowingHistory.isEmpty())
					System.out.println("No Borrowing History");
				else {

					for (User item: book.borrowingHistory) 
						System.out.println(item);

				}
			}

		}// end of outer for loop

	}// end of checkBorrowingHistoryForABook

	public static void checkBorrowingHistoryForAUser() {
		var reader = new Scanner(System.in);

		for(User user : users) {
			if(user.Uertype.equalsIgnoreCase("librarian"))
				continue;

			System.out.println(user.ID+ ","+user.name+","+user.Uertype);

		}


		System.out.println("Which users’ history you want to check?");
		var userID = reader.nextInt();
		for(User user : users) {
			if(user.ID == userID) {
				System.out.println(user.name+" borrowed:");
				for(Book book : user.borrowingHistory)
					System.out.println(book.ISBN+","+ book.name);

			}
		}
	}// end of checkBorrowingHistoryForAUser

	public static void addBook() {
		var reader = new Scanner(System.in);
		try {
			System.out.println("Enter Book ISBN..");
			var bookISBN = reader.nextInt();
			reader.nextLine();

			System.out.println("Enter Book Name..");
			var bookName = reader.nextLine();

			books.add(new Book(bookISBN,bookName, true, new Stack<User>()));
		}finally {
			reader.close();
		}
	}// end of addBook()

	public static void manageUsers() {
		var reader = new Scanner(System.in);
		try {
			for(User user : users) 
				if(user.Uertype.equalsIgnoreCase("librarian"))
					continue;
				else
					System.out.println(user.ID+", "+user.name+", "+ user.Uertype+", "+ user.isActive);

			System.out.println("Which users’ status you want to toggle?");
			var userID = reader.nextInt();

			for(User user : users) 
				if(user.ID == userID)
					user.isActive = !user.isActive;
		}finally {
			reader.close();
		}

	}// end of manageUsers()
	
	public static void BorrowingRequestQueue() {
		var reader = new Scanner(System.in);
		
		System.out.println("Borrowing Requests:");
		if(requests.isEmpty())
			System.out.println("No requests");
		else
			for(Request request : requests ) {
				System.out.println(request.user.name+ " wants to borrow" + "("+request.book.name+ ", "+ request.book.ISBN+ ")" );
				System.out.println("1- Accept");
				System.out.println("2- Reject");
				
				var choice = reader.nextInt();
				if(choice == 1) {
					request.book.isAvailable = false;
					request.user.borrowingHistory.add(request.book);
					System.out.println("The book has been registered as borrowed.");
					
				}else 
					System.out.println("The request is rejected");
					
			}	
	}// end of BorrowingRequestQueue


	public static void librarianMenu(User user) {
		var reader = new Scanner(System.in);
		char repeat;
		do {
			System.out.println("Welcome librarian "+ user.name);
			System.out.println("1- Approve Borrowing Request Queue");
			System.out.println("2- View All Books");
			System.out.println("3- Check the borrowing history for a book");
			System.out.println("4- Check the borrowing history for a user");
			System.out.println("5- Manage Library users");
			System.out.println("6- Explore books");
			System.out.println("7- Add Book");

			var choice = reader.nextInt(); 
			switch (choice) {
			case 1 : 
				BorrowingRequestQueue();
				break;
			case 2:
				showBooks();
				break;
			case 3:
				checkBorrowingHistoryForABook();
				break;
			case 4:
				checkBorrowingHistoryForAUser();
				break;
			case 5:
				manageUsers();
				break;
			case 6:
				showBooks();
				break;
			case 7:
				addBook();
				break;
			default :
				System.out.println(" no such as choice.. repeat!");
				break;
			}// end of switch

			System.out.println("\nare you willing to repeat?!(y or n) \n");
			repeat = reader.next().charAt(0);  

		}while(repeat == 'y');


	}



	public static void main(String[] args) {
		System.out.println("Welcome to Library System");

		var reader = new Scanner(System.in);

		var stay = false;
		do {
			//User ID
			System.out.println("Enter your ID");
			var id = reader.nextInt();
			//User Password
			System.out.println("Enter your password");
			var password = reader.nextInt();

			for(User user : users) 
				if(id == user.ID) {
					if(password == user.password) {

						if(user.Uertype.equalsIgnoreCase("student")) {
							userMenu(user);
							System.out.println("\nExit Software write (y or n) \n");
							var answer =  reader.next().charAt(0);   
							if(answer == 'y')
								System.exit(0);
							else
								stay = true;

						}

						else {
							librarianMenu(user);
							System.out.println("\nExit Software write (y or n) \n");
							var answer =  reader.next().charAt(0);   
							if(answer == 'y')
								System.exit(0);
							else
								stay = true;
						}
						break;
					}else {
						stay = true;
						System.out.println("Your Password Is Wrong");

						break;
					}
				}
		}while(stay == true);

	}// end of main method
}// end of Main class