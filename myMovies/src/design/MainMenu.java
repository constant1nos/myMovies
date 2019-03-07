/*
 * Κλιάνης Χρήστος - Μπατζώνης Κωνσταντίνος - Σερβοζλίδης Γιώργος - Χαντζή Στεφανία
 */
package design;

import java.awt.event.KeyEvent;
import communication.CommunicationWorker;
import controller.FavoriteListController;
import controller.MovieController;
import entity.FavoriteList;
import entity.Genre;
import entity.Movie;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.persistence.NoResultException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dinob
 */
public class MainMenu extends java.awt.Frame {

    private int userInput;  // κρατάει τη χρονιά που εισάγει ο χρήστης στην αναζήτηση ταινιών
    private MovieController mc = new MovieController();
    private FavoriteListController flc = new FavoriteListController();
    private ImageIcon bckgndImage = new ImageIcon("src/resources/bckgnd.jpg");
    //private String genreSelection;
    private int comboBoxActionCounter;     // Μετρητής πρόσβασης στη μέθοδο favoriteListComboBoxActionPerformed
    /**
     * Creates new form MainMenu
     * @throws java.net.MalformedURLException
     */
    public MainMenu() throws MalformedURLException, IOException, NoResultException {
        /* Borderless window */
        //this.setUndecorated(true);

        /* Αρχικοποίηση components της εφαρμογής. Δημιουργείται αυτόματα */
        initComponents();
        /* Αρχικοποίηση του χρώματος και της διαφάνειας των κυριώς panels */
        mainPanelHome.setBackground(new Color(0, 204, 102, 40));
        mainPanelFavorite.setBackground(new Color(0, 204, 102, 40));
        mainPanelStatistics.setBackground(new Color(0, 204, 102, 40));
        mainPanelSearch.setBackground(new Color(0, 204, 102, 40));

        /* Εμφάνιση του αρχικού optionsBarPanel */
        CardLayout card = (CardLayout) optionsBarPanel.getLayout();
        card.show(optionsBarPanel, "homePanel");

        /* 
         * Δοκιμαστική προβολή δεδομένων μιας ταινίας για εμφάνιση στην αρχική οθόνη 
         * Θα δημιουργηθεί μέθοδος (σύντομα) η οποία θα διαβάζει από τη βάση μια ταινία 
         * και θα εμφανίζει στοιχεία και εικόνα στην οθόνη 
         */
        try{
            // Προσθήκη Exception σε περίπτωση που δεν υπάρχουν δεδομένα στη βάση
            Movie movie = mc.getMovie(9806);
            movieTitle.setText(movie.getTitle());
            movieOverview.setText(movie.getOverview());
            URL url = new URL("https://image.tmdb.org/t/p/w200//l7GqbzkJwowYRIXAtUz2iCPi64a.jpg");
            Image image = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(image);
            posterLabel.setIcon(icon);
        }
        // Προσθήκη Exception σε περίπτωση που δεν υπάρχουν δεδομένα στη βάση
        catch(NoResultException nre){
            JOptionPane.showMessageDialog(null, "Δεν υπάρχει το αντικείμενο στη βάση δεδομένων", "Σφάλμα", JOptionPane.INFORMATION_MESSAGE);
        }

        /* Αλλαγή χρώματος background στα buttons, όταν ο κέρσορας είναι πάνω τους */
        rolloverButton(homeButton);
        rolloverButton(retrieveButton);
        rolloverButton(statisticsButton);
        rolloverButton(favoriteButton);
        rolloverButton(exitButton);
        rolloverButton(searchButton);
        
        fillFavoriteList();
        fillFavoriteListComboBox();
        // Αρχικά το jComboBox είναι κενό        
        favoriteListComboBox.setSelectedIndex(-1);
        // Αρχικά το jComboBox είναι κενό
        genreComboBox.setSelectedIndex(-1);  
        jScrollPane1.setVisible(false);
        movieTableScrollPane.setVisible(false);
    }

    /* Μέθοδος διαχείρισης εμφάνισης jButton σε δυναμικό περιβάλλον */
    private void rolloverButton(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            Color oldcolor1;
            Color oldcolor2;

            @Override
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

            @Override
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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        myMoviesPUEntityManager0 = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("myMoviesPU").createEntityManager();
        genreQuery = java.beans.Beans.isDesignTime() ? null : myMoviesPUEntityManager0.createQuery("SELECT g FROM Genre g");
        genreList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : genreQuery.getResultList();
        genreRenderer1 = new mymovies.GenreRenderer();
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
        genreLabel = new javax.swing.JLabel();
        genreComboBox = new javax.swing.JComboBox<>();
        yearLabel1 = new javax.swing.JLabel();
        setYearText = new javax.swing.JTextField();
        searchMoviesButton = new javax.swing.JButton();
        clearContentsButton = new javax.swing.JButton();
        addToListLabel = new javax.swing.JLabel();
        favoriteListComboBox = new javax.swing.JComboBox<>();
        deleteFromListButton = new javax.swing.JButton();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        favoriteListTable = new javax.swing.JTable();
        mainPanelSearch = new javax.swing.JPanel();
        movieTableScrollPane = new javax.swing.JScrollPane();
        searchMovieTable = new javax.swing.JTable();
        mainPanelStatistics = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));
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
                .addContainerGap(513, Short.MAX_VALUE)
                .addComponent(infoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addContainerGap(452, Short.MAX_VALUE))
            .addGroup(upperBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(upperBarLayout.createSequentialGroup()
                    .addComponent(tmdbLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 941, Short.MAX_VALUE)))
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

        homeButton.setBackground(new java.awt.Color(0, 0, 0));
        homeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/home_white_18dp.png"))); // NOI18N
        homeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });

        retrieveButton.setBackground(new java.awt.Color(0, 0, 0));
        retrieveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/retrieve_white_18dp.png"))); // NOI18N
        retrieveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retrieveButtonActionPerformed(evt);
            }
        });

        favoriteButton.setBackground(new java.awt.Color(0, 0, 0));
        favoriteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/favorite_white_18dp.png"))); // NOI18N
        favoriteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButtonActionPerformed(evt);
            }
        });

        searchButton.setBackground(new java.awt.Color(0, 0, 0));
        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/search_white_18dp.png"))); // NOI18N
        searchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        statisticsButton.setBackground(new java.awt.Color(0, 0, 0));
        statisticsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/statistics_white_18dp.png"))); // NOI18N
        statisticsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statisticsButtonActionPerformed(evt);
            }
        });

        exitButton.setBackground(new java.awt.Color(0, 0, 0));
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/exit_white_18dp.png"))); // NOI18N
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/app_logo_small.png"))); // NOI18N

        javax.swing.GroupLayout sideMenuBarLayout = new javax.swing.GroupLayout(sideMenuBar);
        sideMenuBar.setLayout(sideMenuBarLayout);
        sideMenuBarLayout.setHorizontalGroup(
            sideMenuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(sideMenuBarLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(sideMenuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(homeButton)
                    .addComponent(retrieveButton)
                    .addComponent(favoriteButton)
                    .addComponent(searchButton)
                    .addComponent(statisticsButton)
                    .addComponent(exitButton)))
        );
        sideMenuBarLayout.setVerticalGroup(
            sideMenuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideMenuBarLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(retrieveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(favoriteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(statisticsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        optionsBarPanel.setBackground(new java.awt.Color(21, 21, 21));
        optionsBarPanel.setLayout(new java.awt.CardLayout());

        homeOptionsPanel.setBackground(new java.awt.Color(21, 21, 21));

        javax.swing.GroupLayout homeOptionsPanelLayout = new javax.swing.GroupLayout(homeOptionsPanel);
        homeOptionsPanel.setLayout(homeOptionsPanelLayout);
        homeOptionsPanelLayout.setHorizontalGroup(
            homeOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 959, Short.MAX_VALUE)
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
        createListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createListButtonActionPerformed(evt);
            }
        });

        editListButton.setBackground(new java.awt.Color(0, 0, 0));
        editListButton.setForeground(new java.awt.Color(0, 204, 102));
        editListButton.setText("Επεξεργασία");
        editListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editListButtonActionPerformed(evt);
            }
        });

        deleteListButton.setBackground(new java.awt.Color(0, 0, 0));
        deleteListButton.setForeground(new java.awt.Color(0, 204, 102));
        deleteListButton.setText("Διαγραφή");
        deleteListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteListButtonActionPerformed(evt);
            }
        });

        favoriteList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                favoriteListMousePressed(evt);
            }
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

        genreLabel.setBackground(new java.awt.Color(21, 21, 21));
        genreLabel.setForeground(new java.awt.Color(255, 255, 255));
        genreLabel.setText("Είδος Ταινίας");
        searchOptionsPanel.add(genreLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 110, 20));

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${resultList}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, genreQuery, eLProperty, genreComboBox);
        bindingGroup.addBinding(jComboBoxBinding);
        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, genreRenderer1, org.jdesktop.beansbinding.ObjectProperty.create(), genreComboBox, org.jdesktop.beansbinding.BeanProperty.create("renderer"));
        bindingGroup.addBinding(binding);

        searchOptionsPanel.add(genreComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 130, -1));

        yearLabel1.setBackground(new java.awt.Color(21, 21, 21));
        yearLabel1.setForeground(new java.awt.Color(255, 255, 255));
        yearLabel1.setText("Έτος Κυκλοφορίας");
        searchOptionsPanel.add(yearLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 110, 20));

        setYearText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setYearTextActionPerformed(evt);
            }
        });
        setYearText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                setYearTextKeyTyped(evt);
            }
        });
        searchOptionsPanel.add(setYearText, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 115, 30));

        searchMoviesButton.setBackground(new java.awt.Color(0, 0, 0));
        searchMoviesButton.setForeground(new java.awt.Color(0, 204, 102));
        searchMoviesButton.setText("Αναζήτηση");
        searchMoviesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchMoviesButtonActionPerformed(evt);
            }
        });
        searchOptionsPanel.add(searchMoviesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 180, 25));

        clearContentsButton.setBackground(new java.awt.Color(0, 0, 0));
        clearContentsButton.setForeground(new java.awt.Color(0, 204, 102));
        clearContentsButton.setText("Καθαρισμός Κριτηρίων");
        clearContentsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearContentsButtonActionPerformed(evt);
            }
        });
        searchOptionsPanel.add(clearContentsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 180, 25));

        addToListLabel.setBackground(new java.awt.Color(21, 21, 21));
        addToListLabel.setForeground(new java.awt.Color(255, 255, 255));
        addToListLabel.setText("Προσθήκη σε Λίστα");
        searchOptionsPanel.add(addToListLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 120, 20));

        favoriteListComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteListComboBoxActionPerformed(evt);
            }
        });
        searchOptionsPanel.add(favoriteListComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 160, -1));

        deleteFromListButton.setBackground(new java.awt.Color(0, 0, 0));
        deleteFromListButton.setForeground(new java.awt.Color(0, 204, 102));
        deleteFromListButton.setText("Αφαίρεση από Λίστα");
        deleteFromListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFromListButtonActionPerformed(evt);
            }
        });
        searchOptionsPanel.add(deleteFromListButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 40, 180, 25));

        optionsBarPanel.add(searchOptionsPanel, "searchPanel");

        statisticsOptionsPanel.setBackground(new java.awt.Color(21, 21, 21));
        statisticsOptionsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topTenButton.setBackground(new java.awt.Color(0, 0, 0));
        topTenButton.setForeground(new java.awt.Color(0, 204, 102));
        topTenButton.setText("Οι Καλύτερες 10 Ταινίες");
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
                .addContainerGap(175, Short.MAX_VALUE)
                .addComponent(posterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(mainPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(movieScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(movieTitle))
                .addGap(188, 188, 188))
        );
        mainPanelHomeLayout.setVerticalGroup(
            mainPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelHomeLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(mainPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(posterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelHomeLayout.createSequentialGroup()
                        .addComponent(movieTitle)
                        .addGap(18, 18, 18)
                        .addComponent(movieScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        mainPanel.add(mainPanelHome, "homeCard");
        mainPanelHome.getAccessibleContext().setAccessibleName("");

        mainPanelFavorite.setBackground(new java.awt.Color(102, 102, 102));
        mainPanelFavorite.setLayout(new java.awt.BorderLayout());

        favoriteListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(favoriteListTable);

        mainPanelFavorite.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        mainPanel.add(mainPanelFavorite, "favoriteCard");
        mainPanelFavorite.getAccessibleContext().setAccessibleName("");

        mainPanelSearch.setBackground(new java.awt.Color(102, 102, 102));
        mainPanelSearch.setLayout(new java.awt.BorderLayout());

        searchMovieTable.setBackground(new java.awt.Color(48, 48, 48));
        searchMovieTable.setForeground(new java.awt.Color(255, 255, 255));
        searchMovieTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Τλιτλος Ταινίας", "Βαθμολογία", "Περιγραφή"
            }
        ));
        searchMovieTable.setGridColor(new java.awt.Color(0, 154, 57));
        searchMovieTable.setSelectionBackground(new java.awt.Color(0, 204, 102));
        searchMovieTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        searchMovieTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                searchMovieTableMousePressed(evt);
            }
        });
        movieTableScrollPane.setViewportView(searchMovieTable);
        if (searchMovieTable.getColumnModel().getColumnCount() > 0) {
            searchMovieTable.getColumnModel().getColumn(0).setMinWidth(250);
            searchMovieTable.getColumnModel().getColumn(0).setPreferredWidth(250);
            searchMovieTable.getColumnModel().getColumn(0).setMaxWidth(350);
            searchMovieTable.getColumnModel().getColumn(1).setMinWidth(90);
            searchMovieTable.getColumnModel().getColumn(1).setPreferredWidth(90);
            searchMovieTable.getColumnModel().getColumn(1).setMaxWidth(90);
        }

        mainPanelSearch.add(movieTableScrollPane, java.awt.BorderLayout.CENTER);

        mainPanel.add(mainPanelSearch, "card5");

        mainPanelStatistics.setBackground(new java.awt.Color(102, 102, 102));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("mainPanelStatistics: Εδώ μπορείτε να προσθέσετε πίνακες ή μηνύματα προς τον χρήστη");

        javax.swing.GroupLayout mainPanelStatisticsLayout = new javax.swing.GroupLayout(mainPanelStatistics);
        mainPanelStatistics.setLayout(mainPanelStatisticsLayout);
        mainPanelStatisticsLayout.setHorizontalGroup(
            mainPanelStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 948, Short.MAX_VALUE)
            .addGroup(mainPanelStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainPanelStatisticsLayout.createSequentialGroup()
                    .addGap(0, 224, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGap(0, 224, Short.MAX_VALUE)))
        );
        mainPanelStatisticsLayout.setVerticalGroup(
            mainPanelStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
            .addGroup(mainPanelStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainPanelStatisticsLayout.createSequentialGroup()
                    .addGap(0, 193, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGap(0, 193, Short.MAX_VALUE)))
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
                .addComponent(backGroundImage, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE))
        );
        backGroundPanelLayout.setVerticalGroup(
            backGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(optionsBarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(backGroundPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(sideMenuBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(backGroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(backGroundImage, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE))
        );

        add(backGroundPanel, java.awt.BorderLayout.CENTER);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm
    /* Μέθοδος εκτέλεσης ενεργειών, όταν πατηθεί το κουμπί homeButton*/
    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        CardLayout card = (CardLayout) optionsBarPanel.getLayout();
        card.show(optionsBarPanel, "homePanel");
        mainPanelHome.setVisible(true);
        mainPanelFavorite.setVisible(false);
        mainPanelStatistics.setVisible(false);
        mainPanelSearch.setVisible(false);
    }//GEN-LAST:event_homeButtonActionPerformed
    /* Μέθοδος εκτέλεσης ενεργειών, όταν πατηθεί το κουμπί statisticsButton*/
    private void statisticsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statisticsButtonActionPerformed
        CardLayout card = (CardLayout) optionsBarPanel.getLayout();
        card.show(optionsBarPanel, "statisticsPanel");
        mainPanelHome.setVisible(false);
        mainPanelFavorite.setVisible(false);
        mainPanelStatistics.setVisible(true);
        mainPanelSearch.setVisible(false);
    }//GEN-LAST:event_statisticsButtonActionPerformed
    /* Μέθοδος εκτέλεσης ενεργειών, όταν πατηθεί το κουμπί exitButton*/
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // έξοδος από την εαφρμογή
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed
    /* Μέθοδος εκτέλεσης ενεργειών, όταν πατηθεί το κουμπί retrieveButton*/
    private void retrieveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retrieveButtonActionPerformed
        CommunicationWorker cm = new CommunicationWorker();
        cm.execute();
    }//GEN-LAST:event_retrieveButtonActionPerformed
    /* Μέθοδος εκτέλεσης ενεργειών, όταν πατηθεί το κουμπί favoriteButton*/
    private void favoriteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButtonActionPerformed
        CardLayout card = (CardLayout) optionsBarPanel.getLayout();
        card.show(optionsBarPanel, "favoritePanel");
        mainPanelHome.setVisible(false);
        mainPanelFavorite.setVisible(true);
        mainPanelStatistics.setVisible(false);
        mainPanelSearch.setVisible(false);
    }//GEN-LAST:event_favoriteButtonActionPerformed

    /* 
     * Μέθοδος προσαρμογής της εικόνας που βρίσκεται στο background, 
     * όταν το παράθυρο αλλάξει μέγεθος. Αν αυτό γίνει μικρότερο από το αρχικό,
     * τότε η εικόνα διατηρεί τις ίδιες διαστάσεις που είχε.
     */
    private void backGroundPanelComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_backGroundPanelComponentResized
        // Τροποποίηση, μόνο σε περίπτωση που το παράθυρο γίνει μεγαλύτερο από το αρχικό
        System.out.println(this.getBounds().width+", "+this.getBounds().height);
        if(this.getBounds().width>1064 || this.getBounds().height>613){
            // Οι διαστάσεις τις εικόνας μεταβάλλονται σε σχέση με το μήκος και πλάτος του παραθύρου
            backGroundImage.setSize(this.getBounds().width-16, this.getBounds().height-77);
        }
        else{
            backGroundImage.setSize(1048, 536);            
        }
        // Επανασχεδιασμός του background image σύμφωνα με τις νέες διαστάσεις
        BufferedImage bi = new BufferedImage(backGroundImage.getWidth(), backGroundImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D)bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
        RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(bckgndImage.getImage(), 0, 0, backGroundImage.getWidth(), backGroundImage.getHeight(), null);
        ImageIcon imageIcon = new ImageIcon(bi);
        backGroundImage.setIcon(imageIcon);                    
    }//GEN-LAST:event_backGroundPanelComponentResized
    /* Μέθοδος εκτέλεσης ενεργειών, όταν πατηθεί το κουμπί searchButton*/
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        fillFavoriteListComboBox();     // Ενημέρωση του favoriteListComboBox
        favoriteListComboBox.setSelectedIndex(-1);
        CardLayout card = (CardLayout) optionsBarPanel.getLayout();
        card.show(optionsBarPanel, "searchPanel");
        mainPanelHome.setVisible(false);
        mainPanelFavorite.setVisible(false);
        mainPanelStatistics.setVisible(false);
        mainPanelSearch.setVisible(true);
    }//GEN-LAST:event_searchButtonActionPerformed

    private void clearContentsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearContentsButtonActionPerformed
        setYearText.setText("");
        genreComboBox.setSelectedIndex(-1);
    }//GEN-LAST:event_clearContentsButtonActionPerformed

    private void setYearTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setYearTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_setYearTextActionPerformed

    private void setYearTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_setYearTextKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_setYearTextKeyTyped
    /* Μέθοδος εκτέλεσης ενεργειών, όταν πατηθεί το κουμπί searchMoviesButton*/
    private void searchMoviesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchMoviesButtonActionPerformed
        /* Έλεγχος ορθής εισαγωγής χρονιάς από τον χρήστη */
        try{
            userInput = Integer.parseInt(setYearText.getText());  
        }
        catch(NumberFormatException nfe){
            userInput = 0;
        }
        /* 
         * Έλεγχος ορθότητας επιλεγμένων δεδομένων για ανεύρεση ταινιών και εμφάνιση
         * δεδομένων στον πίνακα searchMovieTable 
         */
        if(genreComboBox.getSelectedItem() != null && userInput >= 2000 && userInput <= 2030 && !setYearText.getText().equals("")){
            Genre g = (Genre)genreComboBox.getSelectedItem();
            DefaultTableModel tModel = (DefaultTableModel) searchMovieTable.getModel();
            tModel.setRowCount(0);
            int colCount = searchMovieTable.getColumnCount();
            Object[] ob = new Object[colCount];
            List<Movie> movieList = mc.getSelectedMovies(userInput, g);
            for(Movie m : movieList){
                for(int row = 0; row < movieList.size(); row++){
                    ob[0] = m.getTitle();
                    ob[1] = m.getRating();
                    ob[2] = m.getOverview();
                }
                tModel.addRow(ob);
            }
            mainPanelSearch.setBackground(new Color(102,102,102));
            movieTableScrollPane.setVisible(true);
        }
        else{
           JOptionPane.showMessageDialog(null, "Επιλέξτε ένα από τα διαθέσιμα είδη και εισάγετε έτος (2000-2030)", "Μη έγκυρες τιμές", JOptionPane.INFORMATION_MESSAGE); 
        }
    }//GEN-LAST:event_searchMoviesButtonActionPerformed

    /* Μέθοδος εκτέλεσης ενεργειών, όταν πατηθεί το κουμπί createListButton*/
    private void createListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createListButtonActionPerformed
        // Δημιουργία νέου παραθύρου διαλόγου
        CreateNewList newList = new CreateNewList(this, true);
        newList.setLocationRelativeTo(this);
        newList.setVisible(true);
        // Εάν πατήθηκε το κουμπί αποθήκευση, προχωράμε
        if(newList.getReturnStatus() == 1){
            JTextField t = newList.getUserText();   // Τα δεδομένα που εισήγαγε ο χρήστης
            FavoriteList fl = new FavoriteList();
            System.out.println(t.getText()+", returnedStatus = "+newList.getReturnStatus());
            fl.setName(t.getText());
            flc.storeFavoriteToDataBase(fl);        // Αποθήκευση στη βάση δεδομένων
            fillFavoriteList();    
        }
    }//GEN-LAST:event_createListButtonActionPerformed
    /* Μέθοδος εκτέλεσης ενεργειών, όταν πατηθεί το κουμπί deleteListButton*/
    private void deleteListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteListButtonActionPerformed
        if(favoriteList.getSelectedValuesList() != null){
            // Δημιουργία jOptionPane για προειδοποίηση διαγραφής
            Object[] options = {"Ναι","Ακύρωση"};
            int n = JOptionPane.showOptionDialog(null,
            "Πρόκειται να διαγράψετε την Λίστα Αγαπημένων. "
            +"Θέλετε να συνεχίσετε;",
            "Προειδοποίηση!",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.WARNING_MESSAGE,
            null,options,options[1]);
            // Εάν επιλέχτηκε "Ναι", προχωράμε στη διαγραφή
            if(n == 0){
                for(int i =0; i<favoriteList.getSelectedValuesList().size(); i++){
                    // Αρχικά θέτουμε null τα αντίστοιχα favoriteListId στις ταινίες
                    FavoriteList fl = flc.getFavoriteListByName(favoriteList.getSelectedValuesList().get(i));
                    ArrayList<Movie> movies = mc.getMoviesForList(fl);
                    fl = null;
                    for(int j = 0; j < movies.size(); j++){
                        mc.updateMovie(movies.get(j).getId(), fl);
                    }
                    // Έπειτα γίνεται διαγραφή της λίστας από τη βάση δεδομένων
                    flc.deleteFavoriteFromDataBase(favoriteList.getSelectedValuesList().get(i));                    
                }               
            }
        }
        fillFavoriteList();        
    }//GEN-LAST:event_deleteListButtonActionPerformed
    /* Μέθοδος εκτέλεσης ενεργειών, όταν πατηθεί το κουμπί editListButton*/
    private void editListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editListButtonActionPerformed
        // TODO: Να θέσω πρώτα null τις αντίστοιχες ταινίες
        // Ελέγχουμε εάν επιλέχθηκε κάποιο στοιχείο από τη λίστα αγαπημένων
        if(favoriteList.getSelectedValue() == null){
            JOptionPane.showMessageDialog(null, "Δεν επιλέχθηκε κάποια Λίστα", "Μήνυμα", JOptionPane.INFORMATION_MESSAGE);        
        }
        // Έλεγχος εάν επιλέχθηκαν περισσότερα από ένα στοιχεία από τη λίστα
        else if(favoriteList.getSelectedValuesList().size() > 1){
            JOptionPane.showMessageDialog(null, "Παρακαλώ επιλέξτε μια μόνο Λίστα", "Μήνυμα", JOptionPane.INFORMATION_MESSAGE);           
        }
        else{
            // Δημιουργία νέου παραθύρου διαλόγου, όμοιο με αυτό της Δημιουργίας            
            CreateNewList newList = new CreateNewList(this, true);
            newList.setLocationRelativeTo(this);
            JTextField t = new JTextField();
            t.setText(favoriteList.getSelectedValue());
            newList.setUserText(t);
            newList.setVisible(true);
            if(newList.getReturnStatus() == 1){
                String oldName = favoriteList.getSelectedValue();
                flc.deleteFavoriteFromDataBase(oldName);
                t = newList.getUserText();
                FavoriteList fl = new FavoriteList();
                fl.setName(t.getText());
                flc.storeFavoriteToDataBase(fl);
                fillFavoriteList();    
            }            
        }
           
    }//GEN-LAST:event_editListButtonActionPerformed

    private void deleteFromListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFromListButtonActionPerformed
        // TODO add your handling code here:
        if(favoriteListComboBox.getSelectedItem() != null){
            int row = searchMovieTable.getSelectedRow();
            String title = (String)searchMovieTable.getValueAt(row, 0);
            Movie m = mc.getMovieByTtitle(title);  
            FavoriteList fl = null;
            mc.updateMovie(m.getId(), fl);
            favoriteListComboBox.setSelectedIndex(-1);
           JOptionPane.showMessageDialog(null, "Η ταινία αφαιρέθηκε από τη λίστα", "Ενημέρωση", JOptionPane.INFORMATION_MESSAGE);             
        }                 
    }//GEN-LAST:event_deleteFromListButtonActionPerformed
    /* Μέθοδος προσθήκης σε λίστα, μιας επιλεγμένης ταινίας */
    private void favoriteListComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteListComboBoxActionPerformed
        // Έλεγχος αν υπάρχει εγγραφή στον πίνακα, αν επιλέχθηκε στοιχείο στο comboBox και αν η μέθοδος κλήθηκε από τον χρήστη
        comboBoxActionCounter++;
        if(searchMovieTable.getRowCount() != 0 && favoriteListComboBox.getSelectedItem() != null && comboBoxActionCounter > 0){            
            int row = searchMovieTable.getSelectedRow();
            String title = (String)searchMovieTable.getValueAt(row, 0);
            Movie m = mc.getMovieByTtitle(title);           
            FavoriteList fl = flc.getFavoriteListByName((String)favoriteListComboBox.getSelectedItem());
            mc.updateMovie(m.getId(), fl);
            JOptionPane.showMessageDialog(null, "Η ταινία "+title+" προστέθηκε στη λίστα "+fl.getName()
                    , "Ενημέρωση", JOptionPane.INFORMATION_MESSAGE); 
            comboBoxActionCounter = 0;      // Επαναφορά μετρητή
        }
    }//GEN-LAST:event_favoriteListComboBoxActionPerformed

   /* Μέθοδος εμφάνισης πίνακα με τις ταινίες που ανήκουν στην επιλεγμένη λίστα */
    private void favoriteListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_favoriteListMousePressed
        // Έλεγχος αν επιλέχθηκε κάποια λίστα
        System.out.println("Mpike");
        if(favoriteList.getSelectedValue() != null){
            FavoriteList fl = flc.getFavoriteListByName(favoriteList.getSelectedValue());
            DefaultTableModel tModel = (DefaultTableModel) searchMovieTable.getModel();
            tModel.setRowCount(0);
            int colCount = searchMovieTable.getColumnCount();
            Object[] ob = new Object[colCount];
            List<Movie> movieList = mc.getMoviesForList(fl);
            for(Movie m : movieList){
                for(int row = 0; row < movieList.size(); row++){
                    ob[0] = m.getTitle();
                    ob[1] = m.getRating();
                    ob[2] = m.getOverview();
                }
                tModel.addRow(ob);
            }
            favoriteListTable.setModel(tModel);
            mainPanelFavorite.setBackground(new Color(102,102,102));
            jScrollPane1.setVisible(true);
        }
    }//GEN-LAST:event_favoriteListMousePressed

    private void searchMovieTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMovieTableMousePressed
        checkSelectedRecordFromTable(); 
    }//GEN-LAST:event_searchMovieTableMousePressed

   /* Ανανέωση των περιεχομένων που εμφανίζονται στην jList favoriteList */
    private void fillFavoriteList(){
        FavoriteList fl;
        DefaultListModel m = new DefaultListModel();
        List fList = flc.getFavoriteList();
        for(int i = 0; i<fList.size(); i++){
            fl = (FavoriteList)fList.get(i);
            m.add(i, fl.getName());
        }
        favoriteList.setModel(m);
    }
    /* Ανανέωση των περιεχομένων που εμφανίζονται στο jComboBox favoriteListComboBox */    
    private void fillFavoriteListComboBox(){
        FavoriteList fl;
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        List fList = flc.getFavoriteList();
        for(int i = 0; i < fList.size(); i++){
            fl = (FavoriteList)fList.get(i);
            m.addElement(fl.getName());
        }
        favoriteListComboBox.setModel(m);
    }
    /* Έλεγχος της επιλεγμένης εγγραφής στον πίνακα movieTable */    
    public void checkSelectedRecordFromTable(){
        comboBoxActionCounter = -1;
        int row = searchMovieTable.getSelectedRow();
        System.out.println(row);
        String title = (String)searchMovieTable.getValueAt(row, 0);
        System.out.println(title);
        Movie m = mc.getMovieByTtitle(title); 
        if(m.getFavoriteListId() != null){
            favoriteListComboBox.setSelectedItem(m.getFavoriteListId().getName());
        }
        else{
            favoriteListComboBox.setSelectedIndex(-1);  
        }
        
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addToListLabel;
    private javax.swing.JLabel backGroundImage;
    private javax.swing.JPanel backGroundPanel;
    private javax.swing.JButton clearContentsButton;
    private javax.swing.JButton createListButton;
    private javax.swing.JButton deleteFromListButton;
    private javax.swing.JButton deleteListButton;
    private javax.swing.JButton editListButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton favoriteButton;
    private javax.swing.JList<String> favoriteList;
    private javax.swing.JComboBox<String> favoriteListComboBox;
    private javax.swing.JTable favoriteListTable;
    private javax.swing.JPanel favoriteOptionsPanel;
    private javax.swing.JComboBox<String> genreComboBox;
    private javax.swing.JLabel genreLabel;
    private java.util.List<entity.Genre> genreList;
    private javax.persistence.Query genreQuery;
    private mymovies.GenreRenderer genreRenderer1;
    private javax.swing.JButton homeButton;
    private javax.swing.JPanel homeOptionsPanel;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane listScrollPane;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel mainPanelFavorite;
    private javax.swing.JPanel mainPanelHome;
    private javax.swing.JPanel mainPanelSearch;
    private javax.swing.JPanel mainPanelStatistics;
    private javax.swing.JTextArea movieOverview;
    private javax.swing.JScrollPane movieScrollPane;
    private javax.swing.JScrollPane movieTableScrollPane;
    private javax.swing.JLabel movieTitle;
    private javax.persistence.EntityManager myMoviesPUEntityManager0;
    private javax.swing.JPanel optionsBarPanel;
    private javax.swing.JLabel posterLabel;
    private javax.swing.JButton retrieveButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTable searchMovieTable;
    private javax.swing.JButton searchMoviesButton;
    private javax.swing.JPanel searchOptionsPanel;
    private javax.swing.JTextField setYearText;
    private javax.swing.JPanel sideMenuBar;
    private javax.swing.JButton statisticsButton;
    private javax.swing.JPanel statisticsOptionsPanel;
    private javax.swing.JLabel tmdbLabel;
    private javax.swing.JButton topTenButton;
    private javax.swing.JButton topTenPerListButton;
    private javax.swing.JLayeredPane upperBar;
    private javax.swing.JLabel yearLabel1;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
