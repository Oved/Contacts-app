package com.example.contactsapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsapp.R;
import com.example.contactsapp.databinding.CardViewBinding;
import com.example.contactsapp.domain.Contact;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private List<Contact> contactList;

    public ContactsAdapter(List<Contact> contactList){
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder{

        private CardViewBinding binding;

        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CardViewBinding.bind(itemView);
        }

        public void onBind(int position){
            binding.tvName.setText(contactList.get(position).getName());
            binding.tvUserName.setText(contactList.get(position).getUsername());
            binding.tvEmail.setText(contactList.get(position).getEmail());
            binding.tvPhone.setText(contactList.get(position).getPhone());
            binding.tvWebsite.setText(contactList.get(position).getWebsite());
        }
    }
}
