package dlgx.gis.com.dlgx.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;



import java.io.Serializable;
import java.util.List;

import dlgx.gis.com.dlgx.R;
import dlgx.gis.com.dlgx.adapter.ImageBucketAdapter;
import dlgx.gis.com.dlgx.utils.AlbumHelper;
import dlgx.gis.com.dlgx.utils.ImageBucket;

//联拍照片
public class ImageListRootActivity extends Activity {

	// ArrayList<Entity> dataList;//用来装载数据源的列表
	List<ImageBucket> dataList;
	GridView gridView;

	ImageBucketAdapter adapter;// 自定义的适配器
	AlbumHelper helper;

	public static final String EXTRA_IMAGE_LIST = "imagelist";
	public static Bitmap bimap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_bucket);

		helper = AlbumHelper.getHelper();
		helper.init(getApplicationContext());

		initData();
		initView();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		// /**
		// * 这里，我们假设已经从网络或者本地解析好了数据，所以直接在这里模拟了10个实体类，直接装进列表中
		// */
		// dataList = new ArrayList<Entity>();
		// for(int i=-0;i<10;i++){
		// Entity entity = new Entity(R.drawable.picture, false);
		// dataList.add(entity);
		// }
		dataList = helper.getImagesBucketList(false);
		bimap = BitmapFactory
				.decodeResource(getResources(), R.drawable.img_add);
	}

	/**
	 * 初始化view视图
	 */
	private void initView() {

		gridView = (GridView) findViewById(R.id.gridview);
		adapter = new ImageBucketAdapter(ImageListRootActivity.this, dataList);
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				/**
				 * 根据position参数，可以获得跟GridView的子View相绑定的实体类，然后根据它的isSelected状态，
				 * 来判断是否显示选中效果。 至于选中效果的规则，下面适配器的代码中会有说明
				 */
				// if(dataList.get(position).isSelected()){
				// dataList.get(position).setSelected(false);
				// }else{
				// dataList.get(position).setSelected(true);
				// }
				/**
				 * 通知适配器，绑定的数据发生了改变，应当刷新视图
				 */
				// adapter.notifyDataSetChanged();
				Intent intent = new Intent(ImageListRootActivity.this,
						ImageGridActivity.class);
				intent.putExtra(ImageListRootActivity.EXTRA_IMAGE_LIST,
						(Serializable) dataList.get(position).imageList);
				// startActivityForResult(intent, 1);
				startActivity(intent);
				finish();
			}

		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}
}
