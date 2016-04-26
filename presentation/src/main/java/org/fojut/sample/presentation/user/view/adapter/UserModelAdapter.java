package org.fojut.sample.presentation.user.view.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.fojut.sample.presentation.R;
import org.fojut.sample.presentation.base.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.user.model.UserEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

@PerActivity
public class UserModelAdapter extends BaseAdapter {
    private List<UserEntity> userEntityList;
    private final LayoutInflater layoutInflater;

    @Inject
    public UserModelAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.userEntityList = new ArrayList<>();
    }

    public void setUserEntityList(List<UserEntity> userEntityList) {
        this.userEntityList = userEntityList;
    }

    public void updateDataList(List<UserEntity> userEntityList){
        setUserEntityList(userEntityList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return (this.userEntityList != null) ? this.userEntityList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return userEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_user, null); // set view
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        setData(position, viewHolder);
        setListener(position, viewHolder);
        return convertView;
    }

    /**
     * Set data to view component
     */
    public void setData(int position, ViewHolder viewHolder){
        UserEntity userModel = userEntityList.get(position);
        viewHolder.textView.setText(userModel.toString());
    }

    /**
     * Set listener to view component
     */
    public void setListener(int position, ViewHolder viewHolder){
        //TODO set listener to view component if needed.
    }

    public class ViewHolder{
        @Bind(R.id.userText)
        TextView textView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
