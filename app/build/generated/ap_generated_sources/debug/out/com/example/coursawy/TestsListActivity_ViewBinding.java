// Generated code from Butter Knife. Do not modify!
package com.example.coursawy;

import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TestsListActivity_ViewBinding implements Unbinder {
  private TestsListActivity target;

  @UiThread
  public TestsListActivity_ViewBinding(TestsListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TestsListActivity_ViewBinding(TestsListActivity target, View source) {
    this.target = target;

    target.progress = Utils.findRequiredViewAsType(source, R.id.progress, "field 'progress'", ProgressBar.class);
    target.testAlertTv = Utils.findRequiredViewAsType(source, R.id.test_alert_tv, "field 'testAlertTv'", TextView.class);
    target.testsListView = Utils.findRequiredViewAsType(source, R.id.tests_list_view, "field 'testsListView'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TestsListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.progress = null;
    target.testAlertTv = null;
    target.testsListView = null;
  }
}
