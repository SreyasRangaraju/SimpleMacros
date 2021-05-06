package com.sreyas.simplemacros;

import android.content.Context;
import android.util.Log;

import org.joda.time.LocalDate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public final class DayMacroRecordData {
    private static final String TAG = "DayMacroRecordData";
    private static final String RECORDS_KEY = "DayMacroRecordData";
    private static ArrayList<DayMacroRecord> records;

    public static ArrayList<DayMacroRecord> getRecords() {
        if (records == null) {
            try {
                FileInputStream fileInputStream =
                        MacroApp.getAppContext().openFileInput(RECORDS_KEY);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                records = (ArrayList<DayMacroRecord>) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                Log.e(TAG, e.getMessage());
            } finally {
                if (records == null) {
                    records = new ArrayList<>();
                }
            }
        }
        return records;
    }

    public static DayMacroRecord getCurrentRecord() {
        getRecords();
        if (records.isEmpty() || !isToday(getLatest())) {
            records.add(new DayMacroRecord());
        }
        return getLatest();
    }

    public static void saveRecords() {
        try {
            FileOutputStream fileOutputStream =
                    MacroApp.getAppContext().openFileOutput(RECORDS_KEY, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(records);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private static DayMacroRecord getLatest() {
        return records.get(records.size() - 1);
    }

    private static boolean isToday(DayMacroRecord record) {
        return record.getDate().compareTo(new LocalDate()) == 0;
    }
}
