import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SoftwareFrame extends JFrame {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    JPanel mainPanel, tablePanel, buttonPanel;
    JTabbedPane tabbedPane;
    JLabel id, title, price, author, publisher;
    JTextField idField, titleField, priceField, authorField, publisherField;
    JButton saveButton, updateButton, deleteButton;
    JTable table;

    private void connectToDatabase() {
        Properties properties = new Properties();
        try (FileInputStream fs = new FileInputStream("C:\\Users\\Acer\\Documents\\sql-credentials\\mysql-abstract-details.properties")) {
            properties.load(fs);

            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            con = DriverManager.getConnection(url, username, password);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println((con != null) ? "Established" : "Connection failed");
    }

    // This function should work only when there is at least one entry in the table
    private void createTable() {
        try {
            String query = "Create Table Book(bookid int(4), title varchar(20) NOT NULL, price double(6,2), author varchar(20) , publisher varchar(10), primary key(bookid))";
            this.ps = con.prepareStatement(query);
            this.ps.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private static boolean tableExist(Connection conn) {
        boolean tExists = false;
        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet tables = meta.getTables(null, null, "Book", null);
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                if (tableName != null && tableName.equalsIgnoreCase("Book")) {
                    tExists = true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tExists;
    }

    // We'll create table when save button is clicked first time.
    private void insertTheQuery(int id,String title,double price, String author, String publisher) {
        try {
            if (!tableExist(con))
                createTable();
            String query = "insert into Book(bookid, title, price, author, publisher) values(?,?,?,?,?)";
            this.ps = con.prepareStatement(query);
            this.ps.setInt(1, id);
            this.ps.setString(2, title);
            this.ps.setDouble(3, price);
            this.ps.setString(4, author);
            this.ps.setString(5, publisher);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Data insertion failed.");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public SoftwareFrame() {
        connectToDatabase();

        mainPanel = new JPanel();
        tablePanel = new JPanel(new BorderLayout());
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tabbedPane = new JTabbedPane();

        setResizable(false);
        setTitle("Book Record Manager");
        setVisible(true);
        setSize(700, 500);
        mainPanel.setLayout(null);
        mainPanel.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();
        positionComponents();
        activateActionListeners();
    }

    private void positionComponents() {
        mainPanel.setBounds(5, 5, 490, 490);
        id.setBounds(170, 50, 100, 20);
        title.setBounds(170, 50 * 2, 100, 20);
        price.setBounds(170, 50 * 3, 100, 20);
        author.setBounds(170, 50 * 4, 100, 20);
        publisher.setBounds(170, 50 * 5, 100, 20);

        idField.setBounds(170 * 2, 51, 200, 20);
        titleField.setBounds(170 * 2, 51 * 2, 200, 20);
        priceField.setBounds(170 * 2, 51 * 3, 200, 20);
        authorField.setBounds(170 * 2, 51 * 4, 200, 20);
        publisherField.setBounds(170 * 2, 51 * 5, 200, 20);

        saveButton.setBounds(290, 320, 100, 40);


    }

    private void initComponents() {

        id = new JLabel("<html><h2>Book ID:</h2></html>");
        title = new JLabel("<html><h2>Title: </h2></html>");
        price = new JLabel("<html><h2>Price: </h2></html>");
        author = new JLabel("<html><h2>Author: </h2></html>");
        publisher = new JLabel("<html><h2>Publisher: </h2></html>");

        idField = new JTextField();
        titleField = new JTextField();
        priceField = new JTextField();
        authorField = new JTextField();
        publisherField = new JTextField();

        saveButton = new JButton("Save");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Book ID");
        model.addColumn("Title");
        model.addColumn("Price");
        model.addColumn("Author");
        model.addColumn("Publisher");
        table = new JTable(model);

        // on the main panel
        mainPanel.add(id);
        mainPanel.add(title);
        mainPanel.add(price);
        mainPanel.add(author);
        mainPanel.add(publisher);
        mainPanel.add(idField);
        mainPanel.add(titleField);
        mainPanel.add(priceField);
        mainPanel.add(authorField);
        mainPanel.add(publisherField);
        mainPanel.add(saveButton);

        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        tablePanel.add(buttonPanel, BorderLayout.SOUTH);

        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        tablePanel.add(new JScrollPane(table));

        tabbedPane.addTab("Data Entry", mainPanel);
        tabbedPane.addTab("Table View", tablePanel);

        add(tabbedPane);
    }

    private void activateActionListeners() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String title = titleField.getText();
                double price = Double.parseDouble(priceField.getText());
                String author = authorField.getText();
                String publisher = publisherField.getText();
                insertTheQuery(id,title,price,author,publisher);

                idField.setText("");
                titleField.setText("");
                priceField.setText("");
                authorField.setText("");
                publisherField.setText("");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SoftwareFrame::new);
    }
}