package alanh.android.tipcalc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import alanh.android.tipcalc.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TipActivityDetail extends AppCompatActivity {

    @Bind(R.id.txtBillTotal)
    TextView txtBillTotal;
    @Bind(R.id.txtTip)
    TextView txtTip;
    @Bind(R.id.txtTimeStamp)
    TextView txtTimeStamp;

    public final static String TIP_KEY="tip";
    public final static String DATE_KEY="timestamp";
    public final static String BILL_TOTAL_KEY="total";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_activity_detail);
        ButterKnife.bind(this);

        Intent intent=getIntent();
        String strTotal=String.format(getString(R.string.tipdeatil_message_tip),
                intent.getDoubleExtra(BILL_TOTAL_KEY, 0d));
        String strTip=String.format(getString(R.string.globla_message_tip),
                intent.getDoubleExtra(TIP_KEY, 0d));

        txtTip.setText(strTip);
        txtBillTotal.setText(strTotal);
        txtTimeStamp.setText(intent.getStringExtra(DATE_KEY));

    }
}
