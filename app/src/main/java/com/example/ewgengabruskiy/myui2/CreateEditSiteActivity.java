package com.example.ewgengabruskiy.myui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ewgengabruskiy.myui2.data.datas.Site;

public class CreateEditSiteActivity extends AppCompatActivity implements View.OnClickListener {

    private  Site site;
    private Button btn;
    private  EditText name;
    private  EditText url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_site);

        site = getIntent().getParcelableExtra("test site");

        name = (EditText) findViewById(R.id.name_site);
        if (site.getName()!=null) name.setText(site.getName());
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                site.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        url = (EditText) findViewById(R.id.url);
        if (site.getUrl()!=null) url.setText(site.getUrl());
        url.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                site.setUrl(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        btn = (Button) findViewById(R.id.add_button_site);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view == btn) {

            if (TextUtils.isEmpty(name.getText().toString())){
                name.setError(getString(R.string.error_field_required));
                return;
            }

            if (TextUtils.isEmpty(url.getText().toString())){
                url.setError(getString(R.string.error_field_required));
                return;
            }

            Intent intent = new Intent();
            intent.putExtra("Back Site",site);
            setResult(RESULT_OK, intent);
            finish();
            return;

        }


    }


    @Override
    public void onBackPressed() {
        finish();
    }


}
