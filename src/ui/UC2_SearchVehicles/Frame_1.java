package ui.UC2_SearchVehicles;


//import dto.*;

import dto.Dealer;
import dto.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;

public class Frame_1 extends JFrame {

    Dealer dealer;
    Collection<Vehicle> vehicles;

    ArrayList<JLabel> lblList;
    JLabel lbl_headline, lbl_make, lbl_module, lbl_year, lbl_gif, lbl_price, lbl_Err_YearEnd, lbl_to;
    ImageIcon icon;
    ArrayList<JComboBox<String>> cbbList;
    JComboBox cbb_make, cbb_module, cbb_price;
    JComboBox<Integer> cbb_yearStart, cbb_yearEnd;
    ArrayList<JButton> jbList;
    ArrayList<ImageIcon> imageList;
    JFrame jf;

    final static int yInternal = 100;
    final static int xInterval = 100;

    public Frame_1(Dealer dealer) {
        this.dealer = dealer;
        getVehiclesOnDealerID(dealer.getDealerId());

        InitialComponents();
        AddComponents();
        setVisible(true);
    }// querty database and fill ArrayList<Vehicles> with data;

    private void getVehiclesOnDealerID(int dearID) {

    }

    private void AddComponents() {
        for (JLabel jLabel : lblList) {
            getContentPane().add(jLabel);
        }

        for (JComboBox<String> jComboBox : cbbList) {
            getContentPane().add(jComboBox);
        }

        for(JButton jb : jbList){
            getContentPane().add(jb);
        }

    }
    private void InitialComponents() {
        InitFrame();
        InitLabels();
        InitComboBox();
        InitButtons();
        InitImageIcon();
    }
    private void InitImageIcon(){
        ImageIcon icon = new ImageIcon("src/ui/UC2_SearchVehicles/Dealer.png");
        lbl_gif = new JLabel(icon);
        lbl_gif.setBounds(350, 100, 400, 400);
        getContentPane().add(lbl_gif);
    }
    private void InitButtons(){
        jbList = new ArrayList<JButton>();
        JButton search = new JButton("Search");
        search.setBounds(lbl_price.getX() + xInterval * 1 / 2, lbl_price.getY() + yInternal * 2 / 3, 180, 40);
        search.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                lbl_gif.setVisible(true);
                Frame_2 f2 = new Frame_2("default");
                f2.setBounds(100, 100, 600, 700);
                f2.setVisible(true);
            }

            private boolean compareYear(String yearInput, String yearInputEnd) {
                int yearStart = Integer.parseInt(yearInput);
                int yearEnd = Integer.parseInt(yearInputEnd);
                return yearEnd >= yearStart;
            }

        });
        jbList.add(search);
    }

    private void InitComboBox() {
        cbbList = new ArrayList<>();

        cbb_make = new JComboBox();
        cbb_make.setBounds(lbl_make.getX() + xInterval, lbl_make.getY(), 100, 20);
        cbbList.add(cbb_make);

        cbb_module = new JComboBox();
        cbb_module.setBounds(cbb_make.getX(), lbl_module.getY(), 100, 20);
        cbbList.add(cbb_module);

        cbb_yearStart = new JComboBox<>(FrameUtilities.initStartYearModel());
        cbb_yearStart.setBounds(lbl_year.getX() + xInterval - 30, lbl_year.getY(), 60, 20);
        this.add(cbb_yearStart);

        cbb_yearStart.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    int startYear = Integer.parseInt(e.getItem().toString());
                    cbb_yearEnd.setModel(new DefaultComboBoxModel(FrameUtilities.initEndYearModel(startYear)));
                }

            }
        });

        cbb_yearEnd = new JComboBox<>();
        cbb_yearEnd.setBounds(lbl_year.getX() + xInterval + 70, lbl_year.getY(), 60, 20);
        cbb_yearEnd.setModel(new DefaultComboBoxModel(FrameUtilities.initEndYearModel(1990)));
        this.add(cbb_yearEnd);

        cbb_price = new JComboBox<>(FrameUtilities.initPriceModel());
        cbb_price.setBounds(cbb_make.getX(), lbl_price.getY(), 100, 20);
        this.add(cbb_price);

        // For Testing
        cbb_make.setModel(new DefaultComboBoxModel(FrameUtilities.createMake()));
        cbb_module.setModel(new DefaultComboBoxModel(FrameUtilities.createModel(cbb_make.getSelectedItem().toString())));
        cbb_make.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("User Select" + cbb_make.getSelectedItem());
                }
                cbb_module.setModel(new DefaultComboBoxModel(FrameUtilities.createModel(cbb_make.getSelectedItem().toString())));
            }
        });
    }

    private void InitLabels() {
        lblList = new ArrayList<>();

        lbl_headline = new JLabel("Dealer " + dealer.getDealerName());
        lbl_headline.setFont(new Font("B", Font.BOLD, 20));
        lbl_headline.setBounds(300, 30, 300, 20);
        lblList.add(lbl_headline);

        lbl_make = new JLabel("Make", JLabel.RIGHT);
        lbl_make.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_make.setBounds(100, 100, 50,20);
        lblList.add(lbl_make);

        lbl_module = new JLabel("Module", 4);
        lbl_module.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_module.setBounds(lbl_make.getX(), lbl_make.getY() + yInternal, 50,20);
        lblList.add(lbl_module);

        lbl_year = new JLabel("Year", 4);
        lbl_year.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_year.setBounds(lbl_make.getX(), lbl_module.getY() + yInternal, 50,20);
        lblList.add(lbl_year);

        lbl_price = new JLabel("Price", JLabel.RIGHT);
        lbl_price.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_price.setBounds(lbl_make.getX(), lbl_year.getY() + yInternal, 50,20);
        lblList.add(lbl_price);

        lbl_to = new JLabel("to", JLabel.RIGHT);
        lbl_to.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl_to.setBounds(lbl_make.getX()+ 105, lbl_year.getY(), 50, 20);
        lblList.add(lbl_to);

    }

    private void InitFrame() {
        setBounds(00, 00, 800, 650);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("5100 Final Project UserCase 2");

        getContentPane().setLayout(null);
        jf =this;
    }

    public static void main(String[] args) {
        Dealer d = new Dealer();
        d.setDealerId(15);
        d.setDealerName("default");
        new Frame_1(d);
    }

}

