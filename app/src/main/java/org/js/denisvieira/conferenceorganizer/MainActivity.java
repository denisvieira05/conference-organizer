package org.js.denisvieira.conferenceorganizer;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final int FILE_SELECT_CODE = 0;
    private Fragment fragment;
//    @BindView(R.id.organizer_button)
//    Button organizerButton;

    TextView tvFilename;
    Toolbar toolbar;
    Button helpButton;
    ImageView ivIconFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ivIconFile = (ImageView) findViewById(R.id.iv_IconFile);
        tvFilename = (TextView) findViewById(R.id.filename_tv);
        setSupportActionBar(toolbar);

//        fragment = new SelectionFileFragment();
//
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.content_main, fragment).commit();


        Drawable myFabSrc = getResources().getDrawable(android.R.drawable.ic_dialog_info);
        Drawable willBeWhite = myFabSrc.getConstantState().newDrawable();
        willBeWhite.mutate().setColorFilter(getResources().getColor(R.color.colorRedDark), PorterDuff.Mode.MULTIPLY);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageDrawable(willBeWhite);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Denis Vieira. All Rights Reserved \n" +
                        "https://github .com/fromdenisvieira", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        helpButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Insira um arquivo no formato txt.",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

        ivIconFile.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                showFileChooser();
            }
        });
    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("text/plain");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select txt file"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    String path = uri.getPath();

                    tvFilename.setText(uri.getLastPathSegment());

                    try {
                        AssetManager assetManager = getResources().getAssets();
                        InputStream inputStream = assetManager.open("proposals.txt");
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String linha;

                        ArrayList<String> lectures = new ArrayList<String>();

                        while((linha = bufferedReader.readLine())!=null){
                            lectures.add(linha);
                        }

                        inputStream.close();
                        organizeConference(lectures);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void organizeConference(ArrayList<String> lectures){
        Intent intent = new Intent(this,SchedulesActivity.class);
        Bundle b = new Bundle();
        b.putStringArrayList("lectures", lectures);
        intent.putExtras(b);
        startActivity(intent);
    }

}
