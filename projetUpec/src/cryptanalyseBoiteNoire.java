import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;


public class cryptanalyseBoiteNoire {

	public static void main(String[] args) throws IOException {
		// TODO Stub de la méthode généré automatiquement
		String f="sortie2.txt";
		TreeSet<Integer> s1=CalculeTailleCle(f,6);
		 
		/*System.out.println( " le s1 ");
		Iterator<Integer> iterator = s1.iterator();
		    while (iterator.hasNext()) {
		      System.out.print(iterator.next() + " \n -- ");
		    }*/
		    
		    
		TreeSet<Integer> s2=CalculeTailleCle(f,5);
		//System.out.println( " le s2 ");

		/* Iterator<Integer> iterator1 = s2.iterator();
		    while (iterator1.hasNext()) {
		      System.out.print(iterator1.next() + " \n -- ");
		    }*/
		s1.retainAll(s2);
		//System.out.println( " le s1  inter s2");

	/*iterator1 = s1.iterator();
		    while (iterator1.hasNext()) {
		      System.out.print(iterator1.next() + " \n -- ");
		    }*/
		int T=s1.last();
		System.out.println(T + " le t");
		int[] tabCle= new int[T];
		int[] tabFreq= new int[T];
		for(int i=0;i<T;i++){
			tabFreq[i]=HauteFrequenceAt(f,T,i);
			
		}
		
		CalculeCle(f,T,tabFreq,tabCle);
		String M="sortieCryptanalyse-"+f;
		CryptanalyseBN(f,M,T,tabCle);
		//43061257
		
	String O="",supos="couches ",sortie="sor2.txt";
		
		O=ClePermutation(M,T,supos);
		System.out.println("\n   le grand O :"+O);
		
		OrdreParBloc(M,O,sortie);
	}

	/******************* 		Calcule de la suite des permutations 		***********************/
	
	private static String ClePermutation(String m, int t,String supo) throws IOException {
		InputStream flux = new FileInputStream(m);
		int i=0,k=0;
		int[] tab=new int[supo.length()];
		
		int[] tab2=new int[t];
		int[] tab3=new int[t];
		
		for(int j=0;j<supo.length();j++){
			tab[j]=supo.charAt(j);
			
		}
		
		String s="";
		
		boolean bool=true;
		
		boolean bb=true;
		
		while(i<flux.available()&&bool){
			s="";
			for(int j=0;j<t;j++){
				tab2[j]=flux.read();
				tab3[j]=0;
			}
			bb=false;
			for(int j=0;(j<supo.length())&&!bb;j++){
				bb=true;System.out.println("  4sd2");
				
				for(int l=0;(l<t)&&bb;l++){
					if((tab[j]==tab2[l])&&tab3[l]==0)
					{
		    			tab3[l]=j+1;
						bb=false;System.out.println("  42");
					}
				} 
				System.out.println("  42sd");
				
			}
			
			if(!bb){
				System.out.print("  324344242342");
				bool=false;
				for(int j=0;j<t;j++){
					tab3[j]=tab3[j]-1;
					if(tab3[j]!=-1){
						
					s=s+tab3[j];
					}
				}
				for(int j=0;j<t;j++){
				
					if(!s.contains(""+j)){
						s=s+j;
					}
				}
				
				
			}
			
			
			
		}
		return s;
	}

	/*************** 		ORDRE PAR BLOC 			 * @throws IOException **********************/
	
	private static void OrdreParBloc(String m, String o,String sortie) throws IOException {
		int[] ord= new int[o.length()];
		InputStream flux = new FileInputStream(m);
		 OutputStream flux2 = new FileOutputStream(sortie);
		int j=0,taillef=flux.available();
		System.out.println(" le t"+taillef);
		while(j<(taillef-(   (  (taillef)%(o.length()))) )){
			
		
		for(int i=0;i<o.length();i++){
			ord[Character.getNumericValue(o.charAt(i))]=flux.read();
		}
		
		for(int i=0;i<o.length();i++){
			flux2.write((char)ord[i]);
		}
		
		j=j+o.length();
		}
		char c=' ';
		for(int i=0;i<((taillef)%(o.length()));i++){
			c=(char)flux.read();
			flux2.write(c);
			System.out.println(" le c "+(int)c+" "+j);
			
		}
		
	}



	/****************   HAUTE FREQUENCE A LA POSITION I   * @throws IOException ****************/
	
	
	static int HauteFrequenceAt(String f, int T,int p) throws IOException {
		InputStream flux = new FileInputStream(f);
		
		int taille=flux.available();
		int[]tab= new int[taille];
		
		int bloc=(taille/T);
	int[] tab1=new int[bloc+1];
		// lecture et recherche du MOD N 
	int pos=p;
		for(int i=0; i<taille; i++){
		   tab[i]=flux.read();
			
		   
			if(i%T==pos){
				tab1[(i-pos)/T]=tab[i];
				}
		}
		
		int el=0,max=0,cp=0,ma=0,k=0;
		int[] xx=new int[bloc];
		
		for(int i=0; i<bloc; i++){
			el=tab1[i];//System.out.println(max);
			for(int j=0; j<bloc; j++){
			   
				if(el==tab1[j]){cp++;}
			}
			xx[i]=cp;
		cp=0;
		}
		
		for(int j=0; j<bloc; j++){
			   
			if((max<xx[j]))//&&(xx[j]!=64))//&&(xx[j]!=23))//&&(xx[j]!=25))//)//)//)//)
			{max=xx[j];k=j;}
		}
		return tab1[k];
		
		/*System.out.println("le max: "+max);
		
		System.out.println("la letre : "+tab1[k]+" et en binaire"+Integer.toBinaryString(tab1[k]));
		System.out.println("correspond: "+(int)' '+" et en binaire"+Integer.toBinaryString((int)' '));*/
		
	}
	
	
	
	/***************** CRYPTANALYSE PAR BLOC (XOR)    * @throws IOException *************************/

	private static void CryptanalyseBN(String f,String s,int t, int[] Cle) throws IOException {
		 InputStream flux1 = new FileInputStream(f);
		 OutputStream flux2 = new FileOutputStream(s);
		 int taille=flux1.available();
		 
		// String S="abcdefgh";
		 
		 //System.out.println("c:");
		 int i=0;
		 int k=0,m=0;
		 long rXOR=0;
		 String X="",M="",dm="",dc="",gc="",gm="",rg="",rd="";
		 int[] tmp=new int[t];
		 
		 while(i<taille){
			 
		tmp[i%t]=flux1.read();
		
		i++;
		
		if(i%t==0){
			 for(int j=0;j<t;j++){
				 k=Cle[j];
				 X=Integer.toBinaryString(k);
				// System.out.println(" le j: "+j+" donc le k"+X);
				 while(X.length()<8){X="0"+X;}
				  
				  dc=X.substring(4);
				  gc=X.substring(0,4);
				 
				 m=tmp[j];
				 M=Integer.toBinaryString(m);
				 while(M.length()<8){M="0"+M;}
				  
				  dm=M.substring(4);
				  gm=M.substring(0,4);
				 
				  rd=Integer.toBinaryString((Integer.parseInt(dm,2))^(Integer.parseInt(gc,2)));
				  rg=Integer.toBinaryString((Integer.parseInt(gm,2))^(Integer.parseInt(dc,2)));
				  while(rg.length()<4){rg="0"+rg;}
				  while(rd.length()<4){rd="0"+rd;}
				rXOR=Integer.parseInt((rd+rg),2);
				flux2.write((char) rXOR);
				//System.out.println("les deux parties :"+rd+rg);
			 } 
		}
		
			 
		 
		 
		 }
		 if(taille%t!=0){
		 
			 for(int j=0;j<taille%t;j++){
				 k=Cle[j];
				 X=Integer.toBinaryString(k);
				// System.out.println(" le j: "+j+" donc le k"+X);
				 while(X.length()<8){X="0"+X;}
				  
				  dc=X.substring(4);
				  gc=X.substring(0,4);
				 
				 m=tmp[j];
				 M=Integer.toBinaryString(m);
				 while(M.length()<8){M="0"+M;}
				  
				  dm=M.substring(4);
				  gm=M.substring(0,4);
				 
				  rd=Integer.toBinaryString((Integer.parseInt(dm,2))^(Integer.parseInt(gc,2)));
				  rg=Integer.toBinaryString((Integer.parseInt(gm,2))^(Integer.parseInt(dc,2)));
				  while(rg.length()<4){rg="0"+rg;}
				  while(rd.length()<4){rd="0"+rd;}
				rXOR=Integer.parseInt((rd+rg),2);
				flux2.write((char) rXOR);
				//System.out.println("les deux parties :"+rd+rg);
			
			 } 
			 
		 }
	}

	//a faire pour tarik
	/********************* CALCULE DE LA CLE XOREE **************************/
	private static void CalculeCle(String f, int t,int[] tabFreq, int[] tabCle) {
		long z=0;
		String S=Integer.toBinaryString(32);
		System.out.println(S);
		while(S.length()<8){S="0"+S;}
		int z1=0,z2=0;
		String dm="",gm="",dc="",gc="",c="";
		for(int i=0;i<t;i++){
			System.out.println(" le i: "+i);

			   dm=S.substring(4);
			   gm=S.substring(0,4);
			  
			  c=Integer.toBinaryString(tabFreq[i]);
			  while(c.length()<8){c="0"+c;}
			  
			  dc=c.substring(4);
			  gc=c.substring(0,4);
				System.out.println(" le 2: "+i);

			  while(   (Integer.parseInt(dm,2)^z1) !=(Integer.parseInt(gc,2) ))
					  {z1++;}
				System.out.println(" le 3: "+i);

			  while(   (Integer.parseInt(gm,2)^z2) !=(Integer.parseInt(dc,2) ))
			  {z2++;}
			 
			  dc=Integer.toBinaryString(z1);
			  while(dc.length()<4){dc="0"+dc;}
			  
			  gc=Integer.toBinaryString(z2);
			  while(gc.length()<4){gc="0"+gc;}
			  
			tabCle[i]=Integer.parseInt((gc+dc),2);
			System.out.println(" le z"+i+" : "+gc+dc);
			z1=0;z2=0;
			
		}
		
	}

	
		/********************  CALCULE DE LA TAILLE DE LA CLE @throws Exception    ***************************///
	
	
	
	private static TreeSet<Integer> CalculeTailleCle(String f,int tb) throws IOException {
		
		InputStream flux = new FileInputStream(f);
		
		int taille=flux.available();
		int[] tab= new int[taille];
		//int modn=0;
		//System.out.println("taille: "+taille);
	
	
		for(int i=0; i<taille; i++){
		   tab[i]=flux.read();
			
		}
	
		
		//FileOutputStream flux2 = new FileOutputStream("testtaillecle.txt");
		//byte[] bloc= new byte[60];
		int[] tab2=new int[tb];
		int[] tab3=new int[tb];
		
		int i=0,t=0,cp=0;
		boolean bool=false;
		int j=0,zz=0;
		//Vector v = new Vector();
		TreeSet<Integer> set = new TreeSet<Integer>();
		
		while(i<((taille-(2*tb)))){
			//System.out.println("hauuuut le i: "+i+" le z:"+zz+" --  "+((i%tb)-zz));
			
			do{//System.out.println("le i: "+i+" le z:"+zz+" --  "+((i-zz)%tb));
				tab2[((i-zz)%tb)]=tab[i];
				i++;
			}while(i%tb!=zz);
			
			
			j=i;
			while(j<((taille-tb))){
			
				
				do{//System.out.println("le j: "+j+" le z:"+zz+" --  "+((j-zz)%tb));
				tab3[((j-zz)%tb)]=tab[j];
				j++;
			
				
				}while(j%tb!=zz);
				
				while(!bool && t<tb){
					if(tab2[t]==tab3[t]){
						cp++;
						}else{bool=true;
								}
					t++;
						}
				t=0;
				
				
				if(cp==tb){
					
					//flux2.write(("le premier bloc a la position : "+(i-tb)+" et le deuxieme bloc a la position:"+(j-tb)).getBytes());
					//flux2.write((", de distance :"+((j-i))+"\n").getBytes());
					
					set.add((j-i));
				//	System.out.println("la dis: "+((j-i)));
					}
				cp=0;
				bool=false;
				//j++;
			}
			i=(i-tb)+1;
			
			
			zz=(zz+1)%tb;
			}
			
			
			j=0;i=0;
			bool=true;
			  Iterator<Integer> iterator = set.iterator();
			  /*   while (iterator.hasNext()) {
			      System.out.print(iterator.next() + " \n -- ");
			    }
		*/
			
			int min=0;
			//iterator = set.iterator();
			if(iterator.hasNext()){min=set.first();}else{min=0;}
			
		
			j=0;
			cp=0;
			//System.out.println("le min: "+min);
			//flux2.write(("\n Recherche de la taille de la cle, Voici le PGCD des distances").getBytes());
			TreeSet<Integer> set2 = new TreeSet<Integer>();

			while(j<min){
				j++;
				iterator = set.iterator();
				//System.out.println("le j: "+j);
				while(iterator.hasNext()&&bool){
					if(!(iterator.next()%j==0)){
						
						bool=false;
					}
					
				}
				
				if(bool){
				//flux2.write((" Le : "+j+"\n").getBytes());
					set2.add(j);	//System.out.println("le j2: "+j);
					}
				bool=true;
				cp=0;
				i=0;
				
			}
		//if(min!=0){
		//	flux2.write((" Le PGCD est : "+set2.last()+"\n").getBytes());}else{
		//		flux2.write((" Le PGCD est : 0 \n").getBytes());
			//return
		//	}
		return set2;
		//System.out.println(set.retainAll(set2));;
	}
	

}
