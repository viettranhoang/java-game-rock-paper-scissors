package BEAN;


public class User{
	
	private String user_login,user_name,user_pass;
	private int draw,status,win,lose,score;
	
	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public User(String user_login, String user_name, String user_pass, int draw, int status, int win, int lose,int score) {
		super();
		this.user_login = user_login;
		this.user_name = user_name;
		this.user_pass = user_pass;
		this.draw = draw;
		this.status = status;
		this.win = win;
		this.lose = lose;
		this.score = score;
	}


	public User() {
		super();
	}
	
	
	public User( String user_login,String user_name, String user_pass) {
		super();
		this.user_name = user_name;
		this.user_login = user_login;
		this.user_pass = user_pass;
	}
	
	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getUser_login() {
		return user_login;
	}
	
	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}
	
	public String getUser_pass() {
		return user_pass;
	}
	
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	
	public int getDraw() {
		return draw;
	}
	
	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


	@Override
	public String toString() {
		return "-" + user_login + "-" + user_login + "-" + win + "-" + score ;
	}
	
}
