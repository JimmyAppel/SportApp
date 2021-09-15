package com.example.cbr__fitness.fragments;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.helper.widget.Flow;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cbr__fitness.R;
import com.example.cbr__fitness.data.User;
import com.example.cbr__fitness.databasehelper.FitnessDBSqliteHelper;
import com.example.cbr__fitness.enums.EquipmentEnum;
import com.example.cbr__fitness.enums.LimitationEnum;
import com.example.cbr__fitness.logic.SharedPreferenceManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragmentLanding#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragmentLanding extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragmentLanding() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragmentLanding.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragmentLanding newInstance(String param1, String param2) {
        ProfileFragmentLanding fragment = new ProfileFragmentLanding();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_landing, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FitnessDBSqliteHelper helper = new FitnessDBSqliteHelper(requireContext());

        User user = helper.getUserById(SharedPreferenceManager.getLoggedUserID(requireContext()));

        ConstraintLayout constraint = view.findViewById(R.id.constraint_content_profile);
        TextView textName = view.findViewById(R.id.text_label_name_profile);
        TextView textWeight = view.findViewById(R.id.text_weight_profile);
        TextView textAge = view.findViewById(R.id.text_age_profile);
        TextView textGender = view.findViewById(R.id.text_gender_profile);
        TextView textHeight = view.findViewById(R.id.text_height_profile);
        TextView textBMI = view.findViewById(R.id.text_BMI_profile);
        TextView textWorkout = view.findViewById(R.id.text_workout_type_profile);
        Flow flowLimitations = view.findViewById(R.id.flow_physical_restrictions_profile);
        Flow flowEquipment = view.findViewById(R.id.flow_equipment_profile);

        textName.setText(user.getUserName());
        textWeight.setText(Integer.toString(user.getWeightN()));
        textAge.setText(Integer.toString(user.getAgeN()));
        textGender.setText(user.getGenderN().name());
        textHeight.setText(Integer.toString(user.getHeight()));
        textBMI.setText(Float.toString(user.getBMI()));
        textWorkout.setText(user.getWorkoutTypeN().name());

        for (LimitationEnum e : user.getLimitations()) {
            TextView v = new TextView(requireContext());
            v.setId(View.generateViewId());
            v.setPadding(5,5,5,5);
            v.setText(e.getLabel());
            constraint.addView(v);
            flowLimitations.addView(v);
        }
        for (EquipmentEnum e : user.getEquipments()) {
            TextView v = new TextView(requireContext());
            v.setText(e.getLabel());
            v.setId(View.generateViewId());
            v.setPadding(5,5,5,5);
            v.setText(e.getLabel());
            constraint.addView(v);
            flowEquipment.addView(v);
        }
    }
}