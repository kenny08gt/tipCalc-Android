package alanh.android.tipcalc.fragments;

import alanh.android.tipcalc.model.TipRecord;

/**
 * Created by alan2 on 2/06/2016.
 */
public interface TipHistoryListFragmentListener {
    void addToList(TipRecord record);
    void clearList();
}
