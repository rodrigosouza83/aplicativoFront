package com.rsouzasouza.viewModel;

import com.google.gson.annotations.SerializedName;
import com.rsouzasouza.view.SyncLoginActivity;

public class cadastroAluno extends SyncLoginActivity {


    @SerializedName("ID")
    private int id;
    private String  name;
    private String password;
    private String email;

    public cadastroAluno(String email, String password) {
        super(email, password);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
                this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

