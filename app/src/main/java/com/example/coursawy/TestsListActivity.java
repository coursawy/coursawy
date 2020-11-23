package com.example.coursawy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestsListActivity extends AppCompatActivity {

    ArrayList<TestInfoItem> testInfoItemList;
    DatabaseReference myRef = null;
    FirebaseDatabase database;
    @BindView(R.id.progress)
    ProgressBar progress;
    String type = "";
    @BindView(R.id.test_alert_tv)
    TextView testAlertTv;
    @BindView(R.id.tests_list_view)
    ListView testsListView;
    TestListAdapter listAdapter;
    String stuCode = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests_list);
        ButterKnife.bind(this);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("ExamTitles");
        testInfoItemList = new ArrayList<>();
        type = Objects.requireNonNull(getIntent().getExtras()).getString("type");
        assert type != null;
        if (type.equals("offline")) {
            progress.setVisibility(View.GONE);
            testsListView.setVisibility(View.VISIBLE);

            testInfoItemList.clear();
            for (int i = 1; i < 13; i++) {
                testInfoItemList.add(new TestInfoItem(i + "", "General", "Test " + i, ""));
            }

            listAdapter = new TestListAdapter(TestsListActivity.this, testInfoItemList);
            testsListView.setAdapter(listAdapter);

            testsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TestInfoItem item = testInfoItemList.get(position);
                    Intent intent = new Intent(TestsListActivity.this, OfflineTests.class);
                    intent.putExtra("id", item.getId());
                    intent.putExtra("type", type);
                    intent.putExtra("unitName", item.getTitle());
                    startActivity(intent);
                }
            });
        }

    }
}