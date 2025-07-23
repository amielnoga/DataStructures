/**
 * class Main
 *
 * @author Noga Matzliach
 * @version 03.03.23
 */
import java.util.Scanner;

public class Main
{
    public static void main(String []args)
    {
        Library libary=new Library();
        System.out.println("Welcome to our library!");
        Scanner scanner= new Scanner(System.in);
        String userInputTillSpace;
        do
        {
            userInputTillSpace=scanner.next();
            String lastName,id,code;
            if(userInputTillSpace.charAt(0)=='+')
            {
                lastName=scanner.next();
                id=scanner.next();
                libary.addSubscriber(lastName,id);
            }

            else if(userInputTillSpace.charAt(0)=='-')
            {

                lastName=scanner.next();
                id=scanner.next();
                libary.deleteSubscriber(lastName,id);
            }

            else if(Character.isLetter(userInputTillSpace.charAt(0)))
            {
                lastName=userInputTillSpace;
                id=scanner.next();
                code=scanner.next();
                if(scanner.next().charAt(0)=='+')
                    libary.borrowBook(lastName,id,code);

                else
                    libary.returnBorrowBook(lastName,id,code);
            }

            else if(userInputTillSpace.charAt(0)=='?')
            {
                String userSelectionInsideTheMenu=scanner.next();

                if(Character.isLetter(userSelectionInsideTheMenu.charAt(0)))
                    libary.getSubscriebrName(userSelectionInsideTheMenu);

                else if(userSelectionInsideTheMenu.charAt(0)=='!')
                    libary.getSubscribersMaxBooks();

                else
                    libary.getBooksBorrowed(userSelectionInsideTheMenu);
            }
        }
        while(true);
    }
}

