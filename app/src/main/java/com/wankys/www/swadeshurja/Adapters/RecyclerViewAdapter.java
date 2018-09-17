package com.wankys.www.swadeshurja.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wankys.www.swadeshurja.R;

/**
 * Created by Elakiya on 5/4/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    Context context;
    int[] images;
    String[] names;

  public RecyclerViewAdapter(Context context,int[] images, String[] names)
  {
      this.context=context;
      this.images=images;
      this.names=names;
  }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(context).inflate(R.layout.adapter_recyclerview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
      holder.name.setText(names[position]);
      holder.image.setImageResource(images[position]);
    }
    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      public TextView name;
      public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView_id);
            image =itemView.findViewById(R.id.imageview_id);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent(context, ProductDetail.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
                }
            });
        }
    }
}
