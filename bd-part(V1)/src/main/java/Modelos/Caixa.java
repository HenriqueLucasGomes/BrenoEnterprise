package Modelos;

import java.util.Map;



import firebase.CRUDFirebase;

public class Caixa {
	
	CRUDFirebase crudfirebase;
	Map <String, Object> InfoBD;
	float Ganhos;
	float Custos;
	float Saldo;
	String Setor;
	
	public Caixa(String Setor, CRUDFirebase crudfirebase) {
		this.crudfirebase=crudfirebase;
		this.Setor=Setor;
		InfoBD=crudfirebase.getFirebase("Caixa", this.Setor);
		this.Ganhos=Float.parseFloat(InfoBD.get("Ganhos").toString());
		this.Custos=Float.parseFloat(InfoBD.get("Custos").toString());
		this.Saldo=Float.parseFloat(InfoBD.get("Saldo").toString());
	}
	
	
	//GANHOS
	public void addGanhos(float Ganhos) {
		this.Ganhos=getGanhos()+Ganhos;
		setSaldo();
		crudfirebase.addFirebase(this.Ganhos,this.Custos, this.Setor);
	}
	
	public float getGanhos() {
		InfoBD=crudfirebase.getFirebase("Caixa",this.Setor);
		return this.Ganhos=Float.parseFloat(InfoBD.get("Ganhos").toString());
	}
	
	
	//CUSTOS
	public void addCustos(float Custos) {
		this.Custos=getCustos()+Custos;
		setSaldo();
		crudfirebase.addFirebase(this.Ganhos,this.Custos,this.Setor);
	}
	
	public float getCustos() {
		InfoBD=crudfirebase.getFirebase("Caixa",this.Setor);
		return this.Custos=Float.parseFloat(InfoBD.get("Custos").toString());
	}
	
	
	//SALDO
	public void setSaldo() {
		this.Saldo=this.Ganhos-this.Custos;
	}
	
	public float getSaldo() {
		InfoBD=crudfirebase.getFirebase("Caixa",this.Setor);
		return this.Saldo=Float.parseFloat(InfoBD.get("Saldo").toString());
	}
	
}
