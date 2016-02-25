package org.fojut.sample.presentation.view.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.fojut.sample.presentation.R;
import org.fojut.sample.presentation.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

@PerActivity
public class UserModelAdapter extends BaseAdapter {
    private List<UserModel> userModelList;
    private final LayoutInflater layoutInflater;

    @Inject
    public UserModelAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.userModelList = new ArrayList<>();
    }

    public void setUserModelList(List<UserModel> userModelList) {
        this.userModelList = userModelList;
    }

    public void updateDataList(List<UserModel> userModelList){
        setUserModelList(userModelList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return (this.userModelList != null) ? this.userModelList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return userModelList.get(position);
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
        UserModel userModel = userModelList.get(position);
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
