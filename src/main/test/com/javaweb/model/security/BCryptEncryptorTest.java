package com.javaweb.model.security;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Andrii Chernysh on 12-Feb-17. E-Mail : itcherry97@gmail.com
 */
public class BCryptEncryptorTest {

    @Test
    public void testEncryptAndCheckWithDifferentClasses(){
        BCryptEncryptor encryptor1 = new BCryptEncryptor();
        BCryptEncryptor encryptor2 = new BCryptEncryptor();

        String hash = encryptor1.encryptPassword("1234");
        Assert.assertTrue(encryptor2.checkPassword("1234",hash));
    }
}
