package com.example.demo_ps08611_asm_androidcoban.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo_ps08611_asm_androidcoban.R;
import com.example.demo_ps08611_asm_androidcoban.SQL_ASM.BookDAO;
import com.example.demo_ps08611_asm_androidcoban.model.Bookmodel;

import java.util.ArrayList;
import java.util.List;

public class SachAdapter extends BaseAdapter implements Filterable {
    public Activity context;
    public LayoutInflater inflater;
    List<Bookmodel> arrSach;
    List<Bookmodel> arrSortSach;
    BookDAO sachDAO;
    private Filter sachFilter;

    public SachAdapter(Activity context, List<Bookmodel> arraySach) {
        super();
        this.context = context;
        this.arrSach = arraySach;
        this.arrSortSach = arraySach;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sachDAO = new BookDAO(context);
    }

    @Override
    public int getCount() {
        return arrSach.size();
    }

    @Override
    public Object getItem(int position) {
        return arrSach.get(position);
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
            convertView = inflater.inflate(R.layout.itemsach, null);
            holder.img = convertView.findViewById(R.id.ivIcon);
            holder.txtBookName = convertView.findViewById(R.id.tvBookName);
            holder.txtBookPrice = convertView.findViewById(R.id.tvBookPrice);
            holder.txtSoLuong = convertView.findViewById(R.id.tvSoLuong);
            holder.imgDelete = convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sachDAO.deleteSachByID(arrSach.get(position).getMaSach());
                    arrSach.remove(position);
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        Bookmodel _entry = arrSach.get(position);
        if (position % 5 == 0) {
            holder.img.setImageResource(R.drawable.ic7);
        } else if (position % 5 == 1) {
            holder.img.setImageResource(R.drawable.ic3);
        } else if (position % 5 == 2) {
            holder.img.setImageResource(R.drawable.ic4);
        } else if (position % 5 == 3) {
            holder.img.setImageResource(R.drawable.ic5);
        } else if (position % 5 == 4) {
            holder.img.setImageResource(R.drawable.ic6);
        }
        holder.txtBookName.setText("Mã sách: " + _entry.getMaSach());
        holder.txtSoLuong.setText("Tên Sách: " + _entry.getTenSach());
        holder.txtBookPrice.setText("Giá: " + _entry.getGiaBia() + "$");
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<Bookmodel> items) {
        this.arrSach = items;
        notifyDataSetChanged();
    }

    public void resetData() {
        arrSach = arrSortSach;
    }

    public Filter getFilter() {
        if (sachFilter == null)
            sachFilter = new CustomFilter();
        return sachFilter;
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtBookName;
        TextView txtBookPrice;
        TextView txtSoLuong;
        ImageView imgDelete;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortSach;
                results.count = arrSortSach.size();
            } else {
                List<Bookmodel> lsSach = new ArrayList<Bookmodel>();
                for (Bookmodel p : arrSach) {
                    if (p.getMaSach().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsSach.add(p);
                }
                results.values = lsSach;
                results.count = lsSach.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                arrSach = (List<Bookmodel>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
