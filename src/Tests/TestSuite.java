package Tests;
import junit.framework.TestResult;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
/**
 * Created by Mihnea on 10.11.2017.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({FarmacieRepositoryTest.class, FarmacieServiceTest.class,MedValidatorTest.class, ShoppingCartTest.class})
public class TestSuite extends junit.framework.TestSuite {

    public static TestSuite suite(){
        TestSuite suite = new TestSuite();
        suite.addTest(new FarmacieRepositoryTest());
        suite.addTest(new FarmacieServiceTest());
        suite.addTest(new MedValidatorTest());
        suite.addTest(new ShoppingCartTest());
        return suite;
    }

    public static void runAllTests(){
        TestResult result = new TestResult();
        junit.framework.TestSuite suite = suite();
        suite.run(result);
    }
}
