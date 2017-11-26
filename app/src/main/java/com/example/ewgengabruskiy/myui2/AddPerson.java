package com.example.ewgengabruskiy.myui2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.ewgengabruskiy.myui2.data.datas.Keyword;
import com.example.ewgengabruskiy.myui2.data.datas.Person;

import java.util.ArrayList;

/**
 * Created by ewgengabruskiy on 21.11.17.
 */

public class AddPerson extends AppCompatActivity implements View.OnClickListener {


    private final static int VERTICAL = 1;
    private AddPerson.MyAdapter adapter;
    private Person person;
    private ArrayList<AddPerson.BufferKeyword> buf = new ArrayList<>();
    private Button btn;
    private EditText name;


    private class BufferKeyword{

        Integer indexFromPersonKeyword;
        public Keyword keyword;

        public BufferKeyword(Integer indexFromPersonKeyword, Keyword keyword) {
            this.indexFromPersonKeyword = indexFromPersonKeyword;
            this.keyword = keyword;
        }

        public Integer getIndexFromPersonKeyword() {
            return indexFromPersonKeyword;
        }

        public void setIndexFromPersonKeyword(Integer indexFromPersonKeyword) {
            this.indexFromPersonKeyword = indexFromPersonKeyword;
        }

        public Keyword getKeyword() {
            return keyword;
        }

        public void setKeyword(Keyword keyword) {
            this.keyword = keyword;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_person_layout);


        RecyclerView itemCityRecyclerView = findViewById(R.id.recycler_view_add_person); //Найдем наш RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); //Создадим LinearLayoutManager
        layoutManager.setOrientation(VERTICAL);//Обозначим ориентацию
        itemCityRecyclerView.setLayoutManager(layoutManager);//Назначим нашему RecyclerView созданный ранее layoutManager

        adapter = new AddPerson.MyAdapter();
        itemCityRecyclerView.setAdapter(adapter);



        person = getIntent().getParcelableExtra("AddPerson");

        ArrayList<Keyword> addingArr = person.getKeywordList();
        if (addingArr!=null){
            for (int i = 0; i < addingArr.size(); i++)
                buf.add(new AddPerson.BufferKeyword(i, addingArr.get(i)));
        }
        buf.add(new AddPerson.BufferKeyword(null,new Keyword()));

        name = (EditText) findViewById(R.id.new_name);
        name.setText(person.getName());
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                person.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn = findViewById(R.id.add_new_person_button);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btn) {

            if (TextUtils.isEmpty(name.getText().toString())){
                name.setError(getString(R.string.error_field_required));
                return;
            }
            handleBufArrayResult();

            Intent intent = new Intent();
            intent.putExtra("Back", person);
            setResult(RESULT_OK, intent);
            finish();
            return;


        }

    }

    private void handleBufArrayResult() {

        String lastName = buf.get(buf.size()-1).getKeyword().getName();
        if (lastName == null || lastName.equals("")) buf.remove(buf.size() - 1);

        for(int i = 0; i < buf.size(); i++){
            Keyword k = buf.get(i).getKeyword();
            Integer index = buf.get(i).getIndexFromPersonKeyword();
            if (index == null) person.addKeyword(k);
            else person.getKeywordList().get(index).setName(k.getName());
        }
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {

        private EditText categoryNameTextView;
        public AddPerson.MyCustomEditTextListener myCustomEditTextListener;
        public AddPerson.MyCustomOnClickListener myCustomOnClickListener;
        private ImageButton imageButton;

        public MyViewHolder(LayoutInflater inflater, ViewGroup parent, AddPerson.MyCustomEditTextListener myCustomEditTextListener,
                            AddPerson.MyCustomOnClickListener myCustomOnClickListener){
            super(inflater.inflate(R.layout.view_item,parent,false));
            this.myCustomEditTextListener = myCustomEditTextListener;
            categoryNameTextView = (EditText) itemView.findViewById(R.id.category_item_text);
            imageButton = (ImageButton) itemView.findViewById(R.id.del_button);
            imageButton.setVisibility(View.GONE);
            this.categoryNameTextView.addTextChangedListener(myCustomEditTextListener);
            categoryNameTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (!b) {
                        String last = buf.get(buf.size()-1).getKeyword().getName();
                        if (last!=null && !last.equals("")) buf.add(new AddPerson.BufferKeyword(null,new Keyword()));
                        imageButton.setVisibility(View.GONE);
                    }
                    else imageButton.setVisibility(View.VISIBLE);
                }
            });
            this.myCustomOnClickListener = myCustomOnClickListener;
            imageButton.setOnClickListener(myCustomOnClickListener);
        }

        void bind(int position) {
            String category = buf.get(position).getKeyword().getName();
            categoryNameTextView.setText(category);
        }

    }

    //Адаптер для RecyclerView
    private class MyAdapter extends RecyclerView.Adapter<AddPerson.MyViewHolder> {

        @Override
        public AddPerson.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(AddPerson.this);
            return new AddPerson.MyViewHolder(inflater, parent, new AddPerson.MyCustomEditTextListener(), new AddPerson.MyCustomOnClickListener());
        }

        @Override
        public void onBindViewHolder(AddPerson.MyViewHolder holder, int position) {
            holder.myCustomEditTextListener.updatePosition(position);
            holder.myCustomOnClickListener.updatePosition(position);
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return buf.size();
        }
    }

    private class MyCustomEditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            buf.get(position).getKeyword().setName(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //System.out.println(editable.toString());
        }
    }

    private class MyCustomOnClickListener implements View.OnClickListener {

        private int position;
        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            if (buf.size() > 1) {
                Integer index = buf.get(position).getIndexFromPersonKeyword();
                if (index!=null) person.getKeywordList().get(index).setToDelete(true);
                buf.remove(position);
            }

            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        return;
    }
}