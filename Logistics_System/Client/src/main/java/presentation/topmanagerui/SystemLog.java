/**
 * 2015年11月19日
 *author:
 *description:
 */
package presentation.topmanagerui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import businesslogicservice.chartblservice.SystemLogBLService;
import presentation.img.Img;
import presentation.mainui.CheckFormat;
import presentation.mainui.CurrentUser;
import presentation.mainui.MainFrame;
import presentation.mainui.MyButton;
import vo.SystemLogVO;

/**
 * @author 谭期友
 *
 */
public class SystemLog extends JPanel{

	private static final long serialVersionUID = -1194559040892610991L;
	private SystemLogBLService bl;
	private TopManagerFrame frame;
	private CurrentUser currentUser;
	
	//最基本按钮
	private MyButton close;
	private MyButton min;
	private MyButton _return;
	//功能按钮
	private MyButton goto_Statistic;
	private MyButton goto_Check;
	private MyButton goto_PeopleAgencyManage;
	private MyButton goto_SalaryStrategy;
	private MyButton goto_ConstantManage;
	private MyButton goto_SystemLog;
	//详细操作按钮以及其他组件
	private MyButton search;
	
	private JTextField start_date;
	private JTextField end_date;
	private DefaultTableModel SystemLogTableModel;
	private JTable SystemLogTable;

	private boolean willprintMessage;//是否将要打印消息
	private String result;//打印的消息
	private Color co;//消息的颜色

	protected void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.WHITE);
        g.drawImage(Img.BACKGROUND2, TopManagerFrame.w/6, TopManagerFrame.h/6, TopManagerFrame.w, TopManagerFrame.h, null);
        g.drawImage(Img.BACKGROUND1, TopManagerFrame.w/6, 0, TopManagerFrame.w, TopManagerFrame.h/6, null);
        g.drawImage(Img.BACKGROUND0, 0, 0, TopManagerFrame.w/6, TopManagerFrame.h, null);
        g.drawLine(TopManagerFrame.w/6-1, 0, TopManagerFrame.w/6-1, TopManagerFrame.h);
        g.drawLine(TopManagerFrame.w/6, 0, TopManagerFrame.w/6, TopManagerFrame.h);
        g.drawLine(TopManagerFrame.w/6+1, 0, TopManagerFrame.w/6+1, TopManagerFrame.h);
        g.drawLine(TopManagerFrame.w/6, TopManagerFrame.h/6, TopManagerFrame.w, TopManagerFrame.h/6);
        g.drawLine(TopManagerFrame.w/6, TopManagerFrame.h/6+1, TopManagerFrame.w, TopManagerFrame.h/6+1);
        
        if(willprintMessage){
        	g.drawImage(Img.BLACK_BG, 0, TopManagerFrame.h-50, TopManagerFrame.w, 50, null);
        	
            g.setColor(co);
            g.setFont(new Font("宋体", Font.BOLD, 26));
            g.drawString(result, -result.length()*13+TopManagerFrame.w/2, 13+TopManagerFrame.h-30);
        }
	}
	
	public SystemLog(TopManagerFrame frame, SystemLogBLService bl, CurrentUser currentUser){
		this.frame=frame;
		this.bl=bl;
		this.currentUser=currentUser;
		willprintMessage=false;
		result="";
		co=Color.RED;
		this.setLayout(null);

		//初始化组件
		initComponent();
	}
	private void initComponent() {
		//最基本按钮
		close = new MyButton(30, 30, Img.CLOSE_0, Img.CLOSE_1, Img.CLOSE_2);
        close.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
        });
        min = new MyButton(30, 30, Img.MINI_0, Img.MINI_1, Img.MINI_2);
        min.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				frame.setExtendedState(JFrame.ICONIFIED);
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
        });
        _return = new MyButton(30, 30, Img.RETURN_0, Img.RETURN_1, Img.RETURN_2);
        _return.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
				new MainFrame();
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
        });
        //功能按钮
        goto_Statistic = new MyButton(frame.getWidth()/6, 30, Img.GOTONGJIBAOBIAO_0, Img.GOTONGJIBAOBIAO_1, Img.GOTONGJIBAOBIAO_2);
        goto_Statistic.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				clear();
				frame.setStated(frame.getState());
				frame.setState(1);
				frame.change();
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
        });
        goto_Check = new MyButton(frame.getWidth()/6, 30, Img.GOSHENPAN_0, Img.GOSHENPAN_1, Img.GOSHENPAN_2);
        goto_Check.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				clear();
				frame.setStated(frame.getState());
				frame.setState(2);
				frame.change();
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
        });
        goto_PeopleAgencyManage = new MyButton(frame.getWidth()/6, 30, Img.GORENYUAN_0, Img.GORENYUAN_1, Img.GORENYUAN_2);
        goto_PeopleAgencyManage.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				clear();
				frame.setStated(frame.getState());
				frame.setState(3);
				frame.change();
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
        });
        goto_SalaryStrategy = new MyButton(frame.getWidth()/6, 30, Img.GOXINSHUI_0, Img.GOXINSHUI_1, Img.GOXINSHUI_2);
        goto_SalaryStrategy.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				clear();
				frame.setStated(frame.getState());
				frame.setState(4);
				frame.change();
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
        });
    	goto_ConstantManage = new MyButton(frame.getWidth()/6, 30, Img.GOCHANGLIANG_0, Img.GOCHANGLIANG_1, Img.GOCHANGLIANG_2);
    	goto_ConstantManage.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				clear();
				frame.setStated(frame.getState());
				frame.setState(5);
				frame.change();
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
        });
    	goto_SystemLog = new MyButton(frame.getWidth()/6, 30, Img.GOXITONG_2, Img.GOXITONG_2, Img.GOXITONG_2);
    	goto_SystemLog.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				clear();
				frame.setStated(frame.getState());
				frame.setState(6);
				frame.change();
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
        });
    	//详细操作按钮
    	search = new MyButton(90, 30, Img.SEARCH_0, Img.SEARCH_1, Img.SEARCH_2);
    	search.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				_search();
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
        });
    	
    	//最基本元素
        JLabel titleLabel = new JLabel("物流信息管理系统");
        titleLabel.setSize((int)(50*8*1.07f), 50);
        titleLabel.setFont(new Font("宋体", Font.BOLD, 50));
        titleLabel.setLocation(596-(int)(50*8*1.07f)/2,20);

        String func="查看系统日志";
        JLabel funLabel = new JLabel(func);
        funLabel.setSize((int)(40*func.length()*1.07f), 40);
        funLabel.setFont(new Font("宋体", Font.BOLD, 40));
        funLabel.setLocation(596-(int)(40*func.length()*1.07f)/2,128+10);

        JLabel currentuserAgencyNameLabel = new JLabel(currentUser.getAgencyName());
        currentuserAgencyNameLabel.setSize((int)(30*currentUser.getAgencyName().length()*1.07f), 30);
        currentuserAgencyNameLabel.setFont(new Font("宋体", Font.BOLD, 30));
        currentuserAgencyNameLabel.setForeground(Color.DARK_GRAY);
        currentuserAgencyNameLabel.setLocation(170,128-30);
        
        String s="总经理";
        JLabel currentuserLabel = new JLabel(s);
        currentuserLabel.setSize((int)(30*s.length()*1.07f), 30);
        currentuserLabel.setFont(new Font("宋体", Font.BOLD, 30));
        currentuserLabel.setLocation(170+(int)(30*currentUser.getAgencyName().length()*1.07f),128-30);
        
        JLabel currentusernameLabel = new JLabel(currentUser.getname());
        currentusernameLabel.setSize((int)(30*currentUser.getname().length()*1.07f), 30);
        currentusernameLabel.setFont(new Font("宋体", Font.BOLD, 30));
        currentusernameLabel.setForeground(Color.DARK_GRAY);
        currentusernameLabel.setLocation(170+(int)(30*currentUser.getAgencyName().length()*1.07f)+(int)(30*s.length()*1.07f),128-30);
    	//最基本按钮
    	close.setLocation(TopManagerFrame.w-30,0);
    	min.setLocation(TopManagerFrame.w-80,0);
    	_return.setLocation(20,50);
    	//功能按钮
    	goto_Statistic.setLocation(0,150);
    	goto_Check.setLocation(0,200);
    	goto_PeopleAgencyManage.setLocation(0,250);
    	goto_SalaryStrategy.setLocation(0,300);
    	goto_ConstantManage.setLocation(0,350);
    	goto_SystemLog.setLocation(0,400);
    	
    	//其他组件
		JLabel l1 = new JLabel("起始日期：");
		l1.setSize((int)(16*5*1.07f), 16);
		l1.setFont(new Font("宋体", Font.BOLD, 15));
		l1.setLocation(596-220, 128+80+20);
		start_date = new JTextField();
		start_date.setSize(110, 20);
		start_date.setLocation(596-220+(int)(16*5*1.07f),128+80+20-3);

		JLabel l2 = new JLabel("终止日期：");
		l2.setSize((int)(16*5*1.07f), 16);
		l2.setFont(new Font("宋体", Font.BOLD, 15));
		l2.setLocation(596+20, 128+80+20);
		Date date_=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String time=format.format(date_);
		end_date = new JTextField(time);
		end_date.setSize(110, 20);
		end_date.setLocation(596+20+(int)(16*5*1.07f),128+80+20-3);

		//表头
		Vector<String> vColumns = new Vector<String>();
		vColumns.add("日期");
		vColumns.add("操作者");
		vColumns.add("改动");
		//数据
		Vector<String> vData = new Vector<String>();
		//模型
		SystemLogTableModel = new DefaultTableModel(vData, vColumns);
		//表格
		SystemLogTable = new JTable(SystemLogTableModel){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column){
				return false;//不能修改
			}
		};
		SystemLogTable.setPreferredScrollableViewportSize(new Dimension(800,360));
		SystemLogTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		SystemLogTable.setSelectionBackground(Color.YELLOW);
		JPanel jp=new JPanel();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().add(SystemLogTable);
		SystemLogTable.getTableHeader().setReorderingAllowed(false);
		SystemLogTable.getTableHeader().setResizingAllowed(false);
		SystemLogTable.setFillsViewportHeight(true);
		jp.setSize(820, 390);
		jp.setLocation(596-820/2, 128+80+100);
		jp.setOpaque(false);
		jp.add(scrollPane,BorderLayout.CENTER);

		

    	search.setLocation(596+300, 128+80+20);
        

        add(titleLabel);
        add(funLabel);
        add(currentuserAgencyNameLabel);
        add(currentuserLabel);
        add(currentusernameLabel);
    	
    	add(close);
    	add(min);
    	add(_return);
    	add(goto_Statistic);
    	add(goto_Check);
    	add(goto_PeopleAgencyManage);
    	add(goto_SalaryStrategy);
    	add(goto_ConstantManage);
    	add(goto_SystemLog);

    	add(l1);
    	add(start_date);
    	add(l2);
    	add(end_date);
    	add(jp);
    	
    	add(search);
	}

	private void _search(){
		String start_date_s = start_date.getText();
		String end_date_s = end_date.getText();

		result = CheckFormat.checkTime(start_date_s);
		if(result.compareTo("格式正确")!=0){
			printMessage(result, Color.RED);
			return;
		}
		result = CheckFormat.checkTime(end_date_s);
		if(result.compareTo("格式正确")!=0){
			printMessage(result, Color.RED);
			return;
		}
		ArrayList<SystemLogVO> arr=bl.showAll(start_date_s, end_date_s);

		if(arr==null){
			printMessage("找不到相关信息！", Color.RED);
			return;
		}

		while(SystemLogTable.getRowCount()!=0)//先清空原来的
			SystemLogTableModel.removeRow(0);
		
		for(int i = 0 ; i<arr.size(); i++){
			Vector<String> v = new Vector<String>();
    		v.add(arr.get(i).getTime());
    		v.add(arr.get(i).getUser());
    		v.add(arr.get(i).getContent());
    		SystemLogTableModel.addRow(v);
        }
	}
	private void clear(){
		start_date.setText("");
		while(SystemLogTable.getRowCount()!=0)
			SystemLogTableModel.removeRow(0);
		willprintMessage=false;
		repaint();
	}
	
	
	private void printMessage(String message, Color c){
		result=message;
		co=c;
		if(!willprintMessage){
			willprintMessage=true;
			repaint();
			new Thread(new Runnable(){
				public void run() {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}

					willprintMessage=false;
					repaint();
				}
			}).start();
		}
	}
}
