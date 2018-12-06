package com.java5.quanlynhanvien.DAO;

import java.util.List;

import com.java5.quanlynhanvien.model.Records;

public interface RecordsDAO {
	public List<Records> listRecords();

	public int addRecords(int manv, int type, String reason, String date);

	public List<Records> recordsByIdStaffs(int id_staffs);
}
