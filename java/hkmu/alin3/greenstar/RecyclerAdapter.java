package hkmu.alin3.greenstar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private Context mContext;
    private  List<String> mlist=new ArrayList<String>();
    public RecyclerAdapter(Context context){
        mContext=context;
    }
    public void setData(List<String> list){
        mlist=list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_gongdanlistview,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        //asdf
        return viewHolder;
    }


    /*
    * 綁定数据显示
    * */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
           String str=mlist.get(position);
           holder.text.setText(str);

           holder.itemview.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   mlist.remove(position);
                   notifyDataSetChanged();

               }
           });
    }

    @Override
    public int getItemCount() {
        return mlist==null?0:mlist.size();
    }


    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public TextView text_fjh;
        public TextView text_name;
        public TextView text_tel;
        private LinearLayout itemview;
        public ViewHolder(View view){
            super(view);
            text = (TextView)view.findViewById(R.id.text);

            itemview=view.findViewById(R.id.itemview);

        }
    }
}
