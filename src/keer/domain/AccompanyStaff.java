package keer.domain;

import java.util.ArrayList;
import java.util.List;

public class AccompanyStaff {
    private List<String> accompanyStaffs;
    private double ratio;

    public AccompanyStaff(){
        accompanyStaffs = new ArrayList<>();
    }

    public void addStaff(String staff){
        this.accompanyStaffs.add(staff);
    }

    public void setRatio(double ratio){
        this.ratio = ratio;
    }

    public List<String> getAccompanyStaffs(){
        return this.accompanyStaffs;
    }
}
