package choretracker;

import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;  // Using AWT event classes and listener interfaces
import javax.swing.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.awt.Image;

import uk.co.caprica.picam.Camera;
import uk.co.caprica.picam.CameraConfiguration;
import uk.co.caprica.picam.CameraException;
import uk.co.caprica.picam.CaptureFailedException;
import uk.co.caprica.picam.FilePictureCaptureHandler;
import uk.co.caprica.picam.NativeLibraryException;
import uk.co.caprica.picam.enums.AutomaticWhiteBalanceMode;
import uk.co.caprica.picam.enums.Encoding;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import static uk.co.caprica.picam.CameraConfiguration.cameraConfiguration;
import static uk.co.caprica.picam.PicamNativeLibrary.installTempLibrary;

import jssc.SerialPort;
import jssc.SerialPortException;


// An AWT GUI program inherits from the top-level container java.awt.Frame
public class AWTAccumulator extends Frame {
   private JLabel slideMenuLabel = new JLabel();     // Declare input Label
   private JTextField tfInput;  // Declare input TextField
   private int chorePosition = 150;        // Accumulated sum, init to 0
   private int choreCount = 0;
   private JFrame frame;
   private JTextField tfInput2;
   private ImageIcon checkIcon = new ImageIcon("src/resources/checkIcon.png");
   private ArrayList<Chore> choreList = new ArrayList<Chore>();
   private JScrollPane scroll;
   private JPanel pane;
   private JPanel picPane = new JPanel();
   private JPanel slidePane = new JPanel();
   private JLabel sumLbl;
   JButton slideshow = new JButton();
   JButton playButton;
   JButton pauseButton;
   JButton exitButton;
   JButton nextButton;
   JButton prevButton;
   private int sum = 0;
   int picIterator = 0;
   ActionListener nextPhoto;
   Timer t;
   SerialPort serialPort = new SerialPort("/dev/ttyACM0");
   Image img1 = Toolkit.getDefaultToolkit().getImage("src/resources/ShariPhotoAlbum/Mtn1.jpg");
   ArrayList<Image> piclist = new ArrayList<>();



   // Constructor to setup the GUI components and event handlers
   @SuppressWarnings("checkstyle")
   public AWTAccumulator() throws NativeLibraryException, SerialPortException, IOException {



    try {
    File f = new File("/home/pi/ShariSmartHome/src/resources/ShariPhotoAlbum");
    ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
    for(int i = 0; i < files.size(); i ++){
        piclist.add(i, ImageIO.read(files.get(i)));
    }
        }catch(IOException | ArrayIndexOutOfBoundsException exception) {
    exception.printStackTrace();
}

        installTempLibrary();
            System.out.println(serialPort.openPort());
            System.out.println(serialPort.setParams(9600, 8, 1, 0));

   // GroupLayout layout = new GroupLayout(this);


     frame = new JFrame("Shari's Magic Photo Album");

      frame.setSize(this.getToolkit().getScreenSize());// "super" Frame sets initial window size

      pane = new JPanel() {
        public void paintComponent(Graphics g) {
           g.drawImage(img1, 0, 0, this.getWidth(), this.getHeight(), this);
      }
    };

      scroll = new JScrollPane(pane);
      scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

      // Then, add the jScrollPane to your frame
      frame.getContentPane().add(scroll);
      pane.setLayout(null);
      pane.setVisible(true);
      pane.setSize(600, 400);
      picPane.setLayout(null);
      picPane.setVisible(false);
      picPane.setSize(600, 400);
      slidePane.setLayout(null);
      slidePane.setVisible(false);
      slidePane.setSize(600, 400);
      scroll.setSize(600, 400);

      JLabel titlePage = new JLabel("Shari's Magic Photo Album");
      titlePage.setFont(new Font("Lucida", Font.BOLD, 34));
      titlePage.setForeground(new java.awt.Color(21, 52, 80));
      titlePage.setBounds(250, 0, 700, 50);
      pane.add(titlePage);



       nextPhoto = new ActionListener(){
        public void actionPerformed(ActionEvent e){
            startShow(piclist);
            }
      };


      slideshow.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
                showButtons();
            }
        });

      JButton startButton = new JButton("Start Slideshow");
      startButton.setBounds(150, 100, 300, 50);
      startButton.setBackground(new java.awt.Color(143, 188, 219));
      pane.add(startButton);

      t = new Timer(5000, nextPhoto);

      startButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
             startShow(piclist);
                t.start();
             }
         });

         JButton weddingPhotoButton = new JButton("Wedding Photos");
         weddingPhotoButton.setBounds(150, 200, 300, 50);
         weddingPhotoButton.setBackground(new java.awt.Color(143, 188, 219));
         pane.add(weddingPhotoButton);


         weddingPhotoButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                startShow(piclist);
                   t.start();
                }
            });

            JButton petPhotoButton = new JButton("Mowgli, Kaila, and Hagrid");
            petPhotoButton.setBounds(150, 300, 300, 50);
            petPhotoButton.setBackground(new java.awt.Color(143, 188, 219));
            pane.add(petPhotoButton);


            petPhotoButton.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                   startShow(piclist);
                      t.start();
                   }
               });


         playButton = new JButton("Resume");
         playButton.setBounds(150, 100, 200, 50);
         playButton.setBackground(new java.awt.Color(143, 188, 219));
         picPane.add(playButton);


         playButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    t.start();
                    frame.setContentPane(slidePane);
                    frame.setVisible(true);

                }
            });

            pauseButton = new JButton("Pause");
            pauseButton.setBounds(400, 100, 200, 50);
            pauseButton.setBackground(new java.awt.Color(143, 188, 219));
            picPane.add(pauseButton);


            pauseButton.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                        t.stop();
                        frame.setContentPane(slidePane);
                        frame.setVisible(true);
                   }
               });

               exitButton = new JButton("Exit");
               exitButton.setBounds(650, 100, 200, 50);
               exitButton.setBackground(new java.awt.Color(143, 188, 219));
               picPane.add(exitButton);


               exitButton.addActionListener(new ActionListener(){
                  public void actionPerformed(ActionEvent e){
                            showMenu();
                      }
                  });

            nextButton = new JButton("Next");
            nextButton.setBounds(650, 300, 200, 50);
            nextButton.setBackground(new java.awt.Color(143, 188, 219));
            picPane.add(nextButton);


            nextButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                            t.start();
                            frame.setContentPane(slidePane);
                            frame.setVisible(true);

                        }
                    });

            prevButton = new JButton("Previous");
            prevButton.setBounds(150, 300, 200, 50);
            prevButton.setBackground(new java.awt.Color(143, 188, 219));
            picPane.add(prevButton);


            prevButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                            t.start();
                            frame.setContentPane(slidePane);
                            frame.setVisible(true);

                        }
                    });

                picPane.add(nextButton);
                picPane.add(prevButton);
                picPane.add(playButton);
                picPane.add(pauseButton);
                 picPane.add(exitButton);


        /* JButton dimCreepButton = new JButton("Dim Creep");
         dimCreepButton.setBounds(150, 200, 300, 50);
         dimCreepButton.setBackground(new java.awt.Color(143, 188, 219));
         pane.add(dimCreepButton);

         dimCreepButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    //SnewChore();
                    dimCreepLight();
                }
            });

         JButton creepButton = new JButton("Creep");
         creepButton.setBounds(150, 300, 300, 50);
         creepButton.setBackground(new java.awt.Color(143, 188, 219));
         pane.add(creepButton);

         creepButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    //SnewChore();
                    creepLight();
                }
            });

         JButton cyclonButton = new JButton("Cyclon");
         cyclonButton.setBounds(150, 400, 300, 50);
         cyclonButton.setBackground(new java.awt.Color(143, 188, 219));
         pane.add(cyclonButton);

         cyclonButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    //SnewChore();
                    cyclonLight();
                }
            });

        JButton pictureButton = new JButton("Take Photo");
        pictureButton.setBounds(150, 500, 300, 50);
        pictureButton.setBackground(new java.awt.Color(143, 188, 219));
        pane.add(pictureButton);


        pictureButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)  {
                    //newChore();

                    takePicture();

                }
            });

            JButton clapButton = new JButton("Clap Feature");
            clapButton.setBounds(550, 100, 200, 50);
            clapButton.setBackground(new java.awt.Color(143, 188, 219));
            pane.add(clapButton);


            clapButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)  {
                        //newChore();

                        setClap();

                    }
                });*/


           // "tfInput" is the source object that fires an ActionEvent upon entered.
         // The source add "this" instance as an ActionEvent listener, which provides
         //  an ActionEvent handler called actionPerformed().
         // Hitting "enter" on tfInput invokes actionPerformed().

                 // "super" Frame adds Label

      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      pane.setVisible(true);
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      frame.setUndecorated(true);
      frame.setVisible(true);
      frame.setContentPane(pane);
      frame.setVisible(true);

   }

   @SuppressWarnings("checkstyle")

   public void showButtons(){
       Image tempImage = scaleImage(piclist.get(picIterator),1200,600);

    ImageIcon icon = new ImageIcon(tempImage);
    slideMenuLabel.setIcon(icon);
    slideMenuLabel.setBounds(0,0,1200,600);
    t.stop();
    slideMenuLabel.setIcon(icon);
    slideMenuLabel.setBounds(0,0,1200,600);
    picPane.setVisible(true);
    picPane.add(slideMenuLabel);
    frame.setContentPane(picPane);
    frame.setVisible(true);


   }

   public void showMenu(){
    pane.setVisible(true);
    frame.setContentPane(pane);
    frame.setVisible(true);
   }

   public void startShow(ArrayList<Image> piclist) {

     picIterator++;
       if( picIterator == piclist.size()){
        picIterator = 0;
       }

    Image tempImage = scaleImage(piclist.get(picIterator),1200,600);
    ImageIcon icon = new ImageIcon(tempImage);
    slideshow.setIcon(icon);
    slideshow.setBounds(0,0,1200,600);
    slidePane.add(slideshow);
    frame.pack();
    slidePane.setVisible(true);
    pane.setVisible(false);
    frame.setContentPane(slidePane);
    frame.setVisible(true);
   }

   public void setClap()  {
    try{
    serialPort.writeInt(99);

    }
    catch(SerialPortException e){
        e.printStackTrace();
    }
}
   public void whiteLight()  {
       try{
       serialPort.writeInt(3);
       }
       catch(SerialPortException e){
           e.printStackTrace();
       }
   }

   public void cyclonLight()  {
        try{
        serialPort.writeInt(1);
        }
        catch(SerialPortException e){
            e.printStackTrace();
        }
    }
public void dimCreepLight()  {
    try{
        serialPort.writeInt(2);
    }
    catch(SerialPortException e){
        e.printStackTrace();
    }
}
public void creepLight()  {
    try{
        serialPort.writeInt(0);
    }
    catch(SerialPortException e){
        e.printStackTrace();
    }
}

   public void takePicture(){


    CameraConfiguration config = cameraConfiguration()
    .width(1920)
    .height(1080)
    .automaticWhiteBalance(AutomaticWhiteBalanceMode.AUTO)
    .encoding(Encoding.PNG);
    try (Camera camera = new Camera(config)) {
        camera.takePicture(new FilePictureCaptureHandler(new File("photo.png")));
    }
    catch (CameraException e) {
        e.printStackTrace();
    }
    catch (CaptureFailedException e) {
        e.printStackTrace();
    }

    ImageIcon wPic = new ImageIcon("photo.png");

    Image scaled = scaleImage(wPic.getImage(), 320, 240);

    ImageIcon scaledIcon = new ImageIcon(scaled);
    JLabel wIcon = new JLabel(scaledIcon);
    wIcon.setBounds(600, 300, 320, 240 );
    pane.add(wIcon);
    frame.pack();
    pane.setVisible(true);
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

   private Image scaleImage(Image image, int w, int h) {

    Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);

    return scaled;
}


}
