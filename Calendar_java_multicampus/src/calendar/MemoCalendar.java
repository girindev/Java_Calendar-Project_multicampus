package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import calendarServer.CalenderClient;
import chatting.ChattingPanel;
import dao.ScheduleAllSelectDao;
import popUp.AddSchedule;
import popUp.IRefreshListener;
import user.UserListPanel;
import vo.ScheduleVo;

class CalendarDataManager { // 6*7배열에 나타낼 달력 값을 구하는 class
   static final int CAL_WIDTH = 7;
   final static int CAL_HEIGHT = 6;
   int calDates[][] = new int[CAL_HEIGHT][CAL_WIDTH];
   int calYear;
   int calMonth;
   int calDayOfMon;
   int calHour;
   int calMinute;
   final int calLastDateOfMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
   int calLastDate;
   Calendar today = Calendar.getInstance();
   Calendar cal;
   AddSchedule popUp01;

   public CalendarDataManager() {
      setToday();
   }

   public void setToday() {
      calYear = today.get(Calendar.YEAR);
      calMonth = today.get(Calendar.MONTH);
      calDayOfMon = today.get(Calendar.DAY_OF_MONTH);
      calHour = today.get(Calendar.HOUR_OF_DAY);
      calMinute = today.get(Calendar.MINUTE);
      makeCalData(today);
   }

   private void makeCalData(Calendar cal) {
      // 1일의 위치와 마지막 날짜를 구함
      int calStartingPos = (cal.get(Calendar.DAY_OF_WEEK) + 7 - (cal.get(Calendar.DAY_OF_MONTH)) % 7) % 7;
      if (calMonth == 1)
         calLastDate = calLastDateOfMonth[calMonth] + leapCheck(calYear);
      else
         calLastDate = calLastDateOfMonth[calMonth];
      // 달력 배열 초기화
      for (int i = 0; i < CAL_HEIGHT; i++) {
         for (int j = 0; j < CAL_WIDTH; j++) {
            calDates[i][j] = 0;
         }
      }

      // 달력 배열에 값 채워넣기
      for (int i = 0, num = 1, k = 0; i < CAL_HEIGHT; i++) {
         if (i == 0)
            k = calStartingPos;
         else
            k = 0;
         for (int j = k; j < CAL_WIDTH; j++) {
            if (num <= calLastDate)
               calDates[i][j] = num++;
         }
      }
   }

   private int leapCheck(int year) { // 윤년인지 확인하는 함수
      if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
         return 1;
      else
         return 0;
   }

   public void moveMonth(int mon) { // 현재달로 부터 n달 전후를 받아 달력 배열을 만드는 함수(1년은 +12, -12달로 이동 가능)
      calMonth += mon;
      if (calMonth > 11)
         while (calMonth > 11) {
            calYear++;
            calMonth -= 12;
         }
      else if (calMonth < 0)
         while (calMonth < 0) {
            calYear--;
            calMonth += 12;
         }
      cal = new GregorianCalendar(calYear, calMonth, calDayOfMon);
      makeCalData(cal);
   }
}

public class MemoCalendar extends CalendarDataManager implements IRefreshListener { // CalendarDataManager의 GUI
   // 창 구성요소와 배치도
   JFrame mainFrame;
   ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));

   JPanel calOpPanel;
   JButton todayBut;
   JLabel todayLab;
   JButton lYearBut;
   JButton lMonBut;
   JLabel curMMYYYYLab;
   JButton nMonBut;
   JButton nYearBut;
   ListenForCalOpButtons lForCalOpButtons = new ListenForCalOpButtons();

   JPanel group = new JPanel();
   UserListPanel groupLogin = new UserListPanel();
   ChattingPanel groupChat = new ChattingPanel();

   //캘린더 클라이언트
   CalenderClient calenderClient = new CalenderClient(this);
   
   
   // 그룹 참여 레이아웃 테스트
   JLabel grouptest1 = new JLabel("그룹참여");
   JLabel grouptest2 = new JLabel("그룹 채팅방 자리");

   JPanel calPanel;
   JButton weekDaysName[];
   JPanel datePanel[][] = new JPanel[6][7];
   JLabel dateButs[][] = new JLabel[6][7];
   listenForDateButs lForDateButs = new listenForDateButs();

   // 상수, 메세지
   final String WEEK_DAY_NAME[] = { "SUN", "MON", "TUE", "WED", "THR", "FRI", "SAT" };
   final String title = "달력메인";
   private String id;
   public MemoCalendar() {
      
   }

   public MemoCalendar(String id) { // 구성요소 순으로 정렬되어 있음. 각 판넬 사이에 빈줄로 구별
      this.id = id;
      mainFrame = new JFrame(title);
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainFrame.setSize(1200, 800);
      mainFrame.setLocationRelativeTo(null);
      mainFrame.setResizable(false);
      mainFrame.setIconImage(icon.getImage());
      try {
         UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// LookAndFeel Windows 스타일 적용
         SwingUtilities.updateComponentTreeUI(mainFrame);
      } catch (Exception e) {
      }

      calOpPanel = new JPanel();
      todayBut = new JButton("Today");
      todayBut.setToolTipText("Today");
      todayBut.addActionListener(lForCalOpButtons);
      todayLab = new JLabel(today.get(Calendar.MONTH) + 1 + "/" + today.get(Calendar.DAY_OF_MONTH) + "/"
            + today.get(Calendar.YEAR));
      lYearBut = new JButton("<<");
      lYearBut.setToolTipText("Previous Year");
      lYearBut.addActionListener(lForCalOpButtons);
      lMonBut = new JButton("<");
      lMonBut.setToolTipText("Previous Month");
      lMonBut.addActionListener(lForCalOpButtons);
      // 9/2/2017
      curMMYYYYLab = new JLabel("<html><table width=100><tr><th><font size=5>" + ((calMonth + 1) < 10 ? "&nbsp;" : "")
            + (calMonth + 1) + " / " + calYear + "</th></tr></table></html>");
      nMonBut = new JButton(">");
      nMonBut.setToolTipText("Next Month");
      nMonBut.addActionListener(lForCalOpButtons);
      nYearBut = new JButton(">>");
      nYearBut.setToolTipText("Next Year");
      nYearBut.addActionListener(lForCalOpButtons);
      calOpPanel.setLayout(new GridBagLayout());
      GridBagConstraints calOpGC = new GridBagConstraints();
      calOpGC.gridx = 1;
      calOpGC.gridy = 1;
      calOpGC.gridwidth = 2;
      calOpGC.gridheight = 1;
      calOpGC.weightx = 1;
      calOpGC.weighty = 1;
      calOpGC.insets = new Insets(5, 5, 0, 0);
      calOpGC.anchor = GridBagConstraints.WEST;
      calOpGC.fill = GridBagConstraints.NONE;
      calOpPanel.add(todayBut, calOpGC);
      calOpGC.gridwidth = 3;
      calOpGC.gridx = 2;
      calOpGC.gridy = 1;
      calOpPanel.add(todayLab, calOpGC);
      calOpGC.anchor = GridBagConstraints.CENTER;
      calOpGC.gridwidth = 1;
      calOpGC.gridx = 1;
      calOpGC.gridy = 2;
      calOpPanel.add(lYearBut, calOpGC);
      calOpGC.gridwidth = 1;
      calOpGC.gridx = 2;
      calOpGC.gridy = 2;
      calOpPanel.add(lMonBut, calOpGC);
      calOpGC.gridwidth = 2;
      calOpGC.gridx = 3;
      calOpGC.gridy = 2;
      calOpPanel.add(curMMYYYYLab, calOpGC);
      calOpGC.gridwidth = 1;
      calOpGC.gridx = 5;
      calOpGC.gridy = 2;
      calOpPanel.add(nMonBut, calOpGC);
      calOpGC.gridwidth = 1;
      calOpGC.gridx = 6;
      calOpGC.gridy = 2;
      calOpPanel.add(nYearBut, calOpGC);

      calPanel = new JPanel();
      weekDaysName = new JButton[7];
      for (int i = 0; i < CAL_WIDTH; i++) {
         weekDaysName[i] = new JButton(WEEK_DAY_NAME[i]);
         weekDaysName[i].setBorderPainted(false);
         weekDaysName[i].setContentAreaFilled(false);
         weekDaysName[i].setForeground(Color.WHITE);
         if (i == 0)
            weekDaysName[i].setBackground(new Color(200, 50, 50));
         else if (i == 6)
            weekDaysName[i].setBackground(new Color(50, 100, 200));
         else
            weekDaysName[i].setBackground(new Color(150, 150, 150));
         weekDaysName[i].setOpaque(true);
         weekDaysName[i].setFocusPainted(false);
         calPanel.add(weekDaysName[i]);
      }
      // 날짜 패널에 날짜 버튼 하나씩 넣기
      for (int i = 0; i < CAL_HEIGHT; i++) {
         for (int j = 0; j < CAL_WIDTH; j++) {
            datePanel[i][j] = new JPanel();
            datePanel[i][j].setLayout(new BoxLayout(datePanel[i][j], BoxLayout.Y_AXIS));
            dateButs[i][j] = new JLabel();            
            dateButs[i][j].setOpaque(true);
            datePanel[i][j].add(dateButs[i][j]);

            if (calDates[i][j] != 0) {
               // 클릭한 패널 날짜 받아오기 위한 마우스 리스너생성
               datePanel[i][j].addMouseListener(new DateClickListener(i, j, 
                     calYear, calMonth, calDates, calHour, calMinute,id,
                     datePanel, MemoCalendar.this));
            }

            datePanel[i][j].setBorder(new EtchedBorder(EtchedBorder.LOWERED));
            calPanel.add(datePanel[i][j]);

         }
      }

      group.setLayout(new BoxLayout(group, BoxLayout.Y_AXIS));
      // groupLogin.add(grouptest1);
      // groupChat.add(grouptest2);
      group.add(groupLogin);
      group.add(groupChat);

      calPanel.setLayout(new GridLayout(0, 7, 2, 2));
      calPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
      showCal(); // 달력을 표시

      // calOpPanel, calPanel을 frameSubPanelWest에 배치
      JPanel frameSubPanelWest = new JPanel();
      Dimension calOpPanelSize = calOpPanel.getPreferredSize();
      calOpPanelSize.height = 120;
      calOpPanel.setPreferredSize(calOpPanelSize);
      frameSubPanelWest.setLayout(new BorderLayout());
      frameSubPanelWest.add(calOpPanel, BorderLayout.NORTH);
      frameSubPanelWest.add(calPanel, BorderLayout.CENTER);

      Dimension frameSubPanelWestSize = frameSubPanelWest.getPreferredSize();
      // 달력 패널 크기
      frameSubPanelWestSize.width = 800;
      frameSubPanelWest.setPreferredSize(frameSubPanelWestSize);

      // frame에 전부 배치
      mainFrame.setLayout(new BorderLayout());
      mainFrame.add(frameSubPanelWest, BorderLayout.WEST);
      // 그룹 자리욤
      mainFrame.add(group, BorderLayout.CENTER);
      mainFrame.setVisible(true);

      focusToday(); // 현재 날짜에 focus를 줌 (mainFrame.setVisible(true) 이후에 배치해야함)

   }

   private void focusToday() {
      if (today.get(Calendar.DAY_OF_WEEK) == 1)
         dateButs[today.get(Calendar.WEEK_OF_MONTH)][today.get(Calendar.DAY_OF_WEEK) - 1].requestFocusInWindow();
      else
         dateButs[today.get(Calendar.WEEK_OF_MONTH) - 1][today.get(Calendar.DAY_OF_WEEK) - 1].requestFocusInWindow();
   }

   private void showCal() {
      for (int i = 0; i < CAL_HEIGHT; i++) {
         for (int j = 0; j < CAL_WIDTH; j++) {
            String fontColor = "black";
            if (j == 0)
               fontColor = "red";
            else if (j == 6)
               fontColor = "blue";

            File f = new File("MemoData/" + calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1)
                  + (calDates[i][j] < 10 ? "0" : "") + calDates[i][j] + ".txt");
            if (f.exists()) {
               dateButs[i][j]
                     .setText("<html><b><font color=" + fontColor + ">" + calDates[i][j] + "</font></b></html>");
            } else
               dateButs[i][j].setText("<html><font color=" + fontColor + ">" + calDates[i][j] + "</font></html>");

            JLabel todayMark = new JLabel("<html><font color=green>*</html>");
            dateButs[i][j].removeAll();
            if (calMonth == today.get(Calendar.MONTH) && calYear == today.get(Calendar.YEAR)
                  && calDates[i][j] == today.get(Calendar.DAY_OF_MONTH)) {
               dateButs[i][j].add(todayMark);
               dateButs[i][j].setToolTipText("Today");
            }

            if (calDates[i][j] == 0) {
               dateButs[i][j].setVisible(false);
            } else
               dateButs[i][j].setVisible(true);
         }
      }
      String temp = "";
      if (calMonth < 10)
         temp = "0";
      int month = calMonth + 1;
      initCalendar(calYear + "-" + temp + month);
   }

   private class ListenForCalOpButtons implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         removeDatePanelContent();

         if (e.getSource() == todayBut) {
            setToday();
            lForDateButs.actionPerformed(e);
            focusToday();
         } else if (e.getSource() == lYearBut)
            moveMonth(-12);
         else if (e.getSource() == lMonBut)
            moveMonth(-1);
         else if (e.getSource() == nMonBut)
            moveMonth(1);
         else if (e.getSource() == nYearBut)
            moveMonth(12);

         curMMYYYYLab.setText("<html><table width=100><tr><th><font size=5>" + ((calMonth + 1) < 10 ? "&nbsp;" : "")
               + (calMonth + 1) + " / " + calYear + "</th></tr></table></html>");

         showCal();
      }
   }

   // 이 부분 어디다가 쓰는 건지 아시는분????? (승호)
   private class listenForDateButs implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         if (e.getSource() == dateButs) {
            lForDateButs = new listenForDateButs();
         }
      }
   }
   /**
    * @author 박성훈
    * DB에서 스케줄 불러와서 캘린더에 초기화
    * */
   public void initCalendar(String date) {
      ScheduleAllSelectDao scheduleAllSelectDao = new ScheduleAllSelectDao();
      ArrayList<ScheduleVo> scheduleList = scheduleAllSelectDao.selectScheduleAllList(date);

      for (ScheduleVo sv : scheduleList) {
         for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
               SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               String to = transFormat.format(sv.getDate());
               String day = to.substring(8, 10);

               if (calDates[i][j] == Integer.valueOf(day)) {
                  JLabel label = new JLabel(sv.getContent());
                  label.setMinimumSize(new Dimension(111,15));
                  label.setPreferredSize(new Dimension(111,15));
                  label.setMaximumSize(new Dimension(111,15));
                  
                  label.setOpaque(true);
                  label.setForeground(Color.white);
                  label.addMouseListener(new DateClickListener(i, j, label.getText(),  
                        calYear, calMonth, calDates, calHour, calMinute, 
                        id, sv.getSchPK(),datePanel, MemoCalendar.this));
                  label.setBackground(UserColor.getCalcColor(sv.getColor())); // 서버에서 받아오는 색상으로 변경
                  datePanel[i][j].add(label);

                  mainFrame.repaint();
                  mainFrame.invalidate();
                  mainFrame.validate();
               }
            }
         }
      }
   }

   /**
    * @author 박성훈
    * 스케줄 add하면 db select 호출
    * */
   @Override
   public void refresh(boolean flag) {
      if (flag) {
         removeDatePanelContent();
         showCal();
      }
   }
   /**
    * @author 박성훈
    * 캘린더 날짜별 패널 내용 삭제
    * */
   public void removeDatePanelContent() {
      for (int i = 0; i < 6; i++) {
         for (int j = 0; j < 7; j++) {
            if (datePanel[i][j].getComponentCount() >= 2) {
               while (datePanel[i][j].getComponentCount() != 1)
                  datePanel[i][j].remove(datePanel[i][j].getComponentCount() - 1);
            }
         }
      }
      mainFrame.repaint();
      mainFrame.invalidate();
      mainFrame.validate();
   }
}