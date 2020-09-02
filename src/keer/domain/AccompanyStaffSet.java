package keer.domain;

import java.util.ArrayList;

import java.util.List;

public class AccompanyStaffSet {
    private List<String> accompanyStaffs;
    private boolean isStaffsSettleUp = false;

    public AccompanyStaffSet(){
        accompanyStaffs = new ArrayList<>(3);
    }

    public AccompanyStaffSet(List<String> accompanyStaffs){
        this.accompanyStaffs = accompanyStaffs;
        this.isStaffsSettleUp = true;
    }

    public void addStaff(String staff){
        this.accompanyStaffs.add(staff);
        this.isStaffsSettleUp = true;
    }

    public List<String> getAccompanyStaffs(){
        return this.accompanyStaffs;
    }

    public boolean isStaffsSettleUp(){
        return this.isStaffsSettleUp;
    }
}
