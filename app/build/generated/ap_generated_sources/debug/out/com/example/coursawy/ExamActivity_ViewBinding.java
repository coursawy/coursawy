// Generated code from Butter Knife. Do not modify!
package com.example.coursawy;

import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ExamActivity_ViewBinding implements Unbinder {
  private ExamActivity target;

  @UiThread
  public ExamActivity_ViewBinding(ExamActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ExamActivity_ViewBinding(ExamActivity target, View source) {
    this.target = target;

    target.onlineBtn = Utils.findRequiredViewAsType(source, R.id.online_btn, "field 'onlineBtn'", Button.class);
    target.offlineBtn = Utils.findRequiredViewAsType(source, R.id.offline_btn, "field 'offlineBtn'", Button.class);
    target.addTestBtn = Utils.findRequiredViewAsType(source, R.id.add_test_btn, "field 'addTestBtn'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ExamActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.onlineBtn = null;
    target.offlineBtn = null;
    target.addTestBtn = null;
  }
}
