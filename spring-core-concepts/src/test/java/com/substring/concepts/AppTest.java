package com.substring.concepts;

import com.substring.concepts.config.Student;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{

    private Student student=new Student("Sahil");


    public void testStudent(){

        if(student.getName().length()==2){
            System.out.println("Student name is valid");
        }else{
            System.out.println("Student name is invalid");
        }
    }

    /**
     *
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
    public void testApp()
    {
        assertTrue( true );
    }



}
