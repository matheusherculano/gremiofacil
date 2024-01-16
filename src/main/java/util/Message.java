package util;

import java.util.Locale;

import org.springframework.context.support.ResourceBundleMessageSource;

public class Message {
	
	public static String get(String chave) {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasenames("i18n/messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource.getMessage(chave, null, Locale.getDefault());
	}

}
