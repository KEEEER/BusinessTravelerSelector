package keer.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Staff {
    private String staffCode;
    private String staffName;
    private int businessTravelTimes;

    public Staff(String staffCode, String staffName){
        this.staffCode = staffCode;
        this.staffName = staffName;
        this.businessTravelTimes = 0;
    }

    public Staff(String staffName){
        this.staffName = staffName;
    }

    public String getStaffName(){
        return this.staffName;
    }

    public void setBusinessTravelTimes(int times){
        this.businessTravelTimes = times;
    }

    public int getBusinessTravelTimes(){
        return this.businessTravelTimes;
    }
}
