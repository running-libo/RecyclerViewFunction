package com.example.recyclerview.recyclerviewfunction;

import android.content.Context;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import java.util.List;

/**
 * Created by libo on 2017/11/25.
 */

public class MyAdapter extends MultiItemTypeAdapter<Bean>{

    public MyAdapter(Context context, List datas) {
        super(context, datas);

        addItemViewDelegate(new TypeOne());
        addItemViewDelegate(new TypeTwo());
    }

    public class TypeOne implements ItemViewDelegate<Bean> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_one;
        }

        @Override
        public boolean isForViewType(Bean item, int position) {
            if(position < 5){
                return true;
            }else{
                return false;
            }
        }

        @Override
        public void convert(ViewHolder holder, Bean bean, int position) {

        }
    }

    public class TypeTwo implements ItemViewDelegate<Bean>{

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_two;
        }

        @Override
        public boolean isForViewType(Bean item, int position) { //返回true表示使用该布局,如果有混合用，就会报No ItemViewDelegateManager added that matches position=1 in data source异常
            if(position >= 5){
                return true;
            }else{
                return false;
            }
        }

        @Override
        public void convert(ViewHolder holder, Bean bean, int position) {

        }

    }
}
