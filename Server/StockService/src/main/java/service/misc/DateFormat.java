package service.misc;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
public class DateFormat extends SimpleDateFormat {
	
	public DateFormat() {
		super("yyyy-MM-dd");
	}
	
}
