// Generated code from Butter Knife. Do not modify!
package com.example.coursawy;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OfflineTests_ViewBinding implements Unbinder {
  private OfflineTests target;

  @UiThread
  public OfflineTests_ViewBinding(OfflineTests target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OfflineTests_ViewBinding(OfflineTests target, View source) {
    this.target = target;

    target.timerTv = Utils.findRequiredViewAsType(source, R.id.timer_tv, "field 'timerTv'", TextView.class);
    target.examTitleTv = Utils.findRequiredViewAsType(source, R.id.exam_title_tv, "field 'examTitleTv'", TextView.class);
    target.counterTv = Utils.findRequiredViewAsType(source, R.id.counter_tv, "field 'counterTv'", TextView.class);
    target.infoV = Utils.findRequiredView(source, R.id.info_v, "field 'infoV'");
    target.markTv = Utils.findRequiredViewAsType(source, R.id.mark_tv, "field 'markTv'", TextView.class);
    target.equestionTv = Utils.findRequiredViewAsType(source, R.id.equestion_tv, "field 'equestionTv'", TextView.class);
    target.eradioOne = Utils.findRequiredViewAsType(source, R.id.eradio_one, "field 'eradioOne'", RadioButton.class);
    target.eradioTwo = Utils.findRequiredViewAsType(source, R.id.eradio_two, "field 'eradioTwo'", RadioButton.class);
    target.eradioThree = Utils.findRequiredViewAsType(source, R.id.eradio_three, "field 'eradioThree'", RadioButton.class);
    target.eradioFour = Utils.findRequiredViewAsType(source, R.id.eradio_four, "field 'eradioFour'", RadioButton.class);
    target.eradioGroupAll = Utils.findRequiredViewAsType(source, R.id.eradio_group_all, "field 'eradioGroupAll'", RadioGroup.class);
    target.examSubmitBtn = Utils.findRequiredViewAsType(source, R.id.exam_submit_btn, "field 'examSubmitBtn'", Button.class);
    target.examLl = Utils.findRequiredViewAsType(source, R.id.exam_ll, "field 'examLl'", LinearLayout.class);
    target.showMarksBtn = Utils.findRequiredViewAsType(source, R.id.show_marks_btn, "field 'showMarksBtn'", Button.class);
    target.questionsLl = Utils.findRequiredViewAsType(source, R.id.questions_ll, "field 'questionsLl'", LinearLayout.class);
    target.backIv = Utils.findRequiredViewAsType(source, R.id.back_iv, "field 'backIv'", ImageView.class);
    target.backBtn = Utils.findRequiredViewAsType(source, R.id.back_btn, "field 'backBtn'", Button.class);
    target.nextBtn = Utils.findRequiredViewAsType(source, R.id.next_btn, "field 'nextBtn'", Button.class);
    target.questionsProgress = Utils.findRequiredViewAsType(source, R.id.questions_progress, "field 'questionsProgress'", SeekBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OfflineTests target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.timerTv = null;
    target.examTitleTv = null;
    target.counterTv = null;
    target.infoV = null;
    target.markTv = null;
    target.equestionTv = null;
    target.eradioOne = null;
    target.eradioTwo = null;
    target.eradioThree = null;
    target.eradioFour = null;
    target.eradioGroupAll = null;
    target.examSubmitBtn = null;
    target.examLl = null;
    target.showMarksBtn = null;
    target.questionsLl = null;
    target.backIv = null;
    target.backBtn = null;
    target.nextBtn = null;
    target.questionsProgress = null;
  }
}
