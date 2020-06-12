package com.demo.shaadi.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.demo.shaadi.R;

@Entity(tableName = "result_table")
public class ResultHolder {

    @PrimaryKey(autoGenerate = true)
    private int _rId;
    @Embedded(prefix = "name_")
    private NameHolder name;
    @Embedded(prefix = "location_")
    private LocationHolder location;
    @Embedded(prefix = "login_")
    private LoginHolder login;
    @Embedded(prefix = "dob_")
    private DobHolder dob;
    @Embedded(prefix = "reg_")
    private RegisteredHolder registered;
    @Embedded(prefix = "id_")
    private IdHolder id;
    @Embedded(prefix = "pic_")
    private PictureHolder picture;
    private String gender;
    private String email;
    private String phone;
    private String cell;
    private String nat;
    private String status;


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public NameHolder getName() {
        return name;
    }

    public void setName(NameHolder name) {
        this.name = name;
    }

    public LocationHolder getLocation() {
        return location;
    }

    public void setLocation(LocationHolder location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoginHolder getLogin() {
        return login;
    }

    public void setLogin(LoginHolder login) {
        this.login = login;
    }

    public DobHolder getDob() {
        return dob;
    }

    public void setDob(DobHolder dob) {
        this.dob = dob;
    }

    public RegisteredHolder getRegistered() {
        return registered;
    }

    public void setRegistered(RegisteredHolder registered) {
        this.registered = registered;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public IdHolder getId() {
        return id;
    }

    public void setId(IdHolder id) {
        this.id = id;
    }

    public PictureHolder getPicture() {
        return picture;
    }

    public void setPicture(PictureHolder picture) {
        this.picture = picture;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public int get_rId() {
        return _rId;
    }

    public void set_rId(int _rId) {
        this._rId = _rId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @BindingAdapter("userAction")
    public static void actionStatus(TextView textView, ResultHolder resultHolder) {
        if (resultHolder.getStatus() == null || resultHolder.getStatus().isEmpty())
            textView.setVisibility(View.GONE);
        else {
            if (resultHolder.getStatus().equalsIgnoreCase("accept")) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(R.string.s_accept);
            }
            if (resultHolder.getStatus().equalsIgnoreCase("decline")) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(R.string.s_decline);
            }
        }
    }

    @BindingAdapter("performAction")
    public static void userActionView(LinearLayout v, ResultHolder resultHolder) {
        if (resultHolder.getStatus() == null || resultHolder.getStatus().isEmpty())
            v.setVisibility(View.VISIBLE);
        else v.setVisibility(View.GONE);
    }


}
