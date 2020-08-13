# HeartRateGraph

#### ScreenShot

<img src="https://github.com/103style/HeartRateGraph/blob/master/screenshot/minute.jpg"  height="432" width="216"> <img src="https://github.com/103style/HeartRateGraph/blob/master/screenshot/day.jpg"  height="432" width="216">

---

#### Install
add to the project `build.gradle`
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
add to the app  `build.gradle`
```
dependencies {
        implementation 'com.github.103style:HeartRateGraph:0.0.3'
}

```

---

#### Usage
```
<com.lxk.heartrate.HeartRateGraphWidget
    android:id="@+id/heart_rate"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_margin="16dp"
    android:background="#FFFFFF"
    android:padding="16dp"
    app:day_heart_rate_line_color="#80FF0000"
    app:day_heart_rate_line_with="1dp"
    app:day_heart_rate_max_show="100"
    app:dotted_line_color="@android:color/darker_gray"
    app:dotted_line_gap="1dp"
    app:dotted_line_height="2dp"
    app:dotted_line_width="3dp"
    app:mark_text_color="@android:color/darker_gray"
    app:mark_text_size="10sp"
    app:max_min_bg_color="@color/colorAccent"
    app:max_min_text_color="@android:color/black"
    app:max_min_text_left_right_padding="4dp"
    app:max_min_text_size="12sp"
    app:max_min_text_top_bottom_padding="2dp"
    app:max_min_text_triangle_height="5dp"
    app:select_line_color="#FFFF0000"
    app:select_line_width="1dp"
    app:shader_color_end="#20FF0000"
    app:shader_color_start="#80FF0000"
    app:show_day_shader="true"
    app:show_max_min="true"
    app:show_test_data="true"
    app:solid_line_color="@android:color/darker_gray"
    app:solid_line_height="2dp"
    app:time_string_top_margin="8dp" />
```

---

### attr:
```
<declare-styleable name="HeartRateGraphWidget">
    <!--  虚线的高度   -->
    <attr name="dotted_line_height" format="dimension" />
    <!--  虚线的宽度  -->
    <attr name="dotted_line_width" format="dimension" />
    <!--  虚线的空白间隔   -->
    <attr name="dotted_line_gap" format="dimension" />
    <!--  虚线的颜色   -->
    <attr name="dotted_line_color" format="color" />
    <!--  实线的高度   -->
    <attr name="solid_line_height" format="dimension" />
    <!--  实线的颜色   -->
    <attr name="solid_line_color" format="color" />
    <!--  坐标系标记的文字大小  x,y轴的标记  -->
    <attr name="mark_text_size" format="dimension" />
    <!--  坐标系标记的文字颜色  x,y轴的标记 -->
    <attr name="mark_text_color" format="color" />
    <!--  时间轴/x轴 文字和线的距离 -->
    <attr name="time_string_top_margin" format="dimension" />

    <!--  最大最小文字的大小 -->
    <attr name="max_min_text_size" format="dimension" />
    <!--  最大最小文字的颜色 -->
    <attr name="max_min_text_color" format="color" />
    <!--  最大最小文字的上下边距 -->
    <attr name="max_min_text_top_bottom_padding" format="dimension" />
    <!--  最大最小文字的左右边距 -->
    <attr name="max_min_text_left_right_padding" format="dimension" />
    <!--  最大最小文字的背景三角形的高度 -->
    <attr name="max_min_text_triangle_height" format="dimension" />
    <!--  最大最小文字的背景颜色 -->
    <attr name="max_min_bg_color" format="color" />
    <!--  最大最小文字的文字格式 -->
    <attr name="max_min_text_format" format="string" />

    <!--  每天按分钟显示的折线图 线的颜色  -->
    <attr name="day_heart_rate_line_color" format="color" />
    <!--  每天按分钟显示的折线图 线的宽度  -->
    <attr name="day_heart_rate_line_with" format="dimension" />
    <!--  每天按分钟显示的折线图最多显示多少个点 -->
    <attr name="day_heart_rate_max_show" format="integer" />

    <!--  柱状图的宽度  -->
    <attr name="histogram_with" format="dimension" />
    <!--  柱状图线的颜色  -->
    <attr name="histogram_line_color" format="color" />
    <!--  柱状图圆点的颜色  -->
    <attr name="histogram_dot_color" format="color" />

    <!-- 标记选中item的线的宽度 -->
    <attr name="select_line_width" format="dimension" />
    <!-- 标记选中item的线的颜色 -->
    <attr name="select_line_color" format="color" />

    <!-- 是否显示每天的心率折线图的阴影 -->
    <attr name="show_day_shader" format="boolean" />
    <!--  折线图的阴影的渐变颜色的开始和结束 -->
    <attr name="shader_color_start" format="color" />
    <attr name="shader_color_end" format="color" />
    <!-- 是否显示最大最小值 -->
    <attr name="show_max_min" format="boolean" />
    <!-- 是否显示测试数据 -->
    <attr name="show_test_data" format="boolean" />

</declare-styleable>
```

---

###  Method:
```
/**
 * 设置选中的回调
 */
public void setOnItemSelectCallback(HeartRateGraphWidget.onItemSelectCallback onItemSelectCallback) {}

/**
 * @param type     {@link ShowType}显示的类型
 * @param times    时间轴文字
 * @param dataList 心率数据
 */
public void updateHeartRateShow(@ShowType int type, List<String> times, List<List<HeartRateBean>> dataList) {}

/**
 * 更新心率数据
 *
 * @param dataList 心率数据
 */
public void updateHeartRateData(List<List<HeartRateBean>> dataList) {}
```

---
