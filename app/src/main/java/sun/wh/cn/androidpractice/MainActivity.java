package sun.wh.cn.androidpractice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
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

        //SharedPreference 文件保存在/data/data/<package name>/shared_prefs 路径下
        // (如/data/data/com.android.alarmclock/shared_prefs/com.android.text_preferences.xml)
        SharedPreferences preferences = getSharedPreferences(getString(R.string.data), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(getString(R.string.saved_high_score), 30);

        //commit是同步方法，apply是异步方法
        edit.commit();

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
