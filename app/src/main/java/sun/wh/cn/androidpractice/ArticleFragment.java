package sun.wh.cn.androidpractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by marc on 17-5-2.
 */

public class ArticleFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 拉伸该 Fragment 的布局
        return inflater.inflate(R.layout.fragment_articles, container, false);
    }
}
