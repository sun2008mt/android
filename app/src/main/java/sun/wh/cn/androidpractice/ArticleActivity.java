package sun.wh.cn.androidpractice;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by marc on 17-5-2.
 */

public class ArticleActivity extends AppCompatActivity implements HeadlinesFragment.OnHeadlineSelectedListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_articles);

        // 确认 Activity 使用的布局版本包含 headlines_fragment FrameLayout
        if (findViewById(R.id.new_fragment_container) != null) {

            // 不过，如果我们要从先前的状态还原，则无需执行任何操作而应返回，否则
            // 就会得到重叠的 Fragment。
            if (savedInstanceState != null) {
                return;
            }

            // 创建一个要放入 Activity 布局中的新 Fragment
            HeadlinesFragment newFragment = new HeadlinesFragment();

            // 如果此 Activity 是通过 Intent 发出的特殊指令来启动的，
            // 请将该 Intent 的 extras 以参数形式传递给该 Fragment
            newFragment.setArguments(getIntent().getExtras());

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // 将该 Fragment 添加到“fragment_container” FrameLayout 中
            transaction.add(R.id.new_fragment_container, newFragment);

//            // 将 fragment_container View 中的内容替换为此 Fragment，
//            // 然后将该事务添加到返回堆栈，以便用户可以向后导航
//            transaction.replace(R.id.new_fragment_container, newFragment);
//            transaction.addToBackStack(null);

            // 执行事务
            transaction.commit();
        }

        SharedPreferences preferences = getSharedPreferences(getString(R.string.data), Context.MODE_PRIVATE);
        int defaultValue = Integer.parseInt(getResources().getString(R.string.saved_high_score_default));
        int highscore = preferences.getInt(getString(R.string.saved_high_score), defaultValue);
        Toast.makeText(this, "high score: " + highscore, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onArticleSelected(int position) {
        Toast.makeText(this, "position: " + position, Toast.LENGTH_LONG).show();
    }
}
