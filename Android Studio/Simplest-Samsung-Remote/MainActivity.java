package com.blaqtech.simplest_remote;

import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    ConsumerIrManager mCIR;
    private static final int Rem_Freq = 38000; //Carrier Frequency of SAMSUNG
     private static final int[] Rem_ON = {4600,4350,700,1550,650,1550,650,1600,650,450,650,450,650,450,650,450,700,400,700,1550,650,1550,650,1600,650,450,650,450,650,450,700,450,650,450,650,450,650,1550,700,450,650,450,650,450,650,450,650,450,700,400,650,1600,650,450,650,1550,650,1600,650,1550,650,1550,700,1550,650,1550,650};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCIR = (ConsumerIrManager) this.getSystemService(Context.CONSUMER_IR_SERVICE);
        setContentView(R.layout.activity_main);
    }

        public void onButtonTap(View v) {
            if (!mCIR.hasIrEmitter()) {
                Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
                myToast.show();
                return;
            }
            mCIR.transmit(Rem_Freq, Rem_ON);
            Toast myToast = Toast.makeText(getApplicationContext(), "IR Signal Sent!", Toast.LENGTH_LONG);
            myToast.show();
        }
}

