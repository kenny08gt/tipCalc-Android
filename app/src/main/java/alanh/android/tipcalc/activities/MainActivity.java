package alanh.android.tipcalc.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import alanh.android.tipcalc.R;
import alanh.android.tipcalc.TipCalcApp;
import alanh.android.tipcalc.fragments.TipHistoryListFragment;
import alanh.android.tipcalc.fragments.TipHistoryListFragmentListener;
import alanh.android.tipcalc.model.TipRecord;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.inputPercentage)
    EditText inputPercentage;
    @Bind(R.id.txtTip)
    TextView txtTip;

    private TipHistoryListFragmentListener fragmentListener;
    private final static int TIP_STEP_CHANGE=1;
    private final static int DEFAULT_TIP_PERCENTAGE=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        TipHistoryListFragment fragment= (TipHistoryListFragment) getSupportFragmentManager()
                                .findFragmentById(R.id.fragmentList);
        fragment.setRetainInstance(true);
        fragmentListener=(TipHistoryListFragmentListener)fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            about();
        }
        return super.onOptionsItemSelected(item);
    }
    @OnClick(R.id.btnSubmit)
    public void handleClickSubmit(){
        hideKeyboard();
        String strInputTotal=inputBill.getText().toString().trim();
        if(!strInputTotal.isEmpty()){
            double total=Double.parseDouble(strInputTotal);
            int tipPercentage=getTipPercentage();

            TipRecord record=new TipRecord();
            record.setBill(total);
            record.setTipPercentage(tipPercentage);
            record.setTimeStamp(new Date());

            String strTip=String.format(getString(R.string.globla_message_tip),record.getTip());
            fragmentListener.addToList(record);
            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);
        }
    }
    @OnClick(R.id.btnClear)
    public void hadleClickClear(){
        fragmentListener.clearList();
    }
    @OnClick(R.id.btnIncrease)
    public void handleClickIncrease(){
        hideKeyboard();
        handleTipChange(TIP_STEP_CHANGE);
    }

    private void handleTipChange(int change) {
        int tipPercentage=getTipPercentage();
        tipPercentage+=change;
        if(tipPercentage>0){
            inputPercentage.setText(String.valueOf(tipPercentage));
        }
    }

    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease(){
        hideKeyboard();
        handleTipChange(-TIP_STEP_CHANGE);
    }
    private int getTipPercentage() {
        int tipPercentage= DEFAULT_TIP_PERCENTAGE;
        String strInput=inputPercentage.getText().toString().trim();
        if(!strInput.isEmpty()){
            tipPercentage=Integer.parseInt(strInput);
        }else{
            inputPercentage.setText(String.valueOf(tipPercentage));
        }
        return tipPercentage;
    }

    private void hideKeyboard() {//ocultar teclado
        InputMethodManager inputManager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }catch(NullPointerException ex){
            Log.e(getLocalClassName()+"", Log.getStackTraceString(ex));
        }
    }

    private void about() {
        TipCalcApp app = (TipCalcApp) getApplication();
        String strUrl = app.getAboutUrl();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);
    }
}
