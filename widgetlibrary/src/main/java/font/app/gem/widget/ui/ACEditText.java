package font.app.gem.widget.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import font.app.gem.widgetlibrary.utils.TextWatcherAdapter;

/**
 * Created by hi on 11/7/16.
 */

public class ACEditText extends CFEditText implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcherAdapter.TextWatcherListener {

  public static enum Location {
    LEFT(0), RIGHT(2);

    final int idx;

    private Location(int idx) {
      this.idx = idx;
    }
  }

  public interface Listener {
    void onClickIcon(ACEditText editText, boolean isShow);
  }

  public ACEditText(Context context) {
    super(context);
    init();
  }

  public ACEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public ACEditText(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init();
  }

  public void setListener(Listener listener) {
    this.listener = listener;
  }

  public void setPasswordVisible(boolean isShow){
    this.isPasswordShow = isShow;
  }
  /**
   * null disables the icon
   */
  public void setIconLocation(Location loc) {
    this.loc = loc;
    initIcon();
  }

  @Override
  public void setOnTouchListener(View.OnTouchListener l) {
    this.l = l;
  }

  @Override
  public void setOnFocusChangeListener(View.OnFocusChangeListener f) {
    this.f = f;
  }

  private Location loc = Location.RIGHT;

  private Drawable xD;
  private Listener listener;
  private boolean isPasswordShow = false;
  private View.OnTouchListener l;
  private View.OnFocusChangeListener f;

  @Override
  public boolean onTouch(View v, MotionEvent event) {
    if (getDisplayedDrawable() != null) {
      int x = (int) event.getX();
      int y = (int) event.getY();
      int left = (loc == Location.LEFT) ? 0 : getWidth() - getPaddingRight() - xD.getIntrinsicWidth();
      int right = (loc == Location.LEFT) ? getPaddingLeft() + xD.getIntrinsicWidth() : getWidth();
      boolean tappedX = x >= left && x <= right && y >= 0 && y <= (getBottom() - getTop());
      if (tappedX) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
          if (listener != null) {
            listener.onClickIcon(this, this.isPasswordShow);
          }
        }
        return true;
      }
    }
    if (l != null) {
      return l.onTouch(v, event);
    }
    return false;
  }

  @Override
  public void onFocusChange(View v, boolean hasFocus) {
    if (f != null) {
      f.onFocusChange(v, hasFocus);
    }
  }

  @Override
  public void onTextChanged(EditText view, String text) {

  }

  @Override
  public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
    super.setCompoundDrawables(left, top, right, bottom);
    initIcon();
  }

  private void init() {
    super.setOnTouchListener(this);
    super.setOnFocusChangeListener(this);
    addTextChangedListener(new TextWatcherAdapter(this, this));
    initIcon();
    setIconVisible(true);
  }

  private void initIcon() {
    xD = null;
    if (loc != null) {
      xD = getCompoundDrawables()[loc.idx];
    }
    if (xD == null) {
      xD = getResources().getDrawable(android.R.drawable.presence_offline);
    }
    xD.setBounds(0, 0, xD.getIntrinsicWidth(), xD.getIntrinsicHeight());
    int min = getPaddingTop() + xD.getIntrinsicHeight() + getPaddingBottom();
    if (getSuggestedMinimumHeight() < min) {
      setMinimumHeight(min);
    }
  }

  private Drawable getDisplayedDrawable() {
    return (loc != null) ? getCompoundDrawables()[loc.idx] : null;
  }

  protected void setIconVisible(boolean visible) {
    Drawable[] cd = getCompoundDrawables();
    Drawable displayed = getDisplayedDrawable();
    boolean wasVisible = (displayed != null);
    if (visible != wasVisible) {
      Drawable x = visible ? xD : null;
      super.setCompoundDrawables((loc == Location.LEFT) ? x : cd[0], cd[1], (loc == Location.RIGHT) ? x : cd[2],
          cd[3]);
    }
  }
}
