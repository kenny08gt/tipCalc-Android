package alanh.android.tipcalc.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import alanh.android.tipcalc.R;
import alanh.android.tipcalc.model.TipRecord;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alan2 on 2/06/2016.
 */
public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder> {
    List<TipRecord> dataset;
    Context context;
    public TipAdapter(Context context,List<TipRecord> dataset){
        this.context=context;
        this.dataset=dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TipRecord element=dataset.get(position);
        String strTip=String.format(context.getString(R.string.globla_message_tip),element.getTip());
        holder.txtContent.setText(strTip);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
    public void add(TipRecord record){
        dataset.add(0,record);
        notifyDataSetChanged();
    }
    public void clear(){
        dataset.clear();
        notifyDataSetChanged();
    }
    public static class ViewHolder extends  RecyclerView.ViewHolder{
        @Bind(R.id.txtContent)
        TextView txtContent;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
