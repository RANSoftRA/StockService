package main.java.client.android;

import java.util.ArrayList;

import android.content.Context;
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
        
        TextView id = (TextView) convertView.findViewById(R.id.tv_stockId);
        id.setText(stock.getId());
        TextView price = (TextView) convertView.findViewById(R.id.tv_stockPrice);
        price.setText(stock.getPrice() + "   >");  

        return convertView;
	}
	
	

}
