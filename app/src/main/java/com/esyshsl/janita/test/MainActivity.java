package com.esyshsl.janita.test;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    ImageButton imgb;
    ImageView imgl;
    Boolean bool = false;
    TextView Licht;
    EditText onoroff;

    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Licht = (TextView) findViewById(R.id.Licht);
        onoroff = (EditText) findViewById(R.id.onOrOff);

        layout = (RelativeLayout) findViewById(R.id.content_layout);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getBaseContext(), AddActivity.class);
                startActivity(Intent);
            }
        });

        imgl = (ImageView) findViewById(R.id.imgl);


        final TextView mTextView = (TextView) findViewById(R.id.text);

// Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(this);

        imgb = (ImageButton) findViewById(R.id.imgb);
        imgb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                imgb.setRotation(imgb.getRotation() + 180);
                bool = !bool;
                if (bool){
                    //layout.setBackgroundColor(Color.GREEN);
                   // imgl.setImageResource(R.drawable.an);

                    String url ="http://"+onoroff.getText()+"/ON";

// Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the first 500 characters of the response string.
                                    mTextView.setText(response);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            mTextView.setText("That didn't work!");
                        }
                    });
// Add the request to the RequestQueue.
                    queue.add(stringRequest);




                    Licht.setText("Licht ist an.");

                }else {

                    String url ="http://"+onoroff.getText()+"/OFF";

// Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the first 500 characters of the response string.
                                    mTextView.setText(response);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            mTextView.setText("That didn't work!");
                        }
                    });
// Add the request to the RequestQueue.
                    queue.add(stringRequest);




                    Licht.setText("Licht ist aus.");
                    //layout.setBackgroundColor(Color.RED);
                   // imgl.setImageResource(R.drawable.aus);
                }


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
