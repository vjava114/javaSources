package push;

public class pushMessage {
	
	private String alert = ""; // - 텍스트 메시지
	private String sound = ""; // - 재생할 사운드 (기기 내 사운드 파일 이름)
	private String  badge = ""; //- 앱아이콘에 보여줄 뱃지번호
	private String customkey = ""; //- 길이가 허용되는 한도에서 임이 데이터 전달이 가능하다
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public String getSound() {
		return sound;
	}
	public void setSound(String sound) {
		this.sound = sound;
	}
	public String getBadge() {
		return badge;
	}
	public void setBadge(String badge) {
		this.badge = badge;
	}
	public String getCustomkey() {
		return customkey;
	}
	public void setCustomkey(String customkey) {
		this.customkey = customkey;
	}
	
	
}
