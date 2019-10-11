package com.example.rickmortykarakter.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Karakter implements Parcelable {
    private String nama;
    private String status;
    private String spesies;
    private String gender;
    private String origin;
    private String gambar;

    public Karakter(String nama, String status, String gambar) {
        this.nama = nama;
        this.status = status;
        this.gambar = gambar;
    }

    private String lokasi;

    public Karakter(String nama, String status, String spesies, String gender, String origin, String gambar, String lokasi) {
        this.nama = nama;
        this.status = status;
        this.spesies = spesies;
        this.gender = gender;
        this.origin = origin;
        this.gambar = gambar;
        this.lokasi = lokasi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpesies() {
        return spesies;
    }

    public void setSpesies(String spesies) {
        this.spesies = spesies;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
        dest.writeString(this.status);
        dest.writeString(this.spesies);
        dest.writeString(this.gender);
        dest.writeString(this.origin);
        dest.writeString(this.gambar);
        dest.writeString(this.lokasi);
    }

    protected Karakter(Parcel in) {
        this.nama = in.readString();
        this.status = in.readString();
        this.spesies = in.readString();
        this.gender = in.readString();
        this.origin = in.readString();
        this.gambar = in.readString();
        this.lokasi = in.readString();
    }

    public static final Parcelable.Creator<Karakter> CREATOR = new Parcelable.Creator<Karakter>() {
        @Override
        public Karakter createFromParcel(Parcel source) {
            return new Karakter(source);
        }

        @Override
        public Karakter[] newArray(int size) {
            return new Karakter[size];
        }
    };
}
