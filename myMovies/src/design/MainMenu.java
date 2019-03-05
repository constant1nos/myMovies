/*
 * Κλιάνης Χρήστος - Μπατζώνης Κωνσταντίνος - Σερβοζλίδης Γιώργος - Χαντζή Στεφανία
 */
package design;

import communication.CommunicationWorker;
import controller.MovieController;
//import entity.Movie;
import java.awt.CardLayout;
import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
//import java.beans.Statement;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
//import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dinob
 */
public class MainMenu extends java.awt.Frame {

    BufferedImage img = null;
    int xMouse, yMouse;

    /**
     * Creates new form MainMenu
     */
    public MainMenu() throws MalformedURLException, IOException {
        /* Borderless window */
        //this.setUndecorated(true);

        initComponents();

        // fill image in jlabel for general background //
        try {
            img = ImageIO.read(new File("src/resources/bckgnd.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(backGroundImage.getWidth(), backGroundImage.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        backGroundImage.setIcon(imageIcon);
        // end of function //

        // change panel's opacity and color //
        // Αρχικοποίηση του χρώματος και της διαφάνειας των κυριώς panels //
        mainPanelHome.setBackground(new Color(0, 204, 102, 40));
        mainPanelFavorite.setBackground(new Color(0, 204, 102, 40));
        mainPanelStatistics.setBackground(new Color(0, 204, 102, 40));
        mainPanelSearch.setBackground(new Color(0, 204, 102, 40));
        //homeMainPanel.setBackground(new Color(0,204,102,50));
        //CardLayout cardTest = (CardLayout)mainPanel.getLayout();
        //cardTest.show(mainPanel, "homeMainPanel");

        // Εμφάνιση του αρχικού optionsBarPanel //
        CardLayout card = (CardLayout) optionsBarPanel.getLayout();
        card.show(optionsBarPanel, "homePanel");

        // Δοκιμαστική προβολή δεδομένων μιας ταινίας για εμφάνιση στην αρχική οθόνη //
        // Θα δημιουργηθεί μέθοδος (σύντομα) η οποία θα διαβάζει από τη βάση μια ταινία //
        // και θα εμφανίζει στοιχεία και εικόνα στην οθόνη //
        MovieController mc = new MovieController();
        // TODO: Προσθήκη Exception σε περίπτωση που δεν υπάρχουν δεδομένα στη βάση
//        Movie movie = mc.getMovie(260513); //ΠΡΟΣΟΧΗ! Αν η βάση δεδομένων είναι κενή, εδώ θα έχετε σφάλμα.
  //      movieTitle.setText(movie.getTitle());
    //    movieOverview.setText(movie.getOverview());
        URL url = new URL("https://image.tmdb.org/t/p/w200//l7GqbzkJwowYRIXAtUz2iCPi64a.jpg");
        Image image = ImageIO.read(url);
        ImageIcon icon = new ImageIcon(image);
        posterLabel.setIcon(icon);

        // Αλλαγή χρώματος background στα buttons, όταν ο κέρσορας είναι πάνω τους //
        rolloverButton(homeButton);
        rolloverButton(retrieveButton);
        rolloverButton(statisticsButton);
        rolloverButton(favoriteButton);
        rolloverButton(exitButton);
        rolloverButton(searchButton);
    }

    /* Function that changes attributes of button when mouse is over */
    private void rolloverButton(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            Color oldcolor1;
            Color oldcolor2;

            public void mouseEntered(MouseEvent me) {
                if (button == homeButton) {
                    infoLabel.setText("Αρχική");
                } else if (button == retrieveButton) {
                    infoLabel.setText("Ανάκτηση και Αποθήκευση Δεδομένων");
                } else if (button == favoriteButton) {
                    infoLabel.setText("Διαχείριση Λιστών Αγαπημένων Ταινιών");
                } else if (button == searchButton) {
                    infoLabel.setText("Αναζήτηση Ταινιών");
                } else if (button == statisticsButton) {
                    infoLabel.setText("Στατιστικά");
                } else if (button == exitButton) {
                    infoLabel.setText("Έξοδος Εφαρμογής");
                }
                oldcolor1 = button.getForeground();
                oldcolor2 = button.getBackground();
                button.setForeground(new Color(0, 33, 33));
                button.setBackground(new Color(0, 102, 51));
            }

            public void mouseExited(MouseEvent me) {
                button.setForeground(oldcolor1);
                button.setBackground(oldcolor2);
                infoLabel.setText("");
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        upperBar = new javax.swing.JLayeredPane();
        infoLabel = new javax.swing.JLabel();
        tmdbLabel = new javax.swing.JLabel();
        backGroundPanel = new javax.swing.JPanel();
        backGroundImage = new javax.swing.JLabel();
        sideMenuBar = new javax.swing.JPanel();
        homeButton = new javax.swing.JButton();
        retrieveButton = new javax.swing.JButton();
        favoriteButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        statisticsButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        logoLabel = new javax.swing.JLabel();
        optionsBarPanel = new javax.swing.JPanel();
        homeOptionsPanel = new javax.swing.JPanel();
        favoriteOptionsPanel = new javax.swing.JPanel();
        createListButton = new javax.swing.JButton();
        editListButton = new javax.swing.JButton();
        deleteListButton = new javax.swing.JButton();
        listScrollPane = new javax.swing.JScrollPane();
        favoriteList = new javax.swing.JList<>();
        searchOptionsPanel = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        createListButton1 = new javax.swing.JButton();
        createListButton2 = new javax.swing.JButton();
        statisticsOptionsPanel = new javax.swing.JPanel();
        topTenButton = new javax.swing.JButton();
        topTenPerListButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        mainPanelHome = new javax.swing.JPanel();
        posterLabel = new javax.swing.JLabel();
        movieTitle = new javax.swing.JLabel();
        movieScrollPane = new javax.swing.JScrollPane();
        movieOverview = new javax.swing.JTextArea();
        mainPanelFavorite = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        mainPanelSearch = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        mainPanelStatistics = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        topTenTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(51, 51, 51));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        upperBar.setBackground(new java.awt.Color(0, 0, 0));
        upperBar.setOpaque(true);

        infoLabel.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        infoLabel.setForeground(new java.awt.Color(255, 255, 255));
        infoLabel.setText("my Movies");

        tmdbLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/tmdb_small.png"))); // NOI18N
        tmdbLabel.setText("jLabel1");

        upperBar.setLayer(infoLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        upperBar.setLayer(tmdbLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout upperBarLayout = new javax.swing.GroupLayout(upperBar);
        upperBar.setLayout(upperBarLayout);
        upperBarLayout.setHorizontalGroup(
            upperBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperBarLayout.createSequentialGroup()
                .addContainerGap(515, Short.MAX_VALUE)
                .addComponent(infoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addContainerGap(452, Short.MAX_VALUE))
            .addGroup(upperBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(upperBarLayout.createSequentialGroup()
                    .addComponent(tmdbLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 943, Short.MAX_VALUE)))
        );
        upperBarLayout.setVerticalGroup(
            upperBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperBarLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(infoLabel)
                .addGap(5, 5, 5))
            .addGroup(upperBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tmdbLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
        );

        add(upperBar, java.awt.BorderLayout.NORTH);

        backGroundPanel.setBackground(new java.awt.Color(255, 255, 255));
        backGroundPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                backGroundPanelComponentResized(evt);
            }
        });

        sideMenuBar.setBackground(new java.awt.Color(21, 21, 21));
        sideMenuBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        homeButton.setBackground(new java.awt.Color(0, 0, 0));
        homeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/home_white_18dp.png"))); // NOI18N
        homeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });
        sideMenuBar.add(homeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 50, 70, 50));

        retrieveButton.setBackground(new java.awt.Color(0, 0, 0));
        retrieveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/retrieve_white_18dp.png"))); // NOI18N
        retrieveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retrieveButtonActionPerformed(evt);
            }
        });
        sideMenuBar.add(retrieveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 105, 70, 50));

        favoriteButton.setBackground(new java.awt.Color(0, 0, 0));
        favoriteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/favorite_white_18dp.png"))); // NOI18N
        favoriteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButtonActionPerformed(evt);
            }
        });
        sideMenuBar.add(favoriteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 160, 70, 50));

        searchButton.setBackground(new java.awt.Color(0, 0, 0));
        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/search_white_18dp.png"))); // NOI18N
        searchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        sideMenuBar.add(searchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 215, 70, 50));

        statisticsButton.setBackground(new java.awt.Color(0, 0, 0));
        statisticsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/statistics_white_18dp.png"))); // NOI18N
        statisticsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statisticsButtonActionPerformed(evt);
            }
        });
        sideMenuBar.add(statisticsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 270, 70, 50));

        exitButton.setBackground(new java.awt.Color(0, 0, 0));
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/exit_white_18dp.png"))); // NOI18N
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        sideMenuBar.add(exitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 325, 70, 50));

        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/app_logo_small.png"))); // NOI18N
        sideMenuBar.add(logoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 474, 80, 50));

        optionsBarPanel.setBackground(new java.awt.Color(21, 21, 21));
        optionsBarPanel.setLayout(new java.awt.CardLayout());

        homeOptionsPanel.setBackground(new java.awt.Color(21, 21, 21));

        javax.swing.GroupLayout homeOptionsPanelLayout = new javax.swing.GroupLayout(homeOptionsPanel);
        homeOptionsPanel.setLayout(homeOptionsPanelLayout);
        homeOptionsPanelLayout.setHorizontalGroup(
            homeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 961, Short.MAX_VALUE)
        );
        homeOptionsPanelLayout.setVerticalGroup(
            homeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );

        optionsBarPanel.add(homeOptionsPanel, "homePanel");

        favoriteOptionsPanel.setBackground(new java.awt.Color(21, 21, 21));

        createListButton.setBackground(new java.awt.Color(0, 0, 0));
        createListButton.setForeground(new java.awt.Color(0, 204, 102));
        createListButton.setText("Δημιουργία");

        editListButton.setBackground(new java.awt.Color(0, 0, 0));
        editListButton.setForeground(new java.awt.Color(0, 204, 102));
        editListButton.setText("Επεξεργασία");

        deleteListButton.setBackground(new java.awt.Color(0, 0, 0));
        deleteListButton.setForeground(new java.awt.Color(0, 204, 102));
        deleteListButton.setText("Διαγραφή");

        favoriteList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listScrollPane.setViewportView(favoriteList);

        javax.swing.GroupLayout favoriteOptionsPanelLayout = new javax.swing.GroupLayout(favoriteOptionsPanel);
        favoriteOptionsPanel.setLayout(favoriteOptionsPanelLayout);
        favoriteOptionsPanelLayout.setHorizontalGroup(
            favoriteOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(favoriteOptionsPanelLayout.createSequentialGroup()
                .addGap(275, 275, 275)
                .addComponent(listScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(favoriteOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(createListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        favoriteOptionsPanelLayout.setVerticalGroup(
            favoriteOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(favoriteOptionsPanelLayout.createSequentialGroup()
                .addGroup(favoriteOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(favoriteOptionsPanelLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(createListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(editListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(deleteListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(favoriteOptionsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(listScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );

        optionsBarPanel.add(favoriteOptionsPanel, "favoritePanel");

        searchOptionsPanel.setBackground(new java.awt.Color(21, 21, 21));
        searchOptionsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        searchOptionsPanel.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(249, 40, -1, -1));

        jTextField2.setText("Έτος Κυκλοφορίας");
        searchOptionsPanel.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(429, 42, -1, -1));

        createListButton1.setBackground(new java.awt.Color(0, 0, 0));
        createListButton1.setForeground(new java.awt.Color(0, 204, 102));
        createListButton1.setText("Αναζήτηση");
        searchOptionsPanel.add(createListButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 180, 25));

        createListButton2.setBackground(new java.awt.Color(0, 0, 0));
        createListButton2.setForeground(new java.awt.Color(0, 204, 102));
        createListButton2.setText("Καθαρισμός Κριτηρίων");
        searchOptionsPanel.add(createListButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 180, 25));

        optionsBarPanel.add(searchOptionsPanel, "searchPanel");

        statisticsOptionsPanel.setBackground(new java.awt.Color(21, 21, 21));
        statisticsOptionsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topTenButton.setBackground(new java.awt.Color(0, 0, 0));
        topTenButton.setForeground(new java.awt.Color(0, 204, 102));
        topTenButton.setText("Οι Καλύτερες 10 Ταινίες");
        topTenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topTenButtonActionPerformed(evt);
            }
        });
        statisticsOptionsPanel.add(topTenButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 240, 25));

        topTenPerListButton.setBackground(new java.awt.Color(0, 0, 0));
        topTenPerListButton.setForeground(new java.awt.Color(0, 204, 102));
        topTenPerListButton.setText("Οι Καλύτερες 10 Ταινίες ανά Λίστα");
        statisticsOptionsPanel.add(topTenPerListButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 240, 25));

        optionsBarPanel.add(statisticsOptionsPanel, "statisticsPanel");

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.CardLayout());

        mainPanelHome.setBackground(new java.awt.Color(102, 102, 102));

        posterLabel.setToolTipText("");

        movieTitle.setForeground(new java.awt.Color(255, 255, 255));
        movieTitle.setText("jLabel2");

        movieOverview.setBackground(new java.awt.Color(21, 21, 21));
        movieOverview.setColumns(20);
        movieOverview.setForeground(new java.awt.Color(255, 255, 255));
        movieOverview.setLineWrap(true);
        movieOverview.setRows(5);
        movieScrollPane.setViewportView(movieOverview);

        javax.swing.GroupLayout mainPanelHomeLayout = new javax.swing.GroupLayout(mainPanelHome);
        mainPanelHome.setLayout(mainPanelHomeLayout);
        mainPanelHomeLayout.setHorizontalGroup(
            mainPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelHomeLayout.createSequentialGroup()
                .addContainerGap(179, Short.MAX_VALUE)
                .addComponent(posterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(mainPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelHomeLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(movieTitle))
                    .addGroup(mainPanelHomeLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(movieScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(197, 197, 197))
        );
        mainPanelHomeLayout.setVerticalGroup(
            mainPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelHomeLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(mainPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(posterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelHomeLayout.createSequentialGroup()
                        .addComponent(movieTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(movieScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        mainPanel.add(mainPanelHome, "homeCard");
        mainPanelHome.getAccessibleContext().setAccessibleName("");

        mainPanelFavorite.setBackground(new java.awt.Color(102, 102, 102));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("mainPanelFavorite: Εδώ μπορείτε να προσθέσετε πίνακες ή μηνύματα προς τον χρήστη");

        javax.swing.GroupLayout mainPanelFavoriteLayout = new javax.swing.GroupLayout(mainPanelFavorite);
        mainPanelFavorite.setLayout(mainPanelFavoriteLayout);
        mainPanelFavoriteLayout.setHorizontalGroup(
            mainPanelFavoriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 952, Short.MAX_VALUE)
            .addGroup(mainPanelFavoriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelFavoriteLayout.createSequentialGroup()
                    .addContainerGap(265, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(179, Short.MAX_VALUE)))
        );
        mainPanelFavoriteLayout.setVerticalGroup(
            mainPanelFavoriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 448, Short.MAX_VALUE)
            .addGroup(mainPanelFavoriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelFavoriteLayout.createSequentialGroup()
                    .addContainerGap(217, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addContainerGap(217, Short.MAX_VALUE)))
        );

        mainPanel.add(mainPanelFavorite, "favoriteCard");
        mainPanelFavorite.getAccessibleContext().setAccessibleName("");

        mainPanelSearch.setBackground(new java.awt.Color(102, 102, 102));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("mainPanelSearch: Εδώ μπορείτε να προσθέσετε πίνακες ή μηνύματα προς τον χρήστη");

        javax.swing.GroupLayout mainPanelSearchLayout = new javax.swing.GroupLayout(mainPanelSearch);
        mainPanelSearch.setLayout(mainPanelSearchLayout);
        mainPanelSearchLayout.setHorizontalGroup(
            mainPanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 952, Short.MAX_VALUE)
            .addGroup(mainPanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainPanelSearchLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        mainPanelSearchLayout.setVerticalGroup(
            mainPanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 448, Short.MAX_VALUE)
            .addGroup(mainPanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainPanelSearchLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        mainPanel.add(mainPanelSearch, "card5");

        mainPanelStatistics.setBackground(new java.awt.Color(102, 102, 102));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("mainPanelStatistics: Εδώ μπορείτε να προσθέσετε πίνακες ή μηνύματα προς τον χρήστη");

        topTenTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Όνομσ Ταινίας", "Βαθμολογία"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(topTenTable);

        javax.swing.GroupLayout mainPanelStatisticsLayout = new javax.swing.GroupLayout(mainPanelStatistics);
        mainPanelStatistics.setLayout(mainPanelStatisticsLayout);
        mainPanelStatisticsLayout.setHorizontalGroup(
            mainPanelStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelStatisticsLayout.createSequentialGroup()
                .addGap(0, 259, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 154, Short.MAX_VALUE))
        );
        mainPanelStatisticsLayout.setVerticalGroup(
            mainPanelStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelStatisticsLayout.createSequentialGroup()
                .addGroup(mainPanelStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelStatisticsLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelStatisticsLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 174, Short.MAX_VALUE))
        );

        mainPanel.add(mainPanelStatistics, "statisticsCard");

        javax.swing.GroupLayout backGroundPanelLayout = new javax.swing.GroupLayout(backGroundPanel);
        backGroundPanel.setLayout(backGroundPanelLayout);
        backGroundPanelLayout.setHorizontalGroup(
            backGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGroundPanelLayout.createSequentialGroup()
                .addComponent(sideMenuBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backGroundPanelLayout.createSequentialGroup()
                        .addComponent(optionsBarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addGroup(backGroundPanelLayout.createSequentialGroup()
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(backGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(backGroundImage, javax.swing.GroupLayout.DEFAULT_SIZE, 1048, Short.MAX_VALUE))
        );
        backGroundPanelLayout.setVerticalGroup(
            backGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(optionsBarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(backGroundPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(sideMenuBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(backGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(backGroundImage, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE))
        );

        add(backGroundPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm
    /* Move window when dragged. Useful if setUndecorated = true */
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) optionsBarPanel.getLayout();
        card.show(optionsBarPanel, "homePanel");
        mainPanelHome.setVisible(true);
        mainPanelFavorite.setVisible(false);
        mainPanelStatistics.setVisible(false);
        mainPanelSearch.setVisible(false);
    }//GEN-LAST:event_homeButtonActionPerformed

    private void statisticsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statisticsButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) optionsBarPanel.getLayout();
        card.show(optionsBarPanel, "statisticsPanel");
        mainPanelHome.setVisible(false);
        mainPanelFavorite.setVisible(false);
        mainPanelStatistics.setVisible(true);
        mainPanelSearch.setVisible(false);
        topTenTable.setVisible(false);
    }//GEN-LAST:event_statisticsButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void retrieveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retrieveButtonActionPerformed
        // TODO add your handling code here:
        CommunicationWorker cm = new CommunicationWorker();
        cm.execute();
    }//GEN-LAST:event_retrieveButtonActionPerformed

    private void favoriteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) optionsBarPanel.getLayout();
        card.show(optionsBarPanel, "favoritePanel");
        mainPanelHome.setVisible(false);
        mainPanelFavorite.setVisible(true);
        mainPanelStatistics.setVisible(false);
        mainPanelSearch.setVisible(false);      
    }//GEN-LAST:event_favoriteButtonActionPerformed

    /* This method is used to scale the background image when jPanel dimensions change */
    private void backGroundPanelComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_backGroundPanelComponentResized
        // TODO add your handling code here:
        if (this.getExtendedState() == 0) {
            backGroundImage.setSize(1048, 536);
        }
        Image dimg = img.getScaledInstance(backGroundImage.getWidth(), backGroundImage.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        backGroundImage.setIcon(imageIcon);
        // System.out.println("width: "+backGroundImage.getWidth()+"Height: "+backGroundImage.getHeight());
    }//GEN-LAST:event_backGroundPanelComponentResized

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) optionsBarPanel.getLayout();
        card.show(optionsBarPanel, "searchPanel");
        mainPanelHome.setVisible(false);
        mainPanelFavorite.setVisible(false);
        mainPanelStatistics.setVisible(false);
        mainPanelSearch.setVisible(true);
    }//GEN-LAST:event_searchButtonActionPerformed
    //δημιουργία μεθόδου για πραγματοποίηση του CONNECTION με την βάση δεδομένων
    public Connection getConnection(){
        Connection con=null;
        try{
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/myMouvies","root","admin");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return con;
    }
    
    private void topTenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topTenButtonActionPerformed
        // TODO add your handling code here:
       topTenTable.setVisible(true);        //Εμφάνιση πίνακα 10 καλύτερων ταινιών
       DefaultTableModel tmodel;   //Δημιοργία default Table για την πρόσθεση στοιχείων στον πίνακα που δημιοργήσαμε
       tmodel=(DefaultTableModel) topTenTable.getModel();
       
       try{
       Connection con = getConnection();
       java.sql.Statement pst = con.createStatement();
       ResultSet rs = pst.executeQuery("SELECT * FROM movie");
       
       ArrayList<String> stListO=new ArrayList<String>();  //Δημιουργία λίστας με τα ονόματα των Ταινιών από MOVIE δαταβασε
       ArrayList<Double> stListN=new ArrayList<Double>();  //Δημιουργία λίστας με τα RATINGS των Ταινιών
       
            while(rs.next()){    
                    stListO.add(rs.getString("TITLE"));
                    stListN.add(rs.getDouble("RATING"));
            }
            int n = stListN.size();         //Χρήση bubble sort για ταξινόμηση των λιστών stListO και stListN 
                for (int i = 0; i < n; i++) {
                    for (int j = 1; j < (n - i); j++) {
                        if (stListN.get(j - 1) < stListN.get(j)) {
//Κατανέμουμε κατά φθίνουσα με RATINGS
                        double tempN = stListN.get(j - 1);
                        stListN.set(j-1, stListN.get(j));
                        stListN.set(j,tempN);
//Κατανέμουμε κατά φθίνουσα με TITLES σύμφωνα με τα RATINGS
                        String tempS = stListO.get(j - 1);
                        stListO.set(j-1, stListO.get(j));
                        stListO.set(j,tempS);
                        }
                    }
                }
//Εισαγωγή λίστας καλύτερων 10 ταινιών στον πίνακα που δημιουργήσαμε.
            for(int i=0; i<10; i++){
                tmodel.setValueAt(stListO.get(i), i, 0);
                tmodel.setValueAt(stListN.get(i), i, 1);
            }
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
       
    }//GEN-LAST:event_topTenButtonActionPerformed
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backGroundImage;
    private javax.swing.JPanel backGroundPanel;
    private javax.swing.JButton createListButton;
    private javax.swing.JButton createListButton1;
    private javax.swing.JButton createListButton2;
    private javax.swing.JButton deleteListButton;
    private javax.swing.JButton editListButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton favoriteButton;
    private javax.swing.JList<String> favoriteList;
    private javax.swing.JPanel favoriteOptionsPanel;
    private javax.swing.JButton homeButton;
    private javax.swing.JPanel homeOptionsPanel;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JScrollPane listScrollPane;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel mainPanelFavorite;
    private javax.swing.JPanel mainPanelHome;
    private javax.swing.JPanel mainPanelSearch;
    private javax.swing.JPanel mainPanelStatistics;
    private javax.swing.JTextArea movieOverview;
    private javax.swing.JScrollPane movieScrollPane;
    private javax.swing.JLabel movieTitle;
    private javax.swing.JPanel optionsBarPanel;
    private javax.swing.JLabel posterLabel;
    private javax.swing.JButton retrieveButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JPanel searchOptionsPanel;
    private javax.swing.JPanel sideMenuBar;
    private javax.swing.JButton statisticsButton;
    private javax.swing.JPanel statisticsOptionsPanel;
    private javax.swing.JLabel tmdbLabel;
    private javax.swing.JButton topTenButton;
    private javax.swing.JButton topTenPerListButton;
    private javax.swing.JTable topTenTable;
    private javax.swing.JLayeredPane upperBar;
    // End of variables declaration//GEN-END:variables
}
