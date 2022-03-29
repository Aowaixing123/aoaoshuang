package com.tencent.pipishuang;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import java.io.IOException;
import android.view.WindowManager;
import android.view.Window;
import android.view.View;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import android.content.Context;
import android.view.Display;
import android.graphics.Point;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;
import android.os.PowerManager;
import android.provider.Settings;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.widget.ImageButton;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.View.OnClickListener;
import android.os.Build;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Button;
import android.util.Log;

import androidx.annotation.RequiresApi;


import java.io.FileOutputStream;
import java.io.InputStream;


public class MainActivity extends Activity {
    private CheckBox fk1, sx1, jl1, hp1, bd1, wz1;


    private static boolean fk1状态, sx1状态, jl1状态, hp1状态, bd1状态, wz1状态;

    private CheckBox nc1, nc2, nc3, nc4, nc5, nc6, nc7, nc8;
    private static boolean nc1状态, nc2状态, nc3状态, nc4状态, nc5状态, nc6状态, nc7状态, nc8状态;


    public static boolean fk, sx, jl, hp, bd, wz;//绘制状态

    private Switch 初始化;//初始化
    private static boolean 初始化状态;//初始化状态


    private View displayMenu;
    private LayoutInflater inflater;


    private WindowManager mwMenu;

    private View dis;


    public static int screenx, screeny;


    public static int pmx, pmy;//屏幕宽和高

    private WindowManager.LayoutParams lparam;


    private ImageButton mbutton;

    private boolean xfq = false;//悬浮球初始化
    private boolean xfc = false;  //悬浮窗初始化
    private int mTouchStartX, mTouchStartY;//手指按下时坐标

    private boolean isMove = false;

    private WindowManager mwindow;


    private WindowManager.LayoutParams mparam;

    private boolean panduan;

    private int aX, aY;//按下x按下y

    private boolean yd = false;//移动

    private Intent DrawService = null;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);///这个是隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//屏幕常亮
        setContentView(R.layout.activity_main);//封包
        Mobilephonemodel();//显示手机型号
        Superprivilege();//申请root权限
        store();//储存权限
        hqfbl();//获取分辨率
        cell();//电池优化
        display();//申请悬浮窗权限
        inflater = inflater.from(this);
        写出二进制();

    }


    private void Mobilephonemodel() {
//获取手机型号显示到ID未gg的文本上
        TextView text = (TextView) findViewById((R.id.gg));
        StringBuilder phoneInfo = new StringBuilder();
        phoneInfo.append("当前机型：" + android.os.Build.MODEL);
        text.setText(phoneInfo);
        phoneInfo.append("\nProduct: " + android.os.Build.PRODUCT);
        phoneInfo.append("\nUSER: " + android.os.Build.USER);
        phoneInfo.append("\nMANUFACTURER: " + android.os.Build.MANUFACTURER);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void opyx(View view) {

        //监听悬浮窗开启

        if (panduan == true) {
            //判断悬浮球是否开启
            Toast.makeText(getApplication(), "悬浮窗已经开启啦", Toast.LENGTH_SHORT).show();
            //如果开启提示已经打开
        } else {
            //这里是没有开启就开启悬浮球

            goyrs();//开启悬浮球
            cell();//电量优化
        }


    }


    private void 写出二进制() {

        yydscn(this, getFilesDir() + "/assets", "1");


        //你的绘制二进制

    }

    public void tickling(View view) {

        //监听反馈群

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqapi://card/show_pslcard?src_type=internal&version=1&uin=867947726&card_type=group&source=qrcode")));


    }

    public void tickling1(View view) {

        //重置游客信息
        RunShell("su -c rm -rf /data/data/com.rekoo.pubgm/databases");
        RunShell("su -c rm -rf /sdcard/Android/data/com.rekoo.pubgm/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/SaveGames/loginInfoFile.json");
        RunShell("su -c rm -rf /sdcard/Android/data/com.rekoo.pubgm/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Intermediate");
        RunShell("su -c rm -rf /sdcard/Android/data/com.rekoo.pubgm/files/UE4Game/ShadowTrackerExtra/ShadowTrackerExtra/Saved/SaveGames");
        RunShell("su -c rm -rf /data/data/com.rekoo.pubgm/shared_prefs/device_id.xml");
        提示内容("重置成功");

    }


    public void tickl(View view) {

        //监听隐藏辅助


    }


    public void tickf(View view) {

        //监听关于软件


    }


    public void Superprivilege() {
        //申请root权限
        try {
            Runtime.getRuntime().exec("su");
        } catch (IOException e) {
        }
    }


    public boolean 文件检测(String strFile) {
        //检测文件
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void 创建文件(String path) {
        //新建一个File类型的成员变量，传入文件名路径。
        File mFile = new File(path);
        //判断文件是否存在，存在就删除
        if (mFile.exists()) {

        }
        try {
            //创建文件
            mFile.createNewFile();
            //给一个吐司提示，显示创建成功
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void 创建文件夹(String path) {
        //新建一个File，传入文件夹目录
        File file = new File(path);
        //判断文件夹是否存在，如果不存在就创建，否则不创建
        if (!file.exists()) {
            //通过file的mkdirs()方法创建目录中包含却不存在的文件夹
            file.mkdirs();
        }

    }

    public static void xieru(String 路径, String 内容) {
        try {
            FileWriter fw = new FileWriter(路径);
            fw.write(内容);
            fw.close();
        } catch (IOException e) {
        }
    }

    /*
     * 读取文件内容
     */
    public static String 读取文件(String path) {
        String str = "";
        try {
            File urlFile = new File(path);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(urlFile), "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String mimeTypeLine = null;
            while ((mimeTypeLine = br.readLine()) != null) {
                str = str + mimeTypeLine;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static int huqux(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }


    /**
     * 获取屏幕高度(px)
     */
    public static int huquy(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;

    }

    public void hqfbl() {
        WindowManager windowManager = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point outPoint = new Point();
        display.getRealSize(outPoint);
        pmx = outPoint.x;
        pmy = outPoint.y;
        String x = String.valueOf(pmx);
        String y = String.valueOf(pmy);
        xieru("/storage/emulated/0/Y", x);
        xieru("/storage/emulated/0/X", y);
        new File("/storage/emulated/0/Y", "r");
        new File("/storage/emulated/0/X", "r");
    }


    private long firstBackTime;

    //监听在退出
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - firstBackTime > 2000) {
            Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
            firstBackTime = System.currentTimeMillis();
            return;
        }

        super.onBackPressed();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void display() {
        //申请悬浮窗权限

        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "请开启悬浮窗权限", Toast.LENGTH_LONG).show();

            startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 0);
        }
    }

    //获取悬浮窗回调
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (!Settings.canDrawOverlays(this)) {
                display();
                cell();


                //Toast.makeText(this, "获取权限失败了哦", Toast.LENGTH_SHORT).show();
            } else {
                cell();

//防止辅助掉后台
                Toast.makeText(this, "获取权限成功", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void cell() {
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);

        boolean hasIgnored = powerManager.isIgnoringBatteryOptimizations(this.getPackageName());
        if (!hasIgnored) {
            Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + this.getPackageName()));
            startActivity(intent);
        }
    }


    public void store() {
        boolean isGranted = true;
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            if (this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
            if (this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
            if (!isGranted) {
                this.requestPermissions(
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission
                                .ACCESS_FINE_LOCATION,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        102);
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void goyrs() {

        mwindow = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        lparam = new WindowManager.LayoutParams();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            lparam.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            lparam.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        if (Settings.canDrawOverlays(this)) {
            mbutton = new ImageButton(getApplicationContext());
            mbutton.setBackgroundResource(R.drawable.xfc);///悬浮球的图片
            mbutton.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            isMove = false;
                            mTouchStartX = (int) event.getRawX();
                            mTouchStartY = (int) event.getRawY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            int nowX = (int) event.getRawX();
                            int nowY = (int) event.getRawY();
                            int movedX = nowX - mTouchStartX;
                            int movedY = nowY - mTouchStartY;
                            if (movedX > 5 || movedY > 5) {
                                isMove = true;
                            }
                            mTouchStartX = nowX;
                            mTouchStartY = nowY;
                            lparam.x += movedX;
                            lparam.y += movedY;
                            mwindow.updateViewLayout(mbutton, lparam);
                            break;
                        case MotionEvent.ACTION_UP:
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            break;
                    }
                    return isMove;
                }
            });
            mbutton.setOnClickListener(new OnClickListener()//悬浮球的单击事件
            {
                @Override
                public void onClick(View v) {

                    go();//加载悬浮窗
                    xfc = true;//悬浮窗状态
                    mwindow.removeView(mbutton);//关闭悬浮球
                    xfq = false;//悬浮球状态
                }
            });
            lparam.format = PixelFormat.RGBA_8888;
            lparam.gravity = Gravity.LEFT;
            lparam.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            lparam.width = 120;
            //宽度
            lparam.height = 120;
            //高度
            if (mparam == null) {
                lparam.x = 300;//悬浮球起始x
                lparam.y = 0;//悬浮球起始y
            } else {
                lparam.x = mparam.x;
                lparam.y = mparam.y;//y
            }
            mwindow.addView(mbutton, lparam);//加载悬浮球

            xfq = true;//判断悬浮球状态
            panduan = true;//判断悬浮窗状态
        } else {
            display();//申请悬浮窗
            Toast.makeText(this, "开启失败", Toast.LENGTH_LONG).show();
            panduan = false;//判断悬浮窗状态
        }
    }


    private void go() {
        mwMenu = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);

        mparam = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mparam.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mparam.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        displayMenu = inflater.inflate(R.layout.xfc, null);
        //绑定悬浮窗
        dis = displayMenu.findViewById(R.id.menu);
        mparam.format = PixelFormat.RGBA_8888;
        mparam.gravity = Gravity.LEFT;
        mparam.flags = WindowManager.LayoutParams.FLAG_SECURE+WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;//NOT_FOCUSABLE;
        mparam.width = WindowManager.LayoutParams.WRAP_CONTENT;
        //宽度
        mparam.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //高度
        mparam.x = lparam.x;//x
        mparam.y = lparam.y;//y
        mwMenu.addView(dis, mparam);

        //悬浮窗长按监听
        LinearLayout ll1 = displayMenu.findViewById(R.id.menu);
        ll1.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://单击
                        yd = false;
                        aX = (int) event.getRawX();//按下x
                        aY = (int) event.getRawY();//按下y
                        break;
                    case MotionEvent.ACTION_MOVE://拖动
                        int nowX = (int) event.getRawX();
                        int nowY = (int) event.getRawY();
                        int movedX = nowX - aX;//x
                        int movedY = nowY - aY;//y
                        if (movedX > 5 || movedY > 5) {
                            yd = true;//移动
                        }
                        aX = nowX;
                        aY = nowY;
                        lparam.x += movedX;
                        lparam.y += movedY;
                        mwindow.updateViewLayout(displayMenu, mparam);
                        break;
                    case MotionEvent.ACTION_UP://抬起
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }
                return yd;//移动
            }
        });

        Button 缩小 = displayMenu.findViewById(R.id.缩小);
        Button 关闭 = displayMenu.findViewById(R.id.关闭);

        缩小.setOnClickListener(new OnClickListener() {


            //绑定悬浮窗按钮

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                goyrs();//显示主悬浮窗
                mwMenu.removeView(dis);//移除菜单
                xfc = false;//菜单状态
            }
        });

        关闭.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mwMenu.removeView(dis);//移除菜单
                xfc = false;//菜单状态
                Set.setA(0);//退出悬浮窗退出绘图
                stopService(DrawService);//关闭绘图
                //  关闭悬浮球();
                panduan = false;
            }
        });


        初始化 = (Switch) displayMenu.findViewById(R.id.初始化);

        fk1 = (CheckBox) displayMenu.findViewById(R.id.fk1);
        sx1 = (CheckBox) displayMenu.findViewById(R.id.sx1);
        jl1 = (CheckBox) displayMenu.findViewById(R.id.jl1);
        hp1 = (CheckBox) displayMenu.findViewById(R.id.hp1);
        bd1 = (CheckBox) displayMenu.findViewById(R.id.血量);

        //开关绑定ID



        /*选择框绑定ID

         功能一局一开用不到监听
         */
        刷新按钮状态();
        初始化.setOnCheckedChangeListener(new DuxMian());

        fk1.setOnCheckedChangeListener(new DuxMian());
        sx1.setOnCheckedChangeListener(new DuxMian());
        jl1.setOnCheckedChangeListener(new DuxMian());
        hp1.setOnCheckedChangeListener(new DuxMian());
        bd1.setOnCheckedChangeListener(new DuxMian());

    }


    public static boolean fanhui(int count) {
        switch (count) {
            case 1:
                return fk;///方框
            case 2:
                return sx;//数据
            case 3:
                return jl;///距离
            case 4:
                return hp;///方框
            case 5:
                return bd;//guge
            case 6:
                return wz;//wz
            case 7:
                return 初始化状态;
            default:
                return false;
        }
    }


    private void 刷新按钮状态() {
        初始化.setChecked(初始化状态);
        fk1.setChecked(fk1状态);
        sx1.setChecked(sx1状态);
        jl1.setChecked(jl1状态);
        hp1.setChecked(hp1状态);
        bd1.setChecked(bd1状态);

    }


   static public void 执行二进制(String 路径) {  //getFilesDir去点
        RunShell("chmod -R 777 "  + 路径);
        RunShell("chmod 777 "  + 路径);
        RunShell("su -c chmod 777 "  + 路径);
        RunShell("su -c chmod -R 777 "  + 路径);
        RunShell("su -c " + 路径);
        RunShell(" " + 路径);
    }


    //执行二进制("/assets/cn1");


    class DuxMian implements OnCheckedChangeListener {


        public void onCheckedChanged(CompoundButton p1, boolean p2) {
            switch (p1.getId()) {

                case R.id.初始化: {
                    if (p2) {
                        Set.setA(1);
                        File stop = new File("/sdcard/ascj/stop");
                        if(stop.exists())
                        //执行二进制("/data/local/ascj");
                        DrawService = new Intent(getApplicationContext(), DrawService.class);
                        startService(DrawService);//开启
                        Log.d("绘画","main中");
                        初始化状态 = true;

                        RunShell("su -c touch /storage/emulated/0/Android/data/com.rekoo.pubgm/files/stop");

                    } else {
                        //  执行二进制("/assets/2f");
                        Set.setA(0);//关闭
                        RunShell("su -c rm -rf touch /storage/emulated/0/Android/data/com.rekoo.pubgm/files/stop");
                        stopService(DrawService);//关闭绘制页面
                        初始化状态 = false;

                    }
                }
                break;

                case R.id.fk1: {
                    if (p2) {


                        fk1状态 = true;
                        fk = true;

                        提示内容("显示方框开启成功");

                    } else {
                        //  执行二进制("/assets/2f");
                        fk = false;
                        提示内容("显示方框关闭成功");
                        fk1状态 = false;

                    }
                }
                break;

                case R.id.sx1: {
                    if (p2) {

                        提示内容("显示射线开启成功");
                        sx = true;

                        sx1状态 = true;


                    } else {

                        提示内容("显示射线关闭成功");
                        sx = false;
                        sx1状态 = false;

                    }
                }
                break;
                case R.id.jl1: {
                    if (p2) {


                        jl = true;
                        提示内容("显示距离开启成功");
                        jl1状态 = true;


                    } else {

                        提示内容("显示距离关闭成功");
                        jl = false;
                        jl1状态 = false;

                    }
                }
                break;

                case R.id.hp1: {
                    if (p2) {


                        hp = true;
                        提示内容("显示血量开启成功");
                        hp1状态 = true;


                    } else {

                        提示内容("显示血量关闭成功");
                        hp = false;
                        hp1状态 = false;

                    }
                }
                break;


                case R.id.血量:{
                    if (p2) {


                        bd = true;
                        提示内容("骨骼绘制开启成功");
                        bd1状态 = true;


                    } else {

                        提示内容("骨骼绘制关闭成功");
                        bd = false;
                        bd1状态 = false;

                    }

                }
                break;
            }
        }

    }
    //提示内容


    private void 提示内容(String 内容) {
        Toast.makeText(this, 内容, Toast.LENGTH_LONG).show();
    }


    public static boolean yydscn(Context context, String outPath, String fileName) {
        File file = new File(outPath);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("--Method--", "copyAssetsSingleFile: cannot create directory.");
                return false;
            }
        }
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            File outFile = new File(file, fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            // Transfer bytes from inputStream to fileOutputStream
            byte[] buffer = new byte[1024];
            int byteRead;
            while (-1 != (byteRead = inputStream.read(buffer))) {
                fileOutputStream.write(buffer, 0, byteRead);
            }
            inputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void RunShell(String shell) {
        String s = shell;

        try {
            Runtime.getRuntime().exec(s, null, null);//执行
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}