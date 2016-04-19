package com.curso.ialvarez.supermercado;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by ialvarez on 14/4/2016.
 */
public class SuperCursorAdapter extends CursorAdapter
{

    private SuperDbAdapter dbAdapter = null ;

    public SuperCursorAdapter(Context context, Cursor c)
    {
        super(context, c);
        dbAdapter = new SuperDbAdapter(context);
        dbAdapter.abrir();
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        TextView tv = (TextView) view ;

        tv.setText(cursor.getString(cursor.getColumnIndex(SuperDbAdapter.C_COLUMNA_NOMBRE)));

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);

        return view;
    }
}
