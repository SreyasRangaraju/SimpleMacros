package com.sreyas.simplemacros;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements UpdateMacroDialog.UpdateMacroDialogListener {
    private DayMacroRecord record;
    private TextView calories;
    private TextView protein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        setClickListeners();
        updateMacrosView();
    }

    @Override
    public void onDialogPositiveClick(int calories, int proteinGrams) {
        record.addMeal(calories, proteinGrams);
        updateMacrosView();
    }

    private void setViews() {
        record = DayMacroRecordData.getCurrentRecord();
        calories = findViewById(R.id.calories);
        protein = findViewById(R.id.protein);
    }

    private void setClickListeners() {
        findViewById(R.id.add_meal).setOnClickListener((View view) -> {
            UpdateMacroDialog.showDialog(getSupportFragmentManager());
        });
        findViewById(R.id.reset).setOnClickListener((View view) -> {
            record.reset();
            updateMacrosView();
        });
        findViewById(R.id.history).setOnClickListener((View view) -> {
            launchHistoryActivity();
        });
    }

    private void launchHistoryActivity() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    private void updateMacrosView() {
        calories.setText(record.getCaloriesString());
        protein.setText(record.getProteinGramsString());
        DayMacroRecordData.saveRecords();
    }
}