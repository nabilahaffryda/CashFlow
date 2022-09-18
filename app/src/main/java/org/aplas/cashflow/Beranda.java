package org.aplas.cashflow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.aplas.cashflow.db.DBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Beranda extends AppCompatActivity {
    //initial class Dbhelper
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        init();
    }

    public void init() {
        //initial komponen
        dbHelper = new DBHelper(getApplicationContext());
        TextView txtPemasukan = findViewById(R.id.txtPemasukan);
        TextView txtPengeluaran = findViewById(R.id.txtPengeluaran);
        LinearLayout tambahPemasukan = findViewById(R.id.btnPemasukanplus);
        LinearLayout tambahPengeluaran = findViewById(R.id.btnPengeluaranplus);
        LinearLayout pengaturan = findViewById(R.id.btnSetting);
        LinearLayout detailCash = findViewById(R.id.btnDetail);

        //set value pengeluaran dan pemasukan
        txtPengeluaran.setText(getResources().getString(R.string.pengeluaran) + ": " + dbHelper.getcashthismonth("O", new SimpleDateFormat("MM").format(new Date())));
        txtPemasukan.setText(getResources().getString(R.string.pemasukan) + ": " + dbHelper.getcashthismonth("I", new SimpleDateFormat("MM").format(new Date())));

        //fungsi untuk pindah ke halaman tambah pemasukan
        tambahPemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent(TambahPemasukanActivity.class);
            }
        });

        //fungsi untuk pindah ke halaman tambah pengeluaran
        tambahPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent(TambahPengeluaranActivity.class);
            }
        });

        //fungsi untuk pindah ke halaman pengaturan
        pengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent(PengaturanActivity.class);
            }
        });

        //fungsi untuk pindah ke halaman detail cash flow
        detailCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent(org.aplas.cashflow.DetailCashFlowActivity.class);
            }
        });

    }

    //method untuk menambahkan bulan
    public ArrayList<String> getMonth() {
        ArrayList<String> label = new ArrayList<>();
        label.add("Jan");
        label.add("Feb");
        label.add("Mar");
        label.add("Apr");
        label.add("Mei");
        label.add("Jun");
        label.add("Jul");
        label.add("Aug");
        label.add("Sep");
        label.add("Oct");
        label.add("Nov");
        label.add("Des");
        label.add("");
        return label;
    }

    //centralisasi method intent
    public void intent(Class classes) {
        Intent intent = new Intent(Beranda.this, classes);
        intent.putExtra("username", getIntent().getStringExtra("username"));
        startActivity(intent);
        finish();
    }
}