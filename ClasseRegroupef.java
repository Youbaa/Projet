
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.TreeSet;


public class ClasseRegroupef {
	
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
	private static String substitution(String f, char a, char b) throws IOException {
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
		

		for(int i=0; i<taille; i++)
		{
		   tab[i]=flux.read();
			
		}

		
		
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
	
	private static String SubstitutionParPositionDansLeBloc(String f, char a, char b, int T,int j) throws IOException {
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
	
	
	  private static int HauteFrequenceAt(String f, int T,int p) throws IOException {
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
	   * @param p la position dans le bloc
	   * @return tableau d'entiers 
	   * @see HauteFrequenceAt
	   * @exception IOException
	   */
	  private static int[] HauteFrequence(String f, int T,int p) throws IOException {
		 int [] tab =new int[T];
		 for(int i=0;i<T;i++){
			 tab[i]=HauteFrequenceAt(f,T,i);
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
	  *@return un fichier
	 
	  */
	  
	  public static void XORParBloc(String f,String sortie,String cle ) throws IOException{
	  InputStream flux1 = new FileInputStream(f);
		
		OutputStream flux2 = new FileOutputStream(sortie);
		
		int taille=flux1.available();
		String S=cle;
		int[] Cle= new int[S.length()];
		
		for(int i=0;i<S.length();i++)
		{
			 Cle[i]=(int) S.charAt(i);
		}
		System.out.println("c:");
		int i=0;
		int k=0,m=0;
		long rXOR=0;
		int[] tmp=new int[S.length()];
		while(i<taille)
		{
			tmp[i%S.length()]=flux1.read();
			i++;
			if(i%S.length()==0)
			{
				for(int j=0;j<S.length();j++)
				{
					 k=Cle[j];
					 m=tmp[j];
					 rXOR=( k ) ^ ( m);
					 flux2.write((char) rXOR);
				} 
			}
		 }
		
		 if(taille%S.length()!=0)
		 {
			 for(int j=0;j<taille%S.length();j++)
			 {
				 k=Cle[j];
				 m=tmp[j];
				 rXOR=( k ) ^ ( m);
				 flux2.write((char) rXOR);
} 
	flux1.close();
	flux2.close();
	
		 }

				}
	  /** le programme main pour faire appel 
	   * aux differentes methodes
	   */
	  
	  public static void main(String[] args) throws IOException{
		  
		  /// Les appeles aux classes !!
		  
		  String s="cryptogramme3.txt";
		  int t=CalculeTailleCle(s,6);
		  int a=HauteFrequenceAt(s,t,1);
		 
		  
char c='c';
char x='x';
	
		substitution(s,c,x);
		SubstitutionParPositionDansLeBloc(s,c,x,t,1);

		  
		  
		  String f="schneier.txt";
		  String sortie="schneier--Xor.txt";
		  
		  XORParBloc(f,sortie,"abcdefghi");
		  
		  
		  	String clair="schneier--Xor--clair.txt";
		  
		  XORParBloc(sortie,clair,"abcdefghi");
		  
		  substitution(clair,' ','#');
		 
		  
		  
		  
	  


		  
		  
	
		
		  
		  
	  }
}
