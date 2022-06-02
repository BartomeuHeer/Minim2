package edu.upc.eetac.dsa.minim2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    private List<Repo> userRepos;

    public RecyclerViewAdapter(List<Repo> mr){
            this.userRepos = mr;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView language;

        public MyViewHolder(@NonNull View view) {
            super(view);
            language = (TextView) view.findViewById(R.id.tvLanguage);
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_repos,parent,false);
        return new MyViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        String language = userRepos.get(position).getLanguage();
        holder.language.setText(language);
    }

    @Override
    public int getItemCount() {
        return userRepos.size();
    }
}
