package com.java5.quanlynhanvien.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java5.quanlynhanvien.DAO.RecordsDaoImpl;
import com.java5.quanlynhanvien.model.Records;

@Service
public class RecordsServicesImpl implements RecordsServices {
	@Autowired
	private RecordsDaoImpl recordsDao;

	// GET LIST RECORDS
	@Override
	public List<Records> listRecords() {
		return recordsDao.listRecords();
	}

	/// ADD ONE RECORDS
	@Override
	public int addRecords(int manv, int type, String reason, String date) {
		return recordsDao.addRecords(manv, type, reason, date);

	}

	@Override
	public List<Records> recordsByIdStaffs(int id_staffs) {
		// TODO Auto-generated method stub
		return recordsDao.recordsByIdStaffs(id_staffs);
	}

}
