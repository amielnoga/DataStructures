import java.util.*;
/**
 * class Library
 *
 * @author Noga Matzliach
 * @version 03.03.23
 */
public class Library
{
    // instance variables 
    private Hashtable<String, Subscriber> subscribers;
    private Hashtable<String, Book> borrowedBooks;
    LinkedList<Subscriber> list0=new LinkedList<Subscriber>();
    LinkedList<Subscriber> list1=new LinkedList<Subscriber>();
    LinkedList<Subscriber> list2=new LinkedList<Subscriber>();
    LinkedList<Subscriber> list3=new LinkedList<Subscriber>();
    LinkedList<Subscriber> list4=new LinkedList<Subscriber>();
    LinkedList<Subscriber> list5=new LinkedList<Subscriber>();
    LinkedList<Subscriber> list6=new LinkedList<Subscriber>();
    LinkedList<Subscriber> list7=new LinkedList<Subscriber>();
    LinkedList<Subscriber> list8=new LinkedList<Subscriber>();
    LinkedList<Subscriber> list9=new LinkedList<Subscriber>();
    LinkedList<Subscriber> list10=new LinkedList<Subscriber>();
    LinkedList[] subscribersPerNumbersOfBorrowedBooks={list0,list1,list2,list3,list4,list5,list6,list7,list8,list9,list10};

    /**
     * Constructor for objects of class Library
     */
    public Library()
    {
        subscribers = new Hashtable<String, Subscriber>(); 
        borrowedBooks = new Hashtable<String, Book>(); 
    }

    /**
     * borrowBook method - updating user is borrowing the book
     * find the subscriber --> adding the book to his books list --> adding the book the borrowed books by the subscriber
     * @param  lastName  user last name
     * @param  id  user id
     * @param  bookCode  the code of the book the user borrow
     */
    public void borrowBook(String lastName,String id,String bookCode)
    {
        Subscriber subscriber = subscribers.get(id); //find the subscriber who wants to borrow the book
        //validation
        if (subscriber.getNumberOfBorrowdBooks() >= 10) 
        {
            System.out.println("Sorry subscriber! You reached the maximum number of borrowed books");
        }
        else 
        {
            borrowedBooks.put(bookCode,new Book(bookCode,subscriber));
            Book book=borrowedBooks.get(bookCode);
            subscriber.addBookToSubscriberBookList(book);
            subscribersPerNumbersOfBorrowedBooks[subscriber.getNumberOfBorrowdBooks()-1].remove(subscriber);
            subscribersPerNumbersOfBorrowedBooks[subscriber.getNumberOfBorrowdBooks()].add(subscriber);
            System.out.println("Book borrowed: "+book);
        }
    } 

    /**
     * returnBorrowBook method -  updating the user returned borrowed book
     * find the subscriber --> deleting the book from his list --> deleting the book from hash table of the borrowed books
     * @param  lastName  user last name
     * @param  id  user id
     * @param  bookCode  the code of the book the user borrow
     */
    public void returnBorrowBook(String lastName,String id,String bookCode)
    {
        Subscriber subscriber = subscribers.get(id); //find the subscriber who wants to return the book
        Book book = borrowedBooks.get(bookCode);
        subscriber.removeBookFronSubscriberBookList(book);//deleting the book from his list
        subscribersPerNumbersOfBorrowedBooks[subscriber.getNumberOfBorrowdBooks()+1].remove(subscriber);
        subscribersPerNumbersOfBorrowedBooks[subscriber.getNumberOfBorrowdBooks()].add(subscriber);
        borrowedBooks.remove(bookCode); //deleting the book from hash table of the borrowed books
        System.out.println("Book returned: "+book);

    }

    /**
     * addSubscriber method - updating a new subscriber
     * adding subscriber to the hash table of the subscribers with no books
     * Average O(1)
     * @param  lastName  user last name
     * @param  id  user id
     */
    public void addSubscriber(String lastName,String id)
    {        
        Subscriber subscriber = new Subscriber(lastName, id);
        subscribers.put(id, subscriber);
        System.out.println("New subscriber added: "+subscriber);
        subscribersPerNumbersOfBorrowedBooks[0].add(subscriber);
    }

    /**
     * deleteSubscriber method - deleting a subscriber
     * deleting a subscriber from the hash table of the subscribers
     * Average O(1)
     * @param  lastName  user last name
     * @param  id  user id
     */
    public void deleteSubscriber(String lastName,String id)
    {
        Subscriber subscriber = subscribers.get(id);
        subscribersPerNumbersOfBorrowedBooks[subscriber.getNumberOfBorrowdBooks()].remove(subscriber);
        subscriber.emptyBorrowdBooks();
        subscribers.remove(id);
        System.out.println("Subscriber deleted: " + subscriber);

    } 

    /**
     * getBooksBorrowed method - prints which books are now borrowed by the user
     * (search user id (unique - key) in hash table all the current books he is borrowing) 
     * Average O(1)
     * @param  userId  id of the user
     */
    public void getBooksBorrowed(String userId)
    {
        Subscriber subscriber = subscribers.get(userId);
        System.out.println("User current borrowed books: "+subscriber.getUserBorrowedbooks());
    }

    /**
     * getSubscriebrName method - prints the subscriber is now borrowing the book
     * (search book in hash table and return who is borrowing the book)
     * Average O(1)
     * @param  bookCode  Each code consists of two letters and four digits
     */
    public void getSubscriebrName(String bookCode)
    {
        Book book= borrowedBooks.get(bookCode);
         System.out.println("The subscriber is now borrowing the book: "+book.getBookCurrentSubscriber());

    }

    /**
     * getSubscribersMaxBooks method - the method prints all the subscribers who currently own the largest number of books
     * maximun numbers of books user can borrow is 10 (0-10)
     * linked list of each number of borrow book
     */
    public void getSubscribersMaxBooks()
    {
        for(int i=10;i>=0;i--)
        {
            if(subscribersPerNumbersOfBorrowedBooks[i].size()!=0)
            {
                for(int j=0;j<subscribersPerNumbersOfBorrowedBooks[i].size();j++)
                {
                    System.out.println(subscribersPerNumbersOfBorrowedBooks[i].get(j));
                }
                break;
            }
        }
    }
}
