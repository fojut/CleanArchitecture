package org.fojut.sample.presentation.presenter.extra;

/**
 * Interface representing a contract for presenter that contains a render view.
 */
public interface HasRenderView<V> {

    void setRenderView(V view);
}
