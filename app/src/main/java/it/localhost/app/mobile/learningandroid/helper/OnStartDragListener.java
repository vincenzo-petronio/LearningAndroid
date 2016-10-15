package it.localhost.app.mobile.learningandroid.helper;

import android.support.v7.widget.RecyclerView;

/**
 * Listener per notificare l'inizio di un evento Drag
 */
public interface OnStartDragListener {

    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
