package com.google.apiguide.appcomponents.ipc;

import android.os.Binder;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kangren on 2018/1/10.
 */

public class User implements Parcelable{
    public int userId;
    public String userName;
    public boolean isMale;

    protected User(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        isMale = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeByte((byte) (isMale ? 1 : 0));
    }
}
