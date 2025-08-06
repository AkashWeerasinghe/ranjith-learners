/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.ranjithlearners.validation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Akash Weerasinghe
 */
public class ValidatorTest {
    
    public ValidatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isEmailValid method, of class Validator.
     */
    @Test
    public void testIsEmailValid() {
        System.out.println("isEmailValid");
        String value = "akash@gmail.com";
        boolean expResult = true;
        boolean result = Validator.isEmailValid(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMobileValid method, of class Validator.
     */
    @Test
    public void testIsMobileValid() {
        System.out.println("isMobileValid");
        String value = "0715533880";
        boolean expResult = true;
        boolean result = Validator.isMobileValid(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPasswordValid method, of class Validator.
     */
    @Test
    public void testIsPasswordValid() {
        System.out.println("isPasswordValid");
        String value = "Akash1234!";
        boolean expResult = true;
        boolean result = Validator.isPasswordValid(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isNameValid method, of class Validator.
     */
    @Test
    public void testIsNameValid() {
        System.out.println("isNameValid");
        String value = "asd";
        boolean expResult = true;
        boolean result = Validator.isNameValid(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isNicValid method, of class Validator.
     */
    @Test
    public void testIsNicValid() {
        System.out.println("isNicValid");
        String value = "200323";
        boolean expResult = true;
        boolean result = Validator.isNicValid(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDOBValid method, of class Validator.
     */
    @Test
    public void testIsDOBValid() {
        System.out.println("isDOBValid");
        String value = "asdasda";
        boolean expResult = true;
        boolean result = Validator.isDOBValid(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAddressvalid method, of class Validator.
     */
    @Test
    public void testIsAddressvalid() {
        System.out.println("isAddressvalid");
        String value = "asdasd";
        boolean expResult = true;
        boolean result = Validator.isAddressvalid(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
