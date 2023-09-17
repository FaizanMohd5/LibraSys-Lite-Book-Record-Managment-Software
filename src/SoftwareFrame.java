import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SoftwareFrame extends JFrame{
    JPanel mainPanel, tablePanel, buttonPanel;
    JTabbedPane tabbedPane;
    JLabel id,title,price,author,publisher;
    JTextField idField, titleField, priceField, authorField, publisherField;
    JButton saveButton, updateButton, deleteButton;
    JTable table;
    public SoftwareFrame(){
        mainPanel = new JPanel();
        tablePanel = new JPanel(new BorderLayout());
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tabbedPane = new JTabbedPane();

        setResizable(false);
        setTitle("Book Record Manager");
        setVisible(true);
        setSize(700,500);
        mainPanel.setLayout(null);
        mainPanel.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();
        positionComponents();
    }
    private void positionComponents() {
        mainPanel.setBounds(5,5,490,490);
        id.setBounds(170,50,100,20);
        title.setBounds(170,50*2,100,20);
        price.setBounds(170,50*3,100,20);
        author.setBounds(170,50*4,100,20);
        publisher.setBounds(170,50*5,100,20);

        idField.setBounds(170*2,51,200,20);
        titleField.setBounds(170*2,51*2,200,20);
        priceField.setBounds(170*2,51*3,200,20);
        authorField.setBounds(170*2,51*4,200,20);
        publisherField.setBounds(170*2,51*5,200,20);

        saveButton.setBounds(290,320,100,40);


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

        tabbedPane.addTab("Data Entry",mainPanel);
        tabbedPane.addTab("Table View",tablePanel);

        add(tabbedPane);
    }
    public static void main(String []args){
        SwingUtilities.invokeLater(SoftwareFrame::new);
    }
}