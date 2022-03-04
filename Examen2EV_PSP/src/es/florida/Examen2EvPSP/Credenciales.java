package es.florida.Examen2EvPSP;

import java.io.Serializable;
@SuppressWarnings("serial")
public class Credenciales implements Serializable{
	String user;
	String pass;
	
	public Credenciales() {
	}	
	public String getUser(){
		return user;
		}
	public void setUser(String user){
		this.user=user;
		}
	public String getPass(){
		return pass;
		}
	public void setPass(String pass){
		this.pass=pass;
		}
}
