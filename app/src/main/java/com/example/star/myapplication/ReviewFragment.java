package com.example.star.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class ReviewFragment extends Fragment {
    private String title;
    private String nickname;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Intent intent=getActivity().getIntent();
        title =intent.getStringExtra("title");
        nickname = intent.getStringExtra("nickname");
        View view = inflater.inflate(R.layout.fragment_review,null);

        Button btn = (Button)view.findViewById(R.id.btnwrite);
        Button.OnClickListener bClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReviewActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("nickname",nickname);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        };
        btn.setOnClickListener(bClickListener);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        new GetReview(getActivity(),title).execute();
    }


}
