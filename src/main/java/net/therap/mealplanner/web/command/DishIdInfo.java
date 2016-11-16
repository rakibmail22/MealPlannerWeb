package net.therap.mealplanner.web.command;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author bashir
 * @since 11/13/16
 */
public class DishIdInfo {

    @Size(min = 1)
    List<Integer> dishIdList;

    public DishIdInfo() {
        dishIdList = new ArrayList<Integer>();
    }

    public DishIdInfo(List<Integer> idList) {
        dishIdList = idList;
    }

    public List<Integer> getDishIdList() {
        return dishIdList;
    }

    public void setDishIdList(List<Integer> dishIdList) {
        this.dishIdList = dishIdList;
    }

    public String toString() {
        return Arrays.deepToString(dishIdList.toArray());
    }
}
