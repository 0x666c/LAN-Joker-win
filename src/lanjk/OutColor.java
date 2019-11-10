package lanjk;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinNT.HANDLE;

public final class OutColor {
	private static final Kernel32P K32;
	private static final HANDLE CONSOLE;
	static {
		K32 = Native.load(Kernel32P.class);
		CONSOLE = K32.GetStdHandle(Kernel32P.STD_OUTPUT_HANDLE);
	}
	
	public static boolean set(Clr bg, Clr fg) {
		return K32.SetConsoleTextAttribute(CONSOLE, (short)(bg.color << 4 | fg.color)).booleanValue();
	}
	
	public static enum Clr {
		
		BLACK(0x0), BLUE(0x1), GREEN(0x2), CYAN(0x3), RED(0x4), MAGENTA(0x5), BROWN(0x6), LIGHT_GRAY(0x7), DARK_GRAY(0x8), LIGHT_BLUE(0x9), LIGHT_GREEN(0xa), LIGHT_CYAN(0xb), LIGHT_RED(0xc), LIGHT_MAGENTA(0xd), YELLOW(0xe), WHITE(0xf);
		
		public final int color;
		
		private Clr(int color) {
			this.color = color;
		}
	}
	
}