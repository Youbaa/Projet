
            import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
        

            import java.awt.BorderLayout;
            import java.awt.EventQueue;
            import java.awt.FlowLayout;
            import java.awt.GridBagConstraints;
            import java.awt.GridBagLayout;
            import java.awt.Image;

            import java.awt.Toolkit;
            import java.awt.event.ActionEvent;
            import java.awt.event.ActionListener;
            import java.awt.event.ItemEvent;
            import java.awt.event.ItemListener;
            import java.io.FileInputStream;
            import java.io.FileNotFoundException;
            import java.io.IOException;
            import java.io.InputStreamReader;
            import java.util.ArrayList;
            import java.util.HashSet;
            import java.util.Iterator;
            import java.util.List;
            import java.util.Scanner;
            import java.util.Set;
            import java.util.logging.Level;
            import java.util.logging.Logger;
            import javax.imageio.ImageIO;
            import javax.swing.ComboBoxModel;
            import javax.swing.DefaultComboBoxModel;
            import javax.swing.GroupLayout;
            import javax.swing.GroupLayout.Alignment;
            import javax.swing.ImageIcon;
            import javax.swing.JButton;
            import javax.swing.JComboBox;
            import javax.swing.JFileChooser;
            import javax.swing.JFrame;
            import javax.swing.JLabel;
            import javax.swing.JMenu;
            import javax.swing.JMenuBar;
            import javax.swing.JMenuItem;
            import javax.swing.JOptionPane;
            import javax.swing.JPanel;
            import javax.swing.JScrollPane;
            import javax.swing.JTabbedPane;
            import javax.swing.JTextArea;
            import javax.swing.JTextField;
            import javax.swing.JToolBar;
            import javax.swing.LayoutStyle.ComponentPlacement;
            import javax.swing.UIManager;
            import javax.swing.UnsupportedLookAndFeelException;
            import javax.swing.border.EmptyBorder;
            import javax.swing.border.TitledBorder;

            public class principale extends JFrame {

            public static String[] tab = new String[] {"Code hexadecimal", "Code binaire"};
            public JPanel contentPane;
            public JTextField fichier;
            public static JTextField remplace_Field;
            public static JTextField par_Field;
            public static JLabel par;
            public static JLabel remplace;
            public static JComboBox comboBox1;
            public static JTextField fichier2;
            public static JButton btnParcourir_1;
            public final JComboBox comboBox_1;
            public static JTextArea resultat;

            classeRegroupef CRG=new classeRegroupef();
            classeRegroupef2 CRG2=new classeRegroupef2();
            bonnePermutation BPerm=new bonnePermutation();
            String s;
            /**
            * Launch the application.
            */
            public static void main(String[] args) {
            String lookAndFeelName = UIManager.getSystemLookAndFeelClassName();

            try {

            UIManager.setLookAndFeel(lookAndFeelName);
            } 
            catch (ClassNotFoundException e) {} 
            catch (InstantiationException e) {}
            catch (IllegalAccessException e) {}
            catch (UnsupportedLookAndFeelException e) {}

            EventQueue.invokeLater(new Runnable() {
            public void run() {
            try {
            principale frame = new principale();
            frame.setVisible(true);
            } catch (Exception e) {
            e.printStackTrace();
            }
            }
            });
            }

            /**
            * Create the frame.
            */
            public principale() {
            setTitle("Projet Upec");
            
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 711, 501);

            JMenuBar menuBar = new JMenuBar();
            setJMenuBar(menuBar);

            JMenu mnNewMenu = new JMenu("Fichier");
            menuBar.add(mnNewMenu);

            JMenuItem mntmNewMenuItem = new JMenuItem("Ouvrir");
            mnNewMenu.add(mntmNewMenuItem);

            JMenu mnAide = new JMenu("Aide");
            menuBar.add(mnAide);

            JMenuItem mntmModeDemploi = new JMenuItem("Mode d'emploi");
            mnAide.add(mntmModeDemploi);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(new BorderLayout(0, 0));

            JPanel panel = new JPanel();
            contentPane.add(panel, BorderLayout.CENTER);
            panel.setLayout(new BorderLayout(0, 0));

            JPanel panel_1 = new JPanel();
            panel.add(panel_1, BorderLayout.NORTH);

            final JComboBox comboBox = new JComboBox();
            comboBox.setModel(new DefaultComboBoxModel(new String[] {"Decoupage par Bloc", "Haut Frequence At","Haute Frequence","Analyse de frequence par caractere","Analyse en boite noir", "Substitution", "Substitution par bloc","XOR", "UnicodeToAscii", "EnsembleSymbolesEgaux", "LeByteMaximum", "Calculer la taille de la cle","Trouver la bonne permutation","CalculSetClePermute","LigneCompar","CoincidenceFinal","LongeurC","LongeurMots","N_Grammes","Francais","Information data"}));

            fichier = new JTextField();
            fichier.setColumns(10);

            JButton btnParcourir = new JButton("Parcourir");
            btnParcourir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
            {
            fichier.setText(chooser.getSelectedFile().getAbsolutePath());
            }  
            }
            });

            remplace = new JLabel("Remplacer :");

            remplace_Field = new JTextField();
            remplace_Field.setColumns(10);

            par = new JLabel("Par :");

            par_Field = new JTextField();
            par_Field.setColumns(10);

            comboBox1 = new JComboBox();
            comboBox1.setModel(new DefaultComboBoxModel(new String[] {"Bloc jumaux","Code hexadecimal", "Code binaire"}));

            fichier2 = new JTextField();
            fichier2.setColumns(10);

            btnParcourir_1 = new JButton("Parcourir");
            btnParcourir_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
            {
            fichier2.setText(chooser.getSelectedFile().getAbsolutePath());
            }
            }
            });

            comboBox_1 = new JComboBox();
            comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Xor par bloc", "Xor par caractere"}));
            GroupLayout gl_panel_1 = new GroupLayout(panel_1);
            gl_panel_1.setHorizontalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
            .addGroup(gl_panel_1.createSequentialGroup()
            .addContainerGap()
            .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
            .addGroup(gl_panel_1.createSequentialGroup()
            .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
            .addComponent(fichier2, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
            .addComponent(fichier, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
            .addPreferredGap(ComponentPlacement.RELATED)
            .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
            .addComponent(btnParcourir_1)
            .addComponent(btnParcourir))
            .addGap(118))
            .addGroup(gl_panel_1.createSequentialGroup()
            .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
            .addGroup(gl_panel_1.createSequentialGroup()
            .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(ComponentPlacement.RELATED)
            .addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGroup(gl_panel_1.createSequentialGroup()
            .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(ComponentPlacement.UNRELATED)
            .addComponent(remplace)))
            .addPreferredGap(ComponentPlacement.RELATED)
            .addComponent(remplace_Field, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(ComponentPlacement.UNRELATED)
            .addComponent(par)
            .addPreferredGap(ComponentPlacement.RELATED)
            .addComponent(par_Field, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
            .addContainerGap(167, Short.MAX_VALUE))))
            );
            gl_panel_1.setVerticalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
            .addGroup(gl_panel_1.createSequentialGroup()
            .addGap(5)
            .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
            .addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(remplace)
            .addComponent(remplace_Field, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(par)
            .addComponent(par_Field, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(ComponentPlacement.RELATED)
            .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
            .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGap(1)
            .addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
            .addComponent(btnParcourir)
            .addComponent(fichier, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
            .addComponent(fichier2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(btnParcourir_1))
            .addContainerGap())
            );
            panel_1.setLayout(gl_panel_1);

            JPanel panel_2 = new JPanel();
            panel_2.setBorder(new TitledBorder(null, "Resultat", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            panel.add(panel_2, BorderLayout.CENTER);
            panel_2.setLayout(new BorderLayout(0, 0));

            JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
            panel_2.add(tabbedPane);

            JPanel panel_3 = new JPanel();
            tabbedPane.addTab("Resultat", null, panel_3, null);
            panel_3.setLayout(new BorderLayout(0, 0));

            resultat = new JTextArea();
            resultat.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(resultat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            panel_3.add(scrollPane, BorderLayout.CENTER);

            JPanel panel_7 = new JPanel();
            tabbedPane.addTab("Historique", null, panel_7, null);
            panel_7.setLayout(new BorderLayout(0, 0));

            final JTextArea historique = new JTextArea();
            historique.setEditable(false);
            JScrollPane scrollPane1 = new JScrollPane(historique, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            panel_7.add(scrollPane1, BorderLayout.CENTER);

            JToolBar toolBar = new JToolBar();
            contentPane.add(toolBar, BorderLayout.NORTH);

            remplace.setVisible(false);
            remplace_Field.setVisible(false);
            par.setVisible(false);
            par_Field.setVisible(false);

            JButton btnNewButton = new JButton("");
            btnNewButton.setIcon(new ImageIcon(getClass().getResource("run_exc.gif")));
            btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            int i;

            if(comboBox.getSelectedItem().toString().equals("Decoupage par Bloc")){
            i=1;
            String chemin=fichier.getText();
            if (chemin.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir un fichier ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            JOptionPane jop = new JOptionPane();
            String cle = jop.showInputDialog(null, "Veuillez introduire la taille du bloc", "Taille de la cle", JOptionPane.QUESTION_MESSAGE);
            int taille=Integer.parseInt(cle);
            try {
            s=CRG.decoupbloc(chemin, taille);
            resultat.setText("");
            resultat.append(s+"\n");
            CRG.Ecrire(s);
            historique.append("Decoupage par bloc avec le fichier :\n"+chemin+"\n"+" résultat :\n"+s+"\n");
            historique.append("\n ________________________________________________________ \n");	
            } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }
            }
            }else{
            if(comboBox.getSelectedItem().toString().equals("Analyse de frequence par caractere")){
            i=2;
            String chemin=fichier.getText();
            if (chemin.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir un fichier ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            s=CRG.frequence(chemin);
            resultat.setText("");
            resultat.append(s+"\n");
            CRG.Ecrire(s);
            historique.append("Analyse de frequence par caractere pour le fichier :\n"+chemin+"\n"+" résultat :\n"+s+"\n");
            historique.append("\n ________________________________________________________ \n");
            }

            }else{
            if(comboBox.getSelectedItem().toString().equals("UnicodeToAscii")){
            i=3;
            String chemin=fichier.getText();
            if (chemin.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir un fichier ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{

            try {
            s=CRG.UnicodeToAscii(chemin);
            resultat.setText("");
            resultat.append(s+"\n");
            CRG.Ecrire(s);
            historique.append("UnicodeToAscii pour le fichier :\n"+chemin+"\n"+" résultat :\n"+s+"\n");
            historique.append("\n ________________________________________________________ \n");	
            } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }
            //historique.append("Analyse par frequence avec le fichier :"+chemin+"et la taille"+taille+"\n");
            }
            //menu m=new menu(i);
            //m.setVisible(true);
            }else{
            if(comboBox.getSelectedItem().toString().equals("Substitution")){
            i=4;
            String chemin=fichier.getText();
            if (chemin.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir un fichier ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            String aa=remplace_Field.getText();
            String bb=par_Field.getText();
            char a=aa.charAt(0);
            char b=bb.charAt(0);
            try {
            s=CRG.substitution(chemin, a, b);
            resultat.setText("");
            resultat.append(s+"\n");
            CRG.Ecrire(s);
            historique.append("Substitution "+a+" par "+b+" dans le fichier :\n"+chemin+"\n"+" résultat :\n"+s+"\n");
            historique.append("\n ________________________________________________________ \n");
            } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }
            }

            }else{
            if(comboBox.getSelectedItem().toString().equals("Information data")){
            i=5;
            String chemin=fichier.getText();
            if (chemin.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir un fichier ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            try {
            s=CRG.informationData(chemin);
            resultat.setText("");
            resultat.append(s+"\n");
            CRG.Ecrire(s);
            historique.append("Information data du fichier :\n"+chemin+"\n"+" résultat :\n"+s+"\n");
            historique.append("\n ________________________________________________________ \n");
            } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }
            }
            }else{
            if(comboBox.getSelectedItem().toString().equals("Analyse en boite noir")){
            if (comboBox1.getSelectedItem().toString().equals("Code hexadecimal")){
            String chemin=fichier.getText();
            String chemin2=fichier2.getText();
            if (chemin.equals("")||chemin2.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir les deux fichiers ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            JOptionPane jop = new JOptionPane();
            String cle = jop.showInputDialog(null, "Veuillez introduire la cle", "la cle", JOptionPane.QUESTION_MESSAGE);
            try {
            s=CRG.Code_Hexa(chemin, chemin2, cle);
            resultat.setText("");
            resultat.append(s+"\n");
            CRG.Ecrire(s);
            historique.append("Code Héxa pour le fichier :\n"+chemin+"\n"+"et le fichier :\n"+chemin2+
            "\n avec la cle : "+cle
            + "\n résultat : \n"+s+"\n");
            historique.append("\n ________________________________________________________ \n");

            } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }	
            }
            }else{
            if(comboBox1.getSelectedItem().toString().equals("Code binaire")){
            String chemin=fichier.getText();
            String chemin2=fichier2.getText();
            if (chemin.equals("")||chemin2.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir les deux fichiers ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            JOptionPane jop = new JOptionPane();
            String cle = jop.showInputDialog(null, "Veuillez introduire la cle", "la cle", JOptionPane.QUESTION_MESSAGE);
            try {
            s=CRG.Code_bin(chemin, chemin2, cle);
            resultat.setText("");
            resultat.append(s+"\n");
            CRG.Ecrire(s);
            historique.append("Code binaire pour le fichier :\n"+chemin+"\n"+"et le fichier :\n"+chemin2+
            "\n avec la cle : "+cle
            + "\n résultat : \n"+s+"\n");
            historique.append("\n ________________________________________________________ \n");
            } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }	
            }	
            }
            }if(comboBox1.getSelectedItem().toString().equals("Bloc jumaux")){
            String chemin=fichier.getText();
            if (chemin.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir un fichier ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            JOptionPane jop = new JOptionPane();
            String cle = jop.showInputDialog(null, "Veuillez introduire la taille de la cle", "Taille de la cle", JOptionPane.QUESTION_MESSAGE);
            int taille=Integer.parseInt(cle);
            JOptionPane jop1 = new JOptionPane();
            String cl = jop1.showInputDialog(null, "Veuillez introduire le nombre de caracteres existant dans les deux blocs", "", JOptionPane.QUESTION_MESSAGE);
            int t =Integer.parseInt(cl);
            try {
            s=CRG.BlocsJumeaux(chemin, taille, t);
            resultat.setText("");
            resultat.append(s+"\n");
            CRG.Ecrire(s);
            historique.append("Bloc jumaux du fichier :\n"+chemin+"\n"+
            " avec la taille : "+taille+
            "\n est le nombre de caractere : \n"+t+
            "\n résultat :\n"
            +s+"\n");
            historique.append("\n ________________________________________________________ \n");
            } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }	
            }
            }
            }else{
            if(comboBox.getSelectedItem().toString().equals("XOR")){
            if(comboBox_1.getSelectedItem().toString().equals("Xor par bloc")){

            String chemin=fichier.getText();
            if (chemin.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir un fichier ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            JOptionPane jop = new JOptionPane();
            String cle = jop.showInputDialog(null, "Veuillez introduire la cle", "la cle", JOptionPane.QUESTION_MESSAGE);

            try {
            s=CRG.XorParBloc(chemin, cle);
            resultat.setText("");
            resultat.append(s+"\n");
            CRG.Ecrire(s);
            historique.append("Xor du fichier :\n"+chemin+"\n"
            +"avec la cle : "+cle+"\n"
            +" résultat :\n"+s+"\n");
            historique.append("\n ________________________________________________________ \n");
            } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }	
            }
            }else{
            if(comboBox_1.getSelectedItem().toString().equals("Xor par caractere")){
            String chemin=fichier.getText();
            if (chemin.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir un fichier ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            JOptionPane jop = new JOptionPane();
            String cle = jop.showInputDialog(null, "Veuillez introduire la cle", "la cle", JOptionPane.QUESTION_MESSAGE);

            s=CRG.xor(chemin, cle);
            resultat.setText("");
            resultat.append(s+"\n");
            CRG.Ecrire(s);
            historique.append("Xor par caractere du fichier :\n"+chemin+"\n"
            +"avec la cle : "+cle+"\n"
            +" résultat :\n"+s+"\n");
            historique.append("\n ________________________________________________________ \n");
            }	
            }	
            }
            }else{
            if (comboBox.getSelectedItem().toString().equals("EnsembleSymbolesEgaux")){

            String chemin=fichier.getText();
            String chemin2=fichier2.getText();
            if (chemin.equals("")||chemin2.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir les deux fichiers ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            try {   
            resultat.setText("");
            resultat.append("Resultat Ensemble symbole Egaux :"+"\n");
            CRG.ensembleSymboleEgaux(chemin, chemin2);

            historique.append("Ensemble symbole egaux des fichiers :\n"+chemin+"\n et \n"+chemin2+"\n");
            historique.append("\n ________________________________________________________ \n");

            } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }	
            }	
            }else{

            if (comboBox.getSelectedItem().toString().equals("LeByteMaximum")){

            String chemin=fichier.getText();
            if (chemin.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir un fichier ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            try {
            s=CRG.Max_bytes(chemin);
            resultat.setText("");
            resultat.append(s+"\n");


            historique.append("Le byte max du fichier :\n"+chemin+"\n"+" résultat :\n"+s+"\n");
            historique.append("\n ________________________________________________________ \n");

            } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }	
            }	
            }else{
            if (comboBox.getSelectedItem().toString().equals("Calculer la taille de la cle")){

            String chemin=fichier.getText();
            if (chemin.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir un fichier ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            try {
            JOptionPane jop = new JOptionPane();
            String cle = jop.showInputDialog(null, "Taille des blocs identiques", "", JOptionPane.QUESTION_MESSAGE);
            int taille=Integer.parseInt(cle);

            int r=CRG.CalculeTailleCle(chemin,taille);


            resultat.setText(r+"");

            historique.append("calcule de la taille du fichier :\n"+chemin+"\n"+" résultat :\n"+r+"\n");
            historique.append("\n ________________________________________________________ \n");

            } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }	
            }	
            }else{
            if (comboBox.getSelectedItem().toString().equals("Haut Frequence At")){

            String chemin=fichier.getText();
            if (chemin.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir un fichier ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            try {
            JOptionPane jop = new JOptionPane();
            String cle = jop.showInputDialog(null, "Taille du bloc", "", JOptionPane.QUESTION_MESSAGE);
            int t1=Integer.parseInt(cle);

            JOptionPane jop1 = new JOptionPane();
            String cl = jop1.showInputDialog(null, "La position", "", JOptionPane.QUESTION_MESSAGE);
            int t2=Integer.parseInt(cl);

            int r=CRG.HauteFrequenceAt(chemin,t1,t2);

            resultat.setText("Le byte le plus répété à la position "+t2+" est: "+r+" et en char "+(char)r);

            historique.append("Haut Frequence At du fichier :\n"+chemin+"\n"+" résultat :\n"+r+"\n");
            historique.append("\n ________________________________________________________ \n");
            } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }	
            }	
            }else{

            if (comboBox.getSelectedItem().toString().equals("Substitution par bloc")){

            String chemin=fichier.getText();
            if (chemin.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir un fichier ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            try {
            JOptionPane jop = new JOptionPane();
            String cle = jop.showInputDialog(null, "Taille du bloc", "", JOptionPane.QUESTION_MESSAGE);
            int t1=Integer.parseInt(cle);

            JOptionPane jop1 = new JOptionPane();
            String cl = jop1.showInputDialog(null, "La position", "", JOptionPane.QUESTION_MESSAGE);
            int t2=Integer.parseInt(cl);
            String aa=remplace_Field.getText();
            String bb=par_Field.getText();
            char a=aa.charAt(0);
            char b=bb.charAt(0);
            s=CRG.SubstitutionParPositionDansLeBloc(chemin,a,b,t1,t2);
            resultat.setText("");
            resultat.append(s+"\n");
            CRG.Ecrire(s);
            historique.append("Substitution "+a+" par "+b+
              " avec la taille "+t1+" et "+" la position "+t2+"\n"
              +" dans le fichier :\n"+chemin+"\n"+" résultat :\n"+s+"\n");
            historique.append("\n ________________________________________________________ \n");


            } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }	
            }	
            }else{

            if (comboBox.getSelectedItem().toString().equals("Trouver la bonne permutation")){

            String chemin=fichier.getText(); //cryptogramme
            String chemin2=fichier2.getText(); //N_grammes
            if (chemin.equals("")||chemin2.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir un fichier ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            Set<String> set= new HashSet();

            JOptionPane jop1 = new JOptionPane();
            JOptionPane jop2 = new JOptionPane();
            JOptionPane jop3 = new JOptionPane();
            String cle1 = jop1.showInputDialog(null, "Taille minimale des n_grammes", "", JOptionPane.QUESTION_MESSAGE);
            int t1=Integer.parseInt(cle1);
            String cle2 = jop2.showInputDialog(null, "Taille maximale des n_grammes", "", JOptionPane.QUESTION_MESSAGE);
            int t2=Integer.parseInt(cle2);
            String cle3 = jop3.showInputDialog(null, "Le nombre de n_grammes pour chaque taille donnée", "", JOptionPane.QUESTION_MESSAGE);
            int t3=Integer.parseInt(cle3);

            try {

            set=BPerm.SetOfMostFrequentGrammes(chemin2, t1, t2, t3);
            String cle4 = jop1.showInputDialog(null, "Veuillez donner la taille des blocs identiques qu'on doit chercher", "", JOptionPane.QUESTION_MESSAGE);
            int t4=Integer.parseInt(cle4);
            int T=CRG.CalculeTailleCle(chemin, t4);

            System.out.print("T"+T);

            int[] Bcle= BPerm.GenerationEtTest(chemin,T,set);
            String[] Tab=new String[Bcle.length];

            for(int j=0;j<Bcle.length;j++){
            resultat.append(""+Bcle[j]);

            Tab[j]=""+Bcle[j];
            }


            String S= "resultat_bonne_permutation.txt";
            String Resultat=BPerm.Permutation(chemin,Bcle,S);
            resultat.setText("");
            resultat.append(Resultat+"\n");
            resultat.append("La bonne permutation est:\n");
            for(int j=0;j<Tab.length;j++){
            resultat.append(Tab[j]);
            }
            //CRG.Ecrire(Resultat);
            historique.append("Le résultat de la bonne permutation est dans:\n"+Resultat+"\n");
            historique.append("\n ________________________________________________________ \n");


            } catch (IOException ex) {
            Logger.getLogger(principale.class.getName()).log(Level.SEVERE, null, ex);
            }

            }
            }else{

            if(comboBox.getSelectedItem().toString().equals("LigneCompar")){
            String chemin=fichier.getText();
            String chemin2=fichier2.getText();

            if (chemin.equals("")||chemin2.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir les deux fichiers ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            try {
            JOptionPane jop1 = new JOptionPane();
            String cle1 = jop1.showInputDialog(null, "1: Lines in file1 but not in file2: \n 2: Lines in file1 and in file2:", "", JOptionPane.QUESTION_MESSAGE);
            int z=Integer.parseInt(cle1);
            int T=CRG2.LigneCompar(chemin, chemin2,z);
            
            //  resultat.append("Le résultat est :"+T+"\n");
            // historique.append("Résultat de LigneCompar :"+T+"\n");
            historique.append("\n ________________________________________________________ \n");


            } catch (IOException ex) {
            Logger.getLogger(principale.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            }else{
            if(comboBox.getSelectedItem().toString().equals("fileToLines")){
            
            }else{
            if(comboBox.getSelectedItem().toString().equals("CoincidenceFinal")){
            String chemin=fichier.getText();
            if (chemin.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir un fichier ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            float f;
            try {
            FileInputStream fis1 = new FileInputStream(chemin);//ouvrir le fichier
            InputStreamReader isr1 = new InputStreamReader(fis1);			    

            int c2;
            String Texte_Etudie="";
            while ((c2 = isr1.read()) > -1)//tant que le fichier n'est pas vide 
            {
            Texte_Etudie=Texte_Etudie+(char)c2;//recuperer les caract�res 
            }
            isr1.close();



            f=CRG2.CoincidenceFinal(Texte_Etudie);
            System.out.print(f);
            resultat.setText("");
            resultat.append("Le résultat est :"+f+"\n");
            historique.append("Résultat de CoincidenceFinal :"+f+"\n");
            historique.append("\n ________________________________________________________ \n");

            } catch (IOException ex) {
            Logger.getLogger(principale.class.getName()).log(Level.SEVERE, null, ex);
            }
            } 
            }else{
            if(comboBox.getSelectedItem().toString().equals("LongeurC")){
            String chemin=fichier.getText();
            if (chemin.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir un fichier ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            ArrayList Tab = new ArrayList();
            JOptionPane jop = new JOptionPane();
            String cle = jop.showInputDialog(null, "Veuillez introduire le ICF", "ICF", JOptionPane.QUESTION_MESSAGE);
            float f = Float.parseFloat(cle);
            ArrayList Tab1 = new ArrayList();
            try {
            FileInputStream fis1 = new FileInputStream(chemin);//ouvrir le fichier
            InputStreamReader isr1 = new InputStreamReader(fis1);

            String Texte_Etudie="";
            int c2;
            while ((c2 = isr1.read()) > -1)//tant que le fichier n'est pas vide 
            {
            if (c2!=32)
            {
            Texte_Etudie=Texte_Etudie+(char)c2;//recuperer les caract�res 
            }			
            }
            isr1.close();
            float If=(float) 0.0778;
            Tab=CRG2.LongeurC(Texte_Etudie, f);

            Tab1=(ArrayList)Tab.get(1);
            } catch (IOException ex) {
            Logger.getLogger(principale.class.getName()).log(Level.SEVERE, null, ex);
            }
            resultat.setText("");
            resultat.append("Les moyennes de IC de chaque longeur de clé sont : \n"+Tab1+"\n");
            resultat.append("La longeur de la clé probable est : "+Tab.get(3));

            historique.append("Résultat de LongeurC :"+Tab+"\n");
            historique.append("\n ________________________________________________________ \n");


            }    
            }else{
            if(comboBox.getSelectedItem().toString().equals("LongeurMots")){
            String chemin=fichier.getText();
            if (chemin.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir un fichier ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            resultat.setText("");

            Hashtable LongeurMots = new Hashtable();
            try {
            LongeurMots=CRG2.LongeurMots(chemin);
            int p=0;
            resultat.append("Les  longeurs des mots et les frequences  associes sont:\n");
            for(int a=0;a<LongeurMots.size();a++)
            {
            //System.out.println("Les "+Nombre_L+"-grammes pr�sents dans le texte sont: "+(Ngramme.get(i)));
            resultat.append(""+(LongeurMots.get(a))+"\n");
            }
            } catch (IOException ex) {
            Logger.getLogger(principale.class.getName()).log(Level.SEVERE, null, ex);
            }
            //   resultat.append("Le résultat est :"+LongeurMots+"\n");
            historique.append("Résultat de LongeurMots :"+LongeurMots+"\n");
            historique.append("\n ________________________________________________________ \n");
            } 
            }else{
            if(comboBox.getSelectedItem().toString().equals("N_Grammes")){
            String chemin=fichier.getText();
            if (chemin.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir un fichier ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            JOptionPane jop1 = new JOptionPane();
            JOptionPane jop2 = new JOptionPane();
            JOptionPane jop3 = new JOptionPane();
            String cle1 = jop1.showInputDialog(null, "Si Glissant mettez 1 sinon 0", "", JOptionPane.QUESTION_MESSAGE);
            int t1=Integer.parseInt(cle1);
            String cle2 = jop2.showInputDialog(null, 
            "Type_L vaut \n"
            + "1 si l'analyse est effectue que sur les lettres, \n" +
                    
            "2 : si l'analyse est effectue que sur les chiffres, \n" +
            "3 : si l'analyse est effectue sur les chiffres et les lettres et enfin, \n" +
            "4 :si l'analyse est effectue sur tous les caracteres.", "", JOptionPane.QUESTION_MESSAGE);
                                      int t2=Integer.parseInt(cle2);
                                      String cle3 = jop3.showInputDialog(null, "1: 1-gramme\n" +
            "			 2: 2-grammes\n" +
            "			 ect", "", JOptionPane.QUESTION_MESSAGE);
            int t3=Integer.parseInt(cle3);
            Hashtable Ngramfreq = new Hashtable() ;
            try {
            Ngramfreq=CRG2.N_Grammes(chemin, t1, t2, t3);
            resultat.setText("");
            resultat.append("Le "+t3+"-grammes le + fréquents dans le texte est : "+(Ngramfreq.get((int)Ngramfreq.get(0))));
            System.out.println("Les "+t3+"-grammes présents dans le texte associes à leurs fréquences sont: \n");	
            for(int a=1;a<Ngramfreq.size();a++)
            {
            //System.out.println("Les "+Nombre_L+"-grammes pr�sents dans le texte sont: "+(Ngramme.get(i)));
            resultat.append(""+(Ngramfreq.get(a))+"\n");
            }
            //resultat.append("Le N_Grammes est :"+Ngramfreq+"\n");
            historique.append("Résultat de N_Grammes :\n");
            historique.append("\n ________________________________________________________ \n");

            } catch (IOException ex) {
            Logger.getLogger(principale.class.getName()).log(Level.SEVERE, null, ex);
            }

            }
            }else{
            if(comboBox.getSelectedItem().toString().equals("Francais")){
            String chemin=fichier.getText();
            boolean Res = false;
            if (chemin.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir un fichier ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{

            try {
            ArrayList Tab=new ArrayList();
            Tab.add("ES");
            Tab.add("DE");
            Tab.add("LE");
            Tab.add("EN");
            int f=0;
            Res = false;
            while (f<Tab.size())
            {
            String bigramF=(String)Tab.get(f);
            boolean F = CRG2.Francais(chemin, bigramF);
            Res = Res || F;
            f=f+1;
            }
            resultat.setText("");
            resultat.append("Resultat :" +Res);

            } catch (IOException ex) {
            Logger.getLogger(principale.class.getName()).log(Level.SEVERE, null, ex);
            }
         
            historique.append("Résultat de francais :"+Res+"\n");
            historique.append("\n ________________________________________________________ \n");

            } 
            }else{
            if(comboBox.getSelectedItem().toString().equals("CalculSetClePermute")){
            String chemin=fichier.getText();
            String chemin2=fichier2.getText();

            if (chemin.equals("")||chemin2.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir les deux fichiers ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            try {

            //                                                    int[] tabm=BPerm.fileToInt(chemin);
            //                                                    int[] tabc=BPerm.fileToInt(chemin2);
            Set<int []>set= BPerm.CalculSetClePermute(chemin,chemin2);

            Iterator<int[]> it= set.iterator();

            int[] tm=null;

            while(it.hasNext()){
            tm=it.next();
            for(int l=0;l<tm.length;l++){

            System.out.print((int)tm[l]);
            }
            System.out.print('\n');

            }

            } catch (IOException ex) {
            Logger.getLogger(principale.class.getName()).log(Level.SEVERE, null, ex);
            }

            }
            }else{

            if (comboBox.getSelectedItem().toString().equals("Haute Frequence")){

            String chemin=fichier.getText();
            if (chemin.equals("")){
            JOptionPane jop3 = new JOptionPane();
            jop3.showMessageDialog(null, "Veuillez choisir un fichier ... ", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            }else{
            try {
            JOptionPane jop = new JOptionPane();
            String cle = jop.showInputDialog(null, "Taille du bloc", "", JOptionPane.QUESTION_MESSAGE);
            int t1=Integer.parseInt(cle);

            cryptanalyseBoiteNoire Cbn = new cryptanalyseBoiteNoire();
            CRG.HauteFrequence(chemin,t1);



            historique.append("haut frequence du fichier :\n"+chemin+"\n");
            historique.append("\n ________________________________________________________ \n");
            } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }	


            }
            }
            }
            }
            }
            }
            }
            }
            }
            } 
            }
            }
            }
            }
            }

            }

            }
            }
            }
            }
            }
            }
            }
            }
            });

            JButton btnNewButton_3 = new JButton("");
            btnNewButton_3.setIcon(new ImageIcon(getClass().getResource("/cut_edit.gif")));
            toolBar.add(btnNewButton_3);

            JButton btnNewButton_2 = new JButton("");
            btnNewButton_2.setIcon(new ImageIcon(getClass().getResource("copy_edit_co.gif")));
            toolBar.add(btnNewButton_2);

            JButton btnNewButton_1 = new JButton("");
            btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
            });
            btnNewButton_1.setIcon(new ImageIcon(getClass().getResource("paste_edit.gif")));
            toolBar.add(btnNewButton_1);
            toolBar.add(btnNewButton);

            JButton btnNewButton_4 = new JButton("");
            btnNewButton_4.setIcon(new ImageIcon(getClass().getResource("apropos.png")));
            toolBar.add(btnNewButton_4);

            comboBox.addItemListener(new ItemState());
            comboBox1.addItemListener(new ItemState1());

            remplace_Field.setVisible(false);
            par_Field.setVisible(false);
            remplace.setVisible(false);
            par.setVisible(false);
            comboBox1.setVisible(false);
            fichier2.setVisible(false);
            btnParcourir_1.setVisible(false);
            comboBox_1.setVisible(false);
            }
            class ItemState implements ItemListener{
            public void itemStateChanged(ItemEvent e) {

            if(e.getItem().equals("Substitution")||e.getItem().equals("Substitution par bloc")){
            remplace_Field.setVisible(true);
            par_Field.setVisible(true);
            remplace.setVisible(true);
            par.setVisible(true);
            fichier2.setVisible(false);
            btnParcourir_1.setVisible(false);
            comboBox1.setVisible(false);
            }else{
            fichier2.setVisible(false);
            btnParcourir_1.setVisible(false);
            remplace_Field.setVisible(false);
            par_Field.setVisible(false);
            remplace.setVisible(false);
            par.setVisible(false);


            }
            if (e.getItem().equals("Analyse en boite noir")){
            comboBox1.setVisible(true);
            fichier2.setVisible(true);
            btnParcourir_1.setVisible(true);
            }else{
            comboBox1.setVisible(false);
            fichier2.setVisible(false);
            btnParcourir_1.setVisible(false);

            }
            if(e.getItem().equals("XOR")){
            comboBox_1.setVisible(true);

            }else{
            comboBox_1.setVisible(false);
            }
            if(e.getItem().equals("EnsembleSymbolesEgaux")||e.getItem().equals("Trouver la bonne permutation")||e.getItem().equals("LigneCompar")||e.getItem().equals("CalculSetClePermute")){
            fichier2.setVisible(true);
            btnParcourir_1.setVisible(true);
            }else{
            fichier2.setVisible(false);
            btnParcourir_1.setVisible(false);
            }
            }               
            }

            //-----------------
            class ItemState1 implements ItemListener{
            public void itemStateChanged(ItemEvent e) {
            if(e.getItem().equals("Code binaire")||e.getItem().equals("Code hexadecimal")){
            fichier2.setVisible(true);
            btnParcourir_1.setVisible(true);
            }else{
            fichier2.setVisible(false);
            btnParcourir_1.setVisible(false);
            }
            }
            }
            }
