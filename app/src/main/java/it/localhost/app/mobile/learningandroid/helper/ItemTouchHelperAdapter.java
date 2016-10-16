package it.localhost.app.mobile.learningandroid.helper;

/**
 * Interface per notificare all'adapter i movimenti degli elementi della lista.
 */
public interface ItemTouchHelperAdapter {

    /**
     * Chiamato quando l'item viene spostato attraverso un move.
     *
     * @param fromPosition int
     * @param toPosition   int
     * @return true se l'item è nella nuova posizione
     */
    boolean onItemMove(int fromPosition, int toPosition);

    /**
     * Chiamato quando l'item viene rimosso attraverso uno swipe.
     *
     * @param position int
     */
    void onItemDismiss(int position);
}
