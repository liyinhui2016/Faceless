package org.apache.hadoop.hive.ql.udf;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.hadoop.io.Text;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        UDFAge age = new UDFAge();
        Text val = new Text();
        val = age.evaluate(12);
        val = age.evaluate(20);
        val = age.evaluate(24);
        val = age.evaluate(34);
        val = age.evaluate(59);
        val = age.evaluate(60);
        val = age.evaluate(66);
        val = age.evaluate(77);
    }
}
