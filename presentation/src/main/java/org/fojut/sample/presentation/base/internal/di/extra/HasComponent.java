package org.fojut.sample.presentation.base.internal.di.extra;

/**
 * Interface representing a contract for clients that contains a component for dependency injection.
 */
public interface HasComponent<C> {
    C getComponent();
}

