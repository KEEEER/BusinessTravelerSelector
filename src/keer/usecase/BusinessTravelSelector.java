package keer.usecase;

import keer.domain.AccompanyStaffSet;
import keer.domain.Staff;
import keer.repository.AccompanyFileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.min;

public class BusinessTravelSelector {
    private Map<String, Integer> staffTotalTravelDays;
    private Map<String, Integer> staffRemainTravelDays;
    private List<AccompanyStaffSet> choreographyResult;

    public BusinessTravelSelector(){
        this.staffRemainTravelDays = new HashMap<>();
        this.staffTotalTravelDays = new HashMap<>();
    }

    public List<AccompanyStaffSet> choreographyBusinessTravel(List<Staff> staffs, List<AccompanyStaffSet> accompanyStaffSets, int totalTravelDays){
        int totalTravelCount = totalTravelDays * 2;
        this.choreographyResult = new ArrayList<>(totalTravelDays);
        for(int i=0 ; i<totalTravelDays ; i++) this.choreographyResult.add(new AccompanyStaffSet());
        this.staffRemainTravelDays = new HashMap<>();
        this.staffTotalTravelDays = new HashMap<>();

        fairlyDistributedDaysToStaffs(staffs, totalTravelCount);
        randomDistributeAccompanyStaffs(accompanyStaffSets);
        randomDistributeStaffs(staffs);
        return choreographyResult;
    }

    private void randomDistributeStaffs(List<Staff> staffs) {
        if(getUndistributedDayCount()%2 == 1) staffRemainTravelDays.put("unknown", 1);
        while(getUndistributedDayCount() >= 1){
            int randomDay = getUndistributedDay();
            AccompanyStaffSet accompanyStaffSet = randomMatchAccompanyStaff(staffs);
            choreographyResult.set(randomDay, accompanyStaffSet);
        }
    }

    private void randomDistributeAccompanyStaffs(List<AccompanyStaffSet> accompanyStaffSets) {
        for(AccompanyStaffSet accompanyStaffSet : accompanyStaffSets){
            // special : a row item need to put in same list to show, so last one is ratio.
            double ratio = Double.parseDouble(accompanyStaffSet.getAccompanyStaffs().get(accompanyStaffSet.getAccompanyStaffs().size()-1));
            int accompanyDay = (int) (getLessDay(accompanyStaffSet) * ratio / 100.0);
            for(int i=0 ; i<accompanyDay ; i++){
                int randomDay = getUndistributedDay();
                this.choreographyResult.set(randomDay, accompanyStaffSet);
                updateRemainDays(accompanyStaffSet);
            }
        }
    }

    private int getLessDay(AccompanyStaffSet accompanyStaffSet) {
        String staff1 = accompanyStaffSet.getAccompanyStaffs().get(0);
        String staff2 = accompanyStaffSet.getAccompanyStaffs().get(1);
        return min(staffTotalTravelDays.get(staff1), staffTotalTravelDays.get(staff2));
    }

    private int getUndistributedDay() {
        int randomDay;
        do{
            randomDay = (int) (Math.random() * this.choreographyResult.size());
        }while(choreographyResult.get(randomDay).isStaffsSettleUp());
        return randomDay;
    }

    private void fairlyDistributedDaysToStaffs(List<Staff> staffs, int totalTravelDays) {
        int avgDays = totalTravelDays / staffs.size();
        int remainDays = totalTravelDays % staffs.size();
        for(Staff staff : staffs){
            staff.setBusinessTravelTimes(avgDays);
            staffRemainTravelDays.put(staff.getStaffName(), staff.getBusinessTravelTimes());
            staffTotalTravelDays.put(staff.getStaffName(), staff.getBusinessTravelTimes());
        }
        randomDistributionRemainDays(staffs, remainDays);
    }

    private void randomDistributionRemainDays(List<Staff> staffs, int remainDays) {
        for(int i=0 ; i<remainDays ; i++){
            int randomStaff = (int) (Math.random() * staffs.size());
            staffs.get(randomStaff).setBusinessTravelTimes(staffs.get(randomStaff).getBusinessTravelTimes() + 1);
            staffRemainTravelDays.put(staffs.get(randomStaff).getStaffName(), staffs.get(randomStaff).getBusinessTravelTimes());
            staffTotalTravelDays.put(staffs.get(randomStaff).getStaffName(), staffs.get(randomStaff).getBusinessTravelTimes());
        }
    }

    private void updateRemainDays(AccompanyStaffSet accompanyStaffSet){
        for(int j=0 ; j<accompanyStaffSet.getAccompanyStaffs().size()-1 ; j++){
            String name = accompanyStaffSet.getAccompanyStaffs().get(j);
            int staffRemainDays = staffRemainTravelDays.get(name);
            staffRemainTravelDays.put(name, staffRemainDays-1);
        }
    }

    private int getUndistributedDayCount(){
        int result = 0;
        for(String staffName : staffRemainTravelDays.keySet()){
            result += staffRemainTravelDays.get(staffName);
        }
        return result;
    }

    private AccompanyStaffSet randomMatchAccompanyStaff(List<Staff> staffs){
        AccompanyStaffSet resultAccompanySttaffSet = new AccompanyStaffSet();
        int randomStaff;
        String randomStaffName;
        while(resultAccompanySttaffSet.getAccompanyStaffs().size() < 2){
            do{
                randomStaff = (int) (staffs.size() * Math.random());
                randomStaffName = staffs.get(randomStaff).getStaffName();
            }while(staffRemainTravelDays.get(randomStaffName) == 0);
            resultAccompanySttaffSet.addStaff(randomStaffName);
            staffRemainTravelDays.put(randomStaffName, staffRemainTravelDays.get(randomStaffName)-1);
        }
        return resultAccompanySttaffSet;
    }
//"travel.xlsx"
    public void generateResultFile(String filePath, List<AccompanyStaffSet> accompanyStaffSets) throws IOException {
        AccompanyFileWriter accompanyFileWriter = new AccompanyFileWriter(filePath);
        accompanyFileWriter.generateFile(accompanyStaffSets);
    }


}
