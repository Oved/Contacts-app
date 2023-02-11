package com.example.contactsapp.model;

import com.example.contactsapp.domain.Contact;
import com.example.contactsapp.interfaces.ApiService;
import com.example.contactsapp.interfaces.iModel;
import com.example.contactsapp.interfaces.iPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModelImpl implements iModel {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private iPresenter presenter;

    public ModelImpl(iPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void modelSearchContacts() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<List<Contact>> call = service.service();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                if (!response.isSuccessful()){
                    presenter.presenterShowError("Ha ocurrido un error en la respuesta");
                }
                presenter.presenterShowResults(response.body());
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {

            }
        });
    }
}
