package dlgx.gis.com.dlgx.activitys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import dlgx.gis.com.dlgx.R;
import dlgx.gis.com.dlgx.utils.SupportedSizesReflect;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("SimpleDateFormat")
@SuppressWarnings("deprecation")
//联拍照片
public class CameraActivity extends Activity {
	
	private FrameLayout mFlContent;
	private SurfaceView mSvTakePic;
	private ScrollView mSvPicShow;
	private LinearLayout mLlContentPic;
	
	private LinearLayout mLlTransLay;
	
	private ImageView mIvClose;
	private ImageView mIvTakePic;
	private ImageView mIvAddPic;
	private ImageView mIvComplete;
	
	private Camera mCamera;
	
	private Display mDisplay;
	
	private int mHeight;
	private int mWidth;
	
	private File mFile = null;
	
	private String mFilePath =  Environment.getExternalStorageDirectory() + "/电缆井/";
	
	private boolean isFirst = true;
	
	private List<Bitmap> mBitmap = null;
	
	private int picCount = 1;
	
	private String type = "takePic";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN); 
		setContentView(R.layout.activity_camera);
		// 得到屏幕分辨率
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mDisplay = wm.getDefaultDisplay();
		
		initView();
		
		initData();
		
		initEvent();
	}
	
	private void initView() {
		mFlContent = (FrameLayout) findViewById(R.id.flContent_activity_camera);
		mSvTakePic = (SurfaceView) findViewById(R.id.svTakePic_activity_camera);
		mSvPicShow = (ScrollView) findViewById(R.id.svPicShow_activity_camera);
		mLlContentPic = (LinearLayout) findViewById(R.id.llContent_activity_camera);
		mIvTakePic = (ImageView) findViewById(R.id.ivTakePic_activity_camera);
		mIvClose = (ImageView) findViewById(R.id.ivBack_activity_camera);
		mIvComplete = (ImageView) findViewById(R.id.ivComplete_activity_camera);
		mIvAddPic = (ImageView) findViewById(R.id.ivAddPic_activity_camera);
		mIvClose.setVisibility(View.GONE);
	}
	
	private void initData() {
        
		mFile = new File(mFilePath);
		
		if (!mFile.exists()) {
			mFile.mkdirs();
		}
		
		mIvAddPic.setVisibility(View.GONE);
		mIvTakePic.setVisibility(View.VISIBLE);
		
		mBitmap = new ArrayList<Bitmap>();
		
		mHeight = mDisplay.getHeight() - 300;
		mWidth = mDisplay.getWidth() - 200;
		
		RelativeLayout.LayoutParams mFlContentParams = (RelativeLayout.LayoutParams) mFlContent.getLayoutParams();
		mFlContentParams.width = mWidth;
		mFlContentParams.height = mHeight;
		mFlContent.setLayoutParams(mFlContentParams);
		
		// SurfaceView不维护自己的缓冲区，等待屏幕渲染引擎将内容推送到用户面前
		mSvTakePic.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);  // 指定Push Buffer
		mSvTakePic.getHolder().setFixedSize(mDisplay.getWidth(), mDisplay.getHeight()); // 设置Surface分辨率  
		mSvTakePic.getHolder().setKeepScreenOn(true); // 屏幕常亮
		mSvTakePic.getHolder().addCallback(new SurfaceCallback());// 为SurfaceView的句柄添加一个回调函数
	}
	
	private void initEvent() {
		mIvTakePic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 拍照    
                if (mCamera != null) {  
                	mIvTakePic.setEnabled(false);
                	mCamera.takePicture(null, null, new PictureCallBack());
                } 
			}
		});
		
//		mIvClose.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				if (mBitmap.size() == 0) {
//					mBitmap.clear();
//					mLlContentPic.removeAllViews();
//					deleteDir();
//					finish();
//				} else {
//					mLlContentPic.removeViewAt(mLlContentPic.getChildCount() - 1);
//					if (type.equals("takePic")) {
//						mIvAddPic.setVisibility(View.VISIBLE);
//						mIvTakePic.setVisibility(View.GONE);
//
//						mIvTakePic.setEnabled(false);
//						mIvAddPic.setEnabled(true);
//
//						type = "addMore";
//						picCount--;
//					} else {
//						mIvTakePic.setEnabled(false);
//						mIvAddPic.setEnabled(true);
//						mBitmap.remove(mBitmap.size() - 1);
//						deleteBitmap();
//					}
//					if (mBitmap.size() == 0) {
//
//						type = "takePic";
//
//						mLlContentPic.removeAllViews();
//						mCamera.startPreview();
//
//						mIvAddPic.setVisibility(View.GONE);
//						mIvTakePic.setVisibility(View.VISIBLE);
//
//						mIvTakePic.setEnabled(true);
//						mIvAddPic.setEnabled(false);
//						isFirst = true;
//
//						FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mSvTakePic.getLayoutParams();
//						lp.height = mHeight;
//						lp.width = mWidth;
//						mSvTakePic.setLayoutParams(lp);
//					}
//				}
//			}
//		});
		
		mIvComplete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		mIvAddPic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mIvTakePic.setVisibility(View.VISIBLE);
				mIvAddPic.setVisibility(View.GONE);
				
				type = "takePic";
				
				picCount++;
				
				isFirst = false;
				
				mLlTransLay = (LinearLayout) View.inflate(CameraActivity.this, R.layout.trans_layout, null);
				
				LayoutParams params = new LayoutParams(mWidth, mHeight - 200);
				mLlTransLay.setLayoutParams(params);
				
				mLlContentPic.addView(mLlTransLay);
				
				mSvPicShow.post(new Runnable() { // ScrollView 滚动到最后
					@Override
					public void run() {
						mSvPicShow.fullScroll(ScrollView.FOCUS_DOWN);
					}
				});
				
				if (!isFirst) {
					FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mSvTakePic.getLayoutParams();
					lp.height = mHeight - 200;
					lp.width = mWidth;
					lp.gravity = Gravity.BOTTOM;
					mSvTakePic.setLayoutParams(lp);
				}
				
				mCamera.startPreview(); // 重新开始预览
			}
		});
	}
	
	class SurfaceCallback implements Callback {

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			try {  
				mCamera = Camera.open();   // 打开摄像头  
				mCamera.setDisplayOrientation(getPreviewDegree(CameraActivity.this));  
				setCameraPre(holder);
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			setCameraPre(holder);
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;  
			holder = null;
			mSvTakePic = null;
		}

	}
	
	private void setCameraPre(SurfaceHolder holder) {
		
		Camera.Parameters params = mCamera.getParameters();
		
		params.setPictureFormat(PixelFormat.JPEG); // 设置图片格式
		params.setJpegQuality(100);  // 设置照片质量  
	     
    	// 得到相机所支持的像素（图片大小，预览大小）
    	List<Size> supportedPictureSizes = SupportedSizesReflect.getSupportedPictureSizes(params);
    	List<Size> supportedPreviewSizes = SupportedSizesReflect.getSupportedPreviewSizes(params);
     
    	if ( supportedPictureSizes != null && supportedPreviewSizes != null &&
    			supportedPictureSizes.size() > 0 && supportedPreviewSizes.size() > 0) {
    		
    		Size pictureSize = supportedPictureSizes.get(0);  //  2.x
     
    		int maxSize =Math.max(mDisplay.getWidth(), mDisplay.getHeight());
    		
			for(Size size : supportedPictureSizes){    			
				if(Math.min(size.width, size.height) >= maxSize){
					pictureSize = size;
					break;
				}						
			}
     
    		WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);		
    		Display display = windowManager.getDefaultDisplay();		
    		DisplayMetrics displayMetrics = new DisplayMetrics();
    		display.getMetrics(displayMetrics);
     
    		Size previewSize = getOptimalPreviewSize(supportedPreviewSizes, 
    				display.getWidth(), display.getHeight()); 
     
    		params.setPictureSize(pictureSize.width, pictureSize.height);		
    		params.setPreviewSize(previewSize.width, previewSize.height);								
     
    	}
    	
    	mCamera.setParameters(params);
    	
    	try {
    		mCamera.setPreviewDisplay(holder);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	mCamera.startPreview();
    	
    	mCamera.autoFocus(new AutoFocusCallback() {  // 对焦（自动）
			@Override
			public void onAutoFocus(boolean success, Camera camera) {
				if (success) {  
					mCamera.startPreview();
		        }
			}
		});
    	
	}
	
	
	/** 
     *  重构照相类
     */  
	class PictureCallBack implements PictureCallback {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			
			Bitmap bitmap = comAndRotateBitmap(data);
			showBitmap(bitmap);
			saveToSDCard(bitmap, "mrPro" + picCount + ".jpg");
			mBitmap.add(bitmap);
			
			mIvAddPic.setEnabled(true);
			
			mIvAddPic.setVisibility(View.VISIBLE);
			mIvTakePic.setVisibility(View.GONE);
			
			type = "addMore";
			
			mIvAddPic.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					mIvTakePic.setVisibility(View.VISIBLE);
					mIvAddPic.setVisibility(View.GONE);
					
					mIvTakePic.setEnabled(true);
					mIvAddPic.setEnabled(false);
					
					type = "takePic";
					
					picCount++;
					
					isFirst = false;
					
					mLlTransLay = (LinearLayout) View.inflate(CameraActivity.this, R.layout.trans_layout, null);
					
					LayoutParams params = new LayoutParams(mWidth, mHeight - 200);
					mLlTransLay.setLayoutParams(params);
					
					mLlContentPic.addView(mLlTransLay);
					
					mSvPicShow.post(new Runnable() {
						@Override
						public void run() {
							mSvPicShow.fullScroll(ScrollView.FOCUS_DOWN);
						}
					});
					
					if (!isFirst) {
						FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mSvTakePic.getLayoutParams();
						lp.height = mHeight - 200;
						lp.width = mWidth;
						lp.gravity = Gravity.BOTTOM;
						mSvTakePic.setLayoutParams(lp);
					}
					
					mCamera.startPreview();
				}
			});
		}
	}
	
	private Bitmap comAndRotateBitmap(byte[] data) {
        InputStream is = new ByteArrayInputStream(data);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 4; //这个的值压缩的倍数，数值越小，压缩率越小，图片越清晰
        Bitmap bitmap = BitmapFactory.decodeStream(is, null, opts);  // 把ByteArrayInputStream数据生成图片  
        Matrix matrix = new Matrix();  
        matrix.setRotate(getPreviewDegree(CameraActivity.this));  // 转90度
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),bitmap.getHeight(), matrix, true);
        try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}
	
	private void showBitmap(Bitmap bitmap) {
		if (!isFirst) {
			mLlContentPic.removeView(mLlTransLay);
			LinearLayout ll_view = (LinearLayout) View.inflate(CameraActivity.this, R.layout.image_items, null);
			ImageView imageView = (ImageView) ll_view.findViewById(R.id.ivShowPic_image_items);
			
			LayoutParams ivParams = (LayoutParams) imageView.getLayoutParams();
			ivParams.height = mHeight - 200;
			ivParams.width = mWidth;
			imageView.setImageBitmap(bitmap);
			mLlContentPic.addView(ll_view);
		} else {
			LinearLayout ll_view = (LinearLayout) View.inflate(CameraActivity.this, R.layout.image_items, null);
			ImageView imageView = (ImageView) ll_view.findViewById(R.id.ivShowPic_image_items);
			
			LayoutParams ivParams = (LayoutParams) imageView.getLayoutParams();
			ivParams.height = mHeight;
			ivParams.width = mWidth;
			imageView.setImageBitmap(bitmap);
			mLlContentPic.addView(ll_view);
		}
	}
	
	private void saveToSDCard(Bitmap bitmap , String picName) {
		
		File picFile = new File(mFile,picName);
		
		try {
			FileOutputStream outputStream = new FileOutputStream(picFile);// 文件流
	        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private void deleteBitmap() {
		
		File file = new File(mFilePath + "/mrPro" + picCount +".jpg");
		if (file.isFile()) {
			file.delete();
			picCount--;
		}
		file.exists();
	}
	
	private void deleteDir() {
		
		File dir = new File(mFilePath);
		
		if (dir == null || !dir.exists() ||    !dir.isDirectory()) {
			return;
		}
		
		for (File file : dir.listFiles()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()){
				deleteDir();
			}
		}
		dir.delete();
	}
	
	// 提供一个静态方法，用于根据手机方向获得相机预览画面旋转的角度    
    public static int getPreviewDegree(Activity activity) {    
    	// 获得手机的方向   
        int rotation = activity.getWindowManager().getDefaultDisplay()    
                .getRotation();    
        int degree = 0;    
        // 根据手机的方向计算相机预览画面应该选择的角度
        switch (rotation) {    
        case Surface.ROTATION_0:
            degree = 90;    
            break;    
        case Surface.ROTATION_90:
            degree = 0;    
            break;    
        case Surface.ROTATION_180:
            degree = 270;    
            break;    
        case Surface.ROTATION_270:
            degree = 180;    
            break;    
        }    
        return degree;    
    }    
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	if (mCamera != null) {
    		mCamera.stopPreview();
    		// 释放相机资源
    		mCamera.release();
    		mCamera = null;  
			mSvTakePic = null;
		}
    }

    private Size getOptimalPreviewSize(List<Size> sizes, int w, int h) {
            final double ASPECT_TOLERANCE = 0.1;
            double targetRatio = (double) w / h;
            if (sizes == null) return null;

            Size optimalSize = null;
            double minDiff = Double.MAX_VALUE;

            int targetHeight = h;

            // Try to find an size match aspect ratio and size
            for (Size size : sizes) {
            	
                double ratio = (double) size.width / size.height;
                
                if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
                	continue;
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }

            // Cannot find the one match the aspect ratio, ignore the requirement
            if (optimalSize == null) {
            	
                minDiff = Double.MAX_VALUE;
                
                for (Size size : sizes) {
                    if (Math.abs(size.height - targetHeight) < minDiff) {
                        optimalSize = size;
                        minDiff = Math.abs(size.height - targetHeight);
                    }
                }
            }
            return optimalSize;
        }

    
    // 当没有SD卡时，图片存储（保留）
//	private void saveToRoot(byte[] data) {
//	
//	String picName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".jpg";
//	String filePath = Environment.getRootDirectory() + "/MrPro";
//	File mFile = new File(filePath);
//	if (mFile.exists()) {
//		
//	} else {
//		mFile.mkdir();
//	}
//	
//	File picFile = new File(mFile,picName);
//    try {
//		FileOutputStream outputStream = new FileOutputStream(picFile); 
//		
//		try {
//			outputStream.write(data);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//	        try {
//				outputStream.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} 
//		}
//	} catch (FileNotFoundException e) {
//		e.printStackTrace();
//	}
//}
	 
}
