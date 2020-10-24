package choretracker;

import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;  // Using AWT event classes and listener interfaces
import javax.swing.*;
import java.util.*;


// An AWT GUI program inherits from the top-level container java.awt.Frame
public class AWTAccumulator extends Frame {
   private JLabel lblInput;     // Declare input Label
   private JTextField tfInput;  // Declare input TextField
   private int chorePosition = 150;        // Accumulated sum, init to 0
   private int choreCount = 0;
   private JFrame frame;
   private JTextField tfInput2;
   private JButton addButton;
   private ImageIcon checkIcon = new ImageIcon("src/resources/checkIcon.png");
   private ArrayList<Chore> choreList = new ArrayList<Chore>();
   private JScrollPane scroll;
   private JPanel pane;
   private JLabel sumLbl;
   private int sum = 0;
   


  

 
   // Constructor to setup the GUI components and event handlers
   @SuppressWarnings("checkstyle")
   public AWTAccumulator() {

   

     frame = new JFrame("Chore Tracker");
         
         // "super" Frame (container) sets layout to FlowLayout, which arranges
         // the components from left-to-right, and flow to next row from top-to-bottom.

      frame.setSize(1200, 800);  // "super" Frame sets initial window size
      pane = new JPanel();
      scroll = new JScrollPane(pane);
      scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

      // Then, add the jScrollPane to your frame
      frame.getContentPane().add(scroll);
      
      pane.setVisible(true);
      pane.setLayout(null);
      pane.setSize(600, 400);
      scroll.setSize(600, 400);
      
      JLabel titlePage = new JLabel("Kayden's Chore Tracker");
      titlePage.setFont(new Font("Lucida", Font.BOLD, 34));
      titlePage.setForeground(new java.awt.Color(21, 52, 80));
      titlePage.setBounds(400, 0, 500, 50);
      pane.add(titlePage);

      lblInput = new JLabel("Enter Chore: "); // Construct Label
      lblInput.setFont(new Font("Lucida",Font.BOLD,24));
      lblInput.setForeground(new java.awt.Color(21, 52, 80));
      lblInput.setBounds(50, 100, 300, 30);
      pane.add(lblInput);                // "super" Frame container adds Label component

      lblInput = new JLabel("Enter Chore Price: "); // Construct Label
      lblInput.setFont(new Font("Lucida",Font.BOLD,24));
      lblInput.setForeground(new java.awt.Color(21, 52, 80));
      lblInput.setBounds(525, 100, 300, 30);
      pane.add(lblInput);                // "super" Frame container adds Label component

      sumLbl = new JLabel("Current Total: \n $" + sum + "/$0"); // Construct Label
      sumLbl.setFont(new Font("Lucida",Font.BOLD,24));
      sumLbl.setForeground(new java.awt.Color(21, 52, 80));
      sumLbl.setBounds(50, 200, 300, 70);
      pane.add(sumLbl);                // "super" Frame container adds Label component

      JButton addButton = new JButton("Add Chore");
      addButton.setBounds(1000, 90, 150, 50);
      addButton.setBackground(new java.awt.Color(143, 188, 219));
      pane.add(addButton);
      
      tfInput = new JTextField(10);
      tfInput.setBounds(200, 100, 200, 30); // Construct TextField
      pane.add(tfInput);                // "super" Frame adds TextField

      tfInput2 = new JTextField(10);
      tfInput2.setBounds(750, 100, 200, 30); // Construct TextField
      pane.add(tfInput2); 
 

      addButton.addActionListener(new ActionListener(){  
         public void actionPerformed(ActionEvent e){  
                 newChore();
             }  
         });  

         // "tfInput" is the source object that fires an ActionEvent upon entered.
         // The source add "this" instance as an ActionEvent listener, which provides
         //  an ActionEvent handler called actionPerformed().
         // Hitting "enter" on tfInput invokes actionPerformed().
 
                 // "super" Frame adds Label

      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      pane.setVisible(true);
      frame.setContentPane(pane);  
      frame.setResizable(false);
      frame.getContentPane().setBackground(new java.awt.Color(68, 114, 148));
      frame.setVisible(true);

   }
 

 
   @SuppressWarnings("checkstyle")
   public void newChore(){

      String numberIn = tfInput.getText();
      int cost = Integer.parseInt(tfInput2.getText());

      JCheckBox checkbox = new JCheckBox(numberIn, checkIcon);
      checkbox.setSelectedIcon(new ImageIcon("src/resources/checkIcon1.png"));
      checkbox.setBackground(new java.awt.Color(68, 114, 148));
      checkbox.setFont(new Font("Lucida",Font.BOLD,24));
      checkbox.setBounds(500,chorePosition, 200,50);
      checkbox.setForeground(new java.awt.Color(21, 52, 80));

      checkbox.addActionListener(new ActionListener(){  
         public void actionPerformed(ActionEvent e){ 
                 sumLbl.setText("Current Total: $" + getSum() + "/$" + getMaxSum());
                 if(checkbox.isSelected() == true) {
                 SMS.sendSMS(numberIn + " is complete.");
                 }
             }  
         });  

      JLabel costLabel = new JLabel("$" + cost);
      costLabel.setBounds(700, chorePosition, 100, 50);
      costLabel.setFont(new Font("Lucida",Font.BOLD,24));
      costLabel.setForeground(new java.awt.Color(21, 52, 80));

      JButton rmvButton = new JButton("Remove");
      rmvButton.setBounds(900, chorePosition, 100, 50);
      rmvButton.setBackground(new java.awt.Color(182, 33, 45));
      rmvButton.setFont(new Font("Lucida",Font.BOLD,10));
      

      Chore chore = new Chore(checkbox, costLabel, cost, rmvButton, choreCount);

     

      rmvButton.addActionListener(new ActionListener(){  
         public void actionPerformed(ActionEvent e){  
                 removeChore(chore);
             }  
         });  


      chorePosition += 50;
      pane.add(costLabel);
      pane.add(checkbox);
      pane.add(rmvButton);
      choreList.add(chore);
      choreCount++;
      frame.setContentPane(pane);
      pane.setVisible(true);
      frame.setVisible(true);
      sumLbl.setText("Current Total: \n $"  + getSum() + "/$" + getMaxSum());
     
   }


   @SuppressWarnings("checkstyle")
   public void removeChore(Chore chore) {
      chore.checkbox.setVisible(false);
      chore.costLbl.setVisible(false);
      chore.cost = 0;
      chore.rmvButton.setVisible(false);
      choreList.remove(chore);

      chorePosition = 150;        // Accumulated sum, init to 0
      choreCount--;
      sumLbl.setText("Current Total: \n $"  + getSum() + "/$" + getMaxSum());

      for(int i = 0; i <= choreList.size(); i++) {
         choreList.get(i).checkbox.setBounds(500, chorePosition, 200, 50);
         choreList.get(i).costLbl.setBounds(700, chorePosition, 100, 50);
         choreList.get(i).rmvButton.setBounds(900, chorePosition, 100, 25);
         chorePosition += 50;
      }


   }

   @SuppressWarnings("checkstyle")
   public int getSum(){
      sum = 0;
      for (int i = 0; i < choreCount; i++){
         if(choreList.get(i).cost > 0)
            if(choreList.get(i).checkbox.isSelected()){
               sum = sum + choreList.get(i).cost;
            }
      }
  
      return sum;
   }

   @SuppressWarnings("checkstyle")
   public int getMaxSum(){
      int maxSum = 0;
      for (int i = 0; i < choreCount; i++){
         if(choreList.get(i).cost > 0)
            
               maxSum = maxSum + choreList.get(i).cost;
            
      }
  
      return maxSum;
   }


}
