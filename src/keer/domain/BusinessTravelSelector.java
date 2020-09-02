package keer.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusinessTravelSelector {
    private Map<String, Integer> staffAlreadyDistributedDays;
    private List<AccompanyStaffSet> choreographyResult;

    public BusinessTravelSelector(){
        staffAlreadyDistributedDays = new HashMap<>();
    }

    public List<AccompanyStaffSet> choreography(List<Staff> staffs, List<AccompanyStaffSet> accompanyStaffSets, int totalTravelDays){
        this.choreographyResult = new ArrayList<>(totalTravelDays);
        staffAlreadyDistributedDays = new HashMap<>();
        fairlyDistributedDays(staffs, totalTravelDays);
        randomDistributeAccompanyStaffs(accompanyStaffSets);
        randomDistributeStaffs(staffs);
        return choreographyResult;
    }

    private void randomDistributeStaffs(List<Staff> staffs) {

    }

    private void randomDistributeAccompanyStaffs(List<AccompanyStaffSet> accompanyStaffSets, ) {
        for(AccompanyStaffSet accompanyStaffSet : accompanyStaffSets){
            int randomDay;
            int ratio = Integer.parseInt(accompanyStaffSet.getAccompanyStaffs().get(accompanyStaffSet.getAccompanyStaffs().size()-1));
            int accompanyDay =
            for(int i=0 ; i<accompanyDay ; i++){
                randomDay = getUndistributedDay();
                choreographyResult.set(randomDay, accompanyStaffSet);
            }
        }

    }

    private int getUndistributedDay() {
        int randomDay;
        do{
            randomDay = (int) (Math.random() * this.choreographyResult.size());
        }while(!choreographyResult.get(randomDay).isStaffsSettleUp());
        return randomDay;
    }

    private void fairlyDistributedDays(List<Staff> staffs, int totalTravelDays) {
        int avgDays = totalTravelDays / staffs.size();
        int remainDays = totalTravelDays % staffs.size();
        for(Staff staff : staffs){
            staff.setBusinessTravelTimes(avgDays);
        }
        randomDistributionRemainDays(staffs, remainDays);
    }

    private void randomDistributionRemainDays(List<Staff> staffs, int remainDays) {
        for(int i=0 ; i<remainDays ; i++){
            int randomStaff = (int) (Math.random() * staffs.size());
            staffs.get(randomStaff).setBusinessTravelTimes(staffs.get(randomStaff).getBusinessTravelTimes() + 1);
        }
    }

    public void generateResultFile(){

    }


}
