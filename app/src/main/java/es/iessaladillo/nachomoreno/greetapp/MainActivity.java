package es.iessaladillo.nachomoreno.greetapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import es.iessaladillo.nachomoreno.greetapp.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MainActivityBinding binding;
    private int times;
    private String gender;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupViews();
    }

    private  void swapGender(){

        if (binding.rdbMr.isChecked()) {
            binding.imgShow.setImageResource(R.drawable.ic_mr);
            gender = "Mr.";
        } else if (binding.rdbMrs.isChecked()) {
            binding.imgShow.setImageResource(R.drawable.ic_mrs);
            gender = "Mrs.";
        } else if (binding.rdbMs.isChecked()) {
            binding.imgShow.setImageResource(R.drawable.ic_ms);
            gender = "Ms.";
        }
    }

    private void setupViews(){
        binding.btnGreet.setOnClickListener(this);
        binding.btnGreet.setText("Greet");
        showTimes();

        binding.rdbMr.setOnCheckedChangeListener((x, y) -> swapGender());
        binding.rdbMrs.setOnCheckedChangeListener((x, y) -> swapGender());
        binding.rdbMs.setOnCheckedChangeListener((x, y) -> swapGender());

        binding.btnGreet.setOnClickListener(v -> Greet());

        binding.premium.setOnClickListener(v -> isPremium());

    }
    private void Greet() {
        if (!binding.txtNameIn.getText().toString().isEmpty() && !binding.txtSirnameIn.getText().toString().isEmpty()) {
            if (binding.poliChck.isChecked()) {
                binding.txtSaludo1.setText("Good afternoon " + gender + " " + binding.txtNameIn.getText().toString() +
                        " " + binding.txtSirnameIn.getText().toString() +", i hope you're having a wonderful day.");
                binding.pbar.setProgress(i++);
                times++;
            } else {
                binding.txtSaludo1.setText("Yo " + gender + binding.txtNameIn.getText().toString()
                        + " " + binding.txtSirnameIn.getText().toString() + ", what's up homie?");
                binding.pbar.setProgress(i++);
                times++;
            }

            if (i > 10) {
                binding.txtSaludo1.setText("Buy premium suscription to go on greeting!!");
            }

            else if  (i < 11) {
                binding.lblTimes.setText(i + " of 10");
            }


        } else if (binding.txtNameIn.getText().toString().isEmpty() && binding.txtSirnameIn.getText().toString().isEmpty()){
            binding.txtSaludo1.setText("");
        }
    }


    private void isPremium() {
        if (binding.premium.isChecked()) {
            binding.pbar.setVisibility(View.GONE);
            binding.lblTimes.setVisibility(View.GONE);
            i = 0;
            reset();
        } else {
            binding.pbar.setVisibility(View.VISIBLE);
            binding.lblTimes.setVisibility(View.VISIBLE);
            binding.pbar.setProgress(0);
            binding.lblTimes.setText( i + " of 10");
        }
    }

    public void onClick(View v) {
        times++;
        showTimes();
    }

    private void reset() {
        Greet();
    }

    private void showTimes() {
        if (times < 11) {
            binding.lblTimes.setText(i + " of 10");
        }
        binding.txtSaludo1.setVisibility(View.VISIBLE);
        binding.txtSaludo1.setText(" ");
    }
}