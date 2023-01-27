package com.example.loginregister;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

    FloatingActionButton btn_add, btn_mic, btn_image, btn_camera, btn_contact;
    Animation from_bottom_anim, to_bottom_anim, rotate_open_anim, rotate_close_anim;
    boolean isOpen = false;
    boolean isRecording = false;
    String name, number;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        btn_add = (FloatingActionButton) view.findViewById(R.id.btn_add);
        btn_mic = (FloatingActionButton) view.findViewById(R.id.btn_mic);
        btn_image = (FloatingActionButton) view.findViewById(R.id.btn_image);
        btn_camera = (FloatingActionButton) view.findViewById(R.id.btn_camera);
        btn_contact = (FloatingActionButton) view.findViewById(R.id.btn_contact);

        rotate_open_anim = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_open_anim);
        rotate_close_anim = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_close_anim);

        to_bottom_anim = AnimationUtils.loadAnimation(getContext(),R.anim.to_bottom_anim);
        from_bottom_anim= AnimationUtils.loadAnimation(getContext(),R.anim.from_bottom_anim);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();
            }
        });

        btn_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), " Record button coming soon",Toast.LENGTH_SHORT).show();
               /* if(isRecording)
                {
                    stopRecording();
                    isRecording = false;
                    Toast.makeText(getActivity(), " Start..",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    startRecording();
                    isRecording = true;
                    Toast.makeText(getActivity(), " Stop.",Toast.LENGTH_SHORT).show();
                }

                */
            }
        });
        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //animateFab();
                Toast.makeText(getActivity(), " Gallery button coming soon",Toast.LENGTH_SHORT).show();
            }
        });
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent capture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(capture, 101);
                 */
                Toast.makeText(getActivity(), " Camera button coming soon",Toast.LENGTH_SHORT).show();
            }
        });
        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GetContactsIntoArrayList();
                Toast.makeText(getActivity(), " Contact button coming soon",Toast.LENGTH_SHORT).show();
            }

        });

        return view;
    }
/*
    @SuppressLint("Range")
    public void GetContactsIntoArrayList(){

        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);

        while (cursor.moveToNext()) {

            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            StoreContacts.add(name + " "  + ":" + " " + number);
        }

        cursor.close();
    }

 */

    private void animateFab(){
        if (isOpen){

            btn_add.startAnimation(rotate_close_anim);
            btn_mic.startAnimation(to_bottom_anim);
            btn_image.startAnimation(to_bottom_anim);
            btn_camera.startAnimation(to_bottom_anim);
            btn_contact.startAnimation(to_bottom_anim);
            btn_mic.setClickable(false);
            btn_image.setClickable(false);
            btn_camera.setClickable(false);
            btn_contact.setClickable(false);
            isOpen = false;
        }
        else{

            btn_add.startAnimation(rotate_open_anim);
            btn_mic.startAnimation(from_bottom_anim);
            btn_image.startAnimation(from_bottom_anim);
            btn_camera.startAnimation(from_bottom_anim);
            btn_contact.startAnimation(from_bottom_anim);
            btn_mic.setClickable(true);
            btn_image.setClickable(true);
            btn_camera.setClickable(true);
            btn_contact.setClickable(true);
            isOpen = true;
        }
    }

}
