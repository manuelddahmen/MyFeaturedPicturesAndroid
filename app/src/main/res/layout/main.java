<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/camera_preview"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:layout_weight="1"
    android:orientation="horizontal" >

    <include
        android:id="@+id/controls_layout"
        layout="@layout/controls" />

    <include
        android:id="@+id/sensors_data_layout"
        layout="@layout/sensors_data" />

</FrameLayout>
