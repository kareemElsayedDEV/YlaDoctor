/*
 * Copyright (c) 2017. KAREEM ElSAYED ALY  ALL RIGHTS RESERVED.
 */

package com.kareem.yladoctor.Views.Fragments.MainScreensFragments.PatientMainScreenFramgents;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kareem.yladoctor.MainApplication;
import com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals.Patient;
import com.kareem.yladoctor.R;
import com.kareem.yladoctor.ViewModels.Engine.LogInHandler.GeneralLoginHandler;
import com.kareem.yladoctor.Views.Activites.general.EntryContainer;
import com.kareem.yladoctor.Views.Activites.general.MainPage;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kareem on 5/26/2017 - HealMe.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class PatientMainScreenTwo extends Fragment implements View.OnClickListener {
	private final String Home = "Home";
	private final String Settings = "Settings";
	private final String SignOut = "SignOut";
//	private View view;

	@OnClick(R.id.patientMainViewTwo_ImageButton_settings)
	public void onSettingsClicked () {
		resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
	}

	@BindView(R.id.patientMainViewTwo_textView_name)
	TextView name;
	@BindView(R.id.patientMainViewTwo_imageView_personalImage)
	ImageView profilePicture;

	@OnClick(R.id.patientMainViewTwo_linearLayout_mentalIllness)
	public void onMentalIllnessClicked () {

	}

	@OnClick(R.id.patientMainViewTwo_linearLayout_physicalIllness)
	public void onPhysicalIllnessClicked () {

	}

	@OnClick(R.id.patientMainViewTwo_linearLayout_regularIllness)
	public void onRegularIllnessClicked () {

	}

	@OnClick(R.id.patientMainViewTwo_linearLayout_surgeries)
	public void onSurgeriesClicked () {

	}

	ResideMenu resideMenu;

	@Nullable
	@Override
	public View onCreateView ( LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState ) {
		return inflater.inflate(R.layout.patient_mainview_two, container, false);
	}

	@Override
	public void onViewCreated ( View view, @Nullable Bundle savedInstanceState ) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);
//		this.view = view;
		Patient patient = (Patient) ((MainApplication) this.getActivity().getApplication()).getUser();
		name.setText(patient.getName() == null || patient.getName().trim().isEmpty() ? "Hi There :)" : patient.getName());
		if (patient.getProfilePicture() != null)
			Glide.with(this.getActivity()).load(patient.getProfilePicture().getURL()).into(profilePicture);

		resideMenu = new ResideMenu(this.getActivity());
		resideMenu.setMinimumHeight(view.getHeight() + 100);
		resideMenu.setBackground(R.mipmap.logo);
		resideMenu.attachToActivity(this.getActivity());

		// create menu items;
		String titles[] = { Home, Settings, SignOut };
		int icon[] = {
				  R.mipmap.home
				  , R.mipmap.settings
				  , R.mipmap.exit
		};

		for (int i = 0; i < titles.length; i++) {
			ResideMenuItem item = new ResideMenuItem(this.getActivity(), icon[i], titles[i]);
			item.setTag(titles[i]);
			item.setOnClickListener(this);
			resideMenu.addMenuItem(item, ResideMenu.DIRECTION_LEFT); // or  ResideMenu.DIRECTION_RIGHT

		}

		resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
		resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_LEFT);
	}

	@Override
	public void onClick ( View v ) {
		ResideMenuItem resideMenuItem = (ResideMenuItem) v;
		switch (resideMenuItem.getTag().toString()) {
			case Home:
				((MainPage) PatientMainScreenTwo.this.getActivity()).convertViewToHomeFramgents();
				break;
			case Settings:
				break;
			case SignOut:
				GeneralLoginHandler.getUserSignOut();
				startActivity(new Intent(PatientMainScreenTwo.this.getActivity(), EntryContainer.class));
				PatientMainScreenTwo.this.getActivity().finish();
				break;
		}
	}
}
