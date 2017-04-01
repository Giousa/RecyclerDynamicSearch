package com.zmm.recyclerdynamicsearch;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2017/4/1
 * Time:上午11:57
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {


    private List<StudentBean> mStudentBeanList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public SearchAdapter(List<StudentBean> studentBeanList) {
        mStudentBeanList = studentBeanList;
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  View.inflate(parent.getContext(), R.layout.item_search, null);
        return new SearchHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, final int position) {

        holder.setData(position);

        holder.item_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener != null){
                    mOnItemClickListener.OnItemClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mStudentBeanList.size();
    }

    class SearchHolder extends RecyclerView.ViewHolder{

        private TextView item_name,item_age;
        private LinearLayout item_ll;

        public SearchHolder(View itemView) {
            super(itemView);
            item_name = (TextView) itemView.findViewById(R.id.item_name);
            item_age = (TextView) itemView.findViewById(R.id.item_age);
            item_ll = (LinearLayout) itemView.findViewById(R.id.item_ll);
        }

        public void setData(int position) {
            item_name.setText(mStudentBeanList.get(position).getName());
            item_age.setText(mStudentBeanList.get(position).getAge()+"");
        }
    }
}
