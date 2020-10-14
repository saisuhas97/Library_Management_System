import java.awt.*;
import java.awt.event.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.*;

import net.proteanit.sql.DbUtils;

import java.sql.Connection;

public class MainPage extends JFrame
{
	JFrame mainFrame;
	JPanel controlPanel;
	JLabel headerLabel;
	
	static Connection conn;
	
	MainPage()
	{
		prepareGUI();
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				try{
					MainPage mainPage=new MainPage();
					//mainPage.setVisible(true);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		
	}
	
	void prepareGUI()
	{
		mainFrame=new JFrame("LIBRARY MANAGEMENT SYSTEM");
                mainFrame.setForeground(Color.blue);
		mainFrame.setSize(1000,750);
		mainFrame.setLocation(0,0);
		mainFrame.setLayout(new GridLayout(2,6));
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		controlPanel=new JPanel();
		controlPanel.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(controlPanel);
		GridBagLayout gbl_controlPanel=new GridBagLayout();
		gbl_controlPanel.columnWidths=new int[]{0,0};
		gbl_controlPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0};
		gbl_controlPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_controlPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		controlPanel.setLayout(gbl_controlPanel);
		
		//Heading
		headerLabel=new JLabel("LIBRARY MANAGEMENT SYSTEM",JLabel.CENTER);
		headerLabel.setFont(new Font("Calibri",Font.BOLD,36));
		headerLabel.setForeground(Color.green.darker().darker());
		headerLabel.setBackground(Color.white);
                
		/*GridBagConstraints gbc_lblHeader=new GridBagConstraints();
		gbc_lblHeader.fill=GridBagConstraints.BOTH;
		gbc_lblHeader.insets = new Insets(0, 0, 5, 0);
		gbc_lblHeader.gridx = 0;
		gbc_lblHeader.gridy = 1;
		//gbc_lblHeader.gridwidth=2;
		controlPanel.add(headerLabel, gbc_lblHeader);*/

		
		
		//search button
		JButton search=new JButton("BOOK SEARCH");
                search.setFont(new Font("Arial Black", Font.BOLD, 14));
                search.setBackground(Color.orange);
                search.setForeground(Color.white);
		search.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
					mainFrame.setVisible(false);
					try {
						new Search();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		GridBagConstraints gbc_btnSearch=new GridBagConstraints();
		gbc_btnSearch.fill=GridBagConstraints.HORIZONTAL;
		gbc_btnSearch.insets = new Insets(5, 0, 5,0);
		gbc_btnSearch.gridx = 0;
		gbc_btnSearch.gridy = 1;
		gbc_btnSearch.gridwidth=2;
		controlPanel.add(search, gbc_btnSearch);
		
				
		
		//Check Out Button
		JButton checkOut=new JButton("CHECK OUT");
                checkOut.setFont(new Font("Arial Black", Font.BOLD, 14));
                checkOut.setBackground(Color.orange);
                checkOut.setForeground(Color.white);
		checkOut.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				mainFrame.setVisible(false);
				new CheckOut();
			}
		});
		GridBagConstraints gbc_btnCheckOut=new GridBagConstraints();
		gbc_btnCheckOut.fill=GridBagConstraints.HORIZONTAL;
		gbc_btnCheckOut.insets = new Insets(5, 0, 5, 0);
		gbc_btnCheckOut.gridx = 0;
		gbc_btnCheckOut.gridy = 2;
		gbc_btnCheckOut.gridwidth=2;
		controlPanel.add(checkOut,gbc_btnCheckOut);
		
		//Check In Button
		JButton checkIn=new JButton("CHECK IN");
                checkIn.setFont(new Font("Arial Black", Font.BOLD, 14));
                checkIn.setBackground(Color.orange);
                checkIn.setForeground(Color.white);
		checkIn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				mainFrame.setVisible(false);
				new CheckIn();
			}
		});
		GridBagConstraints gbc_btnCheckIn=new GridBagConstraints();
		gbc_btnCheckIn.fill=GridBagConstraints.HORIZONTAL;
		gbc_btnCheckIn.insets = new Insets(5, 0, 5,0);
		gbc_btnCheckIn.gridx = 0;
		gbc_btnCheckIn.gridy = 3;
		gbc_btnCheckIn.gridwidth=2;
		controlPanel.add(checkIn, gbc_btnCheckIn);

		
		//New Borrower Button
		JButton newBorrower = new JButton("ADD NEW BORROWER");
                newBorrower.setFont(new Font("Arial Black", Font.BOLD, 14));
                newBorrower.setBackground(Color.orange);
                newBorrower.setForeground(Color.white);
                newBorrower.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				mainFrame.setVisible(false);
				new NewBorrower();
			}
		});
		GridBagConstraints gbc_btnNewBorrower=new GridBagConstraints();
		gbc_btnNewBorrower.fill=GridBagConstraints.HORIZONTAL;
		gbc_btnNewBorrower.insets = new Insets(5, 0, 5,0);
		gbc_btnNewBorrower.gridx = 0;
		gbc_btnNewBorrower.gridy = 4;
		gbc_btnNewBorrower.gridwidth=2;
		controlPanel.add(newBorrower,gbc_btnNewBorrower);
		
		//Fines Button
		JButton fines=new JButton("FINES");
                fines.setFont(new Font("Arial Black", Font.BOLD, 14));
                fines.setBackground(Color.orange);
                fines.setForeground(Color.white);		
                fines.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
		
				//
				mainFrame.setVisible(false);
				new Fines();
			}
		});
		GridBagConstraints gbc_btnFines=new GridBagConstraints();
		gbc_btnFines.fill=GridBagConstraints.HORIZONTAL;
		gbc_btnFines.insets = new Insets(5, 0, 5,0);
		gbc_btnFines.gridx = 0;
		gbc_btnFines.gridy = 5;
		gbc_btnFines.gridwidth=2;
		controlPanel.add(fines, gbc_btnFines);		
		
		//JLabel
		JLabel space=new JLabel(" ");
		GridBagConstraints gbc_lblSpace=new GridBagConstraints();
		gbc_lblSpace.fill=GridBagConstraints.HORIZONTAL;
		gbc_lblSpace.insets = new Insets(5, 0, 5, 0);
		gbc_lblSpace.gridx = 0;
		gbc_lblSpace.gridy = 6;
		controlPanel.add(space, gbc_lblSpace);		


		//Close Button
		JButton close=new JButton("EXIT");
                close.setFont(new Font("Arial Black", Font.BOLD, 14));
                close.setBackground(Color.white);
                close.setForeground(Color.red);
		close.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				mainFrame.dispose();
			}
		});
		GridBagConstraints gbc_btnClose=new GridBagConstraints();
		gbc_btnClose.fill=GridBagConstraints.HORIZONTAL;
		gbc_btnClose.insets = new Insets(5, 0, 5,0);
		gbc_btnClose.gridx = 1;
		gbc_btnClose.gridy = 7;
		gbc_btnClose.anchor=GridBagConstraints.PAGE_END;
		controlPanel.add(close, gbc_btnClose);		

		
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
	}
	
	
}