package view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import controller.BorrowTransactionHandler;
import model.Borrow;
import model.BorrowItem;

public class ViewBorrowHistoryForm extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	
	public ViewBorrowHistoryForm () {
		
		BorrowTransactionHandler bth = new BorrowTransactionHandler();
		
		int month = 12;
		int year = 2000;
		
		String sDate = "01/%02d/%d";
		sDate = String.format(sDate, month, year);
		Date date = null;
		
		try {
			
			date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
			
		} catch (Exception e) {
			
		}
		
		List<Borrow> lb = bth.getAcceptStatus(date);
        
		// TODO: show lb di sini
		
		Borrow b = new Borrow();
		List<BorrowItem> lbi = bth.getBookItem(b.getId());
		
		// TODO: show lbi di sini
		
		// TODO: kalau salah satu lbi di klik return keluar ini
		BorrowItem bi = new BorrowItem();
		
		// Create date
		Date dateNow = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
        String strDate = dateFormat.format(dateNow);
		
		HashMap<String, String> inputs = new HashMap<String, String>();
		inputs.put("id", bi.getId());
		inputs.put("bookId", bi.getBookId());
		inputs.put("returnTimestamp", strDate);
		
		if (bth.returnBook(inputs) != null) {
			
			JOptionPane.showMessageDialog(null, "Book is successfully returned");
			
		}
		
	}

}