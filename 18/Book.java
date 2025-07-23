
/**
 * class Book - represent a book in the library.
 *
 * @author Noga Matzliach
 * @version 03.03.23
 */
public class Book
{
    // instance variables
    private String code; //unique
    private Subscriber bookCurrentSubscriber;

    /**
     * Constructor for objects of class Book
     */
    public Book(String code, Subscriber bookCurrentSubscriber)
    {
        // initialise instance variables
        this.code = code;
        this.bookCurrentSubscriber=bookCurrentSubscriber;
    }

    public Subscriber getBookCurrentSubscriber()
    {
        return this.bookCurrentSubscriber;
    }

    public String toString()
    {
        return "code: "+this.code+" subscriber: "+this.bookCurrentSubscriber+" ";

    }

}
