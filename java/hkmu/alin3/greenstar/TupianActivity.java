package hkmu.alin3.greenstar;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class TupianActivity extends AppCompatActivity implements View.OnClickListener {
    private Button add;
    private Button qd;
    private Button clear;
    private Button paint_bai;
    private Button paint_hei;
    private ImageView imgshow;
    private String chooseClass;
    private RecyclerView datalist;
    private Spinner spinnertitle;
    private List<String> list=new ArrayList<>();
    private  String[] strlist=new String[]{"plug wire","metal container","beverage can","portable charger","envelope","leaflet","paper","paper packing",
            "paper box","toy","plastic bag","plastic packaging","cosmetic bottle","washbasin","plastic bottle","cigarette butts","vegetable",
            "tea","fishbone","egg","food plastic packaging"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ask for the permission
        String[] permissions = new String[]{
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        if (ActivityCompat.checkSelfPermission(this, permissions[0]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, permissions[1]) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, 1);
        }
        setContentView(R.layout.activity_tupian);
        initview();
    }
    float downx;
    float downy;
    float upx;
    float upy;
    private RecyclerAdapter recyclerAdapter;
    UploadRepository httpManager;
    private String photoPath = "";
    private void initview() {
//        add=findViewById(R.id.add);
        qd=findViewById(R.id.qd);
        datalist=findViewById(R.id.datalist);
//        title=findViewById(R.id.title);
        spinnertitle=findViewById(R.id.spinnertitle);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,strlist);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spinnertitle.setAdapter(adapter);
        spinnertitle.setSelection(0); // 设置最初显示的内容
        spinnertitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //获取Spinner控件的适配器
                ArrayAdapter<String> adapter = (ArrayAdapter<String>) adapterView.getAdapter();
                chooseClass = adapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        paint_hei=findViewById(R.id.paint_hei);
        clear=findViewById(R.id.clear);
//        add.setOnClickListener(this::onClick);
        paint_hei.setOnClickListener(this::onClick);
        clear.setOnClickListener(this::onClick);
        imgshow=findViewById(R.id.imgshow);
        paint_bai=findViewById(R.id.paint_bai);
        imgshow.setOnClickListener(this::onClick);
        paint_bai.setOnClickListener(this::onClick);
        qd.setOnClickListener(this::onClick);
        gameView= new GameView(this);
        datalist.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter =new RecyclerAdapter(TupianActivity.this);
        datalist.setAdapter(recyclerAdapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgshow:
//                 Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//调用android图库
////                     Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

                //开启系统图库
                Intent intent1 = new Intent(Intent.ACTION_PICK);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

                //开相机
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //intent选择器
                Intent chooser = Intent.createChooser(intent1, "choose method");
                chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{intent2});
                startActivityForResult(chooser, 0);
                if (gameView!=null) {
                    gameView.notifyView();
                }
                break;

            case R.id.clear:
                if ((ViewGroup) gameView.getParent() != null){
                    mCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                    mCanvas = new Canvas(mAlterBitmapcopy);
                    imgshow.invalidate();
                    gameView.notifyView();
                    ((ViewGroup) gameView.getParent()).removeView(gameView);
                    imgshow.setImageResource(R.mipmap.tu);
                }
                break;

            case R.id.paint_hei:
                mPaint.setColor(getResources().getColor(R.color.black));
                break;
            case R.id.paint_bai:
                mPaint.setColor(getResources().getColor(R.color.white));
                break;
            case R.id.qd:

                String ddd= SharedPreferencesUtils.getInstace().getString("zuobiao","");
                if (!ddd.equals("")){

                    String str=   SharedPreferencesUtils.getInstace().getString("zuobiao","");
                    StringBuffer stringBuffer=new StringBuffer(str);
                    stringBuffer.append("," + chooseClass);
                    SharedPreferencesUtils.getInstace().save("zuobiao",stringBuffer.toString());

                    System.out.println("---------------------: "  + SharedPreferencesUtils.getInstace().getString("zuobiao",""));
                    File xmlFile = writeXML(SharedPreferencesUtils.getInstace().getString("zuobiao",""));

                    list.add(SharedPreferencesUtils.getInstace().getString("zuobiao",""));
                    recyclerAdapter.setData(list);
                    SharedPreferencesUtils.getInstace().save("zuobiao","");
                    // [X[left,bottom], Y[left,bottom], X[right, top], Y[right, top], class]

                    System.out.println("upload: " + photoPath);
                    try {
                        if (uploadFile(xmlFile) == "-1"){
                            Toast.makeText(TupianActivity.this,"upload failed",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(TupianActivity.this,"upload successful",Toast.LENGTH_LONG).show();
                        };
                    } catch (IOException e) {
                        System.out.println("failed");
                    }
                }else {
                    Toast.makeText(TupianActivity.this,"请重新选择图片中的区域",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public String uploadFile(File xmlFile) throws IOException {
        UploadRepository repo = new UploadRepository(photoPath, xmlFile);
        repo.start();
        System.out.println("start upload......");
        return "successful";
    }

    public File writeXML(String info){
        String[] parts = info.split(",");
        String fileName = photoPath.substring(photoPath.lastIndexOf("/")+1);
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element annotationElement = document.createElement("annotation");
            document.appendChild(annotationElement);

            Element filenameElement = document.createElement("filename");

            filenameElement.appendChild(document.createTextNode(fileName));
            annotationElement.appendChild(filenameElement);

            Element pathElement = document.createElement("path");
            pathElement.appendChild(document.createTextNode(photoPath));
            annotationElement.appendChild(pathElement);
            Element sourceElement = document.createElement("source");
            annotationElement.appendChild(sourceElement);

            Element sizeElement = document.createElement("size");
            annotationElement.appendChild(sizeElement);
            Element widthElement = document.createElement("width");
            widthElement.appendChild(document.createTextNode("1080"));
            sizeElement.appendChild(widthElement);
            Element heightElement = document.createElement("height");
            heightElement.appendChild(document.createTextNode("1920"));
            sizeElement.appendChild(heightElement);
            Element depthElement = document.createElement("depth");
            depthElement.appendChild(document.createTextNode("3"));
            sizeElement.appendChild(depthElement);

            Element segmentedElement = document.createElement("segmented");
            segmentedElement.appendChild(document.createTextNode("0"));
            annotationElement.appendChild(segmentedElement);

            Element objectElement = document.createElement("object");
            annotationElement.appendChild(objectElement);
            Element nameElement = document.createElement("name");
            nameElement.appendChild(document.createTextNode(parts[parts.length - 1]));
            objectElement.appendChild(nameElement);

            Element bndboxElement = document.createElement("bndbox");
            objectElement.appendChild(bndboxElement);
            Element xminElement = document.createElement("xmin");
            xminElement.appendChild(document.createTextNode(parts[0]));
            bndboxElement.appendChild(xminElement);
            Element yminElement = document.createElement("ymin");
            yminElement.appendChild(document.createTextNode(parts[1]));
            bndboxElement.appendChild(yminElement);
            Element xmaxElement = document.createElement("xmax");
            xmaxElement.appendChild(document.createTextNode(parts[2]));
            bndboxElement.appendChild(xmaxElement);
            Element ymaxElement = document.createElement("ymax");
            ymaxElement.appendChild(document.createTextNode(parts[3]));
            bndboxElement.appendChild(ymaxElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            System.out.println("-------------------xml:" + photoPath);
            File file = new File(photoPath.split("\\.")[0] + ".xml");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
            return file;

        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    private Rect rect = new Rect(0,0,0,0);//手动绘制矩形
    private int StrokeWidth = 5;
    Bitmap mBitmap=null;
    Bitmap mAlterBitmap=null;
    Bitmap mAlterBitmapcopy=null;
    Canvas mCanvas;
    Paint mPaint;
    GameView  gameView;

    // get image from Gallery to activity.
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode ==RESULT_OK) {
            try {
                Uri imageFileUri = data.getData();
                System.out.println("------------------take a photo (uri):" + imageFileUri);
                if (imageFileUri==null){
                    Bundle bundle = data.getExtras();
                    //bitmap是拍照回来的照片
                    mBitmap = (Bitmap) bundle.get("data");

                    FileOutputStream fileOutputStream = null;
                    try {
                        // Get SD Root
                        String saveDir = Environment.getExternalStorageDirectory() + "/Green_star_photo";
                        // make a new directory
                        File dir = new File(saveDir);
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        // generate the file
                        System.out.println("------------------take a photo (generate the file):" + imageFileUri);
                        SimpleDateFormat t = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
                        String filename = "TP_" + (t.format(new Date())) + ".jpg";
                        File file = new File(saveDir, filename);

                        fileOutputStream = new FileOutputStream(file);
                        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                        photoPath  = file.getPath();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }else {
                    Display defaultDisplay = getWindowManager().getDefaultDisplay();
//                 float ddw = defaultDisplay.getWidth();
                    float ddw = 1080;
//                 float ddh = defaultDisplay.getHeight();
                    float ddh = 1920;
                    //BitmapFactory.Options options = new BitmapFactory().Options();//注意别写错了,options是静态内部类,需要直接使用外部类直接饮用
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    /**
                     * inJustDecodeBounds 如果设置为true，并不会把图像的数据完全解码，亦即decodeXyz()返回值为null，但是Options的outAbc中解出了图像的基本信息。
                     * 先设置inJustDecodeBounds= true，调用decodeFile()得到图像的基本信息,利用图像的宽度（或者高度，或综合）以及目标的宽度，得到inSampleSize值，
                     * 再设置inJustDecodeBounds= false，调用decodeFile()得到完整的图像数据。
                     * 先获取比例，再读入数据，如果欲读入大比例缩小的图，将显著的节约内容资源。有时候还会读入大量的缩略图，这效果就更明显了。
                     */
                    options.inJustDecodeBounds = true;
                    mBitmap = BitmapFactory.decodeStream(getContentResolver()
                            .openInputStream(imageFileUri), null, options);//需要权限android.permission.READ_EXTERNAL_STORAGE

                    //计算缩放因子
                    int heightRatio = (int) Math.ceil(options.outHeight/ddh);
                    int widthRatio = (int) Math.ceil(options.outWidth/ddw);
                    if (heightRatio > widthRatio) {
                        options.inSampleSize = heightRatio;
                    } else {
                        options.inSampleSize = widthRatio;
                    }
                    options.inSampleSize = 5;
                    Point size = new Point();
                    options.inJustDecodeBounds = false;
                    WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
                    wm.getDefaultDisplay().getSize(size);
                    mBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageFileUri), null, options);

                    // update choose photo path
                    Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    photoPath = cursor.getString(columnIndex);  //获取照片路径
                    cursor.close();
                }
                mAlterBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), mBitmap.getConfig());
                mAlterBitmapcopy = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), mBitmap.getConfig());
//                 mAlterBitmap = Bitmap.createBitmap(screenWidth, screenWidthy, mBitmap.getConfig());

                mCanvas = new Canvas(mAlterBitmap);
                mPaint = new Paint();
//                 mPaint.setColor(getResources().getColor(R.color.white));
//                 mPaint.setPathEffect(new DashPathEffect(new float[] {5, 5}, 0));
//                 mPaint.setStrokeWidth(5);
                Matrix matrix = new Matrix();//矩阵
//              //获取屏幕的尺寸
                //屏幕宽度

                mCanvas.drawBitmap(mBitmap, matrix, mPaint);
                imgshow.setImageBitmap(mAlterBitmap);
                ViewGroup.LayoutParams params2 = new ViewGroup.LayoutParams(imgshow.getWidth(), imgshow.getHeight());
                if (gameView.getParent()!=null) {
                    ((ViewGroup) gameView.getParent()).removeView(gameView);
                }
                addContentView(gameView,params2);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static Bitmap textAsBitmap(String text, float textSize) {

        TextPaint textPaint = new TextPaint();

        textPaint.setColor(Color.DKGRAY);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(textSize);
        textPaint.setAlpha(100);//透明度

        int spaceValue=150;//控制各个间距的大小

        StaticLayout layout = new StaticLayout(text, textPaint, 400,
                Layout.Alignment.ALIGN_NORMAL, 1.5f, 0.0f, true);
        Bitmap bitmap = Bitmap.createBitmap(layout.getWidth() + spaceValue,
                layout.getHeight() + spaceValue, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.translate(10, 10);
        layout.draw(canvas);
        return bitmap;
    }
}