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
        implementation 'com.github.103style:HeartRateGraph:1.0.0'
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
    app:hrg_cur_show_type="DAY"
    app:hrg_day_heart_rate_dot_max_show="50"
    app:hrg_day_heart_rate_line_color="#FFF33838"
    app:hrg_day_heart_rate_line_with="1dp"
    app:hrg_dotted_line_color="#26000000"
    app:hrg_dotted_line_gap="1dp"
    app:hrg_dotted_line_height="1dp"
    app:hrg_dotted_line_width="3dp"
    app:hrg_heart_rate_warn_line_color="#FFDE0000"
    app:hrg_heart_rate_warn_line_height="1dp"
    app:hrg_histogram_line_color="#FFFFCC95"
    app:hrg_histogram_with="4dp"
    app:hrg_mark_text_color="#61000000"
    app:hrg_mark_text_size="10sp"
    app:hrg_max_min_bg_color="@color/colorAccent"
    app:hrg_max_min_text_color="@android:color/black"
    app:hrg_max_min_text_format="%d bmp"
    app:hrg_max_min_text_left_right_padding="4dp"
    app:hrg_max_min_text_size="12sp"
    app:hrg_max_min_text_top_bottom_padding="2dp"
    app:hrg_max_min_text_triangle_height="5dp"
    app:hrg_select_line_color_end="#00FF9319"
    app:hrg_select_line_color_in_histogram="#FFFF8700"
    app:hrg_select_line_color_middle="#FFFF9319"
    app:hrg_select_line_color_start="#2AFF9319"
    app:hrg_select_line_width="1dp"
    app:hrg_shader_color_end="#00F33838"
    app:hrg_shader_color_start="#B3F33838"
    app:hrg_show_day_shader="true"
    app:hrg_show_max_min="false"
    app:hrg_show_test_data="true"
    app:hrg_solid_line_color="#26000000"
    app:hrg_solid_line_height="1dp"
    app:hrg_time_line_bottom_margin="40dp"
    app:hrg_time_line_to_x_line_height="35dp"
    app:hrg_time_string_top_margin="16dp"
    app:hrg_y_value_string_array="@array/y_values" />
```

---

### attr:
```
<declare-styleable name="HeartRateGraphWidget">
    <!-- 当前的显示格式 -->
    <attr name="hrg_cur_show_type" format="integer">
        <flag name="DAY" value="0" />
        <flag name="WEEK" value="1" />
        <flag name="MONTH" value="2" />
        <flag name="YEAR" value="3" />
    </attr>

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


    <!-- y轴的数据 -->
    <attr name="hrg_y_value_string_array" format="reference" />
    <!--  坐标系标记的文字大小  x,y轴的标记  -->
    <attr name="hrg_mark_text_size" format="dimension" />
    <!--  坐标系标记的文字颜色  x,y轴的标记 -->
    <attr name="hrg_mark_text_color" format="color" />
    <!-- 时间线和 坐标轴 x轴的距离 -->
    <attr name="hrg_time_line_to_x_line_height" format="dimension" />
    <!--  时间轴 下方距离 -->
    <attr name="hrg_time_line_bottom_margin" format="dimension" />
    <!--  时间轴 文字和线的距离 -->
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
    <!--  每天按分钟显示的折线图最多显示多少个点  默认 144个点-->
    <attr name="hrg_day_heart_rate_dot_max_show" format="integer" />

    <!--  柱状图的宽度  -->
    <attr name="hrg_histogram_with" format="dimension" />
    <!--  柱状图线的颜色  -->
    <attr name="hrg_histogram_line_color" format="color" />

    <!-- 标记选中item的线的宽度 -->
    <attr name="hrg_select_line_width" format="dimension" />
    <!-- 标记选中item的线的颜色 -->
    <attr name="hrg_select_line_color_start" format="color" />
    <attr name="hrg_select_line_color_middle" format="color" />
    <attr name="hrg_select_line_color_end" format="color" />
    <attr name="hrg_select_line_color_in_histogram" format="color" />

    <!-- 心跳预警线的颜色 和 高度  以及是否显示 -->
    <attr name="hrg_heart_rate_warn_line_color" format="color" />
    <attr name="hrg_heart_rate_warn_line_height" format="dimension" />

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
 * 设置心跳警告的最大最小值
 */
public void setHeartRateWarnMax(int heartRateWarnMax, int heartRateWarnMin) {
    this.heartRateWarnMax = heartRateWarnMax;
    this.heartRateWarnMin = heartRateWarnMin;
}

/**
 * 更新当前显示状态
 */
public int getCurShowType()
public void setCurShowType(@ShowType int curShowType)

/**
 * 设置选中的回调
 */
public void setOnItemSelectCallback(HeartRateGraphWidget.onItemSelectCallback onItemSelectCallback)

/**
 * 设置时间轴时间
 */
public void setTimeStrings(List<String> timeStrings)

/**
 * 更新心率数据
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

/**
 * 设置每天的数据最多显示点的个数
 */
public int getDayHeartRateMaxShow()
public void setDayHeartRateMaxShow(int dayHeartRateMaxShow)

/**
 * 虚线的高度
 */
public float getDottedLineHeight()
public void setDottedLineHeight(float dottedLineHeight)}

/**
 * 虚线的每个实线长度
 */
public float getDottedLineWidth()
public void setDottedLineWidth(float dottedLineWidth)

/**
 * 虚线的每个空白间隔
 */
public float getDottedLineGap()
public void setDottedLineGap(float dottedLineGap)

/**
 * 虚线的颜色
 */
public int getDottedLineColor()
public void setDottedLineColor(int dottedLineColor)

/**
 * 坐标系 标记文字的颜色
 */
public int getMarkTextColor()
public void setMarkTextColor(int markTextColor)

/**
 * 最高最低心率的文字颜色
 */
public int getMaxMinTextColor()
public void setMaxMinTextColor(int maxMinTextColor)

/**
 * Y轴的最大值
 */
public float getMaxYValue()
public void setMaxYValue(float maxYValue)

/**
 * Y轴的最大值所占宽度
 */
public float getMaxYValueWidth()
public void setMaxYValueWidth(float maxYValueWidth)

/**
 * 坐标系 标记文字的大小
 */
public int getMarkTextSize()
public void setMarkTextSize(int markTextSize)

/**
 * 最高最低心率的文字大小
 */
public int getMaxMinTextSize()
public void setMaxMinTextSize(int maxMinTextSize)

/**
 * 最高最低心率框的 上下边距
 */
public float getMaxMinBlockTopBottomPadding()
public void setMaxMinBlockTopBottomPadding(float maxMinBlockTopBottomPadding)

/**
 * 最高最低心率框的 左右边距
 */
public float getMaxMinBlockLeftRightPadding()
public void setMaxMinBlockLeftRightPadding(float maxMinBlockLeftRightPadding)


/**
 * 最高最低心率框的 背景颜色
 */
public int getMaxMinBgColor()
public void setMaxMinBgColor(int maxMinBgColor)

/**
 * 最高最低心率框的 三角形高度
 */
public float getMaxMinTriAngleHeight()
public void setMaxMinTriAngleHeight(float maxMinTriAngleHeight)

/**
 * 最大最小值的显示格式  默认 "%d bmp"
 */
public String getMaxMinFormat()
public void setMaxMinFormat(String maxMinFormat)

/**
 * 每日心率折线图的颜色
 */
public int getDayHeartRateLineColor()
public void setDayHeartRateLineColor(int dayHeartRateLineColor)

/**
 * 每日心率折线图的宽度
 */
public float getDayHeartRateLineWidth()
public void setDayHeartRateLineWidth(float dayHeartRateLineWidth)

/**
 * 柱状图的线宽度
 */
public float getHistogramWidth()
public void setHistogramWidth(float histogramWidth)

/**
 * 柱状图的线的颜色
 */
public int getHistogramLineColor()
public void setHistogramLineColor(int histogramLineColor)

/**
 * 心跳警告的最大值
 */
public int getHeartRateWarnMax()
public void setHeartRateWarnMax(int heartRateWarnMax)

/**
 * 心跳警告的最小值
 */
public int getHeartRateWarnMin()
public void setHeartRateWarnMin(int heartRateWarnMin)

/**
 * 心跳警告线的颜色
 */
public int getHeartRateWarnLineColor()
public void setHeartRateWarnLineColor(int heartRateWarnLineColor)

/**
 * 心跳警告线的高度
 */
public int getHeartRateWarnLineHeight()
public void setHeartRateWarnLineHeight(int heartRateWarnLineHeight)
```

---
