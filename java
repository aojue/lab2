mport java.util.*;
public class HuiWen
{
    public static void main(String[] args)
    {
       Scanner in=new Scanner(System.in);
       System.out.println("please input a String:");
       String st=in.nextLine();
       String s=st.toLowerCase();
       int i=0;
       int j=s.length()-1;
       boolean t=true;
       char first=s.charAt(i);
       char last=s.charAt(j);
       for(;i<j;)
       {
           while(!(first>='a'&&first<='z'))
           {
              i++;
              first=s.charAt(i);
           }
           while(!(last>='a'&&last<='z'))
           {
              j--;
              last=s.charAt(i);
           }
           if(i>=j)
             break;     
           if(first==last)
           {
              i++;
              j--;
              first=s.charAt(i);
              last=s.charAt(j);
           } 
           else
           {   
             t=false;
             break;
           }
     }
       if(t)
        System.out.println(st+"是回文串");
       else
        System.out.println(st+"不是回文串");
        
    }
}

//判断是否为回文字串
//什么实用的程序，说具体点！你要计算器的程序吗？
//下面是计算器的程序，把分拿来吧！
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator
{
      public static void main(String[] args)
      {
         CalculatorFrame frame=new CalculatorFrame();
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setVisible(true);
      }
}

class CalculatorFrame extends JFrame
{
    public CalculatorFrame()
    {
       setTitle("Calculator");
       CalculatorPanel panel=new CalculatorPanel();
       add(panel);
       pack();
    }
}

class CalculatorPanel extends Panel
{
    public CalculatorPanel()
    {
       setLayout(new BorderLayout());

       result=0;
       lastCommand="=";
       start=true;
       flag=true;

       display=new JButton("0");
       display.setEnabled(false);
       add(display,BorderLayout.NORTH);

       ActionListener insert=new InsertAction();
       ActionListener command=new CommandAction();

       panel=new JPanel();
       panel.setLayout(new GridLayout(4,5));

       addButton("7",insert);
       addButton("8",insert);
       addButton("9",insert);
       addButton("/",command);
       addButton("CE",command);

       addButton("4",insert);
       addButton("5",insert);
       addButton("6",insert);
       addButton("*",command);
       addButton("Backspace",command);

       addButton("1",insert);
       addButton("2",insert);
       addButton("3",insert);
       addButton("-",command);
       addButton("sqrt",command);

       addButton("0",insert);
       addButton(".",insert);
       addButton("=",command);
       addButton("+",command);
       addButton("1/x",command);

       add(panel,BorderLayout.CENTER);
    }
    private void addButton(String label,ActionListener listener)
    {
       JButton button=new JButton(label);
       button.addActionListener(listener);
       panel.add(button);
    }
    private class InsertAction implements ActionListener
    {
       public void actionPerformed(ActionEvent event)
       {
          String input=event.getActionCommand();
          if(start&&flag)
          {
             display.setText("");
             start=false;
          }
          if(flag)
          display.setText(display.getText()+input);
       }
    }
    private class CommandAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
        String command=event.getActionCommand();

  if(command.equals("CE"))
  {
            display.setText("0");
            start=true;
            flag=true;
            command="=";
     }
        else
        if(start&&flag)
        {
           if(command.equals("-"))
           {
              display.setText(command);
              start=false;
           }
           else
           if((command.equals("1/x")||command.equals("sqrt"))&&flag)
              calculate(Double.parseDouble(display.getText()),command);
           else
             if(flag)
                lastCommand=command;
        }
        else
        {
   if(command.equals("Backspace")&&flag)
   {
    String s=display.getText();
    char[] s1=s.toCharArray();
    if(s.length()>=2)
    {
     String s2=new String(s1,0,s.length()-1);
     display.setText(s2);
    }
    else
    {
        display.setText("0");
        start=true;
       }

             }
             else  if(flag)
             {
                calculate(Double.parseDouble(display.getText()),command);

                  lastCommand=command;
                start=true;
         }
        }
}
}

public void calculate(double x,String command)
{
   if(lastCommand.equals("+"))   result+=x;
   else if(lastCommand.equals("-"))   result-=x;
   else if(lastCommand.equals("/"))
   {
    if(x!=0)
      result/=x;
    else
    {
       display.setText("除数不能为0");
       start=false;
       flag=false;
       return;
       }
   }
   else if(lastCommand.equals("*"))   result*=x;
   else if(command.equals("1/x"))
   {
    if(x!=0)
         result=1/x;
       else
    {
       display.setText("除数不能为0");
       start=false;
       flag=false;
       return;
       }

   }
   else if(command.equals("sqrt"))
   {
    if(x>=0)
      result=Math.sqrt(x);
    else
    {
      display.setText("函数输入无效");
      start=false;
      flag=false;
      return;
       }
   }
   else if(lastCommand.equals("="))   result=x;

   display.setText(""+result);
}
  private JButton display;
  private JPanel panel;
  private double result;
  private String lastCommand;
  private boolean start;
  private boolean flag;
}
