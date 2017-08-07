package dlgx.gis.com.dlgx.activitys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import dlgx.gis.com.dlgx.R;
import dlgx.gis.com.dlgx.adapter.ImageGridAdapter;
import dlgx.gis.com.dlgx.utils.AlbumHelper;
import dlgx.gis.com.dlgx.utils.Bimpone;
import dlgx.gis.com.dlgx.utils.ImageItem;

//连拍照片
public class ImageGridActivity extends Activity {

	public static final String EXTRA_IMAGE_LIST = "imagelist";

	// ArrayList<Entity> dataList;
	List<ImageItem> dataList;
	GridView gridView;
	
	ImageGridAdapter adapter;
	AlbumHelper helper;

	private Button mBtnComplete;

	@SuppressLint("HandlerLeak")
	Handler mHandler = new Handler() {
		@SuppressLint("ShowToast")
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(ImageGridActivity.this, "只能选择9张图片", Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
		}
	};

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_image_grid);

		helper = AlbumHelper.getHelper();
		helper.init(getApplicationContext());

		dataList = (List<ImageItem>) getIntent().getSerializableExtra(
				EXTRA_IMAGE_LIST);

		initView();

		mBtnComplete = (Button) findViewById(R.id.btnComplete);
		mBtnComplete.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ArrayList<String> list = new ArrayList<String>();
				Collection<String> c = adapter.map.values();
				Iterator<String> it = c.iterator();
				for (; it.hasNext();) {
					list.add(it.next());
				}

				if (Bimpone.act_bool) {
					// Intent intent = new Intent(ImageGridActivity.this,
					// PublishedActivity.class);
					// startActivity(intent);
					setResult(1);
					Bimpone.act_bool = false;
				}
				for (int i = 0; i < list.size(); i++) {
					if (Bimpone.drr.size() < 9) {
						Bimpone.drr.add(list.get(i));
					}
				}
				finish();
			}

		});
	}

	private void initView() {
		gridView = (GridView) findViewById(R.id.gridview);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		adapter = new ImageGridAdapter(ImageGridActivity.this, dataList,
				mHandler);
		gridView.setAdapter(adapter);
		
		adapter.setTextCallback(new ImageGridAdapter.TextCallback() {
			public void onListen(int count) {
				mBtnComplete.setText("完成" + "(" + count + ")");
			}
		});

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				adapter.notifyDataSetChanged();
			}

		});

	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
}
