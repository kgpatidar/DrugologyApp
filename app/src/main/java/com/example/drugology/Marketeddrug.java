package com.example.drugology;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;

public class Marketeddrug extends AppCompatActivity {

    PDFView pdfView;
    Button antibioticBtn, antipyreticBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketeddrug);

        antibioticBtn = findViewById(R.id.antibiticbuttonmd);
        antipyreticBtn = findViewById(R.id.antipyreticbuttonmd);

        antipyreticBtn.setEnabled(true);
        antibioticBtn.setEnabled(false);
        antipyreticBtn.setBackground(getDrawable(R.drawable.anitibiotic_btninactive));
        antibioticBtn.setBackground(getDrawable(R.drawable.analgesic_btn));

        pdfView = findViewById(R.id.pdf_viewer);

        ShowData("antibioticpdf.pdf");

        antibioticBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                antipyreticBtn.setEnabled(true);
                antibioticBtn.setEnabled(false);
                antipyreticBtn.setBackground(getDrawable(R.drawable.anitibiotic_btninactive));
                antibioticBtn.setBackground(getDrawable(R.drawable.analgesic_btn));
                ShowData("antibioticpdf.pdf");
            }
        });
        antipyreticBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                antipyreticBtn.setEnabled(false);
                antibioticBtn.setEnabled(true);
                antibioticBtn.setBackground(getDrawable(R.drawable.analgesic_btninactive));
                antipyreticBtn.setBackground(getDrawable(R.drawable.anitibiotic_btn));
                ShowData("antipyreticpdf.pdf");
            }
        });









    }

    public void ShowData(String url ) {
        pdfView.fromAsset(url)
                .password(null)
                .defaultPage(0)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .onPageError(new OnPageErrorListener() {
                    @Override
                    public void onPageError(int page, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error in Loading.\nTry to Restart", Toast.LENGTH_SHORT).show();
                    }
                })
                .onRender(new OnRenderListener() {
                    @Override
                    public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
                        pdfView.fitToWidth();
                    }
                })
                .enableAnnotationRendering(true)
                .invalidPageColor(Color.WHITE)
                .load();
    }
}
