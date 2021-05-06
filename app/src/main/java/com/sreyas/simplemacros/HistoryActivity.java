package com.sreyas.simplemacros;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setRecyclerView();
        setClickListeners();
    }

    private void setRecyclerView() {
        RecyclerView history = (RecyclerView) findViewById(R.id.history);
        history.setAdapter(new HistoryAdapter());
        history.setLayoutManager(new LinearLayoutManager(this));
        history.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    private void setClickListeners() {
        findViewById(R.id.today).setOnClickListener((View view) -> {
            launchMainActivity();
        });
    }

    private void launchMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private static class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

        private final List<DayMacroRecord> records = DayMacroRecordData.getRecords();

        private static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView date;
            public TextView calories;
            public TextView proteinGrams;

            private ViewHolder(View view) {
                super(view);
                date = view.findViewById(R.id.date);
                calories = view.findViewById(R.id.calories);
                proteinGrams = view.findViewById(R.id.protein);
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View historyRow = inflater.inflate(R.layout.row_history, parent, false);
            return new ViewHolder(historyRow);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            DayMacroRecord record = records.get(records.size() - position - 1);
            holder.date.setText(record.getDateString());
            holder.calories.setText(record.getCaloriesString());
            holder.proteinGrams.setText(record.getProteinGramsString());
        }

        @Override
        public int getItemCount() {
            return records.size();
        }
    }
}
