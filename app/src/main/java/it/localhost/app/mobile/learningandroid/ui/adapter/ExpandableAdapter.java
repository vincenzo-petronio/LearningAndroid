package it.localhost.app.mobile.learningandroid.ui.adapter;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import it.localhost.app.mobile.learningandroid.R;
import it.localhost.app.mobile.learningandroid.data.model.Day;
import it.localhost.app.mobile.learningandroid.data.model.Hour;
import it.localhost.app.mobile.learningandroid.ui.adapter.viewholder.DayViewHolder;
import it.localhost.app.mobile.learningandroid.ui.adapter.viewholder.HourViewHolder;

/**
 *
 */

public class ExpandableAdapter extends ExpandableRecyclerViewAdapter<DayViewHolder, HourViewHolder> {


    public ExpandableAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public DayViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_expander_group, parent, false);
        return new DayViewHolder(view);
    }

    @Override
    public HourViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_expander_child, parent, false);
        return new HourViewHolder(view);
    }

    @Override
    public void onBindGroupViewHolder(DayViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setTitle(group.getTitle());
    }

    @Override
    public void onBindChildViewHolder(HourViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        Hour hh = ((Day) group).getItems().get(childIndex);
        holder.setHours(hh.getHhmm().concat("1"), hh.getHhmm().concat("2"), hh.getHhmm().concat("3"));
    }

}
