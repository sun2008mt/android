package sun.wh.cn.androidpractice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import android.provider.CalendarContract;
import static java.net.Proxy.Type.HTTP;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "cn.wh.sun.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.logo);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button button_send = (Button) findViewById(R.id.button_send);
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DisplayMessageActivity.class);
                EditText editText = (EditText) findViewById(R.id.edit_message);
                String message = editText.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });

        Button fragment_go = (Button) findViewById(R.id.fragment_go);
        fragment_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ArticleActivity.class);
                startActivity(intent);
            }
        });

        //android五中存储方式
        //1.Shared Preferences
        // 2.Internal Storage
        // 3. External Storage
        // 4. SQLite Database
        // 5.Network Connection

        //SharedPreference 文件保存在/data/data/<package name>/shared_prefs 路径下
        // (如/data/data/com.android.alarmclock/shared_prefs/com.android.text_preferences.xml)
        SharedPreferences preferences = getSharedPreferences(getString(R.string.data), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(getString(R.string.saved_high_score), 30);

        //commit是同步方法，apply是异步方法
        edit.commit();

        //internal storage
        //Internal Storage 把数据存储在设备内部存储器上，存储在/data/data/<package name>/files目录下
        //Internal storage 是属于应用程序的，文件管理器看不见
        //External storage 在文件浏览器里是可以看见的  /mnt

        //implicit intent
        //dial
        Button intent_dial = (Button) findViewById(R.id.intent_dial);
        intent_dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri tel = Uri.parse("tel:111111111");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, tel);
                startActivity(callIntent);
            }
        });

        //location
        Button intent_location = (Button) findViewById(R.id.intent_location);
        intent_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
                // Or map point based on latitude/longitude
                // Uri location = Uri.parse("geo:37.422219,-122.08364?z=14"); // z param is zoom level
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                startActivity(mapIntent);
            }
        });

        //webpage
        Button intent_webpage = (Button) findViewById(R.id.intent_webpage);
        intent_webpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("http://www.baidu.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
            }
        });

        //email
        Button intent_email = (Button) findViewById(R.id.intent_email);
        intent_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                // The intent does not have a URI, so declare the "text/plain" MIME type
                emailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"jon@example.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "email subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "email message text");
//        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));

            }
        });

        //calendar
        Button intent_calendar = (Button) findViewById(R.id.intent_calendar);
        intent_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(2012, 0, 19, 7, 30);
                Calendar endTime = Calendar.getInstance();
                endTime.set(2012, 0, 19, 10, 30);
                calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
                calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
                calendarIntent.putExtra(CalendarContract.Events.TITLE, "Ninja class");
                calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Secret dojo");

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_settings:
                openSettings();
                return true;

            case R.id.action_search:
                openSearch();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openSettings() {
        Toast.makeText(this, "opening settings", Toast.LENGTH_LONG).show();
    }

    private void openSearch() {
        Toast.makeText(this, "opening search", Toast.LENGTH_LONG).show();
    }
}
