package com.example.a2223damp3grup01.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2223damp3grup01.R;
import com.example.a2223damp3grup01.adapters.ReviewAdapter;
import com.example.a2223damp3grup01.objects.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentProfile#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class FragmentProfile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<Review> reviews;
    RecyclerView reviewsRecycler;
    ReviewAdapter reviewAdapter;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentProfile newInstance(String param1, String param2) {
        FragmentProfile fragment = new FragmentProfile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentProfile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void initRecyclerReview(){
        reviews = new ArrayList<>();
        reviews.add(new Review(3,"Comentario","Repsol"));
        reviews.add(new Review(2,"To guapa la gasolinera sisi lolol","Repsol"));
        reviews.add(new Review(0,"Lorem Ipsum tremendo texto yokse wow","Repsol"));
        reviews.add(new Review(5,"La gasolinera muy guapa pero leanse One Piece que esta tremendo","Repsol"));
        reviews.add(new Review(5,"La gasolinera muy guapa pero leanse One Piece que esta tremendo","Repsol"));
        reviews.add(new Review(5,"La gasolinera muy guapa pero leanse One Piece que esta tremendo","Repsol"));
        reviews.add(new Review(5,"La gasolinera muy guapa pero leanse One Piece que esta tremendo","Repsol"));
        reviewAdapter = new ReviewAdapter(reviews);
        reviewsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        reviewsRecycler.setHasFixedSize(true);
        reviewsRecycler.setAdapter(reviewAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_profile,container,false);
        reviewsRecycler=vista.findViewById(R.id.reviewsRecycler);
        initRecyclerReview();
        return vista;
    }
}