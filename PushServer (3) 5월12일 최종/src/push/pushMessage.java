package push;

public class pushMessage {
	
	private String alert = ""; // - �ؽ�Ʈ �޽���
	private String sound = ""; // - ����� ���� (��� �� ���� ���� �̸�)
	private String  badge = ""; //- �۾����ܿ� ������ ������ȣ
	private String customkey = ""; //- ���̰� ���Ǵ� �ѵ����� ���� ������ ������ �����ϴ�
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
