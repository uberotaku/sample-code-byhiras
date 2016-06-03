package byhiras.main.java.service;

public class ServiceException extends RuntimeException
{
	public ServiceException( String message )
	{
		super( message );
	}

	public ServiceException( String message, Throwable cause )
	{
		super( message, cause );
	}
}
