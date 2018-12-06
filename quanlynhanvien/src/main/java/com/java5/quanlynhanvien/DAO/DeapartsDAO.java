package com.java5.quanlynhanvien.DAO;

import java.util.List;

import com.java5.quanlynhanvien.model.Departs;
import com.java5.quanlynhanvien.model.DepartsExtend;

public interface DeapartsDAO {
	public List<Departs> listDeparts();

	public int addDeparts(Departs departs);

	public String editDeparts(int id, String name);

	public String deleteDeparts(int id);

	public long sumPage(long showInPage);

	public List<Departs> showPagination(int min, int max);

	public long countStaffsInDeparts(int id);

	public List<DepartsExtend> thanhTichPhongBan(int min ,int max);

}
