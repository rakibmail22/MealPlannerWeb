package net.therap.mealplanner.domain;

/**
 * @author bashir
 * @since 11/17/16
 */
public enum  MealType {

    B("B"), L("L"), D("D");

    private String mealType;

    MealType(String mealType) {
        this.mealType = mealType;
    }

    public String getMealType(){
        return mealType;
    }
}
