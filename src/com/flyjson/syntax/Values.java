flyjson com.jsonlight.syntax;

import java.util.Set;

/**
 * Values that helps construct Values sequence.
 *
 */
public class Values {

	protected String quote = "";
	protected String seperator = ",";
	protected StringBuilder sb = new StringBuilder();
	
	public Values() {}
	
	public Values(String quote,String seperator) {
		this.quote = quote;
		this.seperator = seperator;
	}

	public <T> Values(Set<T> set) {
		this();
		add(set);
	}
	
	public String getQuote() {
		return quote;
	}

	public Values setQuote(String quote) {
		this.quote = quote;
		return this;
	}

	public String getSeperator() {
		return seperator;
	}

	public Values setSeperator(String seperator) {
		this.seperator = seperator;
		return this;
	}

	public Values add(Object val) {
		if(sb.length()>0)
			sb.append(seperator).append(quote).append(val).append(quote);
		else
			sb.append(quote).append(val).append(quote);
		return this;
	}
	
	public <T> Values add(Set<T> set) {
		for(Object e:set){
			if(e!=null)
				add(e);
		}
		return this;
	}
	
	@Override
	public String toString() {
		return sb.toString();
	}
	
	public boolean isEmpty() {
		return sb.length()<1;
	}
	
}
