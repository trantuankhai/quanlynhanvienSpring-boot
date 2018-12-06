package com.java5.quanlynhanvien.Services;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java5.quanlynhanvien.model.Records;

@Service
public class SendMailService implements SendMailServices {
	@Autowired
	RecordsServicesImpl recordsServices;
	@Override
	public boolean senMail(String to, int id_staffs) {
		String content = "<h3><b>Bảng thành tích nhân viên</b><h3><br>"
				+ "<table border =\"1\">"
				+ "<tr> <th>Lý do</th> <th>Hình thức</th> <th>Ngày ghi nhận</th>  </tr>";
		List<Records> records = recordsServices.recordsByIdStaffs(id_staffs);
		for (Records x:records) {
			content+="<tr>"+"<td>"+x.getReason()+"</td> <td>"+x.getRecords_Type()+"</td> <td>"+x.getDate()+"</td> </tr"+"</table>";
		}
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("khaittph05424@fpt.edu.vn", "trantuankhai1997");
			}
		});
		try {
			Message msg = new MimeMessage(session);
			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress("khaittph05424@fpt.edu.vn"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			msg.setSubject("Thông Báo Thành Tích Nhân Viên");
			msg.setContent(content,"text/html;charset=UTF-8");
			Transport.send(msg);

		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

}
