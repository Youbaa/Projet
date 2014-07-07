import java.io.*;
import java.text.Normalizer;
import java.util.*;

public class bonnePermutation {
	/******Cette fonctionnailté renvoie  les n-grammes les plus frequents dans
	 * un texte donné
	 * @param TailleDepart  C'est la taille minimale des n-grammes
	 * @param TailleFin  C'est la taille maximale des n-grammes
	 * @param m Le fichier dans lequel on cherche les n-grammes
	 * @param y Le nombre de n-grammes que l'on veut recuperé pour chaque taille de n-gramme 
	 * @see N_Grammes
	 * @exception IOExeption
	 * @return Un ensemble de n-grammes 
	 */
	public static Set SetOfMostFrequentGrammes(String m,int TailleDepart, int TailleFin,int y ) throws IOException {
	
		Set<String> set= new HashSet();
		
		
		int N=TailleDepart,x=Integer.MAX_VALUE;
		
		ArrayList tab=null,tabG=null,tabF=null;
		
		int[] YY=new int[y]; 
		
		while(N<TailleFin){
		
			 tab=N_Grammes(m,1,1,N);
			
			 tabG=(ArrayList)tab.get(3);
			 tabF=(ArrayList) tab.get(2);
			 
			 x=Integer.MAX_VALUE;
			 
			 for(int j=0;j<y;j++){
			
				 
				 for(int i=0;i<tabF.size();i++){

					
						 if(((Integer)tabF.get(i)>YY[j])&&((Integer)tabF.get(i)<x)){
					
							 YY[j]=(Integer)tabF.get(i);
						 

						 
					 }
					
				 }
				 x=YY[j];
			 }
			 
			
			 
			 for(int j=0;j<y;j++){
			
				 if(YY[j]>0){
			 set.add((String)tabG.get(tabF.indexOf(YY[j]))); 
				 }
			 }
			 
			 
			 for(int j=0;j<y;j++){
					YY[j]=0;
					
				 }

			 N++;
		}
		
		
		
		return set;
	}
	/*** La fonctionnalité de permutation permet de permuter un texte avec une clé donnée
	 * @param m Le texte à permuter
	 * @param tab4 Un tableau qui contient la clé de permutation
	 * @param sortie Le nom du fichier de sortie permuté 
	 * @exception IOException
	 *
	 * 
	 */
	public static String Permutation(String m, int[] tab4,String sortie) throws IOException {
		int[] ord= new int[tab4.length];
                File ff=new File(sortie);    
                String  chemin_sortie =ff.getAbsolutePath();
                
		InputStream flux = new FileInputStream(m);
		OutputStream flux2 = new FileOutputStream(ff);
                
		int j=0,taillef=flux.available();
		//System.out.println(" le t"+taillef);
		
		while(j<(taillef-(   (  (taillef)%(tab4.length))) )){
		
						
		
		for(int i=0;i<tab4.length;i++){
			ord[tab4[i]]=flux.read();
		}
		
		for(int i=0;i<tab4.length;i++){
			flux2.write((char)ord[i]);
		}
		
		j=j+tab4.length;
		}
		char c=' ';
		for(int i=0;i<((taillef)%(tab4.length));i++){
			c=(char)flux.read();
			flux2.write(c);
			
			
		}
	
		return chemin_sortie;
	}
	/*** cette fonctionnalité cherche les n_grammes d'une taille donnée dans un fichier 
	 * @param file1 Le fichier donné en entrée
	 * @param Glissant C'est une valeur entiere qui permer à l'utilisateur de choisir le mode glissant ou non glissant
	 * @param Type_Lettre C'est une valuer entiere qui donne le choix à l'utilisateur pour definir le type caracteres qu'il desire chercher
	 * @param int La taille des n-grammes que l'on veut chercher
	 * @return ArrayList contenant les differents n-grammes ainsi que leur frequences respictives
	 * 
	 * 
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
				//Trouv1=0;
				N_Lettres = "";
				Trouv1 = 0;
				while(p<N && Trouv1==0)
				{
					if (((Character)Tab_Caract.get(i+p))!=Tab2.get(j).toString().charAt(p))
					{
						Trouv1=1;
					}
					N_Lettres = N_Lettres+(Character)Tab_Caract.get(i+p);
					p=p+1;
				}			

				if (Trouv1==0)
				{								
					Tab1.set(j, (Integer)Tab1.get(j)+1);
					if (max<(Integer)Tab1.get(j))
					{
						max = (Integer)Tab1.get(j);
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
					N_Lettres = N_Lettres+(Character)Tab_Caract.get(i+p);
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
			somme=somme+(Integer)Tab1.get(i)*((Integer)Tab1.get(i)-1);

		}
		osw.write("La plus FrÈquente est la lettre "+Tab2.get(Idx)+" : "+Tab1.get(Idx));
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
	/***************	Caclcul la permutation 	***********/
	/***Cette fonctionnalité permet de calculer la permutation d'un tableau d'entiers en utilsant une clé donné
	 * @param m Le tableau donné en entrée
	 * @param tab4 La clé de permutation
	 * @return Un tableau qui est le resultat de la permutation du tableau donné en entrée avec la clé donnée
	 * 
	 */
	public static int[] Permutation(int[] m, int[] tab4) {
		
		
		int[] ord= new int[tab4.length];
		int[] Sortie= new int[m.length];
	
		
		int j=0,taillef=m.length; int x=0,xx=0;
		
		while(j<(taillef-(   (  (taillef)%(tab4.length))) )){
			
		
		for(int i=0;i<tab4.length;i++){
			ord[tab4[i]]=m[x];x++;
		}
		
		for(int i=0;i<tab4.length;i++){
			Sortie[xx]=ord[i];xx++;
		}
		
		j=j+tab4.length;
		}
		
		for(int i=0;i<((taillef)%(tab4.length));i++){
			Sortie[xx]=m[x];x++;xx++;
			
			
			
		}
	return Sortie;
		
	}
	/**********		Generation de cles	******************/
	/** cette methode permet de generer des clés à partir d'une clé donné en entrée
	 * @param c C'est un tableau de comparable qui contient la clé initiale
	 * @return les differnetes clés possibles à partir de la clé donnée en entrée
	 * 
	 
	/*** print permutation permet d afficher toutes les permutations possibles à partir d'une clé initiale
	 * 
	 */
	public static void printPermutations( Comparable[] c ) {
		System.out.println( Arrays.toString( c ) );
		while ( ( c = nextPermutation( c ) ) != null ) {
			System.out.println( Arrays.toString( c ) );
		}
	}
	/*** elle revoie, à partir d'une clé donnée la clé de permutation suivante
	 * 
	 */
	//CAlcule la combinaison suivante 
	public static Comparable[] nextPermutation( final Comparable[] c ) {
		// 1. finds the largest k, that c[k] < c[k+1]
		int first = getFirst( c );
		if ( first == -1 ) return null; // no greater permutation
		// 2. find last index toSwap, that c[k] < c[toSwap]
		int toSwap = c.length - 1;
		while ( c[ first ].compareTo( c[ toSwap ] ) >= 0 )
			--toSwap;
		// 3. swap elements with indexes first and last
		swap( c, first++, toSwap );
		// 4. reverse sequence from k+1 to n (inclusive) 
		toSwap = c.length - 1;
		while ( first < toSwap )
			swap( c, first++, toSwap-- );
		return c;
	}
	/*****		renvoie  l'indice i d'une case ou la valeur de la case a l'indice i+1 est superieur a la valeur de la case i si elle existe, null sinon***/
	public static int getFirst( final Comparable[] c ) {
		for ( int i = c.length - 2; i >= 0; --i )
			if ( c[ i ].compareTo( c[ i + 1 ] ) < 0 )
				return i;
		return -1;
	}
	// permuter deux cases
	public static void swap( final Comparable[] c, final int i, final int j ) {
		final Comparable tmp = c[ i ];
		c[ i ] = c[ j ];
		c[ j ] = tmp;
	}
	/***************	calcul score des N-grammes	***********/
	/*** La fonctionnalité  Score permet de calculer le score dES n-grammes pour le tableau donné en entrée c'est à dire en comptant
	 * le nombre d'apparitions de ces derniers dans le tableau passé en parametre
	 * @param tab Le tableau donné en entrée
	 * @param set L'ensemble des n-grammes les plus frequents
	 * @return Le score 
	 *
	 * 
	 */
	public static int Score( int[] tab, Set<String> set) throws IOException {
		
	
		Iterator<String> it=set.iterator();
		
		int taille=tab.length/2;
		int ens= set.size();// set contient les n grammes
	int[] sc=new int[ens];

		boolean bool=true;
		
		String s="";
	
		int tb=0;
		int t=0,score=0;
		char[]tab2= null;
		int i=0,zz=0;
	
		while(it.hasNext()){	
			s=it.next().toLowerCase();
		
			tb=s.length();
	
			tab2= new char[tb];
			i=0;
			zz=0;
			while(i<(taille)){
		
				do{
			
					tab2[((i-zz)%tb)]=(char)tab[i];
			
					i++;
				}while(i%tb!=zz);
		
				t=0;
				bool=true;
				while(t<tb&&bool){
			
					if(!(tab2[t]==s.charAt(t)))
							{bool=false;}
					t++;
				}
				
		
				
				if((t==tb)&&bool){
				
				
						sc[score]=sc[score]+1;
						}
		
		
		
					
				
					i=(i-tb)+1;
		
		
		
					zz=(zz+1)%tb;
		
					}
			
			score++;	
	
		}
		int som=0;
		
		for(int j=0;j<ens;j++){
		
			som=som+sc[j];
		}
		return som;
	}
        /*** Cette fonctionnalité permet de generer toutes les clés de permutation possibles de taille T, et permuter le texte f donné
 * en entrée avec ces dernieres et revoie la bonne clé de  permutation.
 * @param f Le texte permuté
 * @param T La taille de la clé 
 * @param set Un ensemble de n-grammes les plus frequents
 * @see ComparableToInt
 * @see Permutation
 * @see Score
 * @see nextPermutation
 * @return La bonne clé de permutation
 * 
 */
	public static int[] GenerationEtTest(String f, int T,Set<String> set) throws IOException {
		
		Comparable<Integer>[] c=new Comparable[T];
		
		for(int i=0;i<T;i++){
		
			c[i]=i;
		}
		
		InputStream in= new FileInputStream(f);
		
		
		int[] tab = new int[in.available()];
		int[] tab2 = new int[in.available()];
		for(int i=0;i<tab.length;i++)
		{
			
			tab[i]=in.read();
		}
	
		
		
		
		int fact=1,ff=T;
		while(ff>1)
		{
			fact=fact*ff;ff--;
		}
		int[] Sc=new int[fact];
		
		int[] CleInt=ComparableToInt(c);
		
		int x=0;
		
		do
		{
			CleInt=ComparableToInt(c);
			for(int i=0;i<CleInt.length;i++){
				System.out.print(CleInt[i]);
			}
				tab2=Permutation(tab,CleInt);
				
				
				Sc[x]=Score(tab2,set);
				System.out.print(" et le score : "+Sc[x]+"\n");/*********/
				x++;
				c=nextPermutation(c);
				
		 
				
				//System.out.println(4);/*********/
			
		
		}while(c!=null);
		
		//System.out.println(5);/*********/
		
		c=new Comparable[T];//ccccc
		for(int i=0;i<T;i++){
			
			c[i]=i;// quand on sort c egal à null pour la parcourir pour trouver la bnne cle
		}
		int maxSc=0;
		boolean bool=true;
		int indice=0;
		
	for(int i=0;(i<fact);i++){
		
		if(maxSc<Sc[i])
		{maxSc=Sc[i];indice=i;}
	
		
	}
	System.out.println("le score max : "+maxSc);
	
	for(int i=0;(i<fact)&&bool;i++){
		if(i==indice){bool=false;}else{c=nextPermutation(c);}
		
	}
	
		CleInt=ComparableToInt(c);
		return CleInt;
	}
        /*** Cette methode permet de changer le type de la clé qui passe d'un tableau comparable à un tableau d'entiers 
 * @param c La clé donnée en entrée qui est de type comprable
 * @return Un tableau d'entiers qui correspend à la clé
 * 
 * 
 */
	public static int[] ComparableToInt(Comparable<Integer>[] c) {
		int[] cle= new int[c.length];
		for(int i=0;i<c.length;i++){
			cle[i]= (Integer) c[i];
			
		}
		return cle;
	}
	/*** cette foctionnalté permet de renvoyer  les clés de permutation possibles en prenant en entrée le message clair  
	 * et le message chiffré
	 * @param tabm Le message clair
	 * @param tabc Le message chiffré
	 * @return un ensemble set qui contient l'ensemble des clés de permutation candidates 
	 */
	public static Set CalculSetClePermute(String chemin, String chemin2) throws IOException {
		
                
		InputStream flux= new FileInputStream(chemin);
		int taille= flux.available();
                int[] tabm=new int[taille];
		for (int v=0;v<taille;v++)
		{ 
			int c=flux.read();
                        tabm[v]=c;
		}
                
                InputStream flux2= new FileInputStream(chemin2);
		int taille2= flux2.available();
                int[] tabc=new int[taille2];
		for (int h=0;h<taille2;h++)
		{ 
			int c=flux2.read();
                        tabc[h]=c;
		}
		
		if(tabm.length==tabc.length){
			
			Set<int[]> set= new HashSet<int[]>();
			
			Comparable[] c=new Comparable[tabm.length];
			for(int i=0;i<tabm.length;i++){
				c[i]=i;
			}
			
			int[] cle=null;
			
			int[] tmp=new int[tabm.length];
			
			boolean b=true;
		
			while(c!=null)
			
			{
				cle=ComparableToInt(c);
				tmp=Permutation(tabc, cle);
			
				b=true;
			
				for(int i=0;i<tabm.length && b;i++)
				{
			
					if(tmp[i]!=tabm[i])
					{
						
						b=false;
						
					}
	
				}
				
			if(b){set.add(cle);}
			
			c=nextPermutation(c);
		
                  
                        
		}
		
		
		
		return set;
                
                
		
		}else
		{
			return null;
			
		}
	}
	/*** le programme principal pour faire appel aux differentes fonctionnalités
	 * 
	 */
//	public static void main(String[] args) throws IOException {
		
		
//		int T=8;
//		String f="texte1.txt",Cryptogramme="testClePermute.txt",Resultat="resultatesttt.txt",N_grammes="sor2.txt";
//		
//		
//	
//	
//		int[] cle = new int[]{3,7,0,6,2,1,4,5};
//		
//		 Permutation(f,cle,Cryptogramme);
//		 
//		
//        Set<String> set= SetOfMostFrequentGrammes(N_grammes,2,4,4);
//		
//		int[] Bcle=GenerationEtTest(Cryptogramme,T,set);
////		System.out.print("la cle return: ");
////		
////		for(int i=0;i<Bcle.length;i++)
////		{
////			System.out.print(Bcle[i]);
////		} 
////		  System.out.print('\n');
//		
//     	Permutation(Cryptogramme,Bcle,Resultat);
//     	
//     	
//     	
//     	int[] tabm=new int[]{106,104,89,123,99,100,101,89};
//     	int[] tabc=new int[]{100,106,89,104,89,101,123,99};
//     	                                  
//     	                                   Set<int[]> ss=CalculSetClePermute(tabm,tabc);
//     	                                  
//     	                                   Iterator<int[]> it= ss.iterator();
//     	                                   int[] tmp=null;
//     	                                  System.out.print("les clés candidates sont: ");
//     	                                 System.out.print('\n');
//     	                                   while(it.hasNext()){
//     	                                                    
//     	                                    tmp=it.next();
//     	                                    
//     	                                    for(int i=0;i<tmp.length;i++){
//     	                                                      System.out.print(tmp[i]);
//     	                                    }
//     	                                    System.out.print('\n');
//     	                                                    
//     	                                   }
     	 
     	
     	
     
     	
		//}
}

	








