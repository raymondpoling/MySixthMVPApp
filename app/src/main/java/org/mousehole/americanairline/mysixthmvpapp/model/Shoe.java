package org.mousehole.americanairline.mysixthmvpapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Shoe implements Parcelable {
    private int shoeSize;
    private String shoeModel;
    private int shoeId;
    private double shoePrice;

    public Shoe(int shoeSize, String shoeModel, double shoePrice) {
        this.shoeSize = shoeSize;
        this.shoeModel = shoeModel;
        this.shoePrice = shoePrice;
    }

    public Shoe(int shoeSize, String shoeModel, int shoeId, double shoePrice) {
        this.shoeSize = shoeSize;
        this.shoeModel = shoeModel;
        this.shoeId = shoeId;
        this.shoePrice = shoePrice;
    }

    protected Shoe(Parcel in) {
        shoeSize = in.readInt();
        shoeModel = in.readString();
        shoeId = in.readInt();
        shoePrice = in.readDouble();
    }

    public static final Creator<Shoe> CREATOR = new Creator<Shoe>() {
        @Override
        public Shoe createFromParcel(Parcel in) {
            return new Shoe(in);
        }

        @Override
        public Shoe[] newArray(int size) {
            return new Shoe[size];
        }
    };

    public int getShoeId() {
        return shoeId;
    }

    public int getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(int shoeSize) {
        this.shoeSize = shoeSize;
    }

    public String getShoeModel() {
        return shoeModel;
    }

    public void setShoeModel(String shoeModel) {
        this.shoeModel = shoeModel;
    }

    public double getShoePrice() {
        return shoePrice;
    }

    public void setShoePrice(double shoePrice) {
        this.shoePrice = shoePrice;
    }

    @Override
    public String toString() {
        return "Model: " + shoeModel + "Size: " + shoeSize + " Price: " + shoePrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(shoeSize);
        parcel.writeString(shoeModel);
        parcel.writeInt(shoeId);
        parcel.writeDouble(shoePrice);
    }
}
