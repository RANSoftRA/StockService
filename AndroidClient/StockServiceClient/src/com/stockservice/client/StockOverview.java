package com.stockservice.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.stockserviceclient.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.stockservice.client.data.DummyDataGenerator;
import com.stockservice.client.data.Stock;
import com.stockservice.client.data.StockHistory;
import com.stockservice.client.layoutadapter.ListAdapter;

public class StockOverview extends Activity implements TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v13.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	
	ActionBar actionBar;
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	static ViewPager mViewPager;
	ListView lvs;
	
	//the currently chosen stockSymbol
	protected static String stockSymbol = "YHOO";
	
	private static final String URL = "http://192.168.0.2:8080";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stock_overview);	

		// Set up the action bar.
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the two
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
		
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);		
				
		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(i)).setTabListener(this));
		}
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stock_overview, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}
	
	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return the respective fragment (defined as a static inner class
			// below).
			switch(position) {
			case 0:
				return StockOverviewFragment.newInstance(position + 1);
			case 1: 
				return DetailsFragment.newInstance(position + 1);
			}
			return null;
		}

		@Override
		public int getCount() {
			// Show 2 total pages.
			return 2;
		}

		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_tab1).toUpperCase(l);
			case 1:
				return getString(R.string.title_tab2).toUpperCase(l);
			}
			return null;
		}
	}
	
	public static class StockOverviewFragment extends Fragment implements OnItemClickListener {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";	
		
		private DummyDataGenerator dummyDatGen;
		
		private ListAdapter listAdapter;
		
		private ArrayList<Stock> stocks;
		
		private LayoutInflater inflater;
		
		private View rootView;
		
		private ListView lv;		
		
		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static StockOverviewFragment newInstance(int sectionNumber) {
			StockOverviewFragment fragment = new StockOverviewFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			
			this.inflater=inflater;			
			rootView = inflater.inflate(R.layout.fragment_stock_overview, container, false);			
			lv = (ListView) rootView.findViewById(R.id.lv_stockOverview);	
			
			dummyDatGen = new DummyDataGenerator();
        	
			//Initialize ListView with DummyData
        	listAdapter = new ListAdapter(inflater.getContext(), R.layout.list_entry, dummyDatGen.getStocks()); 	
        	lv.setAdapter(listAdapter);
        	lv.setOnItemClickListener(this);
			
				try {
					new StockLoaderTask().execute().get(6, TimeUnit.SECONDS);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TimeoutException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return rootView;
		}
		
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long itemId) {
			
			lv = (ListView) rootView.findViewById(R.id.lv_stockOverview);
			
			Stock stock = (Stock) lv.getItemAtPosition((int) itemId);
			stockSymbol = stock.getSymbol();
			mViewPager.setCurrentItem(1);
			
			try {
				//always 2, isn't used anyway
				DetailsFragment.getInstance(2).new StockDetailTask().execute().get(6, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private class StockLoaderTask extends AsyncTask<Void, Void, List<Stock>> {  
			
			@Override
	        protected List<Stock> doInBackground(Void... params) {					
				
	            try {	            	
	                RestTemplate restTemplate = new RestTemplate();
	                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	                ResponseEntity<Stock[]> responseEntity = restTemplate.getForEntity(URL+"/finance/stocks", Stock[].class);
	                List<Stock> stockList= Arrays.asList(responseEntity.getBody());  	                
	                return stockList;
	                
	            } catch (Exception e) {
	                Log.e("StockLoader", e.getMessage(), e);
	            }

	            return null;
	        }			
		  
	        @Override
	        protected void onPostExecute(List<Stock> list) {
	        	if(list!=null&&list.size()!=0){
	            	stocks = new ArrayList<Stock>(list);    
	            	listAdapter = new ListAdapter(inflater.getContext(), R.layout.list_entry, stocks); 	
		        	lv.setAdapter(listAdapter);	 	
	            }else{
	            	Toast.makeText(getActivity(), "Daten konnten nicht geladen werden!", Toast.LENGTH_LONG).show();
	            }  
	        }
	    }
	}

	public static class DetailsFragment extends Fragment{
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";
		
		private GraphView lineGraph;
		
		LayoutInflater inflater;
		View rootView;
		
		private static DetailsFragment fragment;
		
		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static DetailsFragment newInstance(int sectionNumber) {
			fragment = new DetailsFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}
		
		public static DetailsFragment getInstance(int sectionNumber)
		{
			if(fragment != null)
				return fragment;
			else
				return newInstance(sectionNumber);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			this.inflater=inflater;		
			this.rootView = inflater.inflate(R.layout.fragment_stock_details, container, false);

			//initialize graph
			lineGraph = new LineGraphView(
    			    this.getActivity(), // context
    			    stockSymbol
    			);
			
			RelativeLayout layout = (RelativeLayout) rootView.findViewById(R.id.layout_relativeLayout);
			layout.addView(lineGraph);	
			
			try {
				new StockDetailTask().execute().get(6, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			}
			
			return rootView;
		}
		
		private class StockDetailTask extends AsyncTask<Void, Void, List<StockHistory>> {  
			
			@Override
	        protected List<StockHistory> doInBackground(Void... params) {					
	            try {	            	
	                RestTemplate restTemplate = new RestTemplate();
	                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	                ResponseEntity<StockHistory[]> responseEntity = restTemplate.getForEntity(URL+"/finance/stocks/"+stockSymbol+"/history", StockHistory[].class);
	                List<StockHistory> historyList= Arrays.asList(responseEntity.getBody());  	                
	                return historyList;
	            } catch (Exception e) {
	                Log.e("StockLoader", e.getMessage(), e);
	            }
	            return null;
	        }			
		  
	        @SuppressLint("SimpleDateFormat")
			@Override
	        protected void onPostExecute(List<StockHistory> historyList) {
	        	if((historyList!=null) && (historyList.size()!=0)){
	            	ArrayList<StockHistory> history = new ArrayList<StockHistory>(historyList);	      
	            	
	            	//clear old data
	            	lineGraph.removeAllSeries();
	            	
	            	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	            	String stringDate = "";
	            	Calendar cal = Calendar.getInstance();
	            	Date d;
	            	int dayBefore = 0;
	            	int day = 0;
	            	int month = 0;
	            	double date = 0.0;
	            	double value = 0.0;
	            	int arrayLength = 5;
	            	int loopLength = history.size();
	            	GraphViewData[] data = new GraphViewData[arrayLength];
	            	String[] horizontalLabels = new String[arrayLength];
	            	int[] days = new int[arrayLength]; 
	            	int[] months = new int[arrayLength];
	            	double[] values = new double[arrayLength];
	            	int position = 1;
	            	
	            	//loop through stock history and add every fifth and the last value to the data series (actually to another array at first to switch the values later on)
	            	for(int i = 0; i <= loopLength; i += 5) {
	            		
	            		//necessary because values cannot be equally divided
	            		if(i == 20)
	            			i = 19;
	            		
	            		StockHistory s = history.get(i);
	            		
	            		stockSymbol = s.getSymbol();
	            		stringDate = s.getDate();
	            		value = s.getValue();
	            		
	            		try {
	            			d = formatter.parse(stringDate);
	            			cal.setTime(d);
	            			day = cal.get(Calendar.DAY_OF_MONTH);
	            			month = (cal.get(Calendar.MONTH) + 1);
	            		} catch (ParseException e) {
	            			e.printStackTrace();
	            		}
	            		
	            		days[arrayLength - position ] = day;
	            		months[arrayLength - position] = month;
	            		values[arrayLength - position] = value;
	            				
	            		position++;
	            	}
	            	
	            	//fill the data into the actual output array
	            	for (int j = 0; j < arrayLength; j++) {
	            		
	            		day = days[j];
	            		month = months[j];
	            		value = values[j];
	            		
	            		horizontalLabels[j] = month + "-" + day;
	            		
	            		if (day < dayBefore)
	            			day = (dayBefore + 7);
		            	
		            	dayBefore = day;
	            		
	        			stringDate = day + "." + month;
	            		
	            		try {
	            			date = Double.parseDouble(stringDate);
	            		} catch (NumberFormatException e) {
	            			e.printStackTrace();
	            		}
	            		
	            		data[j] = new GraphViewData(date, value);
	            	}
	            	
	            	lineGraph.setTitle(stockSymbol);
	            	
	            	lineGraph.setHorizontalLabels(horizontalLabels);
	            	
	            	GraphViewSeries dataSeries = new GraphViewSeries(data);
	            	
	    			lineGraph.addSeries(dataSeries);
	            	
	            }else{
	            	Toast.makeText(getActivity(), "Daten konnten nicht geladen werden!", Toast.LENGTH_LONG).show();
	            }  
	        }
	    }
	}
}
