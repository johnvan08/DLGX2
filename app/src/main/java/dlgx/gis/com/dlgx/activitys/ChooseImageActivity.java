package dlgx.gis.com.dlgx.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import dlgx.gis.com.dlgx.R;
import dlgx.gis.com.dlgx.utils.Bimp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/5/15.
 */
public class ChooseImageActivity extends Activity implements MediaScannerConnection.MediaScannerConnectionClient {
    List<Object> listpath;
    public String[] allFiles;// 保存文件的路径
    // ///////////////////////////////////////////////////////////
    private String SCAN_PATH;//
    private static final String FILE_TYPE = "image/*";// 查找类型为image
    private String scanpath2;
    private MediaScannerConnection conn;// 获得一个MediaScannerConnection对象 用于查找文件
    private Button scanbutton;
    private GridView listView;
    private Myadapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooseimageactivity_layout);
        listpath = new ArrayList<Object>();
        listView = (GridView) this.findViewById(R.id.gridView1);
        File folder = new File("/sdcard/电缆井/");// 获得一个文件的路径
        if(folder!=null) {
            allFiles = folder.list();// 把所有的类型都罗列出来
        }
        if(allFiles!=null) {
            // 这个是图片文件的真实路径
            for (int i = 0; i < allFiles.length; i++) {
                scanpath2 = Environment.getExternalStorageDirectory().toString()
                        + "/电缆井/" + allFiles[i];
                listpath.add(scanpath2);
                System.out.println(" scanpath2  " + "--所有图片的路径---->>" + scanpath2);
            }

            SCAN_PATH = Environment.getExternalStorageDirectory().toString()
                    + "/电缆井/" + allFiles[0];// 真正的图片路径

            startScan();
        }else {
            Toast.makeText(ChooseImageActivity.this, "还没有可以选择的照片，去连拍后试试吧!", Toast.LENGTH_SHORT).show();
        }
        if(listpath.size()<0){
            Toast.makeText(ChooseImageActivity.this, "还没有可以选择的照片，去连拍后试试吧!", Toast.LENGTH_SHORT).show();
        }else {
             adapter = new Myadapter(getApplicationContext());
            adapter.setdate(listpath);
            adapter.update();
            listView.setAdapter(adapter);
            //adapter.notifyDataSetChanged();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listpath.size()>0) {
                        String imagePath = Environment.getExternalStorageDirectory().toString()
                                + "/电缆井/" + allFiles[ position];
                        Intent intr = new Intent();
                        intr.putExtra("imagePath", imagePath);
                        setResult(RESULT_OK, intr);
                        finish();
                }else {
                    Toast.makeText(ChooseImageActivity.this, "还没有可以选择的照片，去连拍后试试吧!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    // ////////////////////////////////////////
    // 启动扫描的对象
    private void startScan() {
        Log.d("Connected", "----->>success" + conn);
        if (conn != null) {
            conn.disconnect();// 先关了原来的扫描的对象
        }
        conn = new MediaScannerConnection(this, this);// 第一个参数是扫描的context
        // 第2个是interface
        conn.connect();// 连接
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // //////////////////////////////////////////////////////////////
    // 这个表示的我已经连接了
    @Override
    public void onMediaScannerConnected() {
        // TODO Auto-generated method stub
        conn.scanFile(SCAN_PATH, FILE_TYPE);// 第一个参数是 扫描的初始路径 第2个是表我要扫描的文件类型

    }

    // ////////////////////////////////////////////////////
    // 这个是扫描完成的
    @Override
    public void onScanCompleted(String path, Uri uri) {
        // TODO Auto-generated method stub

        try {
            Log.d("onScanCompleted", uri + "----->>success" + conn);
            System.out.println("URI " + uri);
            // if (uri != null) {
            // Intent intent = new Intent(Intent.ACTION_VIEW);// 表示的查看图片的intent
            // intent.setData(uri);// 用一个指定是我uri显示图片
            // startActivity(intent);//
            // }
        } finally {
            conn.disconnect();
            conn = null;
        }
    }

    public class Myadapter extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;
        private boolean shape;
        private List<Object> listpath;

        public boolean isShape() {
            return shape;
        }

        public void setShape(boolean shape) {
            this.shape = shape;
        }

        @SuppressWarnings("unused")
        private void setdate(List<Object> path) {
            // TODO Auto-generated method stub
            this.listpath = path;
        }

        public void update() {
            loading();
        }

        @SuppressWarnings("static-access")
        public Myadapter(Context context) {
            this.context = context;
            inflater = inflater.from(context);

        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return listpath.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return listpath.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item,
                        parent, false);
                holder = new ViewHolder();
                holder.image = (ImageView) convertView
                        .findViewById(R.id.imageView1);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
//            convertView = inflater.inflate(R.layout.item, null);
//            ImageView imageView = (ImageView) convertView
//                    .findViewById(R.id.imageView1);
            Bitmap bitmap;
            bitmap = BitmapFactory.decodeFile(
                    listpath.get(position).toString(), null);
            holder.image.setImageBitmap(bitmap);

            return convertView;
        }
    }

        public class ViewHolder {
            public ImageView image;
        }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    adapter.notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public void loading() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if (Bimp.max == listpath.size()) {
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                        break;
                    } else {
                        Bimp.max += 1;
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                }
            }
        }).start();
    }


    public String getString(String s) {
        String path = null;
        if (s == null)
            return "";
        for (int i = s.length() - 1; i > 0; i++) {
            s.charAt(i);
        }
        return path;
    }

    protected void onRestart() {
        adapter.update();
        super.onRestart();
    }



}
