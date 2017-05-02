package sun.wh.cn.androidpractice;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by marc on 17-5-2.
 */

public class HeadlinesFragment extends Fragment {
    OnHeadlineSelectedListener mCallback;

    // 容器 Activity 必须实现该接口
    // （译注：“容器 Activity”意即“包含该 Fragment 的 Activity”）
    public interface OnHeadlineSelectedListener {
        void onArticleSelected(int position);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 拉伸该 Fragment 的布局
        View inflateView = inflater.inflate(R.layout.fragment_headlines, container, false);

        TextView headline = (TextView) inflateView.findViewById(R.id.headline);
        headline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onArticleSelected(0);
            }
        });

        return inflateView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // 确认容器 Activity 已实现该回调接口。否则，抛出异常
        try {
            mCallback = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}


