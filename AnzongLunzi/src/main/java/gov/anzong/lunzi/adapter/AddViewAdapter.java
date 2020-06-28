package gov.anzong.lunzi.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * <br>
 * //   When I wrote this, only God and I understood what I was doing<br>
 * //   Now, God only knows<br>
 * //   作者： Anh Lai/竹井詩織里<br>
 * //   邮箱：ymback@sayyoulove.me<br>
 * //   创建时间：2017-07-09 20:33<br>
 * //   这玩意的用处：<br>
 */
public abstract class AddViewAdapter<T> extends BaseAdapter {
    private Context mContext;
    private int resourceId;
    private List<T> mList;
    private ViewGroup parentViewGroup;

    public AddViewAdapter(Context mContext, int layoutResource, ViewGroup parent) {
        super();
        this.mContext = mContext;
        this.resourceId = layoutResource;
        this.parentViewGroup = parent;
    }

    public void setData(List<T> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder")
        View view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
        bindData(view, position);
        return view;
    }

    public abstract void bindData(View view, int position);

    public List<View> getViewList() {
        List<View> views = new ArrayList<>();
        for (int i = 0; i < getCount(); i++) {
            views.add(getView(i, null, parentViewGroup));
        }
        return views;
    }
}
