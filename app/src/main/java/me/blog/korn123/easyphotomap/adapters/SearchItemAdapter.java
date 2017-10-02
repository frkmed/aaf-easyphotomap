package me.blog.korn123.easyphotomap.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.io.FilenameUtils;

import java.util.ArrayList;

import me.blog.korn123.easyphotomap.R;
import me.blog.korn123.easyphotomap.constants.Constant;
import me.blog.korn123.easyphotomap.models.PhotoMapItem;
import me.blog.korn123.easyphotomap.utils.BitmapUtils;

/**
 * Created by CHO HANJOONG on 2016-07-20.
 */
public class SearchItemAdapter extends ArrayAdapter<PhotoMapItem> {

    private final Context mContext;
    private final ArrayList<PhotoMapItem> mEntities;
    private final int mLayoutResourceId;
    private Activity mActivity;

    public SearchItemAdapter(Context context, Activity activity, int layoutResourceId, ArrayList<PhotoMapItem> entities) {
        super(context, layoutResourceId, entities);
        this.mContext = context;
        this.mEntities = entities;
        this.mLayoutResourceId = layoutResourceId;
        this.mActivity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if(row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.textView1 = (TextView)row.findViewById(R.id.text1);
            holder.textView2 = (TextView)row.findViewById(R.id.text2);
            holder.textView3 = (TextView)row.findViewById(R.id.text3);
            holder.imageView1 = (ImageView)row.findViewById(R.id.image1);
            row.setTag(holder);
        } else {
            holder = (ViewHolder)row.getTag();
        }

        PhotoMapItem imageEntity = mEntities.get(position);
        Bitmap bitmap = null;
        String fileName = FilenameUtils.getName(imageEntity.imagePath);
        bitmap = BitmapUtils.decodeFile(mActivity, Constant.WORKING_DIRECTORY + fileName + ".thumb");
        holder.textView1.setText(imageEntity.info);
        holder.textView2.setText(imageEntity.date.toString());
        holder.textView3.setText(imageEntity.imagePath);
        holder.imageView1.setImageBitmap(bitmap);
        return row;
    }

    static class ViewHolder {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        ImageView imageView1;
    }
}
