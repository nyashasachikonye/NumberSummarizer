package numberrangesummarizer;

import java.util.Collection;

/**
 * @author Werner
 *
 * Implement this Interface to produce a comma delimited list of numbers,
 * grouping the numbers into a range when they are sequential.
 *
 *
 * Sample Input: "1,3,6,7,8,12,13,14,15,21,22,23,24,31
 * Result: "1, 3, 6-8, 12-15, 21-24, 31"
 *
 * The code will be evaluated on
 *   - functionality
 *   - style
 *   - robustness
 *   - best practices
 *   - unit tests
 */
public interface NumberRangeSummarizer {

    //collect the input
    // Collection<Integer> collect(String input);

    //get the summarized string
    // String summarizeCollection(Collection<Integer> input);

    public void method1();

}

class Demo implements NumberRangeSummarizer
{
   /* This class must have to implement both the abstract methods
    * else you will get compilation error
    */
   //collect the input
//    Collection<Integer> collect(String input);

   //get the summarized string
//    String summarizeCollection(Collection<Integer> input);

public void method1()
   {
	System.out.println("implementation of method1");
   }

   public static void main(String arg[])
   {
	NumberRangeSummarizer obj = new Demo();
	obj.method1();
   }
}


