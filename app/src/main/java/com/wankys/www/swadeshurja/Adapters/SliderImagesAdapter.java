package com.wankys.www.swadeshurja.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wankys.www.swadeshurja.Models.Image;
import com.wankys.www.swadeshurja.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Elakiya on 12/27/2017.
 */

    public class SliderImagesAdapter extends PagerAdapter {
        Context context;
        ArrayList<String> image_arraylist;

        public SliderImagesAdapter(Context context, ArrayList<String> image_arraylist) {
            this.context = context;
            this.image_arraylist = image_arraylist;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.viewpager_images, container, false);
            ImageView im_slider = view.findViewById(R.id.imageview_id);String imagePath = null;
            String url = "http://Swadeshurja.com//swadeshseller/";
            imagePath = url + image_arraylist.get(position);
            System.out.println("imagePath : " + imagePath);
            System.out.println(image_arraylist.size());
            Glide.with(context).load(imagePath).placeholder(R.drawable.loading).into(im_slider);
            container.addView(view);
            return view;
        }
        @Override
        public int getCount() {
            return image_arraylist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

