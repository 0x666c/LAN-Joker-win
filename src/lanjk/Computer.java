package lanjk;

public class Computer {
	
	private final String name;
	
	public Computer(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void shutdown() {
		try {
			Process proc = Runtime.getRuntime().exec("shutdown.exe /m \\" + name + " /r /f /t 0");
			while(proc.isAlive()); // Busy wait
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}