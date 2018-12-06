package com.java5.quanlynhanvien.Services;

import java.util.List;

import com.java5.quanlynhanvien.model.Departs;
import com.java5.quanlynhanvien.model.DepartsExtend;

public interface DepartsServises {
	public int addDeparts(Departs departs);

	public String deleteDeparts(int id);

	public String editDeparts(int id, String name);

	public List<Departs> listDeparts();

	public long sumPage(long showInPage);

	public List<Departs> showPagination(int min, int max);

	public long countStaffsInDeparts(int id);

	public List<DepartsExtend> listDepartsSl(int  min , int max);

}
