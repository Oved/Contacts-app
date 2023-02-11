package com.example.contactsapp.interfaces;

import com.example.contactsapp.domain.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/users")
    Call<List<Contact>> service();
}
