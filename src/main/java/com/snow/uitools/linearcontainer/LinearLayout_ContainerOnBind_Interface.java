package com.snow.uitools.linearcontainer;

import android.view.View;

public interface LinearLayout_ContainerOnBind_Interface<T> {
    void onBindUI(View item, T bean, int index);
}
