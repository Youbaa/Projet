package lesclasses;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Ngrammes.Ngramme;
import PakLongeurClé.Coincidence;


public class lesFonctionalites {


	/////////////////////////////////..........premiere fonction.............///////////////////////////
	//cette fonction Recherche les lignes différentes entre deux fichiers et elle retourne 
	//Les numéros des lignes et les lignes qui sont présentent dans le fichier1 mais absentent dans le fichier2 
	//2) Les numéros des lignes et les lignes commune au fichier1 et au fichier 2 .


	public static void LigneCompar(String[] args) throws IOException{
		// Files need to be compared
		String fileName1 = "C:/Users/YOUNES/Desktop/site mimi/eclipse/tous-les-classe/src/lesclasses/TEXTE1.txt";
		String fileName2 = "C:/Users/YOUNES/Desktop/site mimi/eclipse/tous-les-classe/src/lesclasses/TEXTE2.txt";
		// ArrayList to store each line for a given file
		List<String> List1;
		List<String> List2;        

		List1 = fileToLines(fileName1);
		List2 = fileToLines(fileName2);        

		// How many lines in each file
		int l1 = List1.size(); 
		int l2 = List2.size();

		// lmin = min(l1 , l2)
		int lmin = l1 >= l2 ? l2 : l1; 
		int lmax = l1 >= l2 ? l1 : l2; 

		int[] result = new int[lmax];
		String str1;
		String str2;       

		for(int i = 0 ; i < lmin; i++){
			str1 = List1.get(i);
			str2 = List2.get(i);
			// Printing each line of the given file
			System.out.println(str1); 
			System.out.println(str2);
			// Comparaing line by line
			if(str1.equals(str2)){                
				result[i] = 1;                
			}
			else{
				result[i] = 0;                
			}
		}
		Scanner input  =new Scanner(System.in);
		System.out.println("************************************");
		System.out.println("Please enter your choice: ");
		System.out.println("1: Lines in file1 but not in file2: ");
		System.out.println("2: Lines in file1 and in file2: ");
		System.out.println("*************** Résultat de la fonction comparaison des lignes*********************");
		int choice = input.nextInt();
		switch(choice){
		case 1: differentLines(List1 , List2);
		break;
		case 2: sameLines(List1, List2);
		break;
		default:
			System.out.println("Wrong choice");
		}

	}
	// présentes dans le fichier 1 mais absentes du fichier 2
	public static void differentLines(List<String> List1 , List<String> List2) throws IOException{
		// How many lines in each file
		int l1 = List1.size(); 
		int l2 = List2.size();
		PrintWriter sortie;
		String Sortie1="C:/Users/YOUNES/Desktop/site mimi/eclipse/tous-les-classe/src/lesclasses/Sortie1.txt";

		FileOutputStream fos = new FileOutputStream(Sortie1);
		OutputStreamWriter osw = new OutputStreamWriter(fos); 		

		// lmin = min(l1 , l2)
		int lmin = l1 >= l2 ? l2 : l1; 
		int lmax = l1 >= l2 ? l1 : l2; 
		for(int i = 0; i < l1; i++ ){
			int k = 0;
			for(int j = 0; j < l2; j++){
				if(List1.get(i).equals(List2.get(j))){
					k++;
				}               
			}
			if(k == 0){
				osw.write("The line " + i+ " is in file1 but not in file2 ");
				System.out.printf("The line %d is in file1 but not in file2", i);                
				System.out.println();
			}
		}  
		osw.close();
	}

	public static void sameLines(List<String> List1 , List<String> List2) throws IOException{
		// How many lines in each file
		int l1 = List1.size(); 
		int l2 = List2.size();

		String Sortie2="C:/Users/YOUNES/Desktop/site mimi/eclipse/tous-les-classe/src/lesclasses/Sortie2.txt";

		FileOutputStream fos = new FileOutputStream(Sortie2);
		OutputStreamWriter osw = new OutputStreamWriter(fos);         

		// lmin = min(l1 , l2)
		int lmin = l1 >= l2 ? l2 : l1; 
		int lmax = l1 >= l2 ? l1 : l2; 
		for(int i = 0; i < l1; i++ ){
			for(int j = 0; j < l2; j++){
				if(List1.get(i).equals(List2.get(j))){
					osw.write("The lines "+i+" and "+j+" are the same: "+List1.get(i)+"\n");
					System.out.printf("The lines %d and %d are the same: %s", i, j, List1.get(i));
					System.out.println();
				}
			}
		}  
		osw.close();
	}


	// Method that reads a file, and stores each line in an arraylist of Strings
	public static List<String> fileToLines(String file) throws FileNotFoundException, IOException{
		BufferedReader br = new BufferedReader(new FileReader(file));
		List<String> Lines;
		Lines = new ArrayList<>();

		try {  

			String line = br.readLine();

			while (line != null) {  

				if(line.length() != 0){ 
					Lines.add(line);
				}
				line = br.readLine();


			}        
		} finally {
			br.close();
		}
		return Lines; 
	}  


	////////////////////////......2em fonction..///////////////////////////////////////
	/* Cette fonction permet de savoir si un texte 
 français a été chiffré avec un chiffre mono-alphabétique ou un  chiffre poly-alphabétique en étudiant la probabilité de répétition des lettres du message
 chiffré .Avec un IC aussi proche du français(0,0778 ), on peut faire l’hypothèse que ce 
cryptogramme est le résultat d’un chiffrement mono-alphabétique.Avec l’IC aussi différent du français (0.0778), on 
peut déduire que le chiffre utilisé est poly-alphabétique. 
	 */

	public static float CoincidenceFinal(String file1) throws IOException
	{
		int i=0;
		int j=0;
		int c1;
		int c2;
		int Sortir = 0;
		int max=0;
		int Idx = 0;
		int n=0;

		ArrayList Tab1 = new ArrayList();//créer un tableau (dynamique) pour stocker les fréquences de chaque caractère
		ArrayList Tab2 = new ArrayList();//créer un tableau (dynamique) pour stocker les caractères présents dans le texte



		while (j<file1.length())// parcourir tous les caractères du texte 'file1'
		{   
	        // normaliser le caractère 'j' du texte 'file1' (ie. enlever les accents si le caractère est accentué)
			c2=(int)file1.charAt(j);
			i=0;			
			char kk;
			String nom ="";
			kk=(char)c2;
			String mot=Character.toString(kk);		
			nom=Normalizer.normalize(mot, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toUpperCase();
			kk=nom.charAt(0);
			c2=(int)kk;
			   // -------------Fin de la normalisation  du caractère 'j'----------------------------


			Sortir=0;//créer une variable binaire (Sortir =1 implique on sort de la boucle et opn passe au caractère suivant) pour 
	        /* 1) : éliminer les caractères non alpha-numériques 
	         * 2) : ou éviter d'éffectuer des calculs inutiles en cas où le caractère est déjà stocké dans 'Tab2'
	         */



			while(i<Tab1.size() && Sortir==0)//on parcout 'Tab1' pour vérifier si le caractère 'j' du texte 'file1' est déjà stocké ou non
			{
				if (Tab1.size()>0)// tester s'il y a au moins un caractère stocké dans 'Tab1' : si 'non', on sort de la boucle.
				{
					if (c2==32 || (c2<65) || (c2>90)) // on vérifie si le caractère 'j' du texte 'file1' est alpha-numérique: si 'non', on sort de la boucle 
					{

						Sortir=1;
					}
					else
					{						

						if((Integer)Tab2.get(i)==c2)// On teste si le caractère 'j' du texte 'file1' est présent déjà stocké 
						{
							Tab1.set(i, (int)Tab1.get(i)+1);// si oui (le caractère 'j' du texte 'file1' est présent déjà stocké), on incrémente sa fréquence dans 'Tab1' et on sort de la boucle (Sortir prends la valeur 1)
							if (max<(int)Tab1.get(i))// cette boucle permet juste de calculer le caractère le plus fréquent (elle peut ettre supprimer)
							{
								max = (int)Tab1.get(i);
								Idx = i;
							}
							Sortir = 1;
						}

					}

				}

				i=i+1;

			}

			if (Sortir==0)// Si le caractère  'j' du texte 'file1' n'est stocké dans 'Tab2' (Sortie =0, cela veut dire que c'est un caractère alpha-numérique et en plus non présent dans 'Tab2')
			{
				Tab1.add(1);//Si oui, ajouter le caractère 'j' du texte 'file1' dans 'Tab2' et affecter sa fréquence à '1' dans 'Tab1'
				Tab2.add(c2);
			}
			j=j+1;

		}



		float somme=0;// créer une variable qui contiendra la sommes de toutes les fréquences des caractère alpha-numériques du texte 'file1'
		ArrayList Tab = new ArrayList(); // créer un tableau qui contiendra les caractères en "unicode" du tableau Tab1 (Tab1 est en ascii). Peut être supprimer

		for (i=0;i<Tab1.size();i++)// Parcourir toutes les fréquences des lettre pour : 1) calculer la somme de ces dernière et 2) transformer Tab1 "unicode"
		{
			StringBuffer car = new StringBuffer();//sert à transformer Tab1 en "unicode"
			char k1;//sert à transformer Tab1 en "unicode"
			int i1;//sert à transformer Tab1 en "unicode"			

			somme=somme+(int)Tab1.get(i)*((int)Tab1.get(i)-1);// mettre à jours la somme des fréquences
			n=n+(int)Tab1.get(i);// mettre à jours le nombre de caractères alpha-numériiques présents dans le texte 'file1'

			i1=(Integer)Tab2.get(i);//sert à transformer Tab1 en "unicode"

			k1=(char)i1;//sert à transformer Tab1 en "unicode"
			car.append(k1);
			Tab.add(car);// ajouter le caractère "unicode" à 'Tab'

		}
		StringBuffer car = new StringBuffer();//sert à Transformer le caractère le plus fréquent dans le texte en 'unicode'
		float IC = (float) somme/(n*(n-1));//calculer l'indice de coincidence (qui vaut la somme des fréquences des caractère alpha-numériques / (n*(n-1)))
		char k1;//sert à Transformer le caractère le plus fréquent dans le texte en 'unicode'
		char k2;
		int i1;//sert à Transformer le caractère le plus fréquent dans le texte en 'unicode'
		int i2;

		i1=(Integer)Tab2.get(Idx);//sert à Transformer le caractère le plus fréquent dans le texte en 'unicode'

		k1=(char)i1;//sert à Transformer le caractère le plus fréquent dans le texte en 'unicode'

		car.append(k1);//sert à Transformer le caractère le plus fréquent dans le texte en 'unicode'


		ArrayList Tab4 = new ArrayList();// créer une variable pour contenir tous les résultats à renvoyer (peut être supprimer car on renvoie que IC)

		Tab4.add(IC);
		Tab4.add(Idx);
		Tab4.add(Tab1);
		Tab4.add(Tab);


		return IC;
	}


	///////////3em fonction//////////////////////

	/*cette fonction calcul La longueur de la clé probable avec l’indice de coïncidence (casser le chiffrement de Vigenère)pour un texte ecrit en francais     */




	public static ArrayList LongeurC(String file1, float ICf) throws IOException
	{
		int i=0;
		int j=0;
		int p=0;
		int c1;
		int c2;
		int Sortir = 0;
		int max=0;
		int Idx = 0;
		int n=0;
		float somme=0;

		ArrayList Tab1 = new ArrayList();
		ArrayList MoyIC = new ArrayList();
		ArrayList EcartType = new ArrayList();


		Tab1.add(Coincidence.CoincidenceFinal(file1));
		MoyIC.add((float)Tab1.get(0));
		EcartType.add((float)0);
		i=1;
		Idx=0;

		while (i<20)
		{
			j=0;
			ArrayList Tab2 = new ArrayList();
			n=0;
			somme=0;
			while (j<=i)
			{				
				String Texte="";
				p=j;
				while (p<file1.length())
				{
					Texte=Texte+file1.charAt(p);
					p=p+i+1;

				}
				//System.out.println(Texte);
				float IC = Coincidence.CoincidenceFinal(Texte);

				Tab2.add(IC);
				somme = somme + IC;
				n=n+1;
				j=j+1;
			}
			Tab1.add(Tab2);
			MoyIC.add((float) somme/n);
			p=0;
			float EcartT = 0;
			n=Tab2.size();			
			while (p<n)
			{

				EcartT=EcartT+ (float)Math.pow(((float)Tab2.get(p)-(float)MoyIC.get(i-1)), 2);
				p=p+1;
			}	
			EcartType.add((float)Math.sqrt(EcartT/(n-1)));


			if (Math.abs((float)MoyIC.get(Idx)-(float)ICf)>Math.abs((float)MoyIC.get(i)-(float)ICf) && i<=10)
			{
				Idx=i;
			}
			i=i+1;
		}
		//System.out.println(Tab1);



		ArrayList Tab4 = new ArrayList();


		Tab4.add(Tab1);
		Tab4.add(MoyIC);
		Tab4.add(EcartType);
		Tab4.add(Idx+1);


		return Tab4;
	}	



	/////////////////4em fonction /////////////////////////
	/*cette fonction  effectue l'analyse des fréquences des longueurs des mots pour un texte donné*/

	public static ArrayList LongeurMots(String file1) throws IOException
	{
		int i=0;
		int j=0;
		int p=0;
		int c1;
		int c2;
		int Sortir = 0;
		int max=0;
		int Idx = 0;
		int n=0;

		ArrayList Tab1 = new ArrayList();
		ArrayList Tab2 = new ArrayList();

		FileInputStream fis1 = new FileInputStream(file1);//ouvrir le fichier
		InputStreamReader isr1 = new InputStreamReader(fis1);

		String Texte_Etudie="";
		while ((c2 = isr1.read()) > -1)//tant que le fichier n'est pas vide 
		{
			char kk;
			String nom ="";
			kk=(char)c2;
			String mot=Character.toString(kk);		
			nom=Normalizer.normalize(mot, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toUpperCase();
			kk=nom.charAt(0);
			c2=(int)kk;

			Texte_Etudie=Texte_Etudie+(char)c2;//recuperer les caractères 


		}			

		String LongeurMot = file1.substring(0, file1.length()-5)+"_Longeur-Mot.txt";
		FileOutputStream fos = new FileOutputStream(LongeurMot);
		OutputStreamWriter osw = new OutputStreamWriter(fos); 		

		int Trouv = 0;
		int Trouv1 = 0;

		i=0;

		while (i<Texte_Etudie.length())
		{	
			j=0;
			Sortir=0;
			String car="";
			Trouv1=0;



			while (Texte_Etudie.charAt(i)>32 && Sortir==0)
			{
				Trouv1=1;
				j=j+1;

				car=car+Texte_Etudie.charAt(i);
				if (i<Texte_Etudie.length()-1)
				{
					i=i+1;
				}
				else
				{
					Sortir = 1;
				}
			}

			p=0;
			Trouv=0;
			while (p<Tab1.size() && Trouv==0)
			{
				if ((int)Tab2.get(p)==j)
				{
					Trouv=1;
					Tab1.set(p, (int)Tab1.get(p)+1);
				}
				p=p+1;
			}

			if (Trouv==0 && Trouv1==1)
			{
				Tab1.add(1);
				Tab2.add(j);
			}	

			i=i+1;
		}

		i=0;
		while (i<Tab1.size())
		{
			osw.write("Mots de longeur "+(int)Tab1.get(i)+" : "+(int)Tab2.get(i)+"\n");
			i=i+1;
		}
		osw.close();

		ArrayList Tab4 = new ArrayList();

		Tab4.add(Tab1);
		Tab4.add(Tab2);


		return Tab4;
	}	



	//// la 5ème fonction : les N-grammes ////
	/*
	 * Cette fonction calcule  les fréquences des n-grammes (n=1 ... infini) présents dans le texte étudié.
	 */

	public static ArrayList N_Grammes(String file1,int Glissant, int Type_Lettre,int Longeur) throws IOException
	{
		int i=0;
		int j=0;
		int p=0;
		int c1;
		int c2;
		int Sortir = 0;
		int max=0;
		int Idx = 0;
		int n=0;

		ArrayList Tab1 = new ArrayList();
		ArrayList Tab2 = new ArrayList();
		ArrayList Tab_Caract = new ArrayList();

		FileInputStream fis1 = new FileInputStream(file1);
		//FileInputStream fis2 = new FileInputStream(file2);

		InputStreamReader isr1 = new InputStreamReader(fis1);
		//InputStreamReader isr2 = new InputStreamReader(fis2);

		while ((c2 = isr1.read()) > -1)
		{
			char kk;
			String nom ="";
			kk=(char)c2;
			String mot=Character.toString(kk);		
			nom=Normalizer.normalize(mot, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toUpperCase();
			kk=nom.charAt(0);
			c2=(int)kk;

			if ((c2>=65) && (c2<=90) && ((Type_Lettre==1)||(Type_Lettre==3)))
			{				
				//System.out.println(c2);
				Tab_Caract.add((char)c2);
			}
			else if (((c2>=48) && (c2<=57) && ((Type_Lettre==2)||(Type_Lettre==3))))
			{
				Tab_Caract.add((char)c2);
			}
			else if (Type_Lettre==4)
			{
				Tab_Caract.add((char)c2);
			}			

		}
		int N=Longeur;

		int Trouv = 0;
		int Trouv1 = 0;
		String N_Lettres = "";

		i=0;

		while (i<Tab_Caract.size()-N+1)
		{			
			j=0;
			Trouv = 0;
			while(j<Tab1.size() && Trouv==0)
			{
				p=0;
				//Trouv1=0;
				N_Lettres = "";
				Trouv1 = 0;
				while(p<N && Trouv1==0)
				{
					if ((char)Tab_Caract.get(i+p)!=Tab2.get(j).toString().charAt(p))
					{
						Trouv1=1;
					}
					N_Lettres = N_Lettres+(char)Tab_Caract.get(i+p);
					p=p+1;
				}			


				if (Trouv1==0)
				{								
					Tab1.set(j, (int)Tab1.get(j)+1);
					if (max<(int)Tab1.get(j))
					{
						max = (int)Tab1.get(j);
						Idx = j;
					}
					Trouv = 1;				
				}
				j=j+1;
			}

			if (Trouv==0)
			{
				N_Lettres = "";
				p=0;
				while(p<N)
				{
					N_Lettres = N_Lettres+(char)Tab_Caract.get(i+p);
					p=p+1;
				}					
				Tab1.add(1);
				Tab2.add(N_Lettres);
			}			
			if (Glissant==0)
			{
				i=i+1;
			}
			i=i+1;
		}

		//System.out.println("----deb affichage----");

		float somme=0;
		ArrayList Tab = new ArrayList();
		String SortieGramme=file1.substring(0, file1.length()-4)+"_N-Grammes.txt";

		FileOutputStream fos = new FileOutputStream(SortieGramme);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		for (i=0;i<Tab1.size();i++)
		{
			StringBuffer car = new StringBuffer();
			char k1;
			int i1;			
			osw.write(Tab2.get(i)+" : "+Tab1.get(i)+"\n");
			somme=somme+(int)Tab1.get(i)*((int)Tab1.get(i)-1);

		}
		osw.write("La plus Fréquente est la lettre "+Tab2.get(Idx)+" : "+Tab1.get(Idx));
		osw.close();
		char k1;
		char k2;
		int i1;
		int i2;



		ArrayList Tab4 = new ArrayList();

		Tab4.add(Tab2.get(Idx));
		Tab4.add(Tab1.get(Idx));
		Tab4.add(Tab1);
		Tab4.add(Tab2);


		return Tab4;
	}	



	///programme main

	public static void main(String[] args) throws IOException {
		int choice1=0;
		while (choice1!=6)	    		
		{	    	
			Scanner choix_f  =new Scanner(System.in);
			System.out.println("************************************");
			System.out.println("Please enter your choice: ");
			System.out.println("1: Comparaison des lignes des deux fichiers: ");
			System.out.println("2: L'indice de coincidence: ");
			System.out.println("3: Longueur de la clé probable ^en utilisant IC^ ");
			System.out.println("4: Longueur de mots: ");
			System.out.println("5: N-grammes: ");
			System.out.println("6: Exit: ");
			System.out.println("************************************");
			choice1 = choix_f.nextInt();
			switch(choice1){
			case 1: 
			{
				LigneCompar(args);//appele a la fonction qui compare les lignes des deux fichiers
				System.out.println("Entrez un chiffre puis Appyez sur la touche 'entrer' pour revenir au menu ....");
				Scanner choix_f1  =new Scanner(System.in);	
				choice1 = choix_f.nextInt();
				break;
			}	               
			case 2: 
			{ System.out.println("**************Résultat de la fonction  coincidence**********************");
			//appel a la fonction de coincidence elle prend en parametre un texte recuperer dans un fichier///
			String fichier = "C:/Users/YOUNES/Desktop/site mimi/eclipse/tous-les-classe/src/lesclasses/test_indice.txt";
			FileInputStream fis1 = new FileInputStream(fichier);//ouvrir le fichier
			InputStreamReader isr1 = new InputStreamReader(fis1);			    

			int c2;
			String Texte_Etudie="";
			while ((c2 = isr1.read()) > -1)//tant que le fichier n'est pas vide 
			{
				Texte_Etudie=Texte_Etudie+(char)c2;//recuperer les caractères 
			}
			isr1.close();
			float fichiersIdentiques = CoincidenceFinal(Texte_Etudie);
			System.out.println("L'indice de coincidence de texte est : "+(fichiersIdentiques));
			System.out.println("Entrez un chiffre puis Appyez sur la touche 'entrer' pour revenir au menu ....");
			Scanner choix_f1  =new Scanner(System.in);	
			choice1 = choix_f.nextInt();

			break;
			}	 
			case 3: 


			{ System.out.println("*************Résultat de la fonction  la longeur de la clé***********************");

			////appel a la fonction qui calcule la longeur de la clé. 
			/* Cette méthode prend comme paramètres: 
			 * 1) un texte (récupéré dans un fichier) 
			 * 2) l'indice de coincidence de la langue étudiée*////


			String fichier1 = "C:/Users/YOUNES/Desktop/site mimi/eclipse/tous-les-classe/src/lesclasses/testLongeurClé.txt";
			FileInputStream fis1 = new FileInputStream(fichier1);//ouvrir le fichier
			InputStreamReader isr1 = new InputStreamReader(fis1);

			String Texte_Etudie="";
			int c2;
			while ((c2 = isr1.read()) > -1)//tant que le fichier n'est pas vide 
			{
				if (c2!=32)
				{
					Texte_Etudie=Texte_Etudie+(char)c2;//recuperer les caractères 
				}			
			}
			isr1.close();

			float If=(float) 0.0778;

			ArrayList fichiersIdentiques1 = LongeurC(Texte_Etudie,If);


			ArrayList Tab = new ArrayList();
			Tab=(ArrayList)fichiersIdentiques1.get(1);
			System.out.println("Les moyennes de IC de chaque longeur de clé sont : \n"+Tab);

			System.out.println("La longeur de la clé probable est : "+fichiersIdentiques1.get(3));

			System.out.println("Entrez un chiffre puis Appyez sur la touche 'entrer' pour revenir au menu ....");
			Scanner choix_f1  =new Scanner(System.in);	
			choice1 = choix_f.nextInt();				

			break;
			}	




			case 4: 
			{ System.out.println("******************Résultat de la fonction longeur de mots******************");
			////////appel a la fonction "longeur de mots" qui donne la distribution des longeurs des mots présents dans le texte.
			/* Cette fonction prend comme paramètres : Le chemin du fichier à étudier   */



			String fichier = "C:/Users/YOUNES/Desktop/site mimi/eclipse/tous-les-classe/src/lesclasses/textelo.txt";

			ArrayList Longeurs = LongeurMots(fichier);


			System.out.println("Les Longeurs des mots sont: \n"+Longeurs.get(1));
			System.out.println("Les Fréquences associees sont: \n"+Longeurs.get(0));

			System.out.println("Entrez un chiffre puis Appyez sur la touche 'entrer' pour revenir au menu ....");
			Scanner choix_f1  =new Scanner(System.in);	
			choice1 = choix_f.nextInt();

			break;
			}




			case 5: 
			{ System.out.println("***************Résultat de la fonction n-grammes*********************");
			//// Appel à la fonction n-grammes /////
			/*
			 * Cette fonction prend comme paramètre : 
			 * 1) Le chemin du texte à étudier (fichier dans l'exemple en bas)
			 * 2) Un entier (entre 0 ou 1) qui indique si l'analyse est glissante ou pas (Glissant dans l'exemple en bas)
			 * 3) Un entier (entre 1 et 4) qui indique le type des lettres étudiées (Type_L dans l'exemple en bas)
			 * 4) Un entier (entre 1 et infini) qui indique la taille des grammes (Nombre_L dans l'exemple en bas)
			 */


			int Glissant = 1;//1 si l'analyse est glissante 0 sinon 
			int Type_L = 1;//Type_L vaut 1 si l'analyse est effectuée que sur les lettres, 
			/* 2 : si l'analyse est effectuée que sur les chiffres, 
			 * 3 : si l'analyse est effectuée sur les chiffres et les lettres et enfin, 
			 * 4 :si l'analyse est effectuée sur tous les caractères.
			 */

			int Nombre_L = 4;// Equivalent à n : 
			/* 1: 1-gramme
			 * 2: 2-grammes
			 * ect
			 */

			String fichier = "C:/Users/YOUNES/Desktop/site mimi/eclipse/tous-les-classe/src/lesclasses/testeGram.txt";


			ArrayList Ngramme =N_Grammes(fichier,Glissant,Type_L,Nombre_L);
			System.out.println("Le "+Nombre_L+"-grammes le + fréquents dans le texte est : "+(Ngramme.get(0)));
			System.out.println("La fréquence du "+Nombre_L+"-grammes le + fréquent dans le texte est: "+(Ngramme.get(1)));
			System.out.println("Les fréquences de tous les "+Nombre_L+"-grammes dans le texte sont: "+(Ngramme.get(2)));
			System.out.println("Les "+Nombre_L+"-grammes présents dans le texte sont: "+(Ngramme.get(3)));	

			System.out.println("Entrez un chiffre puis Appyez sur la touche 'entrer' pour revenir au menu ....");
			Scanner choix_f1  =new Scanner(System.in);	
			choice1 = choix_f.nextInt();				

			break;
			}




			case 6: 
			{
				System.out.println("----------------- FIN du PROGRAMME ------------------");
				break;
			}		            
			default:
				System.out.println("Wrong choice");
			}	
		}

}

}

