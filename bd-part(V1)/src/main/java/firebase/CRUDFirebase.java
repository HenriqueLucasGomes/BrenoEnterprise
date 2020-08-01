package firebase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;




public class CRUDFirebase {
	//ADD Delete Update
	
	private static Firestore bd = null;
	
	//Builder
	public CRUDFirebase(){
		ConexionFirebase  conexionfirebase= new ConexionFirebase();
		bd= conexionfirebase.iniciarFirebase();
	}
	
	//add
	public boolean addFirebase(float Ganhos, float Custos, String Setor) {
		boolean key=false;
		
		// Create a Map to store the data we want to set
		Map<String, Object> docCaixa = new HashMap<>();
		docCaixa.put("Ganhos", Ganhos);
		docCaixa.put("Custos", Custos);
		docCaixa.put("Saldo", (Ganhos - Custos));
		
		// Add a new document (asynchronously) in collection "cities" with id "LA"
		ApiFuture<WriteResult> future = bd.collection("Caixa").document(Setor).set(docCaixa);
		 
		try {
			System.out.println("Update time : " + future.get().getUpdateTime());
			key=true;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return key;
	}
	
	public Map<String, Object> getFirebase(String Coll ,String Docu) {
		
		
		DocumentReference docRef = bd.collection(Coll).document(Docu);
		// asynchronously retrieve the document
		ApiFuture<DocumentSnapshot> future = docRef.get();
		// ...
		// future.get() blocks on response
		DocumentSnapshot document=null;
		try {
			document = future.get();
			System.out.println("Document data: " + document.getData());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			System.out.println("No such document!");
			e.printStackTrace();
		} 

		
		return document.getData();
	}

}
