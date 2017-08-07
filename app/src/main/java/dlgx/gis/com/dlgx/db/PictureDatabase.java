package dlgx.gis.com.dlgx.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.BaseColumns;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 将图片存入数据库
 * Created by admin on 2017/5/8.
 */
public class PictureDatabase extends SQLiteOpenHelper {


    //数据库的字段
        public static class PictureColumns implements BaseColumns {
            public static final String PICTURE = "picture";
        }

        private Context mContext;

        //数据库版本号
        private static final int DATABASE_Version = 1;
        //表名
        private static final String TABLE_NAME = "fenpicture";
    //环网柜表名
    private static final String HUANGWANGGUIPICTURE_NAME = "huangwangguiPicture";
    //箱变表名
    private static final String XIANGBIAN_NAME = "xiangbianPicture";
    //配电室表名
    private static final String PEIDIANSHI_NAME = "peidianshiPicture";
    //变压器表名
    private static final String  BIANYAQI_NAME = "bianyaqiPicture";
    //杆塔表名
    private static final String  GANTA_NAME = "gantaPicture";
    //变电站表名
    private static final String  BIANDIANZHAN_NAME = "biandianzhanPicture";
    //开关站表名
    private static final String  KAIGUANZHAN_NAME = "kaiguanzhanPicture";
    //掩埋井表名
    private static final String  YANMAIJING_NAME = "yanmaijingPicture";
    //隐患表名
    private static final String  YINGHUAN_NAME = "yinghuanPicture";
    //长方形井表名
    private static final String  CHANGFANGJING_NAME = "changfangjingPicture";
    //异形井表名
    private static final String  YIXINGJING_NAME = "yixingjingPicture";
    //北表名
    private static final String  BEI_NAME = "beiPicture";


    //数据库存储路径
    public static final String DB_NAME = PictureHelper.DB_DIR + File.separator
            + "picture.db";

        //创建数据库
        public PictureDatabase(Context context) {
            super(context, DB_NAME, null, DATABASE_Version);
            this.mContext = context;
        }

        //创建表并初始化表
        @Override
        public void onCreate (SQLiteDatabase db) {
            String fenpicture = "Create table " + TABLE_NAME + "(" + BaseColumns._ID
                    + " integer primary key autoincrement," + PictureColumns.PICTURE
                    + " blob not null);";

            String huangwangguiPicture=  "Create table " + HUANGWANGGUIPICTURE_NAME + "(" + BaseColumns._ID
                    + " integer primary key autoincrement," + PictureColumns.PICTURE
                    + " blob not null);";

            String xiangbianPicture=  "Create table " + XIANGBIAN_NAME + "(" + BaseColumns._ID
                    + " integer primary key autoincrement," + PictureColumns.PICTURE
                    + " blob not null);";

            String peidianshiPicture=  "Create table " + PEIDIANSHI_NAME + "(" + BaseColumns._ID
                    + " integer primary key autoincrement," + PictureColumns.PICTURE
                    + " blob not null);";

            String bianyaqiPicture=  "Create table " +  BIANYAQI_NAME  + "(" + BaseColumns._ID
                    + " integer primary key autoincrement," + PictureColumns.PICTURE
                    + " blob not null);";

            String gantaPicture =  "Create table "  +  GANTA_NAME  + "(" + BaseColumns._ID
                    + " integer primary key autoincrement," + PictureColumns.PICTURE
                    + " blob not null);";

            String biandianzhanPicture =  "Create table " +  BIANDIANZHAN_NAME  + "(" + BaseColumns._ID
                    + " integer primary key autoincrement," + PictureColumns.PICTURE
                    + " blob not null);";

            String kaiguanzhanPicture =  "Create table "  +  KAIGUANZHAN_NAME  + "(" + BaseColumns._ID
                    + " integer primary key autoincrement," + PictureColumns.PICTURE
                    + " blob not null);";

            String yanmaijingPicture =  "Create table " +  YANMAIJING_NAME  + "(" + BaseColumns._ID
                    + " integer primary key autoincrement," + PictureColumns.PICTURE
                    + " blob not null);";

            String yinghuanPicture =  "Create table "  +   YINGHUAN_NAME   + "(" + BaseColumns._ID
                    + " integer primary key autoincrement," + PictureColumns.PICTURE
                    + " blob not null);";

            String changfangjingPicture =  "Create table " +  CHANGFANGJING_NAME  + "(" + BaseColumns._ID
                    + " integer primary key autoincrement," + PictureColumns.PICTURE
                    + " blob not null);";

            String yixingjingPicture =  "Create table "  +  YIXINGJING_NAME   + "(" + BaseColumns._ID
                    + " integer primary key autoincrement," + PictureColumns.PICTURE
                    + " blob not null);";
            String beiPicture =  "Create table "  +  BEI_NAME   + "(" + BaseColumns._ID
                    + " integer primary key autoincrement," + PictureColumns.PICTURE
                    + " blob not null);";
            db.execSQL(fenpicture);
            db.execSQL(huangwangguiPicture);
            db.execSQL(xiangbianPicture);
            db.execSQL(peidianshiPicture);
            db.execSQL(bianyaqiPicture);
            db.execSQL(gantaPicture);
            db.execSQL(biandianzhanPicture);
            db.execSQL(kaiguanzhanPicture);
            db.execSQL(yanmaijingPicture);
            db.execSQL(yinghuanPicture);
            db.execSQL(changfangjingPicture);
            db.execSQL(yixingjingPicture);
            db.execSQL(beiPicture);
        }
    //将转换后的图片存入到环网柜数据库中
    public void saveFenZhiXiangPhoto (Bitmap bitmap ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PictureColumns.PICTURE, getPicture(bitmap));
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }
        //将drawable转换成可以用来存储的byte[]类型
        private byte[] getPicture(Bitmap bitmap) {
            if(bitmap == null) {
                return null;
            }
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return os.toByteArray();


    }
    //将转换后的图更新到数据库中
    public boolean updeteFenZhiXiangPhoto(Bitmap bitmap ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put(PictureColumns.PICTURE, getPicture(bitmap));
        //long i = db.update(TABLE_NAME,cv1,PictureColumns.PICTURE+"=?", new String[] {String.valueOf(getPicture(bitmap))});
        long i = db.update(TABLE_NAME,cv1,null,null);
        Log.e("iiiiiiiiiiiiiii",i+"");
        if(i>0){
            return true;
        }else{
            return false;
        }

    }

    public void getFenZhiXiangDelete(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME ,null,null);

    }

    //从数据库读取环网柜照片
    public ArrayList<Bitmap> getFenZhiXiangDrawable() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
        //查询数据库
        Cursor c = db.rawQuery("select * from fenpicture", null);
        //遍历数据
        if( c.getCount() > 0) {
            c.moveToFirst();
            while(!c.isAfterLast()) {
                //获取数据
                byte[] b = c.getBlob(c.getColumnIndexOrThrow(PictureColumns.PICTURE));
                //将获取的数据转换成drawable
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length, null);
                bitmaps.add(bitmap);
                c.moveToNext();
            }
            c.close();
            db.close();
            return bitmaps;
        } else {
            return null;
        }

    }


    //将转换后的图片存入到环网柜数据库中
    public void saveHuangWangGuiPhoto (Bitmap bitmap ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PictureColumns.PICTURE, getPicture(bitmap));
        //db.delete(TABLE_NAME,null,null);//每次设置头像之前清空数据库的数据
        db.insert(HUANGWANGGUIPICTURE_NAME, null, cv);
        db.close();
    }

    //将转换后的图更新到数据库中
    public boolean updeteHuangWangGuiPhoto(Bitmap bitmap ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put(PictureColumns.PICTURE, getPicture(bitmap));
        long i = db.update(HUANGWANGGUIPICTURE_NAME ,cv1, PictureColumns.PICTURE +"=?", new String[]{String.valueOf(getPicture(bitmap))});
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    public void getHuangWangGuiDelete(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(HUANGWANGGUIPICTURE_NAME ,null,null);

    }

    //从数据库读取环网柜照片
    public ArrayList<Bitmap> getHuangWangGuiDrawable() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
        //查询数据库
        Cursor c = db.rawQuery("select * from huangwangguiPicture", null);
        //遍历数据
        if( c.getCount() > 0) {
            c.moveToFirst();
            while(!c.isAfterLast()) {
                //获取数据
                byte[] b = c.getBlob(c.getColumnIndexOrThrow(PictureColumns.PICTURE));
                //将获取的数据转换成drawable
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length, null);
                bitmaps.add(bitmap);
                c.moveToNext();
            }
            c.close();
            db.close();
            return bitmaps;
        } else {
            return null;
        }
    }

    //将转换后的图片存入到箱变数据库中
    public void saveXiangBianPhoto (Bitmap bitmap ) {
        // Drawable drawable = context.getResources().getDrawable(resources); 传入int资源时用
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PictureColumns.PICTURE, getPicture(bitmap));
        //db.delete(TABLE_NAME,null,null);//每次设置头像之前清空数据库的数据
        db.insert(XIANGBIAN_NAME , null, cv);
        db.close();
    }

    //将转换后的图更新到数据库中
    public boolean updeteXiangBianPhoto(Bitmap bitmap ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put(PictureColumns.PICTURE, getPicture(bitmap));
        long i = db.update(XIANGBIAN_NAME ,cv1, PictureColumns.PICTURE +"=?", new String[]{String.valueOf(getPicture(bitmap))});
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    public void getXiangBianDelete(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(XIANGBIAN_NAME ,null,null);

    }
    //从数据库读取箱变照片
    public ArrayList<Bitmap> getXiangBianDrawable() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
        //查询数据库
        Cursor c = db.rawQuery("select * from xiangbianPicture", null);
        //遍历数据
        if( c.getCount() > 0) {
            c.moveToFirst();
            while(!c.isAfterLast()) {
                //获取数据
                byte[] b = c.getBlob(c.getColumnIndexOrThrow(PictureColumns.PICTURE));
                //将获取的数据转换成drawable
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length, null);
                bitmaps.add(bitmap);
                c.moveToNext();
            }
            c.close();
            db.close();
            return bitmaps;
        } else {
            return null;
        }
    }

    //将转换后的图片存入到配电室数据库中
    public void savePeiDainShiPhoto (Bitmap bitmap ) {
        // Drawable drawable = context.getResources().getDrawable(resources); 传入int资源时用
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PictureColumns.PICTURE, getPicture(bitmap));
        //db.delete(TABLE_NAME,null,null);//每次设置头像之前清空数据库的数据
        db.insert(PEIDIANSHI_NAME, null, cv);
        db.close();
    }

    //将转换后的图更新到数据库中
    public boolean updetePeiDainShiPhoto(Bitmap bitmap ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put(PictureColumns.PICTURE, getPicture(bitmap));
        long i = db.update(PEIDIANSHI_NAME,cv1, PictureColumns.PICTURE +"=?", new String[]{String.valueOf(getPicture(bitmap))});
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    public void getPeiDainShiDelete(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(PEIDIANSHI_NAME ,null,null);

    }
    //从数据库读取配电室照片
    public ArrayList<Bitmap> getPeiDainShiDrawable() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
        //查询数据库
        Cursor c = db.rawQuery("select * from peidianshiPicture", null);
        //遍历数据
        if( c.getCount() > 0) {
            c.moveToFirst();
            while(!c.isAfterLast()) {
                //获取数据
                byte[] b = c.getBlob(c.getColumnIndexOrThrow(PictureColumns.PICTURE));
                //将获取的数据转换成drawable
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length, null);
                bitmaps.add(bitmap);
                c.moveToNext();
            }
            c.close();
            db.close();
            return bitmaps;
        } else {
            return null;
        }
    }


    //将转换后的图片存入到变压器数据库中
    public void saveBianYaQiPhoto (Bitmap bitmap ) {
        // Drawable drawable = context.getResources().getDrawable(resources); 传入int资源时用
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PictureColumns.PICTURE, getPicture(bitmap));
        //db.delete(TABLE_NAME,null,null);//每次设置头像之前清空数据库的数据
        db.insert(BIANYAQI_NAME, null, cv);
        db.close();
    }

    //将转换后的图更新到数据库中
    public boolean updeteBianYaQiPhoto(Bitmap bitmap ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put(PictureColumns.PICTURE, getPicture(bitmap));
        long i = db.update(BIANYAQI_NAME,cv1, PictureColumns.PICTURE +"=?", new String[]{String.valueOf(getPicture(bitmap))});
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    public void getBianYaQiDelete(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(BIANYAQI_NAME,null,null);

    }

    //从数据库读取变压器照片
    public ArrayList<Bitmap> getBianYaQiDrawable() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
        //查询数据库
        Cursor c = db.rawQuery("select * from bianyaqiPicture", null);
        //遍历数据
        if( c.getCount() > 0) {
            c.moveToFirst();
            while(!c.isAfterLast()) {
                //获取数据
                byte[] b = c.getBlob(c.getColumnIndexOrThrow(PictureColumns.PICTURE));
                //将获取的数据转换成drawable
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length, null);
                bitmaps.add(bitmap);
                c.moveToNext();
            }
            c.close();
            db.close();
            return bitmaps;
        } else {
            return null;
        }
    }


    //将转换后的图片存入到杆塔数据库中
    public void saveGanTaPhoto (Bitmap bitmap ) {
        // Drawable drawable = context.getResources().getDrawable(resources); 传入int资源时用
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PictureColumns.PICTURE, getPicture(bitmap));
        //db.delete(TABLE_NAME,null,null);//每次设置头像之前清空数据库的数据
        db.insert(GANTA_NAME, null, cv);
        db.close();
    }

    //将转换后的图更新到数据库中
    public boolean updeteGanTaPhoto(Bitmap bitmap ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put(PictureColumns.PICTURE, getPicture(bitmap));
        long i = db.update(GANTA_NAME,cv1, PictureColumns.PICTURE +"=?", new String[]{String.valueOf(getPicture(bitmap))});
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    public void getGanTaDelete(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(GANTA_NAME ,null,null);

    }
    //从数据库读取杆塔照片
    public ArrayList<Bitmap> getGanTaDrawable() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
        //查询数据库
        Cursor c = db.rawQuery("select * from gantaPicture", null);
        //遍历数据
        if( c.getCount() > 0) {
            c.moveToFirst();
            while(!c.isAfterLast()) {
                //获取数据
                byte[] b = c.getBlob(c.getColumnIndexOrThrow(PictureColumns.PICTURE));
                //将获取的数据转换成drawable
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length, null);
                bitmaps.add(bitmap);
                c.moveToNext();
            }
            c.close();
            db.close();
            return bitmaps;
        } else {
            return null;
        }
    }

    //将转换后的图片存入到变电站数据库中
    public void saveBianDainZhanPhoto (Bitmap bitmap ) {
        // Drawable drawable = context.getResources().getDrawable(resources); 传入int资源时用
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PictureColumns.PICTURE, getPicture(bitmap));
        //db.delete(TABLE_NAME,null,null);//每次设置头像之前清空数据库的数据
        db.insert(BIANDIANZHAN_NAME, null, cv);
        db.close();
    }

    //将转换后的图更新到数据库中
    public boolean updeteBianDainZhanPhoto(Bitmap bitmap ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put(PictureColumns.PICTURE, getPicture(bitmap));
        long i = db.update(BIANDIANZHAN_NAME,cv1, PictureColumns.PICTURE +"=?", new String[]{String.valueOf(getPicture(bitmap))});
        db.close();
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    public void getBianDainZhanDelete(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(BIANDIANZHAN_NAME ,null,null);

    }
    //从数据库读取变电站照片
    public ArrayList<Bitmap> getBianDainZhanDrawable() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
        //查询数据库
        Cursor c = db.rawQuery("select * from biandianzhanPicture", null);
        //遍历数据
        if( c.getCount() > 0) {
            c.moveToFirst();
            while(!c.isAfterLast()) {
                //获取数据
                byte[] b = c.getBlob(c.getColumnIndexOrThrow(PictureColumns.PICTURE));
                //将获取的数据转换成drawable
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length, null);
                bitmaps.add(bitmap);
                c.moveToNext();
            }
            c.close();
            db.close();
            return bitmaps;
        } else {
            return null;
        }
    }


    //将转换后的图片存入到开关站数据库中
    public void saveKaiGuanZhanPhoto (Bitmap bitmap ) {
        // Drawable drawable = context.getResources().getDrawable(resources); 传入int资源时用
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PictureColumns.PICTURE, getPicture(bitmap));
        //db.delete(TABLE_NAME,null,null);//每次设置头像之前清空数据库的数据
        db.insert(KAIGUANZHAN_NAME, null, cv);
        db.close();
    }

    //将转换后的图更新到数据库中
    public boolean updeteKaiGuanZhanPhoto(Bitmap bitmap ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put(PictureColumns.PICTURE, getPicture(bitmap));
        long i = db.update(KAIGUANZHAN_NAME,cv1, PictureColumns.PICTURE +"=?", new String[]{String.valueOf(getPicture(bitmap))});
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    public void getKaiGuanZhanDelete(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(KAIGUANZHAN_NAME,null,null);

    }
    //从数据库读取变电站照片
    public ArrayList<Bitmap> getKaiGuanZhanDrawable() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
        //查询数据库
        Cursor c = db.rawQuery("select * from kaiguanzhanPicture", null);
        //遍历数据
        if( c.getCount() > 0) {
            c.moveToFirst();
            while(!c.isAfterLast()) {
                //获取数据
                byte[] b = c.getBlob(c.getColumnIndexOrThrow(PictureColumns.PICTURE));
                //将获取的数据转换成drawable
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length, null);
                bitmaps.add(bitmap);
                c.moveToNext();
            }
            c.close();
            db.close();
            return bitmaps;
        } else {
            return null;
        }
    }



    //将转换后的图片存入到掩埋井数据库中
    public void saveYanMaiJingPhoto (Bitmap bitmap ) {
        // Drawable drawable = context.getResources().getDrawable(resources); 传入int资源时用
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PictureColumns.PICTURE, getPicture(bitmap));
        //db.delete(TABLE_NAME,null,null);//每次设置头像之前清空数据库的数据
        db.insert(YANMAIJING_NAME , null, cv);
        db.close();
    }

    //将转换后的图更新到数据库中
    public boolean updeteYanMaiJingPhoto(Bitmap bitmap ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put(PictureColumns.PICTURE, getPicture(bitmap));
        long i = db.update(YANMAIJING_NAME ,cv1, PictureColumns.PICTURE +"=?", new String[]{String.valueOf(getPicture(bitmap))});
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    public void getYanMaiJingDelete(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(YANMAIJING_NAME ,null,null);

    }
    //从数据库读取掩埋井照片
    public ArrayList<Bitmap> getYanMaiJingDrawable() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
        //查询数据库
        Cursor c = db.rawQuery("select * from yanmaijingPicture", null);
        //遍历数据
        if( c.getCount() > 0) {
            c.moveToFirst();
            while(!c.isAfterLast()) {
                //获取数据
                byte[] b = c.getBlob(c.getColumnIndexOrThrow(PictureColumns.PICTURE));
                //将获取的数据转换成drawable
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length, null);
                bitmaps.add(bitmap);
                c.moveToNext();
            }
            c.close();
            db.close();
            return bitmaps;
        } else {
            return null;
        }
    }


    //将转换后的图片存入到隐患信息数据库中
    public void saveYingHuanPhoto (Bitmap bitmap ) {
        // Drawable drawable = context.getResources().getDrawable(resources); 传入int资源时用
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PictureColumns.PICTURE, getPicture(bitmap));
        //db.delete(TABLE_NAME,null,null);//每次设置头像之前清空数据库的数据
        db.insert(YINGHUAN_NAME , null, cv);
        db.close();
    }

    //将转换后的图更新到数据库中
    public boolean updeteYingHuanPhoto(Bitmap bitmap ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put(PictureColumns.PICTURE, getPicture(bitmap));
        long i = db.update(YINGHUAN_NAME  ,cv1, PictureColumns.PICTURE +"=?", new String[]{String.valueOf(getPicture(bitmap))});
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    public void getYingHuanDelete(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(YINGHUAN_NAME  ,null,null);

    }
    //从数据库读取隐患信息照片
    public ArrayList<Bitmap> getYingHuanDrawable() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
        //查询数据库
        Cursor c = db.rawQuery("select * from yinghuanPicture", null);
        //遍历数据
        if( c.getCount() > 0) {
            c.moveToFirst();
            while(!c.isAfterLast()) {
                //获取数据
                byte[] b = c.getBlob(c.getColumnIndexOrThrow(PictureColumns.PICTURE));
                //将获取的数据转换成drawable
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length, null);
                bitmaps.add(bitmap);
                c.moveToNext();
            }
            c.close();
            db.close();
            return bitmaps;
        } else {
            return null;
        }
    }



    //将转换后的图片存入到长方形井数据库中
    public void saveChangFangXingPhoto (Bitmap bitmap ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PictureColumns.PICTURE, getPicture(bitmap));
        //db.delete(TABLE_NAME,null,null);//每次设置头像之前清空数据库的数据
        db.insert(CHANGFANGJING_NAME , null, cv);
        db.close();
    }

    //将转换后的图更新到数据库中
    public boolean updeteChangFangXingPhoto(Bitmap bitmap ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put(PictureColumns.PICTURE, getPicture(bitmap));
        long i = db.update(CHANGFANGJING_NAME ,cv1, PictureColumns.PICTURE +"=?", new String[]{String.valueOf(getPicture(bitmap))});
        if(i>0){
            return true;
        }else{
            return false;
        }
    }
    public void getChangFangXingDelete(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(CHANGFANGJING_NAME,null,null);

    }

    //从数据库读取长方形井照片
    public ArrayList<Bitmap> getChangFangXingDrawable() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
        //查询数据库
        Cursor c = db.rawQuery("select * from changfangjingPicture", null);
        //遍历数据
        if( c.getCount() > 0) {
            c.moveToFirst();
            while(!c.isAfterLast()) {
                //获取数据
                byte[] b = c.getBlob(c.getColumnIndexOrThrow(PictureColumns.PICTURE));
                //将获取的数据转换成drawable
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length, null);
                bitmaps.add(bitmap);
                c.moveToNext();
            }
            c.close();
            db.close();
            return bitmaps;
        } else {
            return null;
        }
    }



    //将转换后的图片存入到长方形井数据库中
    public void saveYiXingJingPhoto (Bitmap bitmap ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PictureColumns.PICTURE, getPicture(bitmap));
        //db.delete(TABLE_NAME,null,null);//每次设置头像之前清空数据库的数据
        db.insert(YIXINGJING_NAME , null, cv);
        db.close();
    }

    //将转换后的图更新到数据库中
    public boolean updeteYiXingJingPhoto(Bitmap bitmap ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put(PictureColumns.PICTURE, getPicture(bitmap));
        long i = db.update(YIXINGJING_NAME ,cv1, PictureColumns.PICTURE +"=?", new String[]{String.valueOf(getPicture(bitmap))});
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    public void getYiXingJingDelete(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(YIXINGJING_NAME,null,null);
    }
    //从数据库读取异形形井照片
    public ArrayList<Bitmap> getYiXingJingDrawable() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
        //查询数据库
        Cursor c = db.rawQuery("select * from yixingjingPicture", null);
        //遍历数据
        if( c.getCount() > 0) {
            c.moveToFirst();
            while(!c.isAfterLast()) {
                //获取数据
                byte[] b = c.getBlob(c.getColumnIndexOrThrow(PictureColumns.PICTURE));
                //将获取的数据转换成drawable
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length, null);
                bitmaps.add(bitmap);
                c.moveToNext();
            }
            c.close();
            db.close();
            return bitmaps;
        } else {
            return null;
        }
    }


    //将转换后的图片存入到异形井北数据库中
    public void saveBeiPhoto (Bitmap bitmap ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PictureColumns.PICTURE, getPicture(bitmap));
        //db.delete(TABLE_NAME,null,null);//每次设置头像之前清空数据库的数据
        db.insert(BEI_NAME , null, cv);
        db.close();
    }

    //将转换后的图更新到数据库中
    public boolean updetBeiPhoto(Bitmap bitmap ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put(PictureColumns.PICTURE, getPicture(bitmap));
        long i = db.update(BEI_NAME,cv1, PictureColumns.PICTURE +"=?", new String[]{String.valueOf(getPicture(bitmap))});
        if(i>0){
            return true;
        }else{
            return false;
        }
    }

    public void getBeiDelete(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(BEI_NAME,null,null);

    }
    //从数据库读取长方形井照片
    public ArrayList<Bitmap> getBeiDrawable() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
        //查询数据库
        Cursor c = db.rawQuery("select * from beiPicture", null);
        //遍历数据
        if( c.getCount() > 0) {
            c.moveToFirst();
            while(!c.isAfterLast()) {
                //获取数据
                byte[] b = c.getBlob(c.getColumnIndexOrThrow(PictureColumns.PICTURE));
                //将获取的数据转换成drawable
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length, null);
                bitmaps.add(bitmap);
                c.moveToNext();
            }
            c.close();
            db.close();
            return bitmaps;
        } else {
            return null;
        }
    }


    //更新数据库
        @Override
        public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
            String sql = " DROP TABLE IF EXISTS " + TABLE_NAME+HUANGWANGGUIPICTURE_NAME ;
            db.execSQL(sql);
            onCreate(db);
        }


}
