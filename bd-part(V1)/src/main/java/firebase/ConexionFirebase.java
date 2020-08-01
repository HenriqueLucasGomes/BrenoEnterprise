package firebase;

import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class ConexionFirebase {
	
	

	public Firestore iniciarFirebase() {

		//FileInputStream serviceAccount = new FileInputStream("path/to/serviceAccountKey.json");
		
		FirebaseOptions options;
		try {
			options = new FirebaseOptions.Builder()
			  .setCredentials(GoogleCredentials.fromStream(getClass().getResourceAsStream("brenoenterprisev1-firebase-adminsdk-f9gr2-0d7afbcf3e.json")))
			  .setDatabaseUrl("https://teste-5d1e7.firebaseio.com")
			  .build();
			FirebaseApp.initializeApp(options);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return FirestoreClient.getFirestore();
	}
	
}
