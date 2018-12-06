package com.java5.quanlynhanvien.Services;

import java.util.List;

import com.java5.quanlynhanvien.model.Records;

public interface RecordsServices {
	public List<Records> listRecords();

	public int addRecords(int manv, int type, String reason, String date);

	public List<Records> recordsByIdStaffs(int id_staffs);

}
