package keer.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Staff {
    private String staffCode;
    private String staffName;
    private Map<Staff, Double> travelWithRatio;

    public Staff(String staffCode, String staffName){
        this.staffCode = staffCode;
        this.staffName = staffName;
        this.travelWithRatio = new HashMap<>();
    }

    public void setTravelWithRatio(Staff travelWith, Double ratio){
        this.travelWithRatio.put(travelWith, ratio);
    }
}
