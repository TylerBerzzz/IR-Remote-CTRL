// Developed by Tyler Bershad
// Last Modified: 3/5/2017
// Description: This is the main activity for the IR Remote app on the Samsung Galexy S4. It contains the code for the buttons and the code for the IR transmitter.

package com.blaqtech.ir_remote_blaqtech1;

        import android.content.Context;
        import android.hardware.ConsumerIrManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    ConsumerIrManager mCIR;
    private static final int Rem_Freq = 38000; //Carrier Frequency of Remote in use
   
    //Function PWM's. These are the codes for the buttons on the remote:

    //Up
    private static final int[] Up = {9300,4400, 650,500, 650,500, 600,500, 650,500, 700,450, 650,450, 700,450, 700,450, 700,1550, 700,1600, 650,1600, 700,1600, 650,500, 600,1650, 650,1600, 700,1600, 650,450, 700,450, 700,450, 650,500, 650,450, 700,450, 700,450, 700,450, 650,1600, 700,1600, 600,1650, 650,1600, 650,1650, 650,1600, 650,1600, 700,1600, 650};
    //Down
    private static final int[] Down = {9150,4450, 650,500, 700,450, 650,450, 650,500, 650,500, 600,550, 600,500, 650,500, 600,1650, 700,1550, 700,1600, 650,1600, 700,450, 650,1600, 650,1600, 700,1600, 650,1600, 700,450, 650,450, 650,500, 650,500, 600,550, 600,500, 650,500, 600,550, 600,1650, 600,1650, 650,1650, 600,1650, 600,1650, 650,1600, 650,1650, 600};
    //OFF
    private static final int[] Off = {9200,4450, 650,450, 700,450, 650,500, 650,450, 700,450, 650,500, 650,450, 700,450, 650,1600, 650,1600, 700,1600, 650,1600, 650,500, 600,1650, 650,1600, 650,1600, 700,450, 700,1550, 700,450, 650,500, 650,450, 700,450, 650,500, 650,450, 650,1600, 700,450, 650,1600, 700,1550, 700,1600, 650,1600, 650,1600, 700,1600, 650};
    //ON
    private static final int[] ON = {9250,4400, 650,500, 650,500, 600,500, 650,500, 650,500, 650,450, 650,500, 700,450, 650,1600, 650,1600, 700,1600, 600,1650, 650,500, 650,1600, 650,1600, 700,1600, 600,1650, 650,1600, 700,450, 650,500, 650,450, 700,450, 650,500, 600,500, 700,450, 650,500, 650,1600, 700,1550, 650,1650, 650,1600, 650,1600, 700,1600, 600};
    //(1,4)
    private static final int[] Flash = {9300,4450, 700,450, 650,500, 600,500, 650,500, 650,500, 600,550, 600,550, 600,550, 600,1650, 600,1650, 650,1650, 600,1650, 650,500, 600,1650, 650,1600, 650,1650, 600,1650, 650,1600, 650,500, 650,1650, 600,500, 650,500, 650,500, 650,500, 650,500, 600,500, 650,1650, 600,500, 650,1650, 600,1650, 650,1650, 600,1650, 600};
    //(2,4)
    private static final int[] Strobe = {9200,4450, 600,550, 600,500, 650,500, 650,500, 600,500, 650,500, 650,500, 600,500, 650,1650, 650,1600, 650,1600, 700,1600, 600,500, 650,1600, 700,1550, 700,1600, 650,1600, 650,1600, 650,1600, 650,1650, 600,500, 700,450, 650,500, 650,450, 700,450, 700,450, 650,450, 700,450, 650,1600, 700,1600, 650,1600, 700,1550, 700};
    //(3,4)
    private static final int[] Fade = {9150,4450, 650,500, 650,450, 700,450, 700,450, 650,450, 700,450, 700,450, 650,500, 650,1600, 650,1600, 650,1600, 700,1550, 700,450, 650,1600, 700,1600, 650,1600, 700,1550, 700,1550, 700,450, 700,450, 650,1600, 650,500, 650,450, 700,450, 650,500, 650,450, 700,1600, 650,1600, 650,450, 700,1600, 650,1600, 650,1600, 650};
    //(4,4)
    private static final int[] Smooth = {9150,4500, 550,600, 600,500, 600,600, 500,600, 550,550, 600,600, 500,600, 550,550, 600,1700, 550,1700, 550,1700, 600,1650, 600,550, 550,1750, 500,1750, 550,1700, 550,1700, 600,1650, 600,1650, 600,600, 550,1700, 550,550, 550,550, 600,550, 600,600, 500,600, 550,600, 550,1700, 550,600, 500,1750, 500,1750, 550,1700, 550};

    //Color PWM's
        //Red
    private static final int[] Red = {9250,4450, 650,500, 650,500, 600,550, 600,550, 600,550, 600,500, 650,500, 650,500, 600,1650, 650,1600, 650,1650, 650,1600, 650,550, 600,1650, 650,1600, 650,1650, 600,500, 650,500, 650,1650, 600,500, 650,550, 600,500, 650,500, 650,500, 600,1650, 650,1650, 600,550, 600,1650, 650,1650, 600,1650, 650,1600, 650,1650, 600};
        //Green
    private static final int[] Green = {9250,4500, 600,500, 650,500, 600,550, 600,500, 650,500, 600,550, 650,500, 600,550, 600,1650, 650,1650, 550,1700, 600,1700, 600,500, 600,1700, 650,1600, 650,1650, 600,1650, 600,600, 550,1700, 600,550, 550,600, 550,550, 600,550, 550,600, 550,600, 550,1700, 600,550, 600,1650, 600,1700, 550,1750, 600,1650, 550,1750, 550};
        //Blue
    private static final int[] Blue = {9250,4450, 650,500, 650,500, 600,550, 600,500, 650,500, 650,500, 650,500, 650,500, 600,1650, 650,1650, 650,1600, 650,1600, 700,450, 650,1650, 650,1600, 700,1600, 650,450, 750,1550, 650,1600, 700,450, 700,450, 650,500, 650,500, 600,500, 700,1600, 650,450, 700,450, 700,1600, 650,1600, 700,1600, 650,1600, 650,1650, 600};
        //White
    private static final int[] White = {9200,4500, 600,550, 600,550, 600,500, 600,550, 600,550, 600,500, 600,550, 600,550, 600,1700, 600,1650, 600,1650, 600,1700, 600,500, 650,1650, 600,1650, 600,1650, 600,1700, 600,1650, 600,1700, 600,500, 650,550, 600,500, 600,550, 550,600, 600,500, 600,550, 600,550, 600,1650, 650,1650, 600,1650, 600,1700, 600,1650, 600};
        //(1,1)
    private static final int[] Orange = {9300,4400, 700,450, 700,450, 650,500, 650,500, 650,450, 700,450, 700,450, 650,500, 650,1600, 700,1550, 700,1600, 650,1600, 700,450, 650,1600, 650,1600, 700,1600, 650,450, 700,450, 700,450, 700,1600, 650,500, 650,450, 700,450, 700,450, 700,1600, 650,1600, 650,1650, 650,450, 700,1600, 650,1600, 700,1550, 700,1600, 650};
        //(1,2)
    private static final int[] Lgreen = {9250,4450, 650,500, 650,500, 600,550, 600,550, 600,500, 650,500, 650,500, 650,500, 600,1650, 650,1600, 650,1650, 600,1650, 650,500, 600,1650, 650,1600, 650,1650, 600,1650, 650,500, 600,500, 650,1650, 600,500, 650,500, 650,500, 600,550, 600,500, 650,1650, 600,1650, 650,500, 600,1650, 650,1650, 600,1650, 600,1650, 650};
        //(1,3)
    private static final int[] Mblue = {9200,4450, 650,500, 700,450, 650,450, 700,450, 650,500, 650,450, 700,450, 700,450, 650,1600, 650,1600, 700,1550, 650,1600, 700,450, 650,1600, 650,1600, 700,1600, 650,450, 700,1600, 650,450, 650,1650, 650,450, 700,450, 650,500, 600,500, 650,1650, 650,450, 700,1550, 650,500, 650,1600, 700,1550, 700,1550, 700,1600, 650};
        //(2,1)
    private static final int[] MRed = {9250,4450, 650,500, 600,550, 600,500, 650,500, 600,550, 600,500, 650,500, 650,500, 600,1650, 650,1600, 650,1600, 650,1600, 650,500, 650,1600, 650,1600, 650,1650, 600,500, 650,500, 650,1600, 650,1650, 600,500, 650,500, 650,500, 600,500, 650,1650, 600,1650, 600,500, 650,500, 600,1650, 650,1600, 650,1600, 650,1650, 600};
        //(2,2)
    private static final int[] skyBlue = {9250,4450, 700,450, 650,500, 650,450, 700,450, 700,450, 650,450, 700,450, 700,450, 700,1550, 700,1550, 700,1600, 650,1600, 700,450, 650,1600, 700,1550, 700,1600, 650,1600, 700,450, 600,1650, 650,1600, 700,450, 650,500, 600,500, 650,500, 700,450, 650,1600, 700,450, 650,450, 700,1600, 650,1600, 650,1600, 700,1600, 650};
        //(2,3)
    private static final int[] Purple = {9200,4400, 600,550, 600,500, 650,500, 650,500, 600,550, 600,500, 650,500, 650,500, 600,1650, 600,1600, 650,1650, 650,1550, 650,500, 600,1650, 650,1600, 600,1650, 600,500, 650,1600, 650,1600, 650,1600, 650,500, 650,500, 600,500, 650,500, 650,1600, 650,500, 700,450, 650,500, 600,1650, 600,1650, 700,1550, 700,1600, 600};
        //(3,1)
    private static final int[] LOrange = {9300,4350, 650,500, 650,450, 700,450, 700,450, 650,500, 650,450, 700,450, 650,500, 700,1550, 650,1600, 700,1600, 650,1600, 650,450, 700,1600, 650,1600, 650,1600, 650,500, 650,500, 600,500, 650,500, 650,1600, 700,450, 650,500, 600,500, 700,1550, 700,1550, 700,1550, 700,1600, 650,450, 650,1650, 650,1600, 700,1550, 700};
        //(3,2)
    private static final int[] MMblue = {9200,4450, 650,500, 600,500, 650,500, 650,500, 600,500, 650,500, 650,500, 600,500, 650,1650, 600,1650, 650,1600, 650,1600, 650,500, 600,1650, 600,1650, 650,1600, 650,1650, 600,500, 650,500, 650,500, 600,1650, 650,500, 600,500, 650,500, 650,500, 600,1650, 650,1600, 650,1650, 600,500, 650,1600, 650,1650, 600,1650, 600};
        //(3,3)
    private static final int[] Mpurple = {9200,4400, 700,450, 650,500, 650,450, 650,500, 650,500, 650,450, 650,500, 650,450, 700,1550, 700,1600, 650,1600, 650,1600, 650,500, 650,1600, 650,1600, 650,1600, 700,450, 650,1600, 700,450, 650,450, 650,1650, 650,450, 700,450, 700,450, 650,1600, 650,450, 700,1550, 700,1600, 650,450, 700,1550, 700,1600, 600,1650, 650};
        //(4,1)
    private static final int[] Yellow = {9150,4500, 600,500, 650,500, 650,500, 600,500, 650,500, 650,500, 600,500, 650,500, 600,1650, 650,1600, 650,1600, 650,1650, 600,500, 650,1600, 650,1650, 600,1650, 600,550, 600,500, 650,1650, 600,500, 650,1600, 650,500, 600,550, 600,500, 650,1600, 650,1650, 600,500, 650,1600, 650,500, 650,1600, 650,1650, 600,1650, 600};
        //(4,2)
    private static final int[] SeaBlue = {9150,4500, 600,500, 650,500, 600,550, 600,500, 600,550, 600,550, 600,500, 600,550, 600,1650, 600,1650, 600,1650, 650,1600, 650,500, 650,1600, 650,1650, 600,1650, 600,1650, 600,550, 600,1650, 600,500, 650,1600, 650,500, 650,500, 600,500, 650,500, 650,1600, 650,500, 650,1600, 650,500, 600,1650, 600,1650, 600,1650, 650};
        //(4,3)
    private static final int[] Lpurple = {9300,4350, 600,550, 600,550, 600,550, 550,600, 550,550, 600,550, 600,500, 600,550, 600,1650, 600,1650, 600,1650, 600,1650, 600,550, 650,1650, 600,1650, 600,1650, 600,500, 650,1600, 650,1600, 650,500, 650,1600, 600,550, 650,500, 600,500, 650,1650, 600,500, 650,500, 600,1650, 600,550, 600,1650, 600,1650, 600,1650, 650};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCIR = (ConsumerIrManager) this.getSystemService(Context.CONSUMER_IR_SERVICE);
        setContentView(R.layout.activity_main);
    }

    public void onButtonTap_Red(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, Red);
        Toast myToast = Toast.makeText(getApplicationContext(), "Red IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_Green(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, Green);
        Toast myToast = Toast.makeText(getApplicationContext(), "Green IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_Blue(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, Blue);
        Toast myToast = Toast.makeText(getApplicationContext(), "Blue IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_White(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, White);
        Toast myToast = Toast.makeText(getApplicationContext(), "White IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_Orange(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, Orange);
        Toast myToast = Toast.makeText(getApplicationContext(), "Orange IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_Lgreen(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, Lgreen);
        Toast myToast = Toast.makeText(getApplicationContext(), "Light Green IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_Mblue(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, Mblue);
        Toast myToast = Toast.makeText(getApplicationContext(), "Mid Blue IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_MRed(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, MRed);
        Toast myToast = Toast.makeText(getApplicationContext(), "Mid Red IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_skyBlue(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, skyBlue);
        Toast myToast = Toast.makeText(getApplicationContext(), "Sky Blue IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_Purple(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, Purple);
        Toast myToast = Toast.makeText(getApplicationContext(), "Purple IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_LOrange(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, LOrange);
        Toast myToast = Toast.makeText(getApplicationContext(), "Light Orange IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_MMblue(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, MMblue);
        Toast myToast = Toast.makeText(getApplicationContext(), "Mid+ Blue IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_Mpurple(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, Mpurple);
        Toast myToast = Toast.makeText(getApplicationContext(), "Mid Purple IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_Yellow(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, Yellow);
        Toast myToast = Toast.makeText(getApplicationContext(), "Yellow IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_SeaBlue(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, SeaBlue);
        Toast myToast = Toast.makeText(getApplicationContext(), "Sea Blue IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_Lpurple(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, Lpurple);
        Toast myToast = Toast.makeText(getApplicationContext(), "Light Purple IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_Up(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, Up);
        Toast myToast = Toast.makeText(getApplicationContext(), "+ IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_Down(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, Down);
        Toast myToast = Toast.makeText(getApplicationContext(), "- IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_Off(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, Off);
        Toast myToast = Toast.makeText(getApplicationContext(), "Off IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_On(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, ON);
        Toast myToast = Toast.makeText(getApplicationContext(), "On IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_Flash(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, Flash);
        Toast myToast = Toast.makeText(getApplicationContext(), "Flash IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_Strobe(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, Strobe);
        Toast myToast = Toast.makeText(getApplicationContext(), "Strobe IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_Fade(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, Fade);
        Toast myToast = Toast.makeText(getApplicationContext(), "Fade IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
    public void onButtonTap_Smooth(View v) {
        if (!mCIR.hasIrEmitter()) {
            Toast myToast = Toast.makeText(getApplicationContext(), "No IR Emitter Found!", Toast.LENGTH_LONG);
            myToast.show();
            return;
        }
        mCIR.transmit(Rem_Freq, Smooth);
        Toast myToast = Toast.makeText(getApplicationContext(), "Smooth IR Signal Sent!", Toast.LENGTH_LONG);
        myToast.show();
    }
}

