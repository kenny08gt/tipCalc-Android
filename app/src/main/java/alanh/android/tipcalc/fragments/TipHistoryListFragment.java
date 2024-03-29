package alanh.android.tipcalc.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import alanh.android.tipcalc.Adapters.OnItemClickListener;
import alanh.android.tipcalc.Adapters.TipAdapter;
import alanh.android.tipcalc.R;
import alanh.android.tipcalc.activities.TipActivityDetail;
import alanh.android.tipcalc.model.TipRecord;
import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener,OnItemClickListener{
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private TipAdapter adapter;
    public TipHistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tip_history_list, container, false);
        ButterKnife.bind(this,view);
        initAdapter();
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private void initAdapter() {
        if(adapter==null){
            adapter=new TipAdapter(getActivity().getApplicationContext(),this);
        }
    }

    @Override
    public void addToList(TipRecord record) {
        adapter.add(record);
    }

    @Override
    public void clearList() {
        adapter.clear();
    }

    @Override
    public void onItemClick(TipRecord tipRecord) {
        Intent intent=new Intent(getActivity(), TipActivityDetail.class);
        intent.putExtra(TipActivityDetail.TIP_KEY,tipRecord.getTip());
        intent.putExtra(TipActivityDetail.DATE_KEY,tipRecord.getDateFormatted());
        intent.putExtra(TipActivityDetail.BILL_TOTAL_KEY,tipRecord.getBill());
        startActivity(intent);
    }
}
