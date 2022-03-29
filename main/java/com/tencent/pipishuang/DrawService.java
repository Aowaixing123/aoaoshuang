package com.tencent.pipishuang;


import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class DrawService extends Service {

    private boolean 绘制状态;//线程是否继续
    private boolean viewAdded = false;
    private View view;
    private SurfaceView sv;//绘制悬浮
    private WindowManager wm;//悬浮窗

    private WindowManager.LayoutParams params;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("RtlHardcoded")
    @Override
    public void onCreate() {




        view = View.inflate(getApplicationContext(), R.layout.draw, null);
        sv = view.findViewById(R.id.surface_view);
        sv.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        sv.getHolder().addCallback(new SurfaceHolder.Callback() {
            private DrawThread dt;
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                绘制状态 = true;
                dt = new DrawThread(surfaceHolder);
                dt.Flag = true;
                dt.start();
            }
            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                dt.Flag = false;
            }
        });
        wm = (WindowManager) this.getSystemService(WINDOW_SERVICE);


        params = new WindowManager.LayoutParams();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            // android 8.0及以后使用
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            params.flags = computeFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
            params.flags =WindowManager.LayoutParams.FLAG_SECURE+computeFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

        } else {
            // android 8.0以前使用
            params.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
            params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
            params.flags =WindowManager.LayoutParams.FLAG_SECURE+computeFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

        }

        // params.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;               // 设置window type

        // params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL|WindowManager.LayoutParams.FLAG_SECURE;  // 设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）

        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.format = PixelFormat.RGBA_8888;

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (viewAdded) {
            wm.updateViewLayout(view, params);
        } else {
            wm.addView(view, params);
            viewAdded = true;
        }
        Log.d("绘画","service中");
        return super.onStartCommand(intent, flags, startId);
    }

    //销毁服务
    @Override
    public void onDestroy() {

        if (viewAdded && wm != null) {
            wm.removeView(view);
        }
        super.onDestroy();
    }


    //适配低版本悬浮窗
    private int computeFlags(int curFlags) {
        boolean mTouchable = false;
        if (!mTouchable) {
            curFlags |= WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        } else {
            curFlags &= ~WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        }

        boolean mFocusable = false;
        if (!mFocusable) {
            curFlags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        } else {
            curFlags &= ~WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        }

        boolean mTouchModal = true;
        if (!mTouchModal) {
            curFlags |= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        } else {
            curFlags &= ~WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        }

        boolean mOutsideTouchable = false;
        if (mOutsideTouchable) {
            curFlags |= WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        } else {
            curFlags &= ~WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        }

        return curFlags;
    }


    class DrawThread extends Thread {
        private boolean Flag;//用于标注线程是否继续

        private SurfaceHolder surfaceHolder;

        //定义画笔
        Paint paint =new Paint();
        Paint paint1 = new Paint();
        Paint paint2 = new Paint();
        Paint paint3 = new Paint();
        Paint paint4 = new Paint();
        Paint paint5 = new Paint();
        Paint paint6 = new Paint();
        Paint paint7 = new Paint();
        Paint paint8 = new Paint();
        Paint paintrenshu = new Paint();
        Paint paintrenshu2 = new Paint();
        Paint ge = new Paint();
        Paint ge1 = new Paint();
        Paint 准心圆圈 = new Paint();
        Canvas canvas = null;
        float x = 0;//x轴
        float y = 0;//y轴
        float w = 0;//宽
        float qianw = 0;
        float h = 0;//高
        float M = 0;//高
        float hp = 0;//血条
        float kaijing = 0;//血条
        int pmx,pmy,th;
        float ai = 0;
        float zhengy = 0;
        float fm = 0;
        boolean htbool = false;
        float zhenrenc=0,renjic=0;


                 float HeadX, HeadY,// 头部
                ChestX, ChestY,// 胸部
               PelvisX,PelvisY,// 盆骨
                Left_ShoulderX, Left_ShoulderY,// 左肩
                Right_ShoulderX,Right_ShoulderY,// 右肩
                Left_ElbowX,Left_ElbowY,// 左手肘
               Right_ElbowX,Right_ElbowY,// 右手肘
               Left_WristX, Left_WristY,// 左手腕
                Right_WristX, Right_WristY,// 右手腕
                Left_ThighX, Left_ThighY,// 左大腿
                Right_ThighX, Right_ThighY,// 右大腿
                Left_KneeX, Left_KneeY,// 左膝盖
               Right_KneeX, Right_KneeY,// 右膝盖
               Left_AnkleX, Left_AnkleY,// 左脚腕
               Right_AnkleX,Right_AnkleY;  // 右脚腕



        private int mFPS = 0;
        private int mFPSCounter = 0;
        private long mFPSTime = 0;
        int hero,zy,bot;

        private  String getFileContent(File file) {
            String content = "";
            if (!file.isDirectory()) {
                try {
                    InputStream instream = new FileInputStream(file);
                    if (instream != null) {
                        InputStreamReader inputreader
                                = new InputStreamReader(instream, "UTF-8");
                        BufferedReader buffreader = new BufferedReader(inputreader);
                        String line = "";
                        while ((line = buffreader.readLine()) != null) {
                            content += line;
                        }
                        instream.close();//关闭输入流
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return content;
        }

        public DrawThread(SurfaceHolder h) {
            init();
            surfaceHolder = h;
            Flag = true;
        }

        private void init() {

            paint.setAntiAlias(true);
            paint.setColor(Color.rgb(0,255,255));
            paint.setStyle(Paint.Style.FILL);  //方框背景
            paint.setAlpha(70);

            paint.setDither(true);
            paint.setFilterBitmap(true);

            paint1.setAntiAlias(true);
            paint1.setColor(Color.RED);
            paint1.setStyle(Paint.Style.STROKE);  //边框
            paint1.setStrokeWidth(2f);
            paint1.setDither(true);
            paint1.setFilterBitmap(true);

            paint2.setAntiAlias(true);
            paint2.setColor(Color.rgb(0,255,0));  //血条
            paint2.setStyle(Paint.Style.FILL);
            paint2.setStrokeWidth(3f);
            paint2.setAlpha(100);

            paint3.setAntiAlias(true);
            paint3.setColor(Color.rgb(255, 255, 25));  //距离
            paint3.setStyle(Paint.Style.FILL);
            paint3.setStrokeWidth(10f);
            paint3.setTextSize(50);//字体大小
            paint3.setAlpha(255);

            paint4.setAntiAlias(true);
            paint4.setColor(Color.rgb(255,127,0));  //血量
            paint4.setStyle(Paint.Style.STROKE);
            paint4.setAlpha(170);
            paint4.setStrokeWidth(4f);

            //血条边框
            paint5.setColor(Color.rgb(0, 0, 0));
            paint5.setStyle(Paint.Style.STROKE);  //边框
            paint5.setStrokeWidth(2f);

            //血条实心
            paint6.setColor(Color.rgb(248, 248, 255));
            paint6.setStyle(Paint.Style.FILL);
            paint6.setStrokeWidth(10f);
            paint6.setAlpha(200);

            paint7.setColor(Color.GREEN);//倒地敌人线条
            paint7.setStyle(Paint.Style.STROKE);
            paint7.setStrokeWidth(3f);
            paint7.setColor(0xFFFF9C00);



            paint8.setAntiAlias(true);
            paint8.setColor(Color.MAGENTA);  //血量
            paint8.setStyle(Paint.Style.FILL);
            paint8.setTextSize(25);
            paint8.setAlpha(255);

            paintrenshu.setAntiAlias(true);
            paintrenshu.setColor(Color.GREEN);  //shul
            paintrenshu.setStyle(Paint.Style.FILL);
            paintrenshu.setTextSize(25);
            paintrenshu.setAlpha(255);


            paintrenshu2.setAntiAlias(true);
            paintrenshu2.setColor(Color.RED);  //shul
            paintrenshu2.setStyle(Paint.Style.FILL);
            paintrenshu2.setTextSize(25);
            paintrenshu2.setAlpha(255);

            ge.setAntiAlias(true);
            ge.setStyle(Paint.Style.STROKE);
            ge.setColor(Color.WHITE);
            ge.setStrokeWidth(3f);

            ge1.setAntiAlias(true);
            ge1.setStyle(Paint.Style.STROKE);
            ge1.setColor(Color.GREEN);
            ge1.setStrokeWidth(5f);

        }



        private void Draw() {
            if (绘制状态) {


            canvas.drawColor(Color.TRANSPARENT,PorterDuff.Mode.CLEAR);

                File file = new File("/storage/emulated/0/Android/data/com.rekoo.pubgm/files/b.log");//坐标输出路径
                if (file == null)
                    return;



                String bb = getFileContent(file);

                if (MainActivity.screenx>MainActivity.screeny)
                {
                    pmx=MainActivity.screenx;
                    pmy=MainActivity.screeny;
                }
                else if(MainActivity.screenx<MainActivity.screeny)
                {
                    th=MainActivity.screenx;
                    pmx=MainActivity.screeny;
                    pmy=th;
                }
                qianw = w;
                String bbb = getFileContent(file);
                String[] Concent = bbb.split(";");
                //String[] Concent = string.split(";");
                for (int i = 0; i < Concent.length; i++) {
                    String[] coor = Concent[i].split(",");
                    try {
                        x = Float.parseFloat(coor[0]);//y轴
                        y = Float.parseFloat(coor[1]);//x轴
                        w = Float.parseFloat(coor[2]);//宽
                        h = Float.parseFloat(coor[3]);//高
                        M = Float.parseFloat(coor[4]);//距离
                        hp = Float.parseFloat(coor[5]);//血量
                        kaijing =  Float.parseFloat(coor[6]);//血量
                        ai =  Float.parseFloat(coor[7]);//血量
                        zhengy =  Float.parseFloat(coor[8]);//血量

//                        HeadX =Float.parseFloat(coor[9]); HeadY=Float.parseFloat(coor[10]);// 头部
//
//                        ChestX=Float.parseFloat(coor[11]); ChestY=Float.parseFloat(coor[12]);// 胸部
//                        Left_ThighX=Float.parseFloat(coor[27]); Left_ThighY=Float.parseFloat(coor[28]);// 左大腿
//
//                        Right_ThighX=Float.parseFloat(coor[29]); Right_ThighY=Float.parseFloat(coor[30]);// 右大腿
//
//                        Left_KneeX=Float.parseFloat(coor[31]); Left_KneeY=Float.parseFloat(coor[32]);// 左膝盖
//
//                        Right_KneeX=Float.parseFloat(coor[33]); Right_KneeY=Float.parseFloat(coor[34]);// 右膝盖
//
//                        Left_AnkleX=Float.parseFloat(coor[35]); Left_AnkleY=Float.parseFloat(coor[36]);// 左脚腕
//
//                        Right_AnkleX=Float.parseFloat(coor[37]);Right_AnkleY=Float.parseFloat(coor[38]);  // 右脚腕



                    } catch (Exception v) {
                        v.printStackTrace();
                    }
                    int m = (int)M;





                    //if(m<20) fm = m;
                    if(kaijing>2.5)
                        fm = w*0.165f*m/kaijing;
                        else fm = w*0.062f*m;

                    htbool = false;
                    if(hp>0 && h>0 && m>2 &&zhengy>0) htbool = true;

                     File stop = new File("/sdcard/ascj/stop");
                    if(!stop.exists() && MainActivity.fanhui(7)) canvas.drawText(  "正常", 100 , 50, paintrenshu);
                    else {
                        canvas.drawText("异常", 100, 100, paint8);
                      //  MainActivity.执行二进制("/data/local/ascj");
                    }



                    if (MainActivity.fanhui(4) && htbool) {
                        if(ai==0)
                            canvas.drawText("队伍["+(int)zhengy+"]",x - fm,y - w-10,paintrenshu2);
                        else  canvas.drawText("人机"+ai,x - fm,y - w-10,paintrenshu);
                        canvas.drawRoundRect(x -fm-100, y - w-35, (x-fm-100)+(2*fm+w+100)*hp/100,y - w-5,50,50 ,paint2);
                        canvas.drawRoundRect(x - fm-100, y - w-35,x + fm+w ,y - w-5, 50,50,paint4);



                    }

                     if (MainActivity.fanhui(1) && htbool) {

                         canvas.drawRect(x - fm, y - w, x + w - fm, y + w, paint1);//方框填充色
                         canvas.drawRect(x - fm, y - w, x + w - fm, y + w, paint);//方框填充色

//                             canvas.drawRect(x - fm, y - w, x + w - fm, y + w, paint1);//方框填充色
//                             canvas.drawRect(x - fm, y - w, x + w - fm, y + w, paint);//方框填充色


                    }



                    if (MainActivity.fanhui(2)) {

                        //canvas.drawLine(100+4,,x,y,paint8);
                        canvas.drawText("周围人数："+zhenrenc,2280/2f-300,50, paintrenshu2);
                        canvas.drawText("周围ai："+renjic,2280/2f+100,50, paintrenshu);

                        // canvas.drawLine(x / 2, 0, x + w / 2, y - w, paint8);//射线
                    }

                    if (MainActivity.fanhui(3) && htbool) {

                        canvas.drawText(  m + "M", x  -fm+w*0.2f , y + w + 20, paint8);//显示距离


                    }



                    if (MainActivity.fanhui(5) && htbool) {

                        canvas.drawCircle(HeadX-fm,HeadY,0.15f*w,ge1);

                        //pipi zou shou bu feng
                        canvas.drawLine(ChestX-fm,ChestY,Left_ShoulderX-fm,Left_ShoulderY,ge1);
                        canvas.drawLine(Left_ShoulderX-fm,Left_ShoulderY,Left_ElbowX-fm,Left_ElbowY,ge1);
                        canvas.drawLine(Left_ElbowX-fm,Left_ElbowY,Left_WristX-fm,Left_WristY,ge1);
                        //you shou
                        canvas.drawLine(ChestX-fm,ChestY,Right_ShoulderX-fm,Right_ShoulderY,ge1);
                        canvas.drawLine(Right_ShoulderX-fm,Right_ShoulderY,Right_ElbowX-fm,Right_ElbowY,ge1);
                        canvas.drawLine(Right_ElbowX-fm,Right_ElbowY,Right_WristX-fm,Right_WristY,ge1);

                        //身体轴皮皮
                        canvas.drawLine(ChestX-fm,ChestY,PelvisX-fm,PelvisY,ge1);

                        //腿
                        canvas.drawLine(PelvisX-fm,PelvisY,Left_ThighX-fm,Left_ThighY,ge1);
                        canvas.drawLine(Left_ThighX-fm,Left_ThighY,Left_KneeX-fm,Left_KneeY,ge1);
                        canvas.drawLine(Left_KneeX-fm,Left_KneeY,Left_AnkleX-fm,Left_AnkleY,ge1);

                        canvas.drawLine(PelvisX-fm,PelvisY,Right_ThighX-fm,Right_ThighY,ge1);
                        canvas.drawLine(Right_ThighX-fm,Right_ThighY,Right_KneeX-fm,Right_KneeY,ge1);
                        canvas.drawLine(Right_KneeX-fm,Right_KneeY,Right_AnkleX-fm,Right_AnkleY,ge1);


                    }

                /*    if (MainActivity.返回(6)) {
                        if (mz.contains("shu")||mz.contains("kuansi")||mz.contains("qiangbi")||mz.contains("PickUp")||mz.contains("Rail"))
                        {
                            if (w>0&&x > 0 && y > 0 && x < pmx && y < pmy){

                                canvas.drawText(mz+" ["+M+"m]",x+w/2,y,物资描边);
                                canvas.drawText(mz+" ["+M+"m]",x+w/2,y,物资名字);
   }                         }
 }

*/




                }
            }
            surfaceHolder.unlockCanvasAndPost(canvas);
        }



        @Override
        public void run() {

            Thread syncTask = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (Flag) {
                        try {
                            canvas = surfaceHolder.lockCanvas();
                            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

                        } catch (Exception e) {
                            break;
                        }
                        Draw();


                    }
                }
            });
            syncTask.start();
        }
    }


}
