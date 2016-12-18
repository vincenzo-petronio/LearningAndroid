package it.localhost.app.mobile.learningandroid.ui.adapter.viewholder;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.localhost.app.mobile.learningandroid.R;

/**
 *
 */

public class DayViewHolder extends GroupViewHolder {

    @BindView(R.id.tvGroupTitle)
    TextView mTvGroupTitle;
    @BindView(R.id.ivGroup)
    ImageView mIvGroup;

    public DayViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void setTitle(String title) {
        mTvGroupTitle.setText(title.toUpperCase());
    }
}
