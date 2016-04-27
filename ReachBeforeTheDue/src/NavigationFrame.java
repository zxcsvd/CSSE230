import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import javax.swing.BoundedRangeModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class NavigationFrame extends JFrame {
	public Hashtable<String, Location> mapInformation = new Hashtable<String, Location>();
	private JPanel contentPane;
	public JLabel trip = new JLabel("");
	private JTextField txtDestination;
	private JTextField txtWhereYouAre;
	private ArrayList<Point2D> clickedpt;
	private ArrayList<Line2D.Double> lineToDraw = new ArrayList<Line2D.Double>();
	private int searchMode = 0;
	private JTextArea txtPath;
    final static float dash1[] = {10.0f};
    final static BasicStroke dashed =
            new BasicStroke(8.0f,
                            BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_MITER,
                            10.0f, dash1, 0.0f);

	/**
	 * Launch the application.
	 */
	
	
//	public static void main(String[] args) {
		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					NavigationFrame frame = new NavigationFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	/**
	 * Create the frame.
	 */
	public NavigationFrame() {
		loadMapInformation();
		clickedpt = new ArrayList<Point2D>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		trip.setText("Trip Planner");
		trip.setFont(new Font("Lato", Font.PLAIN, 24));
		trip.setBounds(1000, 280, 250, 100);
		
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/disneyland_map.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(34, 63, 750, 420);
		lblNewLabel.addMouseListener(new MouseListener(){
		

			@Override
			public void mouseClicked(MouseEvent e) {
				switch (searchMode){
				case 0:
					int x = e.getX();
					int y = e.getY();
					Point2D.Double newpt = new Point2D.Double(x,y);
					clickedpt.add(newpt);
					if(clickedpt.size() == 1){
						Location pointA = mapInformation.get("UFO");
						for(String key : mapInformation.keySet()){
							Location tempLocation = mapInformation.get(key);
							double driftErrorA = findDistance(clickedpt.get(0), pointA.getCoordinate());
							if(driftErrorA > findDistance(clickedpt.get(0), tempLocation.getCoordinate()) ){
								if(!tempLocation.isCross)
									pointA = tempLocation;
							}
						}
						txtDestination.setText(pointA.getName() + "-> End" );

					}
					if(clickedpt.size() == 2){
						
						drawLine(clickedpt);
						clickedpt.clear();
						
					}
					break;
				case 1:
					break;
					
				case 2:
					int xc = e.getX();
					int yc = e.getY();
					Point2D.Double newptc = new Point2D.Double(xc,yc);
					clickedpt.add(newptc);
					if(clickedpt.size() == 1){
						Location pointA = mapInformation.get("UFO");
						for(String key : mapInformation.keySet()){
							Location tempLocation = mapInformation.get(key);
							double driftErrorA = findDistance(clickedpt.get(0), pointA.getCoordinate());
							if(driftErrorA > findDistance(clickedpt.get(0), tempLocation.getCoordinate()) ){
								if(!tempLocation.isCross)
									pointA = tempLocation;
							}
						}
						txtDestination.setText(pointA.getName() + "-> End" );

					}
					if(clickedpt.size() == 2){
						
						drawLineTime(clickedpt);
						clickedpt.clear();
						
					}
					break;
					
				case 3:
					int xa = e.getX();
					int ya = e.getY();
					Point2D.Double newpta = new Point2D.Double(xa,ya);
					clickedpt.add(newpta);
					Location pointA = mapInformation.get("UFO");
					for(String key : mapInformation.keySet()){
						Location tempLocation = mapInformation.get(key);
						double driftErrorA = findDistance(clickedpt.get(0), pointA.getCoordinate());
						if(driftErrorA > findDistance(clickedpt.get(0), tempLocation.getCoordinate()) ){
							if(!tempLocation.isCross)
								pointA = tempLocation;
						}
					}
					try{
						int limitDis = Integer.parseInt(txtWhereYouAre.getText());
						Dijkstra(pointA.getName(), limitDis/4);
						
						
						Location result = new Location(new Coordinate(-1000, -1000),"", true, 0, 0);
						for(String key : mapInformation.keySet()){
							Location tempLocation = mapInformation.get(key);
							if(tempLocation.rateTotal > result.rateTotal){
								result = tempLocation;
							}
						}					
						String path = "";
						Location current = result;
						System.out.println("Actuall: " + result.distance*4 + "m Limit: "+ limitDis + "m Interest Rate: " + result.rateTotal);
						Location a = current;
						Location b = a.tempLink;
						path += a.getName() + "\n";
						if(b==null){
							break;
						}
						if(!b.isCross)
						path += b.getName() + "\n";
						if(b!=null){
							Point2D.Double pa =  new Point2D.Double(a.getX() + 34, a.getY() + 90);
							Point2D.Double pb =  new Point2D.Double(b.getX() + 34, b.getY() + 90);
							Line2D.Double line = new Line2D.Double(pa, pb);
							lineToDraw.add(line);
							while(b.tempLink!=null){
								a = b;
								b = a.tempLink;
								if(!b.isCross)
								path += b.getName() + "\n";
								pa =  new Point2D.Double(a.getX() + 34, a.getY() + 90);
								pb =  new Point2D.Double(b.getX() + 34, b.getY() + 90);
								line = new Line2D.Double(pa, pb);
								lineToDraw.add(line);
								
							}
						}
						txtPath.setText(path);
						repaint();
						
						clickedpt.clear();
						reset();	
					}catch (NumberFormatException t){
						System.out.println("Please be nice to our application :(");
					}
	
					
					break;
					
				case 4:
					int xb = e.getX();
					int yb = e.getY();
					Point2D.Double newptb = new Point2D.Double(xb,yb);
					clickedpt.add(newptb);
					Location point = mapInformation.get("UFO");
					for(String key : mapInformation.keySet()){
						Location tempLocation = mapInformation.get(key);
						double driftErrorA = findDistance(clickedpt.get(0), point.getCoordinate());
						if(driftErrorA > findDistance(clickedpt.get(0), tempLocation.getCoordinate()) ){
							if(!tempLocation.isCross)
								point = tempLocation;
						}
					}
					try{
						int limitTime = Integer.parseInt(txtWhereYouAre.getText());
						DijkstraTime(point.getName(), limitTime);
						
						
						Location result = new Location(new Coordinate(-1000, -1000),"", true, 0, 0);
						for(String key : mapInformation.keySet()){
							Location tempLocation = mapInformation.get(key);
							if(tempLocation.timeTotal > result.timeTotal){
								result = tempLocation;
							}
						}					
						String path = "";
						Location current = result;
						System.out.println("Actuall: " + result.timeTotal + "min Limit: "+ limitTime + "min Interest Rate: " + result.rateTotal);
						Location a = current;
						Location b = a.tempLink;
						if(b==null){
							break;
						}
						if(!b.isCross)
						path += a.getName() + "\n";

						if(!b.isCross)
						path += b.getName() + "\n";
						if(b!=null){
							Point2D.Double pa =  new Point2D.Double(a.getX() + 34, a.getY() + 90);
							Point2D.Double pb =  new Point2D.Double(b.getX() + 34, b.getY() + 90);
							Line2D.Double line = new Line2D.Double(pa, pb);
							lineToDraw.add(line);
							while(b.tempLink!=null){
								a = b;
								b = a.tempLink;
								if(!b.isCross)
								path += b.getName() + "\n";
								pa =  new Point2D.Double(a.getX() + 34, a.getY() + 90);
								pb =  new Point2D.Double(b.getX() + 34, b.getY() + 90);
								line = new Line2D.Double(pa, pb);
								lineToDraw.add(line);
								
							}
						}
						txtPath.setText(path);
						repaint();
						
						clickedpt.clear();
						reset();	
					}catch (NumberFormatException t){
						System.out.println("Please be nice to our application :(");
					}
					break;
				}

				//System.out.println(clickedpt);
			}
			public void drawLineTime(ArrayList<Point2D> points) {
				
				Location pointA = mapInformation.get("UFO");
				Location pointB = mapInformation.get("UFO");
				for(String key : mapInformation.keySet()){
					Location tempLocation = mapInformation.get(key);
					double driftErrorA = findDistance(points.get(0), pointA.getCoordinate());
					double driftErrorB = findDistance(points.get(1), pointB.getCoordinate());
					if(driftErrorA > findDistance(points.get(0), tempLocation.getCoordinate()) ){
						if(!tempLocation.isCross)
							pointA = tempLocation;
					}
					if(driftErrorB > findDistance(points.get(1), tempLocation.getCoordinate()) ){
						if(!tempLocation.isCross)
							pointB = tempLocation;
					}
				}
				//LinkedList<Location> route = searchroute(pointA.getName(),pointB.getName());
				DijkstraTimeOnly(pointA.getName());
				txtDestination.setText(pointA.getName() + "->" + pointB.getName());
				
				//LinkedList<Location> a = searchroute("Adventureland","Adventureland");
//				Location result = new Location(new Coordinate(-1000, -1000),"", true, 0, 0);
//				for(String key : mapInformation.keySet()){
//					Location tempLocation = mapInformation.get(key);
//					if(tempLocation.timeTotal > result.timeTotal){
//						result = tempLocation;
//					}
//				}					
				String path = "";
//				Location current = result;
				//System.out.println("Actuall: " + result.timeTotal + "min Limit: "+ limitTime + "min Interest Rate: " + result.rateTotal);
				Location a = pointB;
				Location b = a.tempLink;
				if(b!=null)
				if(!b.isCross)
				path += a.getName() + "\n";
				if(b!=null)
				if(!b.isCross)
				path += b.getName() + "\n";
				if(b!=null){
					Point2D.Double pa =  new Point2D.Double(a.getX() + 34, a.getY() + 90);
					Point2D.Double pb =  new Point2D.Double(b.getX() + 34, b.getY() + 90);
					Line2D.Double line = new Line2D.Double(pa, pb);
					lineToDraw.add(line);
					while(b.tempLink!=null){
						a = b;
						b = a.tempLink;
						if(!b.isCross)
						path += b.getName() + "\n";
						pa =  new Point2D.Double(a.getX() + 34, a.getY() + 90);
						pb =  new Point2D.Double(b.getX() + 34, b.getY() + 90);
						line = new Line2D.Double(pa, pb);
						lineToDraw.add(line);
						
					}
				}
				txtPath.setText(path);
				repaint();
				
				clickedpt.clear();
				reset();	
			}
			public void drawLine(ArrayList<Point2D> points) {
				
				Location pointA = mapInformation.get("UFO");
				Location pointB = mapInformation.get("UFO");
				for(String key : mapInformation.keySet()){
					Location tempLocation = mapInformation.get(key);
					double driftErrorA = findDistance(points.get(0), pointA.getCoordinate());
					double driftErrorB = findDistance(points.get(1), pointB.getCoordinate());
					if(driftErrorA > findDistance(points.get(0), tempLocation.getCoordinate()) ){
						if(!tempLocation.isCross)
							pointA = tempLocation;
					}
					if(driftErrorB > findDistance(points.get(1), tempLocation.getCoordinate()) ){
						if(!tempLocation.isCross)
							pointB = tempLocation;
					}
				}
				LinkedList<Location> route = searchroute(pointA.getName(),pointB.getName());
				
				txtDestination.setText(pointA.getName() + "->" + pointB.getName());
				
				//LinkedList<Location> a = searchroute("Adventureland","Adventureland");
				Iterator<Location> it = route.iterator();
				String s = "";
				for(int i=0;i<route.size();i++){
					Location temp = it.next();
					if(!temp.isCross){
						s = s+ temp.name+"\n";
					}
				}
				//System.out.println(s);
				txtPath.setText(s);
				
				
				
				if(route.peek()== null){
					
				}else{
					Location a = route.pop();
					Location b = route.pop();
					Point2D.Double pa =  new Point2D.Double(a.getX() + 34, a.getY() + 90);
					Point2D.Double pb =  new Point2D.Double(b.getX() + 34, b.getY() + 90);
					Line2D.Double line = new Line2D.Double(pa, pb);
					lineToDraw.add(line);
					while(route.peek()!=null){
						a = b;
						b = route.pop();
						pa =  new Point2D.Double(a.getX() + 34, a.getY() + 90);
						pb =  new Point2D.Double(b.getX() + 34, b.getY() + 90);
						line = new Line2D.Double(pa, pb);
						lineToDraw.add(line);
					}
				}	
				repaint();
				
			}

			public double findDistance(Point2D point, Coordinate c){
				int cx = c.getX();
				int cy = c.getY();
				int px = (int)point.getX();
				int py = (int)point.getY();
				double result = Math.sqrt((Math.pow(cx-px, 2.0) + Math.pow(cy-py, 2.0)));
				return result;
			}
			
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub.
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub.
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub.
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub.
				
			}
			
		});
		contentPane.add(lblNewLabel);
		contentPane.add(trip);
//		JButton btnNewButton = new JButton("Detele attraction");
//		btnNewButton.setFont(new Font("Lato", Font.PLAIN, 15));
//		btnNewButton.setBounds(807, 439, 156, 44);
//		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Route by time");
		btnNewButton_1.setFont(new Font("Lato", Font.PLAIN, 15));
		btnNewButton_1.setBounds(62, 518, 250, 57);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchMode = 2;
				System.out.println(searchMode);
				clickedpt.clear();
			}
		});
		contentPane.add(btnNewButton_1);
		
		txtDestination = new JTextField();
		txtDestination.setFont(new Font("Lato", Font.PLAIN, 15));
		txtDestination.setText("Start->End");
		txtDestination.setBounds(807, 63, 156, 44);
		contentPane.add(txtDestination);
		txtDestination.setColumns(10);
		
		txtWhereYouAre = new JTextField();
		txtWhereYouAre.setFont(new Font("Lato", Font.PLAIN, 15));
		txtWhereYouAre.setText("Max Time/distance you want");
		txtWhereYouAre.setBounds(1000, 350, 240, 44);
		contentPane.add(txtWhereYouAre);
		txtWhereYouAre.setColumns(10);
		
		
		
		txtPath = new JTextArea();
		txtPath.setFont(new Font("Lato", Font.PLAIN, 15));
		txtPath.setText("");
//		txtPath.setBounds(807, 117, 144, 365);
		JScrollPane scroll = new JScrollPane(txtPath);
//	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		contentPane.add(txtPath);
//		contentPane.add(scroll);
//	    this.getContentPane().add(scroll);
		scroll.setBounds(807, 117, 160, 365);
		add(scroll);
		
		JButton btnRouteByDistance = new JButton("Route by distance");
		btnRouteByDistance.setFont(new Font("Lato", Font.PLAIN, 15));
		btnRouteByDistance.setBounds(374, 518, 250, 57);
		btnRouteByDistance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchMode = 0;
				System.out.println(searchMode);
				clickedpt.clear();
			}
		});
		contentPane.add(btnRouteByDistance);
		
		JButton btnRouteByRate = new JButton("Route by rate");
		btnRouteByRate.setFont(new Font("Lato", Font.PLAIN, 15));
		btnRouteByRate.setBounds(687, 518, 250, 57);
		btnRouteByRate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchMode = 0;
				System.out.println(searchMode);
				InterestingRank();
				clickedpt.clear();
			}
		});
		contentPane.add(btnRouteByRate);
		
		JButton btnLimitTime = new JButton("Switch to limited time(min) mode");
		btnLimitTime.setFont(new Font("Lato", Font.PLAIN, 10));
		btnLimitTime.setBounds(1000, 518, 250, 57);
		btnLimitTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtWhereYouAre.setText("");
				trip.setText("Click on the map to set the start point");
				trip.setFont(new Font("Lato", Font.PLAIN, 12));
				searchMode = 4;
				System.out.println(searchMode);
				clickedpt.clear();
			}

		});
		
		contentPane.add(btnLimitTime);
		
		
		JButton btnLimitDis = new JButton("Switch to limited Distance(m) mode");
		
		btnLimitDis.setFont(new Font("Lato", Font.PLAIN, 10));
		btnLimitDis.setBounds(1000, 430, 250, 57);
		btnLimitDis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtWhereYouAre.setText("");
				searchMode = 3; 
				trip.setText("Click on the map to set the start point");
				trip.setFont(new Font("Lato", Font.PLAIN, 12));
				System.out.println(searchMode);
				clickedpt.clear();
			}


		});
		
		contentPane.add(btnLimitDis);
		

	}
	public void InterestingRank(){
		Queue<Location> openQueue = new PriorityQueue<Location>(60, new RateComparator()); 
		Set<Entry<String, Location>> entrySet = this.mapInformation.entrySet();
		for (Entry a :entrySet){
			openQueue.add((Location) a.getValue());
		}
		String s = "";
		while(!openQueue.isEmpty()){
			Location x = openQueue.poll();
			if(!x.isCross){
				s = s+ x.name+": " + x.rate + "\n";
			}
		}
		txtPath.setText(s);
	}

	public void paint (Graphics g) {
		
        super.paint(g);  // fixes the immediate problem.
        
        Graphics2D g2 = (Graphics2D) g;
        int cap = BasicStroke.CAP_BUTT;
        int join = BasicStroke.JOIN_MITER;
//        BasicStroke thick = new BasicStroke(10, cap, join);
//        g2.setStroke(thick);


        g2.setStroke(dashed);
        g2.setColor(Color.RED);
        //Line2D lin = new Line2D.Float(100, 100, 250, 260);
        if(!lineToDraw.isEmpty()){
        	for(Line2D.Double line: lineToDraw){
        		g2.draw(line);
        	}
        }
        lineToDraw.clear();
        for (String key : mapInformation.keySet()){
        	Location tempLocation = mapInformation.get(key);
        	tempLocation.father = null;
        }
	}
	
    public LinkedList<Location> searchroute(String start, String end){
    	if(start.compareTo(end) == 0){
    		return new LinkedList<Location>();
    	}
        LinkedList<Location> answer = new LinkedList<Location>();
        
        Queue<Location> openQueue = new PriorityQueue<Location>(11, new NodeComparator()); 
        Location startpoint = mapInformation.get(start);
        startpoint.setreal(0);
        Location endpoint = mapInformation.get(end);
        openQueue.add(startpoint);
        Location tem;
        Set<Location> closedList = new HashSet<Location>();
        while (!openQueue.isEmpty()){
             Location current = openQueue.poll();
             current.calcguess(endpoint);
             if (current.name.equals(end)){
                   while(true){
                        answer.add(current);
                        if (current.father==startpoint){
                              current.father = null;
                              break;
                        }
                        tem = current.father;
                        current.father=null;
                        current = tem;
                   }
                   answer.add(startpoint);
                   return answer;
             }
             closedList.add(current);
             Set<Entry<String, Location>> entrySet = current.nearByLocation.entrySet();
             for (Entry a :entrySet){
                   Location neighbor = (Location) a.getValue();
                   neighbor.calcguess(endpoint);
                   if (neighbor.father==null){
                        neighbor.father=current;
                   }
                   if (closedList.contains(neighbor)) continue;
                   int temx = neighbor.c.x;
            int temy = neighbor.c.y;
            int rx   = current.c.x;
            int ry   = current.c.y;
            
                double distance = Math.sqrt(Math.pow(Math.abs(temx-rx),2)+Math.pow(Math.abs(temy-ry),2));
                neighbor.setreal(current.getreal()+distance);
                double total = neighbor.getreal() +neighbor.getguess();
                neighbor.settotal(total);
                if (!openQueue.contains(neighbor)) {
                 openQueue.add(neighbor);
             }
             }
        }
        return null;
  }
	 public class NodeComparator implements Comparator<Location> {
	        public int compare(Location nodeFirst, Location nodeSecond) {
	            if (nodeFirst.gettotal() > nodeSecond.gettotal()) return 1;
	            if (nodeSecond.gettotal() > nodeFirst.gettotal()) return -1;
	            return 0;
	        }
	} 
	 
	 
	 public class InterestingComparator implements Comparator<Location> {
	        public int compare(Location nodeFirst, Location nodeSecond) {
	            if (nodeFirst.rateTotal > nodeSecond.rateTotal) return -1;
	            if (nodeSecond.rateTotal > nodeFirst.rateTotal) return 1;
	            return 0;
	        }
	 } 
	 
	 public class TimeComparator implements Comparator<Location> {
	        public int compare(Location nodeFirst, Location nodeSecond) {
	            if (nodeFirst.timeTotal > nodeSecond.timeTotal) return 1;
	            if (nodeSecond.timeTotal > nodeFirst.timeTotal) return -1;
	            return 0;
	        }
	 } 
	 
	 public class RateComparator implements Comparator<Location> {
	        public int compare(Location nodeFirst, Location nodeSecond) {
	            if (nodeFirst.rate > nodeSecond.rate) return -1;
	            if (nodeSecond.rate > nodeFirst.rate) return 1;
	            return 0;
	        }
	 } 
	 
		private double Distance(Location a, Location b){
			return Math.sqrt((a.c.x-b.c.x)*(a.c.x-b.c.x) + (a.c.y-b.c.y)*(a.c.y-b.c.y));
		}
	 
	 
	 
	public void reset(){
		Set<Entry<String, Location>> entryset = this.mapInformation.entrySet();
		for(Entry a: entryset){
			Location x = (Location) a.getValue();
			x.rateTotal = 0;
			x.tempLink = null;
			x.distance = 0;
			x.timeTotal = 0;
		}
	}
		
	
	public void DijkstraTimeOnly(String start){
		ArrayList<Location> name = new ArrayList<Location>();
		Set<Entry<String, Location>> entryset = this.mapInformation.entrySet();
		for(Entry a: entryset){
			Location x = (Location) a.getValue();
			x.timeTotal = Double.POSITIVE_INFINITY;
			//x.rateTotal = Double.NEGATIVE_INFINITY;
		}
		Queue<Location> openQueue = new PriorityQueue<Location>(60, new TimeComparator()); 
		Location Start = this.mapInformation.get(start);
		Start.timeTotal = Start.time;
		//Start.rateTotal = Start.rate;
		Set<Location> closedList = new HashSet<Location>();
		openQueue.add(Start);
		Location current;
		while(!openQueue.isEmpty()){
			current = openQueue.poll();
			Set<Entry<String, Location>> entrySet = current.nearByLocation.entrySet();
			for(Entry a: entrySet){
				Location nearBy = (Location) a.getValue();
				if (closedList.contains(nearBy)) continue;
				double time = nearBy.time + current.timeTotal + Distance(current, nearBy)/20;
				if(nearBy.timeTotal > time){
					//nearBy.timeTotal = dis;
					//double dis =  Distance(current, nearBy)/20 + nearBy.time+current.timeTotal;
//					closedList.add(nearBy);
//					closedList.add(current);
					nearBy.timeTotal = time;
					nearBy.tempLink = current;
				}
				if(!openQueue.contains(nearBy)){
					openQueue.add(nearBy);
				}
			}

			closedList.add(current);
		}
	}
	
	
	
	public void DijkstraTime(String start, int limit){
		ArrayList<Location> name = new ArrayList<Location>();
		Set<Entry<String, Location>> entryset = this.mapInformation.entrySet();
		for(Entry a: entryset){
			Location x = (Location) a.getValue();
			x.timeTotal = 0;
			x.rateTotal = Double.NEGATIVE_INFINITY;
		}
		Queue<Location> openQueue = new PriorityQueue<Location>(60, new InterestingComparator()); 
		Location Start = this.mapInformation.get(start);
		Start.timeTotal = Start.time;
		Start.rateTotal = Start.rate;
		Set<Location> closedList = new HashSet<Location>();
		openQueue.add(Start);
		Location current;
		while(!openQueue.isEmpty()){
			current = openQueue.poll();
			Set<Entry<String, Location>> entrySet = current.nearByLocation.entrySet();
			for(Entry a: entrySet){
				Location nearBy = (Location) a.getValue();
				if (closedList.contains(nearBy)) continue;
				double rate = nearBy.rate + current.rateTotal;
				if(nearBy.rateTotal<rate){
					//nearBy.timeTotal = dis;
					double dis =  Distance(current, nearBy)/20 + nearBy.time+current.timeTotal;
					if(dis > limit){
						closedList.add(nearBy);
						closedList.add(current);
						continue;
					}
					nearBy.rateTotal = rate;
					nearBy.timeTotal = dis;
					nearBy.tempLink = current;
				}
				if(!openQueue.contains(nearBy)){
					openQueue.add(nearBy);
				}
			}

			closedList.add(current);
		}
	}
	
	
	
	public void Dijkstra(String start, int limit){
		ArrayList<Location> name = new ArrayList<Location>();
		Set<Entry<String, Location>> entryset = this.mapInformation.entrySet();
		for(Entry a: entryset){
			Location x = (Location) a.getValue();
			x.rateTotal = Double.NEGATIVE_INFINITY;
			x.distance = 0;
		}
		Queue<Location> openQueue = new PriorityQueue<Location>(60, new InterestingComparator()); 
		Location Start = this.mapInformation.get(start);
		Start.rateTotal = Start.rate;
		Start.distance = 0;
		Set<Location> closedList = new HashSet<Location>();
		openQueue.add(Start);
		Location current;
		while(!openQueue.isEmpty()){
			current = openQueue.poll();
			Set<Entry<String, Location>> entrySet = current.nearByLocation.entrySet();
			for(Entry a: entrySet){
				Location nearBy = (Location) a.getValue();
				if (closedList.contains(nearBy)) continue;
				double dis = nearBy.rate+current.rateTotal;
				if(nearBy.rateTotal<dis){
					nearBy.distance = current.distance + Distance(current, nearBy);
					if(nearBy.distance > limit){
						closedList.add(nearBy);
						closedList.add(current);
						continue;
					}
					nearBy.rateTotal = dis;
					nearBy.tempLink = current;
				}
				if(!openQueue.contains(nearBy)){
					openQueue.add(nearBy);
				}
			}

			closedList.add(current);
		}
	}
	 
	 
	 public void loadMapInformation() {
		
		ArrayList<Location> A  = new ArrayList<Location>();
		ArrayList<Location> C  = new ArrayList<Location>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(getClass().getResource("init.txt").getPath()));
			String line;
			
			while((line = br.readLine()) !=null){
				int typeIndicator = 0;
				String location = "";
				String xCord = "";
				String yCord = "";
				String rate = "";
				String time = "";
				boolean isCross = false;
				
				for(char b : line.toCharArray()){
					if(b == ' '){
						typeIndicator += 1;
					}
					else{
						switch (typeIndicator){
						case 0:
							location += b;
							break;
						case 1:
							xCord += b;
							break;
						case 2:
							yCord += b;
							break;
						case 3:
							rate += b;
							break;
						case 4:
							time += b;
							break;
						case 5:
							if(b == '0'){
								isCross = true;
							}
							else{
								isCross = false;
							}
							break;
					}
					}

				}
				Coordinate c = new Coordinate(Integer.parseInt(xCord), Integer.parseInt(yCord));
				if(isCross){
					C.add(new Location(c, location, isCross, Integer.parseInt(rate), Integer.parseInt(time)));
				}else{
					A.add(new Location(c, location, isCross, Integer.parseInt(rate), Integer.parseInt(time)));
				}
				
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(getClass().getResource("set.txt").getPath()));
			String line;
			while((line = br.readLine()) !=null){
				int typeIndicator = 0;
				int arrayIndicator = 0;
				int arrayIndicatorMain = 0;
				String indexMain = "";
				String index = "";
				Location nearl = null;
				Location l = null;
				for(char b : line.toCharArray()){
					if(typeIndicator == 0){
						if(b == ' '){
							typeIndicator++;
							int i = Integer.parseInt(indexMain);
							if(arrayIndicatorMain == 0){
								l = A.get(i-1);
							}else if(arrayIndicatorMain == 1){
								l = C.get(i-1);
							}
						}
						
						else{
							if(b == 'A'){
								arrayIndicatorMain = 0;
							}else if(b == 'C'){
								arrayIndicatorMain = 1;
							}else{
								if(b != ':'){
									indexMain += b;
								}
							}
						}
					}else{
						if(b != ' '){
							if(b == 'A'){
								arrayIndicator = 0;
							}else if(b == 'C'){
								arrayIndicator = 1;
							}else{
								index += b;
							}
						}else{
							int i = Integer.parseInt(index);
							if(arrayIndicator == 0){
								nearl = A.get(i-1);
							}else if(arrayIndicator == 1){
								nearl = C.get(i-1);
							}
							l.setNearByLocation(nearl);
							index = "";
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(Location temp: A){
			mapInformation.put(temp.getName(), temp);
		}
		for(Location temp: C){
			mapInformation.put(temp.getName(), temp);
		}
		
	}
}
