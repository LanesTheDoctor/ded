/*
    File name: GoldCoastESportsGUI.java
    Purpose: Create the GUI Window Application
    Provide all of the application.
    Author: Zac Makkinga
    Date: 25/08/2024
    Version: 1.0
    Notes:

    Required functionalities:
        1. Application is launched.
        1.1 Read-in competition results from competitions.csv external file and set up in the ArrayList<Competition> competitionList data structure.
        **       1.2 Read =pin team names and details from the teams.csv external file and set up in ArryanList<Team> teamList data structure.
        1.3 Display read-in competition results in JTable.
    **    1.4 Display read-in team names in 2x JComboBoxes.
     **   1.5 Display team info for selected team name in Update panel.

     **   2. Add a new comp result.
        2.1 Add a new validated comp result to the ArrayList<Competition> competitionList and display in JTable

        3. Add a new team
    **    3.1 Add a new validated team to ArrayList<Team> teamList and add to the 2x JComboBoxes.

    **    4. Update existing team
        4.1 Update details for a selected existing team, validate changes to person, phone, email and change values in ArrayList<Team> for the selected team.

    **    5. Display top teams in the leaderborad table
        5.1 Calculate total points earned for each team in ArrayList<Team> teamList
        5.2 List he teamList and the total points in total points descending order (highlest to lowest).

    **    6. Application is closed and saving of data
        6.1 When app is closed, provide optin ofr user to save chagnes (competitionList, teamList).
        6.2 Save (write) to 2x external csv files the data from: ArrayListCompetition> competitionList ArrayList<Team> teamList
*/
package goldcoastesports;

import java.util.HashSet; 
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.FormulaEvaluator;  
import org.apache.poi.ss.usermodel.Row;  

import java.io.File;  
import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

// // import goldcoastesports.GoldCoastESportsGUI.Team;
import goldcoastesports.Team;
import goldcoastesports.Competition;

public class GoldCoastESportsGUI extends javax.swing.JFrame
{
    //in the arrow brackets are the classes
    private ArrayList<Competition> competitionList;
    
    private ArrayList<Team> teamList;
    
    private boolean comboBoxStatus;
    
    private DefaultTableModel compResultsTableModel;
    
    //constructor
    public GoldCoastESportsGUI()
        {
            //ititialise private data fields
            initComponents();
            competitionList = new ArrayList<Competition>();
            teamList = new ArrayList<Team>();
            comboBoxStatus = false;
            compResultsTableModel = new DefaultTableModel();

            //customise table model
            String [] columnName_Results = new String []
                {
                    "Date", "Location", "Game", "Team", "Points"
                };

            compResultsTableModel.setColumnIdentifiers(columnName_Results);

            ArrayList<Team> teamList = TeamPointsUtil.loadTeamsFromExcel("teams.xlsx");
            TeamPointsUtil.calculateTotalPoints(teamList, "competitions.csv");

            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
            for (Team team : teamList)
                {
                    comboBoxModel.addElement(team.getName());
                }

            addNewCompResultComboBox_JPanel.setModel(comboBoxModel);
            updateTeamComboBox_JPanel.setModel(comboBoxModel);

            for (Team team : teamList)
                {
                    System.out.println(team);
                }

            //initialise all swing controls
            initComponents();

            //customise table columns
            resizeTableColumns();

            //read external csv files
            readCompetitionData();
            readTeamData();

            //display comp data in table
            displayCompetitions();

            //display teams in comboboxes
            displayTeams();

            //display team details in jtextfields with team combo boxes
            displayTeamDetails();
                // work out urself

            //reset comoboboxstatus to be true (finished with updating comboboxes)
            comboBoxStatus = true;
        }

        
        
    private void resizeTableColumns()
        {
            double[] columnWidthPercentage = {0.2f, 0.2f, 0.3f, 0.2f, 0.1f};
            int tableWidth = compResults_Table.getWidth(); //TODO are these supposed to be compResultsTableModel.getWidth();??
            TableColumn column;
            TableColumnModel tableColumnModel = compResults_Table.getColumnModel();  //TODO are these supposed to be compResultsTableModel.getColumnModel();??
            int cantCols = tableColumnModel.getColumnCount();
            for (int i = 0; i < cantCols; i++)
            {
                column = tableColumnModel.getColumn(i);
                float pWidth = Math.round(columnWidthPercentage[i] * tableWidth);
                column.setPreferredWidth((int)pWidth);
            }
        }
        
    // private void displayTeams() // TODO: DUPLICATE METHOD
    //     {
    //         throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    //     }

    private void displayTeamDetails() 
        {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

 /**************************
     Method: readCompetitionData()
     Purpose: reads the competitions.csv file
     * and populates objects created from the competition class
     * 
     
     * Inputs: void
     * Output: void
     ***********************/

     /*****
      * Method: validateNewTeam()
      * Purpose: basic validation of user inputs when creating a new team
      * - rejects empty text fields for the team name, contact person, phone email
      * - uses boolean validation to track the status of the validation
      * - uses JOptionPane to create a pop-up window if validation is false to advise user of errors
      * Inputs: void
      * outputs: returns boolean validation (true if all fields contain strings, false if any contain nothing
      * 
      * this is basically a validation method
      */




    private boolean validateNewTeam()
        {
            boolean validation;

            String errorMsg = "Error(s) encountered!\n";

            if (newTeamName_TextField.getText().isEmpty())
                {
                    errorMsg += "New team anme required\n";
                    validation = false;
                }

            if (newContactPerson_TextField.getText().isEmpty())
                {
                    errorMsg += "Contact person required\n";
                    validation = false;
                }
                    // check that these variable names correlate with the gui object names
            if (newContactPhone_TextField.getText().isEmpty())
                {
                    errorMsg += "contact email address required\n";
                    validation = false;
                }

            if (validation == false)
                {
                    JOptionPane.showMessageDialog(null, errorMsg, "Error(s)!", JOptionPane.ERROR_MESSAGE);
                }

            return validation;
        }

    private void readCompetitionData()
        {
            try
                {
                    //1. create reader and designate external file to read from
                    FileReader reader = new FileReader("competition.csv");
                    // 2. create bufferedReader which uses the reader object
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    //3. Declare a line string (used to read from and store each line taht is read
                    String line;
                    //4. loop throough each line in the external file
                    // until end of file is reached (EOF)
                    while ((line = bufferedReader.readLine()) != null)
                    {
                        //check line
                        //System.out.println(line);
                        if (line.length() > 0)
                            {
                                //split the line string by the delimiter comma character
                                //"League of Legends" is set up in the linArray [0]
                                //"TAFE coomera" is set up in lineArray[1]
                                //"12_jan-2024" is set up in lineArray[2]
                                String [] lineArray = line.split(",");
                                //set up individual variables ofr ecah split line component
                                String game = lineArray[0];
                                String location = lineArray[1];
                                String compDate = lineArray[2];
                                String team = lineArray[3];
                                // "2" is converted to an actual integer
                                int points = Integer.parseInt(lineArray[4]);
                                //create competition instance
                                Competition comp = new Competition(game, location, compDate, team, points);
                                // add comp to the rrayList
                                competitionList.add(comp);
                            }
                    }

                    //5. close the reader object
                    reader.close();
                }


            catch (FileNotFoundException fnfe)
                {
                    //catch any file not found
                    System.out.println("Error: file not found!");
                }

            catch (IOException ioe)
                {
                    //catch any file in io related exception
                    System.out.println("Error: read problems with competitions.csv file!");
                }

            catch (NumberFormatException nfe)
                {
                    ///catch number string conversion to integer exception
                    System.out.println("Error: Number format exception for integer points!");
                }

            catch (Exception e)
                {

                }
        }

    private void readTeamData()
        {

        }

/**************************
    Method: displayCompetitions()
    Purpose: display competitions from ArrayList in JTable
     * Inputs: void
     * Output: void
     ***********************/

    public class TeamPointsCalculator
        {
            public static ArrayList<Team> loadTeamsFromExcel(String excelFilePath)
                {
                    ArrayList<Team> teamList = new ArrayList<>();
                    
                    try(FileInputStream fis = new FileInputStream(excelFilePath);
                        Workbook workbook = new XSSFWorkbook(fis))
                            {
                                Sheet sheet = workbook.getSheetat(0);

                                for (Row row : sheet)
                                    {
                                        Cell cell = row.getCell(0);
                                        if ( cell != null && cell.getCellType() == CellType.STRING)
                                            {
                                                String teamName = cell.getStringCellValue();
                                                teamList.add(new Team(teamName));
                                            }
                                    }
                            }
                            
                    catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        
                    return teamList;
                }

                public static void calculateTotalPoints(ArrayList<Team> teamList, String csvFilePath)
                    {
                        HashMap<String, Integer> teamPointsMap = new HashMap<String, Integer>();

                        for (Team team : teamList)
                            {
                                teamPointsMap.put(team.getName(),0);
                            }

                            try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath)))
                                {
                                    String line;
                                    while ((line = br.readLine()) != null)
                                        {
                                            String[] columns = line.split(",");
                                            String teamName = columns[0];
                                            int points = 0; // I had to initilise variable to remove errors. 

                                            if (columns.length > 4)
                                                {
                                                    points = Integer.parseInt(columns[4]);    
                                                }
                                            if (teamPointsMap.containsKey(teamName))
                                                {
                                                    teamPointsMap.put(teamName, teamPointsMap.get(teamName + points));
                                                }
                                        }
                                }
                                catch (IOException e)
                                    {
                                        e.printStackTrace();
                                    }
            
                                for (Team team : teamList)
                                    {
                                        String name = team.getName();
                                        if (teamPointsMap.containsKey(name))
                                            {
                                                team.addPoints(teamPointsMap.get(name));
                                            }
                                    }
                    }
        }

    public class Team
        {
            private String name;
            private int totalPoints;
        
            public Team(String name)
                {
                    this.name = name;
                    this.totalPoints = 0;
                }
            
            public String getName()
                {
                    return name;
                }

            public int getTotalPoints()
                {
                    return totalPoints;
                }
            
            public void addPoints(int points)
                {
                    this.totalPoints += points;
                }
            
            @Override
            public String toString()
                {
                    return name + ": " + totalPoints + " points";
                }
        }
    
    public static void ExportTeamsToCSV(ArrayList<Team> teamList String csvFilePath)
        {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath)))
            {
                writer.write("Name, Total Points");
                writer.newLine();
                
                for (Team taem : teamList)
                {
                    String name = team.getName();
                    int totalPoints = team.getTotalPoints();
                    
                    String line = String.format("%s,%d", name totalPoints);
                    writer.write(line);
                    writer.newLine();
                }
                
                System.out.println("Data successfully exorter to " + csvFilePath);
            }
            
            catch (IOException e)
            {
                e.printStackTrace();
                System.out.println("An error occurred while exporting data.");
            }
        }

    
    
    
    private void displayTeams()
    {
        String csvFile = "competitions.csv"; // You need to give this a proper file url
        String line;
        String[] columnNames = null;
        DefaultTableModel model = new DefaultTableModel();
        
        HashSet<String> uniqueValues = new HashSet<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile)))
        {
            if ((line = br.readLine()) != null)
            {
                columnNames = line.split(",");
                model.setColumnIdentifiers(columnNames);
            }
            
            while ((line = br.readLine()) != null)
            {
                String[] rowData = line.split(",");
                model.addRow(rowData);
                
                if (rowData.length > 0)
                    {
                        uniqueValues.add(rowData[0]);
                    }
            }
        }
        
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        //send data to jtable
        compResults_JTable.setModel(model);

        //update the combo boxes
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(uniqueValues.toArray(new String[0]));
        addNewCompResultComboBox_JPanel.setModel(comboBoxModel);
        updateTeamComboBox_JPanel.setModel(comboBoxModel);
    }
        // public static void main(String[] args) 
        //     {
        //         SwingUtilities.invokeLater(() -> new GoldCoastESports().setVisible(true));
        //     }
    

    private void displayCompetitions()
        {
            //populate compeition data into JTable
            if (competitionList.size() > 0)
                {
                    // create Object[] [] @-D array for JTable
                    Object[][] comp2DArray = new Object[competitionList.size()][];
                    //populate 2-D array with the competitions
                    for (int i= 0; i < competitionList.size(); i++)
                        {
                            //create single dimensional Object [] array --- 1 competition (for row)
                            Object[] comp = new Object[5];
                            // date
                            comp[0] = competitionList.get(i).getCompetitionDate();
                            // location
                            comp[1] = competitionList.get(i).getLocation();
                            // game
                            comp[2] = competitionList.get(i).getGame();
                            // team
                            comp[3] = competitionList.get(i).getTeam();
                            // points
                            comp[4] = competitionList.get(i).getPoints();
                            // append comp to the 2D array comp2Drray
                            comp2DArray[i] = comp;
                        }


                        // remove all existing rows in the JTables (if there are any)
                        if (compResultsTableModel.getRowCount() > 0)
                            {
                                for (int i = compResultsTableModel.getRowCount() - 1; i >= 0; i--)
                                    {
                                        compResultsTableModel.removeRow(i);
                                    }
                            }

                            // put 2D array of competition data into JTable
                            if (comp2DArray.length > 0)
                            {
                                //add data to tableModel
                                for (int row = 0; row < comp2DArray.length; row++)
                                    {
                                        compResultsTableModel.addRow(comp2DArray[row]);
                                    }
                            }

                            //update table model
                            compResultsTableModel.fireTableDataChanged();
                }
        }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header_JPanel = new javax.swing.JPanel();
        img_Label = new javax.swing.JLabel();
        body_Panel = new javax.swing.JPanel();
        body_TabedPane = new javax.swing.JTabbedPane();
        teamCompResult_JPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        compResults_JTable = new javax.swing.JTable();
        displayTeamResults_Button = new javax.swing.JButton();
        addNewCompResult_JPannel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        dateAddCompResult_JPanel = new javax.swing.JTextPane();
        jScrollPane11 = new javax.swing.JScrollPane();
        gameAddCompResult_JPanel = new javax.swing.JTextPane();
        jScrollPane13 = new javax.swing.JScrollPane();
        locationAddCompResult_JPanel = new javax.swing.JTextPane();
        jScrollPane14 = new javax.swing.JScrollPane();
        pointsAddCompResult_JPanel = new javax.swing.JTextPane();
        addNewCompResult_Button = new javax.swing.JButton();
        addNewCompResultComboBox_JPanel = new javax.swing.JComboBox<>();
        addNewTeam_JPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        contactPersonTextArea_JButton = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        teamNameTextArea_JButton = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        contactemailTextArea_JButton = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        contactPhoneTextArea_JButton = new javax.swing.JTextPane();
        addNewTeam_Button = new javax.swing.JButton();
        updateexistingTeam_JPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        contactPersonTextAreaUpdate_JButton = new javax.swing.JTextPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        contactemailTextAreaUpdate_JButton = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        contactPhoneTextAreaUpdate_JButton = new javax.swing.JTextPane();
        updateExistingTeam_Button = new javax.swing.JButton();
        updateTeamComboBox_JPanel = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gold Coast ESports Menu App");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        img_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/GoldCoastESports_Header.jpg"))); // NOI18N

        javax.swing.GroupLayout header_JPanelLayout = new javax.swing.GroupLayout(header_JPanel);
        header_JPanel.setLayout(header_JPanelLayout);
        header_JPanelLayout.setHorizontalGroup(
            header_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, header_JPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(img_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 837, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        header_JPanelLayout.setVerticalGroup(
            header_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(img_Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        body_Panel.setBackground(new java.awt.Color(255, 255, 255));

        teamCompResult_JPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Team Competition Results");

        compResults_JTable.setModel(compResultsTableModel);
        jScrollPane1.setViewportView(compResults_JTable);

        displayTeamResults_Button.setText("DISPLAY TEAM RESULTS");
        displayTeamResults_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayTeamResults_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout teamCompResult_JPanelLayout = new javax.swing.GroupLayout(teamCompResult_JPanel);
        teamCompResult_JPanel.setLayout(teamCompResult_JPanelLayout);
        teamCompResult_JPanelLayout.setHorizontalGroup(
            teamCompResult_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teamCompResult_JPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(teamCompResult_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(teamCompResult_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(displayTeamResults_Button)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(201, Short.MAX_VALUE))
        );
        teamCompResult_JPanelLayout.setVerticalGroup(
            teamCompResult_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teamCompResult_JPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(displayTeamResults_Button)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        body_TabedPane.addTab("Team competition result", teamCompResult_JPanel);

        addNewCompResult_JPannel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Add a New Comp Result");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Date:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Points:");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Location: ");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Game:");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Team:");

        jScrollPane10.setViewportView(dateAddCompResult_JPanel);

        jScrollPane11.setViewportView(gameAddCompResult_JPanel);

        jScrollPane13.setViewportView(locationAddCompResult_JPanel);

        jScrollPane14.setViewportView(pointsAddCompResult_JPanel);

        addNewCompResult_Button.setText("ADD NEW COMPETITION RESULT");
        addNewCompResult_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewCompResult_ButtonActionPerformed(evt);
            }
        });

        addNewCompResultComboBox_JPanel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout addNewCompResult_JPannelLayout = new javax.swing.GroupLayout(addNewCompResult_JPannel);
        addNewCompResult_JPannel.setLayout(addNewCompResult_JPannelLayout);
        addNewCompResult_JPannelLayout.setHorizontalGroup(
            addNewCompResult_JPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addNewCompResult_JPannelLayout.createSequentialGroup()
                .addGroup(addNewCompResult_JPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addNewCompResult_JPannelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel12))
                    .addGroup(addNewCompResult_JPannelLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(addNewCompResult_JPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addNewCompResult_Button)
                            .addGroup(addNewCompResult_JPannelLayout.createSequentialGroup()
                                .addGroup(addNewCompResult_JPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(addNewCompResult_JPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane10)
                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addNewCompResultComboBox_JPanel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(296, Short.MAX_VALUE))
        );
        addNewCompResult_JPannelLayout.setVerticalGroup(
            addNewCompResult_JPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addNewCompResult_JPannelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(addNewCompResult_JPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(addNewCompResult_JPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(addNewCompResult_JPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(addNewCompResult_JPannelLayout.createSequentialGroup()
                        .addGroup(addNewCompResult_JPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addNewCompResultComboBox_JPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(addNewCompResult_JPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(addNewCompResult_Button)
                .addGap(49, 49, 49))
        );

        body_TabedPane.addTab("Add new competition result", addNewCompResult_JPannel);

        addNewTeam_JPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Add a New Team");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Contact Phone:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Team name:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Contact Email:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Contact Person:");

        jScrollPane2.setViewportView(contactPersonTextArea_JButton);

        jScrollPane3.setViewportView(teamNameTextArea_JButton);

        jScrollPane4.setViewportView(contactemailTextArea_JButton);

        jScrollPane5.setViewportView(contactPhoneTextArea_JButton);

        addNewTeam_Button.setText("ADD NEW TEAM");
        addNewTeam_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewTeam_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addNewTeam_JPanelLayout = new javax.swing.GroupLayout(addNewTeam_JPanel);
        addNewTeam_JPanel.setLayout(addNewTeam_JPanelLayout);
        addNewTeam_JPanelLayout.setHorizontalGroup(
            addNewTeam_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addNewTeam_JPanelLayout.createSequentialGroup()
                .addGroup(addNewTeam_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addNewTeam_JPanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addNewTeam_JPanelLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(addNewTeam_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addNewTeam_Button)
                            .addGroup(addNewTeam_JPanelLayout.createSequentialGroup()
                                .addGroup(addNewTeam_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(addNewTeam_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(253, Short.MAX_VALUE))
        );
        addNewTeam_JPanelLayout.setVerticalGroup(
            addNewTeam_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addNewTeam_JPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addGap(60, 60, 60)
                .addGroup(addNewTeam_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(addNewTeam_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(addNewTeam_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(27, 27, 27)
                .addGroup(addNewTeam_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(addNewTeam_Button)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        body_TabedPane.addTab("Add new team", addNewTeam_JPanel);

        updateexistingTeam_JPanel.setBackground(new java.awt.Color(255, 255, 255));
        updateexistingTeam_JPanel.setRequestFocusEnabled(false);

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Update an Existing Team");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Contact Phone:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Team name:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Contact Email:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Contact Person:");

        jScrollPane6.setViewportView(contactPersonTextAreaUpdate_JButton);

        jScrollPane8.setViewportView(contactemailTextAreaUpdate_JButton);

        jScrollPane9.setViewportView(contactPhoneTextAreaUpdate_JButton);

        updateExistingTeam_Button.setText("UPDATE EXISTING TEAM");
        updateExistingTeam_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateExistingTeam_ButtonActionPerformed(evt);
            }
        });

        updateTeamComboBox_JPanel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        updateTeamComboBox_JPanel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                updateTeamComboBox_JPanelItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout updateexistingTeam_JPanelLayout = new javax.swing.GroupLayout(updateexistingTeam_JPanel);
        updateexistingTeam_JPanel.setLayout(updateexistingTeam_JPanelLayout);
        updateexistingTeam_JPanelLayout.setHorizontalGroup(
            updateexistingTeam_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateexistingTeam_JPanelLayout.createSequentialGroup()
                .addGroup(updateexistingTeam_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateexistingTeam_JPanelLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(updateexistingTeam_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(updateExistingTeam_Button)
                            .addGroup(updateexistingTeam_JPanelLayout.createSequentialGroup()
                                .addGroup(updateexistingTeam_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(updateexistingTeam_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                                    .addComponent(updateTeamComboBox_JPanel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(updateexistingTeam_JPanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(253, Short.MAX_VALUE))
        );
        updateexistingTeam_JPanelLayout.setVerticalGroup(
            updateexistingTeam_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateexistingTeam_JPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel7)
                .addGap(107, 107, 107)
                .addGroup(updateexistingTeam_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(updateTeamComboBox_JPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(updateexistingTeam_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(updateexistingTeam_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(27, 27, 27)
                .addGroup(updateexistingTeam_JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(updateExistingTeam_Button)
                .addContainerGap(116, Short.MAX_VALUE))
        );

        body_TabedPane.addTab("Update existing team", updateexistingTeam_JPanel);

        javax.swing.GroupLayout body_PanelLayout = new javax.swing.GroupLayout(body_Panel);
        body_Panel.setLayout(body_PanelLayout);
        body_PanelLayout.setHorizontalGroup(
            body_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body_TabedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        body_PanelLayout.setVerticalGroup(
            body_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(body_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(body_TabedPane))
        );

        body_TabedPane.getAccessibleContext().setAccessibleName("Team competition result");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header_JPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(body_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header_JPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    //EVENT HANDLERS
    private void displayTeamResults_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayTeamResults_ButtonActionPerformed
        // put all the shit for the button on the display team results here
        
        
    }//GEN-LAST:event_displayTeamResults_ButtonActionPerformed

    private void addNewCompResult_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewCompResult_ButtonActionPerformed
        // put all the shit for the button on the team comp results here
    }//GEN-LAST:event_addNewCompResult_ButtonActionPerformed

    private void addNewTeam_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewTeam_ButtonActionPerformed
/********
 Method: addNewTeam_ButtonActionPerformed()
 * Purpose: Event handler method for button click when adding a new team validates teh inputs first using ValidateNewTeam() method
 * if validated, then presents a confirmation dialog to the user if aggreed (yes button lciked), then the new team object is created and added to the
 * teamList ArrayList
 * Update the 2 jComboBoxes containing new teamList with the new team
 * 
 * Inputs: ActionEvent evt (the event object passed into the ActionPerformed() method) although not used in this example, the evt object can provide data
 * about the JButton that was clicked.
 * 
 * Output: void
 */

    // put all the shit for the button for the add new team here
        // check if teh text field are validated
        if (validateNewTeam() == true)
            {
                // get new team data string values
                String newTeamName = newTeamName_TextField.getText();
                String newContactPerson = newContactPerson_TextField.getText();
                String newContactPhone = newContactPhone_TextField.getText();
                String newContactEmail = newContactEmail_TextField.getText();

                int yesOrNo = JOptionPane.showConfirmDialog(null,"You are about to add a new team: " + newTeamName + "." + "\n" + 
                "Do you wish to proceed, yes or no?", "Add new team",
                JOptionPane.YES_NO_OPTION);

                // check if yes or no
                if (yesOrNo == JOptionPane.YES_OPTION)
                    {
                        // add the new team to the ArrayList teamList
                        teamList.add(new Team(newTeamName, newContactPerson, newContactPhone, newContactEmail));
                    }

                    // add code to display results in he coboboxes
                    //update new team list inot JComboBoxes
                    displayTeams();
            }

            else
                {
                    //no action
                }






    }//GEN-LAST:event_addNewTeam_ButtonActionPerformed

    private void updateExistingTeam_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateExistingTeam_ButtonActionPerformed
      //  put all the shit for the button to update existing teams
    }//GEN-LAST:event_updateExistingTeam_ButtonActionPerformed

    private void updateTeamComboBox_JPanelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_updateTeamComboBox_JPanelItemStateChanged
        // TODO add your handling code here:

            /*
    Method: updateTeam_ComboBoxItemStateChanged()
    Purpose: Event handler method for update team JComboBox option change
    displays team data for a chosen team name via displayTeamDetails() method
    inputs: ItemEvent evt (the event object passed inot hte ItemStateChagned() method) although not used in this example, the evt object can provide data 
    about the JComboBox
    outputs: void
    */

        
        if (comboBoxStatus == true)
        {
            displayTeamDetails();
        }
    }//GEN-LAST:event_updateTeamComboBox_JPanelItemStateChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {

//GEN-FIRST:event_formWindowClosing
    /****
     * method: formWindowClosing()
     * purpose: event handler method called whenever the app is closed from clicking the x button
     * inputs: WindowEvent evt (the event object passed into the formWindowClosing() method)
     * outputs: void
     */     

    // TODO add your handling code here:

    int yesOrNo = JOptionPane.showConfirmDialog(null, "Do you wish to save changes before closing, sarrr?", "SAVE CHANGES", JOptionPane.YES_NO_OPTION);

    if (yesOrNo ==JOptionPane.YES_OPTION)
        {
            //save compeition data
            //saveCompetionData();
            //save team data
            //saveTeamData();
        }
    else
        {
            // exit wihout saving
        }
    }

    private void saveCompetitionData()
        {
            //somehow work these out
        }

    private void saveTeamData()
        {

        }


//GEN-LAST:event_formWindowClosing

    //leave this area alone
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GoldCoastESportsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GoldCoastESportsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GoldCoastESportsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GoldCoastESportsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GoldCoastESportsGUI().setVisible(true);
        });
    }
    
     //leave this area alone

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> addNewCompResultComboBox_JPanel;
    private javax.swing.JButton addNewCompResult_Button;
    private javax.swing.JPanel addNewCompResult_JPannel;
    private javax.swing.JButton addNewTeam_Button;
    private javax.swing.JPanel addNewTeam_JPanel;
    private javax.swing.JPanel body_Panel;
    private javax.swing.JTabbedPane body_TabedPane;
    private javax.swing.JTable compResults_JTable;
    private javax.swing.JTextPane contactPersonTextAreaUpdate_JButton;
    private javax.swing.JTextPane contactPersonTextArea_JButton;
    private javax.swing.JTextPane contactPhoneTextAreaUpdate_JButton;
    private javax.swing.JTextPane contactPhoneTextArea_JButton;
    private javax.swing.JTextPane contactemailTextAreaUpdate_JButton;
    private javax.swing.JTextPane contactemailTextArea_JButton;
    private javax.swing.JTextPane dateAddCompResult_JPanel;
    private javax.swing.JButton displayTeamResults_Button;
    private javax.swing.JTextPane gameAddCompResult_JPanel;
    private javax.swing.JPanel header_JPanel;
    private javax.swing.JLabel img_Label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextPane locationAddCompResult_JPanel;
    private javax.swing.JTextPane pointsAddCompResult_JPanel;
    private javax.swing.JPanel teamCompResult_JPanel;
    private javax.swing.JTextPane teamNameTextArea_JButton;
    private javax.swing.JButton updateExistingTeam_Button;
    private javax.swing.JComboBox<String> updateTeamComboBox_JPanel;
    private javax.swing.JPanel updateexistingTeam_JPanel;
    // End of variables declaration//GEN-END:variables

    // private void resizeTableColumns() { TODO: DUPLICATE METHOD
    //     throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    // }
}
