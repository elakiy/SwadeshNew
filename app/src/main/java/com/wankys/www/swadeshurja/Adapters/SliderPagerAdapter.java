package com.wankys.www.swadeshurja.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wankys.www.swadeshurja.Models.Sliders;
import com.wankys.www.swadeshurja.R;


import java.util.List;


/**
 * Created by Elakiya on 12/27/2017.
 */

    public class SliderPagerAdapter extends PagerAdapter {
    Context context;
        int[] image_arraylist;

        public SliderPagerAdapter(Context context, int[] image_arraylist) {
            this.context = context;
            this.image_arraylist = image_arraylist;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.adapter_viewpager, container, false);

            ImageView im_slider = (ImageView) view.findViewById(R.id.imageview_id);

            TextView name_tv=view.findViewById(R.id.name_id);

            im_slider.setImageResource(image_arraylist[position]);
//            String url = "http://wankys.com/project/caprolabs/ecommerce/";
//            String imagePath = url + image_arraylist.get(position).getBanner_imgsrc();
//            Glide.with(context).load(imagePath).into(im_slider);
           // name_tv.setText(image_arraylist.get(position).getName());
           // desc_tv.setText(image_arraylist.get(position).getBanner_desc());
//            name_tv.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade));
//            desc_tv.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade));

            container.addView(view);
            return view;
        }
        @Override
        public int getCount() {
            return image_arraylist.length;
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

