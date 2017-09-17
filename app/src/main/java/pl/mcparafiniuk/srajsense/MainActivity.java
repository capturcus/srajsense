package pl.mcparafiniuk.srajsense;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToNewDataPointDefinition(View view)
    {
        Intent intent = new Intent(MainActivity.this, NewDataPointDefinitionActivity.class);
        startActivity(intent);
    }
}
