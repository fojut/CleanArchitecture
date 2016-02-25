package org.fojut.sample.presentation.view.render;

/**
 * Interface representing a contract for presenter that need to render UI view.
 */
public interface RenderView<D> extends LoadView {
    void renderView(D data);
}
