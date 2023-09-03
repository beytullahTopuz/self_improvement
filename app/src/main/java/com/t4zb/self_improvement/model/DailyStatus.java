package com.t4zb.self_improvement.model;

import java.util.List;

public class DailyStatus {

    private String dailyStatusId;
    private String userDataId;
    private List<DailyStatusValue> dailyStatusValue;

    public String getDailyStatusId() {
        return dailyStatusId;
    }

    public void setDailyStatusId(String dailyStatusId) {
        this.dailyStatusId = dailyStatusId;
    }

    public String getUserDataId() {
        return userDataId;
    }

    public void setUserDataId(String userDataId) {
        this.userDataId = userDataId;
    }

    public List<DailyStatusValue> getDailyStatusValue() {
        return dailyStatusValue;
    }

    public void setDailyStatusValue(List<DailyStatusValue> dailyStatusValue) {
        this.dailyStatusValue = dailyStatusValue;
    }
}
