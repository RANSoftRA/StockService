package com.stockservice.client.layoutadapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.stockserviceclient.R;
import com.stockservice.client.data.Stock;

public class ListAdapter extends ArrayAdapter<Stock> {
	
	LayoutInflater inflater;
	int resourceId;
	Context ctx;
	public ListAdapter(Context context, int resourceId,
			ArrayList<Stock> person) {
		super(context, resourceId, person);
		inflater = LayoutInflater.from(context);
		this.resourceId = resourceId;	
		this.ctx = context;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		convertView = (RelativeLayout) inflater.inflate( resourceId, null );
        
        Stock stock = getItem(position);
        
        TextView tvId = (TextView) convertView.findViewById(R.id.tv_stockId);
        tvId.setText(stock.getName());
        TextView tvChange = (TextView) convertView.findViewById(R.id.tv_stockChange);
        tvChange.setText(""+stock.getChange());  
        
        if(stock.getChange() < 0) {
        	tvChange.setText(stock.getChange() + " $");
        	tvChange.setTextColor(Color.RED);
        }
        	
        else {
        	tvChange.setText("+" + stock.getChange() + " $");
        	tvChange.setTextColor(Color.GREEN);
        }

        return convertView;
	}
}
