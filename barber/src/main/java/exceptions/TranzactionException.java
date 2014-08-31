package exceptions;

public class TranzactionException  extends RuntimeException{

	private static final long serialVersionUID = 8936791639632776160L;
	
	public TranzactionException(String message){
		super(message);
	}

}
