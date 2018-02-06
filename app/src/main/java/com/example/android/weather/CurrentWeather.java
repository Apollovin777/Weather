package com.example.android.weather;


import android.net.Uri;

public class CurrentWeather {
    private static String TAG = "CurrentWeather";

    public LocationSetting mLocationSetting;
    public CurrentWeatherConditions mCurrentCondition = new CurrentWeatherConditions();
    public Temperature mTemperature = new Temperature();
    public Wind mWind = new Wind();
    public Rain mRain = new Rain();
    public Snow mSnow = new Snow()	;
    public Clouds mCouds = new Clouds();
    public byte[] mIconData;

    public class CurrentWeatherConditions {
        private int mID;
        private String condition;
        private String descr;
        private String icon;

        private float pressure;
        private float humidity;

        public int getWeatherId() {
            return mID;
        }

        public void setWeatherId(int ID) {
            mID = ID;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getDescr() {
            return descr;
        }

        public void setDescr(String descr) {
            this.descr = descr;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public float getPressure() {
            return pressure;
        }

        public void setPressure(float pressure) {
            this.pressure = pressure;
        }

        public float getHumidity() {
            return humidity;
        }

        public void setHumidity(float humidity) {
            this.humidity = humidity;
        }
    }

    public class Temperature {
        private float temp;
        private float minTemp;
        private float maxTemp;

        public float getTemp() {
            return temp;
        }

        public void setTemp(float temp) {
            this.temp = temp;
        }

        public float getMinTemp() {
            return minTemp;
        }

        public void setMinTemp(float minTemp) {
            this.minTemp = minTemp;
        }

        public float getMaxTemp() {
            return maxTemp;
        }

        public void setMaxTemp(float maxTemp) {
            this.maxTemp = maxTemp;
        }
    }

    public class Wind{
        private float speed;
        private float degree;

        public float getSpeed() {
            return speed;
        }

        public void setSpeed(float speed) {
            this.speed = speed;
        }

        public float getDegree() {
            return degree;
        }

        public void setDegree(float degree) {
            this.degree = degree;
        }
    }

    public  class Rain {
        private float ammount;

        public float getAmmount() {
            return ammount;
        }

        public void setAmmount(float ammount) {
            this.ammount = ammount;
        }
    }

    public  class Snow {

        private float ammount;

        public float getAmmount() {
            return ammount;
        }

        public void setAmmount(float ammount) {
            this.ammount = ammount;
        }
    }

    public  class Clouds {
        private int perc;

        public int getPerc() {
            return perc;
        }

        public void setPerc(int perc) {
            this.perc = perc;
        }
    }

    public Uri getIconUri(){
        return Uri.parse("http://openweathermap.org/img/w/")
                .buildUpon()
                .appendPath(mCurrentCondition.getIcon()+".png")
                .build();
    }

}
