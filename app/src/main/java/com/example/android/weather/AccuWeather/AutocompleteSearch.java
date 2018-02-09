package com.example.android.weather.AccuWeather;

public class AutocompleteSearch {

    public int mVersion;
    public String mKey;
    public String mType;
    public int mRank;
    public String mLocalizedName;
    public Country mCountry = new Country();
    public AdministrativeArea mArea = new AdministrativeArea();


    public class Country{
        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getLocalizedName() {
            return localizedName;
        }

        public void setLocalizedName(String localizedName) {
            this.localizedName = localizedName;
        }

        private String ID;
        private String localizedName;
    }

    public class AdministrativeArea{
        public String getLocalizedName() {
            return localizedName;
        }

        public void setLocalizedName(String localizedName) {
            this.localizedName = localizedName;
        }

        public String getID() {

            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        private String ID;
        private String localizedName;
    }

}
