package com.winkyqin.winrefreshlistview;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements RefreshListView.OnRefreshListener {

    protected static final String TAG = MainActivity.class.getSimpleName();

    private RefreshListView winListView;
    private List<String> 			 textList;
    private MyAdapter 				 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refresh_listview);

        winListView = (RefreshListView) findViewById(R.id.win_list_view);
        textList 		= new ArrayList<String>();

        for (int i = 0; i < 30; i++) {
            textList.add("测试"+i);
        }

        adapter = new MyAdapter();
        winListView.setAdapter(adapter);
        winListView.setOnRefreshListener(this);

    }
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return textList.size();
        }

        @Override
        public Object getItem(int arg0) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(MainActivity.this);
            tv.setText(textList.get(position));
            tv.setTextSize(18);
            tv.setTextColor(Color.BLACK);
            return tv;
        }

    }

    @Override
    public void onRefreshing() {
        new AsyncTask<String, Integer, Void>() {

            @Override
            protected Void doInBackground(String... params) {
                SystemClock.sleep(1000);
                textList.add("0000000");
                textList.add("0000000");
                textList.add("0000000");
                textList.add("0000000");
                return null;
            }
            @Override
            protected void onPostExecute(Void result) {
                adapter.notifyDataSetChanged();
                winListView.refreshFinish();
            }
        }.execute(new String[] {});
    }

    @Override
    public void onLoadingMore() {
        new AsyncTask<String, Integer, Void>() {

            @Override
            protected Void doInBackground(String... params) {
                SystemClock.sleep(1000);
                textList.add("9999");
                textList.add("9999");
                textList.add("9999");
                textList.add("9999");
                return null;
            }
            @Override
            protected void onPostExecute(Void result) {
                adapter.notifyDataSetChanged();
                winListView.refreshFinish();
            }
        }.execute(new String[] {});
    }

    @Override
    public void implOnItemClickListener(AdapterView<?> parent, View view, int position, long id) {

    }
}
