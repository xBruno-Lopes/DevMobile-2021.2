package com.brunolopes.trabalhocomponentesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    static final String[] FRUITS = new String[]{"Maçã", "Morango", "Banana", "Uva"};

    private Spinner spinner;
    private ToggleButton toggleButton1, toggleButton2;
    private Button btnDisplay, buttonPopup, buttonPopup2;
    private EditText edittext;
    private RadioGroup radioGroup;
    Button buttonLongPress;
    Button segundaActivity;
    Button toGoActivity3;
    MediaPlayer mysound;
    Button musicButtonStart;
    Button musicButtonPause;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mysound = MediaPlayer.create(this, R.raw.forestmeadow);
        // mysound = MediaPlayer.create(this, getResources().getIdentifier("forestmeadow","raw", getPackageName()));


        addListenerOnButton(); //Item #1
        addKeyListener(); //Item #2
        list_fruits(); //Item #3, #14 & #15
        auto_complete_list(); //Item #4
        spinner = (Spinner) findViewById(R.id.spinner); //Item #5
        radioButton(); //#6
        popup_menu(); //Item #8
        popup_menu2(); //item #9
        longPress(); //Item #10
        //Item #11 ver o Id imgGalaxy
        segundaActivity();//Item #12
        paraTerceiraActivity();
        musicaStart(); //Item #17
        musicaPause();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mysound.stop();
        mysound.prepareAsync();
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    } //Item #7

    public void addListenerOnButton() {

        toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
        toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(v -> {

            StringBuffer result = new StringBuffer();
            result.append("toggleButton1 : ").append(toggleButton1.getText());
            result.append("\ntoggleButton2 : ").append(toggleButton2.getText());

            Toast.makeText(MainActivity.this, result.toString(),
                    Toast.LENGTH_SHORT).show();

        });

    }

    public void addKeyListener() {
        edittext = (EditText) findViewById(R.id.editText);
        edittext.setOnKeyListener((v, keyCode, event) -> {

            if ((event.getAction() == KeyEvent.ACTION_DOWN)
                    && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                Toast.makeText(MainActivity.this,
                        edittext.getText(), Toast.LENGTH_LONG).show();
                return true;
            } else if ((event.getAction() == KeyEvent.ACTION_DOWN)
                    && (keyCode == KeyEvent.KEYCODE_9)) {
                Toast.makeText(MainActivity.this,
                        "Number 9 is pressed!", Toast.LENGTH_LONG).show();
                return true;
            }

            return false;
        });
    }

    public View list_fruits() {

        List<String> data_list = new ArrayList<>(Arrays.asList(FRUITS));
        ArrayAdapter<String> data_adapter = new ArrayAdapter<>(this, R.layout.list_fruit, data_list);
        ListView data_view = (ListView) this.findViewById(R.id.list_view);
        data_view.setAdapter(data_adapter);


        data_view.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(getApplicationContext(),
                    ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
        });
        return data_view;
    }

    public void auto_complete_list() {
        List<String> data_list = new ArrayList<>(Arrays.asList(FRUITS));
        ArrayAdapter<String> data_adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, data_list);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autoComplete_view);
        textView.setAdapter(data_adapter);
    }

    public void radioButton() {
        radioGroup = (RadioGroup) findViewById(R.id.radioSex);
    }

    public void popup_menu() {
        buttonPopup = (Button) findViewById(R.id.popup);
        buttonPopup.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(MainActivity.this, buttonPopup);
            popup.getMenuInflater()
                    .inflate(R.menu.popup_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    Toast.makeText(
                            MainActivity.this,
                            "Você Clicou : " + item.getTitle(),
                            Toast.LENGTH_SHORT
                    ).show();
                    return true;
                }
            });

            popup.show(); //showing popup menu
        });
    }

    public void popup_menu2() {
        buttonPopup2 = (Button) findViewById(R.id.popup2);
        buttonPopup2.setOnClickListener(v -> {
            PopupMenu popup2 = new PopupMenu(MainActivity.this, buttonPopup2);
            popup2.getMenuInflater()
                    .inflate(R.menu.popup_menu, popup2.getMenu());
            popup2.setOnMenuItemClickListener(item -> {
                Toast.makeText(
                        MainActivity.this,
                        "Você Clicou : " + item.getTitle(),
                        Toast.LENGTH_SHORT
                ).show();
                return true;
            });

            popup2.show(); //showing popup menu
        });
    }

    public void longPress() {
        buttonLongPress = (Button) findViewById(R.id.buttonLongPress);
        buttonLongPress.setOnLongClickListener(v -> {
            // TODO Auto-generated method stub
            Toast.makeText(MainActivity.this,
                    "Você pressionou por muito tempo :)", Toast.LENGTH_LONG).show();
            return true;
        });
        buttonLongPress.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            Toast.makeText(MainActivity.this, "Não foi longo suficiente :(",
                    Toast.LENGTH_LONG).show();
        });
    }

    public void segundaActivity() {
        segundaActivity = (Button) findViewById(R.id.goToActivity2);
        segundaActivity.setOnClickListener(v -> {
            Intent in = new Intent(MainActivity.this, SegundaActivity.class);
            startActivity(in);
        });
    }

    public void paraTerceiraActivity() {
        toGoActivity3 = findViewById(R.id.toGo3Activity);
        toGoActivity3.setOnClickListener(v -> {
            Intent in = new Intent(MainActivity.this, TerceiraActivity.class);
            startActivity(in);
        });
    }

    public void musicaStart() {
        musicButtonStart = (Button) findViewById(R.id.buttonMusicStart);

        try {
            mysound.prepare();
        } catch (Exception e) {
        }

        musicButtonStart.setOnClickListener(v -> {
            mysound.start();
        });
    }

    public void musicaPause() {
        musicButtonPause = (Button) findViewById(R.id.buttonMusicPause);
        musicButtonPause.setOnClickListener(v -> {
            mysound.stop();
            mysound.prepareAsync();
        });
    }

}