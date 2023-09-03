package com.t4zb.self_improvement.model;

import java.util.List;

public class Avatar {
    private String avatarId;// nullable
    private String userDataId;
    private String dataResourceUrl;
    private int primaryColor;
    private int secondaryColor;
    private List<Clothes> clothesList;
    private Clothes currentClothes;

    public String getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    public String getUserDataId() {
        return userDataId;
    }

    public void setUserDataId(String userDataId) {
        this.userDataId = userDataId;
    }

    public String getDataResourceUrl() {
        return dataResourceUrl;
    }

    public void setDataResourceUrl(String dataResourceUrl) {
        this.dataResourceUrl = dataResourceUrl;
    }

    public int getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
    }

    public int getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(int secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public List<Clothes> getClothesList() {
        return clothesList;
    }

    public void setClothesList(List<Clothes> clothesList) {
        this.clothesList = clothesList;
    }

    public Clothes getCurrentClothes() {
        return currentClothes;
    }

    public void setCurrentClothes(Clothes currentClothes) {
        this.currentClothes = currentClothes;
    }
}
