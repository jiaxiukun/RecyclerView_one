package baway.com.recyclerview_one;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private static List<String> mDatas;
    private HomeAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter = new HomeAdapter());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter.setmOnItemClickLitener(new HomeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,mDatas.get(position),Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            mDatas.add("闭心自慎终不失过兮" + i);
        }
    }


    static class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false));
            return holder;
        }

        public interface OnItemClickLitener{
            void onItemClick(View  view,int position);
        }
        private OnItemClickLitener mOnItemClickLitener;
        public void setmOnItemClickLitener(OnItemClickLitener mOnItemClickLitener){
            this.mOnItemClickLitener=mOnItemClickLitener;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            holder.tv.setText(mDatas.get(position));
            if (mOnItemClickLitener!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onItemClick(holder.itemView,pos);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }



        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.textview);
            }
        }
    }
}
