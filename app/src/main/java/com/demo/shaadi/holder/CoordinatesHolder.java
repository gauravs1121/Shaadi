package com.demo.shaadi.holder;

import android.location.Address;
import android.location.Geocoder;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

//@Entity(tableName = "cord_table")
public class CoordinatesHolder {
    /*    @PrimaryKey(autoGenerate = true)
        private int _cId;*/
    private String latitude;
    private String longitude;


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    @BindingAdapter("userLocation")
    public static void getUserAddress(TextView textView, CoordinatesHolder coordinatesHolder) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(textView.getContext(), Locale.getDefault());

        double lat = Double.parseDouble(coordinatesHolder.getLatitude());
        double lng = Double.parseDouble(coordinatesHolder.getLongitude());
        try {
            addresses = geocoder.getFromLocation(lat, lng, 1);
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            if (city != null && country != null)
                textView.setText(String.format("%s, %s", city, country));
            if (city != null && country == null)
                textView.setText(String.format("%s", city));
            if (country != null && city == null)
                textView.setText(String.format("%s", country));
        } catch (Exception e) {
            e.printStackTrace();
            textView.setText("");
        }

    }
}
