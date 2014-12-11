package exceptions;

public class ImpossibleAttackException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public enum Reason { FRIENDLY_FIRE, NOT_REACHABLE, NO_TROOPS, NOT_ENOUGH_TROOPS };
	
	
	Reason reason;
	
	public ImpossibleAttackException(Reason reason)
	{
		this.reason = reason;
	}
	
	
	public Reason getReason()
	{
		return reason;
	}
	
	
}
