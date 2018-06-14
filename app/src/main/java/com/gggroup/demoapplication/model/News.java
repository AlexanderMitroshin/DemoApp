package com.gggroup.demoapplication.model;

import android.os.Parcel;
import android.os.Parcelable;


public class News implements Parcelable {

    private String title;
    private String short_text;
    private String full_text;
    private String picture;
    private String coefficient;
    private String schedule;
    private String publicated_at;
    private String type;

    protected News(Parcel in) {
        title = in.readString();
        short_text = in.readString();
        full_text = in.readString();
        picture = in.readString();
        coefficient = in.readString();
        schedule = in.readString();
        publicated_at = in.readString();
        type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(short_text);
        dest.writeString(full_text);
        dest.writeString(picture);
        dest.writeString(coefficient);
        dest.writeString(schedule);
        dest.writeString(publicated_at);
        dest.writeString(type);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getShort_text() {
        return short_text;
    }

    public String getFull_text() {
        return full_text;
    }

    public String getPicture() {
        return picture;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getPublicated_at() {
        return publicated_at;
    }

    public String getType() {
        return type;
    }
}
