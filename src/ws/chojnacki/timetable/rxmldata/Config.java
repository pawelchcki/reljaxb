package ws.chojnacki.timetable.rxmldata;

public class Config {
	private static Config instance;
	public static Config getGlobalConfig(){
		if (instance == null){
			instance = new Config();
		}
		return instance;
	}
	private String filePath = "./"; // dangerous and not portable (?) default but either way... here it is

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
