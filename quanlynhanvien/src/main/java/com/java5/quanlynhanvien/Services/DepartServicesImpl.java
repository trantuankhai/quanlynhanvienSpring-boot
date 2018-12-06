package com.java5.quanlynhanvien.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java5.quanlynhanvien.DAO.DepartsDaoImpl;
import com.java5.quanlynhanvien.model.Departs;
import com.java5.quanlynhanvien.model.DepartsExtend;

@Service
public class DepartServicesImpl implements DepartsServises {
	@Autowired
	private DepartsDaoImpl departsDao;

	@Override
	public int addDeparts(Departs departs) {
		// TODO Auto-generated method stub
		return departsDao.addDeparts(departs);
	}

	@Override
	public String deleteDeparts(int id) {
		// TODO Auto-generated method stub

		return departsDao.deleteDeparts(id);

	}

	@Override
	public String editDeparts(int id, String name) {
		// TODO Auto-generated method stub
		return departsDao.editDeparts(id, name);
	}

	@Override
	public List<Departs> listDeparts() {
		// TODO Auto-generated method stub
		return departsDao.listDeparts();
	}

//  GET COUNT  PAGE
	@Override
	public long sumPage(long showInPage) {
		return departsDao.sumPage(showInPage);
	}

	// SHOW BETTEWN FROM X TO Y
	@Override
	public List<Departs> showPagination(int min, int max) {
		return departsDao.showPagination(min, max);
	}

	@Override
	public long countStaffsInDeparts(int id) {
		// TODO Auto-generated method stub
		return departsDao.countStaffsInDeparts(id);
	}

	@Override
	public List<DepartsExtend> listDepartsSl(int min, int max) {
		// TODO Auto-generated method stub
		return departsDao.thanhTichPhongBan(min, max);
	}

}
