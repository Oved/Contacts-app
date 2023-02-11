package com.example.contactsapp.interfaces;

import com.example.contactsapp.domain.Contact;

import java.util.List;

public interface iView {

    void showResults(List<Contact> contacts);

    void showError(String message);
}
