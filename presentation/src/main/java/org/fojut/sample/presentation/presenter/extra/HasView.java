package org.fojut.sample.presentation.presenter.extra;

/**
 * Interface representing a contract for presenter that contains a view.
 */
public interface HasView<V> {

    /**
     * Configures the View instance used in this presenter as view.
     */
    void setView(V view);

    /**
     * Returns the view configured in the presenter which real implementation is an Activity or
     * Fragment using this presenter.
     */
    V getView();
}
