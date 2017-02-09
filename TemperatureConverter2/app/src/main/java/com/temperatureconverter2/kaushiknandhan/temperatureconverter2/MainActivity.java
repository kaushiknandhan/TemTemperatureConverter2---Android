package com.temperatureconverter2.kaushiknandhan.temperatureconverter2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;  //declare seekbar object
    TextView textView, textView2;
    //declare member variables for SeekBar
    int discrete=0;
    int start=50;
    int start_position=50; //progress tracker
    int temp=0;
    //declare objects for ViewStub
    ViewStub stub;
    CheckBox checkBox;
    //declare Listview object
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //declare viewstub object
        stub = (ViewStub) findViewById(R.id.viewStub1);
        @SuppressWarnings("unused")
        View inflated = stub.inflate();
        stub.setVisibility(View.INVISIBLE);

        //ViewStub logic

        checkBox=(CheckBox) findViewById(R.id.checkBox1);
        //handle checkbox click event
        checkBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener()
                                                    {
                                                        public void onCheckedChanged(CompoundButton arg0, boolean isChecked)
                                                        {
                                                            if ( isChecked )
                                                            {
                                                                //remove objects from parent view to allow for child view
                                                                checkBox.setVisibility(View.GONE);
                                                                seekBar.setVisibility(View.GONE);
                                                                textView.setVisibility(View.GONE);
                                                                textView2.setVisibility(View.GONE);

                                                                stub.setVisibility(View.VISIBLE);
                                                            }
                                                        }
                                                    });
        //seekbar logic

        textView = (TextView) findViewById(R.id.textview);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView.setText("     Celsius at 0 degrees"); //set default view
        seekBar=(SeekBar) findViewById(R.id.seekbar);
        seekBar.setProgress(start_position);

        //create event handler for SeekBar
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                if(temp==0)  //for initial view result
//                    Toast.makeText(getBaseContext(), "Fahrenheit result 32 degrees",
//                            Toast.LENGTH_SHORT).show();
                    textView2.setText("Fahrenheit result 32 degrees"); // prints the data to the textview

                else
//                    Toast.makeText(getBaseContext(), "Fahrenheit result "
//                                    +String.valueOf(discrete) + " degrees",
//                            Toast.LENGTH_SHORT).show();
                    textView2.setText("Fahrenheit result "+String.valueOf(discrete) + " degrees"); // prints the data to the textview
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean
                    fromUser) {

                // TODO Auto-generated method stub
                // To convert progress passed as discrete (Fahrenheit) value
                temp=progress-start;
                discrete=(int) Math.round((((temp * 9.0) / 5.0) + 32)); //convert C to F temp
                textView.setText("     Celsius at "+temp + " degrees");
            }
        });

        //Listview logic
        String[] wkTemps = new String[] { "Saturday 51"+(char) 0x00B0 +"F", "Sunday 45"+(char) 0x00B0 +"F", "Monday 45"+(char) 0x00B0 +"F", "Tuesday 43"+(char) 0x00B0 +"F", "Wednesday 39"+(char) 0x00B0 +"F"};

        lv=(ListView) findViewById(R.id.listView);
        @SuppressWarnings({ "unchecked", "rawtypes" })
   /*
   * To use a basic ArrayAdapter, you just need to initialize the adapter and
 * attach the adapter to the ListView. First, initialize the adapter...:
 *
 */
                ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, wkTemps);

        // Assign adapter to ListView
        lv.setAdapter(adapter);


    }//end onCreate method

    public void backButton(View view) {
        /**
         * Clicking the button makes the previous view elements visible and making the viewStub invisible.
         */
        checkBox.setChecked(false);
        checkBox.setVisibility(View.VISIBLE);
        seekBar.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.VISIBLE);
        stub.setVisibility(View.GONE);
    }
}
