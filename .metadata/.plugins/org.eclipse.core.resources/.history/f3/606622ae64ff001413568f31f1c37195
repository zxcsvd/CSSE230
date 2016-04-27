import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JList;


public class Songs extends JFrame{
	private String name = null;
	private String sid = null;
	private String URL = null;
	private String length = null;
	private String aname = null;
	private String bname = null;
	
//	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Songs window = new Songs();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 * @param string 
	 */
	public Songs(String string) {
		Procedures temp = new Procedures();
		ResultSet t = temp.Detail(string, "Songs");
		
		try {
			while(t!= null && t.next()){
//				System.out.println(rs.getString("Name"));
				URL = t.getString("URL");
				length = t.getString("Length");
				sid = t.getString("Sid");
			}
		}catch (SQLException e) {
			
			System.out.println(e);
		}
		this.name = string;
		
		Procedures temp2 = new Procedures();
		ResultSet t2 = temp2.song_info(string);
		
		try {
			while(t2!= null && t2.next()){
//				System.out.println(rs.getString("Name"));
				aname = t2.getString("aname");
				 bname = t2.getString("bname");
			}
		}catch (SQLException e) {
			
			System.out.println(e);
		}
		initialize(string, URL, length,aname,bname);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String name, final String URL, String length,final String aname,final String bname) {
//		frame = new JFrame();
		this.setBounds(100, 100, 433, 321);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel.setBounds(85, 55, 54, 15);
		this.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Length");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(85, 96, 54, 23);
		this.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("URL");
		
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(85, 146, 40, 23);
		this.getContentPane().add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Go URL");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if (LoginInfo.userType().equals("0") || LoginInfo.userType().equals("1")){
					System.out.println("new user cannot listen the music");
				}else{
				try {
					java.awt.Desktop.getDesktop().browse(new URI(URL));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}catch (NullPointerException e){
					System.out.println("Sorry. Details of this song is not available.");
				}
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnNewButton.setBounds(58, 194, 98, 30);
		this.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Search Again");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchAlbum window = new SearchAlbum();
				window.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnNewButton_1.setBounds(166, 194, 98, 30);
		this.getContentPane().add(btnNewButton_1);
		
		JLabel nameLabel = new JLabel(name);
		nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		nameLabel.setBounds(158, 56, 192, 15);
		getContentPane().add(nameLabel);
		
		JLabel lengthLabel = new JLabel(length);
		lengthLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lengthLabel.setBounds(158, 101, 192, 15);
		getContentPane().add(lengthLabel);
		
		JLabel URLLabel = new JLabel(URL);
		if (LoginInfo.userType().equals("0")){
			URLLabel.setText("New user cannot see URL");
		}
		URLLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		URLLabel.setBounds(158, 151, 228, 15);
		getContentPane().add(URLLabel);
		
		JButton btnLike = new JButton("Like");
		btnLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Procedures log = new Procedures();
				LoginInfo tem = new LoginInfo();
				System.out.println(log.like(tem.userInfo(), sid));
			}
		});
		btnLike.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnLike.setBounds(274, 194, 98, 30);
		getContentPane().add(btnLike);
		
		JButton btnViewAlbum = new JButton("View Album");
		btnViewAlbum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Album window = new Album(aname);
				window.setVisible(true);
				dispose();
			}
		});
		btnViewAlbum.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnViewAlbum.setBounds(58, 233, 98, 30);
		getContentPane().add(btnViewAlbum);
		
		JButton btnViewBand = new JButton("View Band");
		btnViewBand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Band window = new Band(bname);
				window.setVisible(true);
				dispose();
			}
		});
		btnViewBand.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnViewBand.setBounds(166, 234, 98, 30);
		getContentPane().add(btnViewBand);
	}
}
