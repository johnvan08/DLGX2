package dlgx.gis.com.dlgx.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import dlgx.gis.com.dlgx.R;
import dlgx.gis.com.dlgx.com.yancy.imageselector.ImageLoader;

/**图片加载类
 * Created by admin on 2017/4/18.
 */
public class GlideLoader implements ImageLoader {

    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .placeholder(R.mipmap.imageselector_photo)
                .centerCrop()
                .into(imageView);
    }
}
