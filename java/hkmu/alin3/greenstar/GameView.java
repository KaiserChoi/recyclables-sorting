package hkmu.alin3.greenstar;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class GameView  extends View {
    private static final int NONE_POINT = 0;
    int currentNEAR = NONE_POINT;
    //    声明Paint对象
    private int StrokeWidth = 5;
    Canvas canvas;
    Context mcontext;
    Rect rect = new Rect(0, 0, 0, 0);//手动绘制矩形


    public GameView(Context context) {
        super(context);
        //构建对象
        mcontext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        //设置无锯齿
        Paint mPaint = null;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
//        canvas.drawARGB(0, 255, 227, 0);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(StrokeWidth);
        mPaint.setColor(Color.WHITE);
        mPaint.setAlpha(100);

        canvas.save();

        canvas.drawRect(rect, mPaint);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
        paint.setTextSize(36);
        paint.setStrokeWidth(20);

        canvas.drawPoint(rect.left, rect.top, paint);
//        canvas.drawPoint(rect.left-(rect.right), rect.top, paint);
        canvas.drawPoint(rect.right + StrokeWidth, rect.bottom + StrokeWidth, paint);
        canvas.drawPoint(rect.left, rect.bottom + StrokeWidth, paint);
        canvas.drawPoint(rect.right + StrokeWidth, rect.top, paint);
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(rect.left+","+rect.right+","+rect.right + StrokeWidth+","+rect.bottom + StrokeWidth);
        SharedPreferencesUtils.getInstace().save("zuobiao",stringBuffer.toString());
//        canvas.drawPoint((rect.left +  rect.right)/2, rect.top, paint);


//        rect.left,rect.top,rect.right+StrokeWidth,rect.bottom+StrokeWidth
    }

    /**
     * 更新数据，重绘View
     */
    public void notifyView() {
        invalidate();
    }

    private int roundLength(int w, int max) {
        if (w < 0) {
            return 0;
        } else if (w > max) {
            return max;
        } else {
            return w;
        }
    }

    int x;
    int y;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int) event.getX();
        y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
              rect.right += StrokeWidth;
              rect.bottom += StrokeWidth;
               invalidate(rect);
                rect.left = x;
                rect.top = y;
                rect.right = rect.left;
                rect.bottom = rect.top;

            case MotionEvent.ACTION_MOVE:


//                if (currentNEAR == NONE_POINT) {
                    // do move...
//                    int canMove = canMove();
//
//                    int dx = x;
//                    int dy = y;
//
//                    int newL = roundLength(rect.left + dx, rect.right + StrokeWidth);
//                    int newR = roundLength(rect.right + dx, rect.right + StrokeWidth);
//                    int newT = roundLength(rect.top + dy, rect.bottom + StrokeWidth);
//                    int newB = roundLength(rect.bottom + dy, rect.bottom + StrokeWidth);
//
//                    switch (canMove) {
//                        case MOVE_H:
//                            if (!distortionInMove(rect, newL, rect.top, newR, rect.bottom)) {
//                                rect.set(newL, rect.top, newR, rect.bottom);
//                            }
//
//                            break;
//                        case MOVE_V:
//                            if (!distortionInMove(rect, rect.left, newT, rect.right, newB)) {
//                                rect.set(rect.left, newT, rect.right, newB);
//                            }
//
//                            break;
//                        case MOVE_VH:
////                            oval.inset(dx, dy);
//                            if (!distortionInMove(rect, newL, newT, newR, newB)) {
//                                rect.set(newL, newT, newR, newB);
//                            }
//
//                            break;
//                        case MOVE_ERROR:
//                            break;
//
//                    }

//                }
                Rect old =
                        new Rect(rect.left, rect.top, rect.right + StrokeWidth, rect.bottom + StrokeWidth);
                rect.right = x;
                rect.bottom = y;
                old.union(x, y);


                invalidate();

                break;

            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }

        return true;//处理了触摸信息，消息不再传递
    }
    public static final int MOVE_ERROR = -1024;
    public static final int MOVE_H = 90;
    public static final int MOVE_V = 90 + 1;
    public static final int MOVE_VH = 90 + 1 + 1;

    /**
     * 移动的时候是否变形了
     */
    private boolean distortionInMove(Rect oval, float cL, float cT, float cR, float cB) {
        return Math.abs((cR - cL) - (oval.right - oval.left)) > 0.001
                || Math.abs((cB - cT) - (oval.bottom - oval.top)) > 0.001;
    }

    int canMove() {
        if (touchEdge()) {
            return MOVE_ERROR;
        }
        if (!rect.contains(x, y)) {
            return MOVE_ERROR;
        }
        if (rect.right - rect.left == rect.right + StrokeWidth
                && rect.bottom - rect.top == rect.bottom + StrokeWidth) {
            return MOVE_ERROR;
        } else if (rect.right - rect.left == rect.right + StrokeWidth
                && rect.bottom - rect.top != rect.bottom + StrokeWidth) {
            return MOVE_V;
        } else if (rect.right - rect.left != rect.right + StrokeWidth
                && rect.bottom - rect.top == rect.bottom + StrokeWidth) {
            return MOVE_H;
        } else {
            return MOVE_VH;
        }
    }

    boolean touchEdge() {
        return rect.left < 0 || rect.right > rect.right + StrokeWidth
                || rect.top < 0 || rect.bottom > rect.bottom + StrokeWidth;
    }
}