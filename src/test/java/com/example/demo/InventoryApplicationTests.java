package com.example.demo;

import com.example.demo.security.JwtProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestResultListener.class)
public class InventoryApplicationTests {

    private JwtProvider jwtProvider;

    @BeforeClass
    public void setup() {
        jwtProvider = new JwtProvider();
    }

    @Test
    public void testJwtTokenGeneration() {
        String token = jwtProvider.generateToken("testUser");
        Assert.assertNotNull(token);
    }

    @Test
    public void testJwtTokenValidation() {
        String token = jwtProvider.generateToken("testUser");
        boolean valid = jwtProvider.validateToken(token);
        Assert.assertTrue(valid);
    }
}
