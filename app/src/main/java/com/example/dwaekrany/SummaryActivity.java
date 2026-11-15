package com.example.dwaekrany;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "com.example.dwaekrany.NAME";
    public static final String EXTRA_EMAIL = "com.example.dwaekrany.EMAIL";
    public static final String EXTRA_COURSE = "com.example.dwaekrany.COURSE";
    public static final String EXTRA_LEVEL = "com.example.dwaekrany.LEVEL";
    public static final String EXTRA_NEWSLETTER = "com.example.dwaekrany.NEWSLETTER";
    public static final String EXTRA_NUMBER = "com.example.dwaekrany.NUMBER";

    private TextView tvSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        tvSummary = findViewById(R.id.tvSummary);

        Intent intent = getIntent();
        String name = intent.getStringExtra(EXTRA_NAME);
        String email = intent.getStringExtra(EXTRA_EMAIL);
        String course = intent.getStringExtra(EXTRA_COURSE);
        int level = intent.getIntExtra(EXTRA_LEVEL, 0);
        boolean newsletter = intent.getBooleanExtra(EXTRA_NEWSLETTER, false);

        String news = newsletter ? "tak" : "nie";
        tvSummary.setText("Imię: " + name + "\nE-mail: " + email + "\nKurs: " + course + "\nPoziom: " + level + "/10\nNewsletter: " + news);

        findViewById(R.id.btnEdit).setOnClickListener(v -> finish());
        findViewById(R.id.btnConfirm).setOnClickListener(v -> {
            String number = "ZG/" + (1000 + new java.util.Random().nextInt(9000));
            Intent result = new Intent();
            result.putExtra(EXTRA_NUMBER, number);
            setResult(RESULT_OK, result);
            finish();
        });
        findViewById(R.id.btnShare).setOnClickListener(v -> {
            Intent intent1 = new Intent(Intent.ACTION_SEND);
            intent1.setType("text/plain");
            intent1.putExtra(Intent.EXTRA_TEXT, tvSummary.getText().toString());
            try {
                startActivity(Intent.createChooser(intent, "Udostępnij przez"));
            } catch (android.content.ActivityNotFoundException e) {
                android.widget.Toast.makeText(this, "Brak aplikacji", android.widget.Toast.LENGTH_SHORT).show();
            }
        });
    }
}