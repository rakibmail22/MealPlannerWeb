package net.therap.mealplanner.utils;

import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.User;
import net.therap.mealplanner.service.MealPlanService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author bashir
 * @since 10/17/16
 */
public class Utils {
    public static String hashMd5(String pass) {
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
