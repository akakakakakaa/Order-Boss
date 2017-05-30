package b05studio.com.order_boss.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

import b05studio.com.order_boss.R;
import b05studio.com.order_boss.model.SearchInfo;

/**
 * Created by mansu on 2017-05-30.
 */

public class SearchActivity extends AppCompatActivity {
    private ArrayList<SearchInfo> searchInfos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        loadSearchInfo();
        initSearchButton();
        setSearchEditText();
        RecyclerView searchRecyclerView = (RecyclerView)findViewById(R.id.searchRecyclerView);
        searchRecyclerView.setHasFixedSize(true);
        SearchAdapter searchAdapter = new SearchAdapter(searchInfos, this, getLayoutInflater());
        searchRecyclerView.setAdapter(searchAdapter);
    }

    private void loadSearchInfo() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int size = sharedPreferences.getInt("keywordSize", 0);

        Gson gson = new Gson();
        for(int i=0;i<size;i++) {
            String json = sharedPreferences.getString("keyword" + i, "");
            SearchInfo searchInfo = gson.fromJson(json, SearchInfo.class);
            searchInfos.add(searchInfo);
        }
    }

    private void initSearchButton() {
        ImageButton searchBtn = (ImageButton)findViewById(R.id.searchIcon);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText)findViewById(R.id.searchEditText);
                searchInfos.add(new SearchInfo(Calendar.getInstance(), editText.getText().toString()));
                saveSearchInfo();

                Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                intent.putExtra("keyword", editText.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }

    private void setSearchEditText() {
        EditText editText = (EditText)findViewById(R.id.searchEditText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_NULL && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    searchInfos.add(new SearchInfo(Calendar.getInstance(), textView.getText().toString()));
                    saveSearchInfo();

                    Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                    intent.putExtra("keyword", textView.getText().toString());
                    startActivity(intent);
                    finish();
                }
                return true;
            }
        });
    }

    public void saveSearchInfo() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
    /* sKey is an array */
        editor.putInt("keywordSize", searchInfos.size());

        Gson gson = new Gson();
        for(int i=0;i<searchInfos.size();i++) {
            editor.remove("keyword" + i);
            String json = gson.toJson(searchInfos.get(i));
            editor.putString("keyword"+i, json);
        }
        editor.commit();
    }

    private class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
        private ArrayList<SearchInfo> searchInfos;
        private Context context;
        private LayoutInflater inflater;
        private int[] dateChooseMin = new int[3];
        private TextView[] dates = new TextView[3];

        public SearchAdapter(ArrayList<SearchInfo> searchInfos, Context context, LayoutInflater inflater) {
            this.searchInfos = searchInfos;
            this.context = context;
            this.inflater = inflater;

            for(int i=0; i<dateChooseMin.length; i++)
                dateChooseMin[i] = Integer.MAX_VALUE;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = inflater.from(parent.getContext()).inflate(R.layout.cardview_search, parent, false);
            ViewHolder holder = new ViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final SearchInfo searchInfo = searchInfos.get(position);

            Calendar calendar = searchInfo.getCalendar();
            long diff = System.currentTimeMillis() - calendar.getTimeInMillis();

            if(diff < 1000*60*10)
                if(position < dateChooseMin[0]) {
                    if(dates[0] != null)
                        dates[0].setText("");
                    holder.date.setText("방금");
                    dates[0] = holder.date;
                }
            else if(diff < 1000*60*60*24)
                if (position < dateChooseMin[1]) {
                    if (dates[1] != null)
                        dates[1].setText("");
                    holder.date.setText("오늘");
                    dates[1] = holder.date;
                }
            else if(diff >= 1000*60*60*24)
                    if (position < dateChooseMin[2]) {
                        if (dates[2] != null)
                            dates[2].setText("");
                        holder.date.setText("어제");
                        dates[2] = holder.date;
                    }

            holder.keyword.setText(searchInfo.getKeyword());
            holder.keyword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText editText = (EditText)findViewById(R.id.searchEditText);
                    editText.setText(searchInfo.getKeyword());
                }
            });
        }

        @Override
        public int getItemCount() {
            return searchInfos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView date;
            public TextView keyword;

            public ViewHolder(View view) {
                super(view);
                date = (TextView)view.findViewById(R.id.searchDate);
                keyword = (TextView)view.findViewById(R.id.searchKeyword);
            }
        }
    }
}
