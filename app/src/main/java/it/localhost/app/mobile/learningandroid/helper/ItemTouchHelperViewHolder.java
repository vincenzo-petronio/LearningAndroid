package it.localhost.app.mobile.learningandroid.helper;

/**
 * Interface per notificare ad un item ViewHolder degli eventi catturati da
 * ItemTouchHelper.Callback.
 */
public interface ItemTouchHelperViewHolder {

    /**
     * Per notificare quando inizia un move o uno swipe.
     */
    void onItemSelected();

    /**
     * Per notificare quando finisce un move o uno swipe.
     */
    void onItemClear();
}
