/**
 * Import from Java libraries 
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
/**
 * The game class that extends JFrame and implements KeyListener, ActionListener
 * and the GameInterface creates the game frame, e.g. the board and the window,
 * that holds the menu bar and score panel
 * 
 * @author (Akash Payne) ap567
 * @version 31/03/2014
 */

public class Game extends JFrame implements KeyListener,ActionListener,GameInterface
{   
    /**
     * GUI, preferences and score Variables
     */
    private DrawingPanel drawingArea;

    private JMenu fileMenu; 
    private JMenu helpMenu; 
    private JMenu difficultyMenu; 
    private JMenuItem startItem;
    private JMenuItem exitItem;
    private JMenuItem preferencesItem;
    private JMenuItem informationItem;
    private JMenuItem instructionsItem;
    private JMenuItem controlsItem;
    private JMenuItem authorItem;
    
    private Preferences preferences;
    private ShowInformation showInformation;
    
    private boolean paused = false;

    public static Scores score;
    
    public static boolean listen_keyboard = false;

    /**
     * Constructor Game (STRING url is for the background image)
     * Menu and frame are set 
     */

    public Game(String url)
    {
        super();

        preferences = null;
        
        setJMenuBar(this);
        
        score = new Scores(); // score 
        
        drawingArea = new DrawingPanel(url); // drawing panel
        
        Container container = new Container(); // Frame container 
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 5;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        container.add(drawingArea, constraints);

        constraints.gridx = 4;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridwidth = 1;
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
        container.add(score, constraints);
        
        // Frame settings
        setSize(drawingArea.getBackWidth(), drawingArea.getBackHeight());
        setTitle("CO520 BLUEJ - Snake Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        addKeyListener(this);
        
        // set pane
        setContentPane(container);
        setVisible(true);

        //sets the size
        while(drawingArea.getWidth() < drawingArea.getBackWidth())
        {
            drawingArea.setSize((drawingArea.getWidth() + 1), drawingArea.getHeight());
            this.setSize((getWidth() + 1), getHeight());
        }

        while(drawingArea.getHeight() < drawingArea.getBackHeight())
        {
            drawingArea.setSize(drawingArea.getWidth(), (drawingArea.getHeight() + 1));
            this.setSize(getWidth(), (getHeight() + 1));
        }
    }
    
    /** 
     * This method sets the menu bar that holds the features: Restart game (new game); 
     * Exit game; Change game mode and difficulty; See details about the program.
     */
    private void setJMenuBar(JFrame frame)
    {
        /**
         *Main Menu Bar
         */
        // Options Menu - new game; quit and settings
        fileMenu = new JMenu("File");        
        startItem = new JMenuItem("Start New Game");
        startItem.addActionListener(this);        
        exitItem = new JMenuItem("Quit The Game");
        exitItem.addActionListener(this);
        fileMenu.add(startItem);
        fileMenu.add(exitItem);
        
        // Difficulty Menu - settings - change difficulty
        difficultyMenu = new JMenu ("Difficulty");
        preferencesItem = new JMenuItem("Settings");
        preferencesItem.addActionListener(this);     
        difficultyMenu.add(preferencesItem);
        
        // Help Menu
        helpMenu = new JMenu ("Help");
        //JMenuItem informationItem = new JMenuItem("Information");
        // informationItem.addActionListener(this);
        JMenuItem instructionsItem = new JMenuItem("Instructions");
        instructionsItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {            
                showInstructions();
            } // opens a message dialogue with the text above
        });
        //instructionsItem.addActionListener(this);
        JMenuItem authorItem = new JMenuItem("About");
        authorItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {            
                showAboutAuthor();
            } // opens a message dialogue with the text above
        });
        //authorItem.addActionListener(this);
        JMenuItem controlsItem = new JMenuItem("Controls");
        controlsItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {            
                showControls();
            } // opens a message dialogue with the text above
        });
        //controlsItem.addActionListener(this);
       
        helpMenu.add(instructionsItem);
        helpMenu.add(controlsItem);        
        helpMenu.add(authorItem);
        // helpMenu.add(informationItem);
       
        // set menu bar
        frame.setJMenuBar(new JMenuBar());
        getJMenuBar().add(fileMenu);
        getJMenuBar().add(difficultyMenu);
        getJMenuBar().add(helpMenu);
    }
    /**
     * Start game method draws the board using the Drawing panel
     */
    public void startGame()
    {
        preferencesItem.setEnabled(false);
        if(preferences != null)
        {
            drawingArea.getTimer().setDelay(Preferences.getDifficulty());
        }
        drawingArea.getTimer().start();
    }

    /**
     * keyTyped Method reads what keys the user is entering 
     */
    public void keyTyped(KeyEvent input)
    {
    }

    /**
     * keyPressed Method reads what keys the user is entering 
     * and launches the correct function
     */
    public void keyPressed(KeyEvent input)
    {   
        if(!listen_keyboard && !paused)
        {
            Thread.yield();
        }
        else
        {
            if(input.getKeyCode() == KeyEvent.VK_ESCAPE) // exits the application
            {
                Game.score.updateHighScore(Game.score.getCurrentScore());
                System.exit(0);
            }
            else if(input.getKeyCode() == KeyEvent.VK_SPACE) // pauses the application
            {
                drawingArea.repaint();
                if(paused)
                {
                    paused = false;
                    drawingArea.getTimer().start();
                }
                else
                {
                    paused = true;
                    drawingArea.getTimer().stop();
                }
            }
            else // snake movement 
            {
                if((input.getKeyCode() == KeyEvent.VK_UP) & (drawingArea.getSnake().getBody().get(0).getDirection() != Direction.SOUTH))
                {
                    drawingArea.getSnake().getBody().get(0).setDirection(Direction.NORTH);
                }
                else if((input.getKeyCode() == KeyEvent.VK_DOWN) & (drawingArea.getSnake().getBody().get(0).getDirection() != Direction.NORTH))
                {
                    drawingArea.getSnake().getBody().get(0).setDirection(Direction.SOUTH);
                }
                else if((input.getKeyCode() == KeyEvent.VK_LEFT) & (drawingArea.getSnake().getBody().get(0).getDirection() != Direction.EAST))
                {
                    drawingArea.getSnake().getBody().get(0).setDirection(Direction.WEST);
                }
                else if((input.getKeyCode() == KeyEvent.VK_RIGHT) & (drawingArea.getSnake().getBody().get(0).getDirection() != Direction.WEST))
                {
                    drawingArea.getSnake().getBody().get(0).setDirection(Direction.EAST);
                }
            }
            listen_keyboard = false;
        }
    }
    
    /**
     * keyReleased Method reads when the user releases a key.
     */
    public void keyReleased(KeyEvent input)
    {
    }

    /**
     * Starts a new instance of the game.
     */ 
    public void startNewGame()
    {
        startGame();
    }
    
    /**
     * Quits the application.
     */ 
    public void exitGame()
    {
        System.exit(0);
    }
    
    /**
     * Shows the preferences / settings.
     */
    public void showPreferences()
    {
        preferences = new Preferences();
    }
    
    /**
     * Shows the information text
     */
    public void showInformation()
    {
        showInformation = new ShowInformation();
    }
    
    /**
     * Shows the about author message.
     */
    public void showAboutAuthor()
    {
        String about = "Author: Akash Payne (ap567) \n";
            about += " Version: Snake 1.0 \n  Date: 31/03/2014 \n";     
        JOptionPane.showMessageDialog(this,about,"About!", JOptionPane.INFORMATION_MESSAGE);
    }
        
    /**
     * Controls for the game.
     */
    public void showControls()
    {
        String controls = "*************** Controls ***************\n";
            controls += "      ~ escape : exit the application    \n";     
            controls += "      ~ space  : pause the application   \n"; 
            controls += "      ~ up     : move the snake up        \n"; 
            controls += "      ~ down   : move the snake down      \n"; 
            controls += "      ~ left   : move the snake left      \n"; 
            controls += "      ~ right  : move the snake right     \n"; 
            controls += " ***************          *************** \n";
        JOptionPane.showMessageDialog(this,controls, "Controls!", JOptionPane.PLAIN_MESSAGE);
    }   
    
    /**
     * Shows the user the Instructions.
     */
    public void showInstructions()
    {
        String rules = "***************Game Instructions***************\n";
            rules += "1. Select your game difficulty and start the game\n";
           
            rules += "2. Collect food to increase size and to score points.\n BUT, be careful of the borders!\n";
            rules += "3. AND try not to run into your own snake body,\n  as you will eat yourself and die!\n";
            rules += "                 Enjoy!!!!";
        JOptionPane.showMessageDialog(this,rules,"Game Instructions!", JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * This method finds what source the action is being performed in and then calls the correct method. 
     */
    public void actionPerformed(ActionEvent input)
    {
        if(input.getSource() == exitItem)
        {
            exitGame();
        }
        else if(input.getSource() == informationItem)
        {
            showInformation();
        }
        else if(input.getSource() == startItem)
        {
            startNewGame();
        }
        else if(input.getSource() == preferencesItem)
        {
            showPreferences();
        }
        else if(input.getSource() == authorItem)
        {
            System.out.println("a");
            showAboutAuthor();
        }
        else if(input.getSource() == instructionsItem)
        {
            System.out.println("b");
            showInstructions();
        }
        else if(input.getSource() == controlsItem)
        {
            System.out.println("c");
            showControls();
        }
        else 
        {
            System.out.println(input.getSource());
            System.out.println("???");
        }
    }
}