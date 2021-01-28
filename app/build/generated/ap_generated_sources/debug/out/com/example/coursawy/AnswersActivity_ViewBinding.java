// Generated code from Butter Knife. Do not modify!
package com.example.coursawy;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AnswersActivity_ViewBinding implements Unbinder {
  private AnswersActivity target;

  @UiThread
  public AnswersActivity_ViewBinding(AnswersActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AnswersActivity_ViewBinding(AnswersActivity target, View source) {
    this.target = target;

    target.titleTv = Utils.findRequiredViewAsType(source, R.id.a_exam_title_tv, "field 'titleTv'", TextView.class);
    target.markTv = Utils.findRequiredViewAsType(source, R.id.a_mark_tv, "field 'markTv'", TextView.class);
    target.answersTv = Utils.findRequiredViewAsType(source, R.id.answers_tv, "field 'answersTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AnswersActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleTv = null;
    target.markTv = null;
    target.answersTv = null;
  }
}
