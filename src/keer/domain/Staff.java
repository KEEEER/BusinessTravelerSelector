package keer.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Staff {
    private String staffCode;
    private String staffName;
    private Map<Staff, Double> staffTravelWithRatio;

    public Staff(String staffCode, String staffName){
        this.staffCode = staffCode;
        this.staffName = staffName;
        this.staffTravelWithRatio = new HashMap<>();
    }
    public Staff(String staffName){
        this.staffName = staffName;
        this.staffTravelWithRatio = new HashMap<>();
    }

    public void setTravelWithRatio(Staff travelWith, Double ratio){
        this.staffTravelWithRatio.put(travelWith, ratio);
    }

    public String getStaffName(){
        return this.staffName;
    }
}
