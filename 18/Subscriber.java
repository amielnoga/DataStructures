import java.util.*;
/**
 * class Subscriber - represents person who has library subscription
 *
 * @author Noga Matzliach
 * @version 03.03.23
 */
public class Subscriber
{
    // instance variables 
    private String lastName=null;
    private String id=null; //uniqe
    private int numOfBorrowdBooks;
    private LinkedList <Book>borrowedbooks;
    private final int maxBooksPerSubscriber=10;

    /**
     * Constructor for objects of class Subscriber
     */
    public Subscriber(String lastName,String id)
    {
        // initialise instance variables
        this.lastName=lastName;
        this.id=id;
        numOfBorrowdBooks=0;
        borrowedbooks=new LinkedList<Book>();  
    }

    public int getNumberOfBorrowdBooks()
    {
        return this.numOfBorrowdBooks;
    }
    
      public void emptyBorrowdBooks()
    {
        this.numOfBorrowdBooks=0;
        borrowedbooks=null;
    }

    public void addBookToSubscriberBookList(Book book)
    {
        borrowedbooks.add(book);
        this.numOfBorrowdBooks++;
    }

    public void removeBookFronSubscriberBookList(Book book)
    {
        borrowedbooks.remove(book);
        this.numOfBorrowdBooks--;
    }

    public LinkedList getUserBorrowedbooks()
    {
        return this.borrowedbooks;
    }
    
    public String toString()
    {
       return "last name: "+this.lastName+", id: "+ this.id+", number of borrowed books: "+ this.numOfBorrowdBooks;

    }
}
