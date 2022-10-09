package com.fareye.training.utility;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

public class EncryptionUtil {

    static int strength = 10; // work factor of bcrypt
    static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());

    public static String encodePassword(String plainPassword) {
        return bCryptPasswordEncoder.encode(plainPassword);
    }



}
