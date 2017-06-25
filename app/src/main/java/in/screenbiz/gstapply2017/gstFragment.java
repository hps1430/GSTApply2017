package in.screenbiz.gstapply2017;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by harsh singh on 19-06-2017.
 */

public class gstFragment extends Fragment{

    View gstCalculatorView;

    EditText principal_amount , gstrate , netgstamount;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button gstadd , gstremove ;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

       gstCalculatorView = inflater.inflate(R.layout.fragment_gst_calculator,container,false);



        principal_amount = (EditText) gstCalculatorView.findViewById(R.id.gst_amount);
        gstrate = (EditText) gstCalculatorView.findViewById(R.id.gst_Rate);
        netgstamount = (EditText) gstCalculatorView.findViewById(R.id.gst_net_amount);
        netgstamount.setText("0.0");
        netgstamount.setEnabled(false);
        netgstamount.setClickable(true);



        radioGroup = (RadioGroup) gstCalculatorView.findViewById(R.id.radioGroup);

        radioGroup.check(R.id.radioButton5);

        gstrate.setText("5");



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {


                int checkedradioId = radioGroup.getCheckedRadioButtonId();

                radioButton = (RadioButton) gstCalculatorView.findViewById(checkedradioId);

                String radiotext = radioButton.getText().toString();




                gstrate.setText(radiotext.substring(0,radiotext.indexOf("%")));




            }
        });





        gstadd = (Button) gstCalculatorView.findViewById(R.id.button_gstadd);
        gstadd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                int check_result = check_arguments();
                if(check_result==0)return;



                Float amount = Float.parseFloat(principal_amount.getText().toString());
                Float gstperc = Float.parseFloat(gstrate.getText().toString());

                Float netgstamount1 = amount + ((amount*gstperc)/100);


                netgstamount.setText(netgstamount1.toString());



            }
        });





        gstremove = (Button) gstCalculatorView.findViewById(R.id.button_gstremove);
        gstremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int check_result = check_arguments();
                if(check_result==0)return;



                Float amount = Float.parseFloat(principal_amount.getText().toString());
                Float gstperc = Float.parseFloat(gstrate.getText().toString());

                Float netgstamount1 = amount - (amount - ( amount * ( 100 / ( 100 + gstperc ) ) ));



                netgstamount.setText(netgstamount1.toString());


            }
        });






        return gstCalculatorView;
    }

    private int check_arguments() {


        int result=1;

        String strUserName = principal_amount.getText().toString();

        if(TextUtils.isEmpty(strUserName)) {
            principal_amount.setError("Input Principal Amount");
            result = 0;
        }


        if(TextUtils.isEmpty(gstrate.getText().toString())){

            gstrate.setError("Input GST Rate");
            result = 0;
        }




        return result;



    }


}
