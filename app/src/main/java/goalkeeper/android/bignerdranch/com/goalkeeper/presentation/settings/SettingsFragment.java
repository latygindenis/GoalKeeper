package goalkeeper.android.bignerdranch.com.goalkeeper.presentation.settings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import goalkeeper.android.bignerdranch.com.goalkeeper.R;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.Goal;
import goalkeeper.android.bignerdranch.com.goalkeeper.data.GoalsLab;

/**
 * Created by denis on 11.06.2018.
 */

public class SettingsFragment extends Fragment {
    CardView instruction;
    CardView clean_progress;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, null);
        instruction = v.findViewById(R.id.instruction_card);
        clean_progress = v.findViewById(R.id.clean_progress_card);

        instruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Инструкция")
                        .setMessage("Данное приложение позволяет вам " +
                                "закреплять ваши привычки. Поставьте цель, захо" +
                                "дите каждый деньи вот увидете как ваша жизнь изменится!")
                        .setCancelable(true)
                        .setNegativeButton("Вас понял!", null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        clean_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Сброс прогресса")
                        .setMessage("Вы уверены, что хотите начать заново?")
                        .setCancelable(true)
                        .setNegativeButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                GoalsLab.get(getActivity()).deleteAllGoals();
                            }
                        })
                        .setPositiveButton("Нет", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        return v;
    }
}
