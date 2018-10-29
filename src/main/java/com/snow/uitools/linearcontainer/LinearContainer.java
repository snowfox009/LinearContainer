package com.snow.uitools.linearcontainer;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjm on 2017/8/17.
 */
public class LinearContainer<T> extends LinearLayout {

    private List<T> listData;
    private List<View> listView;
    private LinearLayout_Container_Interface<T> linearLayout_container_interface;
    private LinearLayout_ContainerOnBind_Interface<T> linearLayout_container_onbind_interface;

    private Context mContext;
    private View footer;

    public LinearContainer(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public LinearContainer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public LinearContainer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    public void clearData() {
        this.removeAllViews();
        listData = new ArrayList<>();
        listView = new ArrayList<>();

    }

    private void init() {

        this.setOrientation(VERTICAL);
        listData = new ArrayList<T>();
        listView = new ArrayList<View>();
    }

    public void setData(final List<T> list, int r_item_layout, LinearLayout_Container_Interface<T> callback) {
        clearData();

        if (list == null || list.size() == 0) {
            return;
        }
        listData.addAll(list);
        linearLayout_container_interface = callback;

        for (int i = 0; i < listData.size(); i++) {
            final View view = LayoutInflater.from(mContext).inflate(r_item_layout, null);
            this.addView(view);
            listView.add(view);
            if (linearLayout_container_interface != null) {
                linearLayout_container_interface.onBindUI(view, listData.get(i), i);
            }

            final T bean = listData.get(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (linearLayout_container_interface != null) {
                        linearLayout_container_interface.onClickItem(view, bean, listView);
                    }
                }
            });

        }
    }

    public void setData(final List<T> list, int r_item_layout, LinearLayout_ContainerOnBind_Interface<T> callback) {
        clearData();
        if (list == null || list.size() == 0) {
            return;
        }
        listData.addAll(list);
        linearLayout_container_onbind_interface = callback;

        for (int i = 0; i < listData.size(); i++) {
            final View view = LayoutInflater.from(mContext).inflate(r_item_layout, null);
            this.addView(view);
            listView.add(view);
            if (linearLayout_container_onbind_interface != null) {
                linearLayout_container_onbind_interface.onBindUI(view, listData.get(i), i);
            }

        }
    }

    public void setFooter(int r_footer_layout) {
        footer = LayoutInflater.from(mContext).inflate(r_footer_layout, null);
        this.addView(footer);
        //showFooter(true);
    }

    public void showFooter(boolean isShow) {
        if (footer == null) {
            return;
        }
        if (isShow) {

            footer.setVisibility(VISIBLE);
        } else {
            footer.setVisibility(GONE);
        }

    }

    public List<View> getViewList() {
        return listView;
    }

    public List<T> getListData() {
        return listData;
    }

    public void addAllItem(final List<T> list, int r_item_layout, LinearLayout_ContainerOnBind_Interface<T> callback) {

        if (list == null || list.size() == 0) {
            return;
        }
        listData.addAll(list);
        linearLayout_container_onbind_interface = callback;

        for (int i = 0; i < listData.size(); i++) {
            final View view = LayoutInflater.from(mContext).inflate(r_item_layout, null);
            this.addView(view);
            listView.add(view);
            if (linearLayout_container_onbind_interface != null) {
                linearLayout_container_onbind_interface.onBindUI(view, listData.get(i), i);
            }

        }
    }

    public void addAllItem(final List<T> list, int r_item_layout, LinearLayout_Container_Interface<T> callback) {
        if (list == null || list.size() == 0) {
            return;
        }
        listData.addAll(list);
        linearLayout_container_interface = callback;

        for (int i = 0; i < listData.size(); i++) {
            final View view = LayoutInflater.from(mContext).inflate(r_item_layout, null);
            this.addView(view);
            listView.add(view);
            if (linearLayout_container_interface != null) {
                linearLayout_container_interface.onBindUI(view, listData.get(i), i);
            }

            final T bean = listData.get(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (linearLayout_container_interface != null) {
                        linearLayout_container_interface.onClickItem(view, bean, listView);
                    }
                }
            });

        }
    }

    public void setHeight(int h) {
        try {
            ViewGroup.LayoutParams p = this.getLayoutParams();
            p.height = h;
            this.setLayoutParams(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeAtView(View view) {
        try {

            if (listView.contains(view)) {

                for (int i = 0; i < listView.size(); i++) {

                    if (listView.get(i) == view) {
                        listData.remove(i);
                        listView.remove(view);
                        this.removeView(view);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void notifyDataSetChangedBind() {

        for (int i = 0; i < listData.size(); i++) {
            try {
                if (linearLayout_container_onbind_interface != null) {
                    linearLayout_container_onbind_interface.onBindUI(listView.get(i), listData.get(i), i);
                }
                if (linearLayout_container_interface != null) {
                    linearLayout_container_interface.onBindUI(listView.get(i), listData.get(i), i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
