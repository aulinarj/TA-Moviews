package id.sch.smktelkom_mlg.privateassignment.xirpl505.moviews;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl505.moviews.adapter.SourceAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl505.moviews.model.Result;
import id.sch.smktelkom_mlg.privateassignment.xirpl505.moviews.model.SourcesResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl505.moviews.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl505.moviews.service.VolleySingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment {

    ArrayList<Result> mList = new ArrayList<>();
    SourceAdapter mAdapter;

    public UpdateFragment() {
        // Required empty public constructor
    }


    private void downloadDataSources() {
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=7a60bfebebe5a60abd25c632486bfcea";

        GsonGetRequest<SourcesResponse> myRequest = new GsonGetRequest<SourcesResponse>
                (url, SourcesResponse.class, null, new Response.Listener<SourcesResponse>() {

                    @Override
                    public void onResponse(SourcesResponse response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));
                        mList.addAll(response.results);
                        mAdapter.notifyDataSetChanged();

                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(this.getActivity()).addToRequestQueue(myRequest);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerViewUpdate);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mAdapter = new SourceAdapter(this.getActivity(), mList);
        recyclerView.setAdapter(mAdapter);

        downloadDataSources();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false);
    }

}
