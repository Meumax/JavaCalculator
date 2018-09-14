package net.meumax.kong;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonListener;


//定义一个类CalculateFrame继承于JFrame
public class CalculateFrame extends JFrame {

	//CalculateFrame类主函数
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CalculateFrame frame = new CalculateFrame();
		
	}

	//CalculateFrame的属性
	private JTextField dataText1;//第一个操作数输入框
	private JTextField dataText2;//第二个操作数输入框
	private JTextField result;//计算结果显示框
	private JLabel label1;//显示字符串“第一个操作数：”
	private JLabel label2;//显示字符串“第二个操作数：”
	private JLabel label3;//显示字符串“计算结果：”
	private JButton button;//计算按钮
	private JRadioButton radio1;//+
	private JRadioButton radio2;//-
	private JRadioButton radio3;//×
	private JRadioButton radio4;//÷
	
	//CalculateFrame的构造方法
	public CalculateFrame()
	{
		//获取屏幕大小
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = d.width;
		int height = d.height;
		
		//设置窗体名称
		setTitle("JAVA计算器");
		
		//设置窗体初始位置
		setLocation(width/2-150, height/2-200);
		
		//设置窗体初始大小
		this.setSize(300,400);
		
		//设置是否允许最大化
		this.setResizable(false);
		
		//设置当点击关闭时程序结束
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//添加一个windows事件
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
		
		dataText1 = new JTextField();
		dataText2 = new JTextField();
		result = new JTextField();
		label1 = new JLabel("第一个操作数：");
		label2 = new JLabel("第二个操作数：");
		label3 = new JLabel("计算结果：");
		button = new JButton("计算");
		radio1 = new JRadioButton("+");
		radio2 = new JRadioButton("-");
		radio3 = new JRadioButton("×");
		radio4 = new JRadioButton("÷");
		
		//每行控件再一个组中
		JPanel p1 = new JPanel(new GridLayout(1,2));
		p1.add(label1);
		p1.add(dataText1);	
		
		JPanel p2 = new JPanel(new GridLayout(1, 4));
		p2.add(radio1);
		p2.add(radio2);	
		p2.add(radio3);	
		p2.add(radio4);	
		
		JPanel p3 = new JPanel(new GridLayout(1, 2));
		p3.add(label2);
		p3.add(dataText2);
		
		JPanel p4 = new JPanel(new GridLayout(1, 2));
		p4.add(label3);
		p4.add(result);
		//禁止结果框输入
		result.setEditable(false);
		
		
		JPanel p5 = new JPanel();
		p5.add(button);
		
		//处理一组按钮只能有一个选中
		ButtonGroup group = new ButtonGroup();
		group.add(radio1);
		group.add(radio2);
		group.add(radio3);
		group.add(radio4);
		
		//布局
		this.setLayout(new GridLayout(5, 1));
		
		//将控件加入到窗体
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(p4);
		this.add(p5);
				
		
		//显示窗体
		setVisible(true);
		
		//给Button设置事件监听
		button.addActionListener(new ButtonListener());
	}
	
	//CalculateFrame类的内部类
	class ButtonListener implements ActionListener
	{
		//ButtonListener类方法
		public void actionPerformed(ActionEvent e)
		{
			//保存第一个操作数
			String data1 = dataText1.getText();
			//保存第二个操作数
			String data2 = dataText2.getText();
			//保存符号
			String operation = "";
			if(radio1.isSelected())
			{
				operation = radio1.getText();
			}
			else if(radio2.isSelected())
			{
				operation = radio2.getText();
			}
			else if (radio3.isSelected())
			{
				operation = radio3.getText();
			}
			else if(radio4.isSelected())
			{
				operation = radio4.getText();
			}
			
			//进行数据合法性判断
			//判断是否选择了操作符
			if(operation == "")
			{
				JOptionPane.showMessageDialog(CalculateFrame.this, "请选择运算符");
				return;
			}
			
			//进行计算
			double d = calculate(data1, data2, operation);
			
			//结果显示
			result.setText(String.valueOf(d));
		}
	}
	
	//CalculateFrame类的方法
	public double calculate(String data1,String data2,String operation)
	{
		double result = Double.MAX_VALUE;
		//第一步，把字符串转换成数字
		double d1=0,d2=0;
		try
		{
			d1 = Double.parseDouble(data1);
			d2 = Double.parseDouble(data2);
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "输入有误!");
			result = 0;
		}
		
		
		//第二步，根据操作符进行计算
		if(operation == "+")
		{
			result = d1 + d2;
		}else if(operation == "-")
		{
			result = d1 - d2;
		}else if(operation == "×")
		{
			result = d1 * d2;
		}else if(operation == "÷")
		{
			if(d2 != 0)
			{
				result = d1 / d2;
			}else 
			{
				JOptionPane.showMessageDialog(this, "分母不能为0!");
				result = 0;
			}
		}

		return result;
	}
	
}


