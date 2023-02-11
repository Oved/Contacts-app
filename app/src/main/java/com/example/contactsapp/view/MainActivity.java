package com.example.contactsapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.contactsapp.databinding.ActivityMainBinding;
import com.example.contactsapp.domain.Contact;
import com.example.contactsapp.interfaces.iPresenter;
import com.example.contactsapp.interfaces.iView;
import com.example.contactsapp.presenter.PresenterImpl;
import com.example.contactsapp.view.adapter.ContactsAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements iView {

    private ActivityMainBinding binding;
    private iPresenter presenter;
    private LinearLayoutManager linear;
    private DividerItemDecoration dividerItemDecoration;
    private ContactsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        searchContacts();

        binding.btnRetry.setOnClickListener(v -> searchContacts());
    }

    public void init(){
        presenter = new PresenterImpl(this);
        linear = new LinearLayoutManager(this,RecyclerView.VERTICAL, false);
        dividerItemDecoration = new DividerItemDecoration(this, linear.getOrientation());

    }

    private void searchContacts(){
        binding.linearRetry.setVisibility(View.GONE);
        binding.progress.setVisibility(View.VISIBLE);
        presenter.presenterSearchContacts();
    }

    @Override
    public void showResults(List<Contact> contacts) {
        binding.progress.setVisibility(View.GONE);
        adapter = new ContactsAdapter(contacts);
        binding.recycler.setAdapter(adapter);
        binding.recycler.addItemDecoration(dividerItemDecoration);
        binding.recycler.setLayoutManager(linear);
    }

    @Override
    public void showError(String message) {
        binding.progress.setVisibility(View.GONE);
        binding.linearRetry.setVisibility(View.VISIBLE);
        binding.txtError.setText(message);
    }
}