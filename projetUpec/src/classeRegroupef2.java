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
import java.util.Map;
import java.util.Scanner;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
public class classeRegroupef2 {

    /////////////////////////////////..........premiere fonction.............///////////////////////////
	/**.
*/
	/**
	 * la fonctionnalité prend en entrée les deux fichiers a comparer et elle retourne un entier 1 ou 2 qui correspondant 
	 * au choix effectué ( calculer les lignes differentes ou les lignes qui sont en meme temps dans les deux fichiers )
	 * @param fileName1 fichier d'entrée
	 * @param fileName2 fichier d'entrée
	 * @return un entier soit 1 ou 2 pour faire le choix d'executer la methode qui calcule les lignes differentes ou celle qui calcul les lignes en commun entre les deux fichiers
	 * @see differentLines
         * @see sameLines
	 
	 */
        public static int LigneCompar(String fileName1, String fileName2, int w) throws IOException{
		// Files need to be compared
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

	
//		Scanner input  =new Scanner(System.in);
//		System.out.println("************************************");
//		System.out.println("Please enter your choice: ");
//		System.out.println("1: Lines in file1 but not in file2: ");
//		System.out.println("2: Lines in file1 and in file2: ");
		System.out.println("*************** Résultat de la fonction comparaison des lignes*********************");
		int choice = w;
		switch(choice){
		case 1: {
			OutputStreamWriter a=differentLines(List1 , List2);	
			a.close();
			return 1;
		}
		//break;
		case 2: {
			OutputStreamWriter a=sameLines(List1, List2);
			a.close();
			return 2;
		}
		//break;
		default:
			System.out.println("Wrong choice");
			return 0;
		}

	}
	/**la fonctionnalité Recherche les lignes différentes entre deux fichiers et elle retourne 
	*Les numéros des lignes et les lignes qui sont présentes dans le fichier1 mais absentes dans le fichier2 
	*elle prend en entrée deux listes des lignes de fichier1 et fichier2 respectivement  et on aura en sortie un 
        *fichier dans lequel on trouve les lignes et les numéros des lignes qui sont différentes

	 * 
	 * @param List1 la liste des lignes de prmier fichier
	 * @param List2 la liste des ligne de 2em fichier 
	 * @return  un fichier dans le quel on trouve les lignes qui sont different
	 * @throws IOException
	 */
	public static OutputStreamWriter differentLines(List<String> List1 , List<String> List2) throws IOException{  // How many lines in each file
        int l1 = List1.size(); 
        int l2 = List2.size();
        
        
        File ff=new File("sortie_differentLines.txt");    
        String  sortie =ff.getAbsolutePath();
    
        FileOutputStream fos = new FileOutputStream(sortie);
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
   
                principale.resultat.append("The line " + i+ " is in file1 but not in file2 ");                
                System.out.println();
            }
        }  
        osw.close();
        return osw;
    }
        /**
* la fonctionnalité Recherche les lignes  qui sont au même temps dans le fichier 1 et le fichier2 elle
 *  prend en entrée la liste des lignes de premier fichier et la liste des lignes de 2eme fichier on aura en sortie un fichier
 *   dans lequel on trouve 
 *  les lignes (avec leurs numéros) qui sont au même temps dans les deux fichiers

 *  
 * @param List1 la liste des lignes de prmier fichier
 * @param List2 la liste des lignes de 2eme fichier
 * @return un fichier dans le quel on trouve les lignes qui sont commun
 * @throws IOException
 */
	public static OutputStreamWriter sameLines(List<String> List1 , List<String> List2) throws IOException{
		// How many lines in each file
		int l1 = List1.size(); 
		int l2 = List2.size();

		File ff=new File("sortie_sameLines.txt");    
                String  sortie =ff.getAbsolutePath();
		FileOutputStream fos = new FileOutputStream(sortie);
		OutputStreamWriter osw = new OutputStreamWriter(fos);         

		// lmin = min(l1 , l2)
		int lmin = l1 >= l2 ? l2 : l1; 
		int lmax = l1 >= l2 ? l1 : l2; 
		for(int i = 0; i < l1; i++ ){
			for(int j = 0; j < l2; j++){
				if(List1.get(i).equals(List2.get(j))){
					osw.write("The lines "+i+" and "+j+" are the same: "+List1.get(i)+"\n");
					
                                        principale.resultat.append("The lines "+i+" and "+j+" are the same: "+List1.get(i)+"\n");
					System.out.println();
				}
			}
		}  
		osw.close();
		return osw;

	}
	/**Méthode qui prend en paramètre un fichier et on aura en sortie une liste des chaines de caractères(les lignes)
	 * 
	 * @param file le fichier en entrée
	 * @throws FileNotFoundException
	 * @throws IOException
	 * return lines:une liste des chainnes de caract�re (les lignes des fichier )
	 */
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
	/**la fonctionnalité consiste à calculer l'indice de coÏncidence d'un message à décrypter.
*Si ce message est codé à l'aide d'une substitution monoalphabétique, cet indice doit être voisin de
* l'indice de coÏncidence des textes de la langue utilisée (0,074 pour le français),
* Si, par contre, l'indice de coÏncidence s'éloigne de celui de la langue utilisée,
* il est plus vraisemblable qu'il s'agisse d'un cryptage de type polyalphab�tique.. elle prend en entrée un fichier
*  et aura en sortie un float qui est bien IC

 * @param file1 fichier donné en entrée
 * @return un float qui correspond a l'indice de co�ncidences IC
 * @throws IOException
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

		ArrayList Tab1 = new ArrayList();//cr�er un tableau (dynamique) pour stocker les fr�quences de chaque caract�re
		ArrayList Tab2 = new ArrayList();//cr�er un tableau (dynamique) pour stocker les caract�res pr�sents dans le texte



		while (j<file1.length())// parcourir tous les caract�res du texte 'file1'
		{   
	        // normaliser le caract�re 'j' du texte 'file1' (ie. enlever les accents si le caract�re est accentu�)
			c2=(int)file1.charAt(j);
			i=0;			
			char kk;
			String nom ="";
			kk=(char)c2;
			String mot=Character.toString(kk);		
			nom=Normalizer.normalize(mot, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toUpperCase();
			kk=nom.charAt(0);
			c2=(int)kk;
			   // -------------Fin de la normalisation  du caract�re 'j'----------------------------


			Sortir=0;//cr�er une variable binaire (Sortir =1 implique on sort de la boucle et opn passe au caract�re suivant) pour 
	        /* 1) : �liminer les caract�res non alpha-num�riques 
	         * 2) : ou �viter d'�ffectuer des calculs inutiles en cas o� le caract�re est d�j� stock� dans 'Tab2'
	         */



			while(i<Tab1.size() && Sortir==0)//on parcout 'Tab1' pour v�rifier si le caract�re 'j' du texte 'file1' est d�j� stock� ou non
			{
				if (Tab1.size()>0)// tester s'il y a au moins un caract�re stock� dans 'Tab1' : si 'non', on sort de la boucle.
				{
					if (c2==32 || (c2<65) || (c2>90)) // on v�rifie si le caract�re 'j' du texte 'file1' est alpha-num�rique: si 'non', on sort de la boucle 
					{

						Sortir=1;
					}
					else
					{						

						if((Integer)Tab2.get(i)==c2)// On teste si le caract�re 'j' du texte 'file1' est pr�sent d�j� stock� 
						{
							Tab1.set(i, (int)Tab1.get(i)+1);// si oui (le caract�re 'j' du texte 'file1' est pr�sent d�j� stock�), on incr�mente sa fr�quence dans 'Tab1' et on sort de la boucle (Sortir prends la valeur 1)
							if (max<(int)Tab1.get(i))// cette boucle permet juste de calculer le caract�re le plus fr�quent (elle peut ettre supprimer)
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

			if (Sortir==0)// Si le caract�re  'j' du texte 'file1' n'est stock� dans 'Tab2' (Sortie =0, cela veut dire que c'est un caract�re alpha-num�rique et en plus non pr�sent dans 'Tab2')
			{
				Tab1.add(1);//Si oui, ajouter le caract�re 'j' du texte 'file1' dans 'Tab2' et affecter sa fr�quence � '1' dans 'Tab1'
				Tab2.add(c2);
			}
			j=j+1;

		}



		float somme=0;// cr�er une variable qui contiendra la sommes de toutes les fr�quences des caract�re alpha-num�riques du texte 'file1'
		ArrayList Tab = new ArrayList(); // cr�er un tableau qui contiendra les caract�res en "unicode" du tableau Tab1 (Tab1 est en ascii). Peut �tre supprimer

		for (i=0;i<Tab1.size();i++)// Parcourir toutes les fr�quences des lettre pour : 1) calculer la somme de ces derni�re et 2) transformer Tab1 "unicode"
		{
			StringBuffer car = new StringBuffer();//sert � transformer Tab1 en "unicode"
			char k1;//sert � transformer Tab1 en "unicode"
			int i1;//sert � transformer Tab1 en "unicode"			

			somme=somme+(int)Tab1.get(i)*((int)Tab1.get(i)-1);// mettre � jours la somme des fr�quences
			n=n+(int)Tab1.get(i);// mettre � jours le nombre de caract�res alpha-num�riiques pr�sents dans le texte 'file1'

			i1=(Integer)Tab2.get(i);//sert � transformer Tab1 en "unicode"

			k1=(char)i1;//sert � transformer Tab1 en "unicode"
			car.append(k1);
			Tab.add(car);// ajouter le caract�re "unicode" � 'Tab'

		}
		StringBuffer car = new StringBuffer();//sert � Transformer le caract�re le plus fr�quent dans le texte en 'unicode'
		float IC = (float) somme/(n*(n-1));//calculer l'indice de coincidence (qui vaut la somme des fr�quences des caract�re alpha-num�riques / (n*(n-1)))
		char k1;//sert � Transformer le caract�re le plus fr�quent dans le texte en 'unicode'
		char k2;
		int i1;//sert � Transformer le caract�re le plus fr�quent dans le texte en 'unicode'
		int i2;

		i1=(Integer)Tab2.get(Idx);//sert � Transformer le caract�re le plus fr�quent dans le texte en 'unicode'

		k1=(char)i1;//sert � Transformer le caract�re le plus fr�quent dans le texte en 'unicode'

		car.append(k1);//sert � Transformer le caract�re le plus fr�quent dans le texte en 'unicode'


		ArrayList Tab4 = new ArrayList();// cr�er une variable pour contenir tous les r�sultats � renvoyer (peut �tre supprimer car on renvoie que IC)

		Tab4.add(IC);
		Tab4.add(Idx);
		Tab4.add(Tab1);
		Tab4.add(Tab);


		return IC;
	}
	///////////3em fonction//////////////////////

	/**la fonctionnalité calcul La longueur de la clé probable tel
	 *  que on calcule la moyenne de l'indice de coÏncidence de chaque longeur de clé  (casser le chiffrement de Vigenère)
	 * Les clés incorrectes fournissent un indice très proche de l'indice de donnée
	 *  aléatoires uniformément distribuées(0,038). Une clé donnant un indice plus élevé (qui s'approche le plus
	 *   de 0.0778,)
	 *  a donc des chances d'être la bonne clé elle prend en entré un fichier text et indice 
	 *  de coincidence de la langue utilisé (ici c'est la langue francaise )on aura en sortie un array liste qui contien 
	 * les moyennes de IC de chaque longeur de clé et la longeur de  clé probable
	 * 
	 * 
	 * @param file1 fichier d'entrée
	 * @param ICf un float qui indique l'indice de coincidence de la langue utilisé 
	 * @return un array List qui contien les moyennes de IC de chaque longeur de clé et la longeur de  cl� probable 
	 * @throws IOException
	 * @see CoincidenceFinal
	 
	 */
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


		Tab1.add(CoincidenceFinal(file1));
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
				float IC = CoincidenceFinal(Texte);

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
	/**la fonctionnalité   effectue l'analyse des fréquences des longueurs des mots pour un texte donné
	  
 * @param file1 un fichier en entré
 * @throws IOException
 * @return  hashtable qui contient Les Longueurs des mots et Les Fréquences associées 
 */

	public static Hashtable LongeurMots(String file1) throws IOException
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

			Texte_Etudie=Texte_Etudie+(char)c2;//recuperer les caract�res 


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
		Hashtable LongeurMots = new Hashtable() ;// On construira  un objet Ngramfreq de la classe Hashtable tele 
		//que cette classse permet de cr�er des collections d'objets associ�s � des noms					
		while (i<Tab1.size())			
		{
			ArrayList Tab = new ArrayList();
			Tab.add((int)Tab1.get(i));
			Tab.add((int)Tab2.get(i));
			
			osw.write("Mots de longeur "+(int)Tab1.get(i)+" : "+(int)Tab2.get(i)+"\n");
			LongeurMots.put(new Integer(i),Tab);//l'indice (la cl�)dans hashtable
			i=i+1;
		}
		osw.close();


		return LongeurMots;
	}	
	//// la 5éme fonction : les N-grammes ////
	/**
	 * Cette fonction calcule  les fr�quences des n-grammes (n=1 ... infini) présents dans le texte étudié.
	 */
	/**
	 * 
	 * @param file1 un fichier en entr�e 
	 * @param Glissant  Un entier (entre 0 ou 1) qui indique si l'analyse est glissante ou pas 
	 * @param Type_Lettre Un entier (entre 1 et 4) qui indique le type des cracteres �tudi�es (que les lettre ou bien que les chiffres ou lettres et chiffres ou tous les caract�res)

	 * @param Un entier (entre 1 et infini) qui indique la taille des grammes (Nombre_L dans l'exemple en bas)  
	 * @param Longeur Un entier (entre 1 et infini) qui indique la taille des grammes
	 * @return  hashtable  qui contien Le N-grammes le + fr�quents  associ�s � leurs fr�quences
	 *   et tous les N-grammes pr�sents dans le texte avec leurs frequences
	 
	 * @throws IOException
	 */

	public static Hashtable N_Grammes(String file1,int Glissant, int Type_Lettre,int Longeur) throws IOException
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
	

		InputStreamReader isr1 = new InputStreamReader(fis1);
		

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

		

		//float somme=0;
		ArrayList Tab = new ArrayList();
		String SortieGramme=file1.substring(0, file1.length()-4)+"_N-Grammes.txt";

	
		Hashtable Ngramfreq = new Hashtable() ;// On construira  un objet Ngramfreq de la classe Hashtable tele 
		//que cette classse permet de cr�er des collections d'objets associ�s � des noms
		Ngramfreq.put(new Integer(0),Idx+1);//l'indice (la cl�)dans hashtable		
		for (i=0;i<Tab1.size();i++)
		{
			ArrayList Tab5 = new ArrayList();
			StringBuffer car = new StringBuffer();
			char k1;
			int i1;	
			Tab5.add((Integer)Tab1.get(i));//frequence
			Tab5.add(Tab2.get(i));//Ngramme
			Ngramfreq.put(new Integer(i+1),Tab5);//			
			
			//somme=somme+(int)Tab1.get(i)*((int)Tab1.get(i)-1);

		}
		
		char k1;
		char k2;
		int i1;
		int i2;



		ArrayList Tab4 = new ArrayList();

		Tab4.add(Tab2.get(Idx));
		Tab4.add(Tab1.get(Idx));
		Tab4.add(Tab1);
		Tab4.add(Tab2);


		return Ngramfreq;
	}	
	/* ---------------- TEST FRANCAIS -----------------------
	 * 
	 */
	/**Cette fonctionnalité prend en paramètre un fichier texte et on aura en sortie un booléen
         * qui indique si vrai le texte est ecrit en langue française sinon elle renvoie faut.
         * @param file1 un fichier en entrée 
         * @param Compare_Gramme un String le bigramme parmi les plus fréquent dans la langue française 
         * @return un boulean si c'est la langue francaise vrai sinon faux
         * @throws IOException
         * @see N_Grammes
         */

	public static boolean  Francais(String file1, String Compare_Gramme) throws IOException
	{
		Hashtable Ngramme =N_Grammes(file1,1,1,2);//apel a la fonction n-gramme

		ArrayList Tab = (ArrayList)Ngramme.get((int)Ngramme.get(0));//recuperer le array liste qui contien l'element le plus frequent et sa frequence
		
		boolean Resultat = true;
		int i=0;
		while (Resultat && i<Compare_Gramme.length())//comparaison de Ngramme avec les plus frequents en langue francaise 
		{
			if (Tab.get(1).toString().charAt(i)!=Compare_Gramme.charAt(i))
			{
				Resultat = false;
			}
			i=i+1;
		}

		if (Resultat)
		{
			return true;
		}
		else
		{
			//System.out.println("Non!");
			return false;
		}
		
	}		
	/**programme main  qui fait appel a toute les fonctionalit�s
	 
	 */
//
//	public static void main(String[] args) throws IOException {
//		int choice1=0;
//		while (choice1!=7)	    		
//		{	    	
//			Scanner choix_f  =new Scanner(System.in);
//			System.out.println("************************************");
//			System.out.println("Please enter your choice: ");
//			System.out.println("1: Comparaison des lignes des deux fichiers: ");
//			System.out.println("2: L'indice de coincidence: ");
//			System.out.println("3: Longueur de la cle probable en utilisant IC ");
//			System.out.println("4: Longueur de mots: ");
//			System.out.println("5: N-grammes: ");
//			System.out.println("6: Test de Langue francaise: ");
//			System.out.println("7: Quitter: ");
//			System.out.println("************************************");
//			choice1 = choix_f.nextInt();
//			switch(choice1){
//			case 1: 
//			{
//				String file1 = "C:/Users/YOUNES/Desktop/site mimi/eclipse/tous-les-classe/src/lesclasses/TEXTE1.txt";
//				String file2 = "C:/Users/YOUNES/Desktop/site mimi/eclipse/tous-les-classe/src/lesclasses/TEXTE2.txt";
//				
//				//LigneCompar(file1, file2);//appele a la fonction qui compare les lignes des deux fichiers
//				System.out.println("Entrez un chiffre puis Appyez sur la touche 'entrer' pour revenir au menu ....");
//				Scanner choix_f1  =new Scanner(System.in);	
//				choice1 = choix_f.nextInt();
//				break;
//			}	               
//			case 2: 
//			{ System.out.println("**************R�sultat de la fonction  coincidence**********************");
//			//appel a la fonction de coincidence elle prend en parametre un texte recuperer dans un fichier///
//			String fichier = "C:/Users/YOUNES/Desktop/site mimi/eclipse/tous-les-classe/src/lesclasses/test_indice.txt";
//			FileInputStream fis1 = new FileInputStream(fichier);//ouvrir le fichier
//			InputStreamReader isr1 = new InputStreamReader(fis1);			    
//
//			int c2;
//			String Texte_Etudie="";
//			while ((c2 = isr1.read()) > -1)//tant que le fichier n'est pas vide 
//			{
//				Texte_Etudie=Texte_Etudie+(char)c2;//recuperer les caract�res 
//			}
//			isr1.close();
//			float fichiersIdentiques = CoincidenceFinal(Texte_Etudie);
//			System.out.println("L'indice de coincidence de texte est : "+(fichiersIdentiques));
//			System.out.println("Entrez un chiffre puis Appyez sur la touche 'entrer' pour revenir au menu ....");
//			Scanner choix_f1  =new Scanner(System.in);	
//			choice1 = choix_f.nextInt();
//
//			break;
//			}	 
//			case 3: 
//
//
//			{ System.out.println("*************R�sultat de la fonction  la longeur de la cl�***********************");
//
//			////appel a la fonction qui calcule la longeur de la cl�. 
//			/* Cette m�thode prend comme param�tres: 
//			 * 1) un texte (r�cup�r� dans un fichier) 
//			 * 2) l'indice de coincidence de la langue �tudi�e*////
//
//
//			String fichier1 = "C:/Users/YOUNES/Desktop/site mimi/eclipse/tous-les-classe/src/lesclasses/testLongeurCl�.txt";
//			FileInputStream fis1 = new FileInputStream(fichier1);//ouvrir le fichier
//			InputStreamReader isr1 = new InputStreamReader(fis1);
//
//			String Texte_Etudie="";
//			int c2;
//			while ((c2 = isr1.read()) > -1)//tant que le fichier n'est pas vide 
//			{
//				if (c2!=32)
//				{
//					Texte_Etudie=Texte_Etudie+(char)c2;//recuperer les caract�res 
//				}			
//			}
//			isr1.close();
//
//			float If=(float) 0.0778;
//
//			ArrayList fichiersIdentiques1 = LongeurC(Texte_Etudie,If);
//
//
//			ArrayList Tab = new ArrayList();
//			Tab=(ArrayList)fichiersIdentiques1.get(1);
//			System.out.println("Les moyennes de IC de chaque longeur de cl� sont : \n"+Tab);
//
//			System.out.println("La longeur de la cl� probable est : "+fichiersIdentiques1.get(3));
//
//			System.out.println("Entrez un chiffre puis Appyez sur la touche 'entrer' pour revenir au menu ....");
//			Scanner choix_f1  =new Scanner(System.in);	
//			choice1 = choix_f.nextInt();				
//
//			break;
//			}	
//
//
//
//
//			case 4: 
//			{ System.out.println("******************R�sultat de la fonction longeur de mots******************");
//			////////appel a la fonction "longeur de mots" qui donne la distribution des longeurs des mots pr�sents dans le texte.
//			/* Cette fonction prend comme param�tres : Le chemin du fichier � �tudier   */
//
//
//
//			String fichier = "C:/Users/YOUNES/Desktop/site mimi/eclipse/tous-les-classe/src/lesclasses/textelo.txt";
//
//			Hashtable Longeurs = LongeurMots(fichier);
//            
//			int i=0;
//			System.out.println("Les  longeurs des mots( et la frequence  associ�s sont:");
//            while(i<Longeurs.size())
//            {
//            //ArrayList Tab = new ArrayList();
//            ArrayList Tab = (ArrayList)Longeurs.get(i);
//            System.out.println(Tab);
//			
//			
//            i=i+1;
//            }
//
//			System.out.println("Entrez un chiffre puis Appyez sur la touche 'entrer' pour revenir au menu ....");
//			Scanner choix_f1  =new Scanner(System.in);	
//			choice1 = choix_f.nextInt();
//
//			break;
//			}
//
//
//
//
//			case 5: 
//			{ System.out.println("***************R�sultat de la fonction n-grammes*********************");
//			//// Appel � la fonction n-grammes /////
//			/*
//			 * Cette fonction prend comme param�tre : 
//			 * 1) Le chemin du texte � �tudier (fichier dans l'exemple en bas)
//			 * 2) Un entier (entre 0 ou 1) qui indique si l'analyse est glissante ou pas (Glissant dans l'exemple en bas)
//			 * 3) Un entier (entre 1 et 4) qui indique le type des lettres �tudi�es (Type_L dans l'exemple en bas)
//			 * 4) Un entier (entre 1 et infini) qui indique la taille des grammes (Nombre_L dans l'exemple en bas)
//			 */
//
//
//			int Glissant = 1;//1 si l'analyse est glissante 0 sinon 
//			int Type_L = 1;//Type_L vaut 1 si l'analyse est effectu�e que sur les lettres, 
//			/* 2 : si l'analyse est effectu�e que sur les chiffres, 
//			 * 3 : si l'analyse est effectu�e sur les chiffres et les lettres et enfin, 
//			 * 4 :si l'analyse est effectu�e sur tous les caract�res.
//			 */
//
//			int Nombre_L = 4;// Equivalent � n : 
//			/* 1: 1-gramme
//			 * 2: 2-grammes
//			 * ect
//			 */
//
//			String fichier = "C:/Users/YOUNES/Desktop/site mimi/eclipse/tous-les-classe/src/lesclasses/testeGram.txt";
//
//
//			Hashtable Ngramme =N_Grammes(fichier,Glissant,Type_L,Nombre_L);
//			System.out.println("Le "+Nombre_L+"-grammes le + fr�quents dans le texte est : "+(Ngramme.get((int)Ngramme.get(0))));
//			ArrayList Tab = new ArrayList();
//			Tab = (ArrayList)Ngramme.get((int)Ngramme.get(0));
//		
//		
//			System.out.println("Les "+Nombre_L+"-grammes pr�sents dans le texte associ�s � leurs fr�quences sont: \n");	
//			for(int i=1;i<Ngramme.size();i++)
//			{
//				//System.out.println("Les "+Nombre_L+"-grammes pr�sents dans le texte sont: "+(Ngramme.get(i)));
//				System.out.println((Ngramme.get(i)));
//			}			
//
//			System.out.println("Entrez un chiffre puis Appyez sur la touche 'entrer' pour revenir au menu ....");
//			Scanner choix_f1  =new Scanner(System.in);	
//			choice1 = choix_f.nextInt();				
//
//			break;
//			}
//
//
//
//
//			case 6: 
//			{
//				String file1 = "C:/Users/YOUNES/Desktop/site mimi/eclipse/tous-les-classe/src/lesclasses/testlangue.txt";
//				
//				ArrayList Tab=new ArrayList();
//				Tab.add("ES");
//				Tab.add("DE");
//				Tab.add("LE");
//				Tab.add("EN");
//				int i=0;
//				boolean Res = false;
//				while (i<Tab.size())
//				{
//				String bigramF=(String)Tab.get(i);
//				boolean F = Francais(file1, bigramF);
//				Res = Res || F;
//				i=i+1;
//				}
//				System.out.println("Resultat :" +Res);
//				
//				System.out.println("Entrez un chiffre puis Appyez sur la touche 'entrer' pour revenir au menu ....");
//				Scanner choix_f1  =new Scanner(System.in);	
//				choice1 = choix_f.nextInt();				
//				
//				break;
//			}	
//			case 7:
//			{
//				System.out.println("----------------- FIN du PROGRAMME ------------------");
//				break;
//			}
//			default:
//				System.out.println("Wrong choice");
//			}	
//		}
//
//}

}

