package utils;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;

public class ReportAppender extends AppenderSkeleton {

	@Override
	protected void append(LoggingEvent event) {

		String log = getLayout().format(event);
		// log = org.apache.commons.lang.StringUtils.replace(log, "\n",
		// "</br>");
		Reporter.log(log, false);

	}

	public void close() {
		//ooooo
		if (this.closed)
			return;
		this.closed = true;

	}

	public boolean requiresLayout() {
		return true;
	}

}