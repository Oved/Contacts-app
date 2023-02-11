package com.example.contactsapp.interfaces;

import com.example.contactsapp.domain.Contact;

import java.util.List;

public interface iPresenter {

    void presenterSearchContacts();
    void presenterShowResults(List<Contact> contacts);
    void presenterShowError(String message);
}
