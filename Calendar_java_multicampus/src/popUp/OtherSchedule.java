package popUp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.ReplyAllSelectDao;
import vo.ReplyVo;
import calendar.*;

//import calendar.*;
//import calendar.MemoCalendar.dateClickListener;

public class OtherSchedule extends JFrame {

   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   private JTextField textField;
   private JTextField textField_1;
   private JButton modifyScheduleButton;
   private JTextField textField_4;
   private JButton ReplyButton;
   private String date;
   private String id;   
   private JPanel panel_5;
   private JLabel reply[] ;
   private static int com_count = 0;
   private String comment;
   private JLabel label2;
   private int schPk;
   private String sch;
   JTextField textField_2;
   
   private IRefreshListener iRefreshListener;
   public void setRefreshListener(IRefreshListener iRefreshListener) {
      this.iRefreshListener = iRefreshListener;
   }

   //할일 
   //1. 내꺼냐 남꺼냐
   
   
   public OtherSchedule() {
   }
   
   /////////////라벨값 받아오기
   public void getReply(int scheduleNum) {
      ReplyAllSelectDao replyAllSelectDao = new ReplyAllSelectDao();
      ArrayList<ReplyVo> replyList = replyAllSelectDao.selectCommentList(schPk);

      
      for (ReplyVo sv : replyList) {

         SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String to = transFormat.format(sv.getDate());
         String day = to.substring(8, 10);

         label2 = new JLabel(sv.getName()+" : "+sv.getComment()+"/t"+sv.getDate());
         label2.setSize(50, 10);
         label2.setOpaque(true);
         panel_5.add(label2);
         
         panel_5.repaint();
         contentPane.repaint();
         repaint();
         invalidate();
         validate();
         
      }
      
   }
   
//   private class replylistener implements ActionListener {
      
//   }
   
   public OtherSchedule(int year, int month, int day, String content, String id, int schPk, int hour, int minute) {

      repaint();
      setBounds(100, 100, 450, 300);
      setTitle("나의 일정 수정하기");
      
      this.id = id;
      this.schPk = schPk;
      
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(new GridLayout(0, 1));
      
      JPanel panel = new JPanel();
      contentPane.add(panel);
      panel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
      
      
      
      JLabel lblNewLabel = new JLabel("선택된 날짜");
      lblNewLabel.setFont(new Font("굴림체", Font.BOLD, 14));
      panel.add(lblNewLabel);
      modifyScheduleButton = new JButton("수정완료");
      modifyScheduleButton.addActionListener(new addScheduleListener());
      panel.add(modifyScheduleButton, BorderLayout.EAST);
      
      JPanel panel_1 = new JPanel();
      contentPane.add(panel_1);
      panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 17, 10));
      
      JLabel lblNewLabel_2 = new JLabel(year+"년 "+month+"월 "+day+"일 ");
      lblNewLabel_2.setForeground(Color.BLUE);
      lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 13));
      panel_1.add(lblNewLabel_2);
      
      JPanel panel_2 = new JPanel();
      FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
      flowLayout_1.setVgap(10);
      flowLayout_1.setHgap(15);
      flowLayout_1.setAlignment(FlowLayout.LEFT);
      contentPane.add(panel_2);
      
      JLabel lblNewLabel_1 = new JLabel("내용 / 장소");
      panel_2.add(lblNewLabel_1);
      lblNewLabel_1.setForeground(Color.BLACK);
      lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 14));
      
      JPanel panel_3 = new JPanel();
      contentPane.add(panel_3);
      FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
      flowLayout.setHgap(15);
      flowLayout.setAlignment(FlowLayout.LEFT);
      
      textField_1 = new JTextField();
      panel_3.add(textField_1);
      textField_1.setHorizontalAlignment(SwingConstants.LEFT);
      textField_1.setColumns(30);
      
      textField_1.setText(content ); //수정할 내용 작성하기
      textField_1.setEditable(true);
      
      JPanel panel_4 = new JPanel();
      FlowLayout flowLayout_3 = (FlowLayout) panel_4.getLayout();
      flowLayout_3.setHgap(15);
      flowLayout_3.setAlignment(FlowLayout.LEFT);
      contentPane.add(panel_4);
      
      JLabel label = new JLabel("시간설정");
      label.setFont(new Font("굴림체", Font.BOLD, 14));
      panel_4.add(label,BorderLayout.WEST);
      
    
     // textField_2 = new JTextField();
      textField_2 = new JTextField(hour + ":" + minute);
      textField_2.setHorizontalAlignment(SwingConstants.LEFT);
      textField_2.setColumns(4);
      textField_2.setEditable(true);
      panel_4.add(textField_2);
      
//      JLabel label_1 = new JLabel("~");
//      label_1.setFont(new Font("굴림체", Font.BOLD, 14));
//      panel_4.add(label_1,BorderLayout.CENTER);
//      
//      JTextField textField_3 = new JTextField();
//      textField_3.setText("");
//      textField_3.setHorizontalAlignment(SwingConstants.LEFT);
//      textField_3.setColumns(4);
//      textField_3.setEditable(true);
//      panel_4.add(textField_3);
      
      panel_5 = new JPanel();
//      FlowLayout flowLayout_4 = (FlowLayout) panel_5.getLayout();
//      flowLayout_4.setAlignment(FlowLayout.LEFT);
      panel_5.setLayout(new GridLayout(0, 1));
      
      contentPane.add(panel_5);
//      JLabel jtext = new JLabel();
//      jtext.setText("위치테스트");
//      panel_5.add(jtext);
      
      JPanel panel_6 = new JPanel();
      FlowLayout flowLayout_2 = (FlowLayout) panel_6.getLayout();
      flowLayout_2.setHgap(15);
      flowLayout_2.setAlignment(FlowLayout.RIGHT);
      contentPane.add(panel_6);
      
      textField_4 = new JTextField();
      textField_4.setColumns(25);
      panel_6.add(textField_4);
      
      ReplyButton = new JButton("댓글 입력");
      panel_6.add(ReplyButton);
      ReplyButton.addActionListener(new addReplyListener());
   
      getReply(schPk);
      panel_5.repaint();
      contentPane.repaint();
      repaint();
      invalidate();
      validate();
      setSize(600, 600);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setVisible(true);
      date = year + "-" + month + "-" + day + " " ;//+ textField_2.getText() + ":" + "00";
      
   }
   
   class addScheduleListener implements ActionListener {

      @Override
      public void actionPerformed(ActionEvent e) {
         if (modifyScheduleButton == e.getSource()) {
            sch = textField_1.getText();
            
            ModifyScheduleDAO modsch = new ModifyScheduleDAO();
            modsch.update(sch,date+textField_2.getText()+ ":" + "00", schPk);
         
            repaint();
            invalidate();
            validate();
            
            dispose();
            
            if (iRefreshListener != null) {
               boolean flag = true;
               iRefreshListener.refresh(flag);
            }
         }
      }
   }
   class addReplyListener implements ActionListener {

      @Override
      public void actionPerformed(ActionEvent e) {
         if (ReplyButton == e.getSource()) {
         
            comment = textField_4.getText();
            EnterCommentDAO commentadded = new EnterCommentDAO();
            commentadded.insert(comment,schPk,id,date+textField_2.getText()+ ":" + "00");
            textField_4.setText("");
            panel_5.removeAll();
            getReply(schPk);
            panel_5.repaint();
            contentPane.repaint();
            repaint();
            invalidate();
            validate();
            
         }
      }
   }
}

