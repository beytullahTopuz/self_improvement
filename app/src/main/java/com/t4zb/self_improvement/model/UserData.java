package com.t4zb.self_improvement.model;

public class UserData {
    private String userDataId;
    private String userId;
    private String avatarId;
    private String userPlanId;
    private String userDailyStatusId;
    private int coin;
    private int ranked;

    public UserData() {

    }

    public String getUserDataId() {
        return userDataId;
    }

    public void setUserDataId(String userDataId) {
        this.userDataId = userDataId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    public String getUserPlanId() {
        return userPlanId;
    }

    public void setUserPlanId(String userPlanId) {
        this.userPlanId = userPlanId;
    }

    public String getUserDailyStatusId() {
        return userDailyStatusId;
    }

    public void setUserDailyStatusId(String userDailyStatusId) {
        this.userDailyStatusId = userDailyStatusId;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getRanked() {
        return ranked;
    }

    public void setRanked(int ranked) {
        this.ranked = ranked;
    }
}
