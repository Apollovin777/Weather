package com.example.android.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SearchFragment extends Fragment {

    private AutoCompleteTextView mEditText;
    private Button mButton;
    private RecyclerView mRecyclerView;

    public static SearchFragment newInstance(){
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment,container,false);
        mEditText = view.findViewById(R.id.search_TextView);
        mButton = view.findViewById(R.id.search_Button);

        mRecyclerView =  view.findViewById(R.id.search_RecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
