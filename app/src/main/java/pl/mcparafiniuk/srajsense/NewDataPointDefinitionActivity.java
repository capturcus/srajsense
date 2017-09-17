package pl.mcparafiniuk.srajsense;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewDataPointDefinitionActivity extends Activity {

    RowAdapter rowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_data_point_definition);

        final ArrayList<DataPointDefinition> list = new ArrayList<>();

        final ListView listview = (ListView) findViewById(R.id.listView);

        /*adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);*/
        rowAdapter = new RowAdapter(getApplicationContext());
        listview.setAdapter(rowAdapter);

        final Button addTextButton = (Button) findViewById(R.id.button7);
        addTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rowAdapter.add(new TextDataPointDefinition());
                rowAdapter.notifyDataSetChanged();
                Log.d("MLEKO", "addText " + String.valueOf(listview.getAdapter().getCount()));
            }
        });
        final Button addOptionsButton = (Button) findViewById(R.id.button8);
        addOptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rowAdapter.add(new OptionsDataPointDefinition());
                rowAdapter.notifyDataSetChanged();
                Log.d("MLEKO", "addOptions " + String.valueOf(listview.getAdapter().getCount()));
            }
        });
    }
}

abstract class DataPointDefinition {
    public abstract View getView(Context context);

    String name;
}

class TextDataPointDefinition extends DataPointDefinition {
    public View getView(Context context) {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.text_data_point_definition_layout, null);
        return v;
    }
}

class OptionsDataPointDefinition extends DataPointDefinition {

    OptionRowAdapter adapter;

    @Override
    public View getView(Context context) {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.options_data_point_definition_layout, null);
        Button addOptionButton = (Button) v.findViewById(R.id.add_option);

        final ListView lv = (ListView) v.findViewById(R.id.option_list_view);
        adapter = new OptionRowAdapter(context);
        lv.setAdapter(adapter);

        addOptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OptionRowAdapter la = (OptionRowAdapter) lv.getAdapter();
                la.add(new Option());
                la.notifyDataSetChanged();
                Log.d("MLEKO", "adapter count " + String.valueOf(lv.getAdapter().getCount()) + " lv count " + String.valueOf(lv.getCount()));
                lv.deferNotifyDataSetChanged();
            }
        });
        return v;
    }
}

/**
 * Created by marcin on 30.06.15.
 */
class RowAdapter extends ArrayAdapter<DataPointDefinition> {

    RowAdapter(@NonNull Context context) {
        super(context, R.layout.text_data_point_definition_layout);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataPointDefinition n = getItem(position);
        return n.getView(getContext());
    }
}

class Option {
    String option;
    View getView(Context context) {
        LinearLayout ll = new LinearLayout(context);
        ll.addView(new EditText(context));
        ll.addView(new EditText(context));
        ll.addView(new EditText(context));
        return ll;
    }
}

/**
 * Created by marcin on 30.06.15.
 */
class OptionRowAdapter extends ArrayAdapter<Option> {

    public OptionRowAdapter(@NonNull Context context) {
        super(context, R.layout.options_data_point_definition_layout);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Option n = getItem(position);
        return n.getView(getContext());
    }
}