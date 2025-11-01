package com.example;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the BankAccount class.
 */
class AppTest {

    private BankAccount account;

    // This method runs before each test, ensuring a fresh account object for every test case.
    @BeforeEach
    void setUp() {
        // Start with a balance of $500 for most tests
        account = new BankAccount(500.00);
    }

    @Test
    @DisplayName("Constructor should set initial balance correctly")
    void testInitialBalance() {
        // Test if the constructor correctly sets the starting balance.
        assertEquals(500.00, account.getBalance(), "Initial balance should be 500.00");
    }
    
    @Test
    @DisplayName("Constructor should default negative initial balance to 0")
    void testNegativeInitialBalance() {
        // A bank account cannot start with a negative balance.
        BankAccount negativeAccount = new BankAccount(-100);
        assertEquals(0, negativeAccount.getBalance(), "Negative initial balance should default to 0");
    }

    @Test
    @DisplayName("Should deposit a positive amount successfully")
    void testSuccessfulDeposit() {
        // Test a standard, valid deposit.
        boolean success = account.deposit(100.00);
        assertTrue(success, "Deposit should return true for a positive amount");
        assertEquals(600.00, account.getBalance(), "Balance should be 600.00 after depositing 100.00");
    }

    @Test
    @DisplayName("Should fail to deposit a negative amount")
    void testDepositNegativeAmount() {
        // You can't deposit a negative value.
        boolean success = account.deposit(-50.00);
        assertFalse(success, "Deposit should return false for a negative amount");
        assertEquals(500.00, account.getBalance(), "Balance should not change after a failed deposit");
    }

    @Test
    @DisplayName("Should withdraw a valid amount successfully")
    void testSuccessfulWithdrawal() {
        // Test a standard, valid withdrawal.
        boolean success = account.withdraw(200.00);
        assertTrue(success, "Withdrawal should return true for a valid amount");
        assertEquals(300.00, account.getBalance(), "Balance should be 300.00 after withdrawing 200.00");
    }

    @Test
    @DisplayName("Should fail to withdraw an amount greater than the balance")
    void testWithdrawalExceedingBalance() {
        // Test withdrawing more money than available (insufficient funds).
        boolean success = account.withdraw(600.00);
        assertFalse(success, "Withdrawal should return false if amount exceeds balance");
        assertEquals(500.00, account.getBalance(), "Balance should not change on insufficient funds");
    }

    @Test
    @DisplayName("Should fail to withdraw a negative amount")
    void testWithdrawalNegativeAmount() {
        // You can't withdraw a negative value.
        boolean success = account.withdraw(-100.00);
        assertFalse(success, "Withdrawal should return false for a negative amount");
        assertEquals(500.00, account.getBalance(), "Balance should not change for negative withdrawal amount");
    }
    
    @Test
    @DisplayName("Should successfully withdraw the entire balance")
    void testWithdrawalAllFunds() {
        // Test withdrawing the exact balance.
        boolean success = account.withdraw(500.00);
        assertTrue(success, "Should be able to withdraw the exact balance");
        assertEquals(0.00, account.getBalance(), "Balance should be 0 after withdrawing all funds");
    }
}
