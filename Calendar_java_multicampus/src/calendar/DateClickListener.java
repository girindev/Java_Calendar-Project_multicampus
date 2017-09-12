package calendar;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import popUp.AddSchedule;
import popUp.IRefreshListener;
import popUp.ModifySchedule;
/**
 * @author �ڼ���
 * Ķ���� ��¥ �г� Ŭ��������
 * */
public class DateClickListener implements MouseListener {
	private int i, j;
	private String content;
	private String id;
	private int calYear, calMonth, calDates[][],calHour, calMinute;
	private JPanel datePanel[][];
	private IRefreshListener iRefreshListener;
	private int schPk;
	
	public DateClickListener(int i, int j, int calYear, int calMonth, 
			int calDates[][],int calHour, int calMinute, String id,
			JPanel datePanel[][], IRefreshListener iRefreshListener) {
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
		
	}

	public DateClickListener(int i, int j, String content, int calYear, int calMonth, 
			int calDates[][],int calHour, int calMinute, String id, int schPk,
			JPanel datePanel[][], IRefreshListener iRefreshListener) {
		this(i, j, calYear, calMonth, calDates, calHour, calMinute, id, datePanel, iRefreshListener);
		this.content = content;
		this.schPk =schPk;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == datePanel[i][j]) {
			AddSchedule n = new AddSchedule(calYear, calMonth + 1, calDates[i][j], calHour, calMinute, id);
			n.setRefreshListener(iRefreshListener);
		} else {
			ModifySchedule n2 = new ModifySchedule(calYear, calMonth + 1, calDates[i][j], content);
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