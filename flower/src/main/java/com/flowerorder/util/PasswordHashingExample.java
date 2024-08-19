package com.flowerorder.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHashingExample {
    public static void main(String[] args) {
        String plainPassword = "1234";  
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());  
        System.out.println("Hashed Password: " + hashedPassword);
    }
}
