package com.example.android.weather.AccuWeather;

import java.util.Date;

public class CurrentConditions {
    private Date mLocalObservationDateTime;
    private long mEpochTime;
    private String mWeatherText;
    private int mWeatherIcon;
    private boolean mIsDayTime;
    private Temperature mCurrentTemperature;
    private Temperature mRealFeelTemperature;
    private Temperature mRealFeelTemperatureShade;
    private int mRelativeHumidity;
    private Temperature mDewPoint;
    private Wind mWind;
    private WindGust mWindGust;
    private int mUVIndex;
    private String mUVIndexText;
    private Visibility mVisibility;
    private String mObstructionsToVisibility;
    private int mCloudCover;
    private Ceiling mCeiling;
    private Pressure mPressure;
    private PressureTendency mPressureTendency;
    private Temperature mPast24HourTemperatureDeparture;
    private Temperature mApparentTemperature;
    private Temperature WindChillTemperature;
    private Temperature WetBulbTemperature;
    private Precipitation mPrecip1hr;
    private Precipitation mPrecipitationSummary;

    public Date getLocalObservationDateTime() {
        return mLocalObservationDateTime;
    }

    public void setLocalObservationDateTime(Date localObservationDateTime) {
        mLocalObservationDateTime = localObservationDateTime;
    }

    public long getEpochTime() {
        return mEpochTime;
    }

    public void setEpochTime(long epochTime) {
        mEpochTime = epochTime;
    }

    public String getWeatherText() {
        return mWeatherText;
    }

    public void setWeatherText(String weatherText) {
        mWeatherText = weatherText;
    }

    public int getWeatherIcon() {
        return mWeatherIcon;
    }

    public void setWeatherIcon(int weatherIcon) {
        mWeatherIcon = weatherIcon;
    }

    public boolean isDayTime() {
        return mIsDayTime;
    }

    public void setDayTime(boolean dayTime) {
        mIsDayTime = dayTime;
    }

    public Temperature getCurrentTemperature() {
        return mCurrentTemperature;
    }

    public void setCurrentTemperature(Temperature currentTemperature) {
        mCurrentTemperature = currentTemperature;
    }

    public Temperature getRealFeelTemperature() {
        return mRealFeelTemperature;
    }

    public void setRealFeelTemperature(Temperature realFeelTemperature) {
        mRealFeelTemperature = realFeelTemperature;
    }

    public Temperature getRealFeelTemperatureShade() {
        return mRealFeelTemperatureShade;
    }

    public void setRealFeelTemperatureShade(Temperature realFeelTemperatureShade) {
        mRealFeelTemperatureShade = realFeelTemperatureShade;
    }

    public int getRelativeHumidity() {
        return mRelativeHumidity;
    }

    public void setRelativeHumidity(int relativeHumidity) {
        mRelativeHumidity = relativeHumidity;
    }

    public Temperature getDewPoint() {
        return mDewPoint;
    }

    public void setDewPoint(Temperature dewPoint) {
        mDewPoint = dewPoint;
    }

    public Wind getWind() {
        return mWind;
    }

    public void setWind(Wind wind) {
        mWind = wind;
    }

    public WindGust getWindGust() {
        return mWindGust;
    }

    public void setWindGust(WindGust windGust) {
        mWindGust = windGust;
    }

    public int getUVIndex() {
        return mUVIndex;
    }

    public void setUVIndex(int UVIndex) {
        mUVIndex = UVIndex;
    }

    public String getUVIndexText() {
        return mUVIndexText;
    }

    public void setUVIndexText(String UVIndexText) {
        mUVIndexText = UVIndexText;
    }

    public Visibility getVisibility() {
        return mVisibility;
    }

    public void setVisibility(Visibility visibility) {
        mVisibility = visibility;
    }

    public String getObstructionsToVisibility() {
        return mObstructionsToVisibility;
    }

    public void setObstructionsToVisibility(String obstructionsToVisibility) {
        mObstructionsToVisibility = obstructionsToVisibility;
    }

    public int getCloudCover() {
        return mCloudCover;
    }

    public void setCloudCover(int cloudCover) {
        mCloudCover = cloudCover;
    }

    public Temperature getPast24HourTemperatureDeparture() {
        return mPast24HourTemperatureDeparture;
    }

    public void setPast24HourTemperatureDeparture(Temperature past24HourTemperatureDeparture) {
        mPast24HourTemperatureDeparture = past24HourTemperatureDeparture;
    }

    public Temperature getApparentTemperature() {
        return mApparentTemperature;
    }

    public void setApparentTemperature(Temperature apparentTemperature) {
        mApparentTemperature = apparentTemperature;
    }

    public Temperature getWindChillTemperature() {
        return WindChillTemperature;
    }

    public void setWindChillTemperature(Temperature windChillTemperature) {
        WindChillTemperature = windChillTemperature;
    }

    public Temperature getWetBulbTemperature() {
        return WetBulbTemperature;
    }

    public void setWetBulbTemperature(Temperature wetBulbTemperature) {
        WetBulbTemperature = wetBulbTemperature;
    }

    public Precipitation getPrecip1hr() {
        return mPrecip1hr;
    }

    public void setPrecip1hr(Precipitation precip1hr) {
        mPrecip1hr = precip1hr;
    }

    public Precipitation getPrecipitationSummary() {
        return mPrecipitationSummary;
    }

    public void setPrecipitationSummary(Precipitation precipitationSummary) {
        mPrecipitationSummary = precipitationSummary;
    }

    public Ceiling getmCeiling() {
        return mCeiling;
    }

    public void setmCeiling(Ceiling mCeiling) {
        this.mCeiling = mCeiling;
    }

    public Pressure getmPressure() {
        return mPressure;
    }

    public void setmPressure(Pressure mPressure) {
        this.mPressure = mPressure;
    }

    public PressureTendency getmPressureTendency() {
        return mPressureTendency;
    }

    public void setmPressureTendency(PressureTendency mPressureTendency) {
        this.mPressureTendency = mPressureTendency;
    }

    public static class Wind{
        private WindDirection mDirection;
        private Speed mSpeed;

        public WindDirection getDirection() {
            return mDirection;
        }

        public void setDirection(WindDirection direction) {
            mDirection = direction;
        }

        public Speed getSpeed() {
            return mSpeed;
        }

        public void setSpeed(Speed speed) {
            mSpeed = speed;
        }
    }

    public static class Visibility{
        private MeagumentUnits mMetric;
        private MeagumentUnits mImperial;

        public MeagumentUnits getMetric() {
            return mMetric;
        }

        public void setMetric(MeagumentUnits metric) {
            mMetric = metric;
        }

        public MeagumentUnits getImperial() {
            return mImperial;
        }

        public void setImperial(MeagumentUnits imperial) {
            mImperial = imperial;
        }
    }

    public static class Precipitation{
        private MeagumentUnits mMetric;
        private MeagumentUnits mImperial;

        public MeagumentUnits getMetric() {
            return mMetric;
        }

        public void setMetric(MeagumentUnits metric) {
            mMetric = metric;
        }

        public MeagumentUnits getImperial() {
            return mImperial;
        }

        public void setImperial(MeagumentUnits imperial) {
            mImperial = imperial;
        }
    }

    public static class Temperature{
        private MeagumentUnits mMetric;
        private MeagumentUnits mImperial;

        public MeagumentUnits getMetric() {
            return mMetric;
        }

        public void setMetric(MeagumentUnits metric) {
            mMetric = metric;
        }

        public MeagumentUnits getImperial() {
            return mImperial;
        }

        public void setImperial(MeagumentUnits imperial) {
            mImperial = imperial;
        }
    }

    public static class Pressure{
        private MeagumentUnits mMetric;
        private MeagumentUnits mImperial;

        public MeagumentUnits getMetric() {
            return mMetric;
        }

        public void setMetric(MeagumentUnits metric) {
            mMetric = metric;
        }

        public MeagumentUnits getImperial() {
            return mImperial;
        }

        public void setImperial(MeagumentUnits imperial) {
            mImperial = imperial;
        }
    }

    public static class PressureTendency{
        private String mLocalizedText;
        private String mCode;

        public String getLocalizedText() {
            return mLocalizedText;
        }

        public void setLocalizedText(String localizedText) {
            mLocalizedText = localizedText;
        }

        public String getCode() {
            return mCode;
        }

        public void setCode(String code) {
            mCode = code;
        }
    }

    public static class Ceiling{
        private MeagumentUnits mMetric;
        private MeagumentUnits mImperial;

        public MeagumentUnits getMetric() {
            return mMetric;
        }

        public void setMetric(MeagumentUnits metric) {
            mMetric = metric;
        }

        public MeagumentUnits getImperial() {
            return mImperial;
        }

        public void setImperial(MeagumentUnits imperial) {
            mImperial = imperial;
        }
    }

    public static class MeagumentUnits{
        private double mValue;
        private String mUnit;
        private int mUnitType;

        public double getValue() {
            return mValue;
        }

        public void setValue(double value) {
            mValue = value;
        }

        public String getUnit() {
            return mUnit;
        }

        public void setUnit(String unit) {
            mUnit = unit;
        }

        public int getUnitType() {
            return mUnitType;
        }

        public void setUnitType(int unitType) {
            mUnitType = unitType;
        }
    }

    public static class Speed{
        private MeagumentUnits mMetric;
        private MeagumentUnits mImperial;

        public MeagumentUnits getMetric() {
            return mMetric;
        }

        public void setMetric(MeagumentUnits metric) {
            mMetric = metric;
        }

        public MeagumentUnits getImperial() {
            return mImperial;
        }

        public void setImperial(MeagumentUnits imperial) {
            mImperial = imperial;
        }
    }

    public static class WindGust{
        private Speed mSpeed;

        public Speed getSpeed() {
            return mSpeed;
        }

        public void setSpeed(Speed speed) {
            mSpeed = speed;
        }
    }

    public static class WindDirection{
        private int mDegrees;
        private String mLocalized;
        private String mEnglish;

        public int getDegrees() {
            return mDegrees;
        }

        public void setDegrees(int degrees) {
            mDegrees = degrees;
        }

        public String getLocalized() {
            return mLocalized;
        }

        public void setLocalized(String localized) {
            mLocalized = localized;
        }

        public String getEnglish() {
            return mEnglish;
        }

        public void setEnglish(String english) {
            mEnglish = english;
        }
    }

}
