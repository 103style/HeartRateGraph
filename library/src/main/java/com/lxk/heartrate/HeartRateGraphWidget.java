package com.lxk.heartrate;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;



import com.lxk.heartrate.bean.HeartRateBean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author https://github.com/103style
 * @date 2020/8/11 11:24
 */
public class HeartRateGraphWidget extends View {

    /**
     * 时间轴显示类型
     */
    public static final int DAY = 0;
    public static final int WEEK = 1;
    public static final int MONTH = 2;
    public static final int YEAR = 3;
    private static final String TAG = "HeartRateGraphWidget";
    private final int MAX_RATE = 250;
    /**
     * 当时选中的时间轴类型
     */
    private int curShowType = DAY;
    /**
     * 时间轴时间文字集合
     */
    private List<String> timeStrings;
    /**
     * 虚线效果
     */
    private DashPathEffect dashPathEffect;
    /**
     * 虚线实线路径，每天的折线图路径， 最大心率路径， 最小心率路径
     */
    private Path dashPath, dayHeartRatePath, dayHeartRateShaderPath, maxRatePath, minRatePath;
    /**
     * 画笔， 线/文字/心率折线图
     */
    private Paint linePaint, textPaint, dayHeartRatePaint, maxMinPaint, histogramPaint, selectPaint;
    /**
     * 实线 虚线的高度
     */
    private float solidLineHeight, dottedLineHeight;
    /**
     * 虚线的 实 虚 长度
     */
    private float dottedLineWidth, dottedLineGap;
    /**
     * 虚线 实线的颜色
     */
    private int dottedLineColor, solidLineColor;
    /**
     * 坐标系 标记文字的颜色
     * 最高最低心率的文字颜色
     */
    private int markTextColor, MaxMinTextColor;
    /**
     * 坐标系 标记文字的大小
     * 最高最低心率的文字大小
     */
    private int markTextSize, MaxMinTextSize;
    /**
     * 最高最低心率框的 上下左右边距， 三角形高度，显示格式
     */
    private float maxMinBlockTopBottomPadding;
    private float maxMinBlockLeftRightPadding;
    private int maxMinBgColor;
    private float maxMinTriAngleHeight;
    private String maxMinFormat = "%d bmp";
    /**
     * 时间轴文字上边距
     */
    private float timeStringTopMargin;

    /**
     * 每日心率折线图的颜色和宽度
     */
    private int dayHeartRateLineColor;
    private float dayHeartRateLineWidth;
    private int dayHeartRateMaxShow;
    /**
     * 心率线的间隔
     */
    private float lineGapHeight;
    /**
     * 每天显示的心率渐变效果
     */
    private LinearGradient linearGradient;
    /**
     * 柱状图的宽度
     */
    private float histogramWidth;
    /**
     * 柱状图的线 和 点的颜色
     */
    private int histogramLineColor, histogramDotColor;

    /**
     * 选中item线的宽度和颜色
     */
    private float selectLineWidth;
    private int selectLineColor;

    /**
     * 是否显示每天的心率折线图的阴影
     * 是否显示最大最小值
     * 是否显示测试数据
     */
    private boolean showDayShader, showMaxMin, showTestData;
    /**
     * 每天的心率折线图的阴影的开始和结束颜色
     */
    private int shaderColorStart, shaderColorEnd;

    /**
     * 心率数据集合
     */
    private List<List<HeartRateBean>> dataList;

    /**
     * 触摸的位置
     */
    private float touchedX = -1F, touchedY = -1F;
    /**
     * 内容左上角坐标和 宽高
     */
    private float contentLeft, contentTop, contentWidth, contentHeight;

    /**
     * 选中某一时间的回调
     */
    private onItemSelectCallback onItemSelectCallback;
    /**
     * 选中item的 x坐标
     */
    private float selectedPointX = -1;

    public HeartRateGraphWidget(Context context) {
        this(context, null);
    }

    public HeartRateGraphWidget(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeartRateGraphWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(LAYER_TYPE_HARDWARE, null);
        initAttrs(context, attrs);
        init();

        if (showTestData) {
            timeStrings = getTestList();
            getTestDataList(getMaxShowItemCount());
        }
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HeartRateGraphWidget);
        solidLineHeight = ta.getDimensionPixelOffset(R.styleable.HeartRateGraphWidget_solid_line_height, 10);
        solidLineColor = ta.getColor(R.styleable.HeartRateGraphWidget_solid_line_color, 0x26000000);

        dottedLineHeight = ta.getDimensionPixelOffset(R.styleable.HeartRateGraphWidget_dotted_line_height, 10);
        dottedLineWidth = ta.getDimensionPixelOffset(R.styleable.HeartRateGraphWidget_dotted_line_width, 30);
        dottedLineGap = ta.getDimensionPixelOffset(R.styleable.HeartRateGraphWidget_dotted_line_gap, 10);
        dottedLineColor = ta.getColor(R.styleable.HeartRateGraphWidget_dotted_line_color, 0x26000000);

        markTextColor = ta.getColor(R.styleable.HeartRateGraphWidget_mark_text_color, 0x61000000);
        markTextSize = ta.getDimensionPixelOffset(R.styleable.HeartRateGraphWidget_mark_text_size, DensityUtils.dpToPx(context, 10));

        timeStringTopMargin = ta.getDimensionPixelOffset(R.styleable.HeartRateGraphWidget_time_string_top_margin, 10);

        MaxMinTextColor = ta.getColor(R.styleable.HeartRateGraphWidget_max_min_text_color, 0xDE000000);
        MaxMinTextSize = ta.getDimensionPixelOffset(R.styleable.HeartRateGraphWidget_max_min_text_size, DensityUtils.dpToPx(context, 10));
        maxMinBlockTopBottomPadding = ta.getDimensionPixelOffset(R.styleable.HeartRateGraphWidget_max_min_text_top_bottom_padding, 5);
        maxMinBlockLeftRightPadding = ta.getDimensionPixelOffset(R.styleable.HeartRateGraphWidget_max_min_text_left_right_padding, 10);
        maxMinTriAngleHeight = ta.getDimensionPixelOffset(R.styleable.HeartRateGraphWidget_max_min_text_triangle_height, 20);
        maxMinBgColor = ta.getColor(R.styleable.HeartRateGraphWidget_max_min_bg_color, 0X33398EFF);
        if (ta.hasValue(R.styleable.HeartRateGraphWidget_max_min_text_format)) {
            maxMinFormat = ta.getString(R.styleable.HeartRateGraphWidget_max_min_text_format);
        }

        dayHeartRateLineColor = ta.getColor(R.styleable.HeartRateGraphWidget_day_heart_rate_line_color, 0xFFF33838);
        dayHeartRateLineWidth = ta.getDimensionPixelOffset(R.styleable.HeartRateGraphWidget_day_heart_rate_line_with, 5);
        dayHeartRateMaxShow = ta.getInt(R.styleable.HeartRateGraphWidget_day_heart_rate_max_show, 120);

        histogramWidth = ta.getDimensionPixelOffset(R.styleable.HeartRateGraphWidget_histogram_with, DensityUtils.dpToPx(context, 4));
        histogramLineColor = ta.getColor(R.styleable.HeartRateGraphWidget_histogram_line_color, 0xFFD8D8D8);
        histogramDotColor = ta.getColor(R.styleable.HeartRateGraphWidget_histogram_dot_color, 0xFF43B6F4);

        selectLineColor = ta.getColor(R.styleable.HeartRateGraphWidget_select_line_color, 0xFFF33838);
        selectLineWidth = ta.getDimensionPixelOffset(R.styleable.HeartRateGraphWidget_select_line_width, 10);

        showDayShader = ta.getBoolean(R.styleable.HeartRateGraphWidget_show_day_shader, true);
        showMaxMin = ta.getBoolean(R.styleable.HeartRateGraphWidget_show_max_min, true);
        showTestData = ta.getBoolean(R.styleable.HeartRateGraphWidget_show_test_data, false);

        shaderColorStart = ta.getColor(R.styleable.HeartRateGraphWidget_shader_color_start, 0xFFF33838);
        shaderColorEnd = ta.getColor(R.styleable.HeartRateGraphWidget_shader_color_end, 0x10FF0000);
        ta.recycle();
    }

    private void init() {
        dashPath = new Path();
        dayHeartRatePath = new Path();
        dayHeartRateShaderPath = new Path();
        maxRatePath = new Path();
        minRatePath = new Path();

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        maxMinPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        selectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectPaint.setColor(selectLineColor);
        selectPaint.setStrokeWidth(selectLineWidth);
        selectPaint.setStyle(Paint.Style.STROKE);

        histogramPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        histogramPaint.setStrokeCap(Paint.Cap.ROUND);

        dayHeartRatePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dayHeartRatePaint.setStrokeJoin(Paint.Join.ROUND);

        dashPathEffect = new DashPathEffect(new float[]{dottedLineWidth, dottedLineGap}, 0);
    }

    /**
     * 设置选中的回调
     */
    public void setOnItemSelectCallback(HeartRateGraphWidget.onItemSelectCallback onItemSelectCallback) {
        this.onItemSelectCallback = onItemSelectCallback;
    }

    /**
     * @param type     {@link ShowType}显示的类型
     * @param times    时间轴文字
     * @param dataList 心率数据
     */
    public void updateHeartRateShow(@ShowType int type, List<String> times, List<List<HeartRateBean>> dataList) {
        reset();
        curShowType = type;
        timeStrings = times;
        this.dataList = dataList;
        postInvalidate();
    }

    /**
     * 更新心率数据
     *
     * @param dataList 心率数据
     */
    public void updateHeartRateData(List<List<HeartRateBean>> dataList) {
        reset();
        this.dataList = dataList;
        postInvalidate();
    }

    void reset() {
        touchedX = touchedY = -1;
        selectedPointX = -1;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        contentLeft = getPaddingLeft();
        contentTop = getPaddingTop();
        contentWidth = getMeasuredWidth() - contentLeft - getPaddingRight();
        contentHeight = getMeasuredHeight() - contentTop - getPaddingBottom();
        lineGapHeight = contentHeight / 3F;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (dataList == null || dataList.size() == 0) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                selectedPointX = -1;
                touchedX = event.getX();
                touchedY = event.getY();
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                performClick();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLineAndMarkText(canvas);
        drawHeartRateGraph(canvas);
    }

    /**
     * 画标记线 和 标记文字
     */
    private void drawLineAndMarkText(Canvas canvas) {
        linePaint.setPathEffect(dashPathEffect);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(dottedLineColor);
        linePaint.setStrokeWidth(dottedLineHeight);

        textPaint.setTextSize(markTextSize);
        textPaint.setColor(markTextColor);
        float textHeight = textPaint.descent() - textPaint.ascent();
        float textY = textHeight - textPaint.descent() + dottedLineHeight;
        float y = contentTop;
        dashPath.reset();
        for (int i = 0; i < 3; i++) {
            dashPath.moveTo(contentLeft, y);
            dashPath.lineTo(contentLeft + contentWidth, y);
            canvas.drawText(String.valueOf(MAX_RATE - 100 * i), contentLeft, textY + y, textPaint);
            y += lineGapHeight;
        }
        canvas.drawPath(dashPath, linePaint);
        linePaint.setPathEffect(null);
        linePaint.setColor(solidLineColor);
        y -= lineGapHeight / 2F;
        linePaint.setStrokeWidth(solidLineHeight);
        canvas.drawLine(contentLeft, y, contentLeft + contentWidth, y, linePaint);
        drawTimeLineText(canvas, y + timeStringTopMargin, textY);
    }

    /**
     * 时间轴文字
     */
    private void drawTimeLineText(Canvas canvas, float y, float textY) {
        if (timeStrings == null || timeStrings.size() == 0) {
            Log.e(TAG, "----- please set the timeStrings -----");
            return;
        }
        float perWidth = contentWidth / timeStrings.size();
        for (int i = 0; i < timeStrings.size(); i++) {
            float x = contentLeft + perWidth * i + (perWidth - textPaint.measureText(timeStrings.get(i))) / 2;
            canvas.drawText(timeStrings.get(i), x, y + textY, textPaint);
        }
    }

    /**
     * 画心率图
     */
    private void drawHeartRateGraph(Canvas canvas) {
        if (dataList == null || dataList.size() == 0) {
            Log.e(TAG, "----- please input the  heart rate dataList -----");
            return;
        }
        if (curShowType == DAY) {
            drawPerDayHeartPate(canvas);
        } else {
            //按天 月显示的柱状图
            drawHistogram(canvas);
        }
        drawSelectedItem(canvas);
    }

    private void drawSelectedItem(Canvas canvas) {
        if (selectedPointX == -1) {
            return;
        }
        canvas.drawLine(selectedPointX, contentTop, selectedPointX, contentTop + lineGapHeight * 2.5F, selectPaint);
    }

    /**
     * 获取当前显示模式下的最多显示个数
     */
    private int getMaxShowItemCount() {
        int count;
        switch (curShowType) {
            case DAY:
                count = dayHeartRateMaxShow;
                break;
            case WEEK:
                count = 7;
                break;
            case MONTH:
                count = 30;
                break;
            default:
                count = 12;
                break;
        }
        return count;
    }

    /**
     * 画每天的心率折线图
     */
    private void drawPerDayHeartPate(Canvas canvas) {
        dayHeartRatePaint.setColor(dayHeartRateLineColor);
        dayHeartRatePaint.setStyle(Paint.Style.STROKE);
        dayHeartRatePaint.setStrokeWidth(dayHeartRateLineWidth);
        dayHeartRatePath.reset();
        dayHeartRateShaderPath.reset();
        int count = getMaxShowItemCount();
        float perX = contentWidth / (1 + count);
        float preY = lineGapHeight * 2.5F / MAX_RATE;
        float startY = lineGapHeight * 2.5F + contentTop;
        float startX = contentLeft + perX / 2F;
        int max = 0, min = Integer.MAX_VALUE;
        float[] maxPosition = new float[2];
        float[] minPosition = new float[2];
        for (List<HeartRateBean> beans : dataList) {
            for (int i = 0; i < beans.size(); i++) {
                float x = startX + perX * (beans.get(i).index - 1);
                checkSelectItem(beans.get(i), x, perX / 2);
                int rate = beans.get(i).heartRate;
                float y = startY - rate * preY;
                if (i == 0) {
                    dayHeartRateShaderPath.moveTo(x, startY);
                    dayHeartRatePath.moveTo(x, y);
                } else {
                    dayHeartRatePath.lineTo(x, y);
                }
                dayHeartRateShaderPath.lineTo(x, y);
                if (rate > max) {
                    max = rate;
                    maxPosition[0] = x;
                    maxPosition[1] = y;
                }
                if (rate < min) {
                    min = rate;
                    minPosition[0] = x;
                    minPosition[1] = y;
                }
                if (i + 1 == beans.size()) {
                    dayHeartRateShaderPath.lineTo(x, startY);
                }
            }
        }
        canvas.drawPath(dayHeartRatePath, dayHeartRatePaint);
        if (showDayShader) {
            linearGradient = new LinearGradient(getMeasuredWidth() / 2F, maxPosition[1],
                    getMeasuredWidth() / 2F, startY,
                    new int[]{shaderColorStart, shaderColorEnd},
                    null, Shader.TileMode.CLAMP);
            dayHeartRatePaint.setShader(linearGradient);
            dayHeartRatePaint.setStyle(Paint.Style.FILL);
            canvas.drawPath(dayHeartRateShaderPath, dayHeartRatePaint);
            dayHeartRatePaint.setShader(null);
        }
        drawMaxMin(max, min, maxPosition, minPosition, canvas);
    }

    /**
     * 检查是否时选中的item
     *
     * @param heartRateBean 包装的心率数据
     * @param x             当前的 x 坐标
     * @param range         有效的区间
     */
    private void checkSelectItem(HeartRateBean heartRateBean, float x, float range) {
        if (touchedX == -1) {
            return;
        }
        if (Math.abs(x - touchedX) < range) {
            selectedPointX = x;
            if (onItemSelectCallback != null) {
                onItemSelectCallback.onItemSelected(heartRateBean);
            }
        }
    }

    /**
     * 绘制 最大最小区域和文字
     */
    private void drawMaxMin(int max, int min, float[] maxPosition, float[] minPosition, Canvas canvas) {
        if (!showMaxMin) {
            return;
        }
        String maxRateString = String.format(maxMinFormat, max);
        String minRateString = String.format(maxMinFormat, min);
        maxMinPaint.setTextSize(MaxMinTextSize);

        float maxRateLen = maxMinPaint.measureText(maxRateString);
        float maxRateTotalLen = maxRateLen + maxMinBlockLeftRightPadding * 2;
        float minRateLen = maxMinPaint.measureText(minRateString);
        float minRateTotalLen = minRateLen + maxMinBlockLeftRightPadding * 2;
        float height = maxMinPaint.descent() - maxMinPaint.ascent() + maxMinBlockTopBottomPadding * 2;
        maxMinPaint.setStyle(Paint.Style.FILL);
        maxMinPaint.setColor(maxMinBgColor);
        configMaxMinRatePath(maxRatePath, maxPosition, maxRateTotalLen, height, true);
        canvas.drawPath(maxRatePath, maxMinPaint);
        configMaxMinRatePath(minRatePath, minPosition, minRateTotalLen, height, false);
        canvas.drawPath(minRatePath, maxMinPaint);
        maxMinPaint.setColor(MaxMinTextColor);
        maxMinPaint.setStyle(Paint.Style.STROKE);
        canvas.drawText(maxRateString, maxPosition[0] - maxRateLen / 2,
                maxPosition[1] - maxMinTriAngleHeight - height + maxMinBlockTopBottomPadding - maxMinPaint.ascent(),
                maxMinPaint);
        canvas.drawText(minRateString, minPosition[0] - minRateLen / 2,
                minPosition[1] + maxMinTriAngleHeight - maxMinPaint.ascent() + maxMinBlockTopBottomPadding,
                maxMinPaint);
    }

    /**
     * 配置最大最小的区域路径
     */
    private void configMaxMinRatePath(Path path, float[] pos, float maxRateLen, float height, boolean isMax) {
        path.reset();
        height = isMax ? height : -height;
        float triangleHeight = isMax ? maxMinTriAngleHeight : -maxMinTriAngleHeight;
        path.moveTo(pos[0], pos[1]);
        path.lineTo(pos[0] - maxMinTriAngleHeight, pos[1] - triangleHeight);
        path.lineTo(pos[0] - maxRateLen / 2, pos[1] - triangleHeight);
        path.lineTo(pos[0] - maxRateLen / 2, pos[1] - triangleHeight - height);
        path.lineTo(pos[0] + maxRateLen / 2, pos[1] - triangleHeight - height);
        path.lineTo(pos[0] + maxRateLen / 2, pos[1] - triangleHeight);
        path.lineTo(pos[0] + maxMinTriAngleHeight, pos[1] - triangleHeight);
        path.close();
    }


    /**
     * 画 按天/月显示的柱状图
     */
    private void drawHistogram(Canvas canvas) {
        int count = getMaxShowItemCount();
        float startY = getPaddingTop() + 2.5F * lineGapHeight;
        float perWidth = (getMeasuredWidth() - getPaddingRight() - getPaddingLeft()) * 1.0F / count;
        float preHeight = 2.5F * lineGapHeight / MAX_RATE;

        int max = 0, min = Integer.MAX_VALUE;
        float[] maxPosition = new float[2];
        float[] minPosition = new float[2];

        for (List<HeartRateBean> beans : dataList) {
            for (HeartRateBean bean : beans) {
                float x = getPaddingLeft() + perWidth * bean.index - perWidth / 2;
                checkSelectItem(bean, x, histogramWidth);
                float top = startY - bean.max * preHeight;
                float bottom = startY - bean.min * preHeight;
                if (bean.max > max) {
                    max = bean.max;
                    maxPosition[0] = x;
                    maxPosition[1] = top;
                }
                if (bean.min < min) {
                    min = bean.min;
                    minPosition[0] = x;
                    minPosition[1] = bottom;
                }
                histogramPaint.setStyle(Paint.Style.STROKE);
                histogramPaint.setStrokeWidth(histogramWidth);
                histogramPaint.setColor(histogramLineColor);
                canvas.drawLine(x, top + histogramWidth, x, bottom - histogramWidth, histogramPaint);
                histogramPaint.setColor(histogramDotColor);
                histogramPaint.setStyle(Paint.Style.FILL);
                float radius = histogramWidth / 2;
                canvas.drawCircle(x, top + radius, radius, histogramPaint);
                canvas.drawCircle(x, bottom - radius, radius, histogramPaint);
            }
        }
        drawMaxMin(max, min, maxPosition, minPosition, canvas);
    }


    private List<String> getTestList() {
        return new ArrayList<>(Arrays.asList("0", "6", "12", "18", "0"));
    }


    private void getTestDataList(int count) {
        dataList = new ArrayList<>();
        if (curShowType == DAY) {
            List<HeartRateBean> list = new ArrayList<>();
            for (int j = 0; j < getMaxShowItemCount(); j++) {
                if (j == getMaxShowItemCount() / 2) {
                    list.add(new HeartRateBean(120, j + 1));
                } else {
                    list.add(new HeartRateBean(60 + (int) (Math.random() * 20), j + 1));
                }
            }
            dataList.add(list);
        } else {
            List<HeartRateBean> list = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                list.add(new HeartRateBean(90 + 5 * i, 60 + 3 * i, i + 1));
            }
            dataList.add(list);
        }
    }

    @IntDef({DAY, WEEK, MONTH, YEAR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ShowType {

    }

    /**
     * 选中莫一个时间点时的回调
     */
    public interface onItemSelectCallback {
        /**
         * 选中莫一个时间点时
         *
         * @param heartRateBean 包装数据
         */
        void onItemSelected(HeartRateBean heartRateBean);
    }

    public class Builder {
        private int curShowType;
        private List<String> timeStrings;
        private float selectLineWidth;
        private int selectLineColor;
        private List<List<HeartRateBean>> dataList;
    }
}
