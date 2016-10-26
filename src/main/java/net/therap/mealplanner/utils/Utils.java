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
    public static void printWelcomeMessage() {
        System.out.println("Please select from below:\n[1] View Weekly MealPlan\n[2] Update MealPlan\n[3] Delete MealPlan\n[4] Quit");
    }

    public static void printUpdateWindowMessage() {
        System.out.println("*******UPDATE WINDOW*******");
    }

    public static String daySelector() {
        try {
            System.out.println("SELECT FROM THE DAY BELOW");
            System.out.println("[1] FRIDAY");
            System.out.println("[2] SATURDAY");
            System.out.println("[3] SUNDAY");
            System.out.println("[4] MONDAY");
            System.out.println("[5] TUESDAY");
            System.out.println("[6] WEDNESDAY");
            System.out.println("[7] THURSDAY");
            System.out.println("[0] PREVIOUS MENU");
            Scanner scanner = new Scanner(System.in);
            int index = Integer.parseInt(scanner.nextLine());
            switch (index) {
                case 1:
                    return "FRI";
                case 2:
                    return "SAT";
                case 3:
                    return "SUN";
                case 4:
                    return "MON";
                case 5:
                    return "TUE";
                case 6:
                    return "WED";
                case 7:
                    return "THU";
                default:
                    System.out.println("Invalid Input. Try again.");
                    break;
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public static String mealSelector() {
        try {
            System.out.println("SELECT FROM THE MEAL TYPE BELOW:");
            System.out.println("[1] SELECT BREAKFAST");
            System.out.println("[2] SELECT LUNCH");
            System.out.println("[0] Main Menu");
            Scanner scanner = new Scanner(System.in);
            int index = Integer.parseInt(scanner.nextLine());
            switch (index) {
                case 1:
                    return "B";
                case 2:
                    return "L";
                case 0:
                    return "";
                default:
                    System.out.println("Invalid Input. Try again.");
                    return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    public static int breakfastUpdaterWindow() {
        try {
            System.out.println("[1] UPDATE BREKFAST FROM EXISTING MEAL PLAN");
            System.out.println("[2] CREATE NEW MEAL PLAN FOR BREAKFAST");
            Scanner scanner = new Scanner(System.in);
            int index = Integer.parseInt(scanner.nextLine());
            return index;
        } catch (Exception e) {
            return -1;
        }
    }

    public static int lunchUpdaterWindow() {
        try {
            System.out.println("[1] UPDATE LUNCH FROM EXISTING MEAL PLAN");
            System.out.println("[2] CREATE NEW MEAL PLAN FOR LUNCH");
            Scanner scanner = new Scanner(System.in);
            int index = Integer.parseInt(scanner.nextLine());
            return index;
        } catch (Exception e) {
            return -1;
        }
    }

    public static int mealUpdateWindow(String type) {
        if (type.equals("B")) {
            return breakfastUpdaterWindow();
        } else if (type.equals("L")) {
            return lunchUpdaterWindow();
        }
        return -1;
    }

    public static void noMealFoundMessage(String mealType, String day) {
        if (mealType.equals("B")) {
            System.out.println("No Breakfast Plan Available For This Day. Please Create A New Breakfast Plan For " + day);
        } else if (mealType.equals("L")) {
            System.out.println("No Lunch Plan Available For This Day. Please Create A New Breakfast Plan For " + day);
        } else {
            System.out.println("Meal Not available for this hour");
        }
    }

    public static void showMealOptions(List<Meal> mealList) {
        for (int i = 0; i < mealList.size(); ++i) {
            System.out.println("[" + i + "] " + mealList.get(i));
        }
    }

    public static int dishUpdaterWindow() {
        try {
            System.out.println("[1] SELECT FROM EXISTING DISHES");
            System.out.println("[2] ADD NEW DISH");
            Scanner scanner = new Scanner(System.in);
            int index = Integer.parseInt(scanner.nextLine());
            return index;
        } catch (Exception e) {
            return -1;
        }
    }

    public static void dishUpdateOperation(MealPlanService mealPlanService, List<Dish> dishList, String day, String mealType, User user, Meal existingMeal) {

        try {
            Scanner k = new Scanner(System.in);
            int dishUpdateIndicator = Utils.dishUpdaterWindow();
            if (dishUpdateIndicator == 1) {
                System.out.println("Insert from the below dish index separated by ','");
                System.out.println("[" + 0 + "] To go to main menu.");
                for (int i = 1; i < dishList.size(); ++i) {
                    System.out.println("[" + i + 1 + "] " + dishList.get(i - 1));
                }
                String[] dishIndices = k.nextLine().split(",");
                List<Dish> newDishList = new ArrayList<Dish>();
                for (String dishIndex : dishIndices) {
                    newDishList.add(dishList.get(Integer.parseInt(dishIndex)));
                }
                Meal meal = new Meal();
                meal.getMealDishes().addAll(newDishList);
                meal.setDay(day);
                meal.setType(mealType);
                int flag = mealPlanService.updateMealPlanForUser(meal, existingMeal, user);
                if (flag > 0) {
                    System.out.println("Meal Plan Successfully updated. Now You can add this meal from Meal Menu.");
                }
            } else if (dishUpdateIndicator == 2) {
                System.out.println("Enter dish name or write 'n' to go back ");
                String dishName = k.nextLine();
                if (dishName.equals("n")) {
                    System.out.println("No dish added. Please try again.");
                    return;
                }
                Dish dish = new Dish();
                dish.setName(dishName);
                mealPlanService.insertNewDish(dish);
                System.out.println("Dish Successfully Added. Now you can use this dish to make your meal from meal menu.");
            }
        } catch (Exception e) {
            System.out.println("Please Try Again With valid input");
            e.printStackTrace();
            return;
        }
    }

    public static void deleteMealPlanOperation(MealPlanService mealPlanService, User user) {
        System.out.println("Delete MealPlan Window");
        String day = daySelector();
        String type = mealSelector();
        Meal meal = null;
        if (type.equalsIgnoreCase("B")) {
            meal = mealPlanService.getBreakfastForUserForDay(user, day);
        } else if (type.equalsIgnoreCase("L")) {
            meal = mealPlanService.getLunchForUserForDay(user, day);
        }
        int flag = 0;
        if (meal != null) {
            flag = mealPlanService.deleteMealForUser(meal, user);
        }
        if (flag > 0) {
            System.out.println("Meal Successfully deleted for the selected day and type.");
        }
    }

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
