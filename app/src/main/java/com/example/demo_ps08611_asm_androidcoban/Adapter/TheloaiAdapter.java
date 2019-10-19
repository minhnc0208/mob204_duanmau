package com.example.demo_ps08611_asm_androidcoban.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo_ps08611_asm_androidcoban.R;
import com.example.demo_ps08611_asm_androidcoban.SQL_ASM.CategoryDAO;
import com.example.demo_ps08611_asm_androidcoban.model.CategoryModel;

import java.util.List;

public class TheloaiAdapter extends BaseAdapter {
    public Activity context;
    public LayoutInflater inflater;
    List<CategoryModel> arrTheLoai;
    CategoryDAO theLoaiDAO;

    public TheloaiAdapter(Activity context, List<CategoryModel> arrayTheLoai) {
        super();
        this.context = context;
        this.arrTheLoai = arrayTheLoai;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        theLoaiDAO = new CategoryDAO(context);
    }

    @Override
    public int getCount() {
        return arrTheLoai.size();
    }

    @Override
    public Object getItem(int position) {
        return arrTheLoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.theloaiitem, null);
            holder.img = convertView.findViewById(R.id.ivIcon);
            holder.txtMaTheLoai = convertView.findViewById(R.id.tvMaTheLoai);
            holder.txtTenTheLoai = convertView.findViewById(R.id.tvTenTheLoai);
            holder.imgDelete = convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theLoaiDAO.deleteTheLoaiByID(arrTheLoai.get(position).getMaTheloai());
                    arrTheLoai.remove(position);
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        CategoryModel _entry = arrTheLoai.get(position);
        if (position % 3 == 0) {
            holder.img.setImageResource(R.drawable.ss);
        } else if (position % 3 == 1) {
            holder.img.setImageResource(R.drawable.kk);
        } else {
            holder.img.setImageResource(R.drawable.bookstack);
        }
        holder.txtMaTheLoai.setText(_entry.getMaTheloai());
        holder.txtTenTheLoai.setText(_entry.getTenTheloai());
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<CategoryModel> items) {
        this.arrTheLoai = items;
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtMaTheLoai;
        TextView txtTenTheLoai;
        ImageView imgDelete;
    }
}