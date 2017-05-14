package id.sch.smktelkom_mlg.privateassignment.xirpl505.moviews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl505.moviews.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl505.moviews.model.Result;


public class ComingSoonAdapter extends RecyclerView.Adapter<ComingSoonAdapter.ViewHolder> {
    ArrayList<Result> list;
    IComingSoonAdapter mIComingSoonAdapter;
    Context context;

    public ComingSoonAdapter(Context context, ArrayList<Result> list) {
        this.list = list;
        this.context = context;
        mIComingSoonAdapter = (IComingSoonAdapter) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coming_soon_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result result = list.get(position);
        holder.tvName.setText(result.title);
        holder.tvDesc.setText(result.overview);
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w500" + result.poster_path)
                .into(holder.gambar);
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public interface IComingSoonAdapter {
        void showArticles(String title, String overview, String poeter_path);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView gambar;
        TextView tvName;
        TextView tvDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            gambar = (ImageView) itemView.findViewById(R.id.imageViewPoster);
            tvName = (TextView) itemView.findViewById(R.id.textViewName);
            tvDesc = (TextView) itemView.findViewById(R.id.textViewDesc);

        }
    }
}
