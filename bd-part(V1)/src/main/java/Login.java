import java.util.Map;

import firebase.CRUDFirebase;

public class Login {
	
	CRUDFirebase crudfirebase;
	Map <String, Object> InfoBD;
	
	
	public String autenticar(String Usr, String Senha, CRUDFirebase crudfirebase) {
		String rsl="2";//Erro Desconhecido
		this.crudfirebase=crudfirebase;
		InfoBD=crudfirebase.getFirebase("Logins", Usr);
				
		if(InfoBD==null) {
			return rsl="0";//Usuário Inválido
		}else if(!(InfoBD.get("Senha").toString().equals(Senha))) {
			return rsl="1";//Senha Inválida
		}else if(Usr.equals("Breno")) {
			return rsl="Eletronica";
		} else if(Usr.equals("Luiz")) {
			return rsl="Eletrica";
		}
		
		return rsl;
	}
	
}
