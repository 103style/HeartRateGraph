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
        implementation 'com.github.103style:HeartRateGraph:0.0.6'
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
    android:padding="24dp"
    android:paddingLeft="24dp"
    android:paddingRight="24dp"
    app:hrg_below_solid_line_height="40dp"
    app:hrg_cur_show_type="DAY"
    app:hrg_day_heart_rate_line_color="#FFF33838"
    app:hrg_day_heart_rate_line_with="1dp"
    app:hrg_day_heart_rate_max_show="100"
    app:hrg_dotted_line_color="#26000000"
    app:hrg_dotted_line_gap="1dp"
    app:hrg_dotted_line_height="1dp"
    app:hrg_dotted_line_width="3dp"
    app:hrg_heart_rate_warn_line_height="1dp"
    app:hrg_mark_text_color="#61000000"
    app:hrg_mark_text_size="14sp"
    app:hrg_max_min_bg_color="@color/colorAccent"
    app:hrg_max_min_text_color="@android:color/black"
    app:hrg_max_min_text_format="%d bmp"
    app:hrg_max_min_text_left_right_padding="4dp"
    app:hrg_max_min_text_size="12sp"
    app:hrg_max_min_text_top_bottom_padding="2dp"
    app:hrg_max_min_text_triangle_height="5dp"
    app:hrg_select_line_width="1dp"
    app:hrg_shader_color_end="#1AF33838"
    app:hrg_shader_color_start="#B3F33838"
    app:hrg_show_day_shader="true"
    app:hrg_show_max_min="true"
    app:hrg_show_test_data="true"
    app:hrg_solid_line_color="#26000000"
    app:hrg_solid_line_height="1dp"
    app:hrg_time_string_top_margin="16dp" />
```

---

### attr:
```
<declare-styleable name="HeartRateGraphWidget">
    <!--  虚线的高度   -->
    <attr name="hrg_dotted_line_height" format="dimension" />
    <!--  虚线的宽度  -->
    <attr name="hrg_dotted_line_width" format="dimension" />
    <!--  虚线的空白间隔   -->
    <attr name="hrg_dotted_line_gap" format="dimension" />
    <!--  虚线的颜色   -->
    <attr name="hrg_dotted_line_color" format="color" />
    <!--  实线的高度   -->
    <attr name="hrg_solid_line_height" format="dimension" />
    <!--  实线的颜色   -->
    <attr name="hrg_solid_line_color" format="color" />
    <!--  坐标系标记的文字大小  x,y轴的标记  -->
    <attr name="hrg_mark_text_size" format="dimension" />
    <!--  坐标系标记的文字颜色  x,y轴的标记 -->
    <attr name="hrg_mark_text_color" format="color" />
    <!--  时间轴/x轴 文字和线的距离 -->
    <attr name="hrg_time_string_top_margin" format="dimension" />

    <!--  最大最小文字的大小 -->
    <attr name="hrg_max_min_text_size" format="dimension" />
    <!--  最大最小文字的颜色 -->
    <attr name="hrg_max_min_text_color" format="color" />
    <!--  最大最小文字的上下边距 -->
    <attr name="hrg_max_min_text_top_bottom_padding" format="dimension" />
    <!--  最大最小文字的左右边距 -->
    <attr name="hrg_max_min_text_left_right_padding" format="dimension" />
    <!--  最大最小文字的背景三角形的高度 -->
    <attr name="hrg_max_min_text_triangle_height" format="dimension" />
    <!--  最大最小文字的背景颜色 -->
    <attr name="hrg_max_min_bg_color" format="color" />
    <!--  最大最小文字的文字格式 -->
    <attr name="hrg_max_min_text_format" format="string" />

    <!--  每天按分钟显示的折线图 线的颜色  -->
    <attr name="hrg_day_heart_rate_line_color" format="color" />
    <!--  每天按分钟显示的折线图 线的宽度  -->
    <attr name="hrg_day_heart_rate_line_with" format="dimension" />
    <!--  每天按分钟显示的折线图最多显示多少个点 -->
    <attr name="hrg_day_heart_rate_max_show" format="integer" />

    <!--  柱状图的宽度  -->
    <attr name="hrg_histogram_with" format="dimension" />
    <!--  柱状图线的颜色  -->
    <attr name="hrg_histogram_line_color" format="color" />
    <!--  柱状图圆点的颜色  -->
    <attr name="hrg_histogram_dot_color" format="color" />

    <!-- 标记选中item的线的宽度 -->
    <attr name="hrg_select_line_width" format="dimension" />
    <!-- 标记选中item的线的颜色 -->
    <attr name="hrg_select_line_color" format="color" />

    <!-- 是否显示每天的心率折线图的阴影 -->
    <attr name="hrg_show_day_shader" format="boolean" />
    <!--  折线图的阴影的渐变颜色的开始和结束 -->
    <attr name="hrg_shader_color_start" format="color" />
    <attr name="hrg_shader_color_end" format="color" />
    <!-- 是否显示最大最小值 -->
    <attr name="hrg_show_max_min" format="boolean" />
    <!-- 是否显示测试数据 -->
    <attr name="hrg_show_test_data" format="boolean" />

</declare-styleable>
```

---

###  Method:
```
/**
 * 设置选中的回调
 */
public void setOnItemSelectCallback(HeartRateGraphWidget.onItemSelectCallback onItemSelectCallback)


/**
 * 设置心跳警告的最大最小值
 */
public void setHeartRateWarnMax(int heartRateWarnMax, int heartRateWarnMin)

/**
 * 更新当前显示状态
 */
public void setCurShowType(@ShowType int curShowType)

/**
 * 设置时间轴时间数据
 */
public void setTimeStrings(List<String> timeStrings)

/**
 * 设置心率数据
 *
 * @param dataList 心率数据
 */
public void setHeartRateDataList(List<List<HeartRateBean>> dataList)

/**
 * @param type     {@link ShowType}显示的类型
 * @param times    时间轴文字
 * @param dataList 心率数据
 */
public void setHeartRateShow(@ShowType int type, List<String> times, List<List<HeartRateBean>> dataList)
```

---
