package org.fojut.sample.presentation.view.render;

/**
 * Interface representing a contract for presenter that need to render UI view.
 */
public interface LoadDataView<D> extends LoadView {

    /**
     * Load data to show in the View.
     * @param data
     */
    void loadData(D data);
}
