package com.sreyas.simplemacros;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class UpdateMacroDialog extends DialogFragment {

    private UpdateMacroDialogListener listener;

    public static void showDialog(FragmentManager fragmentManager) {
        UpdateMacroDialog dialog = new UpdateMacroDialog();
        dialog.show(fragmentManager, "");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (UpdateMacroDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement " +
                    "UpdateMacroDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getDialogView();
        return buildDialog(view);
    }

    private View getDialogView() {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        return inflater.inflate(R.layout.dialog_macro, null);
    }

    private Dialog buildDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("Save", getPositiveClickListener(view))
                .setNegativeButton("Cancel", null);
        return builder.create();
    }

    private DialogInterface.OnClickListener getPositiveClickListener(View view) {
        return (dialog, which) -> listener.onDialogPositiveClick(
                getEditTextValue(view, R.id.add_calories),
                getEditTextValue(view, R.id.add_protein));
    }

    private int getEditTextValue(View view, int id) {
        String value = ((EditText) view.findViewById(id)).getText().toString();
        if (value.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(value);
    }

    public interface UpdateMacroDialogListener {
        void onDialogPositiveClick(int calories, int proteinGrams);
    }
}
