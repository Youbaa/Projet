import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class classeRegroupef {
	
	/*****la fonctionnalité de substitution permet de remplacer un caractere par un autre caractere
		    *elle prend en entrée un tableau d'entiers et le caractere qu'on veut 
			*remplacer avec le caractere par lequel on veut le remplacer et on aura en sortie un 
			*tableau dans le quel on a fait la substitution
			* @param v Tableau d'entiers
			* @param a Le caractere à remplacer
			* @param b le caractere par lequel on remplace 
			* @return tableau d'entiers dans lequel la substitution a été appliquée
		
			* */
	private static int[] substitution(int[] v, char a, char b) {
		// TODO Auto-generated method stub
		
		int taille=v.length;
		for (int i=0;i<taille;i++)
		{ 

			if (v[i]==(int)a)
			{
				v[i]=b;			
			}
			
			
		}
		return v;
		
	}
        /*****la fonctionnalité de substitution permet de remplacer un caractere par un autre caractere
	    *elle prend en entrée un fichier et le caractere qu'on veut 
		*remplacer avec le caractere par le quel on veut le remplacer et on aura en sortie un 
		*fichier dans le quel on a fait la substitution
		* @param f fichier d'entrée
		* @param a le caractere à remplacer
		* @param b le caractere par le quel on remplace 
		* @return un fichier dans le quel la substitution a été appliquée
		* @exception IOException
	
		* */
	static String substitution(String f, char a, char b) throws IOException {
		// TODO Auto-generated method stub
		
		String sortie="resultat.txt";
		InputStream flux= new FileInputStream(f);
		OutputStream flux1= new FileOutputStream(sortie); 
		int taille= flux.available();
		
		for (int i=0;i<taille;i++)
		{ 
			int c=flux.read();
			
			if (c==(int)a)
			{
				flux1.write(b);
				              
			
			}
			else
			{
				flux1.write(c);
				
			}
			
		}
		return sortie;
		
	}
	/** la fonctionnalité calcul taille permet de calculer la taille d'une clé 
	*en decoupant un fchier texte en bloc de taille tb et chercher les blocs identiques 
	*et les distances entre ces blocs ainsi que les diviseurs de ces dernieres et chercher 
	*le pgcd de ces diviseurs qui correspond à la taille de la clé
	* @param f fichier donné en entrée
	* @param tb la taille des blocs
	* @return un entier qui correspond à la taille de la clé
	* @exception IOException
	*/
	public static int CalculeTailleCle(String f,int tb) throws IOException {
		
			InputStream flux = new FileInputStream(f);
				
				int taille=flux.available();
				int[] tab= new int[taille];
				


				for(int i=0; i<taille; i++){
				   tab[i]=flux.read();
					
				}

				
				
				int[] tab2=new int[tb];
				int[] tab3=new int[tb];
				
				int i=0,t=0,cp=0; 
				
				boolean bool=false;
				int j=0,zz=0,z2=0;
				
				TreeSet<Integer> set = new TreeSet<Integer>();
				
				while(i<((taille-(2*tb)))){
					//System.out.println("hauuuut le i: "+i+" le z:"+zz+" --  "+((i%tb)-zz));
					
					do{//System.out.println("le i: "+i+" le z:"+zz+" --  "+((i-zz)%tb));
						tab2[((i-zz)%tb)]=tab[i];
						i++;
					}while(i%tb!=zz);
					
					
					j=i;
					z2=zz;
					
					while(j<((taille-tb))){
					
						
						do{
						tab3[((j-z2)%tb)]=tab[j];
						
					
						//System.out.print(tab3[((j-z2)%tb)]);
						j++;
						}while(j%tb!=z2);
						
						while(!bool && t<tb){
							//System.out.println(" tab2 "+tab2[t]+"  tab3 "+tab3[t]+"**************");
							if(tab2[t]==tab3[t]){
								cp++;
								}else{bool=true;
										}
							t++;
								}
						t=0;
						//System.out.println("le bool "+bool+" le t "+t);
						
					//	System.out.println("le zz "+zz+" le z2 "+z2+" le i  "+i+" le j "+j);
						if(cp==tb){
							
							set.add((j-i));//System.out.println(j-i);
						
							}
						cp=0;
						bool=false;
						z2=(z2+1)%tb;
						j=(j-tb)+1;
					}
					i=(i-tb)+1;
					
					
					zz=(zz+1)%tb;
					}
					
					
					j=0;i=0;
					bool=true;
					  Iterator <Integer> iterator = set.iterator();
					
					
					int min=0;
					//iterator = set.iterator();
					if(iterator.hasNext()){min=set.first();}else{min=0;}
					
				
					j=0;
					cp=0;
					
					TreeSet<Integer> set2 = new TreeSet<Integer>();

					while(j<min){
						j++;
						iterator = set.iterator();
						
						while(iterator.hasNext()&&bool){
							if(!(iterator.next()%j==0)){
								
								bool=false;
							}
							
						}
						
						if(bool){
						
							set2.add(j);	//System.out.println(j);
							}
						bool=true;
						cp=0;
						i=0;
						
					}

				return set2.last();
		
		                   
		
	}
	/** la fonctionnalité calcul taille permet de calculer la taille d'une clé 
	*en decoupant un tableau d'entiers en blocs de taille tb et chercher les blocs identiques 
	*et les distances entre ces blocs ainsi que les diviseurs de ces dernieres et chercher 
	*le pgcd de ces diviseurs qui correspond à la taille de la clé
	* @param tab tableau d'entiers donné en entrée
	* @param tb la taille des blocs
	* @return un entier qui correspond à la taille de la clé
	*/
	public static int CalculeTailleCle(int[] tab,int tb)  {
		
	
		int taille=tab.length;
		
		int[] tab2=new int[tb];
		                       
		int[] tab3=new int[tb];
		                       
		
		int i=0,t=0,cp=0;  
		                
		
		boolean bool=false;
		int j=0,zz=0,z2=0;//
		
		TreeSet<Integer> set = new TreeSet<Integer>();
		                                             
		  //recherche des blocs identiques et calculer les distances entre ces distances                                           
		
		while(i<((taille-(2*tb))))
			                       
			                      
		{
			
			
			do
			{
				tab2[((i-zz)%tb)]=tab[i];
				i++;
			}
			while(i%tb!=zz);  
			
			
			j=i;
			    
			z2=zz;
			
			while(j<((taille-tb))) 
				                
			{
			
				
				do
				{
				tab3[((j-z2)%tb)]=tab[j];
				
			
				
				j++;
				}while(j%tb!=z2);
				
				while(!bool && t<tb)
					               
					              
				{
					
					if(tab2[t]==tab3[t])
					{
						cp++;
						}
					else
					{
						bool=true; 
						          
								}
					t++;
						}
				t=0;
				
				
			
				if(cp==tb)
					     
				{
					
					set.add((j-i));
					               
				
					}
				cp=0;
				bool=false;
				z2=(z2+1)%tb;
				j=(j-tb)+1;
			}
			i=(i-tb)+1;
			
			
			zz=(zz+1)%tb;
			}
			
			
			j=0;i=0;
			bool=true;
			  Iterator <Integer> iterator = set.iterator();
			
			
			int min=0;
			
			if(iterator.hasNext())
			{
				min=set.first();
				                
				}
			else
			{
				min=0;
			}
			
		//calculer le pgcd des distances
			j=0;
			cp=0;
			
			TreeSet<Integer> set2 = new TreeSet<Integer>(); 
			                                               

			while(j<min)
                        		
			{
				j++;
				iterator = set.iterator();
				
				while(iterator.hasNext()&&bool)
					                           
					
					                          
				{
					if(!(iterator.next()%j==0))
						                      
						                       
					{
						
						bool=false;
					}
					
				}
				
				if(bool)
				{
				
					set2.add(j);	
					                
					}
				bool=true;
				cp=0;
				i=0;
				
			}

		return set2.last();
		                   
		
	}
	/**la fonctionnalité de SubstitutionParBloc son principe c'est de decouper un fichier 
	*texte en blocs de taille T et verifier si le caractere se trouvant à la position
	*j dans ce bloc egal au caractere que l'on veut remplacer puis si c'est le cas 
	*le remplacer par le caractere voulu
	*@param f fichier donné en entrée 
	*@param a le caractere que l'on veut remplacer
	*@param b le caractere par le quel on veut remplacer
	*@param T la taille des blocs
	*@param j la position à la quelle on veut remplacer 
	*@return un fichier dans le quel la substitution a été faite
	*@exception IOException
	
	*/
	static String SubstitutionParPositionDansLeBloc(String f, char a, char b, int T,int j) throws IOException {
		String sortie="resultat2.txt";
		InputStream flux= new FileInputStream(f);
		OutputStream flux1= new FileOutputStream(sortie); 
		int taille= flux.available();
		
		for (int i=0;i<taille;i++)
		{
			int c=flux.read();
			
			if( (c==(int)a)&&((i%T)==j))
				
			{
				flux1.write(b);
				
			}
			
			else
				
			{
				flux1.write(c);
				
				
			}
			
		}
		
		return sortie;
	}
	/**la fonctionnalité HauteFrequenceAt prend en entrée un fichier texte  puis on le decoupe
	*en bloc de taille T et on cherche le caractere le plus repeté  à une position p donnée
	*dans ces blocs et c'est donc ce caractere  qui a la haute frequence
	*@param f la fichier donné en entrée
	*@param T la taille des blocs
	*@param p la position à la quelle on veut chercher la caractere le plus frequent
	*@return un entier qui correspond au caratctere qui a la plus haute frequence
	
	*/
	static int HauteFrequenceAt(String f, int T,int p) throws IOException {
		  InputStream flux = new FileInputStream(f);

		  int taille=flux.available();
		  int[]tab= new int[taille];
	      int bloc=(taille/T);
	      int[] tab1=new int[bloc+1];

	      int pos=p;
	      for(int i=0; i<taille; i++)
	      {
	     
	    	  tab[i]=flux.read();


	   
	    	  if(i%T==pos)
	   
	    	  {
	    	 
	   
	    		  tab1[(i-pos)/T]=tab[i];
	
	    
	    	  }
	
	      }

	     int el=0,max=0,cp=0,k=0;
	     int[] xx=new int[bloc];

	     for(int i=0; i<bloc; i++){
	     el=tab1[i];
	     for(int j=0; j<bloc; j++){

	     if(el==tab1[j])
	     {
	    	 cp++;
	    	 }
	}
	     xx[i]=cp;
	     
	     cp=0;
	}

	    for(int j=0; j<bloc; j++){

	    if((max<xx[j]))
	  {
	    max=xx[j];
	    k=j;
	    }
	}
	    
	   return tab1[k];
	   

	}
        /**la methode HauteFrequence permet de calculer les caracteres les plus frequents
	    de chaque postion dans les blocs de taille T.
	   * @param f le fichier donné en entrée
	   * @param T la taille des blocs
	   * @return tableau d'entiers 
	   * @see HauteFrequenceAt
	   * @exception IOException
	   */
	static int[] HauteFrequence(String f, int T) throws IOException {
		 int [] tab =new int[T];
                 principale.resultat.setText("");
		 for(int i=0;i<T;i++){
			 tab[i]=HauteFrequenceAt(f,T,i);
                         String dd="le caractère le plus fréquent à la position "+i+"est :"+tab[i];
                         principale.resultat.append(dd+"\n");
		 }
		 
	   return tab;

	}
        /**la fonctionnalité de XORParBloc prend un tableau d'entiers, et une clé de taille t
	  puis on decoupe le texte en blocs de taille t et on fait le XOR entre chaque bloc
	  et la clé et on aura en sortie un autre tableau qui est le chiffré du fichier donné
	  en entrée 
	  *@param tab tableau donné en entrée
	  *@param Cle un tableau qui contient la clé avec la quelle on fait le XOR
	  *@return un tableau d'entiers
	 
	  */  
	public static int[] XORParBloc(int[] tab ,int[] Cle ) {
		  
			
			//OutputStream flux2 = new FileOutputStream(sortie);
		  int[] sortie=new int[tab.length];
			
			int taille=tab.length;
	
			int i=0,xx=0;
			int k=0,m=0;
			long rXOR=0;
			int[] tmp=new int[Cle.length];
			while(i<taille)
			{
				tmp[i%Cle.length]=tab[i];
				i++;
				if(i%Cle.length==0)
				{
					for(int j=0;j<Cle.length;j++)
					{
						 k=Cle[j];
						 m=tmp[j];
						 rXOR=( k ) ^ ( m);
						 sortie[xx]=(int) rXOR;xx++;
						 
					} 
				}
			 }
			
			 if(taille%Cle.length!=0)
			 {
				 for(int j=0;j<taille%Cle.length;j++)
				 {
					 k=Cle[j];
					 m=tmp[j];
					 rXOR=( k ) ^ ( m);
					 sortie[xx]=(int) rXOR;xx++;
					
	} 
		
		
			 }return sortie;

					}
        /**la fonctionnalité de XORParBloc prend un fichier en entrée, et une clé de taille t
	  puis on decoupe le texte en blocs de taille t et on fait le XOR entre chaque bloc
	  et la clé et on aura en sortie un fichier qui est le chiffré du fichier donné
	  en entrée 
	  *@param f le fichier donné en entrée
	  *@param Cle un string qui le clé avec  la quelle on fait le XOR
	  *@return le chemin du fichier résultat
	 
	  */
	String XorParBloc(String fichier, String S) throws IOException{
		File ff=new File("XorParBloc.txt");    
                String  sortie =ff.getAbsolutePath();
		InputStream flux1 = new FileInputStream(fichier);
		 OutputStream flux2 = new FileOutputStream(ff);
		 int taille=flux1.available();
		 
		 //String S="abcdefgh";
		 int[] Cle= new int[S.length()];
	
		 for(int i=0;i<S.length();i++){
			 Cle[i]=(int) S.charAt(i);
		 } 
		 System.out.println("c:");
		 int i=0;
		 int k=0,m=0;
		 long rXOR=0;
		 int[] tmp=new int[S.length()];
		 
		 while(i<taille){
			 
		tmp[i%S.length()]=flux1.read();
		
		i++;
		
		if(i%S.length()==0){
			 for(int j=0;j<S.length();j++){
				 k=Cle[j];
				 m=tmp[j];
				 System.out.println("c:sf");
				rXOR=((long) k ) ^ ((long) m);
				flux2.write((char) rXOR);
				
			 } 
		}
		
			 
		 
		 
		 }
		 if(taille%S.length()!=0){
		 
			 for(int j=0;j<taille%S.length();j++){
			 k=Cle[j];
			 m=tmp[j];
			 
			rXOR=((long) k ) ^ ((long) m);
			flux2.write((char) rXOR);
			
			 } 
			 
		 }
		
                 return sortie;
			
	}
        /**
           * Bloc jumeaux prend en entrée un fichier et deux entier
           * @param tk
           * @param r
           * @return le chemin du fichier
          */
        String BlocsJumeaux (String fichier, int tk, int  r) throws IOException{
		
		
			  
			   File ff=new File("sortie-blocjumeaux.txt");    
                           String sortie =ff.getAbsolutePath();
			   
			   InputStream flux1 = new FileInputStream(fichier);
			   OutputStream flux2 = new FileOutputStream(ff);

				
				
				int t1=flux1.available();
				int[]tab1= new int[t1];
				// System.out.println("le t1: "+t1+"le t: "+t);
				
				
			
				
				for(int i=0; i<t1; i++){
					   tab1[i]=flux1.read();
					
					}
				
				
				
				
				int i=0,c=0;
				int j=tk,k=0;boolean b=true;
				int x=0,y=0;
				
				
				while(i<t1 ){
					k=i;
					if(i%tk==0){
						j=i+tk;
						x=i+(tk*2);
					while(x<t1){
						if(i%tk==0&&j%tk==0){
						while((j<x)&& b){
							if(tab1[i]==tab1[j]){c++;}else{b=false;}
							j++;i++;
						}
						}
					b=true;
					y=j%tk;
					x=x+tk;j=(j-y)+tk;i=k;
                                        byte[] saut="\n".getBytes();
					if(c==r){ flux2.write(("la position du premier bloc: "+i+", la position du deuxieme bloc: "+(j-(2*tk))).getBytes());
                                             flux2.write(saut);
					
					}c=0;
					}
					
					}
					
					i=k+1;
				}
                      return sortie;
				
				
				}
        /**
           * code hexadécimal prend 2 fichier et une chaine de caractère
           * @param fichier
           * @param fichier2
           * @param S
           * @return le chemin du fichier résultat
           */
        String Code_Hexa (String fichier, String fichier2, String S) throws IOException{
	
           String walid="Code_hexa"+"_"+S+".txt";
	   File ff=new File(walid);    
           String sortie =ff.getAbsolutePath();  
	   // clair chiffr
	   InputStream flux = new FileInputStream(fichier);
	   InputStream flux1 = new FileInputStream(fichier2);
           
	   //sortie
	   OutputStream flux2 = new FileOutputStream(sortie);
		int t=flux.available();
		int[]tab= new int[t];
		int t1=flux1.available();
		int[]tab1= new int[t1];
		// System.out.println("le t1: "+t1+"le t: "+t);
		//String S="ricklose";
		String X="";
		flux2.write("Information de la cle : \n ".getBytes());
		flux2.write(("la chaine : "+S+" \n").getBytes());
		int tk=S.length();
		
		for(int i=0; i<S.length(); i++){
				
				X="le symbole "+i+" ,position"+i%tk +" :  "+(char)S.charAt(i)+" En int :  "+(byte)S.charAt(i)+"  Et en Hexadecimal :  "+Integer.toHexString(S.charAt(i)) +"\n";
				flux2.write(X.getBytes());

			}
		
		flux2.write("\n Information du claire : \n ".getBytes());
		
		for(int i=0; i<t1; i++){
			   tab1[i]=flux1.read();
				X="le symbole "+i+" ,position"+i%tk +"  :   "+(char)tab1[i]+" En int :  "+tab1[i]+"  Et en Hexadecimal :  "+Integer.toHexString(tab1[i])+"\n";
				flux2.write(X.getBytes());
				 //System.out.println("le i: "+i);
			}
		
		
		flux2.write("\n Information du chiffre  : \n ".getBytes());
		for(int i=0; i<t; i++){
		   tab[i]=flux.read();
			S="le symbole "+i+" ,position"+i%tk+" :  "+(char)tab[i]+" En int :  "+tab[i]+"  Et en Hexadecimal :  "+Integer.toHexString(tab[i])+"\n";
			flux2.write(S.getBytes());

		}
		
		
		return sortie;
		
		}
        /**
             * Code binaire prend en entrée deux fichier et une chaine de caractère
             * @param fichier
             * @param fichier2
             * @param S
             * @return le chemin du fichier résultat
             */
        String Code_bin(String fichier,String fichier2, String S) throws IOException{
	
		     String walid="Code_bin"+"_"+S+".txt";
	             File ff=new File(walid);    
                     String sortie =ff.getAbsolutePath();  
		     InputStream flux = new FileInputStream(fichier);
		     InputStream flux1 = new FileInputStream(fichier2);
		     OutputStream flux2 = new FileOutputStream(sortie);
			int t=flux.available();
			int[]tab= new int[t];
			int t1=flux1.available();
			int[]tab1= new int[t1];
			// System.out.println("le t1: "+t1+"le t: "+t);
			//String S="0A4Bb9a6";
			String X="";
			flux2.write("Information de la cle : \n ".getBytes());
			flux2.write(("la chaine : "+S+" \n").getBytes());
			int tk=S.length();
			
			for(int i=0; i<S.length(); i++){
					
					X="le symbole "+i+" ,position"+i%tk +" :  "+(char)S.charAt(i)+" En int :  "+(byte)S.charAt(i)+"  Et en binaire :  "+Integer.toBinaryString(S.charAt(i))+"\n";
					flux2.write(X.getBytes());

				}
			
			flux2.write("\n Information du claire : \n ".getBytes());
			
			for(int i=0; i<t1; i++){
				   tab1[i]=flux1.read();
					X="le symbole "+i+" ,position"+i%tk +"  :   "+(char)tab1[i]+" En int :  "+tab1[i]+"  Et en binaire :  "+Integer.toBinaryString(tab1[i])+"\n";
					flux2.write(X.getBytes());
					 //System.out.println("le i: "+i);
				}
			
			
			flux2.write("\n Information du chiffre  : \n ".getBytes());
			for(int i=0; i<t; i++){
			   tab[i]=flux.read();
				S="le symbole "+i+" ,position"+i%tk+" :  "+(char)tab[i]+" En int :  "+tab[i]+"  Et en binaire :  "+Integer.toBinaryString(tab[i])+"\n";
				flux2.write(S.getBytes());

			}
                        return sortie;
			}
        /**
          * Max bytes prend en entrée un fichier et rend le max bytes dans un fichier
          * @param chemin
          * @return le chemin du fichier résultat
          */  
        String  Max_bytes(String chemin) throws IOException {
                // TODO Auto-generated method stub

            //taille en octets 
           // System.out.println("la taille est: "+taille+" " + "octets");
            FileInputStream file = new FileInputStream(chemin);
            InputStream flux= file;
            int taille=flux.available();

            int c=0,max=0;

            for (int i=1;i<taille;i++){
                c=flux.read();
                if(c>max){
                        max=c;

                        }
                }
            //System.out.println("le max est : "+max+" et en char : "+(char)max);
            String S="le max est : "+max+" et en char : "+(char)max;
            File f=new File("max_byte.txt");
            FileOutputStream file2 = new FileOutputStream(f);
            String resultat=S;

            OutputStream flux2= file2;
            flux2.write(S.getBytes());		 

            return resultat;
            }
        /**
         Cette fonctionnalité prend en entré un fichier texte en Unicode et elle renvoi un fichier dans le même emplacement qui contient le  même texte en ASCII UTF-8
         *@param filename un fichier en entré en unicod
         *@return un fichier en Ascii UTF 8
         */
        String UnicodeToAscii (String filename) throws IOException {
			FileInputStream fis = new FileInputStream(filename);
			InputStreamReader isr = new InputStreamReader(fis);
			System.out.println(isr.getEncoding());
                        File ff=new File("UnicodeToAscii.txt");    
                        String sortie =ff.getAbsolutePath();
			FileOutputStream fos = new FileOutputStream(ff);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF8");
			
			int c;
			while ((c = isr.read()) > -1) {
				osw.write(c);
				System.err.println(c);
			}
			 osw.close();
			 isr.close();
			 
			 fis = new FileInputStream(ff);
			 isr = new InputStreamReader(fis);
			 System.out.println(isr.getEncoding());
		 	 isr.close();
		return sortie;
		} 
        /**
          *Cette fonctionnalité  prend en entré deux fichiers texte et elle renvoi une chaine de caractères qui indique est ce que ils ont le même ensemble *des caractères et elle renvoie aussi le nombre des caractères de chaque fichier .
          *@param premier fichier en entrée
          *@param deuxième fichier en entrée
          *@return  String qui indique est ce que ils ont le même ensemble des caractères et elle renvoie aussi le nombre des caractères de chaque fichier . 
         */
        String ensembleSymboleEgaux(String file1, String file2) throws IOException {
		String msg = null;
		FileInputStream fis1 = new FileInputStream(file1);
		FileInputStream fis2 = new FileInputStream(file2);

		InputStreamReader isr1 = new InputStreamReader(fis1);
		InputStreamReader isr2 = new InputStreamReader(fis2);



		//
		int tab1 []=new int [255];
		int tab2 []=new int [255];
		int Taille_Tab1;
		int Taille_Tab2;
		int c;
		int k=0;
		int p=0;
		int Resultat=0;
		// lecture du fichier 1
		while ((c = isr1.read()) > -1) { 		

			p=0;
			Resultat = 0;
			while (p<k && Resultat==0)
			{
				if(c==tab1[p])
				{
					Resultat = 1;
				}
				p=p+1;
			}
			if (Resultat==0)
			{
				tab1[k]=c;
				k=k+1;
			}

			
		}
		Taille_Tab1=k;
		principale.resultat.append("-----fin recup tableau 1-------"+"\n");
		isr1.close();
		// lecture du fichier 2
		int j = 0;      
		int Resultat1=0;

		k=0;
		while ((c = isr2.read()) > -1) { 		

			p=0;
			Resultat = 0;
			while (p<k && Resultat==0)
			{
				if(c==tab2[p])
				{
					Resultat = 1;
				}
				p=p+1;
			}
			if (Resultat==0)
			{
				tab2[k]=c;
				k=k+1;
			}

				
		}
		Taille_Tab2=k;
		isr2.close();


		principale.resultat.append("le nombre des caracteres de premier fichier est:"+Taille_Tab1+"\n");
		principale.resultat.append("le nombre des caracteres de 2eme fichier est:"+Taille_Tab2+"\n");
		principale.resultat.append("-----fin recup tableau 2-------"+"\n");
		k=0;
		if (Taille_Tab1 == Taille_Tab2 )
		{


			k=0;
			while (k < Taille_Tab2 && Resultat1==0) {
				j=0;
				Resultat = 0;
				while (j < Taille_Tab1 && Resultat==0) {
					
					if ((tab2[k] == tab1[j])||(tab2[j] == tab1[k]))
					{
						Resultat = 1;
						j=j+1;
					}
					else 
					{
						j=j+1;
					}
				}	
				if (Resultat==0)
				{
					Resultat1=1;
				}	
				k=k+1;
			}
		}

		else
		{
			Resultat1 = 1;
		}

		if (Resultat1==1)
		{
			principale.resultat.append("Aumoins un caractere n'est pas commun dans les deux fichiers"+"\n");
			
		}
		else
		{
			principale.resultat.append("Les deux ensembles sont concordant!"+"\n");	
			
		}
         return msg;

	}     
        /**
             * XOR prend en entrée un fichier et une chaine de caractère et rend le résultat dans un fichier
             * @param fichier
             * @param str
             * @return le chemin du fichier résultat
             * @see logicalXOR
             * @see ConvertionBinaire
             * @see ConvertionBinaire1
             * @see test1
             * @see test2
             */
        String xor(String fichier,String str) {
		
		String chaine="";
                String sor = null;
		
		//lecture du fichier 	
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne;
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
          
		
		char carac = str.charAt(0);
		
		
		String str2 = chaine;
		String sortie="";
		
		String a1 = ConvertionBinaire(carac);
		String binaryString = ConvertionBinaire1(carac);
		for(int ii=0;ii<str2.length();ii++){
			char carac2 = str2.charAt(ii);		
			String binaryString2 = ConvertionBinaire(carac2);
			binaryString = test1(a1, binaryString2);
			binaryString2 = test2(a1, binaryString2);
			String resultat = binaryString+" XOR "+binaryString2+" : ";
			for(int youba=0;youba<(binaryString.length());youba++){
				char ca = binaryString.charAt(youba);
				boolean a;
				if(ca=='1'){
					a=true;				
				}else{
					a=false;	
				}
				char ca2 = binaryString2.charAt(youba);
				boolean b;
				if(ca2=='1'){
					b=true;				
				}else{
					b=false;	
				}
					boolean c = logicalXOR(a,b);
					if(c==false){
						resultat = resultat +"0";
					}else{
						resultat = resultat +"1";
					}		
			}
			System.out.println(resultat);
			sortie += "\n"+resultat;
			
			try {
                                 File fff=new File("XorParCaractere.txt");    
                                 sor =fff.getAbsolutePath();
				OutputStream ff = new FileOutputStream (fff);
				  byte[] ss= sortie.getBytes();
				try {
					ff.write(ss);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
                return sor;
	}
	public static boolean logicalXOR(boolean x, boolean y) {  // elle effectue l'operation xor (s'il sont pareil ça retourn un vrai sinon un faux)
		if(x==y){
			return false;
		}else{
			return true;
		}
	    
	}
	public static String ConvertionBinaire(char carac) { //fonction qui converti un caractère passé en paramètre
		char lettre = carac; 
		int monAsci = (new Character(lettre)).hashCode(); 
		String binaryString = Integer.toBinaryString(monAsci); 
		return binaryString;
	}
	public static String ConvertionBinaire1(char carac) { // fonction de conversion et d'affichage
		char lettre = carac; 
		int monAsci = (new Character(lettre)).hashCode(); 
		String binaryString = Integer.toBinaryString(monAsci); 
		System.out.println("Vous avez saisi le caractère : " + carac +" en binaire c'est : "+ binaryString);
		return binaryString;
	}
	public static String test1(String binaryString, String binaryString2) {// fonction qui test si les deux caractère on la même taille si le deuxième caractètest plus grand on rajoute des 0 au 1er caractère 
		int a = binaryString.length();
		int b = binaryString2.length();
		int c=0;
		if(a<b){
			c = b-a;
			for(int k=0;k<c;k++){
				binaryString = binaryString+"0";
			}
		}
		return binaryString;
	}
	public static String test2(String binaryString, String binaryString2) {// même choses que le teste1
		int a = binaryString.length();
		int b = binaryString2.length();
		int c=0;
		
		if(a>b){
			c = a-b;
			for(int k=0;k<c;k++){
				binaryString2= binaryString2+"0";
			}
		}
		return binaryString2;
		
	}
        /**
         * Analyse de frequence prend en entrée un fichier et rend un fichier
         * @param chemin
         * @throws FileNotFoundException
         * @throws IOException
         * @return le chemin du fichier résultat
         */
        String analyse_frequence(String chemin) throws FileNotFoundException, IOException {

    String S="";
    File f=new File("Analysefrequence.txt");
    PrintWriter ecrire = new PrintWriter(new BufferedWriter(new FileWriter(f, true))); 
    
    String resultat=f.getAbsolutePath();
    
    InputStream flux = new FileInputStream(chemin);
    int taille=flux.available();
    int[]tab= new int[taille];
    int[][]tab2= new int[2][taille];

    /// lecture fichier
    byte b=0;

    ecrire.println("***** lecture et affichage des caractere lu ***");
    //flux.read(tab);
    for(int i=0; i<taille; i++){
    tab[i]=flux.read();
    //	System.out.println("le symbole "+i+" :  "+(char)tab[i]);

    }


    /// metre les caractere dans une string
    for(int i=0; i<taille; i++){
    S=S+((char)tab[i]);
    }



    boolean a=true;

    int y=0;
    // initialition de la matrice qui calcule les frequence

    for(int i=0; i<taille; i++){
    tab2[0][i]=tab[i];
    tab2[1][i]=-1;
    }

    int i=0,x;
    boolean z=false;

    // calcule des frequence
    ecrire.println("calcule des frequence");
    while(i<taille){

    x=tab2[0][i];

    for(int k=0; k<i ; k++){
    if((tab2[0][k]==x)){
    z=true; 
    }
    }
    if (!z){
    for(int k=i; k<taille; k++){
    if((tab2[0][k]==x)){
    tab2[1][i]++;
    }
    }

    }

    z=false;i++;
    }
    // affichage des frequence de chaque symbole
    i=0;
    while(i<taille){
    if((tab2[1][i]>-1)){
    S=Integer.toBinaryString(tab2[0][i]);
    while(S.length()<8){S="0"+S;}
     ecrire.println("le symbole "+i+" :  "+(char)tab2[0][i]+" nombre de repetition est : "+tab2[1][i]+ " qui est en binaire : "+S);
    }
    i++;
    }


    // recherche de la frequence maximal	
    int max=0;
    i=0;
    while(i<taille){
    if((tab2[1][i]>max)){//
    max=tab2[1][i];}
    i++;
    }



    // affiche le symbole de la frequence maximal
    ecrire.println("\n affiche le symbole de la frequence maximal ");
    
    for(int j=0; j<taille; j++){
    if (tab2[1][j]==max){
    ecrire.println("le symbole "+tab2[0][j]+" nombre de repetition est : "+tab2[1][j]);
    }

    }



    String xx=" esantir";
    i=0;
    // affichage des symbole de haute frequence 
    ecrire.println("\n affichage des symbole de haute frequence \n");
    
    char s=' ';
    String ss="";
    while(i<xx.length()){

    s=(char)xx.charAt(i);

    ss=Integer.toBinaryString(s);
    ecrire.println("le caractere "+xx.charAt(i)+" est representer en decimal : "+ss +"\n");
    i++;
    

    }
    ecrire.close();

    //System.out.println(max);	
    return resultat;
    } 
         /**
         * Analyse de frequence par caractère prend en entrée un fichier et rend un fichier
         * @param fichier
         * @return le chemin du fichier résultat
         */
        String frequence(String fichier) {
		// TODO Auto-generated method stub

		String chaine="";
		String sor = null;
		//lecture du fichier 	
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne;
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		
		ArrayList<String> tab1 = new ArrayList<String>();
		ArrayList<Integer> tab2 = new ArrayList<Integer>();
	
		boolean trouver=false;
		String car;
		for (int i = 0; i < chaine.length(); i++) {
			car = Character.toString(chaine.charAt(i));
			trouver=false;
			for (String s:tab1) {
				if(s.equalsIgnoreCase(car)){
					trouver=true;
				}
			}	
			if(trouver==false){
				tab1.add(car);
				
			}
		}
		
		
		
		//------------------------- compteur-------------------
		int compteur;
		for (String s:tab1) {
			compteur=0;
			for (int i = 0; i < chaine.length(); i++) {
				car = Character.toString(chaine.charAt(i));
				
				if(s.equalsIgnoreCase(car)){
					compteur++;
				}
			}
			tab2.add(compteur);
		}
	
		
		//---------------------- Affichage ---------------------
		String resultat="Les caractères trouvés sont : \n";
	        int x= chaine.length();
                for (int i = 0; i < tab2.size(); i++) {
                int in = tab2.get(i);
                float integer=in;
                String s=tab1.get(i);
                float k= (integer/x)*100;
                resultat+="Le caractere : "+s+" est répété "+in+" la fréquence est: "+k+"%"+"\n";
                
                }
                
                
		try {   
                         File fff=new File("AnalyseFrequenceCaractere.txt");    
                                sor =fff.getAbsolutePath();
			OutputStream f2 = new FileOutputStream (fff);
			
			  byte[] ss= resultat.getBytes();
			try {
				f2.write(ss);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return sor;
	}
        /**
          * decoupage par bloc prend en entrée un fichier et une clé "taille du bloc" 
          * @param chemin
          * @param cle
          * @throws IOException 
          * @return le chemin du fichier résultat
          */
        String decoupbloc(String chemin, int cle) throws IOException {
              
          // TODO Auto-generated method stub
          File ff=new File("decoupage_par_bloc.txt");    
          String sortie =ff.getAbsolutePath();
          InputStream f = new FileInputStream(chemin);
          OutputStream f2 = new FileOutputStream (ff);

          int Taille=f.available();//estimation du nombre d'octets qui peuvent etre lus 

          String S = "";
          int[]tab= new int[Taille];//tableau de taille d'octet du fichier
          int bloc=Taille/cle; //nombre de ligne (qui
          String [] s = new String[bloc];

          int j=0;
          String saut="\n";
          byte[] ss= saut.getBytes();

          for(int i=0; i<Taille; i++){ //boucle qui lis les caracteres du fichier
            tab[i]=f.read();
          }
          int a=0;
          String dernier="";
          for(int i=0; i<Taille; i++){//boucle de test
          if(j<cle){ // 1er test j<cle
              S=S+((char)tab[i]);
              j++;
              if((i+1)==(Taille)) // test qui sauvgarde les dernier caractere quand 'est pas un multiple de la cle
              {
                 dernier=S;
              }
          }else{
              s[a]=S;
              S="";
              j=0;
              a++; 
              i--;
          }
          } 
          String p;
          for (int i=0; i<bloc;i++){ // boucle de remplissage du fichier de sortie
        //	  if(i==0){
        ////		   p = "\n"+(Integer.toString(i+1))+") ";
        ////		  f2.write(p.getBytes());
        //	  }

             f2.write(s[i].getBytes());
             f2.write(ss);
            p = "\n";	//+(Integer.toString(i+1))+") ";
                  f2.write(p.getBytes());
          }

          f2.write(dernier.getBytes()); // recuperation des caracteres en plus
         return sortie;
         }
        /**
         * Information data prend en entrée un fichier et rend les informations concernant le contenu du fichier
         * @param chemin
         * @throws IOException
         * @return le chemin du fichier résultat
         */
        String informationData(String chemin) throws IOException{
     
        File ff=new File("informationData.txt");    
        String sortie =ff.getAbsolutePath();
        InputStream flux = new FileInputStream(chemin);
			  
			   OutputStream flux2 = new FileOutputStream(ff);
				int t=flux.available();
				int[]tab= new int[t];
				
				
				String S="";
				
				int tk=8;// taille de la cle (taille des bolocs)
				
			
				
				flux2.write("\n Information du chiffre  : \n ".getBytes());
                                
				for(int i=0; i<t; i++){
				   tab[i]=flux.read();
					S="le symbole "+i+" ,position"+i%tk+" :  "+(char)tab[i]+" En int :  "+tab[i]+"  Et en binaire :  "+Integer.toBinaryString(tab[i])+"\n";
					flux2.write(S.getBytes());
    
				}
		
				flux2.close();
				flux.close();
        return sortie;
        }       
        
        void Ecrire (String a){
        try{
        InputStream flux=new FileInputStream(a); 
        InputStreamReader lecture=new InputStreamReader(flux);
        BufferedReader buff=new BufferedReader(lecture);
        String ligne;
        while ((ligne=buff.readLine())!=null){

                principale.resultat.append(ligne);
                principale.resultat.append("\n");
        }
        buff.close(); 
        }		
        catch (Exception e){
        System.out.println(e.toString());
        }
        }
        /** le programme main pour faire appel 
	   * aux differentes methodes
	   */
//	public static void main(String[] args) throws IOException{
//		  
//		  /// Les appeles aux classes !!
//		  
//		  String s="cryptogramme3.txt";
//		  int t=CalculeTailleCle(s,6);
//		  int a=HauteFrequenceAt(s,t,1);
//		 
//		  
//char c='c';
//char x='x';
//	
//		substitution(s,c,x);
//		SubstitutionParPositionDansLeBloc(s,c,x,t,1);
//
//		  
//		  
//		  String f="schneier.txt";
//		  String sortie="schneier--Xor.txt";
//		  
//		 // XORParBloc(f,sortie,"abcdefghi");
//		  
//		  
//		  	String clair="schneier--Xor--clair.txt";
//		  
//		 // XORParBloc(sortie,clair,"abcdefghi");
//		  
//		  substitution(clair,' ','#');
//		 
//  
//	  }

    
}
