package lanjk;

import com.sun.jna.platform.win32.Kernel32;

public interface Kernel32P extends Kernel32 {
	
	public BOOL SetConsoleTextAttribute(HANDLE consoleOutput, short attributes);
	
}