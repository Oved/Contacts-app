package com.example.contactsapp.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.contactsapp.databinding.ActivityMainBinding;
import com.example.contactsapp.databinding.DialogContactBinding;
import com.example.contactsapp.domain.Contact;
import com.example.contactsapp.interfaces.iPresenter;
import com.example.contactsapp.interfaces.iView;
import com.example.contactsapp.presenter.PresenterImpl;
import com.example.contactsapp.view.adapter.ContactsAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements iView, SearchView.OnQueryTextListener, SearchView.OnCloseListener {

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

        binding.customTb.searchView.setOnSearchClickListener(v -> {
            binding.customTb.tvTitleToolbar.setVisibility(View.GONE);
            binding.customTb.imgOptions.setVisibility(View.GONE);
        });
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
        adapter = new ContactsAdapter(contacts, this::moveToDescription);
        binding.recycler.setAdapter(adapter);
        binding.recycler.addItemDecoration(dividerItemDecoration);
        binding.recycler.setLayoutManager(linear);
        binding.customTb.searchView.setOnQueryTextListener(this);
        binding.customTb.searchView.setOnCloseListener(this);
    }

    @Override
    public void showError(String message) {
        binding.progress.setVisibility(View.GONE);
        binding.linearRetry.setVisibility(View.VISIBLE);
        binding.txtError.setText(message);
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        adapter.filter(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.isEmpty()) adapter.filter("");
        return false;
    }

    private void moveToDescription(Contact contact) {
        DialogContactBinding dialogBinding= DialogContactBinding.inflate(getLayoutInflater());
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(dialogBinding.getRoot()).setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
        dialogBinding.btnClose.setOnClickListener(v -> dialog.cancel());
        dialogBinding.nameCard.setText(contact.getName());
        dialogBinding.phoneCard.setText(contact.getPhone());
        dialogBinding.emailCard.setText(contact.getEmail());
        dialogBinding.btnCall.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Calling...", Toast.LENGTH_SHORT).show();
            dialog.cancel();
        });
        dialogBinding.btnSendEmail.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Sending mail...", Toast.LENGTH_SHORT).show();
            dialog.cancel();
        });
    }

    @Override
    public boolean onClose() {
        binding.customTb.tvTitleToolbar.setVisibility(View.VISIBLE);
        binding.customTb.imgOptions.setVisibility(View.VISIBLE);
        return false;
    }
}