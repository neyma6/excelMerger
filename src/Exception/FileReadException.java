package Exception;

import java.io.IOException;

import View.Labels;

public class FileReadException extends IOException 
{
	private String path;

	public FileReadException(String path) {
		super();
		this.path = path;
	}

	@Override
	public String getMessage() 
	{
		return Labels.EXCEPTION_CANNOT_READ + path;
	}

}
