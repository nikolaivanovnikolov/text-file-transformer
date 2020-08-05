/*
 * File    : CalcClass.java
 * Project : myclass
 * Package : myclass
 * Created : Sep 19, 2019
 * Author  : Nikola Nikolov
 */
package myclass;

/**
 * Class for calculation of MyClass classes.
 * 
 * @author <a href="mailto:n.nikolov@prolet.org">Nikola Nikolov</a>
 */
public class CalcClass
{
    /**
     * Method getSum(MyClass[] myClasses) returns the sum of values of MyClass[] myClasses array.
     * 
     * @param myClasses
     * @return
     */
    public int getSum(MyClass[] myClasses)
    {
        if (myClasses == null || myClasses.length == 0)
        {
            return 0;
        }

        int sum = 0;

        for (int i = 0; i < myClasses.length; i++)
        {
            if (myClasses[i] != null)
            {
                sum = sum + myClasses[i].getValue();
            }
        }

        return sum;
    }
}
