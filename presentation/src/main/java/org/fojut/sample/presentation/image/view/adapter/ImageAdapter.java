package org.fojut.sample.presentation.image.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.fojut.sample.presentation.R;
import org.fojut.sample.presentation.base.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.image.model.ImageEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

@PerActivity
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private List<ImageEntity> imageEntityList;
    private final LayoutInflater layoutInflater;
    private final Context mContext;

    @Inject
    public ImageAdapter(Context context) {
        this.mContext = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageEntityList = new ArrayList<>();
    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(layoutInflater.inflate(R.layout.item_image, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ViewHolder holder, int position) {
        ImageEntity imageEntity = imageEntityList.get(position);
        if(imageEntity == null) {
            return;
        }
        holder.mTitle.setText(imageEntity.getTitle());
        Glide.with(mContext).load(imageEntity.getPicUrl()).asBitmap().animate(R.anim.image_load)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_loading).error(R.mipmap.ic_fail)
                .centerCrop()
                .into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        if(imageEntityList == null) {
            return 0;
        }
        return imageEntityList.size();
    }

    public ImageEntity getItem(int position) {
        return imageEntityList == null ? null : imageEntityList.get(position);
    }

    public void setImageEntityList(List<ImageEntity> imageEntityList) {
        this.imageEntityList = imageEntityList;
    }

    public void updateDataList(List<ImageEntity> newsEntityList){
        setImageEntityList(newsEntityList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_title)
        public TextView mTitle;
        @Bind(R.id.iv_image)
        public ImageView mImage;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
