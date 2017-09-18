package com.example.administrator.myapp;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * Created by Administrator on 2017/5/2 0002.
 */

public class BottomViewItem {

    public static BottomViewItem instance;
    public static BottomViewItem getInstance() {
        if (instance == null) {
            instance = new BottomViewItem();
        }
        return instance;
    }
    public int viewNum = 3;
    public ImageView[] images = new ImageView[viewNum];
    public TextView[] texts = new TextView[viewNum];
    public LinearLayout[] linears = new LinearLayout[viewNum];
    public int[] images_id = new int[] { R.id.one_image, R.id.two_image, R.id.three_image};
    public int[] texts_id = new int[] { R.id.one_text, R.id.two_text, R.id.three_text};
    public int[] linears_id = new int[] { R.id.one_layout, R.id.two_layout, R.id.three_layout};
    public int[] images_selected = new int[] { R.drawable.one_selected, R.drawable.two_selected, R.drawable.three_selected};
    public int[] images_unselected = new int[] { R.drawable.one_unselected, R.drawable.two_unselected, R.drawable.three_unselected};
}
