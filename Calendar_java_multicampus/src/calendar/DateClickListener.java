package calendar;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import calendarServer.CalenderClient;
import popUp.AddSchedule;
import popUp.IRefreshListener;
import popUp.OtherSchedule;
/**
 * @author 박성훈
 * 캘린더 날짜 패널 클릭리스너
 * */
public class DateClickListener implements MouseListener {
	private int i, j;
	private String content;
	private String id;
	private int calYear, calMonth, calDates[][],calHour, calMinute;
	private JPanel datePanel[][];
	private IRefreshListener iRefreshListener;
	private int schPk;
	private CalenderClient client;
	
	public DateClickListener(int i, int j, int calYear, int calMonth, 
			int calDates[][],int calHour, int calMinute, String id,
			JPanel datePanel[][], IRefreshListener iRefreshListener,CalenderClient client) {
		this.i = i;
		this.j = j;
		this.calYear = calYear;
		this.calMonth = calMonth;
		this.calDates = calDates;
		this.calHour = calHour;
		this.calMinute = calMinute;
		this.iRefreshListener = iRefreshListener;
		this.datePanel = datePanel;
		this.id = id;
		this.client = client;
	}

	public DateClickListener(int i, int j, String content, int calYear, int calMonth, 
			int calDates[][],int calHour, int calMinute, String id, int schPk,
			JPanel datePanel[][], IRefreshListener iRefreshListener, CalenderClient client) {
		this(i, j, calYear, calMonth, calDates, calHour, calMinute, id, datePanel, iRefreshListener, client);
		this.content = content;
		this.schPk =schPk;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == datePanel[i][j]) {
			AddSchedule n = new AddSchedule(calYear, calMonth + 1, calDates[i][j], calHour, calMinute, id, client);
			n.setRefreshListener(iRefreshListener);
		} else {
			//ModifySchedule n2 = new ModifySchedule(calYear, calMonth + 1, calDates[i][j], content);
			OtherSchedule n2 = new OtherSchedule(calYear, calMonth + 1, calDates[i][j], content, id, schPk, calHour, calMinute);
			n2.setRefreshListener(iRefreshListener);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
