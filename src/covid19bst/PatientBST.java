package covid19bst;

public class PatientBST {

	PatientNode root;
	int totalPayments=0;
	int patientsNumber=0;
	int patientsWithFever=0;
	
	public void addPatient(Patient newPatient) {
		if(root==null) {
			root = new PatientNode(newPatient);
			int payment = newPatient.symptoms.length * 100;
			totalPayments+=payment;
			patientsNumber++;
			
			for(int k=0; k<newPatient.symptoms.length; k++) {
			if (newPatient.symptoms[k].equals("fever")) {
				patientsWithFever++;
			}
			}
			System.out.println("Patient " + newPatient.name + " was added to the system.");
			System.out.println("Payment: " + payment );	
			return;
		}
		addPatient(root, newPatient);
	}// end add patient 
	
	public void addPatient(PatientNode node, Patient newPatient) {
		if(node == null) {
			return;
		}
		
		if(newPatient.id>node.patient.id) {
			if(node.right==null) {
				node.right= new PatientNode(newPatient);
				int payment = newPatient.symptoms.length * 100;
				totalPayments+=payment;
				patientsNumber++;
				
				for (int a=0; a<newPatient.symptoms.length; a++) {
					if (newPatient.symptoms[a].equals("fever")) {
						patientsWithFever++;
					}
				}
				
				
				System.out.println("Patient " + newPatient.name + " was added to the system.");
				System.out.println("Payment: " + payment );
				
			}
			else {//climbing by the right 
				addPatient(node.right, newPatient);
			}
		}
		else {// if newPatient.id<node.patient.id
			if(node.left==null) {
				node.left=new PatientNode(newPatient);
				int payment = newPatient.symptoms.length * 100;
				totalPayments+=payment;
				patientsNumber++;
				
				for (int b=0; b<newPatient.symptoms.length; b++) {
				if (newPatient.symptoms[b].equals("fever")) {
					patientsWithFever++;
				}
				}
				
				System.out.println("Patient " + newPatient.name + " was added to the system.");
				System.out.println("Payment: " + payment );
			}
			else {//Climbing by the left 
				addPatient(root.left, newPatient);
			}
		}
	}// end add Patient 2
	
	void print() {
		print(root);
	}
	
	void print(PatientNode node) {
		
		if(node != null) {
			System.out.println(node.patient.name);
			print(node.left);
			print(node.right);
		}
	}
	
	public int average() {
		int av = totalPayments/patientsNumber;
		System.out.println("totalPayments: " + totalPayments);
		System.out.println("patientsNumber: " + patientsNumber);
		System.out.println("Average: " + av);
		return av;
	}
	
	public Patient search(int id) {
		return search(root, id);
	}
	
	private Patient search(PatientNode node, int id) {
		if(node == null) {
			return null;
		}
		if(node.patient.id==id) {
			System.out.print("Patient found: " + node.patient.name + " has " + node.patient.symptoms.length + " symptons: " );
			for(int i=0; i<node.patient.symptoms.length; i++) {
			System.out.print(" " + node.patient.symptoms[i]);
			}
			System.out.println();
			return node.patient;
		}
		if (node.patient.id<id) {
			return search(node.right, id);
		}
		return search(node.left, id);
	}
	
	
	
// verification methods by left and right 
	
	public Patient verificationRight(int id) {
		return verificationRight(root, id);
	}
	
	private Patient verificationRight(PatientNode node, int id) {
		if(node == null) {
			return null;
		}
		if(node.patient.id==id) {
			System.out.println("Patient already exist" );
			return node.patient;
		}
		else {
			return verificationRight(node.right, id);
		}
	}

	public Patient verificationLeft(int id) {
		return verificationLeft(root, id);
	}
	
	private Patient verificationLeft(PatientNode node, int id) {
		if(node == null) {
			return null;
		}
		if(node.patient.id==id) {
			System.out.println("Patient already exist" );
			return node.patient;
		}
		else {
			return verificationRight(node.left, id);
		}
	}

	public void hasFever() {
		System.out.println(patientsWithFever + " Patients have fever");
	}


	
	

	public Patient mostSymptoms(PatientNode node) {
		if(node == null) {
			return null;
		}
		
		
		Patient mostSymptomsPatienleft=null;		
		if(node.left != null) {
			mostSymptomsPatienleft = mostSymptoms(node.left);
		}
		
		
		Patient mostSymptomsPatienRight=null;
		if(node.right != null) {
			mostSymptomsPatienRight = mostSymptoms(node.right);
		}
	
		//comparating 3 variables
		Patient theBiggestOne = node.patient;
		if(mostSymptomsPatienleft != null) {
			if(theBiggestOne.symptoms.length <= mostSymptomsPatienleft.symptoms.length) {
				theBiggestOne = mostSymptomsPatienleft;
			}
		}
		if(mostSymptomsPatienRight != null) {
			if(mostSymptomsPatienRight.symptoms.length >= theBiggestOne.symptoms.length) {
				theBiggestOne = mostSymptomsPatienRight;
			}
		}
		
		return theBiggestOne;
	}
	
}//end main 
