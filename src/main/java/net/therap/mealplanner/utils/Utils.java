package net.therap.mealplanner.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author bashir
 * @since 10/17/16
 */
@Component
public class Utils {
    public String hashMd5(String pass) {
        try {
            byte[] passBytes = pass.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            byte[] digested = md.digest(passBytes);

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digested) {
                stringBuilder.append(Integer.toHexString(0xff & b));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
