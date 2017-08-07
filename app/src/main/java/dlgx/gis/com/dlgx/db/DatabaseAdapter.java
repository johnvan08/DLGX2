package dlgx.gis.com.dlgx.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import dlgx.gis.com.dlgx.beans.DianLanDuanBean;
import dlgx.gis.com.dlgx.beans.DianLanJieTou;
import dlgx.gis.com.dlgx.beans.DianLanJingDuanMianBiao;
import dlgx.gis.com.dlgx.beans.DianlantongdaoBean;
import dlgx.gis.com.dlgx.beans.DldglBean;
import dlgx.gis.com.dlgx.beans.DltdglBean;
import dlgx.gis.com.dlgx.beans.DuanMianKongWei;
import dlgx.gis.com.dlgx.beans.GongJingXinxiBean;
import dlgx.gis.com.dlgx.beans.SheBeiBean;
import dlgx.gis.com.dlgx.beans.YingHuanXinXi;
import dlgx.gis.com.dlgx.utils.PinYin;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作类
 * 
 * @author zihao
 * 
 */
public class DatabaseAdapter {
	private static DatabaseManager manager;
	private static Context mContext;
	/**
	 * 获取一个操作类对象
	 * 
	 * @param context
	 * @return
	 */
	public static DatabaseAdapter getIntance(Context context) {
		DatabaseAdapter adapter = new DatabaseAdapter();
		mContext = context;
		manager = DatabaseManager.getInstance(new DatabaseHelper(mContext));
		return adapter;
	}

	/**
	 * 插入信息
	 *
	 * @param titleArray
	 */
	public void inserInfo(List<String> titleArray) {
		SQLiteDatabase database = manager.getWritableDatabase();

		try {
			for (String title : titleArray) {
				ContentValues values = new ContentValues();
				values.put("title", title);
				values.put("pinyin", PinYin.getPinYin(title));// 讲内容转换为拼音
				database.insert(DatabaseHelper.TABLE_NAME, null, values);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			manager.closeDatabase();
		}
	}

	/**
	 * 查询信息
	 * 
	 * @param pinyin
	 *            // 字符串转换的拼音
	 * @return
	 */
	public List<String> queryInfo(String pinyin) {
		List<String> resultArray = new ArrayList<String>();
		SQLiteDatabase database = manager.getReadableDatabase();
		Cursor cursor = null;

		try {
			// 创建模糊查询的条件
			String likeStr = "'";
			for (int i = 0; i < pinyin.length(); i++) {
				if (i < pinyin.length() - 1) {
					likeStr += "%" + pinyin.charAt(i);
				} else {
					likeStr += "%" + pinyin.charAt(i) + "%'";
				}
			}

			cursor = database.rawQuery("select * from "
					+ DatabaseHelper.TABLE_NAME + " where pinyin like "
					+ likeStr, null);

			while (cursor.moveToNext()) {
				resultArray
						.add(cursor.getString(cursor.getColumnIndex("title")));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.toString();
		} finally {
			manager.closeDatabase();
		}

		return resultArray;
	}

	/**
	 * 删除表中的所有数据
	 */
	public void deleteAll() {
		SQLiteDatabase database = manager.getWritableDatabase();

		try {
			database.delete(DatabaseHelper.TABLE_NAME, null, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			manager.closeDatabase();
		}
	}
      //region  --设备管理---
	//设备
	public boolean insertSheBeiAddress(SheBeiBean address){
		SQLiteDatabase db = manager.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.SHEBEIID, address.getUuid());
		values.put(DatabaseHelper.SHEBEINAME, address.getName());
		values.put(DatabaseHelper.SHEBEIMAISHEN , address.getMaishen());
		values.put(DatabaseHelper.SHEBEIJINGDU, address.getJingdu());
		values.put(DatabaseHelper.SHEBEIWEIDU , address.getWeidu());
		values.put(DatabaseHelper.SHEBEIBEIZHU, address.getBeizhu());
		values.put(DatabaseHelper.SHEBEIZUOYEDANWEI, address.getZuoyedanwei());
		values.put(DatabaseHelper.SHEBEIXINZNEGSHIJIAN , address.getXinznegshijian());
		values.put(DatabaseHelper.SHEBEILEIXING , address.getLx());
		Long i = db.insert(DatabaseHelper.MY_SHEBEI, null, values);
		manager.closeDatabase();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	public boolean updeteSheBeiAddress(SheBeiBean  address){
		SQLiteDatabase db = manager.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.SHEBEIID, address.getUuid());
		values.put(DatabaseHelper.SHEBEINAME, address.getName());
		values.put(DatabaseHelper.SHEBEIMAISHEN , address.getMaishen());
		values.put(DatabaseHelper.SHEBEIJINGDU, address.getJingdu());
		values.put(DatabaseHelper.SHEBEIWEIDU , address.getWeidu());
		values.put(DatabaseHelper.SHEBEIBEIZHU, address.getBeizhu());
		values.put(DatabaseHelper.SHEBEIZUOYEDANWEI, address.getZuoyedanwei());
		values.put(DatabaseHelper.SHEBEIXINZNEGSHIJIAN , address.getXinznegshijian());
		values.put(DatabaseHelper.SHEBEILEIXING , address.getLx());
		long i = db.update(DatabaseHelper.MY_SHEBEI, values, DatabaseHelper.SHEBEIID+"=?", new String[]{address.getUuid()});
		if(i>0){
			return true;
		}else{
			return false;
		}
	}


	public List<SheBeiBean> querySheBeiAddress(String lx){
		SQLiteDatabase db = manager.getWritableDatabase();
		List<SheBeiBean> list = new ArrayList<SheBeiBean >();

		Cursor cursor = db.query(DatabaseHelper.MY_SHEBEI, new String[] {
				DatabaseHelper.SHEBEIID,
				DatabaseHelper.SHEBEINAME,
				DatabaseHelper.SHEBEIMAISHEN,
				DatabaseHelper.SHEBEIJINGDU,
				DatabaseHelper.SHEBEIWEIDU,
				DatabaseHelper.SHEBEIBEIZHU,
				DatabaseHelper.SHEBEIZUOYEDANWEI,
				DatabaseHelper.SHEBEIXINZNEGSHIJIAN,
				DatabaseHelper.SHEBEILEIXING},
				DatabaseHelper.SHEBEILEIXING +"=?", new String[]{lx}, null, null, null);

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				SheBeiBean    add = new SheBeiBean ();
				add.setUuid((cursor.getString(0)));
				add.setName(cursor.getString(1));
				add.setMaishen(cursor.getString(2));
				add.setJingdu(cursor.getString(3));
				add.setWeidu(cursor.getString(4));
				add.setBeizhu(cursor.getString(5));
				add.setZuoyedanwei(cursor.getString(6));
				add.setXinznegshijian(cursor.getString(7));
				add.setLx(cursor.getString(8));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}

	public boolean deleteSheBeiAddress(SheBeiBean address){
		SQLiteDatabase db = manager.getWritableDatabase();

		int i = db.delete(DatabaseHelper.MY_SHEBEI,  DatabaseHelper.SHEBEIID+"=?", new String[]{address.getUuid()});
		manager.closeDatabase();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}


//endregion

     //region  -----电缆井管理----
//电缆井
	public boolean insertJingAddress(GongJingXinxiBean address){
		SQLiteDatabase db = manager.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.JINGID, address.getUuid());
		values.put(DatabaseHelper.JINGNAME, address.getName());
		values.put(DatabaseHelper.JINGNUMMBER , address.getNummber());
		values.put(DatabaseHelper.JINGSHULIANG, address.getShuliang());
		values.put(DatabaseHelper.JINGCAIHZI, address.getCaizhi());
		values.put(DatabaseHelper.JINGXINGZHUANG, address.getXingzhuang());
		values.put(DatabaseHelper.JINGCHICUN, address.getChicun());
		values.put(DatabaseHelper.JINGJINGCHANG , address.getJingchang());
		values.put(DatabaseHelper.JINGJINGKUAN, address.getJingkuan());
		values.put(DatabaseHelper.JINGJINGNEISHEN, address.getJingneishen());
		values.put(DatabaseHelper.JINGJINGSHEN , address.getJingshen());
		values.put(DatabaseHelper.JINGGONGNNEG, address.getGongneng());
		values.put(DatabaseHelper. JINGLEIXING, address.getLeixing());
		values.put(DatabaseHelper.JINGJINGINGDU, address.getJingdu());
		values.put(DatabaseHelper.JINGWEIDU , address.getWeidu());
		values.put(DatabaseHelper.JINGGAODU, address.getGaodu());
		values.put(DatabaseHelper.JINGJIETOUSHULIANG, address.getJietoushuliang());
		values.put(DatabaseHelper.JINGSHEBEIID , address.getShebeiId());
		values.put(DatabaseHelper.JINGDIANZIBAINQIANBIANHAO, address.getDianzibainqianbianhao());
		values.put(DatabaseHelper.JINGOUYUNRIQI, address.getTouyunriqi());
		values.put(DatabaseHelper.JINGSHIGONGDANWEI, address.getShigongdanwei());
		values.put(DatabaseHelper.JINGBEIZHU, address.getBeizhu());
		values.put(DatabaseHelper. JINGZUOYEDANWEI, address.getZuoyedanwei());
		values.put(DatabaseHelper.JINGXINZENGSHIJIAN, address.getXinzengshijian());
		values.put(DatabaseHelper.JINGADRESS, address.getAdress());
		Long i = db.insert(DatabaseHelper.MY_CABLEPIT, null, values);
		manager.closeDatabase();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	public boolean updeteJingAddress(GongJingXinxiBean address){
		SQLiteDatabase db = manager.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.JINGID, address.getUuid());
		values.put(DatabaseHelper.JINGNAME, address.getName());
		values.put(DatabaseHelper.JINGNUMMBER , address.getNummber());
		values.put(DatabaseHelper.JINGSHULIANG, address.getShuliang());
		values.put(DatabaseHelper.JINGCAIHZI, address.getCaizhi());
		values.put(DatabaseHelper.JINGXINGZHUANG, address.getXingzhuang());
		values.put(DatabaseHelper.JINGCHICUN, address.getChicun());
		values.put(DatabaseHelper.JINGJINGCHANG , address.getJingchang());
		values.put(DatabaseHelper.JINGJINGKUAN, address.getJingkuan());
		values.put(DatabaseHelper.JINGJINGNEISHEN, address.getJingneishen());
		values.put(DatabaseHelper.JINGJINGSHEN , address.getJingshen());
		values.put(DatabaseHelper.JINGGONGNNEG, address.getGongneng());
		values.put(DatabaseHelper. JINGLEIXING, address.getLeixing());
		values.put(DatabaseHelper.JINGJINGINGDU, address.getJingdu());
		values.put(DatabaseHelper.JINGWEIDU , address.getWeidu());
		values.put(DatabaseHelper.JINGGAODU, address.getGaodu());
		values.put(DatabaseHelper.JINGJIETOUSHULIANG, address.getJietoushuliang());
		values.put(DatabaseHelper.JINGSHEBEIID , address.getShebeiId());
		values.put(DatabaseHelper.JINGDIANZIBAINQIANBIANHAO, address.getDianzibainqianbianhao());
		values.put(DatabaseHelper.JINGOUYUNRIQI, address.getTouyunriqi());
		values.put(DatabaseHelper.JINGSHIGONGDANWEI, address.getShigongdanwei());
		values.put(DatabaseHelper.JINGBEIZHU, address.getBeizhu());
		values.put(DatabaseHelper. JINGZUOYEDANWEI, address.getZuoyedanwei());
		values.put(DatabaseHelper.JINGXINZENGSHIJIAN, address.getXinzengshijian());
		values.put(DatabaseHelper.JINGADRESS, address.getAdress());
		long i = db.update(DatabaseHelper.MY_CABLEPIT , values, DatabaseHelper.JINGID+"=?", new String[]{address.getUuid()});
		if(i>0){
			return true;
		}else{
			return false;
		}
	}


	public List<GongJingXinxiBean> queryJingAddress(){
		SQLiteDatabase db = manager.getWritableDatabase();
		List<GongJingXinxiBean > list = new ArrayList<GongJingXinxiBean>();

		Cursor cursor = db.query(DatabaseHelper.MY_CABLEPIT, new String[] { DatabaseHelper.JINGID,DatabaseHelper.JINGNAME, DatabaseHelper.JINGNUMMBER,DatabaseHelper.JINGSHULIANG,DatabaseHelper.JINGCAIHZI, DatabaseHelper.JINGXINGZHUANG,DatabaseHelper.JINGCHICUN,DatabaseHelper.JINGJINGCHANG,DatabaseHelper.JINGJINGKUAN,DatabaseHelper.JINGJINGNEISHEN,DatabaseHelper.JINGJINGSHEN,DatabaseHelper.JINGGONGNNEG,DatabaseHelper.JINGLEIXING,DatabaseHelper.JINGJINGINGDU,DatabaseHelper.JINGWEIDU,DatabaseHelper.JINGGAODU,DatabaseHelper.JINGJIETOUSHULIANG,DatabaseHelper.JINGSHEBEIID,DatabaseHelper.JINGDIANZIBAINQIANBIANHAO,DatabaseHelper.JINGOUYUNRIQI,DatabaseHelper.JINGSHIGONGDANWEI,DatabaseHelper.JINGBEIZHU,DatabaseHelper.JINGZUOYEDANWEI,DatabaseHelper.JINGXINZENGSHIJIAN,DatabaseHelper.JINGADRESS},
			null,null, null, null, null);

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				GongJingXinxiBean  add = new GongJingXinxiBean();
				add.setUuid((cursor.getString(0)));
				add.setName(cursor.getString(1));
				add.setNummber(cursor.getString(2));
				add.setShuliang(cursor.getString(3));
				add.setCaizhi(cursor.getString(4));
				add.setXingzhuang(cursor.getString(5));
				add.setChicun(cursor.getString(6));
				add.setJingchang(cursor.getString(7));
				add.setJingkuan(cursor.getString(8));
				add.setJingneishen(cursor.getString(9));
				add.setJingshen(cursor.getString(10));
				add.setGongneng(cursor.getString(11));
				add.setLeixing(cursor.getString(12));
				add.setJingdu(cursor.getString(13));
				add.setWeidu(cursor.getString(14));
				add.setGaodu(cursor.getString(15));
				add.setJietoushuliang (cursor.getString(16));
				add.setShebeiId(cursor.getString(17));
				add.setDianzibainqianbianhao(cursor.getString(18));
				add.setTouyunriqi(cursor.getString(19));
				add.setShigongdanwei(cursor.getString(20));
				add.setBeizhu(cursor.getString(21));
				add.setZuoyedanwei(cursor.getString(22));
				add.setXinzengshijian(cursor.getString(23));
				add.setAdress(cursor.getString(24));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}

   // 查询电缆井信息通过电缆井现场编号
	public List<GongJingXinxiBean> queryJingByAddress(String dljnummber){
		SQLiteDatabase db = manager.getWritableDatabase();
		List<GongJingXinxiBean > list = new ArrayList<GongJingXinxiBean>();

		Cursor cursor = db.query(DatabaseHelper.MY_CABLEPIT, new String[] { DatabaseHelper.JINGID,DatabaseHelper.JINGNAME, DatabaseHelper.JINGNUMMBER,DatabaseHelper.JINGSHULIANG,DatabaseHelper.JINGCAIHZI, DatabaseHelper.JINGXINGZHUANG,DatabaseHelper.JINGCHICUN,DatabaseHelper.JINGJINGCHANG,DatabaseHelper.JINGJINGKUAN,DatabaseHelper.JINGJINGNEISHEN,DatabaseHelper.JINGJINGSHEN,DatabaseHelper.JINGGONGNNEG,DatabaseHelper.JINGLEIXING,DatabaseHelper.JINGJINGINGDU,DatabaseHelper.JINGWEIDU,DatabaseHelper.JINGGAODU,DatabaseHelper.JINGJIETOUSHULIANG,DatabaseHelper.JINGSHEBEIID,DatabaseHelper.JINGDIANZIBAINQIANBIANHAO,DatabaseHelper.JINGOUYUNRIQI,DatabaseHelper.JINGSHIGONGDANWEI,DatabaseHelper.JINGBEIZHU,DatabaseHelper.JINGZUOYEDANWEI,DatabaseHelper.JINGXINZENGSHIJIAN,DatabaseHelper.JINGADRESS},
				DatabaseHelper.JINGNUMMBER+"=?", new String[]{dljnummber}, null, null, null);

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				GongJingXinxiBean  add = new GongJingXinxiBean();
				add.setUuid((cursor.getString(0)));
				add.setName(cursor.getString(1));
				add.setNummber(cursor.getString(2));
				add.setShuliang(cursor.getString(3));
				add.setCaizhi(cursor.getString(4));
				add.setXingzhuang(cursor.getString(5));
				add.setChicun(cursor.getString(6));
				add.setJingchang(cursor.getString(7));
				add.setJingkuan(cursor.getString(8));
				add.setJingneishen(cursor.getString(9));
				add.setJingshen(cursor.getString(10));
				add.setGongneng(cursor.getString(11));
				add.setLeixing(cursor.getString(12));
				add.setJingdu(cursor.getString(13));
				add.setWeidu(cursor.getString(14));
				add.setGaodu(cursor.getString(15));
				add.setJietoushuliang (cursor.getString(16));
				add.setShebeiId(cursor.getString(17));
				add.setDianzibainqianbianhao(cursor.getString(18));
				add.setTouyunriqi(cursor.getString(19));
				add.setShigongdanwei(cursor.getString(20));
				add.setBeizhu(cursor.getString(21));
				add.setZuoyedanwei(cursor.getString(22));
				add.setXinzengshijian(cursor.getString(23));
				add.setAdress(cursor.getString(24));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}

	public void queryJingAddress1(){
		SQLiteDatabase db = manager.getWritableDatabase();
		Cursor cursor = db.query(DatabaseHelper.MY_CABLEPIT  , null,
				null,null, null, null, null);

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			cursor.moveToNext();
		}
		cursor.close();
		manager.closeDatabase();

	}

	public boolean deleteJingAddress(DianLanJingDuanMianBiao address){
		SQLiteDatabase db = manager.getWritableDatabase();

		int i = db.delete(DatabaseHelper.MY_CABLEPIT , DatabaseHelper.JINGID+"=?", new String[]{address.getUuid()});
		manager.closeDatabase();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	//endregion

	//region ----电缆段管理----
	//电缆段
	public boolean insertDuanAddress(DianLanDuanBean address){
		SQLiteDatabase db = manager.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.LANDUANID, address.getUuid());
		values.put(DatabaseHelper.LANDUANNAME, address.getName());
		values.put(DatabaseHelper.LANDUANNUMMBER , address.getNummber());
		values.put(DatabaseHelper.LANDUANSHULIANG, address.getSuoshubiandianzhan());
		values.put(DatabaseHelper.LANDUANCAIHZI  , address.getSuoshuxianlu());
		values.put(DatabaseHelper.LANDUANCHICUN, address.getDianyadengji());
		values.put(DatabaseHelper.LANDUANJINGCHANG , address.getStartname());
		values.put(DatabaseHelper.LANDUANJINGKUAN, address.getEndname());
		values.put(DatabaseHelper.LANDUANJINGNEISHEN, address.getPushefangshi());
		values.put(DatabaseHelper.LANDUANXINGHAO, address.getDianlanxinghao());
		values.put(DatabaseHelper.LANDUANJINGSHEN, address.getChangdu());
		values.put(DatabaseHelper.LANDUANGONGNNEG, address.getZhuangtai());
		values.put(DatabaseHelper.LANDUANLEIXING, address.getStartjingdu());
		values.put(DatabaseHelper.LANDUANJINGDU, address.getStartweidu());
		values.put(DatabaseHelper.LANDUANWEIDU, address.getEndjingdu());
		values.put(DatabaseHelper.LANDUANGAODU, address.getEndweidu());
		values.put(DatabaseHelper.LANDUANSHIGONGDANWEI, address.getXuhao());
		values.put(DatabaseHelper.LANDUANBEIZHU, address.getShebei_id());
		values.put(DatabaseHelper.LANDUANBIAOQIANHAO, address.getBiaoqianhao());
		values.put(DatabaseHelper. LANDUANZUOYEDANWEI, address.getZuoyedanwei());
		values.put(DatabaseHelper.LANDUANXINZENGSHIJIAN, address.getXinzengshijian());
		values.put(DatabaseHelper.LANDUANSTSRTSHEBEI_ID, address.getStartShebei_id());
		values.put(DatabaseHelper.LANDUANENDSHEBEI_ID, address.getEndShebei_id());
		Long i = db.insert(DatabaseHelper.MY_DIANLANDUAN, null, values);
		manager.closeDatabase();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	public boolean updeteDuanAddress(DianLanDuanBean address){
		SQLiteDatabase db = manager.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.LANDUANID, address.getUuid());
		values.put(DatabaseHelper.LANDUANNAME, address.getName());
		values.put(DatabaseHelper.LANDUANNUMMBER , address.getNummber());
		values.put(DatabaseHelper.LANDUANSHULIANG, address.getSuoshubiandianzhan());
		values.put(DatabaseHelper.LANDUANCAIHZI  , address.getSuoshuxianlu());
		values.put(DatabaseHelper.LANDUANCHICUN, address.getDianyadengji());
		values.put(DatabaseHelper.LANDUANJINGCHANG , address.getStartname());
		values.put(DatabaseHelper.LANDUANJINGKUAN, address.getEndname());
		values.put(DatabaseHelper.LANDUANJINGNEISHEN, address.getPushefangshi());
		values.put(DatabaseHelper.LANDUANXINGHAO, address.getDianlanxinghao());
		values.put(DatabaseHelper.LANDUANJINGSHEN, address.getChangdu());
		values.put(DatabaseHelper.LANDUANGONGNNEG, address.getZhuangtai());
		values.put(DatabaseHelper.LANDUANLEIXING, address.getStartjingdu());
		values.put(DatabaseHelper.LANDUANJINGDU, address.getStartweidu());
		values.put(DatabaseHelper.LANDUANWEIDU, address.getEndjingdu());
		values.put(DatabaseHelper.LANDUANGAODU, address.getEndweidu());
		values.put(DatabaseHelper.LANDUANSHIGONGDANWEI, address.getXuhao());
		values.put(DatabaseHelper.LANDUANBEIZHU, address.getShebei_id());
		values.put(DatabaseHelper.LANDUANBIAOQIANHAO, address.getBiaoqianhao());
		values.put(DatabaseHelper. LANDUANZUOYEDANWEI, address.getZuoyedanwei());
		values.put(DatabaseHelper.LANDUANXINZENGSHIJIAN, address.getXinzengshijian());
		values.put(DatabaseHelper. LANDUANSTSRTSHEBEI_ID, address.getStartShebei_id());
		values.put(DatabaseHelper.LANDUANENDSHEBEI_ID, address.getEndShebei_id());
		long i = db.update( DatabaseHelper.MY_DIANLANDUAN, values,DatabaseHelper.LANDUANID+"=?", new String[]{address.getUuid()});
		if(i>0){
			return true;
		}else{
			return false;
		}
	}


	public List<DianLanDuanBean> queryDuanAddress(){
		SQLiteDatabase db = manager.getWritableDatabase();
		List<DianLanDuanBean> list = new ArrayList<DianLanDuanBean>();
  		Cursor cursor = db.query(DatabaseHelper.MY_DIANLANDUAN, new String[] { DatabaseHelper.LANDUANID, DatabaseHelper.LANDUANNAME, DatabaseHelper.LANDUANNUMMBER, DatabaseHelper.LANDUANSHULIANG, DatabaseHelper.LANDUANCAIHZI , DatabaseHelper.LANDUANCHICUN,DatabaseHelper.LANDUANJINGCHANG,DatabaseHelper.LANDUANJINGKUAN,DatabaseHelper.LANDUANJINGNEISHEN,DatabaseHelper.LANDUANXINGHAO,DatabaseHelper.LANDUANJINGSHEN,DatabaseHelper.LANDUANGONGNNEG,DatabaseHelper.LANDUANLEIXING,DatabaseHelper.LANDUANJINGDU,DatabaseHelper.LANDUANWEIDU,DatabaseHelper.LANDUANGAODU,DatabaseHelper.LANDUANSHIGONGDANWEI,DatabaseHelper.LANDUANBEIZHU,DatabaseHelper.LANDUANBIAOQIANHAO,DatabaseHelper.LANDUANZUOYEDANWEI,DatabaseHelper.LANDUANXINZENGSHIJIAN,DatabaseHelper.LANDUANSTSRTSHEBEI_ID,DatabaseHelper.LANDUANENDSHEBEI_ID},
				null,null, null, null, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				DianLanDuanBean  add = new DianLanDuanBean();
				add.setUuid((cursor.getString(0)));
				add.setName(cursor.getString(1));
				add.setNummber(cursor.getString(2));
				add.setSuoshubiandianzhan(cursor.getString(3));
				add.setSuoshuxianlu(cursor.getString(4));
				add.setDianyadengji(cursor.getString(5));
				add.setStartname(cursor.getString(6));
				add.setEndname(cursor.getString(7));
				add.setPushefangshi(cursor.getString(8));
				add.setDianlanxinghao(cursor.getString(9));
				add.setChangdu(cursor.getString(10));
				add.setZhuangtai(cursor.getString(11));
				add.setStartjingdu(cursor.getString(12));
				add.setStartweidu(cursor.getString(13));
				add.setEndjingdu(cursor.getString(14));
				add.setEndweidu(cursor.getString(15));
				add.setXuhao(cursor.getString(16));
				add.setShebei_id (cursor.getString(17));
				add.setBiaoqianhao(cursor.getString(18));
				add.setZuoyedanwei(cursor.getString(19));
				add.setXinzengshijian(cursor.getString(20));
				add.setStartShebei_id(cursor.getString(21));
				add.setEndShebei_id(cursor.getString(22));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}

	public List<DianLanDuanBean> queryNoDuanAddress(){
		SQLiteDatabase db = manager.getWritableDatabase();
		List<DianLanDuanBean> list = new ArrayList<DianLanDuanBean>();
		Cursor cursor = db.query(DatabaseHelper.MY_DIANLANDUAN, new String[] { DatabaseHelper.LANDUANID, DatabaseHelper.LANDUANNAME, DatabaseHelper.LANDUANNUMMBER, DatabaseHelper.LANDUANSHULIANG, DatabaseHelper.LANDUANCAIHZI , DatabaseHelper.LANDUANCHICUN,DatabaseHelper.LANDUANJINGCHANG,DatabaseHelper.LANDUANJINGKUAN,DatabaseHelper.LANDUANJINGNEISHEN,DatabaseHelper.LANDUANXINGHAO,DatabaseHelper.LANDUANJINGSHEN,DatabaseHelper.LANDUANGONGNNEG,DatabaseHelper.LANDUANLEIXING,DatabaseHelper.LANDUANJINGDU,DatabaseHelper.LANDUANWEIDU,DatabaseHelper.LANDUANGAODU,DatabaseHelper.LANDUANSHIGONGDANWEI,DatabaseHelper.LANDUANBEIZHU,DatabaseHelper.LANDUANBIAOQIANHAO,DatabaseHelper.LANDUANZUOYEDANWEI,DatabaseHelper.LANDUANXINZENGSHIJIAN,DatabaseHelper.LANDUANSTSRTSHEBEI_ID,DatabaseHelper.LANDUANENDSHEBEI_ID},
				null,null, null, null, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				DianLanDuanBean  add = new DianLanDuanBean();
				add.setUuid((cursor.getString(0)));
				add.setName(cursor.getString(1));
				add.setNummber(cursor.getString(2));
				add.setSuoshubiandianzhan(cursor.getString(3));
				add.setSuoshuxianlu(cursor.getString(4));
				add.setDianyadengji(cursor.getString(5));
				add.setStartname(cursor.getString(6));
				add.setEndname(cursor.getString(7));
				add.setPushefangshi(cursor.getString(8));
				add.setDianlanxinghao(cursor.getString(9));
				add.setChangdu(cursor.getString(10));
				add.setZhuangtai(cursor.getString(11));
				add.setStartjingdu(cursor.getString(12));
				add.setStartweidu(cursor.getString(13));
				add.setEndjingdu(cursor.getString(14));
				add.setEndweidu(cursor.getString(15));
				add.setXuhao(cursor.getString(16));
				add.setShebei_id (cursor.getString(17));
				add.setBiaoqianhao(cursor.getString(18));
				add.setZuoyedanwei(cursor.getString(19));
				add.setXinzengshijian(cursor.getString(20));
				add.setStartShebei_id(cursor.getString(21));
				add.setEndShebei_id(cursor.getString(22));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}

   //查看电缆段信息通过电缆段现场编号
	public List<DianLanDuanBean> queryDuanXinxiAddress(String dldnummber){
		SQLiteDatabase db = manager.getWritableDatabase();
		List<DianLanDuanBean> list = new ArrayList<DianLanDuanBean>();
		Cursor cursor = db.query(DatabaseHelper.MY_DIANLANDUAN, new String[] { DatabaseHelper.LANDUANID, DatabaseHelper.LANDUANNAME, DatabaseHelper.LANDUANNUMMBER, DatabaseHelper.LANDUANSHULIANG, DatabaseHelper.LANDUANCAIHZI , DatabaseHelper.LANDUANCHICUN,DatabaseHelper.LANDUANJINGCHANG,DatabaseHelper.LANDUANJINGKUAN,DatabaseHelper.LANDUANJINGNEISHEN,DatabaseHelper.LANDUANXINGHAO,DatabaseHelper.LANDUANJINGSHEN,DatabaseHelper.LANDUANGONGNNEG,DatabaseHelper.LANDUANLEIXING,DatabaseHelper.LANDUANJINGDU,DatabaseHelper.LANDUANWEIDU,DatabaseHelper.LANDUANGAODU,DatabaseHelper.LANDUANSHIGONGDANWEI,DatabaseHelper.LANDUANBEIZHU,DatabaseHelper.LANDUANBIAOQIANHAO,DatabaseHelper.LANDUANZUOYEDANWEI,DatabaseHelper.LANDUANXINZENGSHIJIAN,DatabaseHelper.LANDUANSTSRTSHEBEI_ID,DatabaseHelper.LANDUANENDSHEBEI_ID},
				DatabaseHelper.LANDUANNUMMBER+"=?",new String[] {dldnummber }, null, null, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				DianLanDuanBean  add = new DianLanDuanBean();
				add.setUuid((cursor.getString(0)));
				add.setName(cursor.getString(1));
				add.setNummber(cursor.getString(2));
				add.setSuoshubiandianzhan(cursor.getString(3));
				add.setSuoshuxianlu(cursor.getString(4));
				add.setDianyadengji(cursor.getString(5));
				add.setStartname(cursor.getString(6));
				add.setEndname(cursor.getString(7));
				add.setPushefangshi(cursor.getString(8));
				add.setDianlanxinghao(cursor.getString(9));
				add.setChangdu(cursor.getString(10));
				add.setZhuangtai(cursor.getString(11));
				add.setStartjingdu(cursor.getString(12));
				add.setStartweidu(cursor.getString(13));
				add.setEndjingdu(cursor.getString(14));
				add.setEndweidu(cursor.getString(15));
				add.setXuhao(cursor.getString(16));
				add.setShebei_id (cursor.getString(17));
				add.setBiaoqianhao(cursor.getString(18));
				add.setZuoyedanwei(cursor.getString(19));
				add.setXinzengshijian(cursor.getString(20));
				add.setStartShebei_id(cursor.getString(21));
				add.setEndShebei_id(cursor.getString(22));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}
	public boolean deleteDuanAddress(DianLanDuanBean address){
		SQLiteDatabase db = manager.getWritableDatabase();

		int i = db.delete(DatabaseHelper.MY_DIANLANDUAN,  DatabaseHelper.LANDUANID+"=?", new String[]{address.getUuid()});
		db.close();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}


	//endregion

	//region -----电缆井断面管理-------
	//电缆井断面
	public boolean insertDuanMianAddress(DianLanJingDuanMianBiao address){
		SQLiteDatabase db = manager.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.DUANMIANID, address.getUuid());
		values.put(DatabaseHelper.DUANMIANNAME, address.getName());
		values.put(DatabaseHelper.DUANMIANNUMMBER, address.getNummber());
		values.put(DatabaseHelper.DUANMIANFANGWEI, address.getFangwei());
		values.put(DatabaseHelper.DUANMIANDUANMIANXUHAO, address.getDuanmianxuhao());
		values.put(DatabaseHelper.DUANMIANSHANGBIANJU, address.getShangbianju());
		values.put(DatabaseHelper.DUANMIANXIABIANJU, address.getXiabianju());
		values.put(DatabaseHelper.DUANMIANZUOBINAJU, address.getZuobianju());
		values.put(DatabaseHelper.DUANMIANYOUBIANJU, address.getYoubianju());
		values.put(DatabaseHelper.DUANMIANHANGSHU, address.getHangshu());
		values.put(DatabaseHelper.DUANMIANLIESHU , address.getLieshu());
		Long i = db.insert( DatabaseHelper.MY_DUANMINA , null, values);
		manager.closeDatabase();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	public boolean updeteDuanMianAddress(DianLanJingDuanMianBiao  address){
		SQLiteDatabase db = manager.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.DUANMIANID, address.getUuid());
		values.put(DatabaseHelper.DUANMIANNAME, address.getName());
		values.put(DatabaseHelper.DUANMIANNUMMBER, address.getNummber());
		values.put(DatabaseHelper.DUANMIANFANGWEI, address.getFangwei());
		values.put(DatabaseHelper.DUANMIANDUANMIANXUHAO, address.getDuanmianxuhao());
		values.put(DatabaseHelper.DUANMIANSHANGBIANJU, address.getShangbianju());
		values.put(DatabaseHelper.DUANMIANXIABIANJU, address.getXiabianju());
		values.put(DatabaseHelper.DUANMIANZUOBINAJU, address.getZuobianju());
		values.put(DatabaseHelper.DUANMIANYOUBIANJU, address.getYoubianju());
		values.put(DatabaseHelper.DUANMIANHANGSHU, address.getHangshu());
		values.put(DatabaseHelper.DUANMIANLIESHU , address.getLieshu());
		long i = db.update(DatabaseHelper. MY_DUANMINA , values, DatabaseHelper.DUANMIANID+"=?", new String[]{address.getUuid()});
		if(i>0){
			return true;
		}else{
			return false;
		}
	}



	public List<DianLanJingDuanMianBiao> queryDuanMianAddress(){
		SQLiteDatabase db = manager.getWritableDatabase();
		List<DianLanJingDuanMianBiao> list = new ArrayList<DianLanJingDuanMianBiao >();
		Cursor cursor = db.query( DatabaseHelper.MY_DUANMINA , new String[] { DatabaseHelper.DUANMIANID, DatabaseHelper.DUANMIANNAME, DatabaseHelper.DUANMIANNUMMBER, DatabaseHelper.DUANMIANFANGWEI, DatabaseHelper.DUANMIANDUANMIANXUHAO ,DatabaseHelper. DUANMIANSHANGBIANJU,DatabaseHelper.DUANMIANXIABIANJU,DatabaseHelper.DUANMIANZUOBINAJU,DatabaseHelper.DUANMIANYOUBIANJU,DatabaseHelper.DUANMIANHANGSHU,DatabaseHelper.DUANMIANLIESHU},
				null,null, null, null, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				DianLanJingDuanMianBiao  add = new DianLanJingDuanMianBiao();
				add.setUuid((cursor.getString(0)));
				add.setName(cursor.getString(1));
				add.setNummber(cursor.getString(2));
				add.setFangwei(cursor.getString(3));
				add.setDuanmianxuhao(cursor.getString(4));
				add.setShangbianju(Integer.parseInt(cursor.getString(5)));
				add.setXiabianju(cursor.getString(6));
				add.setZuobianju(cursor.getString(7));
				add.setYoubianju(cursor.getString(8));
				add.setHangshu(cursor.getString(9));
				add.setLieshu(cursor.getString(10));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}

   //通过电缆井现场编号查询电缆井断面表
	public List<DianLanJingDuanMianBiao> queryDuanMianByDljAddress(String dljnummber){
		SQLiteDatabase db = manager.getWritableDatabase();
		List<DianLanJingDuanMianBiao> list = new ArrayList<DianLanJingDuanMianBiao >();
		Cursor cursor = db.query( DatabaseHelper.MY_DUANMINA , new String[] { DatabaseHelper.DUANMIANID, DatabaseHelper.DUANMIANNAME, DatabaseHelper.DUANMIANNUMMBER, DatabaseHelper.DUANMIANFANGWEI, DatabaseHelper.DUANMIANDUANMIANXUHAO ,DatabaseHelper. DUANMIANSHANGBIANJU,DatabaseHelper.DUANMIANXIABIANJU,DatabaseHelper.DUANMIANZUOBINAJU,DatabaseHelper.DUANMIANYOUBIANJU,DatabaseHelper.DUANMIANHANGSHU,DatabaseHelper.DUANMIANLIESHU},
				DatabaseHelper.DUANMIANNUMMBER+"=?",new String[] {dljnummber}, null, null, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				DianLanJingDuanMianBiao  add = new DianLanJingDuanMianBiao();
				add.setUuid((cursor.getString(0)));
				add.setName(cursor.getString(1));
				add.setNummber(cursor.getString(2));
				add.setFangwei(cursor.getString(3));
				add.setDuanmianxuhao(cursor.getString(4));
				add.setShangbianju(Integer.parseInt(cursor.getString(5)));
				add.setXiabianju(cursor.getString(6));
				add.setZuobianju(cursor.getString(7));
				add.setYoubianju(cursor.getString(8));
				add.setHangshu(cursor.getString(9));
				add.setLieshu(cursor.getString(10));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}



	public boolean deleteDuanMianAddress(){
		SQLiteDatabase db = manager.getWritableDatabase();
		int i = db.delete( DatabaseHelper.MY_DUANMINA, DatabaseHelper.DUANMIANFANGWEI +"=?", null);
		manager.closeDatabase();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}


//通过方位查询电缆井断面信息
	public List<DianLanJingDuanMianBiao> queryCheckDuanMianAddress(String fangwei ){
		SQLiteDatabase db = manager.getWritableDatabase();
		List<DianLanJingDuanMianBiao> list = new ArrayList<DianLanJingDuanMianBiao >();
		Cursor cursor = db.query(DatabaseHelper.MY_DUANMINA, new String[] { DatabaseHelper.DUANMIANID, DatabaseHelper.DUANMIANNAME, DatabaseHelper.DUANMIANNUMMBER, DatabaseHelper.DUANMIANFANGWEI, DatabaseHelper.DUANMIANDUANMIANXUHAO ,DatabaseHelper. DUANMIANSHANGBIANJU,DatabaseHelper.DUANMIANXIABIANJU,DatabaseHelper.DUANMIANZUOBINAJU,DatabaseHelper.DUANMIANYOUBIANJU,DatabaseHelper.DUANMIANHANGSHU,DatabaseHelper.DUANMIANLIESHU}, DatabaseHelper.DUANMIANFANGWEI+ "=?", new String[] { fangwei }, null, null, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				DianLanJingDuanMianBiao  add = new DianLanJingDuanMianBiao();
				add.setUuid((cursor.getString(0)));
				add.setName(cursor.getString(1));
				add.setNummber(cursor.getString(2));
				add.setFangwei(cursor.getString(3));
				add.setDuanmianxuhao(cursor.getString(4));
				add.setShangbianju(Integer.parseInt(cursor.getString(5)));
				add.setXiabianju(cursor.getString(6));
				add.setZuobianju(cursor.getString(7));
				add.setYoubianju(cursor.getString(8));
				add.setHangshu(cursor.getString(9));
				add.setLieshu(cursor.getString(10));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}


	//YiXing
	public List<DianLanJingDuanMianBiao> queryCheckYingXingDuanMianAddress(String duanmianname ){
		SQLiteDatabase db = manager.getWritableDatabase();
		List<DianLanJingDuanMianBiao> list = new ArrayList<DianLanJingDuanMianBiao >();
		Cursor cursor = db.query(DatabaseHelper.MY_DUANMINA, new String[] { DatabaseHelper.DUANMIANID, DatabaseHelper.DUANMIANNAME, DatabaseHelper.DUANMIANNUMMBER, DatabaseHelper.DUANMIANFANGWEI, DatabaseHelper.DUANMIANDUANMIANXUHAO ,DatabaseHelper. DUANMIANSHANGBIANJU,DatabaseHelper.DUANMIANXIABIANJU,DatabaseHelper.DUANMIANZUOBINAJU,DatabaseHelper.DUANMIANYOUBIANJU,DatabaseHelper.DUANMIANHANGSHU,DatabaseHelper.DUANMIANLIESHU}, DatabaseHelper.DUANMIANNAME+ "=?", new String[] { duanmianname}, null, null, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				DianLanJingDuanMianBiao  add = new DianLanJingDuanMianBiao();
				add.setUuid((cursor.getString(0)));
				add.setName(cursor.getString(1));
				add.setNummber(cursor.getString(2));
				add.setFangwei(cursor.getString(3));
				add.setDuanmianxuhao(cursor.getString(4));
				add.setShangbianju(Integer.parseInt(cursor.getString(5)));
				add.setXiabianju(cursor.getString(6));
				add.setZuobianju(cursor.getString(7));
				add.setYoubianju(cursor.getString(8));
				add.setHangshu(cursor.getString(9));
				add.setLieshu(cursor.getString(10));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}
	//endregion

	//region       -----电缆通道管理-----
	//电缆通道
	public boolean inserttongdaoAddress(DianlantongdaoBean address){
		SQLiteDatabase db = manager.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.TONGID, address.getUuid());
		values.put(DatabaseHelper.TONGNAME, address.getName());
		values.put(DatabaseHelper.TONGNUMMBER , address.getNummber());
		values.put(DatabaseHelper.TONGTONGDAOLONG, address.getTongdaolong());
		values.put(DatabaseHelper. TONGTJIEGOUXINGSHI, address.getJiegouxingshi());
		values.put(DatabaseHelper.TONGSTARTJINGDU, address.getStartjingdu());
		values.put(DatabaseHelper.TONGSTARTWEIDU , address.getStartweidu());
		values.put(DatabaseHelper.TONGENDJINGDU, address.getEndjingdu());
		values.put(DatabaseHelper.TONGENDWEIDU, address.getEndweidu());
		values.put(DatabaseHelper.TONGJINGNEISHEN, address.getDingbumaishen());
		values.put(DatabaseHelper.TONGJINGSHEN, address.getDibumaishen());
		values.put(DatabaseHelper.TONGJGUNAGGOUKUANDU, address.getGuangoukuandu());
		values.put(DatabaseHelper.TONGJINGSHULIANG, address.getJingshuliang());
		values.put(DatabaseHelper.TONGGUANJING, address.getGuanjing());
		values.put(DatabaseHelper.TONGCAIZHI, address.getCaizhi());
		values.put(DatabaseHelper.TONGLEIXING, address.getLeixing());
		values.put(DatabaseHelper.TONGSHIGONGDANWEI, address.getSigongdanwei());
		values.put(DatabaseHelper.TONGTOUYUNRIQI, address.getTouyunriqi());
		values.put(DatabaseHelper.TONGZUOYEDANWEI, address.getZuoyedanwei());
		values.put(DatabaseHelper.TONGXINZENGSHIJIAN, address.getXinzengshijian());
		values.put(DatabaseHelper.TONGGUAIDIANJINGDU, address.getGuaidianjingdu());
		values.put(DatabaseHelper.TONGGUAIDIANWEIDU, address.getGuaidianweidu());
		Long i = db.insert(DatabaseHelper.MY_DIANLANTONGDAO, null, values);
		manager.closeDatabase();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	public boolean updetetongdaoAddress(DianlantongdaoBean address){
		SQLiteDatabase db = manager.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.TONGID, address.getUuid());
		values.put(DatabaseHelper.TONGNAME, address.getName());
		values.put(DatabaseHelper.TONGNUMMBER , address.getNummber());
		values.put(DatabaseHelper.TONGTONGDAOLONG, address.getTongdaolong());
		values.put(DatabaseHelper. TONGTJIEGOUXINGSHI, address.getJiegouxingshi());
		values.put(DatabaseHelper.TONGSTARTJINGDU, address.getStartjingdu());
		values.put(DatabaseHelper.TONGSTARTWEIDU , address.getStartweidu());
		values.put(DatabaseHelper.TONGENDJINGDU, address.getEndjingdu());
		values.put(DatabaseHelper.TONGENDWEIDU, address.getEndweidu());
		values.put(DatabaseHelper.TONGJINGNEISHEN, address.getDingbumaishen());
		values.put(DatabaseHelper.TONGJINGSHEN, address.getDibumaishen());
		values.put(DatabaseHelper.TONGJGUNAGGOUKUANDU, address.getGuangoukuandu());
		values.put(DatabaseHelper.TONGJINGSHULIANG, address.getJingshuliang());
		values.put(DatabaseHelper.TONGGUANJING, address.getGuanjing());
		values.put(DatabaseHelper.TONGCAIZHI, address.getCaizhi());
		values.put(DatabaseHelper.TONGLEIXING, address.getLeixing());
		values.put(DatabaseHelper.TONGSHIGONGDANWEI, address.getSigongdanwei());
		values.put(DatabaseHelper.TONGTOUYUNRIQI, address.getTouyunriqi());
		values.put(DatabaseHelper.TONGZUOYEDANWEI, address.getZuoyedanwei());
		values.put(DatabaseHelper.TONGXINZENGSHIJIAN, address.getXinzengshijian());
		values.put(DatabaseHelper.TONGGUAIDIANJINGDU, address.getGuaidianjingdu());
		values.put(DatabaseHelper.TONGGUAIDIANWEIDU, address.getGuaidianweidu());
		long i = db.update( DatabaseHelper.MY_DIANLANTONGDAO, values, DatabaseHelper.TONGID+"=?", new String[]{address.getUuid()});
		if(i>0){
			return true;
		}else{
			return false;
		}
	}


	public List<DianlantongdaoBean> querytongdaoAddress(){
		SQLiteDatabase db = manager.getWritableDatabase();
		List<DianlantongdaoBean> list = new ArrayList<DianlantongdaoBean>();

		Cursor cursor = db.query(DatabaseHelper.MY_DIANLANTONGDAO, new String[] { DatabaseHelper.TONGID, DatabaseHelper.TONGNAME , DatabaseHelper.TONGNUMMBER, DatabaseHelper.TONGTONGDAOLONG, DatabaseHelper.TONGTJIEGOUXINGSHI, DatabaseHelper.TONGSTARTJINGDU,DatabaseHelper.TONGSTARTWEIDU,DatabaseHelper.TONGENDJINGDU,DatabaseHelper.TONGENDWEIDU,DatabaseHelper.TONGJINGNEISHEN,DatabaseHelper.TONGJINGSHEN,DatabaseHelper.TONGJGUNAGGOUKUANDU,DatabaseHelper.TONGJINGSHULIANG,DatabaseHelper.TONGGUANJING,DatabaseHelper.TONGCAIZHI,DatabaseHelper.TONGLEIXING,DatabaseHelper.TONGSHIGONGDANWEI,DatabaseHelper.TONGTOUYUNRIQI,DatabaseHelper.TONGZUOYEDANWEI,DatabaseHelper.TONGXINZENGSHIJIAN,DatabaseHelper.TONGGUAIDIANJINGDU,DatabaseHelper.TONGGUAIDIANWEIDU},
				null,null, null, null, null);

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				DianlantongdaoBean  add = new DianlantongdaoBean();
				add.setUuid((cursor.getString(0)));
				add.setName(cursor.getString(1));
				add.setNummber(cursor.getString(2));
				add.setTongdaolong(cursor.getString(3));
				add.setJiegouxingshi(cursor.getString(4));
				add.setStartjingdu(cursor.getString(5));
				add.setStartweidu(cursor.getString(6));
				add.setEndjingdu(cursor.getString(7));
				add.setEndweidu(cursor.getString(8));
				add.setDingbumaishen(cursor.getString(9));
				add.setDibumaishen(cursor.getString(10));
				add.setGuangoukuandu(cursor.getString(11));
				add.setJingshuliang(cursor.getString(12));
				add.setGuanjing(cursor.getString(13));
				add.setCaizhi(cursor.getString(14));
				add.setLeixing(cursor.getString(15));
				add.setSigongdanwei (cursor.getString(16));
				add.setTouyunriqi(cursor.getString(17));
				add.setZuoyedanwei(cursor.getString(18));
				add.setXinzengshijian(cursor.getString(19));
				add.setGuaidianjingdu(Double.parseDouble(cursor.getString(20)));
				add.setGuaidianweidu(Double.parseDouble(cursor.getString(21)));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}

//查询电缆通道信息通过电缆通道现场编号
	public List<DianlantongdaoBean>  querytongdaoXinxiAddress(String nummber){
		SQLiteDatabase db = manager.getWritableDatabase();
		List<DianlantongdaoBean> list = new ArrayList<DianlantongdaoBean>();

		Cursor cursor = db.query(DatabaseHelper.MY_DIANLANTONGDAO, new String[] { DatabaseHelper.TONGID, DatabaseHelper.TONGNAME , DatabaseHelper.TONGNUMMBER, DatabaseHelper.TONGTONGDAOLONG, DatabaseHelper.TONGTJIEGOUXINGSHI, DatabaseHelper.TONGSTARTJINGDU,DatabaseHelper.TONGSTARTWEIDU,DatabaseHelper.TONGENDJINGDU,DatabaseHelper.TONGENDWEIDU,DatabaseHelper.TONGJINGNEISHEN,DatabaseHelper.TONGJINGSHEN,DatabaseHelper.TONGJGUNAGGOUKUANDU,DatabaseHelper.TONGJINGSHULIANG,DatabaseHelper.TONGGUANJING,DatabaseHelper.TONGCAIZHI,DatabaseHelper.TONGLEIXING,DatabaseHelper.TONGSHIGONGDANWEI,DatabaseHelper.TONGTOUYUNRIQI,DatabaseHelper.TONGZUOYEDANWEI,DatabaseHelper.TONGXINZENGSHIJIAN,DatabaseHelper.TONGGUAIDIANJINGDU,DatabaseHelper.TONGGUAIDIANWEIDU},
				DatabaseHelper.TONGNUMMBER+"=?", new String[]{nummber}, null, null, null);

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				DianlantongdaoBean  add = new DianlantongdaoBean();
				add.setUuid((cursor.getString(0)));
				add.setName(cursor.getString(1));
				add.setNummber(cursor.getString(2));
				add.setTongdaolong(cursor.getString(3));
				add.setJiegouxingshi(cursor.getString(4));
				add.setStartjingdu(cursor.getString(5));
				add.setStartweidu(cursor.getString(6));
				add.setEndjingdu(cursor.getString(7));
				add.setEndweidu(cursor.getString(8));
				add.setDingbumaishen(cursor.getString(9));
				add.setDibumaishen(cursor.getString(10));
				add.setGuangoukuandu(cursor.getString(11));
				add.setJingshuliang(cursor.getString(12));
				add.setGuanjing(cursor.getString(13));
				add.setCaizhi(cursor.getString(14));
				add.setLeixing(cursor.getString(15));
				add.setSigongdanwei (cursor.getString(16));
				add.setTouyunriqi(cursor.getString(17));
				add.setZuoyedanwei(cursor.getString(18));
				add.setXinzengshijian(cursor.getString(19));
				add.setGuaidianjingdu(Double.parseDouble(cursor.getString(20)));
				add.setGuaidianweidu(Double.parseDouble(cursor.getString(21)));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}

	public boolean deletetongdaoAddress(DianLanJingDuanMianBiao address){
		SQLiteDatabase db = manager.getWritableDatabase();

		int i = db.delete(DatabaseHelper.MY_DIANLANTONGDAO,  DatabaseHelper.TONGID+"=?", new String[]{address.getUuid()});
		manager.closeDatabase();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}
	//endregion

	//region     ------孔位管理-------
	//断面孔位
	public boolean insertKongWeiAddress(DuanMianKongWei address){
		SQLiteDatabase db = manager.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.KONGWEIID, address.getUuid());
		values.put(DatabaseHelper.KONGWEINAME, address.getName());
		values.put(DatabaseHelper.KONGWEINUMMBER, address.getNummber());
		values.put(DatabaseHelper.KONGWEIGUANKONGLEIXIG, address.getGuankongleiixng());
		values.put(DatabaseHelper.KONGWEIGUANKONGCAIZHI, address.getGuankongcaizhi());
		values.put(DatabaseHelper.KONGWEIGUANKONGGUANJING, address.getGuankongguanjing());
		values.put(DatabaseHelper.KONGWEIHANGHAO, address.getHanghao());
		values.put(DatabaseHelper.KONGWEILIEHAO, address.getLiehao());
		Long i = db.insert(DatabaseHelper.MY_KONGWEI, null, values);
		manager.closeDatabase();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	public boolean updeteKongWeiAddress(DuanMianKongWei  address){
		SQLiteDatabase db = manager.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.KONGWEIID, address.getUuid());
		values.put(DatabaseHelper.KONGWEINAME, address.getName());
		values.put(DatabaseHelper.KONGWEINUMMBER, address.getNummber());
		values.put(DatabaseHelper.KONGWEIGUANKONGLEIXIG, address.getGuankongleiixng());
		values.put(DatabaseHelper.KONGWEIGUANKONGCAIZHI, address.getGuankongcaizhi());
		values.put(DatabaseHelper.KONGWEIGUANKONGGUANJING, address.getGuankongguanjing());
		values.put(DatabaseHelper.KONGWEIHANGHAO, address.getHanghao());
		values.put(DatabaseHelper.KONGWEILIEHAO, address.getLiehao());
		long i = db.update(DatabaseHelper.MY_KONGWEI, values, DatabaseHelper.KONGWEIID+"=?", new String[]{address.getUuid()});
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	public List<DuanMianKongWei> queryKongWeiAddress(){
		SQLiteDatabase db = manager.getWritableDatabase();
		List<DuanMianKongWei> list = new ArrayList<DuanMianKongWei>();

		Cursor cursor = db.query(DatabaseHelper.MY_KONGWEI, new String[] { DatabaseHelper.KONGWEIID, DatabaseHelper.KONGWEINAME, DatabaseHelper.KONGWEINUMMBER, DatabaseHelper.KONGWEIGUANKONGLEIXIG, DatabaseHelper.KONGWEIGUANKONGCAIZHI, DatabaseHelper.KONGWEIGUANKONGGUANJING,DatabaseHelper.KONGWEIHANGHAO,DatabaseHelper.KONGWEILIEHAO},
				null,null, null, null, null);

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				DuanMianKongWei  add = new DuanMianKongWei();
				add.setUuid((cursor.getString(0)));
				add.setName(cursor.getString(1));
				add.setNummber(cursor.getString(2));
				add.setGuankongleiixng(cursor.getString(3));
				add.setGuankongcaizhi(cursor.getString(4));
				add.setGuankongguanjing(cursor.getString(5));
				add.setHanghao(cursor.getString(6));
				add.setLiehao(cursor.getString(7));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}

	public boolean deleteKongWeiAddress(DuanMianKongWei  address){
		SQLiteDatabase db = manager.getWritableDatabase();

		int i = db.delete(DatabaseHelper.MY_KONGWEI, DatabaseHelper. KONGWEIID+"=?", new String[]{address.getUuid()});
		db.close();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

    //通过断面名称查看孔位信息
	public List<DuanMianKongWei> queryCheckKongWeiAddress(String name){
		SQLiteDatabase db = manager.getWritableDatabase();
		List<DuanMianKongWei> list = new ArrayList<DuanMianKongWei>();

		Cursor cursor = db.query(DatabaseHelper.MY_KONGWEI, new String[] { DatabaseHelper.KONGWEIID, DatabaseHelper.KONGWEINAME, DatabaseHelper.KONGWEINUMMBER, DatabaseHelper.KONGWEIGUANKONGLEIXIG, DatabaseHelper.KONGWEIGUANKONGCAIZHI, DatabaseHelper.KONGWEIGUANKONGGUANJING},
				DatabaseHelper.KONGWEINAME + "=?", new String[] {name}, null, null, null);

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				DuanMianKongWei  add = new DuanMianKongWei();
				add.setUuid((cursor.getString(0)));
				add.setName(cursor.getString(1));
				add.setNummber(cursor.getString(2));
				add.setGuankongleiixng(cursor.getString(3));
				add.setGuankongcaizhi(cursor.getString(4));
				add.setGuankongguanjing(cursor.getString(5));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}
	//endregion

	//region      ----电缆接头管理----
	//电缆接头
	public boolean insertDianLanJieTouddress(DianLanJieTou address){
		SQLiteDatabase db = manager.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.JIEKOUID, address.getUuid());
		values.put(DatabaseHelper.JIEKOUNAME, address.getJieTouName());
		values.put(DatabaseHelper.JIEKOUNUMMBER , address.getNummber());
		values.put(DatabaseHelper.JIEKOUSUOSUBIANDIANZHAN , address.getSuoshubiandianzhan());
		values.put(DatabaseHelper.JIEKOUSUOSUXIANLU  , address.getSuoshuxianlu());
		values.put(DatabaseHelper. JIEKOUANZHUANGWEIZHI, address.getAnzhuangweizhi());
		values.put(DatabaseHelper.JIEKOULEIXING , address.getAnzhuangweizhi_leixing());
		values.put(DatabaseHelper.JIEKOUBIAOQIANHAO , address.getBiaoqianhao());
		values.put(DatabaseHelper.JIEKOUJINGDU, address.getAnzhuangweizhi_jingdu());
		values.put(DatabaseHelper.JIEKOUWEIDU, address.getAnzhuangweizhi_weidu());
		values.put(DatabaseHelper.JIEKOUBEIZHU, address.getBeizhu());
		values.put(DatabaseHelper. JIEKOUZUOYEDANWEI, address.getZuoyedanwei());
		values.put(DatabaseHelper.JIEKOUXINZNEGSHIJIAN , address.getXinzengshijian());
		Long i = db.insert(DatabaseHelper.MY_JIETOU, null, values);
		manager.closeDatabase();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	public boolean updeteDianLanJieTouAddress(DianLanJieTou address){
		SQLiteDatabase db = manager.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.JIEKOUID, address.getUuid());
		values.put(DatabaseHelper.JIEKOUNAME, address.getJieTouName());
		values.put(DatabaseHelper.JIEKOUNUMMBER , address.getNummber());
		values.put(DatabaseHelper.JIEKOUSUOSUBIANDIANZHAN , address.getSuoshubiandianzhan());
		values.put(DatabaseHelper.JIEKOUSUOSUXIANLU  , address.getSuoshuxianlu());
		values.put(DatabaseHelper. JIEKOUANZHUANGWEIZHI, address.getAnzhuangweizhi());
		values.put(DatabaseHelper.JIEKOULEIXING , address.getAnzhuangweizhi_leixing());
		values.put(DatabaseHelper.JIEKOUBIAOQIANHAO , address.getBiaoqianhao());
		values.put(DatabaseHelper.JIEKOUJINGDU, address.getAnzhuangweizhi_jingdu());
		values.put(DatabaseHelper.JIEKOUWEIDU, address.getAnzhuangweizhi_weidu());
		values.put(DatabaseHelper.JIEKOUBEIZHU, address.getBeizhu());
		values.put(DatabaseHelper. JIEKOUZUOYEDANWEI, address.getZuoyedanwei());
		values.put(DatabaseHelper.JIEKOUXINZNEGSHIJIAN , address.getXinzengshijian());
		long i = db.update(DatabaseHelper.MY_JIETOU, values,DatabaseHelper.JIEKOUID+"=?", new String[]{address.getUuid()});
		if(i>0){
			return true;
		}else{
			return false;
		}
	}


	public List<DianLanJieTou> queryDianLanJieTouAddress(){
		SQLiteDatabase db = manager.getWritableDatabase();
		List<DianLanJieTou> list = new ArrayList<DianLanJieTou>();

		Cursor cursor = db.query(DatabaseHelper.MY_JIETOU, new String[] { DatabaseHelper.JIEKOUID, DatabaseHelper.JIEKOUNAME, DatabaseHelper.JIEKOUNUMMBER, DatabaseHelper.JIEKOUSUOSUBIANDIANZHAN,DatabaseHelper.JIEKOUSUOSUXIANLU, DatabaseHelper.JIEKOUANZHUANGWEIZHI,DatabaseHelper.JIEKOULEIXING,DatabaseHelper. JIEKOUBIAOQIANHAO,DatabaseHelper. JIEKOUJINGDU ,DatabaseHelper. JIEKOUWEIDU  ,DatabaseHelper. JIEKOUBEIZHU ,DatabaseHelper. JIEKOUZUOYEDANWEI,DatabaseHelper. JIEKOUXINZNEGSHIJIAN},
				null,null, null, null, null);

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				DianLanJieTou add = new DianLanJieTou();
				add.setUuid((cursor.getString(0)));
				add.setJieTouName(cursor.getString(1));
				add.setNummber(cursor.getString(2));
				add.setSuoshubiandianzhan(cursor.getString(3));
				add.setSuoshuxianlu(cursor.getString(4));
				add.setAnzhuangweizhi(cursor.getString(5));
				add.setAnzhuangweizhi_leixing(cursor.getString(6));
				add.setBiaoqianhao(cursor.getString(7));
				add.setAnzhuangweizhi_jingdu(cursor.getString(8));
				add.setAnzhuangweizhi_weidu(cursor.getString(9));
				add.setBeizhu(cursor.getString(10));
				add.setZuoyedanwei(cursor.getString(11));
				add.setXinzengshijian(cursor.getString(12));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}

	public boolean deleteJietouAddress(DianLanJieTou address){
		SQLiteDatabase db = manager.getWritableDatabase();

		int i = db.delete(DatabaseHelper.MY_JIETOU,  DatabaseHelper.JIEKOUID+"=?", new String[]{address.getUuid()});
		manager.closeDatabase();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}
	//endregion

	//region     ----隐患信息管理----
	//隐患信息
	public boolean insertYingHuanXinddress(YingHuanXinXi address){
		SQLiteDatabase db = manager.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper. YINGHUANID, address.getUuid());
		values.put(DatabaseHelper.YINGHUANYINGHUANDENGJI, address.getYinghuandengji());
		values.put(DatabaseHelper.YINGHUANLEIXING, address.getYinghuanleixing());
		values.put(DatabaseHelper.YINGHUANYINGHUANBEIZHU, address.getYinghuanbeizhu());
		values.put(DatabaseHelper.YINGHUANYINGHUANWEIZHI, address.getYinghuanweizhi());
		values.put(DatabaseHelper. YINGHUANJINGDU , address.getJingdu());
		values.put(DatabaseHelper.YINGHUANWEIDU, address.getWeidu());
		values.put(DatabaseHelper.YINGHUANXINZENGSHIJIAN , address.getXinzengshijian());
		values.put(DatabaseHelper.YINGHUANZUOYEDANWEI , address.getZuoyedanwei());
		Long i = db.insert(DatabaseHelper. MY_YINGHUAN, null, values);
		manager.closeDatabase();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	public boolean updeteYingHuanXinAddress( YingHuanXinXi  address){
		SQLiteDatabase db = manager.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper. YINGHUANID, address.getUuid());
		values.put(DatabaseHelper.YINGHUANYINGHUANDENGJI, address.getYinghuandengji());
		values.put(DatabaseHelper.YINGHUANLEIXING, address.getYinghuanleixing());
		values.put(DatabaseHelper.YINGHUANYINGHUANBEIZHU, address.getYinghuanbeizhu());
		values.put(DatabaseHelper.YINGHUANYINGHUANWEIZHI, address.getYinghuanweizhi());
		values.put(DatabaseHelper. YINGHUANJINGDU, address.getJingdu());
		values.put(DatabaseHelper.YINGHUANWEIDU , address.getWeidu());
		values.put(DatabaseHelper.YINGHUANXINZENGSHIJIAN , address.getXinzengshijian());
		values.put(DatabaseHelper.YINGHUANZUOYEDANWEI , address.getZuoyedanwei());
		long i = db.update(DatabaseHelper. MY_YINGHUAN, values,DatabaseHelper.YINGHUANID+"=?", new String[]{address.getUuid()});
		if(i>0){
			return true;
		}else{
			return false;
		}
	}


	public List<YingHuanXinXi> queryYingHuanXinAddress(){
		SQLiteDatabase db = manager.getWritableDatabase();
		List< YingHuanXinXi> list = new ArrayList< YingHuanXinXi>();

		Cursor cursor = db.query(DatabaseHelper. MY_YINGHUAN, new String[] { DatabaseHelper.YINGHUANID , DatabaseHelper.YINGHUANYINGHUANDENGJI , DatabaseHelper.YINGHUANLEIXING ,DatabaseHelper.YINGHUANYINGHUANBEIZHU,DatabaseHelper.YINGHUANYINGHUANWEIZHI, DatabaseHelper.YINGHUANJINGDU,DatabaseHelper.YINGHUANWEIDU,DatabaseHelper.YINGHUANXINZENGSHIJIAN,DatabaseHelper.YINGHUANZUOYEDANWEI},null,null, null, null, null);

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				YingHuanXinXi  add = new YingHuanXinXi();
				add.setUuid((cursor.getString(0)));
				add.setYinghuandengji(cursor.getString(1));
				add.setYinghuanleixing(cursor.getString(2));
				add.setYinghuanbeizhu(cursor.getString(3));
				add.setYinghuanweizhi(cursor.getString(4));
				add.setJingdu(cursor.getString(5));
				add.setWeidu(cursor.getString(6));
				add.setXinzengshijian(cursor.getString(7));
				add.setZuoyedanwei(cursor.getString(8));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}

	public boolean deleteYingHuanXinAddress( YingHuanXinXi address){
		SQLiteDatabase db = manager.getWritableDatabase();

		int i = db.delete(DatabaseHelper. MY_YINGHUAN,  DatabaseHelper.YINGHUANID +"=?", new String[]{address.getUuid()});
		manager.closeDatabase();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}
	//endregion

	//region          -----电缆通道电缆段关联表相关操作-----
	//更新电缆通道现场编号
	public boolean updateSql( String  sql){
		try {
			SQLiteDatabase db = manager.getWritableDatabase();
			db.execSQL(sql);
			return  true;
		} catch (Exception e) {
			return  false;
		}

	}

	//电缆通道现场编号
	public List<String> GetDLTDNUMBERBySQL( String  sql, String[] ags){
		List<String> list =new ArrayList<String>();
		SQLiteDatabase db = manager.getWritableDatabase();
		Cursor cursor =db.rawQuery(sql,ags);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				String dltdnumber =cursor.getString(0);
				list.add(dltdnumber);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}

    //获取电缆通道关联表BY设备id或电联井现场编号
	public List<DltdglBean> GetDLTDSql( String  id){
		SQLiteDatabase db = manager.getWritableDatabase();
		List<DltdglBean> list = new ArrayList<DltdglBean>();
		Cursor cursor = db.query(DatabaseHelper.MY_DLTDG, new String[] { DatabaseHelper.DLTDGID, DatabaseHelper.DLTDNUMMBER, DatabaseHelper.SHIBEI_DLTDNUMMBER,DatabaseHelper.DLTDGXUHAO,DatabaseHelper.DLTDTYPE}, DatabaseHelper.SHIBEI_DLTDNUMMBER + "=?", new String[] { id }, null, null, null);

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				DltdglBean  add = new DltdglBean();
				add.setId((cursor.getString(0)));
				add.setNummber(cursor.getString(1));
				add.setShiBei_nummber(cursor.getString(2));
				add.setXuHao(cursor.getString(3));
				add.setShebeiType(cursor.getString(4));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}
	//获取电缆段关联表BY电缆段现场编号
	public List<DldglBean> GetDLDByDltdNumber( String  number){
		SQLiteDatabase db = manager.getWritableDatabase();
		List<DldglBean> list = new ArrayList<DldglBean>();
		Cursor cursor = db.query(DatabaseHelper.MY_DLDGL, new String[] { DatabaseHelper.DLDGLID, DatabaseHelper.DLDGLNUMMBER, DatabaseHelper.SHIBEI_DLDGLNUMMBER,DatabaseHelper.DLDGLGXUHAO}, DatabaseHelper.DLDGLNUMMBER+ "=?", new String[] { number }, null, null, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				DldglBean  add = new DldglBean();
				add.setId((cursor.getString(0)));
				add.setNummber(cursor.getString(1));
				add.setShiBei_nummber(cursor.getString(2));
				add.setShiBei_xuHao(cursor.getString(3));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}
	//通过电缆段现场编号或取坐标经纬度
	public List<DianlantongdaoBean> GetDLTDLatLng(String  number){
		SQLiteDatabase db = manager.getWritableDatabase();
		List<DianlantongdaoBean> list = new ArrayList<DianlantongdaoBean>();
		Cursor cursor = db.query(DatabaseHelper.MY_DIANLANTONGDAO, new String[] { DatabaseHelper.TONGID, DatabaseHelper.TONGNAME , DatabaseHelper.TONGNUMMBER, DatabaseHelper.TONGTONGDAOLONG, DatabaseHelper.TONGTJIEGOUXINGSHI, DatabaseHelper.TONGSTARTJINGDU,DatabaseHelper.TONGSTARTWEIDU,DatabaseHelper.TONGENDJINGDU,DatabaseHelper.TONGENDWEIDU,DatabaseHelper.TONGJINGNEISHEN,DatabaseHelper.TONGJINGSHEN,DatabaseHelper.TONGJGUNAGGOUKUANDU,DatabaseHelper.TONGJINGSHULIANG,DatabaseHelper.TONGGUANJING,DatabaseHelper.TONGCAIZHI,DatabaseHelper.TONGLEIXING,DatabaseHelper.TONGSHIGONGDANWEI,DatabaseHelper.TONGTOUYUNRIQI,DatabaseHelper.TONGZUOYEDANWEI,DatabaseHelper.TONGXINZENGSHIJIAN,DatabaseHelper.TONGGUAIDIANJINGDU,DatabaseHelper.TONGGUAIDIANWEIDU},
				DatabaseHelper.TONGNUMMBER + "=?", new String[] { number }, null, null, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				DianlantongdaoBean  add = new DianlantongdaoBean();
				add.setUuid((cursor.getString(0)));
				add.setName(cursor.getString(1));
				add.setNummber(cursor.getString(2));
				add.setTongdaolong(cursor.getString(3));
				add.setJiegouxingshi(cursor.getString(4));
				add.setStartjingdu(cursor.getString(5));
				add.setStartweidu(cursor.getString(6));
				add.setEndjingdu(cursor.getString(7));
				add.setEndweidu(cursor.getString(8));
				add.setDingbumaishen(cursor.getString(9));
				add.setDibumaishen(cursor.getString(10));
				add.setGuangoukuandu(cursor.getString(11));
				add.setJingshuliang(cursor.getString(12));
				add.setGuanjing(cursor.getString(13));
				add.setCaizhi(cursor.getString(14));
				add.setLeixing(cursor.getString(15));
				add.setSigongdanwei (cursor.getString(16));
				add.setTouyunriqi(cursor.getString(17));
				add.setZuoyedanwei(cursor.getString(18));
				add.setXinzengshijian(cursor.getString(19));
				add.setGuaidianjingdu(Double.parseDouble(cursor.getString(20)));
				add.setGuaidianweidu(Double.parseDouble(cursor.getString(21)));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}



//	//获取电缆井断面孔位名称BY电缆段现场编号
//	public List<DltdglBean> GetDLTDByDltdNumber( String  number){
//		SQLiteDatabase db = manager.getWritableDatabase();
//		List<DltdglBean> list = new ArrayList<DltdglBean>();
//		Cursor cursor = db.query(DatabaseHelper.MY_DLTDG, new String[] { DatabaseHelper.DLTDGID, DatabaseHelper.DLTDNUMMBER, DatabaseHelper.SHIBEI_DLTDNUMMBER,DatabaseHelper.DLTDGXUHAO,DatabaseHelper.DLTDTYPE}, DatabaseHelper.DLTDNUMMBER + "=?", new String[] { number }, null, null, null);
//
//		if (cursor.getCount() > 0) {
//			cursor.moveToFirst();
//			while (!cursor.isAfterLast()){
//				DltdglBean  add = new DltdglBean();
//				add.setId((cursor.getString(0)));
//				add.setNummber(cursor.getString(1));
//				add.setShiBei_nummber(cursor.getString(2));
//				add.setXuHao(cursor.getString(3));
//				add.setShebeiType(cursor.getString(4));
//				list.add(add);
//				cursor.moveToNext();
//			}
//			cursor.close();
//			manager.closeDatabase();
//			return list;
//		} else {
//			return null;
//		}
//	}


	//获取电BY电缆通道现场编号
	public List<DltdglBean> GetDLTDByDltdNumber( String  number){
		SQLiteDatabase db = manager.getWritableDatabase();
		List<DltdglBean> list = new ArrayList<DltdglBean>();
		Cursor cursor = db.query(DatabaseHelper.MY_DLTDG, new String[] { DatabaseHelper.DLTDGID, DatabaseHelper.DLTDNUMMBER, DatabaseHelper.SHIBEI_DLTDNUMMBER,DatabaseHelper.DLTDGXUHAO,DatabaseHelper.DLTDTYPE}, DatabaseHelper.DLTDNUMMBER + "=?", new String[] { number }, null, null, null);

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				DltdglBean  add = new DltdglBean();
				add.setId((cursor.getString(0)));
				add.setNummber(cursor.getString(1));
				add.setShiBei_nummber(cursor.getString(2));
				add.setXuHao(cursor.getString(3));
				add.setShebeiType(cursor.getString(4));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}
	//电缆通道关联
	public boolean insertDltdgldress(DltdglBean address){
		SQLiteDatabase db = manager.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper. DLTDGID, address.getId());
		values.put(DatabaseHelper.DLTDNUMMBER, address.getNummber());
		values.put(DatabaseHelper.SHIBEI_DLTDNUMMBER, address.getShiBei_nummber());
		values.put(DatabaseHelper.DLTDGXUHAO, address.getXuHao());
		values.put(DatabaseHelper.DLTDTYPE, address.getShebeiType());
		Long i = db.insert(DatabaseHelper. MY_DLTDG, null, values);
		manager.closeDatabase();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	public boolean updeteDltdglAddress( DltdglBean  address){
		SQLiteDatabase db = manager.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper. DLTDGID, address.getId());
		values.put(DatabaseHelper.DLTDNUMMBER, address.getNummber());
		values.put(DatabaseHelper.SHIBEI_DLTDNUMMBER, address.getShiBei_nummber());
		values.put(DatabaseHelper.DLTDGXUHAO, address.getXuHao());
		values.put(DatabaseHelper.DLTDTYPE, address.getShebeiType());
		long i = db.update(DatabaseHelper. MY_DLTDG, values,DatabaseHelper.DLTDGID+"=?", new String[]{address.getId()});
		if(i>0){
			return true;
		}else{
			return false;
		}
	}


	public List<DltdglBean> queryDltdglAddress(){
		SQLiteDatabase db = manager.getWritableDatabase();
		List< DltdglBean> list = new ArrayList<DltdglBean>();

		Cursor cursor = db.query(DatabaseHelper. MY_DLTDG, new String[] { DatabaseHelper.DLTDGID, DatabaseHelper.DLTDNUMMBER, DatabaseHelper.SHIBEI_DLTDNUMMBER,DatabaseHelper.DLTDGXUHAO,DatabaseHelper.DLTDTYPE},null,null, null, null, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				DltdglBean  add = new DltdglBean();
				add.setId((cursor.getString(0)));
				add.setNummber(cursor.getString(1));
				add.setShiBei_nummber(cursor.getString(2));
				add.setXuHao(cursor.getString(3));
				add.setShebeiType(cursor.getString(4));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}

	public boolean deleteDltdglAddress( DltdglBean address){
		SQLiteDatabase db = manager.getWritableDatabase();

		int i = db.delete(DatabaseHelper.MY_DLTDG,  DatabaseHelper.DLTDGID +"=?", new String[]{address.getId()});
		manager.closeDatabase();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}



	//电缆段关联
	public boolean insertDldgldress(DldglBean address){
		SQLiteDatabase db = manager.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper. DLDGLID, address.getId());
		values.put(DatabaseHelper.DLDGLNUMMBER, address.getNummber());
		values.put(DatabaseHelper.SHIBEI_DLDGLNUMMBER, address.getShiBei_nummber());
		values.put(DatabaseHelper.DLDGLGXUHAO, address.getShiBei_xuHao());
		Long i = db.insert(DatabaseHelper. MY_DLDGL, null, values);
		manager.closeDatabase();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	public boolean updeteDldglAddress( DldglBean  address){
		SQLiteDatabase db = manager.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper. DLDGLID, address.getId());
		values.put(DatabaseHelper.DLDGLNUMMBER, address.getNummber());
		values.put(DatabaseHelper.SHIBEI_DLDGLNUMMBER, address.getShiBei_nummber());
		values.put(DatabaseHelper.DLDGLGXUHAO, address.getShiBei_xuHao());
		long i = db.update(DatabaseHelper. MY_DLDGL, values,DatabaseHelper.DLDGLID+"=?", new String[]{address.getId()});
		if(i>0){
			return true;
		}else{
			return false;
		}
	}


	public List<DldglBean> queryDldglAddress(){
		SQLiteDatabase db = manager.getWritableDatabase();
		List< DldglBean> list = new ArrayList<DldglBean>();

		Cursor cursor = db.query(DatabaseHelper. MY_DLDGL, new String[] { DatabaseHelper.DLDGLID, DatabaseHelper.DLDGLNUMMBER, DatabaseHelper.SHIBEI_DLDGLNUMMBER,DatabaseHelper.DLDGLGXUHAO},null,null, null, null, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()){
				DldglBean  add = new DldglBean();
				add.setId((cursor.getString(0)));
				add.setNummber(cursor.getString(1));
				add.setShiBei_nummber(cursor.getString(2));
				add.setShiBei_xuHao(cursor.getString(3));
				list.add(add);
				cursor.moveToNext();
			}
			cursor.close();
			manager.closeDatabase();
			return list;
		} else {
			return null;
		}
	}

	public boolean deleteDldglAddress( DldglBean address){
		SQLiteDatabase db = manager.getWritableDatabase();

		int i = db.delete(DatabaseHelper.MY_DLDGL,  DatabaseHelper.DLDGLID+"=?", new String[]{address.getId()});
		manager.closeDatabase();
		if(i>0){
			return true;
		}else{
			return false;
		}
	}
	//endregion
}