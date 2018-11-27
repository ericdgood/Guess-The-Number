package com.example.edgoo.numberguess;

import android.os.Parcel;
import android.os.Parcelable;

public class NumberGuessData implements Parcelable {

    NumberGuessData(){
    }

    private NumberGuessData(Parcel in) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
