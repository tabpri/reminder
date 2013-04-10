package net.enclosing.reminder;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import net.enclosing.gmail.EncodeStr;
import net.enclosing.gmail.SendMailGmail;

public class Remind {
	private static final int remindingDuration = 10;
	private static final int span = 100;
	private static final String remindedAddress = "project.102178079@todoist.net";
	private static final int TIMES = 10;
	public static void main(String[] args) {
		new Remind();
	}
	public Remind(){

		List<Reminder> reminders = net.enclosing.list.List.list(Reminder.class);
		for (Reminder reminder : reminders) {
			Remind.remind(reminder);
		}
	}
	private static void remind(Reminder reminder) {
		try {
			if(!shouldBeReminded(reminder)) return ;

			final  String remindedString =reminder.getName() + " for " + reminder.getProject();
			
			EncodeStr encodeStr = new EncodeStr();
			SendMailGmail sendMailGmail = new SendMailGmail();
			sendMailGmail.setGmailAccount("toukubo@gmail.com");
			sendMailGmail.setGmailPassword("zaq12wsx0101");
			sendMailGmail.setMailTo(remindedAddress);
			sendMailGmail.setMailSubject(encodeStr.utf82Iso2022jp(remindedString));
			sendMailGmail.setMailBody(encodeStr.utf82Iso2022jp(remindedString));
			try {
				sendMailGmail.send();
			} catch (MessagingException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static boolean shouldBeReminded(Reminder reminder) {
		
		Date remindEnd = addDate(reminder.getStartDate(),remindingDuration);
		boolean flag =  ( remindEnd.after(new Date()) && reminder.getStartDate().before(new Date()));

		for (int i = 0; i < TIMES; i++) {
			Date reRemindStartDate = addDate(reminder.getStartDate(), span); 
			Date reRemindEndDate = addDate(remindEnd, span); 
			if(( reRemindEndDate.after(new Date()) && 
					reRemindStartDate.before(new Date())))
				flag = true;
		}
		return flag;
	}
	private static Date addDate(Date startDate, int i) {
		Date workDate = new Date();
		workDate.setTime(startDate.getTime() +( (24 * 60 * 60 * 1000) * i));
		return workDate;
	}
}
