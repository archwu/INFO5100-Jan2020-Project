package ui.guiforcase4;

import com.toedter.calendar.JYearChooser;
import dto.Vehicle;
import persist.ExtractSingleColumnFromDB;
import persist.VehicleManagerImpl;
import service.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AddVehicles extends JFrame {
    int dID;
    JPanel p;
    ExtractSingleColumnFromDB connect = new ExtractSingleColumnFromDB();
    Vehicle vehicle = new Vehicle();
    VehicleManagerImpl vmi = new VehicleManagerImpl();
    DealerUtilities du = new DealerUtilities();
    List<MakeModelVer2> makeModelVer2s = MakeModelJsonPopulator.populateMakeModel();
    Map<String,String> colors = ColorJsonPopulator.populateColorContainer();
    public AddVehicles(int dID) {
        this.dID = dID;
        this.vehicle = vehicle;
        initialFrame();
    }

    private void initialFrame() {
        JFrame frame = new JFrame("Managing Inventory of Dealer " + this.dID);
        JPanel panel = new JPanel(null);
        frame.setSize(450, 650);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        addComponents(frame, panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addComponents(JFrame frame, JPanel panel) {
        JLabel jl = new JLabel("Dealer " + this.dID);
        jl.setFont(new Font("Arial", Font.PLAIN, 18));
        jl.setForeground(Color.BLACK);
        jl.setHorizontalAlignment(JTextField.CENTER);
        jl.setBounds(160, 10, 80, 30);
        panel.add(jl);
        String[] jLabelTexts = new String[]{"VIN:", "Make:", "Model:", "Year:", "Category:", "Price:",
                "Color:", "Miles:","Ratings","Image:"};
        JLabel[] jls = new JLabel[10];
        for (int i = 0; i < jls.length; i++) {
            jls[i] = new JLabel();
            jls[i].setText(jLabelTexts[i]);
            jls[i].setBounds(60, 50 + i * 50, 80,25);
            jls[i].setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(jls[i]);
        }
        JLabel jl1 = new JLabel("The VIN You Entered Already Exists! Please Enter A New VIN");
        jl1.setBounds(165, 70, 300, 25);
        jl1.setFont(new Font("Arial", Font.PLAIN, 10));
        jl1.setForeground(Color.RED);
        JLabel jl2 = new JLabel("");
        jl2.setBounds(165, 320, 300, 25);
        jl2.setFont(new Font("Arial", Font.PLAIN, 10));
        jl2.setForeground(Color.RED);
        JLabel jl3 = new JLabel("");
        jl3.setBounds(165, 420, 300, 25);
        jl3.setFont(new Font("Arial", Font.PLAIN, 10));
        jl3.setForeground(Color.RED);
        panel.add(jl2);
        panel.add(jl3);

        JTextField tf1 = new JTextField(10);
        tf1.setBounds(160, 50, 160, 25);
        JTextField tf2 = new JTextField(10);
        tf2.setBounds(160, 300, 160, 25);
        JTextField tf3 = new JTextField(10);
        tf3.setBounds(160, 400, 160, 25);
        JTextField tf4 = new JTextField(10);
        tf4.setBounds(160, 500, 160, 25);
        JTextField[] jtfs = new JTextField[]{tf1, tf2, tf3, tf4};
        for (int i = 0; i < jtfs.length; i++) {
            jtfs[i].setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(jtfs[i]);
        }
        CheckInput c1 = new CheckInput();
        c1.setLength(4);
        tf1.setDocument(c1);
        CheckInput c2 = new CheckInput();
        c2.setLength(10);
        tf2.setDocument(c2);
        CheckInput c3 = new CheckInput();
        c3.setLength(10);
        tf3.setDocument(c3);


//        if(tf1.getText().length() == 4) {
//            tf1.addKeyListener(new KeyListener() {
//            public void keyTyped(KeyEvent e) {
//                if (e.getKeyChar() = true) {
//                    JOptionPane.showMessageDialog(null, "Please Enter A Number In Four Digits");
//                }
//            }
//            public void keyPressed(KeyEvent e) {
//            }
//            public void keyReleased(KeyEvent e) {
//            }
//        });
//        }
        tf1.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                if (Character.isLetter(e.getKeyChar())) {
                    jl1.setText("Please Enter A Valid Number For VIN");
                }
            }
            public void keyPressed(KeyEvent e) {
            }
            public void keyReleased(KeyEvent e) {
            }
        });
        tf2.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                if (Character.isLetter(e.getKeyChar())) {
                    jl2.setText("Please Enter A Valid Number For Price!");
                }
            }
            public void keyPressed(KeyEvent e) {
            }
            public void keyReleased(KeyEvent e) {
            }
        });
        tf3.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                if (Character.isLetter(e.getKeyChar())) {
                    jl3.setText("Please Enter A Valid Number For Mileage!");
                }
            }
                public void keyPressed (KeyEvent e){
                }
                public void keyReleased (KeyEvent e){
                }
        });

        JButton btn1 = new JButton("Add");
        btn1.setBounds(60, 550, 120, 40);
        JButton btn2 = new JButton("Back");
        btn2.setBounds(220, 550, 120, 40);
        Dimension preferredSize = new Dimension(120, 40);
        JButton[] jButtons = new JButton[]{btn1, btn2};
        for (int i = 0; i < jButtons.length; i++) {
            jButtons[i].setPreferredSize(preferredSize);
            jButtons[i].setBackground(Color.WHITE);
            jButtons[i].setOpaque(true);
            jButtons[i].setFont(new Font("Arial", Font.PLAIN, 15));
            panel.add(jButtons[i]);
        }
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InventoryInformation(dID);
                frame.dispose();
            }
        });

        JYearChooser yc = new JYearChooser();
        yc.setBounds(165, 200, 155, 23);
        yc.setEndYear(2020);
        yc.setStartYear(1985);
        yc.setValue(2020);
        yc.setFont(new Font("Arial", Font.PLAIN, 15));
        yc.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(yc);

        JComboBox cmb1 = new JComboBox();
        cmb1.setBounds(160, 350, 160, 25);
        cmb1.setFont(new Font("Arial", Font.PLAIN, 15));
        for (Map.Entry<String, String> entry : colors.entrySet()) {
            int r = Integer.parseInt(entry.getValue().substring(1,3),16);
            int g = Integer.parseInt(entry.getValue().substring(3,5),16);
            int b = Integer.parseInt(entry.getValue().substring(5,7),16);
            Color c = new Color(r,g,b);
            p = new ColorCell(c,entry.getKey());
            cmb1.addItem(p);
        }
        ListCellRenderer renderer = new PanelComboBoxCellRenderer();
		    cmb1.setRenderer(renderer);
        JComboBox cmb2 = new JComboBox();
        cmb2.setBounds(160, 250, 160, 25);
        cmb2.addItem("New");
        cmb2.addItem("Used");
        cmb2.setFont(new Font("Arial", Font.PLAIN, 15));
        String[] nums = new String[]{String.valueOf(5),String.valueOf(4),String.valueOf(3),String.valueOf(2),String.valueOf(1)};
        JComboBox cmb3 = new JComboBox(nums);
        cmb3.setBounds(160, 450, 160, 25);
        cmb3.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(cmb1);
        panel.add(cmb2);
        panel.add(cmb3);
        String[] makes = new String[makeModelVer2s.size()];
        for(int i = 0; i < makeModelVer2s.size(); i++) {
            makes[i] = makeModelVer2s.get(i).getBrand();
        }
        JComboBox make = new JComboBox(makes);
        make.setBounds(160, 100, 160, 25);
        make.setFont(new Font("Arial", Font.PLAIN, 15));
        String makeValue = make.getSelectedItem().toString();
        List<String> models = makeModel(makeValue).getModels();
        JComboBox model = new JComboBox();
        String[] models1 = models.toArray(new String[models.size()]);
        model.setModel(new DefaultComboBoxModel(models1));
        model.setBounds(160, 150, 160, 25);
        model.setFont(new Font("Arial", Font.PLAIN, 15));
        make.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String makeValue = make.getSelectedItem().toString();
                    List<String> models = makeModel(makeValue).getModels();
                    String[] models1 = models.toArray(new String[models.size()]);
                    model.setModel(new DefaultComboBoxModel(models1));
                }

            }
        });

        panel.add(make);
        panel.add(model);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tf1.getText().equals("") || tf2.getText().equals("") || tf3.getText().equals("")) {
                    btn1.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Please  Fill In All Of" +
                        " VIN, Make, Model, Year, Category, Price, Color, Mileage To Add The Vehicle!");
                    btn1.setEnabled(true);
                } else if (!tf1.getText().equals("") && tf1.getText().length() < 4) {
                    btn1.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Please Enter A Number In Four Digits For VIN!");
                    btn1.setEnabled(true);
                } else {
                    try {
                        vehicle.setVin(Integer.parseInt(tf1.getText()));
                        vehicle.setDealerId(dID);
                        vehicle.setMake(make.getSelectedItem().toString());
                        vehicle.setModel(model.getSelectedItem().toString());
                        vehicle.setYear(yc.getYear());
                        vehicle.setCategory(cmb2.getSelectedItem().toString());
                        vehicle.setPrice(Float.parseFloat(tf2.getText()));
                        vehicle.setColor(cmb1.getSelectedItem().toString());
                        vehicle.setRatings(Integer.parseInt(cmb3.getSelectedItem().toString()));
                        vehicle.setMileage(Integer.parseInt(tf3.getText()));
                        if (du.validateVin(vehicle) == false) {
                            btn1.setEnabled(false);
                            JOptionPane.showMessageDialog(null,"The VIN You Entered Already Exists! Please Enter A New VIN");
                            btn1.setEnabled(true);
                        } else {
                            int vin1 = vmi.addVehicle(vehicle).getVin();
                            if(vin1 == Integer.parseInt(tf1.getText())) {
                                JOptionPane.showMessageDialog(null,"You have successfully added a car！");
                            }
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
    private MakeModelVer2 makeModel(String makeValue) {
        for (MakeModelVer2 mm : makeModelVer2s) {
            if (mm.getBrand().equals(makeValue)) {
                return mm;
            }
        }
        return null;
    }
}








