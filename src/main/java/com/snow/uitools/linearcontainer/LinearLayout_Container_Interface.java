package com.snow.uitools.linearcontainer;

import android.view.View;

import java.util.List;

public interface LinearLayout_Container_Interface<T> {
    void onBindUI(View item, T bean, int index);
    void onClickItem(View item, T bean, List<View> list);
}
