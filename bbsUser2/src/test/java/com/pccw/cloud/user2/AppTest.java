package com.pccw.cloud.user2;

import java.io.UnsupportedEncodingException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
    public void testApp()
    {
        assertTrue( true );
    }
    public static void main(String[] args) throws Exception {
		String s = "中文100%，测试";
		System.out.println(s);
		s = java.net.URLDecoder.decode(java.net.URLDecoder
				.decode(s.replaceAll("%(?![0-9a-fA-F]{2})", "%25") ,"UTF-8").replaceAll("%(?![0-9a-fA-F]{2})", "%25"), "UTF-8");
		System.out.println(s);
		
	}
}
