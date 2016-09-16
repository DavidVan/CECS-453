package net.davidvan.mortgagecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int RADIO_BUTTON_1 = 1;
    private static final int RADIO_BUTTON_2 = 2;
    private static final int RADIO_BUTTON_3 = 3;

    private EditText amountBorrowedText;
    private SeekBar seekBar;
    private TextView seekBarText;
    private TextView monthlyPaymentText;
    private RadioGroup loanTerm;
    private RadioButton buttonOne;
    private RadioButton buttonTwo;
    private RadioButton buttonThree;
    private CheckBox taxAndInsurance;
    private Button calculate;

    private double amountBorrowed;
    private double interestRate;
    private double monthlyPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountBorrowedText = (EditText) findViewById(R.id.amount_to_borrow);
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        seekBarText = (TextView) findViewById(R.id.seek_bar_rate);
        monthlyPaymentText = (TextView) findViewById(R.id.calculated_total);
        loanTerm = (RadioGroup) findViewById(R.id.radio_group);
        buttonOne = (RadioButton) findViewById(R.id.radio_15_years);
        buttonTwo = (RadioButton) findViewById(R.id.radio_20_years);
        buttonThree = (RadioButton) findViewById(R.id.radio_30_years);
        taxAndInsurance = (CheckBox) findViewById(R.id.checkbox_taxes_insurance);
        calculate = (Button) findViewById(R.id.calculate);

        buttonOne.setId(RADIO_BUTTON_1);
        buttonTwo.setId(RADIO_BUTTON_2);
        buttonThree.setId(RADIO_BUTTON_3);

        interestRate = seekBar.getProgress() / 10.0;

        seekBarText.setText(String.valueOf(interestRate) + "%");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                interestRate = progress / 10.0;
                seekBarText.setText(String.valueOf(interestRate + "%"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amountBorrowedText.length() == 0) {
                    Toast.makeText(MainActivity.this, "Please enter an amount to borrow!", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    amountBorrowed = Double.parseDouble(amountBorrowedText.getText().toString());
                }

                int checkedRadioButton = loanTerm.getCheckedRadioButtonId();

                int months;
                if (checkedRadioButton == RADIO_BUTTON_1) {
                    months = 15 * 12;
                }
                else if (checkedRadioButton == RADIO_BUTTON_2) {
                    months = 20 * 12;
                }
                else if (checkedRadioButton == RADIO_BUTTON_3) {
                    months = 30 * 12;
                }
                else {
                    Toast.makeText(MainActivity.this, "Please select a loan term!", Toast.LENGTH_LONG).show();
                    return;
                }

                double taxAndInsuranceAmount;
                if (taxAndInsurance.isChecked()) {
                    taxAndInsuranceAmount = amountBorrowed * 0.001;
                }
                else {
                    taxAndInsuranceAmount = 0;
                }

                if (interestRate != 0) {
                    double monthlyInterestRate = interestRate / 1200;
                    monthlyPayment = (amountBorrowed * (monthlyInterestRate / (1 -Math.pow(1 + monthlyInterestRate, (months * -1))))) + taxAndInsuranceAmount;
                    monthlyPaymentText.setText(String.format("$%.2f Per Month", monthlyPayment));
                }
                else {
                    monthlyPayment = (amountBorrowed / months) + taxAndInsuranceAmount;
                    monthlyPaymentText.setText(String.format("$%.2f Per Month", monthlyPayment));
                }

            }
        });

    }
}
