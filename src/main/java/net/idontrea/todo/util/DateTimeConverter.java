package net.idontrea.todo.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Converter(autoApply=true)
public class DateTimeConverter implements AttributeConverter<DateTime, String>
{

	@Override
	public String convertToDatabaseColumn(DateTime dt)
	{
		DateTimeFormatter formatter=DateTimeFormat.forPattern("MM/dd/yy");
		return formatter.print(dt);
	}

	@Override
	public DateTime convertToEntityAttribute(String s)
	{
		DateTime ret=null;
		if(s!=null)
		{
			DateTimeFormatter formatter=DateTimeFormat.forPattern("MM/dd/yy");
			ret=formatter.parseDateTime(s);
		}
		
		return ret;
	}
}