package com.example.notetakingapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> implements Filterable {
    private List<String> notes, filteredNotes;
    public NotesAdapter(List<String> notes) {
        this.notes = notes;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the layout for each item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Bind data to the ViewHolder
        String note = notes.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String filterText = charSequence.toString().toLowerCase().trim();
                List<String> filteredList = new ArrayList<>();
                Log.d("NOTESADAPTER",charSequence.toString());
                for (String note : notes) {
                    Log.d("NOTESADAPTER","note : "+note+charSequence.toString());

                    if (note.toLowerCase().contains(filterText)) {
                        filteredList.add(note);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = Collections.emptyList();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                notes.clear();
                notes.addAll((List<String>) filterResults.values);
                notifyDataSetChanged(); // Notify the adapter of changes
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView contentTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            contentTextView = itemView.findViewById(R.id.contentTextView);

        }

        public void bind(String note) {
            contentTextView.setText(note);
        }
    }
}