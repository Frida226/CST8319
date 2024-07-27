package com.flowerorder;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

public class BCryptTest {

    @Test
    public void testHashPassword() {
        // Arrange
        String password = "mySecretPassword";
        
        // Act
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Assert
        assertNotNull(hashedPassword);
        assertTrue(BCrypt.checkpw(password, hashedPassword));
    }

    @Test
    public void testPasswordVerification() {
        // Arrange
        String password = "mySecretPassword";
        String wrongPassword = "wrongPassword";
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Act & Assert
        assertTrue(BCrypt.checkpw(password, hashedPassword)); // Correct password
        assertFalse(BCrypt.checkpw(wrongPassword, hashedPassword)); // Incorrect password
    }

    @Test
    public void testHashPasswordConsistency() {
        // Arrange
        String password = "mySecretPassword";
        
        // Act
        String hashedPassword1 = BCrypt.hashpw(password, BCrypt.gensalt());
        String hashedPassword2 = BCrypt.hashpw(password, BCrypt.gensalt());

        // Assert
        assertNotEquals(hashedPassword1, hashedPassword2); // Different salts produce different hashes
        assertTrue(BCrypt.checkpw(password, hashedPassword1)); // Verification should succeed
        assertTrue(BCrypt.checkpw(password, hashedPassword2)); // Verification should succeed
    }
}

