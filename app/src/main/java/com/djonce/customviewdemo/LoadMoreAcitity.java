package com.djonce.customviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.djonce.customviewdemo.loadmore.LoadMoreContainer;
import com.djonce.customviewdemo.loadmore.LoadMoreHandler;
import com.djonce.customviewdemo.loadmore.LoadMoreListViewContainer;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.util.PtrCLog;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

/**
 *
 *
 * Created by djonce on 16/3/3.
 */
public class LoadMoreAcitity extends FragmentActivity {

    private static final String TAG = LoadMoreAcitity.class.getSimpleName();
    private PtrFrameLayout mPtrFrameLayout;
    private ListView listView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_load_more);

        initViews();
    }

    String[] array = { "aaaaaaaaaaa", "bbbbbbbbb", "ccccccccc", "ddddd",
            "eeeee", "fffffffff" , "ggggg", "jfdsaf", "qwwwwqw"

    , "werwerwr"
    , "fdjsf"
            ,"fjdsfjs"
            ,"rewrewr"
            ,"fkdsjarewr"
            ,"erwr"
            ,"werwrwr"
            ,"rewrofsfhsjkl"
            ,"rweruwru"
            ,"fdsfsf"
            , "rweewroi"
            , "fsdrower"
            , "fjdsorjjfdsjf"
            , "fdjsofjweor"

    };

    private void initViews() {
        mPtrFrameLayout = (PtrFrameLayout) findViewById(R.id.material_style_ptr_frame);
        listView = (ListView) findViewById(R.id.listView);

        // header
        final MaterialHeader header = new MaterialHeader(this);
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, PtrLocalDisplay.dp2px(10));
        header.setPtrFrameLayout(mPtrFrameLayout);

        mPtrFrameLayout.setLoadingMinTime(1000);
        mPtrFrameLayout.setDurationToCloseHeader(1500);
        mPtrFrameLayout.setHeaderView(header);
        mPtrFrameLayout.addPtrUIHandler(header);

        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, listView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });
        mPtrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                long delay = (long) (1000 + Math.random() * 2000);
                delay = Math.max(0, delay);

                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();
                    }
                }, delay);
            }
        });


        final LoadMoreListViewContainer listViewContainer = (LoadMoreListViewContainer) findViewById(R.id.load_more_list_view_container);
        listViewContainer.useDefaultFooter();

        listViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                Log.e(TAG, "--- load more-----");


            }
        });

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array));
    }


}
