package Tests;

import junit.framework.TestResult;

/**
 * Created by Mihnea on 11.11.2017.
 */
public class Run {
    public static void main(String[] args) {
        TestSuite suite = new TestSuite();
        TestResult result = new TestResult();
        suite.run(result);
    }
}
