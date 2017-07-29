package deva.transitiveclosure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView t1,t6;
    private EditText e1;
    private Spinner spinner;

    public class MyException extends Exception {
        public MyException(String msg)
        {
            super(msg);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        assert button != null;
        t1 = (TextView) findViewById(R.id.textView4);
        t6 = (TextView) findViewById(R.id.textView6);
        spinner = (Spinner) findViewById(R.id.spinner);
        e1 = (EditText) findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert();
            }
        });

    }
    private void convert()
    {
        int[][] a = new int[9][9];
        int[][] trans = new int[9][9];
        int i,j,k,n;
        n = Integer.parseInt(spinner.getSelectedItem().toString());
        try {
            String[] string = e1.getText().toString().split("");
            k = 1;
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    a[i][j] = Integer.parseInt(string[k]);
                    if(a[i][j]!=0&&a[i][j]!=1)
                        throw new MyException("Please enter valid 0 or 1 relation.");
                    k++;
                }
                k++;
            }
            t6.setText("  ");
            for(i=0;i<n;i++)
                t6.append("\t\t"+(i+1)+".");
            t6.append("\n");
            for (i = 0; i < n; i++) {
                t6.append((i+1)+".");
                for (j = 0; j < n; j++) {
                    t6.append("\t\t" + a[i][j]);
                }
                if(i!=n-1)
                    t6.append("\n");
            }
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    if (a[i][j] == 1)
                        trans[i][j] = 1;
                    else
                        trans[i][j] = 0;
                }
            }
            for (k = 0; k < n; k++) {
                for (i = 0; i < n; i++) {
                    for (j = 0; j < n; j++) {
                        if (trans[i][j] == 1 || ((a[i][k] * a[k][j]) == 1)) {
                            trans[i][j] = 1;
                        }
                    }
                }
                for (i = 0; i < n; i++) {
                    for (j = 0; j < n; j++) {
                        if (trans[i][j] == 1) {
                            a[i][j] = 1;
                        }
                    }
                }
            }
            t1.setText("  ");
            for(i=0;i<n;i++)
                t1.append("\t\t"+(i+1)+".");
            t1.append("\n");
            for (i = 0; i < n; i++) {
                t1.append((i+1)+".");
                for (j = 0; j < n; j++) {
                    t1.append("\t\t" + trans[i][j]);
                }
                if(i!=n-1)
                    t1.append("\n");
            }
        }catch(Exception e)
        {
            t6.setText("");
            t1.setText(e.toString());
        }
    }
}
