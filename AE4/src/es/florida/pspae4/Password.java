package es.florida.pspae4;

import java.io.Serializable;
@SuppressWarnings("serial")
public class Password implements Serializable{
	String textoPlano;
	String encriptada;
	public Password() {
		this.textoPlano="";
		this.encriptada="";
	}	
	public String getTextoPlano(){return textoPlano;}
	public void setTextoPlano(String textoPlano){this.textoPlano=textoPlano;}
	public String getEncriptada(){return encriptada;}
	public void setEncriptada(String encriptada){this.encriptada=encriptada;}
}
