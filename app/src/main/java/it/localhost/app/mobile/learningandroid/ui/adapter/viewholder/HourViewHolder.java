package it.localhost.app.mobile.learningandroid.ui.adapter.viewholder;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.localhost.app.mobile.learningandroid.R;

/**
 *
 */

public class HourViewHolder extends ChildViewHolder {

    @BindView(R.id.tvChild1)
    protected TextView mTvChild1;
    @BindView(R.id.tvChild2)
    protected TextView mTvChild2;
    @BindView(R.id.tvChild3)
    protected TextView mTvChild3;

    public HourViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    public void setHours(String h1, String h2, String h3) {
        mTvChild1.setText(h1);
        mTvChild2.setText(h2);
        mTvChild3.setText(h3);
    }
}
