package com.zmm.recyclerdynamicsearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.et_search)
    EditText mEtSearch;
    @InjectView(R.id.iv_delete)
    ImageView mIvDelete;
    @InjectView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private List<StudentBean> mStudentBeanList;
    private List<StudentBean> mStudentBeanList2;
    private SearchAdapter mSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {

        initData();
        editTextSearchListener();

    }

    private void initData() {

        if(mStudentBeanList != null && mStudentBeanList.size()>0){
            mStudentBeanList.clear();
            mStudentBeanList = null;
        }

        mStudentBeanList = new ArrayList<>();
        mStudentBeanList2 = new ArrayList<>();


        for (int i = 0; i < 20; i++) {
            StudentBean studentBean = new StudentBean("Giousa_" + i, i + 10);
            mStudentBeanList.add(studentBean);
            mStudentBeanList2.add(studentBean);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mSearchAdapter = new SearchAdapter(mStudentBeanList);
        mRecyclerView.setAdapter(mSearchAdapter);

        mSearchAdapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Toast.makeText(MainActivity.this,mStudentBeanList.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void editTextSearchListener() {
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() == 0){
                    mIvDelete.setVisibility(View.GONE);
                }else {
                    mIvDelete.setVisibility(View.VISIBLE);
                }

                search();
            }
        });
    }

    private void search() {
        String data = mEtSearch.getText().toString().trim();
        mStudentBeanList.clear();

        for (int i = 0; i < mStudentBeanList2.size(); i++) {
            StudentBean studentBean = mStudentBeanList2.get(i);

            String ageStr = studentBean.getAge()+"";
            if(studentBean.getName().contains(data) || ageStr.contains(data)){
                mStudentBeanList.add(studentBean);
            }
        }

        mSearchAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.iv_delete)
    public void onClick() {
        mEtSearch.setText("");
    }
}
