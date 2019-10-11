package com.example.rickmortykarakter.pages;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rickmortykarakter.R;
import com.example.rickmortykarakter.adapter.ListKarakterAdapter;
import com.example.rickmortykarakter.model.Karakter;
import com.example.rickmortykarakter.util.APIRickMorty;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Home.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<Karakter> listKarakter = new ArrayList<>();
    private ListKarakterAdapter listKarakterAdapter = new ListKarakterAdapter(listKarakter);

    ShimmerFrameLayout parentShimmerLayout;
    FloatingActionButton fabRetry;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        getCharactersAsync asyncGetChars = new getCharactersAsync();
        asyncGetChars.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fabRetry = getView().findViewById(R.id.retryButton);
        parentShimmerLayout = getView().findViewById(R.id.parentShimmerLayout);
        parentShimmerLayout.startShimmerAnimation();

        fabRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new getCharactersAsync().execute();
                fabRetry.hide();
            }
        });

        RecyclerView rvKarakters = getView().findViewById(R.id.rvKarakter);
        rvKarakters.setHasFixedSize(true);
        rvKarakters.setLayoutManager(new LinearLayoutManager(getContext()));
        rvKarakters.setAdapter(listKarakterAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //AsyncTask untuk mendapatkan data dari API Rick&Morty
    private class getCharactersAsync extends AsyncTask<String, Void, ArrayList>{
        APIRickMorty data = new APIRickMorty();
        @Override
        protected ArrayList doInBackground(String... strings) {
            return data.getCharacters();
        }

        @Override
        protected void onPostExecute(ArrayList arrayList) {
            super.onPostExecute(arrayList);
            if(arrayList!=null){
                parentShimmerLayout.setVisibility(View.GONE);
                parentShimmerLayout.stopShimmerAnimation();
                listKarakter.addAll(arrayList);
                listKarakterAdapter.notifyDataSetChanged();
            }else{
                Toast.makeText(getContext(), "Gagal mengambil data",Toast.LENGTH_LONG).show();
                fabRetry.show();
//                new getCharactersAsync().execute();
            }
//            for(Karakter a: listKarakter){
//                Log.d("character",a.getNama());
//            }

        }
    }
}
