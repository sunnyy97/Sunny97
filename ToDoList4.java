import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;

class ListCourses extends JFrame implements ActionListener,MouseListener {
  Vector<String> columnName;	//ǥ�� ���� ����
  Vector<Vector<String>> rowData;	//ǥ�ȿ� ����ũ���� ������ vector ���
  JTable table = null; 
  TodoTableModel model = null;	//�ϴ� �׳� �����ϰ� null���� �ϴ� �־���!
  
  
  JCheckBox check;
  JTextField todoList;	// ���� ������ JTextField�� Ÿ����
  JButton addB, deleteB;	//�߰� ��ư, ���� ��ư
  
  JScrollPane tableSP;
  int row;
  
  ListCourses() {
    Container ct = getContentPane();
    ct.setLayout(new BorderLayout());
    
    JPanel top = new JPanel();	// ����Ʈ �ۼ��ϴ� ��ܺκ�
    JPanel center = new JPanel();	//����Ʈ�� ����Ǵ� ���ͺκ�
    JPanel bottom = new JPanel();	//����Ʈ�� ���� �Ǵ� �ۼ��ϴ� ��ư �����ϴ� �ϴܺκ�
    
    ct.add(top, BorderLayout.NORTH);
    ct.add(center, BorderLayout.CENTER);
    ct.add(bottom, BorderLayout.SOUTH);
    
    columnName = new Vector<String>();
    columnName.add("üũ");
    columnName.add("����");
    
   /* listTable.getColumn("üũ").setPreferredWidth(50);
    listTable.getColumn("����").setPreferredWidth(100); */
    
    
    rowData = new Vector<Vector<String>>();
    model = new TodoTableModel(rowData, columnName);
    table = new JTable(model);
    tableSP = new JScrollPane(table);
    
    // setting dimensions for tablesp
    tableSP.setPreferredSize(new Dimension(200, 450));
    TableColumnModel columnModel = table.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(10);
    columnModel.getColumn(1).setWidth(100);
    
    check = new JCheckBox();
    todoList = new JTextField(15);
    addB = new JButton("�߰�");
    deleteB = new JButton("����");
    
    
    addB.addActionListener(this);
    deleteB.addActionListener(this);
    table.addMouseListener(this);
    
    
    top.setLayout(new FlowLayout());
    top.add(new JLabel("TO DO LIST"));
    
    center.setLayout(new FlowLayout());
    center.add(check);	center.add(todoList);
    center.add(tableSP);
    
    bottom.setLayout(new FlowLayout());
    bottom.add(addB);				bottom.add(deleteB);	
    
  }
  
  public void actionPerformed(ActionEvent ae) {
    String todo = todoList.getText();
      Vector txt = new Vector();
      txt.add(check.isSelected());
      txt.add(todo);

    if(ae.getActionCommand().equals("�߰�")) {
      
      rowData.add(txt);
      todoList.setText("");	//�߰���ư ������ JTextField ȭ�� clear
      //table.updateUI();
    }
    else 
      rowData.remove(row);

      table.updateUI();
    
  }
  public void mouseClicked(MouseEvent ae) {
    row = table.getSelectedRow();
   // check.setText((String)model.getValueAt(row,0));
    todoList.setText((String)model.getValueAt(row,1));
  }
    public void mousePressed (MouseEvent ae) {}
    public void mouseReleased (MouseEvent ae) {}
    public void mouseEntered (MouseEvent ae) {}
    public void mouseExited (MouseEvent ae) {}

}

class ToDoList4 {
  public static void main(String[] args) {
    ListCourses win = new ListCourses();
    win.setTitle("TODOLIST");
    win.setSize(300, 500);
    win.setLocation(1000,100);
    win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    win.show();
  }
}

class TodoTableModel extends DefaultTableModel {
  public TodoTableModel(Vector<Vector<String>> rowData, Vector<String> columnName) {
    super(rowData,columnName);
  }

  
    public Class<?> getColumnClass(int columnIndex) {
    return columnIndex == 0 ? Boolean.class : String.class;
  }

  
  public boolean isCellEditable(int row, int column) {
    return column == 0;
  }
}
    

