package devsnewapps.mobilecommunitytracker;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {
    Preferences p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p = new Preferences(getApplicationContext());
        if (p.get(p.PREFS_CONFIG, p.KEY_HAS_INIT_CONFIG).equals("")){
            startInitialConfig();
        }


        //start service
        Intent monitorIntent = new Intent(getApplication(), BatteryService.class);
        startService(monitorIntent);
    }

    private void startInitialConfig() {
        //logic to init config
        p.set(p.PREFS_SMS, p.KEY_SMS_BODY, "The location of number device %s is: %s, %s");
        p.set(p.PREFS_SMS, p.KEY_SMS_NO_ACCESS_GPS_NOW_BODY, "The last location of number device %s is: %s, %s");
        p.set(p.PREFS_NUMBER_HELPER, p.KEY_SMS_DANGER_BODY, "Check if one of the numbers: %s have changed in your device. Make sure your friend lost cell.");
        p.set(p.PREFS_ALL_NUMBERS, p.KEY_NUMBER_SIM1, Telephony.getSimNumber(getApplicationContext()));
        p.set(p.PREFS_CONFIG, p.KEY_HAS_INIT_CONFIG, "yes");
    }





}
