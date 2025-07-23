import java.util.*;

/**
 * class DHeap-  Q1 subsection B
 *
 * @author Noga Matzliach
 * @version 16.12.22
 */
class DHeap
{
    // instance variables
    private int d; // every parent has maximum d sons
    private int size; // size of the dHeap and not the size of the array
    private int[] array=new int[1000]; //heap represents by an array

    /**
     * Constructor for objects of class DHeap
     */
    public DHeap(int d, int[] l)
    {
        this.d=d;
        this.size=l[0];
        buildDHeap(l);
    }

    /**
     * buildDHeap - the function gets an array and reorders it in a fashion that represents a dHeap.
     *
     * @param  l  an array that consist the dHeap values but not in the right order
     */
    public void buildDHeap(int[] l)
    {
        for(int i=0;i<l.length;i++)
            this.array[i]=l[i];
        for(int i=this.size/2;i>=1;i--)
        {
            dHeapify(i);
        }
    }

    /**
     * insert method - gets a value the user wants to add to the heap and inserts the value to the array in the right place
     *
     * @param  key  the new value to be added to the heap
     */
    public void insert(int key)
    {
        this.size= this.size+1;
        this.array[0]=this.size;
        this.array[this.size]=Integer.MIN_VALUE;
        heapIncreaseKey(this.size,key);
    }

    /**
     * heapIncreaseKey -  - the function pushes up the inputted node to it's right place in the heap.
     *
     * @param  key  the new value to be added to the heap
     */
    public void heapIncreaseKey(int i, int key)
    {
        if (key<this.array[i])
            System.out.println("new key is smaller than current key");
        else
        {
            this.array[i] = key;

            while (i > 1 && (this.array[getParent(i)] < this.array[i])) {
                swap(i, getParent(i));
                i = getParent(i);
            }
        }
    }

    /**
     *  PrintHeap - The function prints the array represents the heap
     */
    public void PrintHeap()
    {
        System.out.println("The array implements the heap is: ");
        for(int i=1;i<=size;i++)
            if(i!=size)
                System.out.print(this.array[i]+",");
            else
                System.out.println(this.array[i]);
    }

    /**
     * dHeapify - the function pushes down the inputted node to it's right place in the heap.
     *
     * @param  index  the index the heapify act on
     */
    public void dHeapify(int index)
    {
        int largest=index;
        int child=getLeftSon(index);
        for(int a=1;a<=this.d;a++)
        {
            if (child <= this.size && this.array[child] > this.array[largest]) {
                largest = child;
            }
            child++;
        }

        if(largest!=index)
        {
            swap(index,largest);
            dHeapify(largest);
        }
    }

    private void swap(int a,int b)
    {
        int temp= this.array[a];
        this.array[a]=this.array[b];
        this.array[b]=temp;
    }

    /**
     * method getLeftSon
     *
     * @param  i  index of a node
     * @return    the index of the node left son
     */
    public int getLeftSon(int i)
    {
        return d*i+2-d;
    }

    /**
     * method getRightSon
     *
     * @param  i  index of a node
     * @return    the index of the node right son
     */
    public int getRightSon(int i)
    {
        return getLeftSon(i)+d-1;
    }

    /**
     * method getParent
     *
     * @param  i  index of a node
     * @return    the index of the Parent node
     */
    public int getParent(int i)
    {
        return (i+d-2)/d;
    }

    /**
     * ExtractMax - the function delete maximum value and reordering d accordingly
     *
     * @return    the maximum value in the heap
     */
    public int ExtractMax()
    {
        int max=this.array[1];
        this.array[1]=this.array[this.size];
        this.size--;
        dHeapify(1);
        return max;

    }

    /**
     * ChangeD - changing the heap according to the new D value
     *
     * @param  newD  the new d value
     */
    public void ChangeD(int newD)
    {
        this.d=newD;
        buildDHeap(this.array);
    }

    public static void main(String [] args)
    {
        DHeap dheap=null;
        int userSelectionFromTheMenu;
        Scanner scan=new Scanner(System.in);

        do
        {
            System.out.println("Enter the number that represents the action you want to commit: ");
            System.out.println("1 for BuildHeap(S,d,L)");
            System.out.println("2 for INSERT(S,x)");
            System.out.println("3 for ExtractMax(S)");
            System.out.println("4 for PrintHeap(S)");
            System.out.println("5 for ChangeD(S,d)");
            System.out.println("6 to exit the program");
            userSelectionFromTheMenu=scan.nextInt();

            switch(userSelectionFromTheMenu)
            {
                case 1:
                {
                    int d;
                    System.out.println("Insert d value");
                    d=scan.nextInt();

                    System.out.println("Insert L values separated by ','");
                    String [] listFromTheUser=scan.next().split(",");
                    int []revisedList =new int[listFromTheUser.length+1];
                    revisedList[0]=listFromTheUser.length;

                    for(int i=0;i<listFromTheUser.length;i++)
                        revisedList[i+1]=Integer.parseInt(listFromTheUser[i]);

                    dheap= new DHeap(d,revisedList);
                    dheap.PrintHeap();
                    break;
                }
                case 2:
                {
                    System.out.println("Insert new value");
                    int x=scan.nextInt();
                    dheap.insert(x);
                    dheap.PrintHeap();
                    break;
                }
                case 3:
                {
                    System.out.println(dheap.ExtractMax());
                    dheap.PrintHeap();
                    break;
                }
                case 4:
                {
                    dheap.PrintHeap();
                    break;
                }
                case 5:
                {
                    int d;
                    System.out.println("Insert new d value");
                    d=scan.nextInt();
                    dheap.ChangeD(d);
                    dheap.PrintHeap();
                    break;
                }
                case 6:
                {
                    break;
                }
                default:
                {
                    System.out.println("You picked wrong action, pleas try again");
                    break;
                }

            }
        }

        while(userSelectionFromTheMenu!=6);
    }
}
