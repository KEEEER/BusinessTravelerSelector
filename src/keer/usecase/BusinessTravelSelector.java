package keer.usecase;

import keer.domain.AccompanyStaffSet;
import keer.domain.Staff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.min;

public class BusinessTravelSelector {
    private Map<String, Integer> staffAlreadyDistributedDays;
    private List<AccompanyStaffSet> choreographyResult;

    public BusinessTravelSelector(){
        staffAlreadyDistributedDays = new HashMap<>();
    }

    public List<AccompanyStaffSet> choreographyBusinessTravel(List<Staff> staffs, List<AccompanyStaffSet> accompanyStaffSets, int totalTravelDays){
        this.choreographyResult = new ArrayList<>(totalTravelDays);
        staffAlreadyDistributedDays = new HashMap<>();

        fairlyDistributedDaysToStaffs(staffs, totalTravelDays);
        randomDistributeAccompanyStaffs(accompanyStaffSets);
        randomDistributeStaffs(staffs);
        return choreographyResult;
    }

    private void randomDistributeStaffs(List<Staff> staffs) {

    }

    private void randomDistributeAccompanyStaffs(List<AccompanyStaffSet> accompanyStaffSets) {
        for(AccompanyStaffSet accompanyStaffSet : accompanyStaffSets){
            int randomDay;
            int ratio = Integer.parseInt(accompanyStaffSet.getAccompanyStaffs().get(accompanyStaffSet.getAccompanyStaffs().size()-1));
            int accompanyDay = getLessDay(accompanyStaffSet);
            for(int i=0 ; i<accompanyDay ; i++){
                randomDay = getUndistributedDay();
                choreographyResult.set(randomDay, accompanyStaffSet);
            }
        }
    }

    private int getLessDay(AccompanyStaffSet accompanyStaffSet) {
        String staff1 = accompanyStaffSet.getAccompanyStaffs().get(0);
        String staff2 = accompanyStaffSet.getAccompanyStaffs().get(1);
        return min(staffAlreadyDistributedDays.get(staff1), staffAlreadyDistributedDays.get(staff2));
    }

    private int getUndistributedDay() {
        int randomDay;
        do{
            randomDay = (int) (Math.random() * this.choreographyResult.size());
        }while(!choreographyResult.get(randomDay).isStaffsSettleUp());
        return randomDay;
    }

    private void fairlyDistributedDaysToStaffs(List<Staff> staffs, int totalTravelDays) {
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
            staffAlreadyDistributedDays.put(staffs.get(randomStaff).getStaffName(), staffs.get(randomStaff).getBusinessTravelTimes());
        }
    }

    public void generateResultFile(){

    }


}
