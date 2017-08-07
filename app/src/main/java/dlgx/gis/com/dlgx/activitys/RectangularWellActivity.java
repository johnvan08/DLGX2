package dlgx.gis.com.dlgx.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import dlgx.gis.com.dlgx.R;
import dlgx.gis.com.dlgx.beans.GongJingXinxiBean;
import dlgx.gis.com.dlgx.com.yancy.imageselector.ImageConfig;
import dlgx.gis.com.dlgx.com.yancy.imageselector.ImageSelector;
import dlgx.gis.com.dlgx.com.yancy.imageselector.ImageSelectorActivity;
import dlgx.gis.com.dlgx.db.DatabaseAdapter;
import dlgx.gis.com.dlgx.db.PictureDatabase;
import dlgx.gis.com.dlgx.utils.GlideLoader;
import dlgx.gis.com.dlgx.utils.ImageUtil;
import dlgx.gis.com.dlgx.utils.PublicWay;
import dlgx.gis.com.dlgx.utils.Res;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 长方形井
 * Created by admin on 2017/4/21.
 */
public class RectangularWellActivity extends Activity implements View.OnClickListener  {

    @Bind(R.id.title_back1)
    ImageView titleBack1;
    @Bind(R.id.title_title)
    TextView titleTitle;
    @Bind(R.id.top_image)
    ImageView topImage;
    @Bind(R.id.top_center_text)
    LinearLayout topCenterText;
    @Bind(R.id.quit)
    ImageButton quit;
    @Bind(R.id.tv_adress_quanmao)
    TextView tvAdressQuanmao;
    @Bind(R.id.img_image)
    ImageView imgImage;
    @Bind(R.id.img_delete)
    ImageView imgDelete;
    @Bind(R.id.tv_adress_jingnei_quanmao)
    TextView tvAdressJingneiQuanmao;
    @Bind(R.id.img_image2)
    ImageView imgImage2;
    @Bind(R.id.img_delete2)
    ImageView imgDelete2;
    @Bind(R.id.tv_adress_bei)
    TextView tvAdressBei;
    @Bind(R.id.tv_imagename)
    TextView tvImagename;
    @Bind(R.id.img_image3)
    ImageView imgImage3;
    @Bind(R.id.img_delete3)
    ImageView imgDelete3;
    @Bind(R.id.img_etid)
    ImageView imgEtid;
    @Bind(R.id.tv_adress_dong)
    TextView tvAdressDong;
    @Bind(R.id.tv_imagenamedong)
    TextView tvImagenamedong;
    @Bind(R.id.img_image4)
    ImageView imgImage4;
    @Bind(R.id.img_delete4)
    ImageView imgDelete4;
    @Bind(R.id.img_dongetid)
    ImageView imgDongetid;
    @Bind(R.id.tv_adress_nan)
    TextView tvAdressNan;
    @Bind(R.id.tv_imagenamenan)
    TextView tvImagenamenan;
    @Bind(R.id.img_image5)
    ImageView imgImage5;
    @Bind(R.id.img_delete5)
    ImageView imgDelete5;
    @Bind(R.id.img_nanetid)
    ImageView imgNanetid;
    @Bind(R.id.tv_adress_xi)
    TextView tvAdressXi;
    @Bind(R.id.tv_imagenamexi)
    TextView tvImagenamexi;
    @Bind(R.id.img_image6)
    ImageView imgImage6;
    @Bind(R.id.img_textetid)
    ImageView imgTextetid;
    @Bind(R.id.img_xietid)
    ImageView imgXietid;
    @Bind(R.id.btn_up)
    Button btnUp;
    @Bind(R.id.sv)
    ScrollView sv;
    private PopupWindow pop = null;
    private LinearLayout ll_popup;
    public static Bitmap bimap;
    private String fileName;
    private Uri imageUri;

    public static final int REQUEST_CODE = 0x101;
    private static final int TAKE_PICTURE1 = 2;
    private static final int TAKE_PICTURE2 = 3;
    private static final int TAKE_PICTURE3 = 4;
    private static final int TAKE_PICTURE4 = 5;
    private static final int CROP_1_REQUEST = 10;
    private static final int CROP_2_REQUEST = 11;


    Bitmap photo;
    File cameraFile;


    private View parentView;


    private String img1Path = "", img2Path = "", img3Path = "", img4Path = "", img5Path = "", img6Path = "";
    ArrayList<String> path = new ArrayList<>();
    private ArrayList<String> upPath = new ArrayList<>();
    private String cPicState = "1";

    public static RectangularWellActivity RectangularWellActivity;

    private List<GongJingXinxiBean> address = new ArrayList<GongJingXinxiBean>();


    //查看图片
    Bitmap bp = null;
    ImageView imageview;
    float scaleWidth;
    float scaleHeight;
    boolean num = false;
    private String adress;
    private List ImList = new ArrayList();

    //打开指定文件夹的图片相关
    List<Object> listpath;
    public String[] allFiles;// 保存文件的路径
    // ///////////////////////////////////////////////////////////
    private String SCAN_PATH;//
    private static final String FILE_TYPE = "image/*";// 查找类型为image
    private String scanpath2;
    private PictureDatabase pictureDatabase;
    private ArrayList<Bitmap> bitmap_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Res.init(this);
        PublicWay.activityList.add(this);
        parentView = getLayoutInflater().inflate(R.layout.rectangularwellactivity_layout, null);
        setContentView(parentView);
        ButterKnife.bind(this);
        RectangularWellActivity = this;
        address = DatabaseAdapter.getIntance(getBaseContext()).queryJingAddress();
        pictureDatabase = new PictureDatabase(this);
        getPicture();
        getDataForSql();
        initView();

    }


    private void initView() {

        tvAdressBei.setText(adress);
        tvAdressDong.setText(adress);
        tvAdressNan.setText(adress);
        tvAdressXi.setText(adress);
        tvAdressQuanmao.setText(adress + "全貌");
        tvAdressJingneiQuanmao.setText(adress + "井内全貌");
        titleTitle.setText("长方井属性");
        //quit.setVisibility(View.GONE);
        imgEtid.setOnClickListener(this);
        titleBack1.setOnClickListener(this);
        imgImage.setOnClickListener(this);
        imgDongetid.setOnClickListener(this);
        imgNanetid.setOnClickListener(this);
        imgXietid.setOnClickListener(this);
        imgImage2.setOnClickListener(this);
        imgImage3.setOnClickListener(this);
        imgImage4.setOnClickListener(this);
        imgImage5.setOnClickListener(this);
        imgImage6.setOnClickListener(this);
        quit.setOnClickListener(this);
        btnUp.setOnClickListener(this);


    }

    //从数据库中显示照片
    private void getPicture() {
        //获取图片
        bitmap_list = pictureDatabase.getChangFangXingDrawable();
        if(bitmap_list==null|| bitmap_list.size()==0){
            bitmap_list = new ArrayList<>();
            bitmap_list.add(BitmapFactory.decodeResource(getResources(), R.drawable.picbtn));
            bitmap_list.add(BitmapFactory.decodeResource(getResources(), R.drawable.picbtn));
            bitmap_list.add(BitmapFactory.decodeResource(getResources(), R.drawable.picbtn));
            bitmap_list.add(BitmapFactory.decodeResource(getResources(), R.drawable.picbtn));
            bitmap_list.add(BitmapFactory.decodeResource(getResources(), R.drawable.picbtn));
            bitmap_list.add(BitmapFactory.decodeResource(getResources(), R.drawable.picbtn));
            Log.e("bitmap_list222",bitmap_list.size()+"");
        }
        ImList.add(imgImage);
        ImList.add(imgImage2);
        ImList.add(imgImage3);
        ImList.add(imgImage4);
        ImList.add(imgImage5);
        ImList.add(imgImage6);
        for (int i = 0; i < bitmap_list.size(); i++) {
            ((ImageView) ImList.get(i)).setImageBitmap(bitmap_list.get(i));
        }

    }


    //显示本地缓存
    private void getDataForSql() {
        if (address != null && address.toString().length() > 0) {
            for (int i = 0; i < address.size(); i++) {
                //编号
                fileName = address.get(i).getName();
                adress = address.get(i).getAdress();

            }
        }
    }



    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()) {
            case R.id.title_back1:
                //删除数据库图片
                pictureDatabase.getChangFangXingDelete();
                //往数据库插入新的图片
                for (int i = 0; i < bitmap_list.size(); i++) {
                    pictureDatabase.saveChangFangXingPhoto(bitmap_list.get(i));
                }
                finish();

                break;
            case R.id.quit:
                intent = new Intent(RectangularWellActivity.this, CameraActivity.class);
                startActivity(intent);
                break;
            case R.id.img_etid:
                String imagename = tvImagename.getText().toString().trim();
                intent = new Intent(RectangularWellActivity.this, CablePitNorthActivity.class);
                intent.putExtra("imagename",imagename);
                startActivity(intent);
                break;
            case R.id.img_dongetid:
                String dongimagename = tvImagenamedong.getText().toString().trim();
                intent = new Intent(RectangularWellActivity.this, CablePitNorthActivity.class);
                intent.putExtra("dongimagename", dongimagename );
                startActivity(intent);
                break;
            case R.id.img_nanetid:
                String imagenamenan = tvImagenamenan.getText().toString().trim();
                intent = new Intent(RectangularWellActivity.this, CablePitNorthActivity.class);
                intent.putExtra("imagenamenan",imagenamenan);
                startActivity(intent);
                break;
            case R.id.img_xietid:
                String imagenamexi = tvImagenamexi.getText().toString().trim();
                intent = new Intent(RectangularWellActivity.this, CablePitNorthActivity.class);
                intent.putExtra("imagenamexi",imagenamexi);
                startActivity(intent);
                break;
            case R.id.img_image:
                cPicState = "1";
                path.clear();
                if (img1Path.length() > 1) {
                    path.add(img1Path);
                }
                configImageLoader();
                break;
            case R.id.img_image2:
                cPicState = "2";
                path.clear();
                if (img2Path.length() > 1) {
                    path.add(img2Path);
                }

                configImageLoader();
                break;
            case R.id.img_image3://北
                cPicState = "3";
                beiInit();

                break;
            case R.id.img_image4://东
                cPicState = "4";
                dongInit();
                break;
            case R.id.img_image5://南
                cPicState = "5";
                nanInit();

                break;
            case R.id.img_image6://西
                cPicState = "6";
                xiInit();

                break;
            case R.id.btn_up://完成
               offlineSave();
                Log.e("sdsdsdsds1111","qqqqqqqq1111111");
                Toast.makeText(RectangularWellActivity.this, "长方形属性编辑完成!", Toast.LENGTH_SHORT).show();
                intent = new Intent(RectangularWellActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }


    public void beiInit() {

        pop = new PopupWindow(RectangularWellActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);

        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

        pop.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        pop.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button bt1 = (Button) view
                .findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) view
                .findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) view
                .findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beiPhoto();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        //从相册选择图片
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RectangularWellActivity.this,ChooseImageActivity.class);
                startActivityForResult(intent,12);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });

    }

    public void dongInit() {

        pop = new PopupWindow(RectangularWellActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);

        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

        pop.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        pop.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button bt1 = (Button) view
                .findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) view
                .findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) view
                .findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dongPhoto();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        //从相册选择图片
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                openAlbumIntent.setType("image/*");
//                startActivityForResult(openAlbumIntent, CHOOSE_PICTURE1);
                Intent intent = new Intent(RectangularWellActivity.this,ChooseImageActivity.class);
                startActivityForResult(intent,13);
                pop.dismiss();
                ll_popup.clearAnimation();

            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
    }


    public void nanInit() {

        pop = new PopupWindow(RectangularWellActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);

        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

        pop.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        pop.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button bt1 = (Button) view
                .findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) view
                .findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) view
                .findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nanPhoto();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        //从相册选择图片
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RectangularWellActivity.this,ChooseImageActivity.class);
                startActivityForResult(intent,14);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });


    }


    public void xiInit() {

        pop = new PopupWindow(RectangularWellActivity.this);

        View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);

        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

        pop.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        pop.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button bt1 = (Button) view
                .findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) view
                .findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) view
                .findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                xiPhoto();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        //从相册选择图片
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RectangularWellActivity.this,ChooseImageActivity.class);
                startActivityForResult(intent,15);
                pop.dismiss();
                ll_popup.clearAnimation();

            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });

    }


    public void beiPhoto() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, TAKE_PICTURE1);
    }

    public void dongPhoto() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, TAKE_PICTURE2);
    }

    public void nanPhoto() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, TAKE_PICTURE3);
    }

    public void xiPhoto() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, TAKE_PICTURE4);
    }


    public static String SDPATH = Environment.getExternalStorageDirectory() + "/电缆井/";


    public static File createSDDir(String dirName) throws IOException {
        File dir = new File(SDPATH + dirName);
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {

            System.out.println("createSDDir:" + dir.getAbsolutePath());
            System.out.println("createSDDir:" + dir.mkdir());
        }
        return dir;
    }

    public static boolean isFileExist(String fileName) {
        File file = new File(SDPATH + fileName);
        file.isFile();
        return file.exists();
    }

    /**
     * 配置 ImageConfig
     */
    private void configImageLoader() {
        ImageConfig imageConfig = new ImageConfig.Builder(
                // GlideLoader 可用自己用的缓存库
                new GlideLoader())
                // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
                .steepToolBarColor(getResources().getColor(R.color.blue))
                // 标题的背景颜色 （默认黑色）
                .titleBgColor(getResources().getColor(R.color.blue))
                // 提交按钮字体的颜色  （默认白色）
                .titleSubmitTextColor(getResources().getColor(R.color.white))
                // 标题颜色 （默认白色）
                .titleTextColor(getResources().getColor(R.color.white))
                // 开启多选   （默认为多选）  (单选 为 singleSelect)
//                .singleSelect()
                // 开启多选   （默认为多选）
                .mutiSelect()
                .crop()
                // 多选时的最大数量   （默认 9 张）
                .mutiSelectMaxSize(1)
                // 已选择的图片路径
                .pathList(path)
                // 拍照后存放的图片路径（默认 /temp/picture）
                .filePath("/电缆井")
                // 开启拍照功能 （默认开启）
                .showCamera()
                .requestCode(REQUEST_CODE)
                .build();

        ImageSelector.open(RectangularWellActivity, imageConfig);   // 开启图片选择器
    }

    //图片回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                //***********************照片选择**********************
                if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
                    List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
                    if (pathList != null && pathList.size() > 0) {
                        if (cPicState.equals("1")) {
                            img1Path = pathList.get(0);
                            ImageLoader.getInstance().displayImage("file://" + img1Path, imgImage);
                            Bitmap bmp = BitmapFactory.decodeByteArray(ImageUtil.decodeBitmap(img1Path), 0,ImageUtil.decodeBitmap(img1Path).length);
                            for (int i = 0; i < ImList.size(); i++) {
                                if (ImList.get(i).equals(imgImage)) {
                                    bitmap_list.set(i,bmp);
                                }
                            }
                        } else if (cPicState.equals("2")) {
                            img2Path = pathList.get(0);
                            ImageLoader.getInstance().displayImage("file://" + img2Path, imgImage2);
                            Bitmap bmp1 = BitmapFactory.decodeByteArray(ImageUtil.decodeBitmap(img2Path), 0,ImageUtil.decodeBitmap(img2Path).length);
                            for (int i = 0; i < ImList.size(); i++) {
                                if (ImList.get(i).equals(imgImage2)) {
                                    bitmap_list.set(i,bmp1);

                                }
                            }
                            Log.w("bitmap", String.valueOf(bmp1));

                        } else {
                            if (cPicState.equals("1")) {
                                img1Path = "";
                                imgImage.setImageResource(R.drawable.picbtn);
                                imgImage.setScaleType(ImageView.ScaleType.FIT_XY);
                            } else if (cPicState.equals("2")) {
                                img2Path = "";
                                imgImage2.setImageResource(R.drawable.picbtn);
                                imgImage2.setScaleType(ImageView.ScaleType.FIT_XY);
                            }
                        }
                        path.clear();
                        Log.e("onacresult-", "->" + img1Path + "   " + img2Path + "    " + img3Path);
                    }
                }
                break;
            case TAKE_PICTURE1:
                String imagename = tvImagename.getText().toString().trim();
                if (resultCode == RESULT_OK) {
//                    String imagePath = data.getStringExtra("data");
//                    Bitmap bm  = BitmapFactory.decodeByteArray(ImageUtil.decodeBitmap(imagePath), 0,ImageUtil.decodeBitmap(imagePath ).length);
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    try {
                        if (!isFileExist("")) {
                            File tempf = createSDDir("");
                        }
                        File f = new File(SDPATH, fileName + "-" + imagename + ".JPEG");
                        if (f.exists()) {
                            f.delete();
                        }
                        FileOutputStream out = new FileOutputStream(f);
                        bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        out.flush();
                        out.close();
                        imgImage3.setImageBitmap(bm);

                        for (int i = 0; i < ImList.size(); i++) {
                            if (ImList.get(i).equals(imgImage3)) {
                                bitmap_list.set(i,bm);
                            }
                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case TAKE_PICTURE2:
                String dongimagename = tvImagenamedong.getText().toString().trim();
                if (resultCode == RESULT_OK) {
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    try {
                        if (!isFileExist("")) {
                            File tempf = createSDDir("");
                        }
                        File f = new File(SDPATH, fileName + "-" + dongimagename + ".JPEG");
                        if (f.exists()) {
                            f.delete();
                        }
                        FileOutputStream out = new FileOutputStream(f);
                        bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        out.flush();
                        out.close();
                        imgImage4.setImageBitmap(bm);
                        for (int i = 0; i < ImList.size(); i++) {
                            if (ImList.get(i).equals(imgImage4)) {
                                bitmap_list.set(i,bm);
                            }
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case TAKE_PICTURE3:
                String nanimagename = tvImagenamenan.getText().toString().trim();
                if (resultCode == RESULT_OK) {
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    try {
                        if (!isFileExist("")) {
                            File tempf = createSDDir("");
                        }
                        File f = new File(SDPATH, fileName + "-" + nanimagename + ".JPEG");
                        if (f.exists()) {
                            f.delete();
                        }
                        FileOutputStream out = new FileOutputStream(f);
                        bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        imgImage5.setImageBitmap(bm);
                        for (int i = 0; i < ImList.size(); i++) {
                            if (ImList.get(i).equals(imgImage5)) {
                                bitmap_list.set(i,bm);
                            }
                        }
                        out.flush();
                        out.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case   TAKE_PICTURE4:
                String xiimagename = tvImagenamexi.getText().toString().trim();
                if (resultCode == RESULT_OK) {
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    try {
                        if (!isFileExist("")) {
                            File tempf = createSDDir("");
                        }
                        File f = new File(SDPATH, fileName + "-" + xiimagename + ".JPEG");
                        if (f.exists()) {
                            f.delete();
                        }
                        FileOutputStream out = new FileOutputStream(f);
                        bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        imgImage6.setImageBitmap(bm);
                        for (int i = 0; i < ImList.size(); i++) {
                            if (ImList.get(i).equals(imgImage6)) {
                                bitmap_list.set(i,bm);
                            }
                        }
                        out.flush();
                        out.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CROP_1_REQUEST:
                //startPhotoZoom(Uri.fromFile(cameraFile));
                break;
            case CROP_2_REQUEST:
                if (photo != null)
                    photo.recycle();
                photo = data.getExtras().getParcelable("data");
                imgImage3.setImageBitmap(photo);
                imgImage4.setImageBitmap(photo);
                imgImage5.setImageBitmap(photo);
                imgImage6.setImageBitmap(photo);

                break;
            case 12:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        String imagePath = data.getStringExtra("imagePath");
                        if(imagePath!=null) {
                            Bitmap bmp = BitmapFactory.decodeByteArray(ImageUtil.decodeBitmap(imagePath), 0,ImageUtil.decodeBitmap(imagePath).length);
                            imgImage3.setImageBitmap(bmp);
                            for (int i = 0; i < ImList.size(); i++) {
                                if (ImList.get(i).equals(imgImage3)) {
                                    bitmap_list.set(i,bmp);
                                }
                            }
                            Log.e("imagePath", imagePath);
                        }

                    }
                }

                break;
            case 13:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        String imagePath = data.getStringExtra("imagePath");
                        if(imagePath!=null) {
                            Bitmap bmp = BitmapFactory.decodeByteArray(ImageUtil.decodeBitmap(imagePath), 0,ImageUtil.decodeBitmap(imagePath).length);
                            imgImage4.setImageBitmap(bmp);
                            for (int i = 0; i < ImList.size(); i++) {
                                if (ImList.get(i).equals(imgImage4)) {
                                    bitmap_list.set(i,bmp);
                                }
                            }
                            Log.e("imagePath", imagePath);
                        }

                    }
                }
                break;
            case 14:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                            String imagePath = data.getStringExtra("imagePath");
                            if(imagePath!=null) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(ImageUtil.decodeBitmap(imagePath), 0,ImageUtil.decodeBitmap(imagePath).length);
                            imgImage5.setImageBitmap(bmp);
                                for (int i = 0; i < ImList.size(); i++) {
                                    if (ImList.get(i).equals(imgImage5)) {
                                        bitmap_list.set(i,bmp);
                                    }
                                }
                            Log.e("imagePath", imagePath);
                        }

                    }
                }

                break;
            case 15:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        String imagePath = data.getStringExtra("imagePath");
                        if(imagePath!=null) {
                            Bitmap bmp = BitmapFactory.decodeByteArray(ImageUtil.decodeBitmap(imagePath), 0,ImageUtil.decodeBitmap(imagePath).length);
                            imgImage6.setImageBitmap(bmp);
                            for (int i = 0; i < ImList.size(); i++) {
                                if (ImList.get(i).equals(imgImage6)) {
                                    bitmap_list.set(i,bmp);
                                }
                            }
                            Log.e("imagePath", imagePath);
                        }

                    }
                }

                break;

            default:
                break;
        }


    }

    /**
     * 离线存储罚单
     */
    private void offlineSave(){
        String jingleixing= getIntent().getStringExtra("jingleixing");
        GongJingXinxiBean beans = new GongJingXinxiBean();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String id = format.format(date);
        beans.setUuid(id);
        beans.setAdress(adress);
        beans.setJingxingzhuang(jingleixing);
        DatabaseAdapter.getIntance(getBaseContext()).insertJingAddress(beans);
        List<GongJingXinxiBean> list = DatabaseAdapter.getIntance(getBaseContext()).queryJingAddress();
        if(list!=null){
            Iterator<GongJingXinxiBean> iterator = list.iterator();
            while(iterator.hasNext()){
                GongJingXinxiBean a = iterator.next();
                DatabaseAdapter.getIntance(getBaseContext()).updeteJingAddress(a);
            }
        }

    }




}
