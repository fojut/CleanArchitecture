package org.fojut.sample.presentation.base.presenter;

import android.util.Log;

import org.fojut.sample.presentation.base.presenter.extra.HasView;

/**
 * Interface representing a BasePresenter in a model view presenter (MVP) pattern.
 */
public abstract class BasePresenter<V> implements HasView<V> {

  private static final String TAG = BasePresenter.class.getSimpleName();

  private V view;

  /**
   * Method that control the lifecycle of the view. It should be called in the view's
   * (Activity or Fragment) onResume() method.
   */
  public abstract void onResume();

  /**
   * Method that control the lifecycle of the view. It should be called in the view's
   * (Activity or Fragment) onPause() method.
   */
  public abstract void onPause();

  /**
   * Method that control the lifecycle of the view. It should be called in the view's
   * (Activity or Fragment) onDestroy() method.
   */
  public abstract void onDestroy();

  /**
   * Configures the View instance used in this presenter as view.
   */
  public void setView(V view) {
    this.view = view;
  }

  /**
   * Returns the view configured in the presenter which real implementation is an Activity or
   * Fragment using this presenter.
   */
  public V getView() {
    if(view == null){
      Log.e(TAG, "This presenter does not set view!");
      throw new RuntimeException("This presenter does not set view!");
    }
    return view;
  }
}
