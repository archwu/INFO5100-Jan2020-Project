package ui.guiforcase4;

import dto.Vehicle;
import persist.VehicleManagerImpl;
import service.SearchFilter;
import service.SearchFilterElement;
import service.VehicleSearchFilter;
import service.VehicleSearchFilterElement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import static java.awt.Font.PLAIN;

public class InventoryInformation extends JFrame {
  int dID;
  //Use JTable to show the related info of that vehicle
  private JTable table;
  private VehicleManagerImpl vmi;
  DefaultTableModel vModel = new DefaultTableModel();


  public InventoryInformation(int dID) {
    this.dID = dID;
    vmi = new VehicleManagerImpl();
    create();
    populate();
    sorter();
    initialFrame();
  }

  private void create() {
    table = new JTable(vModel);
    String header[] = new String[]{"VehicleID", "VIN", "Make", "Model", "Price", "Mileage"};
    vModel.setColumnIdentifiers(header);
    table.setModel(vModel);

    /*table.setModel(new DefaultTableModel(){
      Class[] types = { Integer.class, Integer.class, String.class, String.class, Float.class, Integer.class };

      @Override
      public Class getColumnClass(int columnIndex) {
        return this.types[columnIndex];
      }
    });*/
    table.setEnabled(false);
  }

  private void populate(){
    table.getTableHeader().setFont(new Font("Arial", PLAIN, 15));
    Collection<Vehicle> veh = vmi.getVehiclesBasedOnDealerId(dID);
    for (Vehicle v : veh) {
      vModel.addRow(new Object[]{v.getVehicleId(), v.getVin(), v.getMake(), v.getModel(), v.getPrice(), v.getMileage()});
    }
  }

  private void sorter(){
    TableRowSorter<DefaultTableModel> sorter=new TableRowSorter<DefaultTableModel>(vModel);
    table.setRowSorter(sorter);

  }

  private void initialFrame() {

    JFrame frame = new JFrame("Inventory of Dealer" + this.dID);
    frame.setSize(570, 520);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel(null);
    panel.setBackground(new Color(0, 0, 0, 0));
    frame.add(panel);
    addComponents(frame, panel);
    frame.setVisible(true);
  }

  private void addComponents(JFrame frame, JPanel panel) {
    JLabel jl = new JLabel("Inventory of Dealer" + this.dID);
    jl.setFont(new Font("Arial", PLAIN, 20));
    jl.setForeground(Color.BLACK);
    jl.setHorizontalAlignment(JTextField.CENTER);
    jl.setBounds(140, 15, 280, 30);
    panel.add(jl);
    //(Ekie)Show the VehicleTable based on DealerID
    JScrollPane js = new JScrollPane(table);
    js.setBounds(80, 60, 400, 250);
    js.setBackground(Color.LIGHT_GRAY);
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.setFont(new Font("Arial", PLAIN, 15));

    js.setVisible(true);
    DefaultListCellRenderer renderer = new DefaultListCellRenderer();
    renderer.setHorizontalAlignment(SwingConstants.CENTER);
    //table.setCellRenderer(renderer);
    panel.add(js);
    JButton btn1 = new JButton("Modify");
    btn1.setBounds(80, 320, 160, 40);
    JButton btn2 = new JButton("Delete");
    btn2.setBounds(319, 320, 160, 40);
    JButton btn3 = new JButton("Add Vehicles");
    btn3.setBounds(80, 380, 160, 40);
    JButton btn4 = new JButton("Back");
    btn4.setBounds(319, 380, 160, 40);
    JButton[] jButtons = new JButton[]{btn1, btn2, btn3, btn4};
    Dimension preferredSize = new Dimension(120, 40);
    for (JButton jButton : jButtons) {
      jButton.setPreferredSize(preferredSize);
      jButton.setBackground(Color.white);
      jButton.setOpaque(true);
      jButton.setFont(new Font("Arial", PLAIN, 15));
      panel.add(jButton);
    }

    btn1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //Get the VehicleID selected vehicle
        try {
          int rowIndex = table.getSelectedRow();
          int vehID = (int) table.getValueAt(rowIndex, 0);
          Collection<Vehicle> veh = vmi.getVehiclesBasedOnDealerId(dID);
          Vehicle modifyV = new Vehicle();
          for (Vehicle v : veh) {
            if (v.getVehicleId() == vehID) {
              modifyV = v;
            }
          }
          new ModifyInventory(modifyV);
          frame.dispose();
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(panel, "Please select one vehicle to modify!");
        }

      }
    });
    //(Ekie)Delete vehicles
    btn2.addActionListener(e -> {
      try {
        //Step1: Delete the vehicle from the db
        int rowIndex = (int) table.getSelectedRow();
        int vehID = (int) table.getValueAt(rowIndex, 0);
        Vehicle deleteV = new Vehicle();
        Collection<Vehicle> veh = vmi.getVehiclesBasedOnDealerId(dID);
        //Use vehicleID to get Vehicle and pass it to deleteVehicle()
        for (Vehicle v : veh) {
          if (v.getVehicleId() == vehID) {
            deleteV = v;
          }
        }
        vmi.deleteVehicle(deleteV);
        table.updateUI();
        //Step2: Delete the vehicle on the screen
        JOptionPane.showMessageDialog(table, "Vehicle " + deleteV.getVehicleId() + " has been deleted");
        vModel.removeRow(rowIndex);
        int size = vModel.getRowCount();
        if (size == 0) { //No vehicles left, disable delete.
          btn2.setEnabled(false);
        } else { //Select an index.
          if (rowIndex == size) {
            rowIndex--;
          }
        /*table.setSelectedIndex(index);
        table.ensure;*/
        }
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(panel, "Please select one vehicle to delete!");
      }
    });

    btn3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new AddVehicles(dID);
        frame.dispose();
      }
    });

    btn4.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
        new OperationOptions(dID);
      }
    });
  }
}
