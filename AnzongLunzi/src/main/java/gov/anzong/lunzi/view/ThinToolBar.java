package gov.anzong.lunzi.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.TintTypedArray;
import androidx.appcompat.widget.Toolbar;

import java.lang.reflect.Field;

import gov.anzong.lunzi.R;
import io.reactivex.annotations.Nullable;

/**
 * <br>
 * //   When I wrote this, only God and I understood what I was doing<br>
 * //   Now, God only knows<br>
 * //   作者： Anh Lai/竹井詩織里<br>
 * //   邮箱：ymback@sayyoulove.me<br>
 * //   创建时间：2017-06-29 10:37<br>
 * //   这玩意的用处：<br>
 */
@SuppressLint("PrivateResource")
public class ThinToolBar extends Toolbar {
    private TextView mTitleTextView;
    private CharSequence mTitleText;
    private int mTitleTextColor;
    private int mTitleTextAppearance;

    public ThinToolBar(Context context) {
        super(context);
        resolveAttribute(context, null, R.attr.toolbarStyle);
    }

    public ThinToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        resolveAttribute(context, attrs, R.attr.toolbarStyle);
    }

    public ThinToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        resolveAttribute(context, attrs, defStyleAttr);
    }

    @SuppressLint("RestrictedApi")
    private void resolveAttribute(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
// Need to use getContext() here so that we use the themed context
        context = getContext();
        final TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs,
                R.styleable.Toolbar, defStyleAttr, 0);
        final int titleTextAppearance = a.getResourceId(R.styleable.Toolbar_titleTextAppearance, 0);
        if (titleTextAppearance != 0) {
            setTitleTextAppearance(context, titleTextAppearance);
        }
        if (mTitleTextColor != 0) {
            setTitleTextColor(mTitleTextColor);
        }
        a.recycle();
        post(() -> {
            if (getLayoutParams() instanceof LayoutParams) {
                ((LayoutParams) getLayoutParams()).gravity = Gravity.CENTER;
            }
        });
        setContentInsetsAbsolute(0, 0);
        getContentInsetEnd();
        setPadding(0, 0, 0, 0);
    }

    @Override
    public CharSequence getTitle() {
        return mTitleText;
    }

    @Override
    public void setTitle(CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            if (mTitleTextView == null) {
                final Context context = getContext();
                mTitleTextView = new TextView(context);
                mTitleTextView.setSingleLine();
                mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                if (mTitleTextAppearance != 0) {
                    mTitleTextView.setTextAppearance(context, mTitleTextAppearance);
                }
                if (mTitleTextColor != 0) {
                    mTitleTextView.setTextColor(mTitleTextColor);
                }
            }
            if (mTitleTextView.getParent() != this) {
                addCenterView(mTitleTextView);
            }
        } else if (mTitleTextView != null && mTitleTextView.getParent() == this) {// 当title为空时，remove
            removeView(mTitleTextView);
        }
        if (mTitleTextView != null) {
            mTitleTextView.setText(title);
        }
        mTitleText = title;
    }

    private void addCenterView(View v) {
        final ViewGroup.LayoutParams vlp = v.getLayoutParams();
        final LayoutParams lp;
        if (vlp == null) {
            lp = generateDefaultLayoutParams();
        } else if (!checkLayoutParams(vlp)) {
            lp = generateLayoutParams(vlp);
        } else {
            lp = (LayoutParams) vlp;
        }
        addView(v, lp);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        LayoutParams lp = new LayoutParams(getContext(), attrs);
        lp.gravity = Gravity.CENTER;
        return lp;
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        LayoutParams lp;
        if (p instanceof LayoutParams) {
            lp = new LayoutParams((LayoutParams) p);
        } else if (p instanceof ActionBar.LayoutParams) {
            lp = new LayoutParams((ActionBar.LayoutParams) p);
        } else if (p instanceof MarginLayoutParams) {
            lp = new LayoutParams((MarginLayoutParams) p);
        } else {
            lp = new LayoutParams(p);
        }
        lp.gravity = Gravity.CENTER;
        return lp;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        return lp;
    }

    @Override
    public void setTitleTextAppearance(Context context, int resId) {
        mTitleTextAppearance = resId;
        if (mTitleTextView != null) {
            mTitleTextView.setTextAppearance(context, resId);
        }
        super.setTitleTextAppearance(context, resId);
    }

    @Override
    public void setTitleTextColor(@ColorInt int color) {
        mTitleTextColor = color;
        if (mTitleTextView != null) {
            mTitleTextView.setTextColor(color);
        }
    }

    @Override
    public void setNavigationIcon(@Nullable Drawable icon) {
        super.setNavigationIcon(icon);
        setGravityCenter();
    }

    public void setGravityCenter() {
        post(new Runnable() {
            @Override
            public void run() {
                setCenter("mNavButtonView");
                setCenter("mMenuView");
            }

        });
    }

    @SuppressWarnings("TryWithIdenticalCatches")
    private void setCenter(String fieldName) {
        try {
            Field field = getClass().getSuperclass().getDeclaredField(fieldName);//反射得到父类Field
            field.setAccessible(true);
            Object obj = field.get(this);//拿到对应的Object
            if (obj == null) return;
            if (obj instanceof View) {
                View view = (View) obj;
                ViewGroup.LayoutParams lp = view.getLayoutParams();//拿到LayoutParams
                if (lp instanceof ActionBar.LayoutParams) {
                    ActionBar.LayoutParams params = (ActionBar.LayoutParams) lp;
                    params.gravity = Gravity.CENTER;//设置居中
                    view.setLayoutParams(lp);
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }



}
