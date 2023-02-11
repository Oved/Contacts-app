package com.example.contactsapp.presenter;

import com.example.contactsapp.domain.Contact;
import com.example.contactsapp.interfaces.iModel;
import com.example.contactsapp.interfaces.iPresenter;
import com.example.contactsapp.interfaces.iView;
import com.example.contactsapp.model.ModelImpl;

import java.util.List;

public class PresenterImpl implements iPresenter {

    private iView view;
    private iModel model;

    public PresenterImpl(iView view){
        this.view = view;
        model = new ModelImpl(this);
    }

    @Override
    public void presenterSearchContacts() {
        model.modelSearchContacts();
    }

    @Override
    public void presenterShowResults(List<Contact> contacts) {
        view.showResults(contacts);
    }

    @Override
    public void presenterShowError(String message) {

    }
}
