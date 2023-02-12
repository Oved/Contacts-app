package com.example.contactsapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsapp.R;
import com.example.contactsapp.databinding.CardViewBinding;
import com.example.contactsapp.domain.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private List<Contact> contactList;
    private List<Contact> newList;
    final ContactsAdapter.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(Contact contact);
    }

    public ContactsAdapter(List<Contact> contactList, ContactsAdapter.OnItemClickListener listener){
        this.contactList = contactList;
        this.newList = new ArrayList<>();
        newList.addAll(contactList);
        this.listener = listener;
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
            itemView.setOnClickListener(v -> listener.onItemClick(contactList.get(position)));
        }
    }

    public void filter(String textSearch) {
        int textLong = textSearch.length();
        if (textLong == 0) {
            contactList.clear();
            contactList.addAll(newList);
        } else {
            contactList.clear();
            for (Contact contact : newList) {
                if (contact.getName().toLowerCase().contains(textSearch.toLowerCase())) {
                    contactList.add(contact);
                }
            }
        }
        notifyDataSetChanged();
    }
}
