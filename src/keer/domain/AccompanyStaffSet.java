package keer.domain;

import java.util.ArrayList;

import java.util.List;

public class AccompanyStaffSet {
    private List<String> accompanyStaffs;

    public  AccompanyStaffSet(){
        accompanyStaffs = new ArrayList<>(3);
    }

    public AccompanyStaffSet(List<String> accompanyStaffs){
        this.accompanyStaffs = accompanyStaffs;
    }

    public void addStaff(String staff){
        this.accompanyStaffs.add(staff);
    }

    public List<String> getAccompanyStaffs(){
        return this.accompanyStaffs;
    }
}
