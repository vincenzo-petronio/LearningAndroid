package it.localhost.app.mobile.learningandroid.helper;

/**
 * Interface per notificare i movimenti degli elementi della lista.
 */
public interface ItemTouchHelperAdapter {

    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
