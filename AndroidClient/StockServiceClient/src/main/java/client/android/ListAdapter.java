package main.java.client.android;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.stockserviceclient.R;

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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		convertView = ( RelativeLayout ) inflater.inflate( resourceId, null );
        
        Stock stock = getItem( position );
        
        TextView tvId = (TextView) convertView.findViewById(R.id.tv_stockId);
        tvId.setText(stock.getId());
        TextView tvPrice = (TextView) convertView.findViewById(R.id.tv_stockPrice);
        tvPrice.setText(stock.getPrice() + "   >");  
        
        if(stock.getPrice() < 0)
        	tvPrice.setTextColor(Color.RED);
        else
        	tvPrice.setTextColor(Color.GREEN);
        
        return convertView;
	}
	
	

}
