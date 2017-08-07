package dlgx.gis.com.dlgx.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

/**
 * 数据库助手类
 * 
 * @author zihao
 * 
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	public static final int VERSION = 1;// 版本号
	// private static final String DB_NAME = "ZiHao.db";// 数据库名称
	public static final String DB_NAME = SDBHelper.DB_DIR + File.separator + "ZiHao.db";
	public static final String TABLE_NAME = "auto";// 表名
	public static final String MY_SHEBEI="myshebei";//设备表
	public static  final String MY_DIANLANDUAN= "mydianlanduan";//电缆段
	public static final  String MY_DIANLANTONGDAO= "mydainlantongdao";//电缆通道
	public static final String MY_DLTDG= "mydltdg";//电缆通道关联表
	public static final String MY_DLDGL= "mydldgl";//电缆段关联表

	public static final  String MY_CABLEPIT = "mycablepit";//电缆井
	public  static final String MY_DUANMINA = "myduanmian";//断面
	public static final  String MY_KONGWEI = "myKongWei";//孔位

	public static final String MY_JIETOU= "mydianlanjietou";//电缆接头
	public static final String MY_YINGHUAN= "myyinghuanxinxi";//隐患信息


	//电缆井
	public  static final String JINGID = "uuid";
	public  static final String JINGNAME = "name";
	public  static final String JINGNUMMBER= "nummber";
	public  static final String JINGSHULIANG= "shuliang";
	public  static final String JINGCAIHZI= "caizhi";
	public  static final String JINGXINGZHUANG= "xingzhuang";
	public  static final String JINGCHICUN= "chicun";
	public  static final String  JINGJINGCHANG= "jingchang";
	public  static final String JINGJINGKUAN= "jingkuan";
	public  static final String JINGJINGNEISHEN= "jingneishen";
	public  static final String JINGJINGSHEN= "jingshen";
	public  static final String  JINGGONGNNEG= "gongneng";
	public  static final String JINGLEIXING= "leixing";
	public  static final String JINGJINGINGDU= "jingdu";
	public  static final String JINGWEIDU= "weidu";
	public  static final String  JINGGAODU= "gaodu";
	public  static final String JINGJIETOUSHULIANG= "jietoushuliang";
	public  static final String JINGSHEBEIID= "shebeiId";
	public  static final String JINGDIANZIBAINQIANBIANHAO= "dianzibainqianbianhao";
	public  static final String  JINGOUYUNRIQI= "touyunriqi";
	public  static final String JINGSHIGONGDANWEI= "shigongdanwei";
	public  static final String JINGBEIZHU= "beizhu";
	public  static final String JINGZUOYEDANWEI= "zuoyedanwei";
	public  static final String JINGXINZENGSHIJIAN= "xinzengshijian";
	public  static final String JINGADRESS= "adress";

	//电缆段
	public  static final String LANDUANID = "uuid";
	public  static final String LANDUANNAME = "name";
	public  static final String LANDUANNUMMBER= "nummber";
	public  static final String LANDUANSHULIANG= "suoshubiandianzhan";
	public  static final String LANDUANCAIHZI= "suoshuxianlu ";
	public  static final String LANDUANCHICUN= "dianyadengji";
	public  static final String  LANDUANJINGCHANG= "startname";
	public  static final String LANDUANJINGKUAN= "endname";
	public  static final String LANDUANJINGNEISHEN= "pushefangshi";
	public  static final String LANDUANXINGHAO= "dianlanxinghao";
	public  static final String LANDUANJINGSHEN= "changdu";
	public  static final String  LANDUANGONGNNEG= "zhuangtai ";
	public  static final String LANDUANLEIXING= "startjingdu";
	public  static final String LANDUANJINGDU= "startweidu";
	public  static final String LANDUANWEIDU= "endjingdu";
	public  static final String  LANDUANGAODU= "endweidu";
	public  static final String LANDUANSHIGONGDANWEI= "xuhao";
	public  static final String LANDUANBEIZHU= "shebei_id";
	public  static final String LANDUANBIAOQIANHAO= "biaoqianhao";
	public  static final String LANDUANZUOYEDANWEI= "zuoyedanwei";
	public  static final String LANDUANXINZENGSHIJIAN= "xinzengshijian";
	public  static final String LANDUANSTSRTSHEBEI_ID= "startShebei_id";
	public  static final String LANDUANENDSHEBEI_ID= "endShebei_id";




	//电缆井断面
	public  static final String DUANMIANID = "uuid";
	public  static final String DUANMIANNAME = "name";
	public  static final String DUANMIANNUMMBER= "nummber";
	public  static final String DUANMIANFANGWEI= "fangwei";
	public  static final String DUANMIANDUANMIANXUHAO = "duanmianxuhao";
	public  static final String DUANMIANSHANGBIANJU= "shangbianju";
	public  static final String DUANMIANXIABIANJU= "xiabianju";
	public  static final String  DUANMIANZUOBINAJU= "zuobianju";
	public  static final String DUANMIANYOUBIANJU= "youbianju";
	public  static final String DUANMIANHANGSHU= "hangshu";
	public  static final String DUANMIANLIESHU= "lieshu";

	//电缆通道
	public  static final String TONGID = "uuid";
	public  static final String TONGNAME = "name";
	public  static final String TONGNUMMBER= "nummber";
	public  static final String TONGTONGDAOLONG= "tongdaolong";
	public  static final String TONGTJIEGOUXINGSHI= "jiegouxingshi";
	public  static final String TONGSTARTJINGDU= "startjingdu";
	public  static final String TONGSTARTWEIDU= "startweidu";
	public  static final String  TONGENDJINGDU= "endjingdu";
	public  static final String TONGENDWEIDU= "endweidu";
	public  static final String TONGJINGNEISHEN= "dingbumaishen";
	public  static final String TONGJINGSHEN= "dibumaishen";
	public  static final String TONGJGUNAGGOUKUANDU= "guangoukuandu";
	public  static final String TONGJINGSHULIANG= "jingshuliang";
	public  static final String TONGGUANJING= "guanjing";
	public  static final String TONGCAIZHI= "caizhi";
	public  static final String  TONGLEIXING= "leixing";
	public  static final String TONGSHIGONGDANWEI= "sigongdanwei";
	public  static final String TONGTOUYUNRIQI= "touyunriqi";
	public  static final String TONGZUOYEDANWEI= "zuoyedanwei";
	public  static final String TONGXINZENGSHIJIAN= "xinzengshijian";
	public  static final String TONGGUAIDIANJINGDU= "guaidianjingdu";
	public  static final String TONGGUAIDIANWEIDU= "guaidianweidu";

	//断面孔位
	public  static final String KONGWEIID = "uuid";
	public  static final String KONGWEINAME = "name";
	public  static final String KONGWEINUMMBER= "nummber";
	public  static final String KONGWEIGUANKONGLEIXIG= "guankongleiixng";
	public  static final String KONGWEIGUANKONGCAIZHI= "guankongcaizhi";
	public  static final String KONGWEIGUANKONGGUANJING = "guankongguanjing";
	public  static final String KONGWEIHANGHAO = "hanghao";
	public  static final String KONGWEILIEHAO = "liehao";
    //设备
	public  static final String SHEBEIID = "uuid";
	public  static final String SHEBEINAME = "name";
	public  static final String SHEBEIMAISHEN = "maishen";
	public  static final String SHEBEIJINGDU = "jingdu";
	public  static final String SHEBEIWEIDU = "weidu";
	public  static final String SHEBEIBEIZHU= "beizhu";
	public  static final String SHEBEIZUOYEDANWEI= "zuoyedanwei";
	public  static final String SHEBEIXINZNEGSHIJIAN = "xinznegshijian";
	public  static final String SHEBEILEIXING = "lx";


	//电缆接头信息
	public  static final String JIEKOUID = "uuid";
	public  static final String JIEKOUNAME = "jieTouName";
	public  static final String  JIEKOUNUMMBER = "nummber";
	public  static final String JIEKOUSUOSUBIANDIANZHAN = "suoshubiandianzhan";
	public  static final String JIEKOUSUOSUXIANLU = "suoshuxianlu";
	public  static final String JIEKOUANZHUANGWEIZHI = "anzhuangweizhi";
	public  static final String JIEKOULEIXING = "anzhuangweizhi_leixing";
	public  static final String JIEKOUBIAOQIANHAO = "biaoqianhao";
	public  static final String JIEKOUJINGDU = "anzhuangweizhi_jingdu";
	public  static final String JIEKOUWEIDU = "anzhuangweizhi_weidu";
	public  static final String JIEKOUBEIZHU= "beizhu";
	public  static final String JIEKOUZUOYEDANWEI= "zuoyedanwei";
	public  static final String JIEKOUXINZNEGSHIJIAN = "xinznegshijian";

	//隐患信息表
	public  static final String YINGHUANID = "uuid";
	public  static final String YINGHUANYINGHUANDENGJI = "yinghuandengji";
	public  static final String  YINGHUANLEIXING = "yinghuanleixing";
	public  static final String YINGHUANYINGHUANBEIZHU= "yinghuanbeizhu";
	public  static final String YINGHUANYINGHUANWEIZHI = "yinghuanweizhi";
	public  static final String YINGHUANJINGDU = "jingdu";
	public  static final String YINGHUANWEIDU = "weidu";
	public  static final String YINGHUANXINZENGSHIJIAN= "xinzengshijian";
	public  static final String YINGHUANZUOYEDANWEI = "zuoyedanwei";

	//电缆通道关联
	public  static final String DLTDGID = "id";
	public  static final String DLTDNUMMBER= "nummber";
	public  static final String SHIBEI_DLTDNUMMBER= "shiBei_nummber";
	public  static final String DLTDGXUHAO= "xuHao";
	public  static final String DLTDTYPE= "shebeiType";

	//电缆段关联
	public  static final String DLDGLID = "id";
	public  static final String DLDGLNUMMBER= "nummber";
	public  static final String SHIBEI_DLDGLNUMMBER= "shiBei_nummber";
	public  static final String DLDGLGXUHAO= "shiBei_xuHao";




	/**
	 * DatabaseHelper构造方法
	 * 
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public DatabaseHelper(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		createAutoTable(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	/**
	 * 创建数据库表
	 * 
	 * @param db
	 */
	private void createAutoTable(SQLiteDatabase db) {
		String auto="create table  if not exists "
				+ TABLE_NAME
				+ "(id  INTEGER PRIMARY KEY AUTOINCREMENT,title varchar(255),pinyin varchar(255))";
		// TODO Auto-generated method stub

		String myshebei = "create table " + MY_SHEBEI
				+ "("+SHEBEIID +" varchar(20) primary key ,"
				+ SHEBEINAME+" varchar(100)," +SHEBEIMAISHEN+" varchar(100),"
				+ SHEBEIJINGDU +" varchar(20)," +SHEBEIWEIDU +" varchar(20),"
				+ SHEBEIBEIZHU+" varchar(20)," +SHEBEIZUOYEDANWEI+" varchar(20)," +SHEBEILEIXING+" varchar(20),"
				+ SHEBEIXINZNEGSHIJIAN +" varchar(20))";



		String mycablepit = "create table " +  MY_CABLEPIT
				+ "("+JINGID+" varchar(20) primary key ,"
				+ JINGNAME+" varchar(100)," +JINGNUMMBER+" varchar(100),"
				+ JINGSHULIANG +" varchar(20)," +JINGCAIHZI+" varchar(20),"
				+ JINGXINGZHUANG+" varchar(20)," +JINGCHICUN+" varchar(20),"
				+ JINGJINGCHANG+" varchar(20)," +JINGJINGKUAN+" varchar(20),"
				+ JINGJINGNEISHEN +" int,"+JINGJINGSHEN+" int,"
				+ JINGGONGNNEG+" varchar(20)," +JINGLEIXING+" varchar(20),"
				+ JINGJINGINGDU+" varchar(20)," +JINGWEIDU+" varchar(20),"
				+ JINGGAODU+" varchar(20),"+JINGJIETOUSHULIANG+" varchar(20),"
				+  JINGSHEBEIID+" varchar(20),"+JINGDIANZIBAINQIANBIANHAO+" varchar(20),"
				+ JINGOUYUNRIQI+" varchar(20),"+JINGSHIGONGDANWEI+" varchar(20),"
				+ JINGBEIZHU+" varchar(20),"+JINGZUOYEDANWEI+" varchar(20),"
				+ JINGXINZENGSHIJIAN+" varchar(20),"
				+JINGADRESS+" varchar(20))";

		String mydianlanduan = "create table " + MY_DIANLANDUAN
				+ "("+LANDUANID+" varchar(20) primary key ,"
				+ LANDUANNAME+" varchar(100)," +LANDUANNUMMBER+" varchar(100),"
				+ LANDUANSHULIANG +" varchar(20)," +LANDUANCAIHZI  +" varchar(20),"
				+LANDUANCHICUN+" varchar(20)," + LANDUANJINGCHANG+" varchar(20),"
				+LANDUANJINGKUAN+" varchar(20)," + LANDUANJINGNEISHEN +" varchar(20),"
				+LANDUANXINGHAO+" varchar(20),"+LANDUANJINGSHEN+" varchar(20),"
				+ LANDUANGONGNNEG+" varchar(20)," +LANDUANLEIXING+" varchar(20),"
				+ LANDUANJINGDU+" varchar(20)," +LANDUANWEIDU+" varchar(20),"
				+ LANDUANGAODU+" varchar(20),"  +LANDUANSHIGONGDANWEI+" varchar(20),"
				+ LANDUANBEIZHU+" varchar(20),"+LANDUANBIAOQIANHAO+" varchar(20),"
				+LANDUANZUOYEDANWEI+" varchar(20),"+LANDUANXINZENGSHIJIAN+" varchar(20),"
				+LANDUANSTSRTSHEBEI_ID+" varchar(20),"
				+LANDUANENDSHEBEI_ID+" varchar(20))";



		String myduanmian = "create table " +  MY_DUANMINA
				+ "("+DUANMIANID+" varchar(20) primary key ,"
				+ DUANMIANNAME+" varchar(100)," +DUANMIANNUMMBER+" varchar(100),"
				+ DUANMIANFANGWEI+" varchar(20)," +DUANMIANDUANMIANXUHAO+" varchar(20),"
				+ DUANMIANSHANGBIANJU+" int," +DUANMIANXIABIANJU+" varchar(20),"
				+ DUANMIANZUOBINAJU+" varchar(20)," +DUANMIANYOUBIANJU+" varchar(20),"
				+ DUANMIANHANGSHU +" varchar(20),"
				+ DUANMIANLIESHU +" varchar(20))";

		String mydainlantongdao = "create table " +  MY_DIANLANTONGDAO
				+ "("+TONGID +" varchar(20) primary key ,"
				+ TONGNAME+" varchar(100)," +TONGNUMMBER+" varchar(100),"
				+ TONGTONGDAOLONG +" varchar(20)," +TONGTJIEGOUXINGSHI+" varchar(20),"
				+ TONGSTARTJINGDU+" varchar(20)," +TONGSTARTWEIDU+" varchar(20),"
				+ TONGENDJINGDU+" varchar(20)," +TONGENDWEIDU+" varchar(20),"
				+ TONGJINGNEISHEN+" varchar(20),"+TONGJINGSHEN+" varchar(20),"
				+ TONGJGUNAGGOUKUANDU+" varchar(20)," +TONGJINGSHULIANG+" varchar(20),"
				+ TONGGUANJING+" varchar(20)," +TONGCAIZHI+" varchar(20),"
				+ TONGLEIXING+" varchar(20),"  +TONGSHIGONGDANWEI+" varchar(20),"
				+ TONGTOUYUNRIQI+" varchar(20),"+TONGZUOYEDANWEI+" varchar(20),"
				+ TONGXINZENGSHIJIAN+" varchar(20),"+TONGGUAIDIANJINGDU+" double,"
				+ TONGGUAIDIANWEIDU+" double)";

		String myKongWei = "create table " + MY_KONGWEI
				+ "("+KONGWEIID+" varchar(20) primary key ,"
				+ KONGWEINAME+" varchar(100)," +KONGWEINUMMBER+" varchar(100),"
				+ KONGWEIGUANKONGLEIXIG +" varchar(20)," +KONGWEIGUANKONGCAIZHI +" varchar(20),"
				+ KONGWEIGUANKONGGUANJING +" varchar(20)," +KONGWEIHANGHAO +" varchar(20),"
		        + KONGWEILIEHAO  +" varchar(20))";






		String mydianlanjietou = "create table " +  MY_JIETOU
				+ "("+JIEKOUID +" varchar(20) primary key ,"
				+ JIEKOUNAME+" varchar(100)," + JIEKOUNUMMBER+" varchar(100),"
				+ JIEKOUSUOSUBIANDIANZHAN+" varchar(20)," +JIEKOUSUOSUXIANLU +" varchar(20),"
				+ JIEKOUANZHUANGWEIZHI+" varchar(20)," +JIEKOULEIXING +" varchar(20),"
				+ JIEKOUBIAOQIANHAO+" varchar(20)," +JIEKOUJINGDU+" varchar(20),"
				+ JIEKOUWEIDU +" varchar(20),"+ JIEKOUBEIZHU +" varchar(20),"
				+ JIEKOUZUOYEDANWEI+" varchar(20),"
				+ JIEKOUXINZNEGSHIJIAN +" varchar(20))";

		String myyinghuanxinxi = "create table " + MY_YINGHUAN
				+ "("+YINGHUANID  +" varchar(20) primary key ,"
				+ YINGHUANYINGHUANDENGJI+" varchar(100)," + YINGHUANLEIXING  +" varchar(100),"
				+ YINGHUANYINGHUANBEIZHU+" varchar(20)," +YINGHUANYINGHUANWEIZHI +" varchar(20),"
				+ YINGHUANJINGDU+" varchar(20)," +YINGHUANWEIDU +" varchar(20),"
				+ YINGHUANXINZENGSHIJIAN+" varchar(20),"
				+ YINGHUANZUOYEDANWEI +" varchar(20))";

		String gltdg = "create table " +  MY_DLTDG
				+ "("+DLTDGID+" varchar(20) primary key ,"
				+ DLTDNUMMBER+" varchar(20)," +SHIBEI_DLTDNUMMBER +" varchar(20),"
				+ DLTDGXUHAO+" varchar(20),"
				+ DLTDTYPE +" varchar(20))";

		String gldgl = "create table " +  MY_DLDGL
				+ "("+DLDGLID+" varchar(20) primary key ,"
				+ DLDGLNUMMBER+" varchar(20)," +SHIBEI_DLDGLNUMMBER +" varchar(20),"
				+ DLDGLGXUHAO+" varchar(20))";
		db.execSQL(auto);
		db.execSQL(myshebei);
		db.execSQL(mycablepit);
		db.execSQL(mydianlanduan);
		db.execSQL(myduanmian);
		db.execSQL(mydainlantongdao);
		db.execSQL(myKongWei);
		db.execSQL(mydianlanjietou);
		db.execSQL(myyinghuanxinxi);
		db.execSQL(gltdg);
		db.execSQL(gldgl);

	}

}