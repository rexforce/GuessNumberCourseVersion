package tw.idv.rex.example.guessnumbercourseversion;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    private int secret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        geneSecret();
    }

    private void geneSecret() {
        Random r = new Random();
        secret = r.nextInt(10)+1;
        Log.d("SECRET = ", secret + "");
    }

    public  void guess(View v){
        final EditText number = (EditText)findViewById(R.id.number);
        int num = Integer.parseInt(number.getText().toString());
        String msg = null;
        if(num > secret){
            msg = "再小一點";
        }else if (num < secret){
            msg = "再大一點";
        }else{
            msg = "恭喜，秘密數字為" + secret;
            new AlertDialog.Builder(this)
                    .setTitle("MSG")
                    .setMessage(msg)
                    .setPositiveButton("再來一局",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            geneSecret();
                            number.setText("");
                        }
                    })
                    .setNegativeButton("不玩了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish(); //結束程式
                        }
                    })
                    .show();
            return;
        }

        new AlertDialog.Builder(this)
                .setTitle("MSG")
                .setMessage(msg)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        number.selectAll(); //當區域變數要被匿名類別裡面用到時，前面要加final
                    }
                })
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
