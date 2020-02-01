package com.example.t1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ownbookAdapter extends RecyclerView.Adapter<ownbookAdapter.ownbookView> {
    public ownbookAdapter(Context mContext, ArrayList<mybookitem> mallbookitemlist) {
        this.mContext = mContext;
        this.mallbookitemlist = mallbookitemlist;
    }

    Context mContext;
    private ArrayList<mybookitem> mallbookitemlist;
    @NonNull
    @Override
    public ownbookView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.row_mybooks,parent,false);
        return new ownbookView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ownbookView holder, int position) {
        holder.mbkname.setText(mallbookitemlist.get(position).getBookname());
        holder.mbkauthor.setText(mallbookitemlist.get(position).getAuthor());
        holder.mbkisbn.setText(mallbookitemlist.get(position).getIsbn());

    }

    @Override
    public int getItemCount() {
        return mallbookitemlist.size();
    }

    public class ownbookView extends RecyclerView.ViewHolder{

        private Context context;

        public TextView mbkname;
        public TextView mbkauthor;
        public TextView mbkisbn;


        public ownbookView(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();
            mbkname=itemView.findViewById(R.id.mybksinglename);
            mbkauthor=itemView.findViewById(R.id.mybksingleauthor);
            mbkisbn=itemView.findViewById(R.id.mybksingleisbn);
        }
    }

}
