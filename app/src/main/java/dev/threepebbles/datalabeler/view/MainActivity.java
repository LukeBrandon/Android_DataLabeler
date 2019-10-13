package dev.threepebbles.datalabeler.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import dev.threepebbles.datalabeler.R;
import dev.threepebbles.datalabeler.contact.MainContract;
import dev.threepebbles.datalabeler.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    MainActivityPresenter mainActivityPresenter;
    TextView tvText;
    EditText etEdit;
    Button btnSetText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityPresenter = new MainActivityPresenter(this);

        initUiComponents();
    }

    private void initUiComponents(){
        this.tvText = findViewById(R.id.tvText);
        this.etEdit = findViewById(R.id.etEdit);
        this.btnSetText = findViewById(R.id.btnSetText);

        this.btnSetText.setOnClickListener( v -> {
            mainActivityPresenter.updateTextView(this.etEdit.getText().toString());
        });

    }

    public void updateTextView(String data){
        this.tvText.setText(data);
    }

    public void clearTextView(){
        this.tvText.setText("");
    }


}
